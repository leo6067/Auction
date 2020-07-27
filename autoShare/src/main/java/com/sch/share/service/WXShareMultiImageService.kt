package com.sch.share.service

import android.accessibilityservice.AccessibilityService
import android.os.Handler
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.view.accessibility.AccessibilityWindowInfo
import android.widget.EditText
import com.sch.share.ShareInfo
import com.sch.share.constant.Constant_android.stringProhibitRepeatTag
import com.sch.share.utils.FindNodeUtils
import java.util.*

//微信
private const val SNS_UPLOAD_UI = "com.tencent.mm.plugin.sns.ui.SnsUploadUI"
private const val ALBUM_PREVIEW_UI = "com.tencent.mm.plugin.gallery.ui.AlbumPreviewUI"

private const val SELECT_FROM_ALBUM_ZH = "从相册选择"
private const val SELECT_FROM_ALBUM_EN = "Select Photos or Videos from Album"
private const val SELECT_FROM_ALBUM_EN_2 = "Choose from Album"

private const val DONE_ZH = "完成"
private const val DONE_EN = "Done"

private const val POST_ZH = "发表"
private const val POST_EN = "Post"

////闲鱼
//private const val XY_HOEM_MAIN_UI = "com.taobao.fleamarket.home.activity.MainActivity";//闲鱼首页
//private const val XY_PUBLISHENTRY_PUBLISHENTRY_UI = "com.taobao.idlefish.post.activity.publishEntry.PublishEntryActivity";//闲鱼发布按钮页面
//private const val XY_FLUTTERBOOST_IDLEFISHFLUTTER_UI = "com.idlefish.flutterbridge.flutterboost.IdleFishFlutterActivity";//闲鱼发布页面

private var hoemPublishPaager = false//是否在发布选项页面
private var selectPhoto = false//是否在选择照片页面
private var selectPhoto_next = false//是否在选择照片页面 点击下一步
private var editPhoto_next = false//编辑照片页面 点击下一步
private var public_text_pause = false


/**
 * Created by StoneHui on 2018/10/22.
 * <p>
 * 微信多图分享服务。
 */
class WXShareMultiImageService : AccessibilityService() {

    // ListView 或者 RecyclerView
    private val lvOrRcvRegex = "(?:\\.ListView|\\.RecyclerView)$".toRegex()
    // GridView 或者 RecyclerView
    private val gvOrRcvRegex = "(?:\\.GridView|\\.RecyclerView)$".toRegex()
    // EditText
    private val etRegex = EditText::class.java.name.toRegex()
    // View 或者 CheckBox
    private val vOrCbRegex = "(?:\\.View|\\.CheckBox)$".toRegex()

    private var textFlag = 0
    private var prepareOpenAlbumFlag = 0
    private var openAlbumFlag = 0
    private var postFlag = 0

    // 当窗口发生的事件是我们配置监听的事件时,会回调此方法.会被调用多次
    override fun onAccessibilityEvent(event: AccessibilityEvent) {

        if (!ShareInfo.options.isAutoFill || event.className == null) {
            return
        }

        when (event.eventType) {
            AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED -> {
                when (event.className.toString()) {
                    SNS_UPLOAD_UI -> {
                        processingSnsUploadUI(event)
                    }
                    ALBUM_PREVIEW_UI -> {
                        selectImage(event)
                    }

//                    XY_HOEM_MAIN_UI -> {
//                        XYHomeMain(event)
//                    }
//
//                    XY_FLUTTERBOOST_IDLEFISHFLUTTER_UI -> {
//                        XYFlutterboostIdlefishflutter(event)
//                    }

                    else -> {
                    }
                }
            }
            AccessibilityEvent.TYPE_VIEW_FOCUSED,
            AccessibilityEvent.TYPE_VIEW_SCROLLED,
            AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED -> {
                if (event.className.contains(lvOrRcvRegex)) {
                    openAlbum(event)
                }
//                val nodeInfo02 = getRootNodeInfo() ?: return
//                Handler().postDelayed({
//                    if (hoemPublishPaager && nodeInfo02 != null) {
//                        FindNodeUtils.getInstance().findNodeInfoByText_Vague(nodeInfo02, "发布闲置", object : FindNodeUtils.IFindNodeListener {
//                            override fun onFindSuccess() {
//                                hoemPublishPaager = false
//                                selectPhoto = true
//                            }
//                        })
//                    }
//                }, 500)
//
//
//                Handler().postDelayed({
//                    if (selectPhoto && nodeInfo02 != null &&
//                            nodeInfo02.getChild(0) != null &&
//                            nodeInfo02.getChild(0).getChild(0) != null &&
//                            nodeInfo02.getChild(0).getChild(0).getChild(0) != null &&
//                            nodeInfo02.getChild(0).getChild(0).getChild(0).getChild(0) != null &&
//                            nodeInfo02.getChild(0).getChild(0).getChild(0).getChild(0).getChild(0) != null &&
//                            nodeInfo02.getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0) != null &&
//                            nodeInfo02.getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0) != null &&
//                            nodeInfo02.getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(2) != null &&
//                            nodeInfo02.getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(2).getChild(0) != null) {
//                        selectPhoto = false
//
//                        val accessibilityNodeInfo = nodeInfo02.getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(2).getChild(0)
//
//                        val count = accessibilityNodeInfo.childCount
//                        val startIndex = count - 9
//                        for (i in startIndex until count) {
//                            val viewNode = accessibilityNodeInfo.getChild(i).getChild(0)
//                            viewNode?.performAction(AccessibilityNodeInfo.ACTION_CLICK)
//                        }
//                        selectPhoto_next = true
//                    }
//                }, 1500)
//
//                if (selectPhoto_next) {
//                    FindNodeUtils.getInstance().printNodeInfo(nodeInfo02)
//                    FindNodeUtils.getInstance().findNodeInfoByText_Vague(nodeInfo02, "下一步", object : FindNodeUtils.IFindNodeListener {
//                        override fun onFindSuccess() {
//                            selectPhoto_next = false
//                            editPhoto_next = true
//                        }
//                    })
//                }
//
//                if (editPhoto_next) {
//                    Handler().postDelayed({
//                        FindNodeUtils.getInstance().findNodeInfoByText(nodeInfo02, "下一步", object : FindNodeUtils.IFindNodeListener {
//                            override fun onFindSuccess() {
//                                editPhoto_next = false
//                            }
//                        })
//                    }, 1000)
//                }
            }
            else -> {
            }
        }
    }

