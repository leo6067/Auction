package com.aten.compiler.widget.glide;

import android.app.ActivityManager;
import android.content.Context;

import com.aten.compiler.base.BaseGlobal;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

/**
 * project:PJHAndroidFrame
 * package:com.frame.pjhandroidframe.widget
 * Created by 彭俊鸿 on 2018/6/4.
 * e-mail : 1031028399@qq.com
 */
@GlideModule
public class MyGlideModule extends AppGlideModule {

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();//获取系统分配给应用的总内存大小
        int memoryCacheSize = maxMemory / 8;//设置图片内存缓存占用八分之一
        //设置内存缓存大小
        builder.setMemoryCache(new LruResourceCache(memoryCacheSize));

        int diskCacheSizeBytes = 1024*1024*250;  //250 MB
        builder.setDiskCache(new DiskLruCacheFactory(BaseGlobal.getImageGlideDir(), diskCacheSizeBytes));

        ActivityManager activityManager=(ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        if (null!=activityManager){
            ActivityManager.MemoryInfo memoryInfo=new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            builder.setDefaultRequestOptions(memoryInfo.lowMemory?
                    new RequestOptions().format(DecodeFormat.PREFER_RGB_565):
                    new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888));
        }

    }
}
