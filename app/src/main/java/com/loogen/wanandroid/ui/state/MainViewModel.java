package com.loogen.wanandroid.ui.state;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    public static final String TAG = "MainViewModel";

    public final MutableLiveData<String> title = new MutableLiveData<>();

}
