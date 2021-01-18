package com.loogen.wanandroid.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;

import com.loogen.wanandroid.R;
import com.loogen.wanandroid.base.BaseFragment;
import com.loogen.wanandroid.base.DataBindingConfig;
import com.loogen.wanandroid.databinding.FragmentMineBinding;
import com.loogen.wanandroid.ui.state.MainViewModel;
import com.loogen.wanandroid.ui.state.MineViewModel;

/**
 *
 */
public class MineFragment extends BaseFragment<FragmentMineBinding, MineViewModel> {
    public static final String TAG = "MineFragment";

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_mine, BR.vm);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainViewModel mainVm = getActivityViewModel(MainViewModel.class);
        mainVm.title.set(getString(R.string.mine_title));
    }

}