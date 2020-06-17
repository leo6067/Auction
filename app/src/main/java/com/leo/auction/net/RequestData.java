package com.leo.auction.net;

import com.aten.compiler.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.HashMap;

/**
 * ================================================
 * 项目名称：dgonline-android
 * 包    名：com.aten.compiler.net
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2018/12/14
 * 描    述：请求基础类（okhttputils版本）Post表单形式
 * ================================================
 */
public class RequestData {
    public static void requesNetWork(String TAG, String url, HashMap<String, String> dataParams, Callback callback){
        LogUtils.e("url:" + url);
        LogUtils.e("dataParams:"+dataParams.toString());
        OkHttpUtils
                .post()
//                .addHeader("Referer","http://dgonline-app.test.atenops.com/")
                .url(url)
                .tag(TAG)
                .params(dataParams)
                .build()
                .execute(callback);
    }
}
