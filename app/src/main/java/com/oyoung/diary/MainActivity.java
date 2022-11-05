package com.oyoung.diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.oyoung.diary.utils.ActivityUtils;
import com.oyoung.diary.view.DiariesFragment;
import com.oyoung.diary.view.MeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initFragment();
        initNavigationBottom();
    }

    private void initNavigationBottom() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(itemSelectedListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private void initFragment() {
        DiariesFragment diariesFragment = getDiariesFragment();
        if (diariesFragment == null) {
            diariesFragment = new DiariesFragment();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), diariesFragment, R.id.content);
        }
    }

    private DiariesFragment getDiariesFragment() {
        return (DiariesFragment) getSupportFragmentManager().findFragmentById(R.id.content);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener itemSelectedListener = item -> {
        switch (item.getItemId()) {
            case R.id.menu_diary:
                ActivityUtils.removeFragmentTOActivity(getSupportFragmentManager(), getSupportFragmentManager().findFragmentById(R.id.content));
                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), new DiariesFragment(), R.id.content);
                break;
            case R.id.menu_me:
                ActivityUtils.removeFragmentTOActivity(getSupportFragmentManager(), getSupportFragmentManager().findFragmentById(R.id.content));
                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), new MeFragment(), R.id.content);
                break;
        }
        return true;
    };
}