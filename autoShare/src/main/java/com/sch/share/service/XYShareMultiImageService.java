package com.sch.share.service;

import android.accessibilityservice.AccessibilityService;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import android.widget.EditText;

import com.sch.share.ShareInfo;
import com.sch.share.constant.Constant_android;
import com.sch.share.utils.ClipboardUtil;
import com.sch.share.utils.FindNodeUtils;

import java.util.LinkedList;

/**
 * Created by StoneHui on 2018/12/12.
 * <p>
 * 闲鱼多图分享服务。
 */
public class XYShareMultiImageService extends AccessibilityService {

    private static final String XY_HOEM_PUBLIC_BUTTON_UI = "com.taobao.fleamarket.home.activity.MainActivity";//闲鱼首页
    private static final String XY_HOEM_PUBLISH_PAAGER_UI = "com.taobao.idlefish.post.activity.publishEntry.PublishEntryActivity";//闲鱼发布按钮页面
    private static final String XY_HOEM_PUBLISH_PHOTO_UI = "com.idlefish.flutterbridge.flutterboost.IdleFishFlutterActivity";//闲鱼发布页面
    private boolean hoemPublishPaager = false;//是否在发布选项页面
    private boolean selectPhoto = false;//是否在选择照片页面
    private boolean selectPhoto_next = false;//是否在选择照片页面 点击下一步
    private boolean editPhoto_next = false;//编辑照片页面 点击下一步
    private boolean public_tag = false;//发布页面

    private boolean public_text_pause = false;

