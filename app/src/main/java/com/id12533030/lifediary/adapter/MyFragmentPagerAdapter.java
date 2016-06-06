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
 */
public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<Diary> mDiaryList;

    public MyFragmentPagerAdapter(FragmentManager fragmentManager, ArrayList<Diary> diaryList) {
        super(fragmentManager);
        mDiaryList = diaryList;
    }


    @Override
    public Fragment getItem(int position) {
        Log.i("position", position + "");
        Log.i("title", mDiaryList.get(position).getTitle());
        return MainFragment.newInstance( mDiaryList.get(position));
    }

    @Override
    public int getCount() {
        return mDiaryList.size();
    }


}


