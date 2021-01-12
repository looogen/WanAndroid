package com.loogen.wanandroid.ui.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.loogen.wanandroid.App;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseFragment<V extends ViewDataBinding,VM extends ViewModel> extends Fragment {

    protected V mViewDataBinding;
    protected VM mViewModel;
    protected AppCompatActivity mActivity;

    private ViewModelProvider mFragmentProvider;
    private ViewModelProvider mActivityProvider;
    private ViewModelProvider.Factory mFactory;

    protected VM initViewModel(){
        return null;
    }

    protected abstract DataBindingConfig getDataBindingConfig();


    @ViewModelScope
    protected int getViewModelStoreScope() {
        // viewModel生命周期默认跟随Fragment
        return ViewModelScope.FRAGMENT_SCOPE;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DataBindingConfig dataBindingConfig = getDataBindingConfig();

        //create view
        mViewDataBinding = DataBindingUtil.inflate(inflater, dataBindingConfig.getLayout(), container, false);
        mViewDataBinding.setLifecycleOwner(this);

        //create viewModel
        mViewModel =  initViewModel();
        if (mViewModel == null) {
            Class modelClass;
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
                if (getViewModelStoreScope() == ViewModelScope.ACTIVITY_SCOPE) {
                    mViewModel = (VM) getActivityViewModel(modelClass);
                } else if (getViewModelStoreScope() == ViewModelScope.APPLICATION_SCOPE) {
                    mViewModel = (VM) getAppViewModelProvider().get(modelClass);
                } else if (getViewModelStoreScope() == ViewModelScope.FRAGMENT_SCOPE){
                    mViewModel = (VM) getFragmentViewModel(modelClass);
                } else {
                    throw new IllegalArgumentException("ViewModelStoreScope not support");
                }
            }
        }
        if (mViewModel != null) {
            mViewDataBinding.setVariable(dataBindingConfig.getVmVariableId(),mViewModel);
        }

        //other
        SparseArray<Object> bindingParams = dataBindingConfig.getBindingParams();
        for (int i = 0, length = bindingParams.size(); i < length; i++) {
            mViewDataBinding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i));
        }
        return mViewDataBinding.getRoot();
    }

    protected <T extends ViewModel> T getFragmentViewModel(@NonNull Class<T> modelClass) {
        if (mFragmentProvider == null) {
            mFragmentProvider = new ViewModelProvider(this);
        }
        return mFragmentProvider.get(modelClass);
    }


    protected <T extends ViewModel> T getActivityViewModel(@NonNull Class<T> modelClass) {
        if (mActivityProvider == null) {
            mActivityProvider = new ViewModelProvider(mActivity);
        }
        return mActivityProvider.get(modelClass);
    }


    protected ViewModelProvider getAppViewModelProvider() {
        return new ViewModelProvider((App) mActivity.getApplicationContext(),
                getAppFactory(mActivity));
    }

    private ViewModelProvider.Factory getAppFactory(Activity activity) {
        checkActivity(this);
        Application application = checkApplication(activity);
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application);
        }
        return mFactory;
    }

    private Application checkApplication(Activity activity) {
        Application application = activity.getApplication();
        if (application == null) {
            throw new IllegalStateException("Your activity/fragment is not yet attached to "
                    + "Application. You can't request ViewModel before onCreate call.");
        }
        return application;
    }

    private void checkActivity(Fragment fragment) {
        Activity activity = fragment.getActivity();
        if (activity == null) {
            throw new IllegalStateException("Can't create ViewModelProvider for detached fragment");
        }
    }
}
