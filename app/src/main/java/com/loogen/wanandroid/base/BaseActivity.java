package com.loogen.wanandroid.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.loogen.wanandroid.App;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public abstract class BaseActivity<V extends ViewDataBinding, VM extends ViewModel> extends AppCompatActivity {

    protected V mViewDataBinding;
    protected VM mViewModel;
    private ViewModelProvider mActivityProvider;
    private ViewModelProvider.Factory mFactory;

    protected VM initViewModel() {
        return null;
    }

    protected abstract DataBindingConfig getDataBindingConfig();


    @ViewModelScope
    protected int getViewModelStoreScope() {
        // viewModel生命周期默认跟随activity
        return ViewModelScope.ACTIVITY_SCOPE;
    }

    /**
     * init data and view
     */
    protected abstract void init();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create view
        DataBindingConfig dataBindingConfig = getDataBindingConfig();
        mViewDataBinding = DataBindingUtil.setContentView(this, dataBindingConfig.getLayout());
        mViewDataBinding.setLifecycleOwner(this);

        //create viewModel
        mViewModel = initViewModel();
        if (mViewModel == null) {
            Class modelClass;
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
                if (getViewModelStoreScope() == ViewModelScope.ACTIVITY_SCOPE) {
                    mViewModel = (VM) getActivityViewModel(modelClass);
                } else if (getViewModelStoreScope() == ViewModelScope.APPLICATION_SCOPE) {
                    mViewModel = (VM) getAppViewModelProvider().get(modelClass);
                } else {
                    throw new IllegalArgumentException("ViewModelStoreScope not support");
                }
            }
        }
        if (dataBindingConfig.getVmVariableId() != 0) {
            mViewDataBinding.setVariable(dataBindingConfig.getVmVariableId(), mViewModel);
        }

        //other
        SparseArray<Object> bindingParams = dataBindingConfig.getBindingParams();
        for (int i = 0, length = bindingParams.size(); i < length; i++) {
            mViewDataBinding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i));
        }
        init();
    }


    protected <T extends ViewModel> T getActivityViewModel(@NonNull Class<T> modelClass) {
        if (mActivityProvider == null) {
            mActivityProvider = new ViewModelProvider(this);
        }
        return mActivityProvider.get(modelClass);
    }

    protected ViewModelProvider getAppViewModelProvider() {
        return new ViewModelProvider((App) this.getApplicationContext(),
                getAppFactory(this));
    }

    private ViewModelProvider.Factory getAppFactory(Activity activity) {
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
}
