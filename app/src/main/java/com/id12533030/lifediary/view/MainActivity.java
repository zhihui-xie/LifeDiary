package com.id12533030.lifediary.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.id12533030.lifediary.R;
import com.id12533030.lifediary.adapter.MyFragmentPagerAdapter;
import com.id12533030.lifediary.model.Homepage;
import com.id12533030.lifediary.model.HomepageManager;
import com.id12533030.lifediary.util.Constants;
import com.id12533030.lifediary.util.MainMenu;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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
        if(Homepage.listAll(Homepage.class).size() == 0){
            Homepage homepage = new Homepage("", "Welcome", System.currentTimeMillis(), "UTS", "Record your valuable moment", "Designed by Zhihui Xie");
            homepage.save();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fragment_main_add_fab);
        fab.setOnClickListener(this);

    }


    @Override
    protected void onStart() {
        super.onStart();
        HomepageManager homepageManager = new HomepageManager();
        ArrayList<Homepage> homepages = homepageManager.getHomepages();
        Collections.reverse(homepages);
        mViewPager = (ViewPager) findViewById(R.id.activity_main_container_viewPager);
        mPagerAdapter = new MyFragmentPagerAdapter(mFragmentManager, homepages);
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


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.fragment_main_add_fab:
                intent = new Intent(MainActivity.this, AddHomePage.class);
                startActivityForResult(intent, Constants.REQUEST_ADD_HOMEPAGE);
                break;
            default:
                break;
        }
    }
}
