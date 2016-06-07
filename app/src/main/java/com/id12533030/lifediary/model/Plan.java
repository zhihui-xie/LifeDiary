package com.id12533030.lifediary.model;

import com.orm.SugarRecord;
import com.orm.dsl.Column;

import java.io.Serializable;

/**
 * Created by LENOVO on 2016/6/2.
 * The class is the model of Plan. The table records five columns including type, title, date,
 * location and text. The date is recorded as String attribute which allows users to record any
 * types of time. there are two types of plan. User can record their plans in the app.
 */
public class Plan extends SugarRecord implements Serializable {
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

    /**
     * Sugar ORM needs to be provided a default constructor
     */
    public Plan() {
    }

    /**
     * The constructor of Plan
     *
     * @param type
     * @param title
     * @param date
     * @param location
     * @param text
     */
    public Plan(int type, String title, String date, String location, String text) {
        super();
        mType = type;
        mTitle = title;
        mDate = date;
        mText = text;
        mLocation = location;

    }

    /**
     * get the type of Plan
     *
     * @return
     */
    public int getType() {
        return mType;
    }

    /**
     * Set the type of Plan
     *
     * @param type
     */
    public void setType(int type) {
        this.mType = type;
    }

    /**
     * Get the title of Plan
     *
     * @return
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Set the title of Plan
     *
     * @param mTitle
     */
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    /**
     * Get the date of Plan
     *
     * @return
     */
    public String getDate() {
        return mDate;
    }

    /**
     * Set the date of Plan
     *
     * @param mDate
     */
    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    /**
     * Get the location of Plan
     *
     * @return
     */
    public String getLocation() {
        return mLocation;
    }

    /**
     * Set the location of Plan
     *
     * @param mLocation
     */
    public void setLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    /**
     * Get the detail of Plan
     *
     * @return
     */
    public String getText() {
        return mText;
    }

    /**
     * Set the detail of Plan
     *
     * @param mText
     */
    public void setText(String mText) {
        this.mText = mText;
    }

}
