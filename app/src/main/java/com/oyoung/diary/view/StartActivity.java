package com.oyoung.diary.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.oyoung.diary.R;
import com.oyoung.diary.utils.GlideUtils;

public class StartActivity extends AppCompatActivity {

    private final StartHandler mHandler = new StartHandler();
    private class StartHandler extends Handler {
        private static final int GO_LOGIN = 1;
        private static final int GO_INTRODUCE = 0;
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case GO_LOGIN:
                    Intent login_intent = new Intent(StartActivity.this, LoginActivity.class);
                    startActivity(login_intent);
                    StartActivity.this.finish();
                    break;
                case GO_INTRODUCE:
                    Intent login2_intent = new Intent(StartActivity.this, IntroduceActivity.class);
                    startActivity(login2_intent);
                    StartActivity.this.finish();
                    break;
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        SharedPreferences sp = getSharedPreferences("Start", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        boolean isFirst = sp.getBoolean("isFirst", true);
        if (isFirst) {
            mHandler.sendEmptyMessage(0);
            editor.putBoolean("isFirst", false);
            editor.apply();
        } else {
            mHandler.sendEmptyMessage(1);
        }
    }
}