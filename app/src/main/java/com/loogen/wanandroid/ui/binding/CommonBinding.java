package com.loogen.wanandroid.ui.binding;

import android.view.View;

import androidx.databinding.BindingAdapter;

public class CommonBinding {

    @BindingAdapter("bindVisible")
    public static void bindVisibility(View view,boolean isVisible){
        if (isVisible) {
            view.setVisibility(View.VISIBLE);
        }else {
            view.setVisibility(View.GONE);
        }
    }
}
