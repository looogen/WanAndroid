package com.loogen.wanandroid.ui.state;

import androidx.databinding.ObservableField;

import com.loogen.wanandroid.base.BaseModel;
import com.loogen.wanandroid.base.BaseViewModel;

public class MainViewModel extends BaseViewModel {
    public static final String TAG = "MainViewModel";
    public final ObservableField<String> title = new ObservableField<>();

    @Override
    public BaseModel getModel() {
        return null;
    }
}
