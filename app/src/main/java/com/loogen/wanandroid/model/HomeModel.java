package com.loogen.wanandroid.model;

import android.util.Log;

import com.loogen.wanandroid.base.BaseModel;
import com.loogen.wanandroid.request.HttpRequestManager;
import com.loogen.wanandroid.request.entity.ArticleListData;
import com.loogen.wanandroid.request.entity.HttpResult;
import com.loogen.wanandroid.utils.RxUtil;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class HomeModel extends BaseModel {
    public static final String TAG = "HomeModel";

    public void getArticleList(int pageIndex, DataResult.IResult<ArticleListData> result) {
        HttpRequestManager.getWanAndroidService()
                .getHomeList(pageIndex)
                .compose(RxUtil.rxRequestSchedulerHelper())
                .subscribe(new Observer<HttpResult<ArticleListData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull HttpResult<ArticleListData> data) {
                        result.onSuccess(new DataResult.Success<>(data.getData()));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        result.onError(new DataResult.Error(e.getMessage()));
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
