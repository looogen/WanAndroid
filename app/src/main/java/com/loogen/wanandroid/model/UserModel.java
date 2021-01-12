package com.loogen.wanandroid.model;

import com.loogen.wanandroid.request.HttpRequestManager;
import com.loogen.wanandroid.request.entity.HttpResult;
import com.loogen.wanandroid.request.entity.LoginData;
import com.loogen.wanandroid.request.entity.LogoutData;
import com.loogen.wanandroid.request.entity.RegisterData;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 用户登录相关，数据业务类
 */
public class UserModel implements IModel {

    public static final String TAG = "LoginModel";

    private final CompositeDisposable mCompositeDisposable;

    public UserModel() {
        mCompositeDisposable = new CompositeDisposable();
    }


    // 测试账号"llg","566778889"
    public void login(String username, String password, DataResult.IResult<LoginData> result) {
        HttpRequestManager.getWanAndroidService().login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<LoginData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull HttpResult<LoginData> httpResult) {
                        if (httpResult.getErrorCode() != 0) {
                            DataResult.Error error = new DataResult.Error(httpResult.getErrorMsg());
                            result.onError(error);
                        }else {
                            DataResult.Success<LoginData> data = new DataResult.Success<>(httpResult.getData());
                            result.onSuccess(data);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        DataResult.Error error = new DataResult.Error(e.getMessage());
                        result.onError(error);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void register(String username, String password, String repassword,DataResult.IResult<RegisterData> result) {
        HttpRequestManager.getWanAndroidService().register(username, password, repassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<RegisterData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull HttpResult<RegisterData> httpResult) {
                        if (httpResult.getErrorCode() != 0) {
                            DataResult.Error error = new DataResult.Error(httpResult.getErrorMsg());
                            result.onError(error);
                        }else {
                            DataResult.Success<RegisterData> data = new DataResult.Success<>(httpResult.getData());
                            result.onSuccess(data);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        DataResult.Error error = new DataResult.Error(e.getMessage());
                        result.onError(error);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    public void logout(DataResult.IResult<LogoutData> result) {
        HttpRequestManager.getWanAndroidService().logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<LogoutData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull HttpResult<LogoutData> httpResult) {
                        if (httpResult.getErrorCode() != 0) {
                            DataResult.Error error = new DataResult.Error(httpResult.getErrorMsg());
                            result.onError(error);
                        }else {
                            DataResult.Success<LogoutData> data = new DataResult.Success<>(httpResult.getData());
                            result.onSuccess(data);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        DataResult.Error error = new DataResult.Error(e.getMessage());
                        result.onError(error);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void onClear() {
        mCompositeDisposable.clear();
    }
}
