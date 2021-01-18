package com.loogen.wanandroid.base;

import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.loogen.wanandroid.base.ViewModelScope.ACTIVITY_SCOPE;
import static com.loogen.wanandroid.base.ViewModelScope.APPLICATION_SCOPE;
import static com.loogen.wanandroid.base.ViewModelScope.FRAGMENT_SCOPE;


@IntDef({FRAGMENT_SCOPE, ACTIVITY_SCOPE, APPLICATION_SCOPE})
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface ViewModelScope {
    /**
     * viewModel 生命周期跟随fragment
     */
    int FRAGMENT_SCOPE = 0;
    /**
     * viewModel 生命周期跟随activity
     */
    int ACTIVITY_SCOPE = 1;

    /**
     * viewModel 生命周期跟随application
     */
    int APPLICATION_SCOPE = 2;
}
