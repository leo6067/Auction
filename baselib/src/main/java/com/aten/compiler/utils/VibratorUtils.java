package com.aten.compiler.utils;

import android.app.Service;
import android.content.Context;
import android.os.Vibrator;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.aten.compiler.utils
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/12/16 0016
 * 描    述：Android 震动工具类
 * ================================================
 */
public class VibratorUtils {
    /**
     * 内部类实现单例模式
     * 延迟加载，减少内存开销
     */
    private static class SingletonHolder {
        private static VibratorUtils instance = new VibratorUtils();
    }

    /**
     * 私有的构造函数
     */
    private VibratorUtils() {
    }

    public static VibratorUtils getInstance() {
        return VibratorUtils.SingletonHolder.instance;
    }

    public void vibrator(Context context,long milliseconds){
        /**震动服务*/
        Vibrator vib = (Vibrator)context.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(milliseconds);//只震动一秒，一次
//                long[] pattern = {1000,2000};
        //两个参数，一个是自定义震动模式，
        //数组中数字的含义依次是静止的时长，震动时长，静止时长，震动时长。。。时长的单位是毫秒
        //第二个是“是否反复震动”,-1 不重复震动
        //第二个参数必须小于pattern的长度，不然会抛ArrayIndexOutOfBoundsException
//                vib.vibrate(pattern, -1);
    }


}
