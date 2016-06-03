package com.id12533030.lifediary.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.id12533030.lifediary.R;
import com.id12533030.lifediary.model.Homepage;
import com.id12533030.lifediary.util.ImageTool;

import java.io.IOException;

/**
 * Created by LENOVO on 2016/5/29.
 */
public class MainFragment extends Fragment {
    int mIndex;
    private static final String NUM_KEY = "num";
    private Homepage mHomepage;
    private boolean mHasLoadedOnce = false;
    private static final String PAGE_KEY = "PAGE";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    public static MainFragment newInstance(Homepage homepage) {
        MainFragment fragment = new MainFragment();
//        mHomepage = homepage;
        Bundle args = new Bundle();
        args.putSerializable("PAGE", homepage);
        // Supply num input as an argument.
//        Bundle args = new Bundle();
//        args.putInt(NUM_KEY, num);
//        fragment.setArguments(args);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * When creating, retrieve this instance's number from its arguments.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomepage = (Homepage) getArguments().getSerializable(PAGE_KEY);
//        mIndex = getArguments() != null ? getArguments().getInt(NUM_KEY) : 1;
    }

    @Override
    public void onStart() {
        super.onStart();
//        if (!mHasLoadedOnce)
//            return;
        ImageView imageView = (ImageView)  this.getView().findViewById(R.id.fragment_main_photo_imageview);
        try {
            ImageTool.showImage(mHomepage.getmPhotoUrl(), imageView);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TextView textView = (TextView) this.getView().findViewById(R.id.fragment_main_title_textview);
        textView.setText(mHomepage.getTitle());

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        // TODO Auto-generated method stub
        if (isVisibleToUser) {
            //fragment可见时加载数据
            mHasLoadedOnce = true;
        } else {
            //不可见时不执行操作
        }
        super.setUserVisibleHint(isVisibleToUser);
    }


}
