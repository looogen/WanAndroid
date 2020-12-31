package com.loogen.wanandroid.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.loogen.wanandroid.R;
import com.loogen.wanandroid.request.HttpRequestManager;

import io.reactivex.schedulers.Schedulers;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "TestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_register).setOnClickListener(this);
        findViewById(R.id.btn_logout).setOnClickListener(this);
        findViewById(R.id.btn_get).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_login:

                break;
            case R.id.btn_register:
                HttpRequestManager.getWanAndroidService().register("llg","566778889","566778889")
                        .subscribeOn(Schedulers.io())
                .subscribe(responseBody -> {

                });
                break;
            case R.id.btn_logout:
                HttpRequestManager.getWanAndroidService().logout()
                        .subscribeOn(Schedulers.io())
                        .subscribe(responseBody -> {

                });
            case R.id.btn_get:
                HttpRequestManager.getWanAndroidService().favorite()
                        .subscribeOn(Schedulers.io())
                        .subscribe(responseBody ->{

                        });
                break;
        }

    }
}