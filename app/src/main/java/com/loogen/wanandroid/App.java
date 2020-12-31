package com.loogen.wanandroid;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

/**
 * created by loogen on 2020-12-22
 */
public class App extends Application implements ViewModelStoreOwner {

    public static final String TAG = "App";


    private static Application sApp;
    private ViewModelStore viewModelStore;

    public static Application getApp() {
        return sApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        viewModelStore = new ViewModelStore();
        sApp = this;
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return viewModelStore;
    }
}
