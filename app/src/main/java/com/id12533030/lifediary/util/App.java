package com.id12533030.lifediary.util;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.orm.SugarApp;
import com.orm.SugarContext;

/**
 * Created by LENOVO on 2016/6/3.
 */
public class App extends SugarApp {
    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
    }

    @Override
    public void onTerminate() {
        SugarContext.terminate();
        super.onTerminate();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }
}
