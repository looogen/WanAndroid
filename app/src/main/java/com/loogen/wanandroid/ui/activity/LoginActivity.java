package com.loogen.wanandroid.ui.activity;


import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Toast;


import com.loogen.wanandroid.BR;
import com.loogen.wanandroid.R;
import com.loogen.wanandroid.databinding.ActivityLoginBinding;
import com.loogen.wanandroid.ui.base.BaseActivity;
import com.loogen.wanandroid.ui.base.DataBindingConfig;
import com.loogen.wanandroid.ui.state.LoginViewModel;
import com.loogen.wanandroid.ui.state.ShareViewModel;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {

    private static final String TAG = "LoginActivity";

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        DataBindingConfig config = new DataBindingConfig(R.layout.activity_login, BR.vm);
        config.addBindingParam(BR.click, new ClickProxy());
        return config;
    }

    @Override
    protected void init() {
        mViewDataBinding.passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString().trim())) {
                    mViewDataBinding.passwordTextInput.setError(null); //Clear the error
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mViewDataBinding.usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString().trim())) {
                    mViewDataBinding.usernameTextInput.setError(null); //Clear the error
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mViewModel.loginData.observe(this, loginData ->{
            //设置共享ViewModel
            ShareViewModel shareViewModel = getAppViewModelProvider().get(ShareViewModel.class);
            shareViewModel.setLoginDataValue(loginData);
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
        mViewModel.toastMsgData.observe(this, s -> Toast.makeText(LoginActivity.this,s, Toast.LENGTH_LONG).show());
    }


    public class ClickProxy {

        public void login() {
            if (TextUtils.isEmpty(mViewModel.username.getValue())) {
                mViewDataBinding.usernameTextInput.setError(getString(R.string.login_error_username));
                return;
            }
            if (TextUtils.isEmpty(mViewModel.password.getValue())) {
                mViewDataBinding.passwordTextInput.setError(getString(R.string.login_error_password));
                return;
            }
            mViewModel.login();
        }

        public void register() {

        }

        public void logout() {
            mViewModel.logout();
        }
    }


}