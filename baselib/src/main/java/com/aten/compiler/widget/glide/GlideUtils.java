package com.aten.compiler.widget.glide;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ImageView;

import com.aten.compiler.R;
import com.aten.compiler.utils.ScreenUtils;
import com.aten.compiler.widget.transformation.RoundedCornersTransformation;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.AppWidgetTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.signature.ObjectKey;

import java.math.BigDecimal;
import java.nio.channels.NonWritableChannelException;
import java.util.UUID;

/**
 * project:PJHAndroidFrame
 * package:com.frame.pjh_core.widget.glide
 * Created by 彭俊鸿 on 2018/6/4.
 * e-mail : 1031028399@qq.com
 */

public class GlideUtils {

    //加载网络图片
    public static void loadImg(Object path, ImageView mImageView) {
        loadImg(path, mImageView, R.color.color_f0f0f0, R.color.color_f0f0f0);
    }

    //加载网络图片
    public static void loadImgDefault(Object path, ImageView mImageView) {
        GlideApp.with(mImageView.getContext()).load(path).centerCrop().signature(new ObjectKey(UUID.randomUUID())).
                placeholder(R.color.color_f0f0f0).error(R.color.color_f0f0f0) .into(mImageView);
    }

    public static void loadImg(Object path, ImageView mImageView, int lodingImage, int errorImageView) {
        loadImg(path, mImageView, lodingImage, errorImageView, DiskCacheStrategy.AUTOMATIC, false,null);
    }

    public static void loadImg(Object path, ImageView mImageView, int lodingImage,Transformation requestOptions) {
        loadImg(path, mImageView, lodingImage, lodingImage, DiskCacheStrategy.AUTOMATIC, false,requestOptions);
    }

    public static void loadImg(Object path, ImageView mImageView, int lodingImage, int errorImageView, DiskCacheStrategy strategy,Transformation requestOptions) {
        loadImg(path, mImageView, lodingImage, errorImageView, strategy, false,requestOptions);
    }

    //needRealtime 是否需要实时获取
    public static void loadImg(Object path, ImageView mImageView, int lodingImage, int errorImageView, DiskCacheStrategy strategy, boolean needRealtime, Transformation requestOptions) {
        if (path instanceof String && (((String) path).contains("http") || ((String) path).contains("https"))) {
            if (((String) path).contains("?")) {
                path = String.valueOf(path) + "&x-oss-process=image/resize,s_320";
            } else {
                path = String.valueOf(path) + "?x-oss-process=image/resize,s_320";
            }
        }

        if (mImageView == null || mImageView.getContext() == null) {
            return;
        }

        if (mImageView.getContext() instanceof FragmentActivity){
            if (isDestroy((FragmentActivity)mImageView.getContext())){
                return;
            }
        }

        if (needRealtime) {
            if (requestOptions==null){
                GlideApp.with(mImageView.getContext()).load(path).centerCrop().signature(new ObjectKey(UUID.randomUUID())).diskCacheStrategy(strategy).
                        placeholder(lodingImage).error(errorImageView).into(mImageView);
            }else {
                GlideApp.with(mImageView.getContext()).load(path).centerCrop().signature(new ObjectKey(UUID.randomUUID())).diskCacheStrategy(strategy).
                        placeholder(lodingImage).error(errorImageView).transform(new CenterCrop(),requestOptions).into(mImageView);
            }
        } else {
            if (requestOptions==null){
                GlideApp.with(mImageView.getContext()).load(path).centerCrop().diskCacheStrategy(strategy).placeholder(lodingImage).
                        error(errorImageView).into(mImageView);
            }else {
                GlideApp.with(mImageView.getContext()).load(path).centerCrop().diskCacheStrategy(strategy).placeholder(lodingImage).
                        error(errorImageView).transform(new CenterCrop(),requestOptions).into(mImageView);
            }
        }
    }

    //图片加载监听
    public static void loadImg_listener(Object path, ImageView mImageView, int lodingImage, RequestListener<Drawable> listener) {
        if (path instanceof String) {
            if (((String) path).contains("?")) {
                path = String.valueOf(path) + "&x-oss-process=image/resize,s_320";
            } else {
                path = String.valueOf(path) + "?x-oss-process=image/resize,s_320";
            }
        }

        if (mImageView == null || mImageView.getContext() == null) {
            return;
        }

        if (mImageView.getContext() instanceof FragmentActivity){
            if (isDestroy((FragmentActivity)mImageView.getContext())){
                return;
            }
        }

        GlideApp.with(mImageView.getContext()).load(path).centerCrop().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).placeholder(lodingImage).
                error(lodingImage).listener(listener).into(mImageView);
    }

    public static void getImageWidHeig(Context context, String pathUrl, final IGetImageData iGetImageData) {
        if (context==null) {
            return;
        }

        if (context instanceof FragmentActivity){
            if (isDestroy((FragmentActivity)context)){
                return;
            }
        }

        //获取图片真正的宽高
        GlideApp.with(context)
                .asBitmap()//Glide返回一个Bitmap对象
                .load(pathUrl)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        double proportionScreen = new BigDecimal(String.valueOf(resource.getWidth())).divide(new BigDecimal(resource.getHeight()), 2, BigDecimal.ROUND_HALF_UP).doubleValue();//屏幕的宽高比
                        iGetImageData.sendData(resource.getWidth(), resource.getHeight(), proportionScreen);
                    }
                });
    }

    public interface IGetImageData {
        void sendData(int width, int height, double radio);
    }

    /**
     * 判断Activity是否Destroy
     *
     * @param activity
     * @return
     */
    public static boolean isDestroy(Activity mActivity) {
        if (mActivity == null || mActivity.isFinishing() || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && mActivity.isDestroyed())) {
            return true;
        } else {
            return false;
        }
    }
}
