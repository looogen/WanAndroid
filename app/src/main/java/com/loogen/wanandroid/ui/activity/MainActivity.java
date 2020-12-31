package com.loogen.wanandroid.ui.activity;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.loogen.wanandroid.R;
import com.loogen.wanandroid.databinding.ActivityMainBinding;
import com.loogen.wanandroid.ui.base.BaseActivity;
import com.loogen.wanandroid.ui.base.DataBindingConfig;
import com.loogen.wanandroid.ui.state.MainViewModel;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_main);
    }

    @Override
    protected void init() {

        setSupportActionBar(mViewDataBinding.toolBar);

        mViewDataBinding.navigationBar.inflateMenu(R.menu.bottom_navigaition);
        mViewDataBinding.navigationBar.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        mViewDataBinding.navigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
            }
        });
    }
}