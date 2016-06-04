package com.id12533030.lifediary.model;

import com.orm.SugarRecord;
import com.orm.dsl.Column;

/**
 * Created by LENOVO on 2016/6/3.
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


    public Setting() {
    }

    public Setting(String url, String name, String gender, int age, String description) {
        mPhotoUrl = url;
        mName = name;
        mGender = gender;
        mAge = age;
        mDescription = description;
    }

    public String getPhotoUrl() {
        return mPhotoUrl;
    }

    public String getName() {
        return mName;
    }

    public String getGender() {
        return mGender;
    }

    public int getAge() {
        return mAge;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setPhotoUrl(String mPhotoUrl) {
        this.mPhotoUrl = mPhotoUrl;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setGender(String mGender) {
        this.mGender = mGender;
    }

    public void setAge(int mAge) {
        this.mAge = mAge;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

}
