/*
******************************* Copyright (c)*********************************\
**
**                 (c) Copyright 2017, King, china
**                          All Rights Reserved
**                                
**                              By(King)
**                         
**------------------------------------------------------------------------------
*/
package com.leo.auction.utils.wxPay;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.aten.compiler.utils.easyPay.base.IPayStrategy;
import com.aten.compiler.utils.easyPay.callback.IPayCallback;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPay implements IPayStrategy<WXPayBean> {
    private WXPayBean payInfoImpli;
    private static IPayCallback sPayCallback;
    private IWXAPI mWXApi;
    private boolean initializated=false;

    /**
     * 内部类实现单例模式
     * 延迟加载，减少内存开销
     */
    private static class SingletonHolder {
        private static WXPay instance = new WXPay();
    }

    /**
     * 私有的构造函数
     */
    private WXPay() {}

    public static WXPay getInstance() {
        return WXPay.SingletonHolder.instance;
    }

    public IWXAPI getWXApi() {
        return mWXApi;
    }

    private void initWXApi(Context context, String appId) {
        mWXApi = WXAPIFactory.createWXAPI(context.getApplicationContext(), appId);
        mWXApi.registerApp(appId);
        initializated = true;
    }

    @Override
    public void pay(Activity activity, WXPayBean payInfo, IPayCallback payCallback) {
        this.payInfoImpli = payInfo;
        sPayCallback = payCallback;

        if(payInfoImpli == null || TextUtils.isEmpty(payInfoImpli.getAppid()) || TextUtils.isEmpty(payInfoImpli.getPartnerid())
                || TextUtils.isEmpty(payInfoImpli.getPrepayId()) || TextUtils.isEmpty(payInfoImpli.getPackageValue()) ||
                TextUtils.isEmpty(payInfoImpli.getNonceStr()) || TextUtils.isEmpty(payInfoImpli.getTimestamp()) ||
                TextUtils.isEmpty(payInfoImpli.getSign())) {
            if(payCallback != null) {
                payCallback.failed(WXErrCodeEx.CODE_ILLEGAL_ARGURE, WXErrCodeEx.getMessageByCode(WXErrCodeEx.CODE_ILLEGAL_ARGURE));
            }
            return;
        }

        if (!initializated) {
            initWXApi(activity.getApplicationContext(), payInfoImpli.getAppid());
        }

        if(!check()) {
            if(payCallback != null) {
                payCallback.failed(WXErrCodeEx.CODE_UNSUPPORT, WXErrCodeEx.getMessageByCode(WXErrCodeEx.CODE_UNSUPPORT));
            }
            return;
        }

        PayReq req = new PayReq();
        req.appId = payInfoImpli.getAppid();
        req.partnerId = payInfoImpli.getPartnerid();
        req.prepayId = payInfoImpli.getPrepayId();
        req.packageValue = payInfoImpli.getPackageValue();
        req.nonceStr = payInfoImpli.getNonceStr();
        req.timeStamp = payInfoImpli.getTimestamp();
        req.sign = payInfoImpli.getSign();

        mWXApi.sendReq(req);
    }

    /**
     * 支付回调响应
     */
    public void onResp(int errorCode, String errorMsg) {
        if(sPayCallback == null) {
            return;
        }

        if(errorCode == BaseResp.ErrCode.ERR_OK) {
            sPayCallback.success();
        } else if(errorCode == BaseResp.ErrCode.ERR_COMM) {
            sPayCallback.failed(errorCode, errorMsg);
        } else if(errorCode == BaseResp.ErrCode.ERR_USER_CANCEL) {
            sPayCallback.cancel();
        } else {
            sPayCallback.failed(errorCode, errorMsg);
        }

        sPayCallback = null;
    }

    /**
     * 检测是否支持微信支付
     */
    private boolean check() {
        return mWXApi.isWXAppInstalled() && mWXApi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
    }
}
