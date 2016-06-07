package com.id12533030.lifediary.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by LENOVO on 2016/6/2.
 * The class is used to process date value. This class include two methods and they can transform
 * long attribute to String attribute
 */
public class DateProcess {
    public DateProcess() {
    }

    /**
     * Format the date to datetime format
     *
     * @param date
     * @return
     */
    public static String getDatetimeAsString(long date) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss  dd-MM-yyyy");
        return format.format(new Date(date));
    }

    /**
     * Format the date to date format
     *
     * @param date
     * @return
     */
    public static String getDateAsString(long date) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        return format.format(new Date(date));
    }
}
