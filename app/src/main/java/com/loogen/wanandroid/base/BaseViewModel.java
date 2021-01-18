package com.loogen.wanandroid.base;

import androidx.lifecycle.ViewModel;


public abstract class BaseViewModel<M extends BaseModel> extends ViewModel {
    protected M model;

    public abstract BaseModel getModel();

    public BaseViewModel() {
        model = (M) getModel();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (model != null) {
            model.onCleared();
        }
    }
}