    // 当窗口发生的事件是我们配置监听的事件时,会回调此方法.会被调用多次
    @Override
    public void onAccessibilityEvent(final AccessibilityEvent event) {
        if (event.getClassName() == null) {
            return;
        }

        switch (event.getEventType()) {
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                Log.e("onAccessibilityEvent xy", "xy =" + event.getClassName().toString());
                final AccessibilityNodeInfo nodeInfo = this.getRootInActiveWindow();
                switch (event.getClassName().toString()) {
                    case XY_HOEM_PUBLIC_BUTTON_UI:
                        public_text_pause = true;
                        if (Constant_android.stringProhibitRepeatTag && nodeInfo != null) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    FindNodeUtils.getInstance().findNodeInfoByContentDescription_Vague(nodeInfo, "卖闲置",
                                            new FindNodeUtils.IFindNodeListener() {
                                                @Override
                                                public void onFindSuccess(AccessibilityNodeInfo accessibilityNodeInfo) {
                                                    accessibilityNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                                    Constant_android.stringProhibitRepeatTag = false;
                                                    hoemPublishPaager = true;
                                                }
                                            });
                                }
                            }, 1500);
                        }
                        break;
                    case XY_HOEM_PUBLISH_PAAGER_UI:
                        break;
                    case XY_HOEM_PUBLISH_PHOTO_UI://发布页面
                        break;
                    default:
                        break;
                }
                break;
            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
            case AccessibilityEvent.TYPE_VIEW_SCROLLED:
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                final AccessibilityNodeInfo nodeInfo02 = getRootInActiveWindow();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (hoemPublishPaager && nodeInfo02 != null) {
                            FindNodeUtils.getInstance().findNodeInfoByText_Vague(nodeInfo02, "发布闲置", new FindNodeUtils.IFindNodeListener() {
                                @Override
                                public void onFindSuccess(AccessibilityNodeInfo accessibilityNodeInfo) {
                                    accessibilityNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                    hoemPublishPaager = false;
                                    selectPhoto = true;
                                }
                            });
                        }
                    }
                }, 500);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (selectPhoto && nodeInfo02 != null &&
                                nodeInfo02.getChildCount()>0&&nodeInfo02.getChild(0) != null &&
                                nodeInfo02.getChild(0).getChildCount()>0&&nodeInfo02.getChild(0).getChild(0) != null &&
                                nodeInfo02.getChild(0).getChild(0).getChildCount()>0&&nodeInfo02.getChild(0).getChild(0).getChild(0) != null &&
                                nodeInfo02.getChild(0).getChild(0).getChild(0).getChildCount()>0&&nodeInfo02.getChild(0).getChild(0).getChild(0).getChild(0) != null &&
                                nodeInfo02.getChild(0).getChild(0).getChild(0).getChild(0).getChildCount()>0&&nodeInfo02.getChild(0).getChild(0).getChild(0).getChild(0).getChild(0) != null &&
                                nodeInfo02.getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChildCount()>0&&nodeInfo02.getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0) != null &&
                                nodeInfo02.getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChildCount()>0&&nodeInfo02.getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0) != null &&
                                nodeInfo02.getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChildCount()>2&&nodeInfo02.getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(2) != null &&
                                nodeInfo02.getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(2).getChildCount()>0&&nodeInfo02.getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(2).getChild(0) != null) {
                            selectPhoto = false;

                            AccessibilityNodeInfo accessibilityNodeInfo = nodeInfo02.getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(2).getChild(0);

                            final int count = accessibilityNodeInfo.getChildCount();
                            final int startIndex = count - ShareInfo.INSTANCE.getOptions().getPicSize();
                            for (int i = startIndex; i < count; i++) {
                                if (i<0){
                                    return;
                                }
                                AccessibilityNodeInfo viewNode = accessibilityNodeInfo.getChild(i).getChild(0);
                                if (viewNode != null) {
                                    viewNode.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                }
                            }
                            selectPhoto_next = true;
                        }
                    }
                }, 1500);

                if (selectPhoto_next) {
                    FindNodeUtils.getInstance().findNodeInfoByText_Vague(nodeInfo02, "下一步", new FindNodeUtils.IFindNodeListener() {
                        @Override
                        public void onFindSuccess(AccessibilityNodeInfo accessibilityNodeInfo) {
                            accessibilityNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            selectPhoto_next = false;
                            editPhoto_next = true;
                        }
                    });
                }

                if (editPhoto_next) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            FindNodeUtils.getInstance().findNodeInfoByText(nodeInfo02, "下一步", new FindNodeUtils.IFindNodeListener() {
                                @Override
                                public void onFindSuccess(AccessibilityNodeInfo accessibilityNodeInfo) {
                                    accessibilityNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                    editPhoto_next = false;
                                    public_tag=true;
                                }
                            });
                        }
                    },1000);
                }

                if (public_tag&&findNodeInfo(nodeInfo02, EditText.class.getName())!=null){
                    public_tag=false;
                    setTextToUI2(nodeInfo02);
                }
                break;
            default:
                break;
        }
    }

    // 显示待分享文字。
    private void setTextToUI2(final AccessibilityNodeInfo rootNodeInfo) {
        // 设置待分享文字。
        final AccessibilityNodeInfo editText = findNodeInfo(rootNodeInfo, EditText.class.getName());
        // 粘贴剪切板内容
        if (editText != null) {
            if (public_text_pause) {
                editText.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                editText.performAction(AccessibilityNodeInfo.ACTION_ACCESSIBILITY_FOCUS);
                editText.performAction(AccessibilityNodeInfo.ACTION_FOCUS);
                performGlobalAction(GLOBAL_ACTION_BACK);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        editText.performAction(AccessibilityNodeInfo.ACTION_FOCUS);
                        editText.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                        public_text_pause = false;
                    }
                }, 500);
            }
        }
    }

    // 查找指定类名的父节点。
    private AccessibilityNodeInfo findParent(AccessibilityNodeInfo childNodeInfo, String parentClassName) {
        AccessibilityNodeInfo parentNodeInfo = childNodeInfo.getParent();
        if (parentNodeInfo == null) {
            return null;
        }
        if (parentNodeInfo.getClassName().equals(parentClassName)) {
            return parentNodeInfo;
        }
        return findParent(parentNodeInfo, parentClassName);
    }

    // 查找指定类名的节点。
    @Nullable
    private AccessibilityNodeInfo findNodeInfo(AccessibilityNodeInfo rootNodeInfo, String className) {
        if (rootNodeInfo == null) {
            return null;
        }
        LinkedList<AccessibilityNodeInfo> queue = new LinkedList<>();
        queue.offer(rootNodeInfo);
        AccessibilityNodeInfo info;
        while (!queue.isEmpty()) {
            info = queue.poll();
            if (info == null) {
                continue;
            }
            if (info.getClassName().toString().contains(className)) {
                return info;
            }
            for (int i = 0; i < info.getChildCount(); i++) {
                queue.offer(info.getChild(i));
            }
        }
        return null;
    }

    // 获取 APPLICATION 的 Window 根节点。
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private AccessibilityNodeInfo getRootNodeInfo() {
        AccessibilityNodeInfo rootNodeInfo = null;
        for (AccessibilityWindowInfo windowInfo : getWindows()) {
            if (windowInfo.getType() == AccessibilityWindowInfo.TYPE_APPLICATION) {
                rootNodeInfo = windowInfo.getRoot();
            }
        }
        return rootNodeInfo != null ? rootNodeInfo : getRootInActiveWindow();
    }

    @Override
    public void onInterrupt() {
        //当服务要被中断时调用.会被调用多次
    }
}