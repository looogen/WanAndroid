package com.loogen.wanandroid.ui.activity;


import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.loogen.wanandroid.BR;
import com.loogen.wanandroid.R;
import com.loogen.wanandroid.base.BaseActivity;
import com.loogen.wanandroid.base.DataBindingConfig;
import com.loogen.wanandroid.databinding.ActivityAccountBinding;
import com.loogen.wanandroid.ui.state.AccountViewModel;

public class AccountActivity extends BaseActivity<ActivityAccountBinding, AccountViewModel> {

    private static final String TAG = "AccountActivity";

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_account, BR.vm);
    }

    @Override
    protected void init() {
        mViewDataBinding.toolbarLayout.toolBar.setNavigationOnClickListener(v -> {

            //findNavController 在host fragment或者里面的子view才能找到
            NavController navController = Navigation.findNavController(mViewDataBinding.fragment);
            if (!navController.popBackStack()) {
                // Call finish() on your Activity
                finish();
            } else {
                navController.navigateUp();
            }
        });


    }
}