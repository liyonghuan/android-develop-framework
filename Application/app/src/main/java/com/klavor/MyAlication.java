package com.klavor;

import android.app.Application;

import com.klavor.utlis.UiUtil;

/**
 * Created by LiHuan on 08/19/2016.
 */
public class MyAlication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        UiUtil.init(this);
    }
}
