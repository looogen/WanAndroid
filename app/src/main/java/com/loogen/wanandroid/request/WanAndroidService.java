package com.loogen.wanandroid.request;

import com.loogen.wanandroid.request.entity.ArticleListData;
import com.loogen.wanandroid.request.entity.HttpResult;
import com.loogen.wanandroid.request.entity.LoginData;
import com.loogen.wanandroid.request.entity.LogoutData;
import com.loogen.wanandroid.request.entity.RegisterData;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WanAndroidService {

    /**
     * 登录
     *
     * @param username 账号
     * @param password 密码
     * @return
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<HttpResult<LoginData>> login(@Field("username") String username, @Field("password") String password);

    /**
     * 注册
     *
     * @param username
     * @param password
     * @param repassword
     * @return
     */
    @POST("user/register")
    @FormUrlEncoded
    Observable<HttpResult<RegisterData>> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);


    /**
     * 登出
     *
     * @return
     */
    @GET("user/logout/json")
    Observable<HttpResult<LogoutData>> logout();


    /**
     * https://www.wanandroid.com/article/list/0/json
     * <p>
     * 方法：GET
     * 参数：页码，拼接在连接中，从0开始。
     */
    @GET("article/list/{index}/json")
    Observable<HttpResult<ArticleListData>> getHomeList(@Path("index") int pageIndex);


    /**
     * 收藏的网站列表
     *
     * @return
     */
    @GET("lg/collect/usertools/json")
    Observable<ResponseBody> favorite();


    /**
     * 积分
     *
     * @return
     */
    @GET("lg/coin/list/1/json")
    Observable<ResponseBody> coin();

}