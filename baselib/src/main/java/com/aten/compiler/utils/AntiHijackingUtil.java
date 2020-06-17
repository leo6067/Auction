package com.aten.compiler.utils;

import com.aten.compiler.utils.activityManager.AppManager_Acivity;


/**
 * ================================================
 * 项目名称：dgonline-android
 * 包   名：com.aten.compiler.utils
 * 作   者：彭俊鸿
 * 邮   箱：1031028399@qq.com
 * 版   本：1.0
 * 创建日期：2018/12/24
 * 描   述：activity 防劫持代码
 * ================================================
 */
public class AntiHijackingUtil {
    public static final String TAG = "AntiHijackingUtil";
    /**
     * 检测app是否在后台
     * true 运行在前台 false运行在后台
     */
    public static boolean isForeground() {
        return AppManager_Acivity.getInstance().getAppCount()>0;
    }
}
