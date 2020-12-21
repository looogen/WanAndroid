package com.loogen.wanandroid.request;

import android.util.Log;

import com.loogen.wanandroid.request.cookie.PersistentCookieStore;

import java.util.Collections;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * created by loogen on 2020-12-22
 * 使用cookies来验证用户身份
 */
public class VerifyCookieJar implements CookieJar {

    public static final String TAG = "VerifyCookieJar";

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        Log.i(TAG, "url" + url.toString());
        //账号相关操作时去保存Cookies
        if (url.toString().contains("user/login") ||
                url.toString().contains("user/register") ||
                url.toString().contains("user/logout")) {
            PersistentCookieStore.getInstance().add(url, cookies);
        }
    }
    

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        Log.i(TAG, "url" + url.toString());
        if (!url.toString().contains("user/login") &&
                !url.toString().contains("user/register") &&
                !url.toString().contains("user/logout")) {
            return PersistentCookieStore.getInstance().get(url);
        }else {
            return Collections.emptyList();
        }
    }
}
