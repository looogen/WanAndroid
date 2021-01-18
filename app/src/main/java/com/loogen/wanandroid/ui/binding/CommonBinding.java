package com.loogen.wanandroid.ui.binding;

import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.BindingAdapter;

public class CommonBinding {

    @BindingAdapter("bindVisible")
    public static void bindVisibility(View view, boolean isVisible) {
        if (isVisible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    @BindingAdapter("bindNavIcon")
    public static void bindToolbarNavIcon(View view, int iconRes) {
        if (view instanceof Toolbar) {
            if (iconRes > 0) {
                ((Toolbar) view).setNavigationIcon(iconRes);
            } else {
                ((Toolbar) view).setNavigationIcon(null);
            }
        }

    }
}
