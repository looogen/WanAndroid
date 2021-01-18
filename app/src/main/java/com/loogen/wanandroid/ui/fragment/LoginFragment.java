package com.loogen.wanandroid.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.loogen.wanandroid.BR;
import com.loogen.wanandroid.R;
import com.loogen.wanandroid.base.BaseFragment;
import com.loogen.wanandroid.base.DataBindingConfig;
import com.loogen.wanandroid.databinding.FragmentLoginBinding;
import com.loogen.wanandroid.ui.activity.MainActivity;
import com.loogen.wanandroid.ui.state.AccountViewModel;
import com.loogen.wanandroid.ui.state.LoginViewModel;
import com.loogen.wanandroid.ui.state.ShareViewModel;

public class LoginFragment extends BaseFragment<FragmentLoginBinding, LoginViewModel> {

    public static final String TAG = "LoginFragment";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        AccountViewModel accountViewModel = getActivityViewModel(AccountViewModel.class);

        //toolbar
        accountViewModel.title.set(getString(R.string.account_login_title));
        accountViewModel.navIcon.set(0);

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

        mViewModel.loginData.observe(getViewLifecycleOwner(), loginData -> {
            //设置共享ViewModel
            ShareViewModel shareViewModel = getAppViewModelProvider().get(ShareViewModel.class);
            shareViewModel.setLoginDataValue(loginData);
            Intent intent = new Intent(mActivity, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        mViewModel.toastMsgData.observe(getViewLifecycleOwner(), s -> Toast.makeText(mActivity, s, Toast.LENGTH_LONG).show());
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        DataBindingConfig config = new DataBindingConfig(R.layout.fragment_login, BR.vm);
        config.addBindingParam(BR.click, new ClickProxy());
        return config;
    }

    public class ClickProxy {
        public void login(View view) {
            if (TextUtils.isEmpty(mViewModel.username.getValue())) {
                mViewDataBinding.usernameTextInput.setError(getString(R.string.account_login_error_username));
                return;
            }
            if (TextUtils.isEmpty(mViewModel.password.getValue())) {
                mViewDataBinding.passwordTextInput.setError(getString(R.string.account_login_error_password));
                return;
            }
            mViewModel.login();
        }

        public void register(View view) {
            NavController controller = Navigation.findNavController(view);
            controller.navigate(R.id.action_loginFragment_to_registerFragment);
        }
    }

}
