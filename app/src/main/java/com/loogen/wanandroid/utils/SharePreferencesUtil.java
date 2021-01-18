package com.loogen.wanandroid.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.loogen.wanandroid.App;

/**
 * SP工具类
 * <p>
 * <p>
 * created by loogen on 2020-12-22
 */
public class SharePreferencesUtil {

    public static final String TAG = "SharePreferenceUtil";

    public static final String NAME_DEFAULT = "app_config";
    public static final String NAME_COOKIES = "request_cookies";


    public static void setCookies(String url, String value) {
        setValue(NAME_COOKIES, url, value, false);
    }

    public static String getCookies(String url) {
        SharedPreferences sp = App.getApp().getSharedPreferences(NAME_COOKIES, Context.MODE_PRIVATE);
        return sp.getString(url, "");
    }

    public static <T> void setValue(String key, T value, boolean commit) {
        setValue(NAME_DEFAULT, key, value, commit);
    }

    public static <T> void setValue(String key, T value) {
        setValue(NAME_DEFAULT, key, value, false);
    }

    @SuppressLint("ApplySharedPref")
    public static <T> void setValue(String name, String key, T value, boolean commit) {
        SharedPreferences sp = App.getApp().getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else {
            throw new IllegalArgumentException("value not support");
        }
        if (commit) {
            editor.commit();
        } else {
            editor.apply();
        }
    }

    public static String getString(String key) {
        return getString(NAME_DEFAULT, key);
    }

    public static String getString(String name, String key) {
        SharedPreferences sp = App.getApp().getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    public static int getInt(String key) {
        return getInt(NAME_DEFAULT, key);
    }

    public static int getInt(String name, String key) {
        SharedPreferences sp = App.getApp().getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getInt(key, -999);
    }


}
