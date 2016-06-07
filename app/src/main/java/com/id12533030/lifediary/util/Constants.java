package com.id12533030.lifediary.util;

import android.os.Environment;

/**
 * Created by LENOVO on 2016/5/31.
 * The class is used to store most of constant values.
 */
public class Constants {
    public static final String HOMEPAGE_MENU = "Home Page";
    public static final String DIARY_MENU = "Diary";
    public static final String DAY_MENU = "Day";
    public static final String PLAN_MENU = "Plan";
    public static final String SETTING_MENU = "Setting";
    public static final String RECORD_MENU = "Record";
    public static final String EXIT_MENU = "Exit";

    public static final String PIC_URL = Environment.getExternalStorageDirectory().getPath().toString() + "/LifeDiary/Image/";
    public static final String[] PIC_URLS = {PIC_URL + "Diary/", PIC_URL + "Setting/"};
    public static final String REQUEST_MAP_RESULT = "Location Result";
    public static final String PIC_FORMAT = ".png";

    public static final String ADD_SUCCESSFULLY = "You add a new diary successfully!";
    public static final String CREATE_SUCCESSFUL = "Create successfully";
    public static final String UPDATE_DIALOG_TITLE = "Do you want to update your plan?";
    public static final String UPDATE_RESULT = "Update successfully";
    public static final String DELETE_DIALOG_TITLE = "Do you want to delete it?";
    public static final String CREATE_PLAN_DIALOG_TITLE = "Create a New Plan";
    public static final String CREATE_DAY_DIALOG_TITLE = "Create an Important Day";

    public static final String PACKAGE_NAME =
            "com.google.android.gms.location.sample.locationaddress";
    public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";
    public static final String RESULT_DATA_KEY = PACKAGE_NAME + ".RESULT_DATA_KEY";
    public static final String LOCATION_DATA_EXTRA = PACKAGE_NAME + ".LOCATION_DATA_EXTRA";
    public static final String EXTRA_DIARY = "com.id12533030.lifediary.view.DIARY";

    public static final String CONFIRM = "Confirm";
    public static final String CANCEL = "Cancel";
    public static final String LATITUDE = "Latitude";
    public static final String LONGITUDE = "Longitude";

    public static final int REQUEST_HOMEPAGE = 1;
    public static final int REQUEST_DIARY = 2;
    public static final int REQUEST_DAY = 3;
    public static final int REQUEST_PLAN = 4;
    public static final int REQUEST_SETTING = 5;
    public static final int REQUEST_RECORD = 10;
    public static final int REQUEST_ADD_HOMEPAGE = 6;
    public static final int PHOTO_REQUEST_GALLERY = 7;
    public static final int PHOTO_REQUEST_CUT = 8;
    public static final int REQUEST_MAP = 9;
    public static final int SETTING_INDEX = 1;

    public static final int SUCCESS_RESULT = 0;
    public static final int FAILURE_RESULT = 1;

    public static final int LIST_ITEM_SIZE = 80;


}
