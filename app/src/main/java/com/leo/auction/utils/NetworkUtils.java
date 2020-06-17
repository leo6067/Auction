package com.leo.auction.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.aten.compiler.utils.ToastUtils;
import com.leo.auction.base.BaseAppContext;

/**
 * Created by leo
 */
public class NetworkUtils {




    //判断是否有网络
    public static boolean isNetworkConnected()
    {
        if (BaseAppContext.getInstance() != null)
        {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) BaseAppContext.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }else{
                ToastUtils.showShort("网络不通畅，请检查网络连接设置。");
                return false;
            }
        }
        return false;
    }





    /**
     * 判断是否是wifi连接
     */
    public static boolean isWifi()
    {

        if (BaseAppContext.getInstance() != null)
        {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) BaseAppContext.getInstance()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;

//        if (cm == null)
//            return false;
//        return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;

    }




    public static boolean isMobileConnected() {
        if (BaseAppContext.getInstance() != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) BaseAppContext.getInstance()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }



    public static int getConnectedType() {
        if (BaseAppContext.getInstance() != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) BaseAppContext.getInstance()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }








    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity)
    {
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings",
                "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }
}
