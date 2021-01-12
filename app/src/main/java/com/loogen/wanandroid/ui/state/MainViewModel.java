package com.loogen.wanandroid.ui.state;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    public static final String TAG = "MainViewModel";
    public final ObservableField<String> title = new ObservableField<>();
}
