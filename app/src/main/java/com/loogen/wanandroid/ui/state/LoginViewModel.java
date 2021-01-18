package com.loogen.wanandroid.ui.state;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;

import com.loogen.wanandroid.base.BaseModel;
import com.loogen.wanandroid.base.BaseViewModel;
import com.loogen.wanandroid.model.Request;
import com.loogen.wanandroid.model.UserModel;
import com.loogen.wanandroid.request.entity.LoginData;
import com.loogen.wanandroid.request.entity.LogoutData;
import com.loogen.wanandroid.request.entity.RegisterData;

public class LoginViewModel extends BaseViewModel<UserModel> {
    public final MutableLiveData<LoginData> loginData = new MutableLiveData<>();
    public final MutableLiveData<RegisterData> registerData = new MutableLiveData<>();
    public final MutableLiveData<LogoutData> logoutData = new MutableLiveData<>();


    public final MutableLiveData<String> toastMsgData = new MutableLiveData<>();
    public final ObservableBoolean loading = new ObservableBoolean(false);
    public final MutableLiveData<String> username = new MutableLiveData<>();
    public final MutableLiveData<String> password = new MutableLiveData<>();
    public final MutableLiveData<String> repassword = new MutableLiveData<>();

    public void login() {
        loading.set(true);
        model.login(username.getValue(), password.getValue(), new Request.IResult<LoginData>() {
            @Override
            public void onSuccess(Request<LoginData> data) {
                loading.set(false);
                loginData.setValue(data.getData());
            }

            @Override
            public void onError(Request<LoginData> error) {
                loading.set(false);
                toastMsgData.setValue(error.getMsg());
            }
        });
    }

    public void logout() {
        loading.set(true);
        model.logout(new Request.IResult<LogoutData>() {
            @Override
            public void onSuccess(Request<LogoutData> data) {
                loading.set(false);
                logoutData.setValue(data.getData());
            }

            @Override
            public void onError(Request<LogoutData> error) {
                loading.set(false);
                toastMsgData.setValue(error.getMsg());
            }
        });
    }

    public void register() {
        loading.set(true);
        model.register(username.getValue(), password.getValue(), repassword.getValue(), new Request.IResult<RegisterData>() {
            @Override
            public void onSuccess(Request<RegisterData> data) {
                loading.set(false);
                registerData.setValue(data.getData());
            }

            @Override
            public void onError(Request<RegisterData> error) {
                loading.set(false);
                toastMsgData.setValue(error.getMsg());
            }
        });
    }

    @Override
    public BaseModel getModel() {
        return new UserModel();
    }
}
