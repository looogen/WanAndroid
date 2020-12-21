package com.loogen.wanandroid.request.interceptors;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * created by loogen on 2020-12-21
 */
public class HeaderInterceptor implements Interceptor {

    public static final String TAG = "HeaderInterceptor";


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
//
//        request.newBuilder().
        return chain.proceed(request);
    }
}
