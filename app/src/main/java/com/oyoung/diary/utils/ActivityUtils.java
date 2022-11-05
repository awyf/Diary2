package com.oyoung.diary.utils;

import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class ActivityUtils {
    private static List<Fragment> fragmentList = new ArrayList<>();
    public static void addFragmentToActivity(FragmentManager manager, Fragment fragment, int fragmentId) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(fragmentId, fragment);
        transaction.commit();
    }
}
