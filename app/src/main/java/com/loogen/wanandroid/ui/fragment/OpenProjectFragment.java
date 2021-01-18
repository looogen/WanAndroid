package com.loogen.wanandroid.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.loogen.wanandroid.R;
import com.loogen.wanandroid.base.BaseFragment;
import com.loogen.wanandroid.base.DataBindingConfig;
import com.loogen.wanandroid.databinding.FragmentOpenProjectBinding;
import com.loogen.wanandroid.ui.state.MainViewModel;
import com.loogen.wanandroid.ui.state.OpenProjectViewModel;

/**
 *
 */
public class OpenProjectFragment extends BaseFragment<FragmentOpenProjectBinding, OpenProjectViewModel> {

    protected String TAG = "OpenProjectFragment";

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_open_project);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainViewModel mainVm = getActivityViewModel(MainViewModel.class);
        mainVm.title.set(getString(R.string.project_title));
    }

}