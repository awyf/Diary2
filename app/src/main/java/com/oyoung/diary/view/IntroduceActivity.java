package com.oyoung.diary.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.oyoung.diary.R;
import com.oyoung.diary.controller.IntroduceAdapter;

import java.util.ArrayList;
import java.util.List;

public class IntroduceActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private Button button;
    private List<View> viewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        initView();
        initAdapter();
        initStart();
    }

    private void initStart() {
        button = viewList.get(2).findViewById(R.id.btn_goto);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroduceActivity.this, LoginActivity.class));
                IntroduceActivity.this.finish();
            }
        });
    }

    private void initAdapter() {
        IntroduceAdapter adapter = new IntroduceAdapter(viewList);
        viewPager.setAdapter(adapter);

    }

    private void initView() {
        ImageView imageView;
        viewPager = findViewById(R.id.introduce_viewPager);
        viewList = new ArrayList<>();
        viewList.add(getView(R.layout.introduce_a));
        viewList.add(getView(R.layout.introduce_b));
        viewList.add(getView(R.layout.introduce_c));
    }

    private View getView(int resId) {
        return LayoutInflater.from(this).inflate(resId,null);
    }
}