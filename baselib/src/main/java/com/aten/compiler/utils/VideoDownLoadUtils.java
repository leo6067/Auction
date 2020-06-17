package com.aten.compiler.utils;

import android.graphics.Bitmap;
import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.aten.compiler.utils
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2020/4/9 0009
 * 描    述：
 * ================================================
 */
public class VideoDownLoadUtils {
    public static void downLoadPic(String url, final String tag,String destFileDir, String destFileName, final IVideoDownLoad videoDownLoad){
        if (!EmptyUtils.isEmpty(url)&&(url.startsWith("http")||url.startsWith("https"))){
            OkHttpUtils.get().url(url).tag(tag).build().execute(new FileCallBack(destFileDir,destFileName) {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Log.e("onError","onError=="+e.toString());
                    videoDownLoad.onDownLoadFail("视频下载失败");
                }

                @Override
                public void onResponse(File response, int id) {
                    videoDownLoad.onDownLoadSuccess(response,tag);
                }
            });
        }else {
            videoDownLoad.onDownLoadFail("视频地址不是网络地址");
        }
    }

    public interface IVideoDownLoad{
        void onDownLoadSuccess(File response,String tag);
        void onDownLoadFail(String errorText);
    }
}
