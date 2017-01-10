package me.stiky;

import android.app.Application;

import me.stiky.utlis.UiUtil;

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
