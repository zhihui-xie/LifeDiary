package com.id12533030.lifediary.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by LENOVO on 2016/6/2.
 */
public class DataProcess {
    public DataProcess(){}

    /**
     * Format the date to a user friendly format
     *
     * @return
     */
    public String getDateAsString(long date) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        return format.format(new Date(date));
    }
}
