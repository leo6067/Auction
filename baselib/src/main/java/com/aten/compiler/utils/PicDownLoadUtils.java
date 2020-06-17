package com.aten.compiler.utils;

import android.graphics.Bitmap;
import android.util.Log;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import okhttp3.Call;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.aten.compiler.utils
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2020/1/15 0015
 * 描    述：
 * ================================================
 */
public class PicDownLoadUtils {

    public static void downLoadPic(String url, final String tag, final IPicDownLoad iPicDownLoad){
        if (!EmptyUtils.isEmpty(url)&&(url.startsWith("http")||url.startsWith("https"))){
            OkHttpUtils.get().url(url).tag(tag).build().execute(new BitmapCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Log.e("onError","onError=="+e.toString());
                    iPicDownLoad.onDownLoadFail("图片下载失败");
                }

                @Override
                public void onResponse(Bitmap response, int id) {
                    iPicDownLoad.onDownLoadSuccess(response,tag);
                }
            });
        }else {
            iPicDownLoad.onDownLoadFail("图片地址不是网络地址");
        }

    }

    public interface IPicDownLoad{
        void onDownLoadSuccess(Bitmap response,String tag);
        void onDownLoadFail(String errorText);
    }
}
