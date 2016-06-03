package com.id12533030.lifediary.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by LENOVO on 2016/6/2.
 */
public class DateProcess {
    public DateProcess(){}

    /**
     * Format the date to a user friendly format
     *
     * @return
     */
    public static String getDatetimeAsString(long date) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss  dd-MM-yyyy");
        return format.format(new Date(date));
    }

    public static String getDateAsString(long date) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        return format.format(new Date(date));
    }
}
