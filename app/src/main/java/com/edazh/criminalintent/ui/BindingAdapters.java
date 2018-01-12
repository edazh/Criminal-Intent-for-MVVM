package com.edazh.criminalintent.ui;

import android.databinding.BindingAdapter;
import android.view.View;

/**
 * Created by edazh on 2018/1/12 0012.
 * e-mail:edazh@qq.com
 */

public class BindingAdapters {

    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
