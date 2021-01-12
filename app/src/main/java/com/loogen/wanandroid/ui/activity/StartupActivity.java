package com.loogen.wanandroid.ui.activity;


import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import com.loogen.wanandroid.R;
import com.loogen.wanandroid.databinding.ActivityStartupBinding;
import com.loogen.wanandroid.request.Constants;
import com.loogen.wanandroid.request.cookie.PersistentCookieStore;
import com.loogen.wanandroid.ui.base.BaseActivity;
import com.loogen.wanandroid.ui.base.DataBindingConfig;
import com.loogen.wanandroid.ui.state.StartupViewModel;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

public class StartupActivity extends BaseActivity<ActivityStartupBinding, StartupViewModel> {
    public static final String TAG = "StartupActivity";

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_startup);
    }

    @Override
    protected void init() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (hasLogged()) {
                    intent = new Intent(StartupActivity.this, MainActivity.class);
                } else {
                    intent = new Intent(StartupActivity.this, AccountActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, 1_000);
    }


    private boolean hasLogged() {
        HttpUrl httpUrl = new HttpUrl.Builder()
                .host(Constants.DOMAIN)
                .scheme("https")
                .build();
        List<Cookie> cookies = PersistentCookieStore.getInstance().get(httpUrl);
        for (Cookie cookie : cookies) {
            if ("token_pass".equals(cookie.name())) {
                return cookie.persistent();
            }
        }
        return false;
    }

}