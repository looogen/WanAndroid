package com.loogen.wanandroid.ui.state;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loogen.wanandroid.request.entity.LoginData;

public class ShareViewModel extends ViewModel {

    private MutableLiveData<LoginData> loginDataLiveData = new MutableLiveData<>();

    public void setLoginDataValue(LoginData loginData) {
        loginDataLiveData.setValue(loginData);
    }

    public LiveData<LoginData> getLoginDataLiveData() {
        return loginDataLiveData;
    }
}
