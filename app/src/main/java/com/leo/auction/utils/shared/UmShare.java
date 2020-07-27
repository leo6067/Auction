package com.leo.auction.utils.shared;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.leo.auction.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

/**
 * 友盟分享
 * Created by qwe on 2017/5/25.
 */

public class UmShare {
    /**
     * 网络图片
     * @param activity
     * @param url
     * @param platform
     * @param umShareListener 回调
     */
    public static void shareImage(Activity activity, String url,SHARE_MEDIA platform,UMShareListener umShareListener){
        UMImage image1 = new UMImage(activity, url);//网络图片
        new ShareAction(activity)
                .setPlatform(platform)
//              .withText("hello,微信盆友圈")
                .withMedia(image1)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 本地图片
     * @param activity
     * @param mBitmap
     * @param platform
     * @param umShareListener 回调
     */
    public static void shareImage(Activity activity, Bitmap mBitmap, SHARE_MEDIA platform, UMShareListener umShareListener){
        UMImage image1 = new UMImage(activity, mBitmap);
        new ShareAction(activity)
                .setPlatform(platform)
//              .withText("hello,微信盆友圈")
                .withMedia(image1)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 分享链接
     * @param activity
     * @param url
     * @param title
     * @param content
     * @param platform
     * @param umShareListener 回调
     */
    public static void shareLink(Activity activity,String url,String title,String picUrl,String content,SHARE_MEDIA platform,UMShareListener umShareListener){
        Bitmap bmp = BitmapFactory.decodeResource(activity.getResources(), R.drawable.logo);
        UMImage image = new UMImage(activity,picUrl);
        UMWeb web = new UMWeb(url);
        web.setTitle(title);//标题
        web.setThumb(image);  //缩略图
        web.setDescription(content);//描述
        new ShareAction(activity)
                .setPlatform(platform)
                .withMedia(web)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 分享文本
     * @param activity
     * @param content
     * @param platform
     * @param umShareListener 回调
     */
    public static void shareText(Activity activity,String content,SHARE_MEDIA platform,UMShareListener umShareListener){
        new ShareAction(activity)
                .setPlatform(platform)
                .withText(content)
                .setCallback(umShareListener)
                .share();
    }




}
