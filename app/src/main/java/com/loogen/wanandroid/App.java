package com.loogen.wanandroid;

import android.app.Application;

/**
 * created by loogen on 2020-12-22
 */
public class App extends Application {

    public static final String TAG = "App";


    private static Application sApp;

    public static Application getApp() {
        return sApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
    }
}
