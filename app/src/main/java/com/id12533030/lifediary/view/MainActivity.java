package com.id12533030.lifediary.view;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.id12533030.lifediary.adapter.MyFragmentPagerAdapter;
import com.id12533030.lifediary.model.Homepage;
import com.id12533030.lifediary.model.HomepageManager;
import com.id12533030.lifediary.util.Constants;
import com.id12533030.lifediary.util.MainMenu;
import com.id12533030.lifediary.R;
import com.orm.SugarContext;


import java.io.File;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    private MainMenu mMainMenu;
    private FragmentManager mFragmentManager;
    private ImageView mImageView;
    private ViewPager mViewPager = null;

    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();
        mMainMenu = new MainMenu(this, mFragmentManager, false);
        createImageFolder();

        SugarContext.init(this);
        Homepage m = new Homepage(1, "Sunny", "Homepage", "Sydney", Constants.PIC_URLS[0] + "/beach.jpg", 1000);
        m.save();
        Homepage mm = new Homepage(2, "Rain", "Second Page", "Guangzhou", Constants.PIC_URLS[0] + "/view.jpg", 2000);
        mm.save();
        final Homepage mmm = new Homepage(3, "Windy", "Third Page", "London", Constants.PIC_URLS[0] + "/sea.jpg", 3000);
        mmm.save();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fragment_main_add_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, mmm.getTitle() , Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
//                try {
//                    showImage(Constants.PIC_URLS[0] + "/view.jpg", mImageView);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        });

        HomepageManager homepageManager = new HomepageManager();
        mViewPager = (ViewPager) findViewById(R.id.activity_main_container_viewPager);
        mPagerAdapter = new MyFragmentPagerAdapter(mFragmentManager, homepageManager);
        mViewPager.setAdapter(mPagerAdapter);



    }

    @Override
    protected void onStart() {
        super.onStart();
        mImageView = (ImageView) findViewById(R.id.fragment_main_photo_imageview);
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

    public void addFragment(Fragment fragment, boolean addToBackStack, int containerId) {
        invalidateOptionsMenu();
        String backStackName = fragment.getClass().getName();
        boolean fragmentPopped = mFragmentManager.popBackStackImmediate(backStackName, 0);
        if (!fragmentPopped) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.add(containerId, fragment, backStackName)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            if (addToBackStack)
                transaction.addToBackStack(backStackName);
            transaction.commit();
        }

    }

    private void createImageFolder() {
        for (int i = 0; i < 5; ++i) {
            File FPath = new File(Constants.PIC_URLS[i]);
            if (!FPath.exists()) {
                FPath.mkdirs();
            }
        }
    }



}
