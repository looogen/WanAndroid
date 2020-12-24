package com.loogen.wanandroid.request;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WanAndroidService {

    /**
     * 登录
     * @param username 账号
     * @param password 密码
     * @return
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<ResponseBody> login(@Field("username") String username, @Field("password") String password);


    /**
     * 注册
     * @param username
     * @param password
     * @param repassword
     * @return
     */
    @POST("user/register")
    @FormUrlEncoded
    Observable<ResponseBody> register(@Field("username") String username,@Field("password") String password,@Field("repassword") String repassword);


    /**
     * 登出
     * @return
     */
    @GET("user/logout/json")
    Observable<ResponseBody> logout();


    /**
     * 收藏的网站列表
     * @return
     */
    @GET("lg/collect/usertools/json")
    Observable<ResponseBody> favorite();


    /**
     * 积分
     * @return
     */
    @GET("lg/coin/list/1/json")
    Observable<ResponseBody> coin();

}