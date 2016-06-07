package com.id12533030.lifediary.model;

import com.orm.SugarRecord;
import com.orm.dsl.Column;

/**
 * Created by LENOVO on 2016/6/3.
 * The class is the model of Setting. The table records five columns including url, name, gender, age,
 * description. The url record the location of photo.Setting records the information of users.
 */
public class Setting extends SugarRecord {
    @Column(name = "url")
    private String mPhotoUrl;
    @Column(name = "name")
    private String mName;
    @Column(name = "gender")
    private String mGender;
    @Column(name = "age")
    private int mAge;
    @Column(name = "description")
    private String mDescription;

    /**
     * Sugar ORM needs to be provided a default constructor
     */
    public Setting() {
    }

    /**
     * The constructor of Setting
     *
     * @param url
     * @param name
     * @param gender
     * @param age
     * @param description
     */
    public Setting(String url, String name, String gender, int age, String description) {
        mPhotoUrl = url;
        mName = name;
        mGender = gender;
        mAge = age;
        mDescription = description;
    }

    /**
     * Get the photo url of Setting
     *
     * @return
     */
    public String getPhotoUrl() {
        return mPhotoUrl;
    }

    /**
     * Get the name of user
     *
     * @return
     */
    public String getName() {
        return mName;
    }

    /**
     * Get the gender of user
     *
     * @return
     */
    public String getGender() {
        return mGender;
    }

    /**
     * Get the age of user
     *
     * @return
     */
    public int getAge() {
        return mAge;
    }

    /**
     * Get the description of user
     *
     * @return
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * Set the photo url of user
     *
     * @param mPhotoUrl
     */
    public void setPhotoUrl(String mPhotoUrl) {
        this.mPhotoUrl = mPhotoUrl;
    }

    /**
     * Set the name of user
     *
     * @param mName
     */
    public void setName(String mName) {
        this.mName = mName;
    }

    /**
     * Set the gender of user
     *
     * @param mGender
     */
    public void setGender(String mGender) {
        this.mGender = mGender;
    }

    /**
     * set the age of user
     *
     * @param mAge
     */
    public void setAge(int mAge) {
        this.mAge = mAge;
    }

    /**
     * Set the description of user
     *
     * @param mDescription
     */
    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

}