    // 处理图文分享界面。
    private fun processingSnsUploadUI(event: AccessibilityEvent) {
        val rootNodeInfo = getRootNodeInfo() ?: return

        setTextToUI(rootNodeInfo)

        if (ShareInfo.waitingImageCount > 0) {
            prepareOpenAlbum(rootNodeInfo)
        } else if (ShareInfo.options.isAutoPost) {
            post(rootNodeInfo)
        }
    }

    // 显示待分享文字。
    private fun setTextToUI(rootNodeInfo: AccessibilityNodeInfo) {
        if (textFlag == rootNodeInfo.hashCode()) {
            return
        } else {
            textFlag = rootNodeInfo.hashCode()
        }
        if (!ShareInfo.hasText()) {
            return
        } else {
            ShareInfo.options.text = ""
        }
        rootNodeInfo.getChild(etRegex)?.run {
            // 粘贴剪切板内容
            performAction(AccessibilityNodeInfo.ACTION_FOCUS)
            performAction(AccessibilityNodeInfo.ACTION_PASTE)
        }
    }

    // 准备打开相册
    private fun prepareOpenAlbum(rootNodeInfo: AccessibilityNodeInfo) {
        if (ShareInfo.waitingImageCount <= 0) {
            return
        }
        if (prepareOpenAlbumFlag == rootNodeInfo.hashCode()) {
            return
        } else {
            prepareOpenAlbumFlag = rootNodeInfo.hashCode()
        }
        // 自动点击添加图片的按钮。
        rootNodeInfo.getChild(gvOrRcvRegex)
                ?.getChild(1)
                ?.performAction(AccessibilityNodeInfo.ACTION_CLICK)
    }

    // 打开相册。
    private fun openAlbum(event: AccessibilityEvent) {
        if (ShareInfo.waitingImageCount <= 0) {
            return
        }
        val sourceView = event.source
        if (sourceView == null || openAlbumFlag == sourceView.hashCode()) {
            return
        } else {
            openAlbumFlag = sourceView.hashCode()
        }
        // 查找从相册选择选项并点击。
        for (i in (0 until sourceView.childCount)) {
            val child = sourceView.getChild(i) ?: continue
            if (child.findAccessibilityNodeInfosByText(SELECT_FROM_ALBUM_ZH).isNotEmpty() ||
                    child.findAccessibilityNodeInfosByText(SELECT_FROM_ALBUM_EN_2).isNotEmpty() ||
                    child.findAccessibilityNodeInfosByText(SELECT_FROM_ALBUM_EN).isNotEmpty()
            ) {
                child.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                break
            }
        }
    }

