package com.jr.weather.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2016-11-22.
 */

public class BaseApplication extends Application {
    private static BaseApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Context getContext() {
        return application;
    }
}
