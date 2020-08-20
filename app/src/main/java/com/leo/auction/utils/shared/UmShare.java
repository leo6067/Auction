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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * 友盟分享
 * Created by qwe on 2017/5/25.
 */

public class UmShare {
    /**
     * 网络图片
     *
     * @param activity
     * @param url
     * @param platform
     * @param umShareListener 回调
     */
    public static void shareImage(Activity activity, String url, SHARE_MEDIA platform, UMShareListener umShareListener) {
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
     *
     * @param activity
     * @param mBitmap
     * @param platform
     * @param umShareListener 回调
     */
    public static void shareImage(Activity activity, Bitmap mBitmap, SHARE_MEDIA platform, UMShareListener umShareListener) {

        UMImage image1 = new UMImage(activity, mBitmap);


        Bitmap bitmap = mBitmap;
        bitmap = compressImage(bitmap);
        UMImage thumb = new UMImage(activity, bitmap);
        image1.setThumb(thumb);
        new ShareAction(activity)
                .setPlatform(platform)
//              .withText("hello,微信盆友圈")
                .withMedia(image1)

                .setCallback(umShareListener)
                .share();
    }


    /**
     * 质量压缩方法
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 90;
        while (baos.toByteArray().length / 1024 > 32) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }


    /**
     * 图片按比例大小压缩方法
     *
     * @param image （根据Bitmap图片压缩）
     * @return
     */
    public static Bitmap compressScale(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        // 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
        if (baos.toByteArray().length / 1024 > 32) {
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 80, baos);// 这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;

        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        // float hh = 800f;// 这里设置高度为800f
        // float ww = 480f;// 这里设置宽度为480f
        float hh = 800f;
        float ww = 480f;
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) { // 如果高度高的话根据高度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be; // 设置缩放比例
        // newOpts.inPreferredConfig = Config.RGB_565;//降低图片从ARGB888到RGB565
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
        //return bitmap;
    }


    /**
     * 分享链接
     *
     * @param activity
     * @param url
     * @param title
     * @param content
     * @param platform
     * @param umShareListener 回调
     */
    public static void shareLink(Activity activity, String url, String title, String picUrl, String content, SHARE_MEDIA platform, UMShareListener umShareListener) {
        Bitmap bmp = BitmapFactory.decodeResource(activity.getResources(), R.drawable.logo);
        UMImage image = new UMImage(activity, picUrl);
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
     *
     * @param activity
     * @param content
     * @param platform
     * @param umShareListener 回调
     */
    public static void shareText(Activity activity, String content, SHARE_MEDIA platform, UMShareListener umShareListener) {
        new ShareAction(activity)
                .setPlatform(platform)
                .withText(content)
                .setCallback(umShareListener)
                .share();
    }


}
