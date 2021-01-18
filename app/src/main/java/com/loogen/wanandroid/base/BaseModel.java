package com.loogen.wanandroid.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseModel {
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public void onCleared() {
        mCompositeDisposable.clear();
    }

    protected void addDisposable(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }
}
