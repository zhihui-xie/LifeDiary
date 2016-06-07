package com.id12533030.lifediary.view;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
 * Created by LENOVO on 2016/6/3.
 */
public class AddHomePage extends AppCompatActivity implements View.OnClickListener {
    private MainMenu mMainMenu;
    private ImageView mImageView;
    private EditText mTitle;
    private TextView mDate;
    private EditText mLocation;
    private EditText mContent;
    private EditText mWeather;
    private ImageTool mImageTool;
    private EditText mText;
    private FloatingActionButton mFab;
    private FloatingActionButton mFabLoc;
    private long mMomentDate;
    private Diary mDiary;
    private static Bitmap mBitmap;
    private static String mStrLoc = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_homepage);
        mMainMenu = new MainMenu(this, true, false);
        mMainMenu.initSystemBar(this);
        init();
        setListener();
        loadInfo();
    }

    private void setListener() {
        mFab.setOnClickListener(this);
        mFabLoc.setOnClickListener(this);
        mImageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.add_homepage_add_fab:
                storeInfo();
                Snackbar.make(v, Constants.ADD_SUCCESSFULLY, Snackbar.LENGTH_LONG).show();
                finish();
                break;
            case R.id.add_homepage_location_fab:
                intent = new Intent(AddHomePage.this, MapsActivity.class);
                startActivityForResult(intent, Constants.REQUEST_MAP);
                break;
            case R.id.add_homepage_photo_imageview:
                mImageTool.gallery();
                break;
            default:
                break;
        }
    }

    private void init() {
        mImageView = (ImageView) findViewById(R.id.add_homepage_photo_imageview);
        mTitle = (EditText) findViewById(R.id.add_homepage_title_edittext);
        mDate = (TextView) findViewById(R.id.add_homepage_date_textview);
        mLocation = (EditText) findViewById(R.id.add_homepage_location_edittext);
        mContent = (EditText) findViewById(R.id.add_homepage_text_edittext);
        mWeather = (EditText) findViewById(R.id.add_homepage_weather_edittext);
        mText = (EditText) findViewById(R.id.add_homepage_text_edittext);
        mFab = (FloatingActionButton) findViewById(R.id.add_homepage_add_fab);
        mFabLoc = (FloatingActionButton) findViewById(R.id.add_homepage_location_fab);
        mImageTool = new ImageTool(this);
    }

    private void loadInfo() {
        mMomentDate = System.currentTimeMillis();
        mDate.setText(DateProcess.getDatetimeAsString(mMomentDate));
    }

    private void storeInfo() {
        mDiary = new Diary();
        String pictureName = String.valueOf(mMomentDate);
        String photoUrl = Constants.PIC_URLS[0] + pictureName + Constants.PIC_FOMATE;
        if (mBitmap != null) {
            mImageTool.saveBitmapTOFile(mBitmap, Constants.PIC_URLS[0], pictureName);
        }
        mDiary.setPhotoUrl(photoUrl);
        mDiary.setTitle(mTitle.getText().toString());
        mDiary.setDate(mMomentDate);
        mDiary.setLocation(mLocation.getText().toString());
        mDiary.setWeather(mWeather.getText().toString());
        mDiary.setText(mText.getText().toString());
        mDiary.save();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Constants.PHOTO_REQUEST_GALLERY:
                if (data != null) {
                    try {
                        ContentResolver resolver = getContentResolver();
                        Uri uri = data.getData();
                        mBitmap = Bitmap.createScaledBitmap(MediaStore.Images.Media.getBitmap(resolver, uri), 500, 500, true);
                        if (mBitmap != null) {
                            mImageView.setImageBitmap(mBitmap);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case Constants.REQUEST_MAP:
                if (data != null) {
                    mStrLoc = data.getStringExtra(Constants.REQUEST_MAP_RESULT).toString();
                    mLocation.setText(mStrLoc);
                }
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
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
