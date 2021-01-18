package com.loogen.wanandroid.model;

import com.loogen.wanandroid.base.BaseModel;
import com.loogen.wanandroid.request.HttpRequestManager;
import com.loogen.wanandroid.request.entity.HttpResult;
import com.loogen.wanandroid.request.entity.LoginData;
import com.loogen.wanandroid.request.entity.LogoutData;
import com.loogen.wanandroid.request.entity.RegisterData;
import com.loogen.wanandroid.utils.RxUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 用户登录相关，数据业务类
 */
public class UserModel extends BaseModel {

    public static final String TAG = "LoginModel";


    // 测试账号"llg","566778889"
    public void login(String username, String password, Request.IResult<LoginData> result) {
        HttpRequestManager.getWanAndroidService().login(username, password)
                .compose(RxUtil.rxRequestSchedulerHelper())
                .subscribe(new Observer<HttpResult<LoginData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull HttpResult<LoginData> httpResult) {
                        if (httpResult.getErrorCode() != 0) {
                            result.onError(Request.error(httpResult.getErrorMsg()));
                        } else {
                            result.onSuccess(Request.success(httpResult.getData()));
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        result.onError(Request.error(e.getMessage()));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void register(String username, String password, String repassword, Request.IResult<RegisterData> result) {
        HttpRequestManager.getWanAndroidService().register(username, password, repassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<RegisterData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull HttpResult<RegisterData> httpResult) {
                        if (httpResult.getErrorCode() != 0) {
                            result.onError(Request.error(httpResult.getErrorMsg()));
                        } else {
                            result.onSuccess(Request.success(httpResult.getData()));
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        result.onError(Request.error(e.getMessage()));
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    public void logout(Request.IResult<LogoutData> result) {
        HttpRequestManager.getWanAndroidService().logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<LogoutData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull HttpResult<LogoutData> httpResult) {
                        if (httpResult.getErrorCode() != 0) {
                            result.onError(Request.error(httpResult.getErrorMsg()));
                        } else {
                            result.onSuccess(Request.success(httpResult.getData()));
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        result.onError(Request.error(e.getMessage()));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
