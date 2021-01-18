package com.loogen.wanandroid.ui.state;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.loogen.wanandroid.base.BaseModel;
import com.loogen.wanandroid.base.BaseViewModel;

public class AccountViewModel extends BaseViewModel {
    public static final String TAG = "AccountViewModel";
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableInt navIcon = new ObservableInt();

    @Override
    public BaseModel getModel() {
        return null;
    }
}
