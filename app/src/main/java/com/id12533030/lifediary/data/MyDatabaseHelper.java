package com.id12533030.lifediary.data;

import android.content.Context;

import com.orm.SugarDb;
import com.orm.SugarTransactionHelper;

/**
 * Created by LENOVO on 2016/6/1.
 */
public class MyDatabaseHelper extends SugarDb {
    public MyDatabaseHelper(Context context) {
        super(context);
    }
}
