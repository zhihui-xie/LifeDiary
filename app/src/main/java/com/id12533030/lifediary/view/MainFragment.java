package com.id12533030.lifediary.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.id12533030.lifediary.R;
import com.id12533030.lifediary.model.Diary;
import com.id12533030.lifediary.util.DateProcess;
import com.id12533030.lifediary.util.ImageTool;

import java.io.IOException;

/**
 * Created by LENOVO on 2016/5/29.
 */
public class MainFragment extends Fragment {

    private Diary mDiary;
    private static final String PAGE_KEY = "PAGE";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    public static MainFragment newInstance(Diary diary) {
        MainFragment fragment = new MainFragment();

        // Supply diary input as an argument.
        Bundle args = new Bundle();
        args.putSerializable(PAGE_KEY, diary);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * When creating, retrieve this instance's number from its arguments.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDiary = getArguments() != null ? (Diary) getArguments().getSerializable(PAGE_KEY) : Diary.findById(Diary.class, 1);
    }

    @Override
    public void onStart() {
        super.onStart();
        ImageView imageView = (ImageView) this.getView().findViewById(R.id.fragment_main_photo_imageview);
        TextView title = (TextView) this.getView().findViewById(R.id.fragment_main_title_textview);
        TextView date = (TextView) this.getView().findViewById(R.id.fragment_main_date_textview);
        TextView location = (TextView) this.getView().findViewById(R.id.fragment_main_location_textview);
        TextView weather = (TextView) this.getView().findViewById(R.id.fragment_main_temperature_textview);
        TextView text = (TextView) this.getView().findViewById(R.id.fragment_main_text_textview);
        try {
            ImageTool.showImage(mDiary.getmPhotoUrl(), imageView, 500, 500);
        } catch (IOException e) {
            e.printStackTrace();
        }

        title.setText(mDiary.getTitle());
        location.setText(mDiary.getLocation());
        weather.setText(mDiary.getWeather());
        date.setText(String.valueOf(DateProcess.getDatetimeAsString(mDiary.getDate())));
        text.setText(mDiary.getText());
    }


}
