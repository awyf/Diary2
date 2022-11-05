package com.oyoung.diary.controller;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oyoung.diary.model.Diary;

import java.util.List;

public class DiariesAdapter extends RecyclerView.Adapter<DiaryHolder> {
    private List<Diary> mDiaries;
    private OnLongClickListener<Diary> mOnLongClickListener;
    public DiariesAdapter(List<Diary> diaries) {
        mDiaries = diaries;
    }

    public void update(List<Diary> diaries) {
        mDiaries = diaries;
        notifyDataSetChanged();
    }

    public void setOnLongClickListener(OnLongClickListener<Diary> onLongClickListener) {
        this.mOnLongClickListener = onLongClickListener;
    }

    @NonNull
    @Override
    public DiaryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DiaryHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryHolder holder, int position) {
        final Diary diary = mDiaries.get(position);
        holder.onBindView(diary);
        holder.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return mOnLongClickListener != null && mOnLongClickListener.onLongClick(view, diary);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDiaries.size();
    }

    public interface OnLongClickListener<T> {
        boolean onLongClick(View v, T data);
    }


}
