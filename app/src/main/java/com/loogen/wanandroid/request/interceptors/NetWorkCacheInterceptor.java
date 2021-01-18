package com.loogen.wanandroid.request.interceptors;


import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class NetWorkCacheInterceptor implements Interceptor {

    private static final String TAG = "NetWorkCacheInterceptor";


    //在线的时候的缓存过期时间，如果想要不缓存，直接时间设置为0
    private int onlineCacheTime = 0;

    public NetWorkCacheInterceptor(int onlineCacheTime) {
        this.onlineCacheTime = onlineCacheTime;
    }

    public NetWorkCacheInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        Log.i(TAG, "intercept: " + response.headers().toString());

        return response.newBuilder()
                .header("Cache-Control", "public, max-age=" + onlineCacheTime)
                .removeHeader("Pragma")
                .build();
    }
}
