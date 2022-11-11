package com.oyoung.diary.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oyoung.diary.YyApplication;
import com.oyoung.diary.R;
import com.oyoung.diary.model.DataCallback;
import com.oyoung.diary.model.DiariesRepository;
import com.oyoung.diary.model.Diary;
import com.oyoung.diary.utils.ActivityUtils;
import com.oyoung.diary.view.AddDiaryFragment;
import com.oyoung.diary.view.DiariesFragment;

import java.util.ArrayList;
import java.util.List;

public class DiariesController {
    private Fragment mView;
    private DiariesAdapter mListAdapter;
    private DiariesRepository mDiariesRepository;
    public DiariesController(@NonNull DiariesFragment diariesFragment) {
        mDiariesRepository = DiariesRepository.getInstance();
        mView = diariesFragment;
        mView.setHasOptionsMenu(true);
        initAdapter();
    }

    private void initAdapter() {
        mListAdapter = new DiariesAdapter(new ArrayList<Diary>());
        mListAdapter.setOnLongClickListener(new DiariesAdapter.OnLongClickListener<Diary>() {
            @Override
            public boolean onLongClick(View v, Diary data) {
                showDeleteDialog(data);
                return false;
            }
        });
        mListAdapter.setOnClickListener(new DiariesAdapter.OnClickListener<Diary>() {
            @Override
            public void onClick(View v, Diary data) {
                showInputDialog(data);
            }
        });
    }

    private void showDeleteDialog(Diary data) {
        new AlertDialog.Builder(mView.getContext()).setMessage(YyApplication.get().getString(R.string.dialog_delete) + data.getTitle())
                .setPositiveButton(YyApplication.get().getString(R.string.ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mDiariesRepository.delete(data.getId());
                                loadDiaries();
                            }
                        })
                .setNegativeButton(YyApplication.get().getString(R.string.cancel), null).show();
    }

    private void showDetailDiary(final Diary diary) {

    }


    private void showInputDialog(final Diary data) {
        final EditText editText = new EditText(mView.getContext());
        editText.setText(data.getDescription());
        new AlertDialog.Builder(mView.getContext()).setTitle(data.getTitle())
                .setView(editText)
                .setPositiveButton(YyApplication.get().getString(R.string.ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                data.setDescription(editText.getText().toString());
                                mDiariesRepository.update(data);
                                loadDiaries();
                            }
                        })
                .setNegativeButton(YyApplication.get().getString(R.string.cancel), null).show();
    }

    public void setDiariesList(RecyclerView recycleView) {
        recycleView.setLayoutManager(new LinearLayoutManager(mView.getContext()));
        recycleView.setAdapter(mListAdapter);
        recycleView.addItemDecoration(new DividerItemDecoration(mView.getContext(), DividerItemDecoration.VERTICAL));
        recycleView.setItemAnimator(new DefaultItemAnimator());
    }

    public void loadDiaries() {
        mDiariesRepository.getAll(new DataCallback<List<Diary>>() {
            @Override
            public void onSuccess(List<Diary> diaryList) {
                processDiaries(diaryList);
            }

            @Override
            public void onError() {
                showError();
            }
        });
    }

    public void gotoWriteDiary(FragmentManager fragmentManager, Fragment fragment) {
        new AlertDialog.Builder(mView.getContext())
                .setMessage(YyApplication.get().getString(R.string.alert))
                .setPositiveButton(YyApplication.get().getString(R.string.ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityUtils.removeFragmentTOActivity(fragmentManager, fragment);
                                ActivityUtils.addFragmentToActivity(fragmentManager, new AddDiaryFragment(), R.id.content);
                            }
                        })
                .setNegativeButton(YyApplication.get().getString(R.string.cancel), null).show();

//        showMessage(mView.getString(R.string.developing));
    }

    private void showError() {
        showMessage(mView.getString(R.string.error));
    }

    private void processDiaries(List<Diary> diaryList) {
        mListAdapter.update(diaryList);
    }

    private void showMessage(String message) {
        Toast.makeText(mView.getContext(), message, Toast.LENGTH_SHORT).show();
    }



}
