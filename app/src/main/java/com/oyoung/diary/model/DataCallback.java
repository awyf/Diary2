package com.oyoung.diary.model;

public interface DataCallback<T> {// 定义回调接口
    void onSuccess(T data);// 通知成功
    void onError();// 通知失败
}
