package com.loogen.wanandroid.request.interceptors;

import android.util.Log;

import com.loogen.wanandroid.App;
import com.loogen.wanandroid.utils.NetworkUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class OfflineCacheInterceptor implements Interceptor {

    private static final String TAG = "OfflineCacheInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetworkUtil.isNetworkAvailable(App.getApp())) {
            Log.i(TAG, "intercept: "+"NetworkNotAvailable");
            int offlineCacheTime = 60*60;//离线的时候的缓存的过期时间
            request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + offlineCacheTime)
                    .build();
            return chain.proceed(request);
        }
        return chain.proceed(request);
    }
}
