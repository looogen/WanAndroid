package com.loogen.wanandroid.ui.activity;

import android.util.Log;

import androidx.annotation.StringDef;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.loogen.wanandroid.R;
import com.loogen.wanandroid.base.BaseActivity;
import com.loogen.wanandroid.base.DataBindingConfig;
import com.loogen.wanandroid.databinding.ActivityMainBinding;
import com.loogen.wanandroid.ui.fragment.HomeFragment;
import com.loogen.wanandroid.ui.fragment.MineFragment;
import com.loogen.wanandroid.ui.fragment.OpenProjectFragment;
import com.loogen.wanandroid.ui.fragment.SortFragment;
import com.loogen.wanandroid.ui.fragment.SubscriptionFragment;
import com.loogen.wanandroid.ui.state.MainViewModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {


    private static final String TAG = "MainActivity";

    @StringDef({FragmentTag.HOME, FragmentTag.SUBSCRIBE, FragmentTag.MINE, FragmentTag.SORT, FragmentTag.PROJECT})
    @Target({ElementType.PARAMETER, ElementType.FIELD})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FragmentTag {
        String HOME = "home";
        String SUBSCRIBE = "subscribe";
        String SORT = "sort";
        String PROJECT = "project";
        String MINE = "mine";
    }

    private @FragmentTag
    String curFragmentTag;

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_main, BR.vm);
    }

    @Override
    protected void init() {
        switchFragment(FragmentTag.HOME);
        mViewDataBinding.navigationBar.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.homeFragment) {
                switchFragment(FragmentTag.HOME);
            } else if (id == R.id.subscriptionFragment) {
                switchFragment(FragmentTag.SUBSCRIBE);
            } else if (id == R.id.sortFragment) {
                switchFragment(FragmentTag.SORT);
            } else if (id == R.id.openProjectFragment) {
                switchFragment(FragmentTag.PROJECT);
            } else if (id == R.id.mineFragment) {
                switchFragment(FragmentTag.MINE);
            }
            return true;
        });
    }

    private void switchFragment(@FragmentTag String tag) {

        if (tag.equals(curFragmentTag)) {
            Log.i(TAG, "switchFragment: Same Fragment TAG");
            return;
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        //隐藏所有Fragment
        for (Fragment hideFragment : fm.getFragments()) {
            transaction.hide(hideFragment);
        }

        Fragment fragment = fm.findFragmentByTag(tag);
        if (fragment != null) {
            transaction.show(fragment);
        } else {
            switch (tag) {
                case FragmentTag.HOME:
                    fragment = new HomeFragment();
                    break;
                case FragmentTag.SUBSCRIBE:
                    fragment = new SubscriptionFragment();
                    break;
                case FragmentTag.SORT:
                    fragment = new SortFragment();
                    break;
                case FragmentTag.PROJECT:
                    fragment = new OpenProjectFragment();
                    break;
                case FragmentTag.MINE:
                    fragment = new MineFragment();
                    break;
            }
            transaction.add(R.id.fragment_container, fragment, tag);
        }
        transaction.commit();
        curFragmentTag = tag;
    }


}