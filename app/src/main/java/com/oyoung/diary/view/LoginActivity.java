package com.oyoung.diary.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.oyoung.diary.MainActivity;
import com.oyoung.diary.R;
import com.oyoung.diary.utils.FileUtils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edit_input_text;
    private EditText edit_input_text_again;
    private Button btn_comeIn;
    private Button btn_goto_psw;
    private static final String TAG = "LoginMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindView();
    }

    private void bindView() {
        edit_input_text = findViewById(R.id.edit_input_text);
        edit_input_text_again = findViewById(R.id.edit_input_text_again);
        btn_comeIn = findViewById(R.id.btn_comeIn);
        btn_comeIn.setOnClickListener(this);
        btn_goto_psw = findViewById(R.id.btn_goto_psw);
        btn_goto_psw.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_comeIn) {
            String psw = edit_input_text.getText().toString().trim();
            String psw_again = edit_input_text_again.getText().toString().trim();
            if (psw.isEmpty()) {
                Toast.makeText(this, "密码不能为空!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (psw_again.isEmpty()) {
                Toast.makeText(this, "请再次输入密码!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!psw.equals(psw_again)) {
                Toast.makeText(this, "两次密码不一致!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (FileUtils.saveInfoByContext(this, psw)) {
                Log.d(TAG, "onClick: saveInfoByContext==>c");
                Toast.makeText(this, "登录成功!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }

        } else if (v.getId() == R.id.btn_goto_psw) {
            Intent intent = new Intent(this, LoginDirectActivity.class);
            startActivity(intent);
        }
    }

}
