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


public class MainActivity extends AppCompatActivity {
    private MainMenu mMainMenu;
    private FragmentManager mFragmentManager;
    private ViewPager mViewPager = null;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();
        mMainMenu = new MainMenu(this, mFragmentManager, false, true);
        mMainMenu.initSystemBar(this);
        createImageFolder();
        if (Diary.listAll(Diary.class).size() == 0) {
            Diary diary = new Diary("", "Welcome", System.currentTimeMillis(), "UTS", "Record your valuable moment", "Designed by Zhihui Xie");
            diary.save();
        }


    }


    @Override
    protected void onStart() {
        super.onStart();
        ArrayList<Diary> diaries = (ArrayList<Diary>) Diary.listAll(Diary.class);
        Collections.reverse(diaries);
        mViewPager = (ViewPager) findViewById(R.id.activity_main_container_viewPager);
        mPagerAdapter = new MyFragmentPagerAdapter(mFragmentManager, diaries);
        mViewPager.setAdapter(mPagerAdapter);
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

//    public void addFragment(Fragment fragment, boolean addToBackStack, int containerId) {
//        invalidateOptionsMenu();
//        String backStackName = fragment.getClass().getName();
//        boolean fragmentPopped = mFragmentManager.popBackStackImmediate(backStackName, 0);
//        if (!fragmentPopped) {
//            FragmentTransaction transaction = mFragmentManager.beginTransaction();
//            transaction.add(containerId, fragment, backStackName)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//            if (addToBackStack)
//                transaction.addToBackStack(backStackName);
//            transaction.commit();
//        }
//    }

    private void createImageFolder() {
        for (int i = 0; i < 5; ++i) {
            File FPath = new File(Constants.PIC_URLS[i]);
            if (!FPath.exists()) {
                FPath.mkdirs();
            }
        }
    }


}
