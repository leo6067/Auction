package com.aten.compiler.utils;

import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * ================================================
 * 项目名称：dgonline-android
 * 包    名：com.aten.compiler.utils
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2018/11/22
 * 描    述：
 * ================================================
 */
public class GaodeMapLocationUtils {
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    private GaodeLocationListener gaodeLocationListener;

    /**
     * 内部类实现单例模式
     * 延迟加载，减少内存开销
     */
    private static class SingletonHolder {
        private static GaodeMapLocationUtils instance = new GaodeMapLocationUtils();
    }

    /**
     * 私有的构造函数
     */
    private GaodeMapLocationUtils() {
    }

    public static GaodeMapLocationUtils getInstance() {
        return GaodeMapLocationUtils.SingletonHolder.instance;
    }

    //初始化高德定位
    public void initGaoDeLocaltion(GaodeLocationListener gaodeLocationListener) {
        this.gaodeLocationListener=gaodeLocationListener;
        //初始化定位
        mLocationClient = new AMapLocationClient(RxTool.getContext());
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mLocationOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mLocationOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        mLocationOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        mLocationOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mLocationOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mLocationOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        mLocationOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);//可选，设置逆地理信息的语言，默认值为默认语言（根据所在地区选择语言）

        //设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
    }

    //声明定位回调监听器
    AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if (location.getErrorCode() == 0) {
                    gaodeLocationListener.locationSuccess(location);
                } else {
                    //定位失败
                    StringBuffer sb = new StringBuffer();
                    sb.append("定位失败" + "\n");
                    sb.append("错误码:" + location.getErrorCode() + "\n");
                    sb.append("错误信息:" + location.getErrorInfo() + "\n");
                    sb.append("错误描述:" + location.getLocationDetail() + "\n");
                    gaodeLocationListener.locationError(sb.toString());
                    LogUtils.e("66666",""+sb.toString());
                }
            } else {
                LogUtils.e("66666","定位失败6666，location is null");
            }
        }
    };

    //开启定位
    public void onStartLocation(){
        mLocationClient.startLocation();
    }

    //停止定位
    public void onStopLocation(){
        mLocationClient.stopLocation();
    }

    //停止定位
    public void onDestoryLocation(){
        if (null != mLocationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            mLocationClient.onDestroy();
            mLocationClient = null;
            mLocationClient = null;
        }
    }

    public interface GaodeLocationListener{
        void locationSuccess(AMapLocation location);
        void locationError(String errors);
    }
}
