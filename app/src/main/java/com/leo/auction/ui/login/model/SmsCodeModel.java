package com.leo.auction.ui.login.model;

import com.alibaba.fastjson.JSON;
import com.leo.auction.base.Constants;
import com.leo.auction.net.CustomerJsonCallBack;
import com.leo.auction.net.HttpRequest;

import java.util.HashMap;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.ui.mine.model
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/10/31 0031
 * 描    述：
 * ================================================
 */
public class SmsCodeModel {
    /**
     * result : {"message":"请求成功","success":true}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * message : 请求成功
         * success : true
         */

        private String message;
        private boolean success;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }
    }

    public static void sendSmsCodeRequest(final String TAG, String bssCode, String number,LoginVerModel loginVerModel, final CustomerJsonCallBack<SmsCodeModel> callback) {
        HashMap<String,String> value=new HashMap<>();
        value.put("bssCode",bssCode);//使用场景码 1:商家审核 2：绑定手机号 4-app端登录使用(选择此选项)
        value.put("number",number);//    sessionId:'会话ID',  'token', scene:'业务场景',     sign:'签名'

        value.put("sessionId",loginVerModel.getSessionId());
        value.put("token",loginVerModel.getToken());
        value.put("scene",loginVerModel.getScene());
        value.put("sign",loginVerModel.getSign());


        HttpRequest.requesNetWork(TAG, Constants.Api.HOMEPAGE_SEND_SMS_URL, JSON.toJSONString(value), callback);
    }
}
