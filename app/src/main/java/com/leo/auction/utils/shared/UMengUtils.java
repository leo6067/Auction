package com.leo.auction.utils.shared;

import android.app.Activity;
import android.app.Application;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMMin;
import com.umeng.socialize.media.UMWeb;


/**
 * ================================================
 * 项目名称：dgonline-android
 * 包   名：com.aten.dgonline_android.utils
 * 作   者：彭俊鸿
 * 邮   箱：1031028399@qq.com
 * 版   本：1.0
 * 创建日期：2018/12/28
 * 描   述：友盟工具类
 * ================================================
 */
public class UMengUtils {
    /**
     * 内部类实现单例模式
     * 延迟加载，减少内存开销
     */
    private static class SingletonHolder {
        private static UMengUtils instance = new UMengUtils();
    }

    /**
     * 私有的构造函数
     */
    private UMengUtils() {
    }

    public static UMengUtils getInstance() {
        return UMengUtils.SingletonHolder.instance;
    }

    public UMengUtils init(Application app, String appkey) {
        //初始化common库
        //参数1:上下文，不能为空
        //参数2:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
        //参数3:Push推送业务的secret
        UMConfigure.init(app, UMConfigure.DEVICE_TYPE_PHONE, appkey);
        // 选用AUTO页面采集模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
        // 设置组件化的Log开关，参数: boolean 默认为false，如需查看LOG设置为true
        //目前SDK默认设置为在Token有效期内登录不进行二次授权，如果有需要每次登录都弹出授权页面，便于切换账号的开发者可以添加上述代码
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(app).setShareConfig(config);
        UMConfigure.setLogEnabled(true);
        return this;
    }

    public UMengUtils setWeixin(String id, String secret) {
        PlatformConfig.setWeixin(id, secret);
        return this;
    }

    public UMengUtils setQQ(String id, String key) {
        PlatformConfig.setQQZone(id, key);
        return this;
    }

    //调起分享(分享链接)
    public void showShared(Activity activity, UMWeb umWeb, SharedCallBack sharedCallBack) {
        new ShareAction(activity)
                .withMedia(umWeb)
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
                .setCallback(sharedCallBack)
                .open();
    }

    //调起分享(分享图片)
    public void showShared01(Activity activity, UMImage umImage, SHARE_MEDIA share_media, SharedCallBack sharedCallBack) {
        new ShareAction(activity)
                .withMedia(umImage)
                .setPlatform(share_media)
                .setCallback(sharedCallBack).share();
    }

    //调起分享(自定义分享平台)
    public void showShared02(Activity activity, UMMin umMin, SHARE_MEDIA share_media, SharedCallBack sharedCallBack) {
        new ShareAction(activity)
                .withMedia(umMin)
                .setPlatform(share_media)
                .setCallback(sharedCallBack).share();
    }

    interface sharedCallBack {
        void onSharedStart();
    }
}
