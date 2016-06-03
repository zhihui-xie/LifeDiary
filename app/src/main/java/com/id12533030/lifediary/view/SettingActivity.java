package com.id12533030.lifediary.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.id12533030.lifediary.model.Setting;
import com.id12533030.lifediary.util.Constants;
import com.id12533030.lifediary.util.ImageTool;
import com.id12533030.lifediary.util.MainMenu;
import com.id12533030.lifediary.R;

/**
 * Created by LENOVO on 2016/5/31.
 */
public class SettingActivity extends AppCompatActivity implements View.OnClickListener{
    private MainMenu mMainMenu;
    private FragmentManager mFragmentManager;
    private ImageView mImageView;
    private EditText mName;
    private EditText mGender;
    private EditText mAge;
    private EditText mDescription;
    private Button mButton;
    private String mPhotoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_main);
        mFragmentManager = getSupportFragmentManager();
        mMainMenu = new MainMenu(this, mFragmentManager, true, true);
        init();
        mButton.setOnClickListener(this);
        mPhotoUrl = Constants.PIC_URLS[4] + "/island.jpg";
        try {
                    ImageTool.showImage(mPhotoUrl, mImageView);
                } catch (Exception e) {
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

    private void init() {
        mImageView = (ImageView) findViewById(R.id.setting_main_profile_imageview);
        mName = (EditText) findViewById(R.id.setting_main_name_edittext);
        mGender = (EditText) findViewById(R.id.setting_main_gender_edittext);
        mAge = (EditText) findViewById(R.id.setting_main_age_edittext);
        mDescription = (EditText) findViewById(R.id.setting_main_description_edittext);
        mButton = (Button) findViewById(R.id.setting_main_ok_button);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_main_ok_button:
                Setting setting = Setting.findById(Setting.class,1);
                setting.setPhotoUrl(mName.getText().toString());
                setting.setName(mName.getText().toString());
                setting.setGender(mGender.getText().toString());
                setting.setAge(Integer.parseInt(mAge.getText().toString()));
                setting.setDescription(mDescription.getText().toString());
                setting.save();
                break;
            default:
                break;
        }
    }
}
