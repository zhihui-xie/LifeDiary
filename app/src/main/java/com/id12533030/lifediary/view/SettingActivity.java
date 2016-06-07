package com.id12533030.lifediary.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.id12533030.lifediary.R;
import com.id12533030.lifediary.model.Setting;
import com.id12533030.lifediary.util.Constants;
import com.id12533030.lifediary.util.ImageTool;
import com.id12533030.lifediary.util.MainMenu;

import java.io.IOException;

/**
 * Created by LENOVO on 2016/5/31.
 * This activity is for users to set the information of themselves. They can select a photo in their
 * phone as their profile
 */
public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private MainMenu mMainMenu;
    private FragmentManager mFragmentManager;
    private ImageView mImageView;
    private EditText mName;
    private EditText mGender;
    private EditText mAge;
    private EditText mDescription;
    private Button mButton;
    private String mPhotoUrl;
    private static final String UPDATE_SUCCESSFULLY = "You update information successfully!";
    private static Bitmap mBitmap;
    private ImageTool mImageTool;
    private static final String PIC_NAME = "profile";

    /**
     * Override the onCreate method
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_main);
        mFragmentManager = getSupportFragmentManager();
        mMainMenu = new MainMenu(this, mFragmentManager, true, true);
        mMainMenu.initSystemBar(this);
        mImageTool = new ImageTool(this);
        init();
        setListener();
        loadInfo();
    }

    /**
     * Override the onCreateOptionsMenu method
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        mMainMenu.onCreateOptionsMenu(menu);
        return true;
    }

    /**
     * Override the onOptionsItemSelected method
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mMainMenu.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    /**
     * Initial all the widgets
     */
    private void init() {
        mImageView = (ImageView) findViewById(R.id.setting_main_profile_imageview);
        mName = (EditText) findViewById(R.id.setting_main_name_edittext);
        mGender = (EditText) findViewById(R.id.setting_main_gender_edittext);
        mAge = (EditText) findViewById(R.id.setting_main_age_edittext);
        mDescription = (EditText) findViewById(R.id.setting_main_description_edittext);
        mButton = (Button) findViewById(R.id.setting_main_ok_button);
    }

    /**
     * Set the listener of button and imageView
     */
    private void setListener() {
        mButton.setOnClickListener(this);
        mImageView.setOnClickListener(this);
    }

    /**
     * Implement the click function
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_main_ok_button:
                storeInfo();
                Snackbar.make(v, UPDATE_SUCCESSFULLY, Snackbar.LENGTH_LONG).show();
                break;
            case R.id.setting_main_profile_imageview:
                mImageTool.gallery();
                break;
            default:
                break;
        }
    }

    /**
     * Store the information of user
     */
    private void storeInfo() {
        Setting setting = new Setting();
        if (Setting.listAll(Setting.class).size() != 0) {
            setting = Setting.findById(Setting.class, Constants.SETTING_INDEX);
        }
        mPhotoUrl = Constants.PIC_URLS[4] + PIC_NAME + Constants.PIC_FORMAT;
        if (mBitmap != null) {
            mImageTool.saveBitmapTOFile(mBitmap, Constants.PIC_URLS[4], PIC_NAME);
        }
        setting.setPhotoUrl(mPhotoUrl);
        setting.setName(mName.getText().toString());
        setting.setGender(mGender.getText().toString());
        int age = mAge.getText().toString().equals("") ? -1 : Integer.parseInt(mAge.getText().toString());
        setting.setAge(age);
        setting.setDescription(mDescription.getText().toString());
        setting.save();
    }

    /**
     * Load the information of user
     */
    private void loadInfo() {
        if (Setting.listAll(Setting.class).size() != 0) {
            Setting setting = Setting.findById(Setting.class, Constants.SETTING_INDEX);
            mName.setText(setting.getName());
            mGender.setText(setting.getGender());
            String age = setting.getAge() != -1 ? String.valueOf(setting.getAge()) : "";
            mAge.setText(age);
            mDescription.setText(setting.getDescription());
            try {
                ImageTool.showImage(setting.getPhotoUrl(), mImageView, 300, 300);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Select a photo as profile
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Constants.PHOTO_REQUEST_GALLERY:
                if (data != null) {
                    Uri uri = data.getData();
                    mImageTool.crop(uri);
                }
                break;
            case Constants.PHOTO_REQUEST_CUT:
                if (data != null) {
                    mBitmap = data.getParcelableExtra("data");
                    if (mBitmap != null) {
                        mImageView.setImageBitmap(mBitmap);
                    }
                }
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
