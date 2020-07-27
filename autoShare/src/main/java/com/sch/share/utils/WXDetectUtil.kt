package com.sch.share.utils

import android.content.Context
import com.sch.share.constant.WWDZ_PACKAGE_NAME
import com.sch.share.constant.WX_PACKAGE_NAME
import com.sch.share.constant.XY_PACKAGE_NAME

/**
 * Created by StoneHui on 2019-11-28.
 * <p>
 * 检测工具
 */

object WXDetectUtil {

    //判断是否安装微信
    fun isWXInstalled(context: Context): Boolean {
        context.packageManager.getInstalledPackages(0)
                ?.filter { it.packageName.equals(WX_PACKAGE_NAME, true) }
                ?.let { return it.isNotEmpty() }
        return false
    }
    //获取微信的版本号
    fun getWXVersionCode(context: Context): Int {
        return context.packageManager.getInstalledPackages(0)
                ?.filter { it.packageName.equals(WX_PACKAGE_NAME, true) }
                ?.let { if (it.isEmpty()) 0 else it[0].versionCode }
                ?: 0
    }
    //判断是否安装闲鱼
    fun isXYInstalled(context: Context): Boolean {
        context.packageManager.getInstalledPackages(0)
                ?.filter { it.packageName.equals(XY_PACKAGE_NAME, true) }
                ?.let { return it.isNotEmpty() }
        return false
    }

    //判断是否安装玩物得志
    fun isWWDZInstalled(context: Context): Boolean {
        context.packageManager.getInstalledPackages(0)
                ?.filter { it.packageName.equals(WWDZ_PACKAGE_NAME, true) }
                ?.let { return it.isNotEmpty() }
        return false
    }
}