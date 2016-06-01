package com.id12533030.lifediary.util;

import android.os.Environment;

/**
 * Created by LENOVO on 2016/5/31.
 */
public class Constants {
    public static final String APP_TITLE = "Life Diary";
    public static final String HOMEPAGE_MENU = "Home Page";
    public static final String DIARY_MENU = "Diary";
    public static final String DAY_MENU = "Day";
    public static final String PLAN_MENU = "Plan";
    public static final String SETTING_MENU = "Setting";

    public final static int REQUEST_HOMEPAGE = 1;
    public final static int REQUEST_DIARY = 2;
    public final static int REQUEST_DAY = 3;
    public final static int REQUEST_PLAN = 4;
    public final static int REQUEST_SETTING = 5;
    public final static String PIC_URL = Environment.getExternalStorageDirectory().getPath().toString() + "/LifeDiary/Image/";
    public final static String[] PIC_URLS = {PIC_URL + "Homepage", PIC_URL + "Diary", PIC_URL + "Day", PIC_URL + "Plan", PIC_URL + "Setting"};


}
