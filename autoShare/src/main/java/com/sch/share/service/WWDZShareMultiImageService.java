package com.sch.share.service;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.sch.share.ShareInfo;
import com.sch.share.constant.BaseSharePerence;
import com.sch.share.constant.Constant_android;
import com.sch.share.utils.AccessibilityHelper;
import com.sch.share.utils.FindNodeUtils;

import java.util.List;

/**
 * Created by StoneHui on 2018/12/12.
 * <p>
 * 微信多图分享服务。
 */
public class WWDZShareMultiImageService extends AccessibilityService {


    //    android:name="com.zdwh.wwdz.ui.shop.activity.ReleaseGoodsActivity   ////UploadImageActivity
    private static final String WWDZ_HOEM_UI = "com.zdwh.wwdz.ui.MainActivity";//玩物得志首页
    private static final String WWDZ_AUCTIONMANAGER_UI = "com.zdwh.wwdz.ui.auction.activity.AuctionManagerActivity";//拍品管理页面
    private static final String WWDZ_UPLOADGOODS_UI = "com.zdwh.wwdz.ui.auction.activity.UploadGoodsActivity";//上传拍品页面
    private static final String WWDZ_PICTURESELECTOR_UI = "com.luck.picture.lib.dialog.PictureDialog";//相机胶卷Dialog
    private static final String WWDZ_PICTURESELECTOR_ACTIVITY_UI = "com.luck.picture.lib.PictureSelectorActivity";//相机胶卷Activity


    private boolean hoemMineTag = false;//是否已经在首页-我的页面
    private boolean canClickCommodityTag = false;//是否可以点击商品管理
    private boolean canAuctionManagementTag = false;//是否可以点击拍卖管理
    private boolean canFromAlbumTag = false;//是否可以点击从相册选择(图片)
    private boolean isToAlbumTag = false;//是否是进入相册选择图片
    private boolean canFromAlbumCompleteTag = false;//是否可以点击从相册选择-已完成(图片)
    private boolean canUploadgoodsTag = true;//是否可以在进入上传拍品页面
    private boolean canScoolUploadTag = false;//是否可以滑动上传视频页面
    private boolean canUploadVideoTag = false;//是否可以点击15秒视频按钮
    private boolean canFromVideoTag = false;//是否可以点击从相册选择（视频）
    private boolean isToVideoTag = false;//是否是进入相册选择视频
    private boolean canFromAlbumCompleteVideoTag = false;//是否可以点击从相册选择-已完成（视频）
    private boolean isAlbumOnceTag = false;//只允许一个出发进入相册页面
    private boolean canAuctionmanagerTag = false;//是否可以执行拍品管理页面

