package com.id12533030.lifediary.adapter;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.id12533030.lifediary.view.ArrayListFragment;


/**
 * Created by LENOVO on 2016/6/1.
 */
public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {


    public MyFragmentPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fm = ArrayListFragment.newInstance(position);
//        TextView title = (TextView) fm.getView().findViewById(R.id.fragment_main_title_textview);
//        if (position == 3){
//            title.setText("This is the third page!");
//        }
        return fm;
    }

    @Override
    public int getCount() {
        //Need to be changed
        return 10;
    }


}


