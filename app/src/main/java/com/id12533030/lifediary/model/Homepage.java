package com.id12533030.lifediary.model;


import com.orm.SugarRecord;
import com.orm.dsl.Column;

/**
 * Created by LENOVO on 2016/6/2.
 */
public class Homepage extends SugarRecord{

    @Column(name = "ID", unique = true)
    private int mId;
    @Column(name = "url")
    private String mPhotoUrl;
    @Column(name = "title")
    private String mTitle;
    @Column(name = "date")
    private long mDate;
    @Column(name = "weather")
    private String mWeather;
    @Column(name = "location")
    private String mLocation;

    public Homepage(){}

    public Homepage(int id, String weather, String title, String location, String url, long date) {
        super();
        mId = id;
        mPhotoUrl = url;
        mTitle = title;
        mDate = date;
        mWeather = weather;
        mLocation = location;
    }

    public String getmPhotoUrl() {
        return mPhotoUrl;
    }

    public int getmId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public long getDate() {
        return mDate;
    }

    public String getWeather() {
        return mWeather;
    }

    public String getLocation() {
        return mLocation;
    }


}