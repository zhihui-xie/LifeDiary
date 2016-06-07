package com.id12533030.lifediary.model;

import com.google.android.gms.wearable.ChannelApi;
import com.orm.SugarRecord;
import com.orm.dsl.Column;

import java.io.Serializable;
/**
 * Created by LENOVO on 2016/6/2.
 */
public class Plan extends SugarRecord implements Serializable{
    @Column(name = "type")
    private int mType;
    @Column(name = "title")
    private String mTitle;
    @Column(name = "date")
    private String mDate;
    @Column(name = "location")
    private String mLocation;
    @Column(name = "text")
    private String mText;


    public Plan(){}

    public Plan(int type, String title, String date, String location, String text) {
        super();
        mType = type;
        mTitle = title;
        mDate = date;
        mText = text;
        mLocation = location;

    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        this.mType = type;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
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
