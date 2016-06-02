package com.id12533030.lifediary.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.id12533030.lifediary.R;
import com.id12533030.lifediary.model.Homepage;
import com.id12533030.lifediary.model.HomepageManager;
import com.id12533030.lifediary.util.ImageTool;

import java.io.IOException;

/**
 * Created by LENOVO on 2016/5/29.
 */
public class MainFragment extends Fragment {
    int mIndex;
    private static final String NUM_KEY = "num";
    private static HomepageManager mHomepageManager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    public static MainFragment newInstance(int num, HomepageManager homepageManager) {
        MainFragment fragment = new MainFragment();
        mHomepageManager = homepageManager;

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
        mIndex = getArguments() != null ? getArguments().getInt(NUM_KEY) : 1;
    }

    @Override
    public void onStart() {
        super.onStart();
        Homepage homepage = mHomepageManager.getHomepages().get(getArguments().getInt(NUM_KEY));

        ImageView imageView = (ImageView)  this.getView().findViewById(R.id.fragment_main_photo_imageview);
        try {
            ImageTool.showImage(homepage.getmPhotoUrl(), imageView);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TextView textView = (TextView) this.getView().findViewById(R.id.fragment_main_title_textview);
        textView.setText(homepage.getTitle());

    }
}
