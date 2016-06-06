package com.id12533030.lifediary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.id12533030.lifediary.R;

/**
 * Created by LENOVO on 2016/6/6.
 */
public class DiaryListAdapter extends RecyclerView.Adapter<DiaryListAdapter.Holder>{
    private static final int ITEMS_COUNT = 64;
    private LayoutInflater mInflater;

    public DiaryListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public DiaryListAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(mInflater.inflate(R.layout.diary_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(DiaryListAdapter.Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return ITEMS_COUNT;
    }

    static class Holder extends RecyclerView.ViewHolder {
        private final ImageView mPhoto;
        private final TextView mTitle;
        private final TextView mDetail;

        public Holder(View view) {
            super(view);
            mPhoto = (ImageView) view.findViewById(R.id.diary_main_photo_imageview);
            mTitle = (TextView) view.findViewById(R.id.diary_main_title_textview);
            mDetail = (TextView) view.findViewById(R.id.diary_main_detail_textview);
        }
    }
}
