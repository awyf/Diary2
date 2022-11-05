package com.oyoung.diary.model;

import androidx.annotation.Nullable;

import com.google.gson.reflect.TypeToken;
import com.oyoung.diary.test.MockDiaries;
import com.oyoung.diary.utils.CollectionUtils;
import com.oyoung.diary.utils.GsonUtils;
import com.oyoung.diary.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DiariesLocalDataSource implements DataSource<Diary>{
    private static DiariesLocalDataSource instance;
    private static Map<String, Diary> LOCAL_DATA = new HashMap<>();
    private static final String DIARY_DATA = "diary_data";
    private static final String ALL_DIARY = "all_diary";
    private static SharedPreferencesUtils mSpUtils;

    private DiariesLocalDataSource() {
        mSpUtils = SharedPreferencesUtils.getInstance(DIARY_DATA);
        String diaryStr = mSpUtils.get(ALL_DIARY);
        LinkedHashMap<String, Diary> diariesObj = json2Obj(diaryStr);
        if (!CollectionUtils.isEmpty(diariesObj)) {
            LOCAL_DATA = diariesObj;
        } else {
            LOCAL_DATA = MockDiaries.mock();
        }
    }

    public static DiariesLocalDataSource getInstance() {
        if (instance == null) {
            synchronized (DiariesLocalDataSource.class) {
                if (instance == null) {
                    instance = new DiariesLocalDataSource();
                }
            }
        }
        return instance;
    }

    private String obj2Json() {
        return GsonUtils.toJson(LOCAL_DATA);
    }

    private LinkedHashMap<String, Diary> json2Obj(String diaryStr) {
        return GsonUtils.fromJson(diaryStr, new TypeToken<LinkedHashMap<String, Diary>>(){}.getType());
    }

    @Override
    public void getAll(@Nullable final DataCallback<List<Diary>> callBack) {
        if (LOCAL_DATA.isEmpty()) {
            callBack.onError(); // 通知查询错误
        } else {
            callBack.onSuccess(new ArrayList<>(LOCAL_DATA.values())); // 通知查询成功
        }

    }

    @Override
    public void get(@Nullable String id, @Nullable DataCallback<Diary> callBack) {
        Diary diary = LOCAL_DATA.get(id);
        if (diary != null) {
            callBack.onSuccess(diary);
        } else {
            callBack.onError();
        }
    }

    @Override
    public void update(@Nullable Diary diary) {
        LOCAL_DATA.put(diary.getId(), diary);
        mSpUtils.put(ALL_DIARY, obj2Json());
    }

    @Override
    public void clear() {
        LOCAL_DATA.clear();
        mSpUtils.remove(ALL_DIARY);
    }

    @Override
    public void delete(@Nullable String id) {
        LOCAL_DATA.remove(id);
        mSpUtils.put(ALL_DIARY, obj2Json());

    }
}