    // 当窗口发生的事件是我们配置监听的事件时,会回调此方法.会被调用多次
    @Override
    public void onAccessibilityEvent(final AccessibilityEvent event) {
        if (event.getClassName() == null) {
            return;
        }

        switch (event.getEventType()) {
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:     //当窗口发生改变
//                Log.e("onAccessibilityEvent ", "TYPE_WINDOW_STATE_CHANGED LEO" + event.getClassName().toString());
                final AccessibilityNodeInfo nodeInfo = FindNodeUtils.getInstance().getRootNodeInfo_for(WWDZShareMultiImageService.this);
                switch (event.getClassName().toString()) {
                    case WWDZ_HOEM_UI:
                        if (Constant_android.wwdz_stringProhibitRepeatTag && nodeInfo != null) {
                            FindNodeUtils.getInstance().findNodeInfoByContentDescription_Vague(nodeInfo, "卖家中心", new FindNodeUtils.IFindNodeListener() {
                                @Override
                                public void onFindSuccess(AccessibilityNodeInfo accessibilityNodeInfo) {
                                    accessibilityNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                    Constant_android.wwdz_stringProhibitRepeatTag = false;
                                    canClickCommodityTag = true;
                                    canAuctionManagementTag = true;


                                }
                            });


                            if (Constant_android.wwdz_stringProhibitRepeatTag && nodeInfo != null) {
                                FindNodeUtils.getInstance().findNodeInfoByContentDescription_Vague(nodeInfo, "我的", new FindNodeUtils.IFindNodeListener() {
                                    @Override
                                    public void onFindSuccess(AccessibilityNodeInfo accessibilityNodeInfo) {
                                        accessibilityNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                        Constant_android.wwdz_stringProhibitRepeatTag = false;
                                        canClickCommodityTag = true;
                                        canAuctionManagementTag = true;
                                    }
                                });
                            }
                        }
//                        if (Constant_android.wwdz_stringProhibitRepeatTag&&nodeInfo != null) {
//                            AccessibilityNodeInfo nodeInfosByIdA = AccessibilityHelper.findNodeInfosByText(nodeInfo, "卖家中心");
//                            nodeInfosByIdA.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                            Constant_android.wwdz_stringProhibitRepeatTag=false;
//                            canClickCommodityTag = true;
//                        }


                        break;
                    case WWDZ_AUCTIONMANAGER_UI:
//
                        if (canAuctionmanagerTag && nodeInfo != null) {
//                            Log.e("onAccessibilityEvent ", "TYPE_WINDOW_STATE_CHANGED WWDZ_AUCTIONMANAGER_UI" + event.getClassName().toString() + canAuctionmanagerTag);
                            FindNodeUtils.getInstance().findNodeInfoByContentDescription_Vague(nodeInfo, "发布拍品", new FindNodeUtils.IFindNodeListener() {
                                @Override
                                public void onFindSuccess(AccessibilityNodeInfo accessibilityNodeInfo) {
                                    Log.e("onAccessibilityEvent ", "02" + event.getClassName().toString() + canAuctionmanagerTag);
                                    accessibilityNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                    canAuctionmanagerTag = false;
                                    canUploadgoodsTag = true;
                                    canAuctionManagementTag = false;

                                }
                            });
                        }


                        if (canAuctionmanagerTag && nodeInfo != null) {
//                            Log.e("onAccessibilityEvent ", "03" + event.getClassName().toString() + canAuctionmanagerTag);
                            AccessibilityNodeInfo nodeInfosByIdA = AccessibilityHelper.findNodeInfosByText(nodeInfo, "发布拍品");
                            nodeInfosByIdA.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            canAuctionmanagerTag = false;
                            canUploadgoodsTag = true;
                            canAuctionManagementTag = false;
                        }


                        break;
                    case WWDZ_UPLOADGOODS_UI:
                        if (canUploadgoodsTag && nodeInfo != null) {
                            //输入标题
                            FindNodeUtils.getInstance().findNodeInfoByText(nodeInfo, "请输入商品名称，最多可输入35个字", new FindNodeUtils.IFindNodeListener() {
                                @Override
                                public void onFindSuccess(AccessibilityNodeInfo accessibilityNodeInfo) {
                                    if (accessibilityNodeInfo != null) {
                                        Bundle arguments = new Bundle();
                                        arguments.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, ShareInfo.INSTANCE.getOptions().getTitle());
                                        accessibilityNodeInfo.performAction(AccessibilityNodeInfo.ACTION_FOCUS);
                                        accessibilityNodeInfo.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
                                    }
                                }
                            });
                            //输入内容
                            FindNodeUtils.getInstance().findNodeInfoByText(nodeInfo, "请输入商品的规格、材质、卖点及描述等信息", new FindNodeUtils.IFindNodeListener() {
                                @Override
                                public void onFindSuccess(AccessibilityNodeInfo accessibilityNodeInfo) {
                                    if (accessibilityNodeInfo != null) {
                                        Bundle arguments = new Bundle();
                                        arguments.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, ShareInfo.INSTANCE.getOptions().getDescribe());
                                        accessibilityNodeInfo.performAction(AccessibilityNodeInfo.ACTION_FOCUS);
                                        accessibilityNodeInfo.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
                                    }
                                }
                            });

                            //设置图片
                            List<AccessibilityNodeInfo> photoNodeInfos = nodeInfo.findAccessibilityNodeInfosByViewId("com.zdwh.wwdz:id/item_photo_iv_source");
                            if (photoNodeInfos != null && !photoNodeInfos.isEmpty()) {
                                AccessibilityNodeInfo accessibilityNodeInfo = photoNodeInfos.get(0);
                                if (accessibilityNodeInfo != null) {
                                    accessibilityNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                    canUploadgoodsTag = false;
                                    canFromAlbumTag = true;
                                }
                            }
                        }
                        break;
                    case WWDZ_PICTURESELECTOR_UI:  //选择图片
                        Log.e("onAccessibilityEvent ", "04" +isAlbumOnceTag);
                        if (isAlbumOnceTag && nodeInfo != null) {
                            isAlbumOnceTag = false;
                            Log.e("onAccessibilityEvent ", "05"  +isAlbumOnceTag);
                            //选择图片
                            if (isToAlbumTag) {
                                Log.e("onAccessibilityEvent ", "06" +isToAlbumTag);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
//                                        final AccessibilityNodeInfo accessibilityNodeInfo = FindNodeUtils.getInstance().findNodeInfo(nodeInfo, "android.support.v7.widget.RecyclerView");
                                        final AccessibilityNodeInfo accessibilityNodeInfo = FindNodeUtils.getInstance().findNodeInfo(nodeInfo, "androidx.recyclerview.widget.RecyclerView");
//                                        final AccessibilityNodeInfo accessibilityNodeInfo = AccessibilityHelper.findParentNodeInfosByClassName(nodeInfo, "androidx.recyclerview.widget.RecyclerView");
                                        if (accessibilityNodeInfo == null) {
                                            Log.e("onAccessibilityEvent ", "07");
                                            return;
                                        }
                                        for (int i = 0; i < ShareInfo.INSTANCE.getOptions().getPicSize(); i++) {
                                            Log.e("onAccessibilityEvent ", "08");
                                            AccessibilityNodeInfo viewNode = accessibilityNodeInfo.getChild(i);
                                            if (viewNode != null && viewNode.getChild(1) != null) {
                                                viewNode.getChild(1).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                            }
                                        }
                                        isToAlbumTag = false;
                                        canFromAlbumTag = true;
                                        canFromAlbumCompleteTag = true;
                                    }
                                }, 500);
                            }

                            //选择视频
                            if (isToVideoTag) {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        final AccessibilityNodeInfo accessibilityNodeInfo = FindNodeUtils.getInstance().findNodeInfo(nodeInfo, "androidx.recyclerview.widget.RecyclerView");

                                        if (accessibilityNodeInfo == null) {
                                            return;
                                        }
                                        AccessibilityNodeInfo viewNode = accessibilityNodeInfo.getChild(0);
                                        if (viewNode != null && viewNode.getChild(1) != null) {
                                            viewNode.getChild(1).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                        }
                                        canFromAlbumTag = true;
                                        isToVideoTag = false;
                                        canFromAlbumCompleteVideoTag = true;
                                    }
                                }, 500);
                            }
                        }
                        break;
                    case WWDZ_PICTURESELECTOR_ACTIVITY_UI:
                        Log.e("onAccessibilityEvent ", "10 se " +isAlbumOnceTag);
                        if (isAlbumOnceTag && nodeInfo != null) {
                            isAlbumOnceTag = false;
                            Log.e("onAccessibilityEvent ", "11 se " +isAlbumOnceTag);
                            //选择图片
                            if (isToAlbumTag) {
                                Log.e("onAccessibilityEvent ", "12 se " +isToAlbumTag);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        final AccessibilityNodeInfo accessibilityNodeInfo = FindNodeUtils.getInstance().findNodeInfo(nodeInfo, "androidx.recyclerview.widget.RecyclerView");
                                        if (accessibilityNodeInfo == null) {
                                            return;
                                        }
                                        for (int i = 0; i < ShareInfo.INSTANCE.getOptions().getPicSize(); i++) {
                                            AccessibilityNodeInfo viewNode = accessibilityNodeInfo.getChild(i);
                                            if (viewNode != null && viewNode.getChild(1) != null) {
                                                viewNode.getChild(1).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                            }
                                        }
                                        isToAlbumTag = false;
                                        canFromAlbumTag = true;
                                        canFromAlbumCompleteTag = true;
                                    }
                                }, 500);
                            }

                            //选择视频
                            if (isToVideoTag) {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        final AccessibilityNodeInfo accessibilityNodeInfo = FindNodeUtils.getInstance().findNodeInfo(nodeInfo, "androidx.recyclerview.widget.RecyclerView");

                                        if (accessibilityNodeInfo == null) {
                                            return;
                                        }
                                        AccessibilityNodeInfo viewNode = accessibilityNodeInfo.getChild(0);
                                        if (viewNode != null && viewNode.getChild(1) != null) {
                                            viewNode.getChild(1).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                        }

                                        canFromAlbumTag = true;
                                        isToVideoTag = false;
                                        canFromAlbumCompleteVideoTag = true;
                                    }
                                }, 500);
                            }
                        }
                        break;

                    default:
