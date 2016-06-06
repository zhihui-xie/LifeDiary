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
public class PlanListAdapter extends RecyclerView.Adapter<PlanListAdapter.Holder>{
    private static final int ITEMS_COUNT = 64;
    private LayoutInflater mInflater;

    public PlanListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(mInflater.inflate(R.layout.plan_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return ITEMS_COUNT;
    }

    static class Holder extends RecyclerView.ViewHolder {
        private final ImageView mPhoto;
        private final TextView mTitle;
        private final TextView mDetail;
        private final TextView mDate;
        private final TextView mLocation;

        public Holder(View view) {
            super(view);
            mPhoto = (ImageView) view.findViewById(R.id.plan_list_item_photo_imageview);
            mTitle = (TextView) view.findViewById(R.id.plan_list_item_title_textview);
            mDetail = (TextView) view.findViewById(R.id.plan_list_item_detail_textview);
            mDate = (TextView) view.findViewById(R.id.plan_list_item_time_textview);
            mLocation = (TextView) view.findViewById(R.id.plan_list_item_location_textview);
        }
    }

}