    // 选择图片。
    private fun selectImage(event: AccessibilityEvent) {
        if (ShareInfo.waitingImageCount <= 0) {
            return
        }
        val rootNodeInfo = getRootNodeInfo() ?: return
        val targetView = rootNodeInfo.getChild(gvOrRcvRegex) ?: return

        val maxIndex = ShareInfo.selectedImageCount + ShareInfo.waitingImageCount - 1
        (ShareInfo.selectedImageCount..maxIndex)
                .map { targetView.getChild(it).getChild(vOrCbRegex) }
                .forEach { it?.performAction(AccessibilityNodeInfo.ACTION_CLICK) }

        // 选图结束。
        ShareInfo.setImageCount(0, 0)

        // 点击完成按钮。
        rootNodeInfo.findAccessibilityNodeInfosByText(DONE_ZH)
                .getOrElse(0) { rootNodeInfo.findAccessibilityNodeInfosByText(DONE_EN).getOrNull(0) }
                ?.performAction(AccessibilityNodeInfo.ACTION_CLICK)
    }

    // 发布
    private fun post(rootNodeInfo: AccessibilityNodeInfo) {
        if (postFlag == rootNodeInfo.hashCode()) {
            return
        } else {
            postFlag = rootNodeInfo.hashCode()
        }
        // 准备发布
        ShareInfo.options.isAutoPost = false
        // 点击发表按钮。
        rootNodeInfo.findAccessibilityNodeInfosByText(POST_ZH)
                .getOrElse(0) { rootNodeInfo.findAccessibilityNodeInfosByText(POST_EN).getOrNull(0) }
                ?.performAction(AccessibilityNodeInfo.ACTION_CLICK)
    }

    /*------------------------------------- 闲鱼 ---------------------------------------------------------*/
    //进入首页 点击卖闲置按钮
    private fun XYHomeMain(event: AccessibilityEvent) {
        val nodeInfo = getRootNodeInfo() ?: return
        public_text_pause = true
        if (stringProhibitRepeatTag && nodeInfo != null) {
            Handler().postDelayed({
                FindNodeUtils.getInstance().findNodeInfoByContentDescription_Vague(nodeInfo, "卖闲置",
                        object : FindNodeUtils.IFindNodeListener {
                            override fun onFindSuccess(accessibilityNodeInfo:AccessibilityNodeInfo) {
                                accessibilityNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                                stringProhibitRepeatTag = false
                                hoemPublishPaager = true
                            }
                        })
            }, 1000)
        }
    }

    //闲鱼发布页面
    private fun XYFlutterboostIdlefishflutter(event: AccessibilityEvent) {
        val rootNodeInfo = getRootNodeInfo() ?: return
        // 设置待分享文字。
        val editText = rootNodeInfo.getChild(etRegex)
        // 粘贴剪切板内容
        if (editText != null) {
            if (public_text_pause) {
                editText!!.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                editText!!.performAction(AccessibilityNodeInfo.ACTION_ACCESSIBILITY_FOCUS)
                editText!!.performAction(AccessibilityNodeInfo.ACTION_FOCUS)
                performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK)

                performGlobalAction(AccessibilityService.GLOBAL_ACTION_NOTIFICATIONS)

                Handler().postDelayed({
                    performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK)
                    public_text_pause = false
                }, 500)

                editText!!.performAction(AccessibilityNodeInfo.ACTION_FOCUS)
                editText!!.performAction(AccessibilityNodeInfo.ACTION_PASTE)
            }
        }
    }

    // 查找指定类名的父节点。
    private fun AccessibilityNodeInfo.getParent(parentClassName: String): AccessibilityNodeInfo? {
        return when {
            parent == null -> null
            parent.className == parentClassName -> parent
            else -> parent.getParent(parentClassName)
        }
    }

    // 查找指定类名的节点。
    private fun AccessibilityNodeInfo.getChild(className: Regex): AccessibilityNodeInfo? {
        val queue = LinkedList<AccessibilityNodeInfo>()
        queue.offer(this)
        var info: AccessibilityNodeInfo?
        while (!queue.isEmpty()) {
            info = queue.poll()
            if (info == null) {
                continue
            }
            if (info.className.toString().contains(className)) {
                return info
            }
            for (i in 0 until info.childCount) {
                queue.offer(info.getChild(i))
            }
        }
        return null
    }

    // 获取最后一个子节点。
    private fun AccessibilityNodeInfo.getLastChild(): AccessibilityNodeInfo? {
        return if (childCount <= 0) null else getChild(childCount - 1)

    }

    // 获取 APPLICATION 的 Window 根节点。
    private fun getRootNodeInfo(): AccessibilityNodeInfo? {
        var rootNodeInfo: AccessibilityNodeInfo? = null
        for (window in windows) {
            if (window.type == AccessibilityWindowInfo.TYPE_APPLICATION) {
                rootNodeInfo = window.root
            }
        }
        return rootNodeInfo ?: rootInActiveWindow
    }

    override fun onInterrupt() {
        //当服务要被中断时调用.会被调用多次
    }

}