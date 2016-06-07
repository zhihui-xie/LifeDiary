package com.id12533030.lifediary.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.id12533030.lifediary.R;
import com.id12533030.lifediary.adapter.PlanListAdapter;
import com.id12533030.lifediary.model.Plan;
import com.id12533030.lifediary.util.Constants;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by LENOVO on 2016/6/6.
 * The fragment is used to show the list of plans and implements the itemClickListener
 */
public class PlanListFragment extends Fragment implements PlanListAdapter.MyItemClickListener {
    private RecyclerView mRecyclerView;
    private PlanListAdapter mPlanAdapter;
    private ArrayList<Plan> mAllPlanList;
    private ArrayList<Plan> mTypePlanList;
    private int mType;
    private static final String PAGE_KEY = "PAGE";
    private LayoutInflater mInflater;
    private TextView mTitle;
    private TextView mDate;
    private TextView mLocation;
    private TextView mDetail;
    private RadioGroup mRadioGroup;
    private static final String SELECT_PLAN_BY_TYPE = "Select * from Plan where type = ?";


    /**
     * New an instance with status and pass the parameter
     *
     * @param type
     * @return
     */
    public static PlanListFragment newInstance(int type) {
        PlanListFragment fragment = new PlanListFragment();
        // Supply diary input as an argument.
        Bundle args = new Bundle();
        args.putSerializable(PAGE_KEY, type);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Override the onCreate method and record the type of plans
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments() != null ? (int) getArguments().getSerializable(PAGE_KEY) : 0;
        mInflater = getLayoutInflater(savedInstanceState);
    }

    /**
     * Override the onCreateView method
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_plan_list, container, false);
    }

    /**
     * Override the onViewCreated method
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAllPlanList = (ArrayList<Plan>) Plan.listAll(Plan.class);
        mTypePlanList = (ArrayList<Plan>) Plan.findWithQuery(Plan.class, SELECT_PLAN_BY_TYPE, String.valueOf(mType));
        Collections.reverse(mTypePlanList);
        setAdapter();
    }

    /**
     * Initial all the widgets
     *
     * @param view
     */
    private void init(View view) {
        mTitle = (TextView) view.findViewById(R.id.add_plan_title_textview);
        mDate = (TextView) view.findViewById(R.id.add_plan_date_textview);
        mLocation = (TextView) view.findViewById(R.id.add_plan_location_textview);
        mDetail = (TextView) view.findViewById(R.id.add_plan_text_textview);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.add_plan_radiogroup);
    }

    /**
     * Show the plan details
     *
     * @param plan
     */
    private void show(Plan plan) {
        mTitle.setText(plan.getTitle());
        mDate.setText(plan.getDate());
        mLocation.setText(plan.getLocation());
        mDetail.setText(plan.getText());
        mRadioGroup.setVisibility(View.GONE);
    }

    /**
     * Updata the plan details
     *
     * @param plan
     */
    private void update(Plan plan) {
        plan.setTitle(mTitle.getText().toString());
        plan.setDate(mDate.getText().toString());
        plan.setLocation(mLocation.getText().toString());
        plan.setText(mDetail.getText().toString());
        plan.save();
    }

    /**
     * Set the adapter
     */
    private void setAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView = (RecyclerView) this.getView().findViewById(R.id.fragment_plan_list_recyclerView);
        mRecyclerView.setLayoutManager(layoutManager);
        mPlanAdapter = new PlanListAdapter(getContext(), mTypePlanList);
        mRecyclerView.setAdapter(mPlanAdapter);
        mPlanAdapter.setOnItemClickListener(this);
    }

    /**
     * Implement the itemClick to update the item
     *
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(View view, final int position) {
        Plan plan = mTypePlanList.get(position);
        final Long id = plan.getId();
        view = mInflater.inflate(R.layout.add_plan, (ViewGroup) this.getView().findViewById(R.id.add_plan_dialog));
        init(view);
        show(plan);
        new AlertDialog.Builder(getContext()).setTitle(Constants.UPDATE_DIALOG_TITLE).setView(view)
                .setPositiveButton(Constants.CONFIRM, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Plan newPlan = Plan.findById(Plan.class, id);
                        update(newPlan);
                        mPlanAdapter.updataAdapter(position, newPlan);
                        Toast.makeText(getContext(), Constants.UPDATE_RESULT, Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(Constants.CANCEL, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }

    /**
     * Implement the itemLongClick method to delete the item
     *
     * @param view
     * @param position
     */
    @Override
    public void onItemLongClick(View view, int position) {
        Plan plan = mTypePlanList.get(position);
        final Long id = plan.getId();
        final int pos = position;
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setMessage(Constants.DELETE_DIALOG_TITLE);
        builder.setPositiveButton(Constants.CONFIRM, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Plan delPlan = Plan.findById(Plan.class, id);
                delPlan.delete();
                mPlanAdapter.deleteItem(pos);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(Constants.CANCEL, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
