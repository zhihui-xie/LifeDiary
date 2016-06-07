package com.id12533030.lifediary.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.id12533030.lifediary.R;
import com.id12533030.lifediary.model.Diary;
import com.id12533030.lifediary.util.Constants;
import com.id12533030.lifediary.util.DateProcess;
import com.id12533030.lifediary.util.ImageTool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 2016/6/6.
 */
public class DiaryListAdapter extends RecyclerView.Adapter<DiaryListAdapter.Holder>{
    private LayoutInflater mInflater;
    private ArrayList<Diary> mDiaryList;
    private int mIndex;

    public DiaryListAdapter(Context context, ArrayList<Diary> data) {
        mInflater = LayoutInflater.from(context);
        mDiaryList = data;
    }

    private MyItemClickListener mItemClickListener = null;

    public void setOnItemClickListener(MyItemClickListener listener){
        mItemClickListener = listener;
    }

    public interface MyItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void deleteItem(int postion){
        mDiaryList.remove(postion);
        notifyDataSetChanged();
    }

    @Override
    public DiaryListAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.diary_list_item, parent, false);
        return new Holder(mView, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(DiaryListAdapter.Holder holder, int position) {
        Diary diary = mDiaryList.get(position);
        holder.mTitle.setText(diary.getTitle());
        holder.mDetail.setText(diary.getText());
        holder.mDatetime.setText(DateProcess.getDatetimeAsString(diary.getDate()));
        try {
            ImageTool.showImage(diary.getmPhotoUrl(),holder.mPhoto, Constants.LIST_ITEM_SIZE, Constants.LIST_ITEM_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mIndex = position;
    }


    @Override
    public int getItemCount() {
        return mDiaryList.size();
    }

    static class Holder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        private final ImageView mPhoto;
        private final TextView mTitle;
        private final TextView mDetail;
        private final TextView mDatetime;
        private LinearLayout mLayout;
        private MyItemClickListener mListener;

        public Holder(View view,  MyItemClickListener listener) {
            super(view);
            mPhoto = (ImageView) view.findViewById(R.id.diary_main_photo_imageview);
            mTitle = (TextView) view.findViewById(R.id.diary_main_title_textview);
            mDetail = (TextView) view.findViewById(R.id.diary_main_detail_textview);
            mDatetime = (TextView) view.findViewById(R.id.diary_main_datetime_textview);
            mLayout = (LinearLayout) view.findViewById(R.id.diary_list_item_linearlayout);
            mListener = listener;
            mLayout.setOnClickListener(this);
            mLayout.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mListener != null){
                mListener.onItemClick(v, getPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if(mListener != null){
                mListener.onItemLongClick(v, getPosition());
            }
            return true;
        }
    }
}
