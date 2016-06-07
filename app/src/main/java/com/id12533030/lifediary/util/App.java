package com.id12533030.lifediary.util;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.orm.SugarApp;
import com.orm.SugarContext;

/**
 * Created by LENOVO on 2016/6/3.
 * The class is used to initial SugarApp
 */
public class App extends SugarApp {
    /**
     * Override the method onCreate
     */
    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
    }

    /**
     * Override the method onTerminate
     */
    @Override
    public void onTerminate() {
        SugarContext.terminate();
        super.onTerminate();
    }

    /**
     * Override the method attachBaseContext
     *
     * @param newBase
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }
}
