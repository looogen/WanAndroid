package com.loogen.wanandroid.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.loogen.wanandroid.R;
import com.loogen.wanandroid.base.BaseFragment;
import com.loogen.wanandroid.base.DataBindingConfig;
import com.loogen.wanandroid.databinding.FragmentSubscriptionBinding;
import com.loogen.wanandroid.ui.state.MainViewModel;
import com.loogen.wanandroid.ui.state.SubscriptionViewModel;

/**
 *
 */
public class SubscriptionFragment extends BaseFragment<FragmentSubscriptionBinding, SubscriptionViewModel> {
    public static final String TAG = "SubscriptionFragment";

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_sort);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}