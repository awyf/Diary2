package com.oyoung.diary.model;

import androidx.annotation.Nullable;

import java.util.List;

public interface DataSource<T> {
    // 获取所有数据
    void getAll(@Nullable DataCallback<List<T>> callBack);
    // 获取某个数据T
    void get(@Nullable String id, @Nullable DataCallback<T> callBack);
    // 更新某个数据
    void update(@Nullable T diary);
    // 清空某个数据
    void clear();
    // 删除某个数据
    void delete(@Nullable String id);

}
