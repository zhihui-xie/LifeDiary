package com.id12533030.lifediary.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.id12533030.lifediary.util.Constants;
import com.id12533030.lifediary.util.MainMenu;
import com.id12533030.lifediary.R;

/**
 * Created by LENOVO on 2016/5/31.
 */
public class SettingActivity extends AppCompatActivity {
    private MainMenu mMainMenu;
    private FragmentManager mFragmentManager;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_main);
        mFragmentManager = getSupportFragmentManager();
        mMainMenu = new MainMenu(this, mFragmentManager, true);
        mImageView = (ImageView) findViewById(R.id.setting_main_profile_imageview);

        try {
                    MainActivity.showImage(Constants.PIC_URLS[4] + "/island.jpg", mImageView);
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
}
