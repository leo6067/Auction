package com.sch.share.service

import android.accessibilityservice.AccessibilityServiceInfo
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import android.view.accessibility.AccessibilityManager
import anet.channel.util.Utils.context
import com.aten.compiler.utils.security.AccessibilityServicesCheckUtil
import com.sch.share.lifecycle.ActivityLifecycleCallbacksAdapter
import kotlin.math.log

/**
 * Created by StoneHui on 2019-11-28.
 * <p>
 * 无障碍服务管理
 */
object ServiceManager_WWDZ {

    /**
     * 打开服务的回调。
     */
    interface OnOpenServiceListener {
        /**
         * 打开服务的回调。
         *
         * @param isOpening 服务是否开启。
         */
        fun onResult(isOpening: Boolean)
    }

    /**
     * 检查服务是否开启。（玩物得志）
     */
    fun isServiceEnabled_WWDZ(context: Context): Boolean {
      return  WWDZShareMultiImageService.isAccessibilitySettingsOn(context.packageName,context);
    }








    /**
     * 打开服务。
     *
     * @param listener 打开服务结果监听。
     */
    fun openService(context: Context, listener: (Boolean) -> Unit) {
        if (isServiceEnabled_WWDZ(context)) {
            listener(true)
            return
        }
        (context.applicationContext as Application).registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacksAdapter() {
            override fun onActivityResumed(activity: Activity?) {
                if (context::class.java == activity!!::class.java) {
                    (context.applicationContext as Application).unregisterActivityLifecycleCallbacks(this)
                    listener(isServiceEnabled_WWDZ(context))
                }
            }
        })
        //打开系统无障碍设置界面
        val accessibleIntent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
        context.startActivity(accessibleIntent)
    }

    /**
     * 打开服务。
     *
     * @param listener 打开服务结果监听。
     */
    fun openService(context: Context, listener: ServiceManager_WWDZ.OnOpenServiceListener) {
        openService(context) { listener.onResult(it) }
    }

}