package com.loogen.wanandroid.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.loogen.wanandroid.R;
import com.loogen.wanandroid.base.BaseFragment;
import com.loogen.wanandroid.base.DataBindingConfig;
import com.loogen.wanandroid.databinding.FragmentSortBinding;
import com.loogen.wanandroid.ui.state.MainViewModel;
import com.loogen.wanandroid.ui.state.SortViewModel;

/**
 *
 */
public class SortFragment extends BaseFragment<FragmentSortBinding, SortViewModel> {
    public static final String TAG = "SortFragment";

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_sort);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainViewModel mainVm = getActivityViewModel(MainViewModel.class);
        mainVm.title.set(getString(R.string.sort_title));
    }
}