package com.leo.auction.net;

import android.content.Intent;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.net.Convert;
import com.aten.compiler.utils.BroadCastReceiveUtils;
import com.aten.compiler.utils.LogUtils;
import com.aten.compiler.utils.NetworkUtils;
import com.aten.compiler.utils.RxTool;


import com.aten.compiler.utils.ToastUtils;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.Constants;
import com.leo.auction.ui.login.model.UserInfoModel;
import com.zhy.http.okhttp.callback.StringCallback;

import org.litepal.LitePal;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Response;

/**
 * ================================================
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2018/5/13
 * 描    述：网络请求 回调解析类
 * ================================================
 */

public abstract class CustomerJsonCallBack<T> extends StringCallback {
    @Override
    public String parseNetworkResponse(Response response, int id) throws IOException {
//        List<Cookie> cookies = Cookie.parseAll(response.request().url(), response.headers());
//        Log.e("cookies",response.request().url()+"====cookies==="+cookies.toString());

        String str = response.body().string();
//        Globals.log("xxxxxxxxxxxxxxxxx str"  +str );
        response.close();
        //1.获取到加密数据 进行解密

//        LogUtils.json(LogUtils.E, "Url:" + response.request().url(), str);
        return str;
    }

    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象,生产onSuccess回调中需要的数据对象
     * 这里的解析工作不同的业务逻辑基本都不一样,所以需要自己实现,以下给出的时模板代码,实际使用根据需要修改
     */
    @Override
    public void onResponse(String response, int id) {
        //2.解密完之后 可以取code值进行回调操作
        if (TextUtils.isEmpty(response)) {
//            ToastUtils.showShort("服务器开小差，请联系客服!");
            return;
        }

        JSONObject jsonObject = null;
        try {
            jsonObject = JSON.parseObject(JSON.parseObject(response).get("result").toString());

            boolean success = jsonObject.getBoolean("success");
            String message = jsonObject.getString("message");
            String code = jsonObject.getString("code");

            // 如果没有通过构造函数传进来，就自动解析父类泛型的真实类型（有局限性，继承后就无法解析到）
            T t = null;
            if (success) {//成功
                try {
                    Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                    t = Convert.fromJson(response, type);
                } catch (Exception e) {
                    onRequestError(t, message);
                    return;
                }
                onRequestSuccess(t);
            } else {

//                Globals.log("xxxxxxxxxxxxx  code"  +code);
//                if ("5002".equals(code)){//登录超时，重新登录
//                    //1.清空数据得数据
//                    //2.跳转首页
//                    LitePal.deleteAll(UserInfoModel.class);
//
//                    //刷新首页
//                    Intent intent=new Intent(Constants.Action.SEND_REFRESH_HOME);
//                    intent.putExtra("type","0");
//                    BroadCastReceiveUtils.sendLocalBroadCast(RxTool.getContext(), intent);
//                    //跳转登录页面
//                    Intent intent02=new Intent(Constants.Action.SEND_START_MAINACTIVITY_0);
//                    intent02.putExtra("type","1");
//                    BroadCastReceiveUtils.sendLocalBroadCast(RxTool.getContext(),intent02);
//                }else if ("5004".equals(code)){//用户被抢登得情况
//                    //1.清空数据得数据
//                    //2.跳转首页
//                    LitePal.deleteAll(UserInfoModel.class);
//                    //刷新首页
//                    Intent intent=new Intent(Constants.Action.SEND_REFRESH_HOME);
//                    intent.putExtra("type","0");
//                    BroadCastReceiveUtils.sendLocalBroadCast(RxTool.getContext(), intent);
//
//                    //跳转首页
//                    Intent intent02=new Intent(Constants.Action.SEND_START_MAINACTIVITY_0);
//                    intent02.putExtra("type","0");
//                    BroadCastReceiveUtils.sendLocalBroadCast(RxTool.getContext(),intent02);
//                }


                if ("5002".equals(code)) {//登录超时，重新登录
                    //1.清空数据得数据
                    //2.跳转首页
                    LitePal.deleteAll(UserInfoModel.class);
                    //刷新首页
                    Intent intent = new Intent(Constants.Action.ACTION_LOGIN);
                    intent.putExtra("type", "0");
                    BroadCastReceiveUtils.sendLocalBroadCast(RxTool.getContext(), intent);
                }

                try {
                    Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                    t = Convert.fromJson(response, type);
                } catch (Exception e) {
                    LogUtils.e(e.getMessage());
                    onRequestError(t, message);
                    return;
                }
                onRequestError(t, message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort("请开启网络!");
        } else {
            onRequestError(null, e.getMessage());
        }
    }

    //后台错误code的回调 若有用到returnData,需要判断returnData是否为空
    public abstract void onRequestError(T returnData, String msg);

    //后台成功code的回调
    public abstract void onRequestSuccess(T returnData);
    //token失效时需要回调的方法
}
