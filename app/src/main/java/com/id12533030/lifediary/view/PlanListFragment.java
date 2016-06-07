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

import com.google.android.gms.vision.text.Text;
import com.id12533030.lifediary.R;
import com.id12533030.lifediary.adapter.PlanListAdapter;
import com.id12533030.lifediary.model.Diary;
import com.id12533030.lifediary.model.Plan;
import com.id12533030.lifediary.util.Constants;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by LENOVO on 2016/6/6.
 */
public class PlanListFragment extends Fragment  implements PlanListAdapter.MyItemClickListener{
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

    public static PlanListFragment newInstance(int type) {
        PlanListFragment fragment = new PlanListFragment();
        // Supply diary input as an argument.
        Bundle args = new Bundle();
        args.putSerializable(PAGE_KEY, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mType = getArguments() != null ? (int) getArguments().getSerializable(PAGE_KEY) : 0;
        mInflater = getLayoutInflater(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_plan_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAllPlanList = (ArrayList<Plan>) Plan.listAll(Plan.class);
        mTypePlanList = (ArrayList<Plan>)Plan.findWithQuery(Plan.class, "Select * from Plan where type = ?", String.valueOf(mType));
        setAdapter();

    }


    private void init(View view) {
        mTitle = (TextView) view.findViewById(R.id.add_plan_title_textview);
        mDate = (TextView) view.findViewById(R.id.add_plan_date_textview);
        mLocation = (TextView) view.findViewById(R.id.add_plan_location_textview);
        mDetail = (TextView) view.findViewById(R.id.add_plan_text_textview);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.add_plan_radiogroup);
    }

    private void show(Plan plan){
        mTitle.setText(plan.getTitle());
        mDate.setText(plan.getDate());
        mLocation.setText(plan.getLocation());
        mDetail.setText(plan.getText());
        mRadioGroup.setVisibility(View.GONE);
    }

    private void update(Plan plan){
        plan.setTitle(mTitle.getText().toString());
        plan.setDate(mDate.getText().toString());
        plan.setLocation(mLocation.getText().toString());
        plan.setText(mDetail.getText().toString());
        plan.save();
    }

    private void setAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView = (RecyclerView) this.getView().findViewById(R.id.fragment_plan_list_recyclerView);
        mRecyclerView.setLayoutManager(layoutManager);
        mPlanAdapter = new PlanListAdapter(getContext(), mTypePlanList);
        mRecyclerView.setAdapter(mPlanAdapter);
        mPlanAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(View view,final int position) {
        Plan plan =  mTypePlanList.get(position);
        final Long id = plan.getId();
        view = mInflater.inflate(R.layout.add_plan,(ViewGroup) this.getView().findViewById(R.id.add_plan_dialog) );
        init(view);
        show(plan);
        new AlertDialog.Builder(getContext()).setTitle("Do you want to update your plan?").setView(view)
                .setPositiveButton(Constants.CONFIRM,  new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Plan newPlan = Plan.findById(Plan.class, id);
                        update(newPlan);
                        mPlanAdapter.updataAdapter(position, newPlan);
                        Toast.makeText(getContext(), "Update successfully", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(Constants.CANCEL, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();

    }

    @Override
    public void onItemLongClick(View view, int position) {
        Plan plan =  mTypePlanList.get(position);
        final Long id = plan.getId();
        final int pos = position;
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setMessage("Do you want to delete it?");
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
