package com.oyoung.diary.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.oyoung.diary.MainActivity;
import com.oyoung.diary.R;
import com.oyoung.diary.utils.FileUtils;

public class LoginDirectActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edit_input_text;
    private Button btn_comeIn;
    private static final String TAG = "Login2Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_login);
        bindView();
    }

    private void bindView() {
        edit_input_text = findViewById(R.id.edit_login2_input_text);
        btn_comeIn = findViewById(R.id.btn_login2_comeIn);
        btn_comeIn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        String psw = edit_input_text.getText().toString().trim();
        if (psw.isEmpty()) {
            Toast.makeText(this, "密码不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }
        String readInfoByContext = FileUtils.readInfoByContext(this);
        if (psw.equals(readInfoByContext)) {
            Toast.makeText(this, "登录成功!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "密码不正确!", Toast.LENGTH_SHORT).show();
        }
    }
}
