package com.klavor.utlis;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by LiHuan on 08/19/2016.
 */
public class UiUtil {
    private static Context mContext;

    /**
     * 初始化UiUtil工具类
     */
    public static void init(Context context) {
        mContext = context;
    }

    /**
     * 弹一个Toast
     *
     * @param message Toast内容
     */
    public static void toast(String message) {
        toast(message, Toast.LENGTH_SHORT);
    }

    /**
     * 弹一个Toast,指定显示时长
     *
     * @param message Toast内容
     * @param duration 时长
     */
    public static void toast(String message, int duration) {
        if (mContext != null) Toast.makeText(mContext, message, duration).show();
    }
}
