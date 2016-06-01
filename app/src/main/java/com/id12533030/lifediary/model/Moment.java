package com.id12533030.lifediary.model;

import com.orm.SugarRecord;

/**
 * Created by LENOVO on 2016/6/1.
 */
public class Moment extends SugarRecord {
    String mPhotoUrl;
    String mTitle;
    long mDate;
    String mWeather;
    String mLocation;

    public Moment() {};
}
