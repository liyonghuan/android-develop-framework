package com.klavor.utlis;

import android.util.Log;

/**
 * Created by LiHuan on 08/19/2016.
 *
 * I want to add a function that can update data to my database on the internet.
 * But now, it's useless.
 */
public class LogUtil {
    private static boolean isOpen = false;  //this's variable is control the Log to print.

    /**
     * Open the Log function.
     */
    public static void open() {
        isOpen = true;
    }

    /**
     * Close the Log function.
     */
    public static void close() {
        isOpen = false;
    }

    /**
     * Print a Log like Log.v().
     * @param tag Log's tag.
     * @param msg Log's content.
     */
    public static void v(String tag, String msg) {
        if (isOpen) Log.v(tag, msg);
    }

    /**
     * Print a Log like Log.d().
     * @param tag Log's tag.
     * @param msg Log's content.
     */
    public static void d(String tag, String msg) {
        if (isOpen) Log.d(tag, msg);
    }

    /**
     * Print a Log like Log.i().
     * @param tag Log's tag.
     * @param msg Log's content.
     */
    public static void i(String tag, String msg) {
        if (isOpen) Log.i(tag, msg);
    }

    /**
     * Print a Log like Log.w().
     * @param tag Log's tag.
     * @param msg Log's content.
     */
    public static void w(String tag, String msg) {
        if (isOpen) Log.w(tag, msg);
    }

    /**
     * Print a Log like Log.e().
     * @param tag Log's tag.
     * @param msg Log's content.
     */
    public static void e(String tag, String msg) {
        if (isOpen) Log.e(tag, msg);
    }

    /**
     * Print a Log like Log.wtf().
     * @param tag Log's tag.
     * @param msg Log's content.
     */
    public static void wtf(String tag, String msg) {
        if (isOpen) Log.wtf(tag, msg);
    }

    /**
     * Print a Log like Log.println().
     * @param priority Log's priority.
     * @param tag Log's tag.
     * @param msg Log's content.
     */
    public static void println(int priority, String tag, String msg) {
        if (isOpen) Log.println(priority, tag, msg);
    }
}
