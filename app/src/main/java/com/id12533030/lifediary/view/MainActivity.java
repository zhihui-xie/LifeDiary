package com.id12533030.lifediary.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.id12533030.lifediary.R;
import com.id12533030.lifediary.adapter.MyFragmentPagerAdapter;
import com.id12533030.lifediary.model.Diary;
import com.id12533030.lifediary.util.Constants;
import com.id12533030.lifediary.util.MainMenu;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This activity is the main activity. When users first launch the app, it is visible to them.
 * It shows the homepage with viewPager of diaries. Users can slide the screen to watch the diaries.
 */
public class MainActivity extends AppCompatActivity {
    private MainMenu mMainMenu;
    private FragmentManager mFragmentManager;
    private ViewPager mViewPager = null;
    private PagerAdapter mPagerAdapter;
    private String DEFAULT_TITLE = "Welcome";
    private String DEFAULT_WEATHER = "Sunny";
    private String DEFAULT_DETAIL = "Record your valuable moment";
    private String DEFAULT_LOCATION = "Designed by Zhihui Xie, UTS";

    /**
     * Override the onCreate method
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();
        mMainMenu = new MainMenu(this, mFragmentManager, false, true);
        mMainMenu.initSystemBar(this);
        createImageFolder();
        if (Diary.listAll(Diary.class).size() == 0) {
            testMethod();
        }
    }

    /**
     * Test the app
     */
    private void testMethod() {
        for (int i = 0; i < 5; ++i) {
            Diary diary2 = new Diary("", DEFAULT_TITLE, System.currentTimeMillis(), DEFAULT_WEATHER, DEFAULT_DETAIL, DEFAULT_LOCATION);
            diary2.save();
        }
    }

    /**
     * Override the onStart method and set the viewPager. In onStart method, when the activity is
     * visible, it can update the viewPager
     */
    @Override
    protected void onStart() {
        super.onStart();
        ArrayList<Diary> diaries = (ArrayList<Diary>) Diary.listAll(Diary.class);
        Collections.reverse(diaries);
        mViewPager = (ViewPager) findViewById(R.id.activity_main_container_viewPager);
        mPagerAdapter = new MyFragmentPagerAdapter(mFragmentManager, diaries);
        mViewPager.setAdapter(mPagerAdapter);
        if (Diary.listAll(Diary.class).size() == 0) {
            Diary diary = new Diary("", DEFAULT_TITLE, System.currentTimeMillis(), DEFAULT_WEATHER, DEFAULT_DETAIL, DEFAULT_LOCATION);
            diary.save();
        }
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
     * Create the folders to store the photos of diary and setting profile
     */
    private void createImageFolder() {
        for (int i = 0; i < Constants.PIC_URLS.length; ++i) {
            File FPath = new File(Constants.PIC_URLS[i]);
            if (!FPath.exists()) {
                FPath.mkdirs();
            }
        }
    }
}
