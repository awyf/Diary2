package com.oyoung.diary.model;

import androidx.annotation.Nullable;
import com.oyoung.diary.utils.CollectionUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DiariesRepository implements DataSource<Diary>{ // 数据仓库
    private static volatile DiariesRepository mInstance; // 数据仓库实例
    private final DataSource<Diary> mLocalDataSource;// 本地数据源
    private Map<String, Diary> mMemoryCache;          // 内存缓存

    public DiariesRepository() {
        mMemoryCache = new LinkedHashMap<>();
        mLocalDataSource = DiariesLocalDataSource.getInstance();
    }

    public static DiariesRepository getInstance() {
        if (mInstance == null) {
            synchronized (DiariesRepository.class) {
                if (mInstance == null) {
                    mInstance = new DiariesRepository();
                }
            }
        }
        return mInstance;
    }

    @Override
    public void getAll(@Nullable DataCallback<List<Diary>> callBack) {
        if (!CollectionUtils.isEmpty(mMemoryCache)) {
            callBack.onSuccess(new ArrayList<>(mMemoryCache.values()));
            return;
        }
        mLocalDataSource.getAll(new DataCallback<List<Diary>>() {
            @Override
            public void onSuccess(List<Diary> data) {
                updateMemoryCache(data);// 更新内存缓存数据
                callBack.onSuccess(new ArrayList<>(mMemoryCache.values())); // 回调通知
            }
            @Override
            public void onError() {
                callBack.onError();
            }
        });
    }

    @Override
    public void get(@Nullable String id, @Nullable DataCallback<Diary> callBack) {
        Diary cachedDiary = getDiaryByIdFromMemory(id);
        if (cachedDiary != null) {
            callBack.onSuccess(cachedDiary);
            return;
        }
        mLocalDataSource.get(id, new DataCallback<Diary>() {
            @Override
            public void onSuccess(Diary data) {
                mMemoryCache.put(data.getId(), data);
                callBack.onSuccess(data);
            }
            @Override
            public void onError() {
                callBack.onError();
            }
        });
    }

    private void updateMemoryCache(List<Diary> diaryList) {
        mMemoryCache.clear();
        for (Diary diary : diaryList) {
            mMemoryCache.put(diary.getId(), diary);
        }
    }

    private Diary getDiaryByIdFromMemory(String id) {
        if (CollectionUtils.isEmpty(mMemoryCache)) {
            return null;
        } else {
            return mMemoryCache.get(id);
        }
    }

    @Override
    public void update(@Nullable Diary diary) {
        mLocalDataSource.update(diary);
        mMemoryCache.put(diary.getId(), diary);
    }

    @Override
    public void clear() {
        mLocalDataSource.clear();
        mMemoryCache.clear();
    }

    @Override
    public void delete(@Nullable String id) {
        mLocalDataSource.delete(id);
        mMemoryCache.remove(id);
    }
}
