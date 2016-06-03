package com.id12533030.lifediary.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.id12533030.lifediary.model.Homepage;
import com.id12533030.lifediary.model.HomepageManager;
import com.id12533030.lifediary.view.MainFragment;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by LENOVO on 2016/6/1.
 */
public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {
    HomepageManager mHomepageManager;
    ArrayList<Homepage> mHomepageList;

    public MyFragmentPagerAdapter(FragmentManager fragmentManager, ArrayList<Homepage> homepageList) {
        super(fragmentManager);

        mHomepageList = homepageList;

    }


    @Override
    public Fragment getItem(int position) {
        Log.i("position", position + "");
        Log.i("title", mHomepageList.get(position).getTitle());
        return MainFragment.newInstance( mHomepageList.get(position));
    }

    @Override
    public int getCount() {
        return mHomepageList.size();
    }


}


