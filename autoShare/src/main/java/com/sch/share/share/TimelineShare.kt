package com.sch.share.share

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.aten.compiler.constant.UrlConstant
import com.aten.compiler.utils.CustomerDialogUtils
import com.aten.compiler.utils.RxTool

import com.aten.compiler.widget.dialog.listener.OnBtnClickL
import com.sch.share.*
import com.sch.share.ShareInfo
import com.sch.share.constant.WX_LAUNCHER_UI
import com.sch.share.constant.WX_PACKAGE_NAME
import com.sch.share.constant.WX_SHARE_TO_TIMELINE_UI
import com.sch.share.manager.FileManager
import com.sch.share.service.ServiceManager
import com.sch.share.utils.ClipboardUtil
import java.io.File
import java.util.*
import kotlin.concurrent.thread

/**
 * Created by StoneHui on 2019-11-28.
 * <p>
 * 分享到朋友圈
 */
object TimelineShare : BaseShare() {

    /**
     * 分享图片 [images] 到朋友圈。
     * 使用 [options] 设置是否自动填充文案、是否自动发布、回调函数等配置。
     */
    fun share(activity: Activity, images: Array<Bitmap>, options: Options) {
        activity.runOnUiThread {
            if (!checkShareEnable(activity)) return@runOnUiThread
            var treeMap = TreeMap<Int, File>()
            images.reversed().forEachIndexed { index, bitmap -> treeMap.put(index, FileManager.saveBitmap(activity, bitmap)) }
            var fileList: MutableList<File> = ArrayList()
            treeMap.forEach { fileList.add(it.value) }
            treeMap.clear()

            startShare(activity, fileList, options)
        }
    }

    /**
     * 分享图片 [images] 到朋友圈。
     * 使用 [options] 设置是否自动填充文案、是否自动发布、回调函数等配置。
     */
    fun share(activity: Activity, images: Array<File>, options: Options) {
        activity.runOnUiThread {
            if (!checkShareEnable(activity)) return@runOnUiThread
            FileManager.clearTmpFile(activity)
            val dir = FileManager.getTmpFileDir(activity)
            startShare(
                    activity,
                    images.map { it.copyTo(File(dir, it.name), true) },
                    options
            )
        }
    }

    /**
     * 分享到微信朋友圈。
     *
     * @param activity [Context]
     * @param fileList 图片列表。
     * @param options [Options] 可选项。
     */
    private fun startShare(activity: Activity, fileList: List<File>, options: Options) {
        if (!options.isAutoFill || WXShareMultiImageHelper.isServiceEnabled(activity)) {
            internalShare(activity, fileList, options)
            return
        }

        CustomerDialogUtils.getInstance().showUpdateDialog(activity, true, activity.getString(R.string.wx_share_dialog_title),
                activity.getString(R.string.wx_share_dialog_message),
                2, "取消,开启", false, OnBtnClickL {
            CustomerDialogUtils.getInstance().dissUpdateDialog()
            options.isAutoFill = false
            internalShare(activity, fileList, options)
        }, OnBtnClickL {
            CustomerDialogUtils.getInstance().dissUpdateDialog()
            ServiceManager.openService(activity) {
                options.isAutoFill = it
                internalShare(activity, fileList, options)
            }
        })
    }

    private fun internalShare(activity: Activity, fileList: List<File>, options: Options) {
        var dialog: ProgressDialog? = null
        if (options.needShowLoading) {
            if (dialog == null){
                dialog = ProgressDialog(activity).apply {
                    setCancelable(false)
                    setMessage("请稍候...")
                    show()
                }
            }else{
                dialog.show()
            }
        }

        thread(true) {
            // 获取图片路径
            val paths = fileList.map { it.absolutePath }.toTypedArray()
            val mimeTypes = Array(paths.size) { "image/*" }
            // 扫描图片
            val uriList = mutableListOf<Uri>()
            MediaScannerConnection.scanFile(activity, paths, mimeTypes) { _, uri ->
                uriList.add(uri)
                if (uriList.size < paths.size) return@scanFile
                // 扫描结束执行分享。
                activity.runOnUiThread {
                    dialog?.cancel()
                    options.onPrepareOpenWXListener?.invoke()
                    if (options.isAutoFill) {
                        shareToTimelineUIAuto(activity, options, uriList)
                    } else {
                        shareToTimelineUIManual(activity, options)
                    }
                }
            }
        }
    }

    // 分享到微信朋友圈（自动模式）。
    private fun shareToTimelineUIAuto(context: Context, options: Options, uriList: List<Uri>) {
        if (!TextUtils.isEmpty(options.text)) {
            ClipboardUtil.setPrimaryClip(context, "", options.text)
        }
        ShareInfo.options = options
        ShareInfo.setImageCount(1, uriList.size - 1)
        // 打开分享到朋友圈界面
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.component = ComponentName(WX_PACKAGE_NAME, WX_SHARE_TO_TIMELINE_UI)
        intent.type = "image/*"
//        intent.putExtra("Kdescription", options.text)
        /*
         * 新版微信会把 Kdescription 参数显示到分享输入框中。
         * 为兼容新旧版本，这里不传文本了，统一使用剪切板粘贴。
         * intent.putExtra("Kdescription", options.text)
         */
        intent.putExtra(Intent.EXTRA_STREAM, uriList[uriList.size-1])
        (context as Activity).startActivity(intent)
    }

    // 分享到微信朋友圈（手动模式）。
    private fun shareToTimelineUIManual(context: Context, options: Options) {
        if (!TextUtils.isEmpty(options.text)) {
            ClipboardUtil.setPrimaryClip(context, "", options.text)
            Toast.makeText(context, "请手动选择图片，长按粘贴内容！", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "请手动选择图片！", Toast.LENGTH_LONG).show()
        }
        // 打开微信
        val intent = Intent(Intent.ACTION_MAIN)
        intent.action = Intent.ACTION_MAIN
        intent.component = ComponentName(WX_PACKAGE_NAME, WX_LAUNCHER_UI)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

}