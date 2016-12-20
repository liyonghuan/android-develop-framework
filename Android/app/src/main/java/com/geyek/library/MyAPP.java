package com.geyek.library;

import android.app.Application;

import com.geyek.library.utils.UiUtil;

/**
 * Created by LiHuan on 08/19/2016.
 */
public class MyAPP extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        UiUtil.init(this);
    }
}
