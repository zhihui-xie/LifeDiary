package com.id12533030.lifediary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.id12533030.lifediary.R;

/**
 * Created by LENOVO on 2016/6/6.
 */
public class PlanListAdapter extends RecyclerView.Adapter<PlanListAdapter.Holder>{
    private static final int ITEMS_COUNT = 64;
    private LayoutInflater inflater;

    public PlanListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(inflater.inflate(R.layout.plan_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return ITEMS_COUNT;
    }

    static class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }

}
