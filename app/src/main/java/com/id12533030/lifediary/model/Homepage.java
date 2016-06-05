package com.id12533030.lifediary.model;


import com.orm.SugarRecord;
import com.orm.dsl.Column;

import java.io.Serializable;

/**
 * Created by LENOVO on 2016/6/2.
 */
public class Homepage extends SugarRecord implements Serializable{

    @Column(name = "url")
    private String mPhotoUrl;
    @Column(name = "title")
    private String mTitle;
    @Column(name = "date")
    private long mDate;
    @Column(name = "weather")
    private String mWeather;
    @Column(name = "text")
    private String mText;
    @Column(name = "location")
    private String mLocation;

    public Homepage(){}

    public Homepage(String url, String title, long date, String weather,String text, String location) {
        super();
        mPhotoUrl = url;
        mTitle = title;
        mDate = date;
        mText = text;
        mWeather = weather;
        mLocation = location;
    }

    public String getmPhotoUrl() {
        return mPhotoUrl;
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


    public void setPhotoUrl(String mPhotoUrl) {
        this.mPhotoUrl = mPhotoUrl;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setDate(long mDate) {
        this.mDate = mDate;
    }

    public void setWeather(String mWeather) {
        this.mWeather = mWeather;
    }

    public void setLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
    }

}
