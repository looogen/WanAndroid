package com.loogen.wanandroid.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.loogen.wanandroid.R;
import com.loogen.wanandroid.base.BaseFragment;
import com.loogen.wanandroid.base.DataBindingConfig;
import com.loogen.wanandroid.databinding.FragmentRegisterBinding;
import com.loogen.wanandroid.ui.state.AccountViewModel;
import com.loogen.wanandroid.ui.state.RegisterViewModel;

public class RegisterFragment extends BaseFragment<FragmentRegisterBinding, RegisterViewModel> {
    public static final String TAG = "RegisterFragment";

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_register);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: ");

        AccountViewModel accountViewModel = getActivityViewModel(AccountViewModel.class);

        //toolbar
        accountViewModel.title.set(getString(R.string.account_register_title));
        accountViewModel.navIcon.set(R.drawable.ic_nav_back);
    }
}
