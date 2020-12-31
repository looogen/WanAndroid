package com.loogen.wanandroid.request;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.loogen.wanandroid.App;
import com.loogen.wanandroid.BuildConfig;
import com.loogen.wanandroid.request.cookie.VerifyCookieJar;
import com.loogen.wanandroid.request.interceptors.NetWorkCacheInterceptor;
import com.loogen.wanandroid.request.interceptors.OfflineCacheInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public final class HttpRequestManager {

    public static final String TAG = "HttpRequestManager";

    private static volatile WanAndroidService service;

    public static WanAndroidService getWanAndroidService() {
        if (service == null) {
            synchronized (HttpRequestManager.class) {
                if (service == null) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Constants.BASE_API)
                            .client(getOkHttpClient())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    service = retrofit.create(WanAndroidService.class);
                }
            }
        }
        return service;
    }


    private static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.connectTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);

        //缓存
        File httpCacheDirectory = new File(App.getApp().getCacheDir(), "okhttpCache");
        int cacheSize = 20 * 1024 * 1024; // 20 MB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        builder.cache(cache);

        //自定义应用拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        builder.addInterceptor(httpLoggingInterceptor);
        builder.addInterceptor(new OfflineCacheInterceptor());

        //两分钟的缓存
        builder.addNetworkInterceptor(new NetWorkCacheInterceptor(2*60));
        //cookie 验证用户身份
        builder.cookieJar(new VerifyCookieJar());
        return builder.build();
    }


}