//                        Log.e("onAccessibilityEvent ", "TYPE_WINDOW_STATE_CHANGED default" + event.getClassName().toString());
//                        if (Constant_android.wwdz_stringProhibitRepeatTag&&nodeInfo != null) {
//                            AccessibilityNodeInfo nodeInfosByIdA = AccessibilityHelper.findNodeInfosByText(nodeInfo, "商品管理");
//
//                            nodeInfosByIdA.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                        canClickCommodityTag = false;
//                        canAuctionManagementTag = true;
//                        }
                        break;
                }

                break;
            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
            case AccessibilityEvent.TYPE_VIEW_SCROLLED:
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:


//                Log.e("onAccessibilityEvent", "  TYPE_VIEW_FOCUSED TYPE_WINDOW_CONTENT_CHANGED " + event.getClassName().toString());
                final AccessibilityNodeInfo nodeInfo02 = getRootInActiveWindow();
//                //首页-我的-滑动
//                if (hoemMineTag && nodeInfo02 != null) {
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            AccessibilityNodeInfo scrollViewNodeInfo = FindNodeUtils.getInstance().findNodeInfo(nodeInfo02, "android.widget.ScrollView");
//                            if (scrollViewNodeInfo != null) {
//                                scrollViewNodeInfo.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
//                                hoemMineTag = false;
//                                canClickCommodityTag = true;
//                            }
//                        }
//                    },500);
//                }


                //关闭广告
                if (nodeInfo02 != null) {
                    List<AccessibilityNodeInfo> imageNodeInfos = nodeInfo02.findAccessibilityNodeInfosByViewId("com.zdwh.wwdz:id/iv_advert_close");
                    if (imageNodeInfos != null && !imageNodeInfos.isEmpty()) {
                        AccessibilityNodeInfo imagerNodeInfo = imageNodeInfos.get(0);
                        if (imagerNodeInfo != null) {
                            imagerNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        }
                    }
                }
                //商品管理
                if (canClickCommodityTag && nodeInfo02 != null) {
                    FindNodeUtils.getInstance().findNodeInfoByText(nodeInfo02, "商品管理", new FindNodeUtils.IFindNodeListener() {
                        @Override
                        public void onFindSuccess(AccessibilityNodeInfo accessibilityNodeInfo) {
                            AccessibilityNodeInfo parentNodeInfo = accessibilityNodeInfo.getParent();
                            if (parentNodeInfo != null) {
                                parentNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                canClickCommodityTag = false;
                                canAuctionManagementTag = true;
                            }
                        }
                    });
                }


                //拍卖管理
                if (canAuctionManagementTag && nodeInfo02 != null) {
                    List<AccessibilityNodeInfo> imageNodeInfos = nodeInfo02.findAccessibilityNodeInfosByViewId("com.zdwh.wwdz:id/ll_seller_tool_manager_root");

                    if (imageNodeInfos != null && !imageNodeInfos.isEmpty()) {
                        AccessibilityNodeInfo imagerNodeInfo = imageNodeInfos.get(0);
                        if (imagerNodeInfo != null) {
                            Log.e("onAccessibilityEvent ", "02 拍卖管理" + imageNodeInfos.size());
                            imagerNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            canAuctionmanagerTag = true;
//                            canAuctionManagementTag = false;
                        }
                    }
                }

                //拍卖管理
                if (canAuctionManagementTag && nodeInfo02 != null) {
                    List<AccessibilityNodeInfo> imageNodeInfos = nodeInfo02.findAccessibilityNodeInfosByViewId("com.zdwh.wwdz:id/tv_seller_tool_manager");
                    if (imageNodeInfos != null && !imageNodeInfos.isEmpty()) {
                        AccessibilityNodeInfo imagerNodeInfo = imageNodeInfos.get(0);
                        if (imagerNodeInfo != null) {
                            Log.e("onAccessibilityEvent ", "03 拍卖管理" + imageNodeInfos.size());
                            imagerNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            canAuctionmanagerTag = true;

                        }
                    }
                }


                //拍卖管理
                if (canAuctionManagementTag && nodeInfo02 != null) {

                    FindNodeUtils.getInstance().findNodeInfoByText(nodeInfo02, "拍卖管理", new FindNodeUtils.IFindNodeListener() {
                        @Override
                        public void onFindSuccess(AccessibilityNodeInfo accessibilityNodeInfo) {
                            if (accessibilityNodeInfo != null) {
                                Log.e("onAccessibilityEvent ", "04 拍卖管理" + isAlbumOnceTag);
                                accessibilityNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                canAuctionmanagerTag = true;

                            }
                        }
                    });
                }



                //从相册选择(图片)
                if (canFromAlbumTag && nodeInfo02 != null) {
                    FindNodeUtils.getInstance().findNodeInfoByText(nodeInfo02, "从相册选择", new FindNodeUtils.IFindNodeListener() {
                        @Override
                        public void onFindSuccess(AccessibilityNodeInfo accessibilityNodeInfo) {
                            if (accessibilityNodeInfo != null) {
                                accessibilityNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                Log.e("onAccessibilityEvent ", "09 从相册选择" + isAlbumOnceTag);
                                canFromAlbumTag = false;
                                isToAlbumTag = true;
                                isAlbumOnceTag = true;
                            }
                        }
                    });
                }

                //相册-已完成(图片)
                if (canFromAlbumCompleteTag && nodeInfo02 != null) {
                    FindNodeUtils.getInstance().findNodeInfoByText(nodeInfo02, "已完成", new FindNodeUtils.IFindNodeListener() {
                        @Override
                        public void onFindSuccess(AccessibilityNodeInfo accessibilityNodeInfo) {
                            AccessibilityNodeInfo parentNodeInfo = accessibilityNodeInfo.getParent();
                            if (parentNodeInfo != null) {
                                parentNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                canFromAlbumCompleteTag = false;
                                canScoolUploadTag = true;
                            }
                        }
                    });
                }

                //上传拍品 - 滑动
                if (canScoolUploadTag && nodeInfo02 != null) {
                    AccessibilityNodeInfo scrollViewNodeInfo = FindNodeUtils.getInstance().findNodeInfo(nodeInfo02, "android.widget.ScrollView");
                    if (scrollViewNodeInfo != null) {
                        scrollViewNodeInfo.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
                        canScoolUploadTag = false;
                        canUploadVideoTag = true;
                    }
                }

                //点击15s视频
                if (ShareInfo.INSTANCE.getOptions().getVideoSize() > 0 && canUploadVideoTag && nodeInfo02 != null) {
                    AccessibilityNodeInfo rlNodeInfo = FindNodeUtils.getInstance().findNodeInfo(nodeInfo02, "android.support.v7.widget.RecyclerView");
                    if (rlNodeInfo != null) {
                        AccessibilityNodeInfo videoNodeInfo = rlNodeInfo.getChild(rlNodeInfo.getChildCount() - 1);
                        if (videoNodeInfo != null && videoNodeInfo.getChildCount() > 0 && videoNodeInfo.getChild(0) != null) {
                            videoNodeInfo.getChild(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            canUploadVideoTag = false;
                            canFromVideoTag = true;
                        }
                    }
                }

                //从相册选择(视频)
                if (canFromVideoTag && nodeInfo02 != null) {
                    FindNodeUtils.getInstance().findNodeInfoByText(nodeInfo02, "从相册选择", new FindNodeUtils.IFindNodeListener() {
                        @Override
                        public void onFindSuccess(AccessibilityNodeInfo accessibilityNodeInfo) {
                            if (accessibilityNodeInfo != null) {
                                accessibilityNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                canFromVideoTag = false;
                                isToVideoTag = true;
                                isAlbumOnceTag = true;
                            }
                        }
                    });
                }

                //相册-已完成(视频)
                if (canFromAlbumCompleteVideoTag && nodeInfo02 != null) {
                    FindNodeUtils.getInstance().findNodeInfoByText(nodeInfo02, "已完成", new FindNodeUtils.IFindNodeListener() {
                        @Override
                        public void onFindSuccess(AccessibilityNodeInfo accessibilityNodeInfo) {
                            AccessibilityNodeInfo parentNodeInfo = accessibilityNodeInfo.getParent();
                            if (parentNodeInfo != null) {
                                parentNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                canFromAlbumCompleteVideoTag = false;
                            }
                        }
                    });
                }

                break;
            default:
                break;
        }
    }


    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        //无障碍服务已打开
        BaseSharePerence.getInstance(WWDZShareMultiImageService.this).putBoolean("isConnect", true);
    }

    /**
     * 当服务要被中断时调用.会被调用多次
     */
    @Override
    public void onInterrupt() {
//        SP_HELPER.initEditor().putBoolean(CommonSpConsts.ACCESSIBILITY_SERVICE_STATE, false).commit();//无障碍服务已关闭

        BaseSharePerence.getInstance(WWDZShareMultiImageService.this).putBoolean("isConnect", false);
    }

    @Override
    public boolean onUnbind(Intent intent) {

        BaseSharePerence.getInstance(WWDZShareMultiImageService.this).putBoolean("isConnect", false);
        return super.onUnbind(intent);
    }


    /**
     * 判断无障碍服务是否开启
     *
     * @param mContext
     * @return
     */
    public static boolean isAccessibilitySettingsOn(String packageName, Context mContext) {
        int accessibilityEnabled = 0;
        final String service = packageName + "/" + WWDZShareMultiImageService.class.getCanonicalName();
        try {
            accessibilityEnabled = Settings.Secure.getInt(
                    mContext.getApplicationContext().getContentResolver(),
                    android.provider.Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {

        }
        TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');

        if (accessibilityEnabled == 1) {
            String settingValue = Settings.Secure.getString(
                    mContext.getApplicationContext().getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                mStringColonSplitter.setString(settingValue);
                while (mStringColonSplitter.hasNext()) {
                    String accessibilityService = mStringColonSplitter.next();
                    if (accessibilityService.equalsIgnoreCase(service)) {
                        BaseSharePerence.getInstance(mContext).putBoolean("isConnect", true);
                        return true;
                    }
                }
            }
        } else {
        }
        BaseSharePerence.getInstance(mContext).putBoolean("isConnect", false);
        return false;
    }


}