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
import com.id12533030.lifediary.model.Plan;

import java.util.ArrayList;

/**
 * Created by LENOVO on 2016/6/6.
 * PlanListAdapter is an adapter of RecyclerView and is used to set the plan list
 */
public class PlanListAdapter extends RecyclerView.Adapter<PlanListAdapter.Holder> {
    private LayoutInflater mInflater;
    private ArrayList<Plan> mPlanList;
    private Context mContext;
    private MyItemClickListener mItemClickListener = null;

    /**
     * Define the ItemClickListener interface
     */
    public interface MyItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    /**
     * Constructor of PlanListAdapter.Pass the context and list to the adapter
     *
     * @param context
     * @param plan
     */
    public PlanListAdapter(Context context, ArrayList<Plan> plan) {
        mInflater = LayoutInflater.from(context);
        mPlanList = plan;
        mContext = context;
    }

    /**
     * Set the listener
     *
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener) {
        mItemClickListener = listener;
    }

    /**
     * Override the method onCreateViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.plan_list_item, parent, false);
        return new Holder(mView, mItemClickListener);
    }

    /**
     * Override the method onBindViewHolder to bind the content to widgets
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Plan plan = mPlanList.get(position);
        holder.mTitle.setText(plan.getTitle());
        holder.mDetail.setText(plan.getText());
        holder.mDate.setText(plan.getDate());
        holder.mLocation.setText(plan.getLocation());
        setImage(holder.mPhoto, plan.getType());
    }

    /**
     * The method is to set different images to different types of plan
     *
     * @param image
     * @param type
     */
    private void setImage(ImageView image, int type) {
        switch (type) {
            case 0:
                image.setImageDrawable(mContext.getResources().getDrawable(R.drawable.urgent));
                break;
            case 1:
                image.setImageDrawable(mContext.getResources().getDrawable(R.drawable.normal));
                break;
            default:
                break;
        }
    }

    /**
     * Override the method get the count of the list
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return mPlanList.size();
    }

    /**
     * Define the Holder class and implement OnClickListener and OnLongClickListener
     */
    static class Holder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private final ImageView mPhoto;
        private final TextView mTitle;
        private final TextView mDetail;
        private final TextView mDate;
        private final TextView mLocation;
        private MyItemClickListener mListener;
        private LinearLayout mLayout;

        /**
         * Constructor of Holder to bind the widgets and set the clickListener and longClickListener of every item
         *
         * @param view
         * @param listener
         */
        public Holder(View view, MyItemClickListener listener) {
            super(view);
            mPhoto = (ImageView) view.findViewById(R.id.plan_list_item_photo_imageview);
            mTitle = (TextView) view.findViewById(R.id.plan_list_item_title_textview);
            mDetail = (TextView) view.findViewById(R.id.plan_list_item_detail_textview);
            mDate = (TextView) view.findViewById(R.id.plan_list_item_time_textview);
            mLocation = (TextView) view.findViewById(R.id.plan_list_item_location_textview);
            mLayout = (LinearLayout) view.findViewById(R.id.plan_list_item_linearlayout);
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

    public void updataAdapter(int position, Plan newPlan) {
        mPlanList.set(position, newPlan);
        notifyDataSetChanged();
    }

    public void deleteItem(int postion) {
        mPlanList.remove(postion);
        notifyDataSetChanged();
    }
}
