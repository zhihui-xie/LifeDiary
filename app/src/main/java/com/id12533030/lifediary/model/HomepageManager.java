package com.id12533030.lifediary.model;

import com.orm.SugarRecord;
import com.orm.dsl.Column;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 2016/6/1.
 */
public class HomepageManager {


    private List<Homepage> mHomepageList;

    public HomepageManager() {
        mHomepageList = new ArrayList<>();
        mHomepageList = Homepage.listAll(Homepage.class);
    }

    public int getSize() {
        return mHomepageList.size();
    }

    public ArrayList<Homepage> getHomepages(){
        return (ArrayList<Homepage>)mHomepageList;
    }

}
