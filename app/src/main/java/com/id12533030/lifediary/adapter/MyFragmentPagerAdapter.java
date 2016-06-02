package com.id12533030.lifediary.adapter;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.id12533030.lifediary.model.Homepage;
import com.id12533030.lifediary.model.HomepageManager;
import com.id12533030.lifediary.view.MainFragment;

import java.util.ArrayList;


/**
 * Created by LENOVO on 2016/6/1.
 */
public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {
    HomepageManager mHomepageManager;

//    public MyFragmentPagerAdapter(FragmentManager fragmentManager){
//        super(fragmentManager);
//
    public MyFragmentPagerAdapter(FragmentManager fragmentManager, HomepageManager homepageManager){
        super(fragmentManager);
        mHomepageManager = homepageManager;
    }


    @Override
    public Fragment getItem(int position) {
        return MainFragment.newInstance(position, mHomepageManager);

    }

    @Override
    public int getCount() {
        //Need to be changed
        return mHomepageManager.getSize();
    }


}


