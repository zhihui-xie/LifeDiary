package com.id12533030.lifediary.adapter;

import android.content.Context;
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

/**
 * Created by LENOVO on 2016/6/6.
 * This adapter is used to set the recyclerview to list diaries
 */
public class DiaryListAdapter extends RecyclerView.Adapter<DiaryListAdapter.Holder> {
    private LayoutInflater mInflater;
    private ArrayList<Diary> mDiaryList;
    private MyItemClickListener mItemClickListener = null;

    /**
     * Define the ItemClickListener interface
     */
    public interface MyItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    /**
     * Constructor of DiaryListAdapter
     *
     * @param context
     * @param data
     */
    public DiaryListAdapter(Context context, ArrayList<Diary> data) {
        mInflater = LayoutInflater.from(context);
        mDiaryList = data;
    }

    /**
     * Set the item click listener
     *
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener) {
        mItemClickListener = listener;
    }


    /**
     * Delete a diary and update the list
     *
     * @param postion
     */
    public void deleteItem(int postion) {
        mDiaryList.remove(postion);
        notifyDataSetChanged();
    }

    /**
     * Override the method onCreateViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public DiaryListAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.diary_list_item, parent, false);
        return new Holder(mView, mItemClickListener);
    }

    /**
     * Override the method onBindViewHolder to bind the content to widgets
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(DiaryListAdapter.Holder holder, int position) {
        Diary diary = mDiaryList.get(position);
        holder.mTitle.setText(diary.getTitle());
        holder.mDetail.setText(diary.getText());
        holder.mDatetime.setText(DateProcess.getDatetimeAsString(diary.getDate()));
        try {
            ImageTool.showImage(diary.getmPhotoUrl(), holder.mPhoto, Constants.LIST_ITEM_SIZE, Constants.LIST_ITEM_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Override the method get the count of the list
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return mDiaryList.size();
    }

    /**
     * Define the Holder class and implement OnClickListener and OnLongClickListener
     */
    static class Holder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private final ImageView mPhoto;
        private final TextView mTitle;
        private final TextView mDetail;
        private final TextView mDatetime;
        private LinearLayout mLayout;
        private MyItemClickListener mListener;

        /**
         * Constructor of Holder to bind the widgets and set the clickListener and longClickListener of every item
         *
         * @param view
         * @param listener
         */
        public Holder(View view, MyItemClickListener listener) {
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

        /**
         * Override the method onClick
         *
         * @param v
         */
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(v, getPosition());
            }
        }

        /**
         * Override the method onLongClick
         *
         * @param v
         * @return
         */
        @Override
        public boolean onLongClick(View v) {
            if (mListener != null) {
                mListener.onItemLongClick(v, getPosition());
            }
            return true;
        }
    }
}
