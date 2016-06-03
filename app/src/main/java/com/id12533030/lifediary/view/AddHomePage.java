package com.id12533030.lifediary.view;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.id12533030.lifediary.R;
import com.id12533030.lifediary.model.Homepage;
import com.id12533030.lifediary.util.Constants;
import com.id12533030.lifediary.util.DateProcess;
import com.id12533030.lifediary.util.MainMenu;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by LENOVO on 2016/6/3.
 */
public class AddHomePage extends AppCompatActivity implements View.OnClickListener{
    private MainMenu mMainMenu;
    private ImageView mImageView;
    private EditText mTitle;
    private TextView mDate;
    private EditText  mLocation;
    private EditText mContent;
    private EditText  mWeather;
    long mMomentDate;
    Homepage mHomepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_homepage);
        mMainMenu = new MainMenu(this);
        mMainMenu.initToolbar(true);
        init();
        setInformation();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_homepage_add_fab);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_homepage_add_fab:
                mHomepage = new Homepage(mWeather.getText().toString(),
                        mTitle.getText().toString(),
                        mLocation.getText().toString(),
                        Constants.PIC_URLS[0] + "/beach.jpg",
                        mMomentDate);
                mHomepage.save();
                break;
            default:
                break;
        }
    }

    private void init() {
        mImageView = (ImageView) findViewById(R.id.add_homepage_photo_imageview);
        mTitle = (EditText) findViewById(R.id.add_homepage_title_edittext);
        mDate= (TextView) findViewById(R.id.add_homepage_date_textview);
        mLocation = (EditText) findViewById(R.id.add_homepage_location_edittext);
        mContent = (EditText) findViewById(R.id.add_homepage_text_edittext);
        mWeather = (EditText) findViewById(R.id.add_homepage_weather_edittext);
    }

    private void setInformation() {
        mMomentDate = System.currentTimeMillis();
        mDate.setText(DateProcess.getDatetimeAsString(mMomentDate));


    }
}
