package com.loogen.wanandroid.request;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.loogen.wanandroid.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;


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

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        builder.addInterceptor(httpLoggingInterceptor);
        builder.cookieJar(new VerifyCookieJar());
        return builder.build();
    }


}
