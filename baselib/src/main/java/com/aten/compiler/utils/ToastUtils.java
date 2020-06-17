package com.aten.compiler.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;




/**
 * Toast统一管理类
 */
public class ToastUtils {

    private static Toast toast;
    public static boolean isShow = true;

    private ToastUtils() {

  /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");

    }


    /**
     * 短时间显示Toast
     */
    public static void timeOut() {


        if (RxTool.getContext()!= null && isShow) {

            if (toast == null) {
                toast = Toast.makeText(RxTool.getContext(), "网络超时", Toast.LENGTH_SHORT);
            } else {
                toast.setText("网络超时");
                toast.setDuration(Toast.LENGTH_SHORT);
            }
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }

    }


    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(CharSequence message) {
        if (!TextUtils.isEmpty(message) && RxTool.getContext() != null && isShow) {
            if (toast == null) {
                toast = Toast.makeText(RxTool.getContext(),message, Toast.LENGTH_SHORT);
            } else {
                toast.setText(message);
                toast.setDuration(Toast.LENGTH_SHORT);
            }
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    /**
     * 短时间显示Toast
     * int 类型
     *
     * @param message
     */
    public static void showShort(int message) {
        if (RxTool.getContext() != null && isShow) {

            if (toast == null) {
                toast = Toast.makeText(RxTool.getContext(),message, Toast.LENGTH_SHORT);
            } else {
                toast.setText(message);
                toast.setDuration(Toast.LENGTH_SHORT);
            }
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }

    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(CharSequence message) {
        if (RxTool.getContext() != null && isShow) {

            if (toast == null) {
                toast = Toast.makeText(RxTool.getContext(),message, Toast.LENGTH_LONG);
            } else {
                toast.setText(message);
                toast.setDuration(Toast.LENGTH_SHORT);
            }
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }

    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(int message) {
        if (RxTool.getContext() != null && isShow) {

            if (toast == null) {
                toast = Toast.makeText(RxTool.getContext(),message, Toast.LENGTH_LONG);
            } else {
                toast.setText(message);
                toast.setDuration(Toast.LENGTH_SHORT);
            }
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }

    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public static void show(CharSequence message, int duration) {
        if (RxTool.getContext() != null && isShow) {
            Toast.makeText(RxTool.getContext(), message, duration).show();
        }
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public static void show(int message, int duration) {
        if (RxTool.getContext() != null && isShow) {
            Toast.makeText(RxTool.getContext(), message, duration).show();
        }
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {


        if (!TextUtils.isEmpty(message) && RxTool.getContext() != null && isShow) {

            if (toast == null) {
                toast = Toast.makeText(RxTool.getContext(),message, Toast.LENGTH_SHORT);
            } else {
                toast.setText(message);
                toast.setDuration(Toast.LENGTH_SHORT);
            }
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }

    }



    /**
     * 网络超时提醒
     *
     * @param context
     */
    public static void TimeOut(Context context) {
        if (RxTool.getContext() != null && isShow) {

            if (toast == null) {
                toast = Toast.makeText(RxTool.getContext(), "网络超时", Toast.LENGTH_SHORT);
            } else {
                toast.setText("网络超时");
                toast.setDuration(Toast.LENGTH_SHORT);
            }
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }

    }



}