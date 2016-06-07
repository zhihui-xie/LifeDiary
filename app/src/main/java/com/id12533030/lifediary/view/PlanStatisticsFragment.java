package com.id12533030.lifediary.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.id12533030.lifediary.R;
import com.id12533030.lifediary.model.Plan;

import java.util.ArrayList;

/**
 * Created by LENOVO on 2016/6/7.
 * This fragment is to show the statistics of plans
 */
public class PlanStatisticsFragment extends Fragment {
    private TextView urgent;
    private TextView normal;
    //These two variables are used to implement lazy load method
    protected boolean isVisible;
    protected boolean isPrepared;

    /**
     * Override the onCreateView method
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plan_statistics, container, false);
        isPrepared = true;
        lazyLoad();
        return view;
    }

    /**
     * When creating, retrieve this instance's number from its arguments.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * Override the onStart method and bind the widgets
     */
    @Override
    public void onStart() {
        super.onStart();
        urgent = (TextView) this.getView().findViewById(R.id.plan_statistics_urgent_number);
        normal = (TextView) this.getView().findViewById(R.id.plan_statistics_normal_number);
    }

    /**
     * Check whether the fragment is visible to avoid preload the fragment
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            lazyLoad();
        }
    }

    /**
     * Prevent the preload of fragment, which will make it fail to update in time. This method ensures
     * that fragment will be only loaded when visible
     */
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        ArrayList<Plan> urgentList = (ArrayList<Plan>) Plan.findWithQuery(Plan.class, "Select * from Plan where type = 0");
        ArrayList<Plan> normalList = (ArrayList<Plan>) Plan.findWithQuery(Plan.class, "Select * from Plan where type = 1");
        urgent.setText(String.valueOf(urgentList.size()));
        normal.setText(String.valueOf(normalList.size()));
    }
}
