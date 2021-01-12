package com.loogen.wanandroid.ui.state;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

public class AccountViewModel extends ViewModel {
    public static final String TAG = "AccountViewModel";
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableInt navIcon = new ObservableInt();
}
