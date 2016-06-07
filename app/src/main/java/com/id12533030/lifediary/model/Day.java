package com.id12533030.lifediary.model;

import com.orm.SugarRecord;
import com.orm.dsl.Column;

import java.io.Serializable;

/**
 * Created by LENOVO on 2016/6/2.
 * The class is the model of Day. The table has four columns including type, title, date and text.
 * Day record some important days of users.
 */
public class Day extends SugarRecord implements Serializable {
    /**
     * There are four types of Day, including Birthday, Anniversary, Festival and Others
     */
    @Column(name = "type")
    private int mType;
    @Column(name = "title")
    /**
     *  The attribute date is defined as String which allow users to input different format of time
     */
    private String mTitle;
    @Column(name = "date")
    private String mDate;
    @Column(name = "text")
    private String mText;

    /**
     * Sugar ORM needs to be provided a default constructor
     */
    public Day() {
    }

    /**
     * The constructor of Day
     *
     * @param type
     * @param title
     * @param date
     * @param text
     */
    public Day(int type, String title, String date, String text) {
        super();
        mType = type;
        mTitle = title;
        mDate = date;
        mText = text;
    }

    /**
     * Return the type of Day
     *
     * @return
     */
    public int getType() {
        return mType;
    }

    /**
     * Set the type of Day
     *
     * @param type
     */
    public void setType(int type) {
        this.mType = type;
    }

    /**
     * Return the title of Day
     *
     * @return
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Set the title of Day
     *
     * @param mTitle
     */
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    /**
     * Get the date of Day
     *
     * @return
     */
    public String getDate() {
        return mDate;
    }

    /**
     * Set the date of Day
     *
     * @param mDate
     */
    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    /**
     * Get the detail of Day
     *
     * @return
     */
    public String getText() {
        return mText;
    }

    /**
     * Set the detail of Day
     *
     * @param mText
     */
    public void setText(String mText) {
        this.mText = mText;
    }

}
