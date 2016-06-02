package com.id12533030.lifediary.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.id12533030.lifediary.R;

/**
 * Created by LENOVO on 2016/5/29.
 */
public class MainFragment extends Fragment {
    int mNum;
    private static final String NUM_KEY = "num";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    public static MainFragment newInstance(int num) {
        MainFragment fragment = new MainFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt(NUM_KEY, num);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * When creating, retrieve this instance's number from its arguments.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments() != null ? getArguments().getInt(NUM_KEY) : 1;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getArguments().getInt(NUM_KEY) == 2){
            TextView textView = (TextView) this.getView().findViewById(R.id.fragment_main_title_textview);
            textView.setText("This is assignment 3");
        }
    }
}
