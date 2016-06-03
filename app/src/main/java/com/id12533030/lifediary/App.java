package com.id12533030.lifediary;

import com.orm.SugarApp;
import com.orm.SugarContext;

/**
 * Created by Hsiaotsefeng on 2016/6/3.
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
}
