package com.sch.share.utils;

import android.accessibilityservice.AccessibilityService;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;

import java.util.LinkedList;

/**
 * ================================================
 * 项目名称：My Application
 * 包    名：com.example.myapplication
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2020/2/15
 * 描    述：查找结点的工具类
 * ================================================
 */
public class FindNodeUtils {

    /**
     * 内部类实现单例模式
     * 延迟加载，减少内存开销
     */
    private static class SingletonHolder {
        private static FindNodeUtils instance = new FindNodeUtils();
    }

    /**
     * 私有的构造函数
     */
    private FindNodeUtils() {
    }

    public static FindNodeUtils getInstance() {
        return FindNodeUtils.SingletonHolder.instance;
    }

    private int printTag = 0;

    //打印节点信息
    public void printNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo == null) {
            return;
        }
        printTag++;
        for (int i = 0; i < accessibilityNodeInfo.getChildCount(); i++) {
            if (accessibilityNodeInfo.getChild(i) != null) {
                Log.e("printNodeInfo", String.valueOf(printTag) + " -- " + i + "printNodeInfo==  " + accessibilityNodeInfo.getChild(i).getClassName() +
                        "   " + accessibilityNodeInfo.getChild(i).getText()+
                        "   " + accessibilityNodeInfo.getChild(i).isFocusable()+
                        "   " + accessibilityNodeInfo.getChild(i).isFocused());
                printNodeInfo(accessibilityNodeInfo.getChild(i));
            }
        }
    }

    //根据文字查找指定节点信息的数据(精确查找)
    public void findNodeInfoByText(AccessibilityNodeInfo accessibilityNodeInfo, String text, IFindNodeListener iFindNodeListener) {
        if (accessibilityNodeInfo == null || accessibilityNodeInfo.getChildCount() == 0) {
            return;
        }
        for (int i = 0; i < accessibilityNodeInfo.getChildCount(); i++) {
            if (accessibilityNodeInfo.getChild(i) != null && text.equals(accessibilityNodeInfo.getChild(i).getText())) {
                iFindNodeListener.onFindSuccess(accessibilityNodeInfo.getChild(i));
                break;
            } else {
                findNodeInfoByText(accessibilityNodeInfo.getChild(i), text, iFindNodeListener);
            }
        }
    }

    //根据文字查找指定节点信息的数据（模糊查找）
    public void findNodeInfoByText_Vague(AccessibilityNodeInfo accessibilityNodeInfo, String text, IFindNodeListener iFindNodeListener) {
        if (accessibilityNodeInfo == null) {
            return;
        }
        for (int i = 0; i < accessibilityNodeInfo.getChildCount(); i++) {
            if (accessibilityNodeInfo.getChild(i) != null) {
                if (accessibilityNodeInfo.getChild(i).getText() != null && accessibilityNodeInfo.getChild(i).getText().toString().contains(text)) {
                    iFindNodeListener.onFindSuccess(accessibilityNodeInfo.getChild(i));
                    break;
                } else {
                    findNodeInfoByText_Vague(accessibilityNodeInfo.getChild(i), text, iFindNodeListener);
                }
            }
        }
    }

    //根据描述查找指定节点信息的数据（模糊查找）
    public void findNodeInfoByContentDescription_Vague(AccessibilityNodeInfo nodeInfo, String text, IFindNodeListener iFindNodeListener) {
        if (nodeInfo == null) {
            return;
        }

        for (int i = 0; i < nodeInfo.getChildCount(); i++) {
            if (nodeInfo.getChild(i) != null) {
                if (nodeInfo.getChild(i).getContentDescription() != null && nodeInfo.getChild(i).getContentDescription().toString().contains(text)) {
                    iFindNodeListener.onFindSuccess(nodeInfo.getChild(i));
                    break;
                } else {
                    findNodeInfoByContentDescription_Vague(nodeInfo.getChild(i),text,iFindNodeListener);
                }
            }
        }
    }

    // 查找指定类名的节点。
    @Nullable
    public AccessibilityNodeInfo findNodeInfo(AccessibilityNodeInfo rootNodeInfo, String className) {
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

    public AccessibilityNodeInfo getRootNodeInfo_for(AccessibilityService service){
        AccessibilityNodeInfo accessibilityNodeInfo=getRootNodeInfo(service);
        if (accessibilityNodeInfo==null){
            return getRootNodeInfo_for(service);
        }else {
            return accessibilityNodeInfo;
        }
    }

    // 获取 APPLICATION 的 Window 根节点。
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private AccessibilityNodeInfo getRootNodeInfo(AccessibilityService service) {
        AccessibilityNodeInfo rootNodeInfo = null;
        for (AccessibilityWindowInfo windowInfo : service.getWindows()) {
            if (windowInfo.getType() == AccessibilityWindowInfo.TYPE_APPLICATION) {
                rootNodeInfo = windowInfo.getRoot();
            }
        }
        return rootNodeInfo != null ? rootNodeInfo : service.getRootInActiveWindow();
    }

    public interface IFindNodeListener {
        void onFindSuccess(AccessibilityNodeInfo accessibilityNodeInfo);
    }
}
