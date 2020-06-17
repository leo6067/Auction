package com.leo.auction.utils.shared;

import android.content.Context;
import android.graphics.Bitmap;

import com.umeng.socialize.media.UMEmoji;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMMin;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.media.UMusic;

/**
 * ================================================
 * 项目名称：dgonline-android
 * 包   名：com.aten.dgonline_android.utils.shared
 * 作   者：彭俊鸿
 * 邮   箱：1031028399@qq.com
 * 版   本：1.0
 * 创建日期：2019/9/3
 * 描   述：
 * ================================================
 */
public class SharedMessageUtils {
    //    UMImage image = new UMImage(ShareActivity.this, "imageurl");//网络图片
//    UMImage image = new UMImage(ShareActivity.this, file);//本地文件
//    UMImage image = new UMImage(ShareActivity.this, R.drawable.xxx);//资源文件
//    UMImage image = new UMImage(ShareActivity.this, bitmap);//bitmap文件
//    UMImage image = new UMImage(ShareActivity.this, byte[]);//字节流
    //获取图片分享
    public UMImage getImagerMsg(Context context, String imageurl, int thumbRes) {
        UMImage image = new UMImage(context, imageurl);//网络图片
        UMImage thumb = new UMImage(context, thumbRes);
//        image.compressStyle = UMImage.CompressStyle.SCALE;//大小压缩，默认为大小压缩，适合普通很大的图
        image.compressStyle = UMImage.CompressStyle.QUALITY;//质量压缩，适合长图的分享
        //压缩格式设置
        image.compressFormat = Bitmap.CompressFormat.PNG;//用户分享透明背景的图片可以设置这种方式，但是qq好友，微信朋友圈，不支持透明背景图片，会变成黑色
        image.setThumb(thumb);
        return image;
    }

    public UMImage getImagerMsg02(Context context, byte[] bytes) {
        UMImage image = new UMImage(context, bytes);//网络图片
        image.compressStyle = UMImage.CompressStyle.QUALITY;//质量压缩，适合长图的分享
        //压缩格式设置
        image.compressFormat = Bitmap.CompressFormat.PNG;//用户分享透明背景的图片可以设置这种方式，但是qq好友，微信朋友圈，不支持透明背景图片，会变成黑色
        return image;
    }

    //获取web链接分享
    public UMWeb getWebLinkMsg(Context context, String title, String imageurl, String describe, int thumbRes) {
        UMImage thumb = new UMImage(context, thumbRes);
        thumb.compressStyle = UMImage.CompressStyle.SCALE;//大小压缩，默认为大小压缩，适合普通很大的图
        //压缩格式设置
        thumb.compressFormat = Bitmap.CompressFormat.PNG;//用户分享透明背景的图片可以设置这种方式，但是qq好友，微信朋友圈，不支持透明背景图片，会变成黑色
        UMWeb web = new UMWeb(imageurl);
        web.setTitle(title);//标题
        web.setThumb(thumb);  //缩略图
        web.setDescription(describe);//描述
        return web;
    }

    //获取视频分享
    public UMVideo getVideoMsg(Context context, String title, String videourl, String describe, String thumbRes) {
        UMVideo video = new UMVideo(videourl);
        UMImage thumb = new UMImage(context, thumbRes);
        video.setTitle(title);//视频的标题
        video.setThumb(thumb);//视频的缩略图
        video.setDescription(describe);//视频的描述
        return video;
    }

    //获取音频分享
    public UMusic getMusicMsg(Context context, String title, String musicurl, String describe, String thumbRes, String targetUrl) {
        UMusic music = new UMusic(musicurl);//音乐的播放链接
        UMImage thumb = new UMImage(context, thumbRes);
        music.setTitle(title);//音乐的标题
        music.setThumb(thumb);//音乐的缩略图
        music.setDescription(describe);//音乐的描述
        music.setmTargetUrl(targetUrl);//音乐的跳转链接
        return music;
    }

    //获取GIF分享
    public UMEmoji getEmojiMsg(Context context, String Emojiurl, int thumbRes) {
        UMEmoji emoji = new UMEmoji(context, Emojiurl);
        emoji.setThumb(new UMImage(context, thumbRes));
        return emoji;
    }

    //获取微信小程序（网页链接图片）
    public UMMin getWXSmallProgram(Context context, String webpageUrl, String thumbPath, String title,
                                   String content, String path, String userName) {
        //兼容低版本的网页链接
        UMMin umMin = new UMMin(webpageUrl);
        if (thumbPath.contains("?")) {
            thumbPath = String.valueOf(thumbPath) + "&x-oss-process=image/resize,s_320";
        } else {
            thumbPath = String.valueOf(thumbPath) + "?x-oss-process=image/resize,s_320";
        }
        // 小程序消息封面图片
        UMImage thumb = new UMImage(context, thumbPath);
        thumb.compressStyle = UMImage.CompressStyle.QUALITY;//质量压缩，适合长图的分享
        //压缩格式设置
        thumb.compressFormat = Bitmap.CompressFormat.PNG;//用户分享透明背景的图片可以设置这种方式，但是qq好友，微信朋友圈，不支持透明背景图片，会变成黑色
        umMin.setThumb(thumb);
        // 小程序消息title
        umMin.setTitle(title);
        // 小程序消息描述
        umMin.setDescription(content);
        //小程序页面路径
        umMin.setPath(path);
        // 小程序原始id,在微信平台查询
        umMin.setUserName(userName);
        //体验版
//        Config.setMiniPreView();

        return umMin;
    }

    //获取微信小程序(bute[]图片)
    public UMMin getWXSmallProgram02(Context context, String webpageUrl, byte[] thumbPath, String title,
                                     String content, String path, String userName) {
        //兼容低版本的网页链接
        UMMin umMin = new UMMin(webpageUrl);
        // 小程序消息封面图片
        UMImage thumb = new UMImage(context, thumbPath);
        thumb.compressStyle = UMImage.CompressStyle.QUALITY;//质量压缩，适合长图的分享
        //压缩格式设置
        thumb.compressFormat = Bitmap.CompressFormat.PNG;//用户分享透明背景的图片可以设置这种方式，但是qq好友，微信朋友圈，不支持透明背景图片，会变成黑色
        umMin.setThumb(thumb);
        // 小程序消息title
        umMin.setTitle(title);
        // 小程序消息描述
        umMin.setDescription(content);
        //小程序页面路径
        umMin.setPath(path);
        // 小程序原始id,在微信平台查询
        umMin.setUserName(userName);

        return umMin;
    }
}
