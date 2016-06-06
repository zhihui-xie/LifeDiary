package com.id12533030.lifediary.model;

import com.orm.SugarRecord;
import com.orm.dsl.Column;

import java.io.Serializable;
/**
 * Created by LENOVO on 2016/6/2.
 */
public class Plan extends SugarRecord implements Serializable{
    @Column(name = "url")
    private String mPhotoUrl;
    @Column(name = "title")
    private String mTitle;
    @Column(name = "date")
    private long mDate;
    @Column(name = "location")
    private String mLocation;
    @Column(name = "text")
    private String mText;

    public Plan(){}

    public Plan(String url, String title, long date, String location, String text) {
        super();
        mPhotoUrl = url;
        mTitle = title;
        mDate = date;
        mText = text;
        mLocation = location;
    }

    public String getPhotoUrl() {
        return mPhotoUrl;
    }

    public void setPhotoUrl(String mPhotoUrl) {
        this.mPhotoUrl = mPhotoUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public long getDate() {
        return mDate;
    }

    public void setDate(long mDate) {
        this.mDate = mDate;
    }

    public String getLocation() {
        return mLocation;
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
