package com.id12533030.lifediary.model;


import com.orm.SugarRecord;
import com.orm.dsl.Column;

import java.io.Serializable;

/**
 * Created by LENOVO on 2016/6/2.
 * The class is the model of Diary. The table has six columns including url, title, date, weather,
 * text and location.The url is record the location of the photo which is stored in a specified
 * folder. The date is record as long attribute. The location can be accessed by google map.
 */
public class Diary extends SugarRecord implements Serializable {

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

    /**
     * Sugar ORM needs to be provided a default constructor
     */
    public Diary() {
    }

    /**
     * The constructor of Diary
     *
     * @param url
     * @param title
     * @param date
     * @param weather
     * @param text
     * @param location
     */
    public Diary(String url, String title, long date, String weather, String text, String location) {
        super();
        mPhotoUrl = url;
        mTitle = title;
        mDate = date;
        mText = text;
        mWeather = weather;
        mLocation = location;
    }

    /**
     * Get the url recorded in photo
     *
     * @return
     */
    public String getmPhotoUrl() {
        return mPhotoUrl;
    }

    /**
     * Get the title recorded in Diary
     *
     * @return
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Get the date recorded in Diary
     *
     * @return
     */
    public long getDate() {
        return mDate;
    }

    /**
     * Get the weather recorded in Diary
     *
     * @return
     */
    public String getWeather() {
        return mWeather;
    }

    /**
     * Get the location recorded in Diary
     *
     * @return
     */
    public String getLocation() {
        return mLocation;
    }

    /**
     * Set the url of photo
     *
     * @param mPhotoUrl
     */
    public void setPhotoUrl(String mPhotoUrl) {
        this.mPhotoUrl = mPhotoUrl;
    }

    /**
     * set the title of Diary
     *
     * @param mTitle
     */
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    /**
     * Set the date recorded in Diary
     *
     * @param mDate
     */
    public void setDate(long mDate) {
        this.mDate = mDate;
    }

    /**
     * Set the weather recorded in Diary
     *
     * @param mWeather
     */
    public void setWeather(String mWeather) {
        this.mWeather = mWeather;
    }

    /**
     * Set the location recorded in Diary
     *
     * @param mLocation
     */
    public void setLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    /**
     * Get the detail of Diary
     *
     * @return
     */
    public String getText() {
        return mText;
    }

    /**
     * Set the detail of Diary
     *
     * @param mText
     */
    public void setText(String mText) {
        this.mText = mText;
    }

}
