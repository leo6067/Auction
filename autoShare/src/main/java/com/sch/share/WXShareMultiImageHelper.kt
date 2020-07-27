package com.sch.share

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import com.aten.compiler.constant.UrlConstant
import com.sch.share.manager.FileManager
import com.sch.share.service.ServiceManager
import com.sch.share.service.ServiceManager_WWDZ
import com.sch.share.service.ServiceManager_XY
import com.sch.share.share.SessionShare
import com.sch.share.share.TimelineShare
import com.sch.share.share.WWDZShare
import com.sch.share.share.XYShare
import java.io.File

/**
 * Created by StoneHui on 2018/10/25.
 * <p>
 * 微信多图分享辅助类。
 */
object WXShareMultiImageHelper {

    /**
     * 分享到好友会话。
     *
     * @param activity [Context]
     * @param images 图片列表。
     * @param text 分享文本。
     */
    @JvmStatic
    @JvmOverloads
    fun shareToSession(activity: Activity, images: Array<Bitmap>, text: String = "") {
        SessionShare.share(activity, images, text)
    }

    /**
     * 分享到好友会话。
     *
     * @param activity [Context]
     * @param images 图片列表。
     * @param text 分享文本。
     */
    @JvmStatic
    @JvmOverloads
    fun shareToSession(activity: Activity, images: Array<File>, text: String = "") {
        SessionShare.share(activity, images, text)
    }

    /**
     * 分享到朋友圈。
     *
     * @param activity [Context]
     * @param images 图片列表。
     * @param options [Options] 可选项。
     */
    @JvmStatic
    @JvmOverloads
    fun shareToTimeline(activity: Activity, images: Array<Bitmap>, options: Options = Options()) {
        TimelineShare.share(activity, images, options)
    }

    /**
     * 分享到朋友圈。
     *
     * @param activity [Context]
     * @param images 图片列表。
     * @param options [Options] 可选项。
     */
    @JvmStatic
    @JvmOverloads
    fun shareToTimeline(activity: Activity, images: Array<File>, options: Options = Options()) {
        TimelineShare.share(activity, images, options)
    }

    /**
     * 分享到闲鱼。
     *
     * @param activity [Context]
     * @param images 图片列表。
     * @param options [Options] 可选项。
     */
    @JvmStatic
    @JvmOverloads
    fun shareToXY(activity: Activity, images: Array<Bitmap>, options: Options = Options()) {
        XYShare.share(activity, images, options)
    }

    /**
     * 分享到玩物得志。
     *
     * @param activity [Context]
     * @param images 图片列表。
     * @param options [Options] 可选项。
     */
    @JvmStatic
    @JvmOverloads
    fun shareToWWDZ(activity: Activity, images: Array<Bitmap>, options: Options = Options()) {
        WWDZShare.share(activity, images, options)
    }

    /**
     * 清理临时文件。可在分享完成后调用该函数。
     */
    @JvmStatic
    fun clearTmpFile(context: Context) {
        FileManager.clearTmpFile(context)
    }

    /**
     * 清理临时文件。可在分享完成后调用该函数。
     */
    @JvmStatic
    fun clearTmpFile_video(context: Context,path:String) {
        FileManager.clearTmpFile_Video(context,path)
    }


    /**
     * 检查服务是否开启。
     */
    @JvmStatic
    fun isServiceEnabled(context: Context): Boolean {
        return ServiceManager.isServiceEnabled(context)
    }

    /**
     * 打开服务。
     *
     * @param listener 打开服务结果监听。
     */
    @JvmStatic
    fun openService(context: Context, listener: (Boolean) -> Unit) {
        ServiceManager.openService(context, listener)
    }

    /**
     * 打开服务。
     *
     * @param listener 打开服务结果监听。
     */
    @JvmStatic
    fun openService(context: Context, listener: ServiceManager.OnOpenServiceListener) {
        ServiceManager.openService(context, listener)
    }


    /**
     * 检查服务是否开启。(闲鱼)
     */
    @JvmStatic
    fun isServiceEnabled_XY(context: Context): Boolean {
        return ServiceManager_XY.isServiceEnabled_XY(context)
    }

    /**
     * 检查服务是否开启。(玩物得志)
     */
    @JvmStatic
    fun isServiceEnabled_WWDZ(context: Context): Boolean {
        return ServiceManager_WWDZ.isServiceEnabled_WWDZ(context)
    }
}