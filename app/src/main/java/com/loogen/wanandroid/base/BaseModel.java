package com.loogen.wanandroid.model;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseModel {
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();



    public void onClear() {
        mCompositeDisposable.clear();
    }

    protected void addDisposable(Disposable disposable){
        mCompositeDisposable.add(disposable);
    }
}
