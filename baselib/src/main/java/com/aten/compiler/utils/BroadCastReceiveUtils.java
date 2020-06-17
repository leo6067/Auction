package com.aten.compiler.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;

/**
 * ================================================
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2018/8/23
 * 描    述：广播工具类
 * ================================================
 */
public abstract class BroadCastReceiveUtils extends BroadcastReceiver {

    //------------------------------------------------注册全局 broadcastreceive  start-----------------------------
    //注册全局广播
    public static void registerReceiver(Context context,String ACTION,BroadCastReceiveUtils beforeDateChangeReceiver) {
        try {
            IntentFilter myIntentFilter = new IntentFilter();
            myIntentFilter.addAction(ACTION);
            context.getApplicationContext().registerReceiver(beforeDateChangeReceiver, myIntentFilter);
        } catch (Exception var3) {

        }
    }

    //解绑全局广播
    public static void unregisterReceiver(Context context, BroadCastReceiveUtils beforeDateChangeReceiver) {
        try {
            context.getApplicationContext().unregisterReceiver(beforeDateChangeReceiver);
        } catch (Exception var3) {

        }
    }

    //发送全局广播
    public static void sendBroadCast(Context context,String ACTION) {
        Intent intent = new Intent();
        intent.setAction(ACTION);
        intent.setPackage("com.aten.dgonline_android");
        context.sendBroadcast(intent);
    }
    //------------------------------------------------注册全局 broadcastreceive  end-----------------------------

    //------------------------------------------------注册本地 broadcastreceive  start-----------------------------

    //注册本地广播
    public static void registerLocalReceiver(Context context,String ACTION,BroadCastReceiveUtils beforeDateChangeReceiver) {

        //步骤2：实例化LocalBroadcastManager的实例
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);

        try {
            IntentFilter myIntentFilter = new IntentFilter();
            myIntentFilter.addAction(ACTION);
            localBroadcastManager.registerReceiver(beforeDateChangeReceiver, myIntentFilter);
        } catch (Exception var3) {

        }
    }

    //解绑本地广播
    public static void unregisterLocalReceiver(Context context, BroadCastReceiveUtils beforeDateChangeReceiver) {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
        try {
            localBroadcastManager.unregisterReceiver(beforeDateChangeReceiver);
        } catch (Exception var3) {

        }
    }

    //发送本地广播
    public static void sendLocalBroadCast(Context context,String ACTION) {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
        Intent intent = new Intent();
        intent.setAction(ACTION);
        localBroadcastManager.sendBroadcast(intent);
    }


    //发送本地广播
    public static void sendLocalBroadCast(Context context,String ACTION,String value) {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
        Intent intent = new Intent();
        intent.putExtra("value",value);
        intent.setAction(ACTION);
        localBroadcastManager.sendBroadcast(intent);
    }

    //发送本地广播
    public static void sendLocalBroadCast(Context context,Intent intent) {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
        localBroadcastManager.sendBroadcast(intent);
    }

    //------------------------------------------------注册本地 broadcastreceive  end-----------------------------

    @Override
    public abstract void onReceive(Context context, Intent intent);
}
