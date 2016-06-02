package com.id12533030.lifediary.adapter;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.id12533030.lifediary.view.MainFragment;


/**
 * Created by LENOVO on 2016/6/1.
 */
public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {


    public MyFragmentPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }


    @Override
    public Fragment getItem(int position) {
        return MainFragment.newInstance(position);

    }

    @Override
    public int getCount() {
        //Need to be changed
        return 10;
    }


}


