package com.id12533030.lifediary.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.id12533030.lifediary.R;
import com.id12533030.lifediary.adapter.PlanListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LENOVO on 2016/6/6.
 */
public class PlanListFragment extends BaseFragment {
    @BindView(R.id.fragment_plan_list_recyclerView)RecyclerView recyclerView;

    @Override
    int getLayoutId() {
        return R.layout.fragment_plan_list;
    }

    @Override
    public void onViewCreated(View view,@Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }


    private void init() {
        recyclerView.setAdapter(new PlanListAdapter(getContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
