package com.oyoung.diary;

import android.app.Application;

import com.oyoung.diary.utils.GlideUtils;

public class EnApplication extends Application {
    private static EnApplication INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        GlideUtils.init(getApplicationContext());
        INSTANCE = this;
    }

    public static EnApplication get() {
        return INSTANCE;
    }
}
