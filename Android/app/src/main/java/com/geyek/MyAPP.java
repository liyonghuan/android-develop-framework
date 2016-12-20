package com.geyek;

import android.app.Application;

import com.geyek.utlis.UiUtil;

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
