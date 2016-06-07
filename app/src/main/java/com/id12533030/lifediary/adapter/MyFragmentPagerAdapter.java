package com.id12533030.lifediary.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.id12533030.lifediary.model.Diary;
import com.id12533030.lifediary.view.MainFragment;

import java.util.ArrayList;


/**
 * Created by LENOVO on 2016/6/1.
 * Define the fragment pager adapter of homepage.This class extends FragmentStatePagerAdapter
 * instead of FragmentPagerAdapter, which only load the fragment when it is visible to the users.
 * Thus, FragmentStatePagerAdapter can save much memory.
 */
public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<Diary> mDiaryList;

    /**
     * The constructor of MyFragmentPagerAdapter
     *
     * @param fragmentManager
     * @param diaryList
     */
    public MyFragmentPagerAdapter(FragmentManager fragmentManager, ArrayList<Diary> diaryList) {
        super(fragmentManager);
        mDiaryList = diaryList;
    }

    /**
     * Return the visible fragment
     *
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        Log.i("position", position + "");
        Log.i("title", mDiaryList.get(position).getTitle());
        return MainFragment.newInstance(mDiaryList.get(position));
    }

    /**
     * Return the size of all list of diary fragments
     *
     * @return
     */
    @Override
    public int getCount() {
        return mDiaryList.size();
    }
}


