package com.id12533030.lifediary.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.id12533030.lifediary.R;
import com.id12533030.lifediary.model.Diary;
import com.id12533030.lifediary.util.Constants;
import com.id12533030.lifediary.util.DateProcess;
import com.id12533030.lifediary.util.ImageTool;
import com.id12533030.lifediary.util.MainMenu;

import java.io.IOException;

/**
 * Created by LENOVO on 2016/6/7.
 */
public class DiaryDetail extends AppCompatActivity {
    private MainMenu mMainMenu;
    private FragmentManager mFragmentManager;
    private ImageView mImageVie;
    private TextView mTitle;
    private TextView mDate;
    private  TextView mLocation;
    private TextView mWeather;
    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_detail);
        mFragmentManager = getSupportFragmentManager();
        mMainMenu = new MainMenu(this, true, false);

        mMainMenu.initSystemBar(this);
        init();
        setContent();
    }

    private void init() {
        mImageVie = (ImageView) findViewById(R.id.diary_detail_photo_imageview);
        mTitle = (TextView) findViewById(R.id.diary_detail_title_textview);
        mDate = (TextView) findViewById(R.id.diary_detail_date_textview);
        mLocation = (TextView) findViewById(R.id.diary_detail_location_textview);
        mWeather = (TextView) findViewById(R.id.diary_detail_temperature_textview);
        mText = (TextView) findViewById(R.id.diary_detail_text_textview);
    }

    private void setContent() {
        Intent intent = getIntent();
        long id = intent.getLongExtra(Constants.EXTRA_DIARY, 1);
        Diary diary = Diary.findById(Diary.class, id);
        try{
            ImageTool.showImage(diary.getmPhotoUrl(), mImageVie, 350, 350);
            mTitle.setText(diary.getTitle());
            mDate.setText(DateProcess.getDatetimeAsString(diary.getDate()));
            mLocation.setText(diary.getLocation());
            mWeather.setText(diary.getWeather());
            mText.setText(diary.getText());
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        mMainMenu.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mMainMenu.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }
}
