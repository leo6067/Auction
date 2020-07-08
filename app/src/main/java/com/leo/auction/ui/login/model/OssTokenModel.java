package com.leo.auction.ui.login.model;

import com.alibaba.fastjson.JSON;
import com.leo.auction.base.Constants;
import com.leo.auction.net.CustomerJsonCallBack;
import com.leo.auction.net.HttpRequest;


import java.util.HashMap;

/**
 * ================================================
 * 项目名称：dgonline-android
 * 包    名：com.aten.dgonline_android.ui.index.model
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2018/12/10
 * 描    述：
 * ================================================
 */
public class OssTokenModel {
    /**
     * data : {"encryptedData":"UaV4v7oiWQY2wHeOohOAE855gTmDOy23UgIx7ag6eCTAlx9J9VdMIaY2ARDNG4GjkGgxBWCBzImdpFQtYsgVXFbAvibNL0EJ/ske7hXSl660jMRHdX35MKUiuoGn3SQP8/wcHKIIuPq13pAF9sFSaPg061ScniU2/4xcQOXHnCPeYFJM4QIPQzK6Xk4PXzycZNicAFb3ysMhqDWWmUK+1yOantFG2FldWbA+nVxzwatPY0hwS0qTQSBGwyMhXPRb"}
     * result : {"code":"0","message":"请求成功","success":true}
     */

    private DataBean data;
    private ResultBean result;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class DataBean {
        /**
         * encryptedData : UaV4v7oiWQY2wHeOohOAE855gTmDOy23UgIx7ag6eCTAlx9J9VdMIaY2ARDNG4GjkGgxBWCBzImdpFQtYsgVXFbAvibNL0EJ/ske7hXSl660jMRHdX35MKUiuoGn3SQP8/wcHKIIuPq13pAF9sFSaPg061ScniU2/4xcQOXHnCPeYFJM4QIPQzK6Xk4PXzycZNicAFb3ysMhqDWWmUK+1yOantFG2FldWbA+nVxzwatPY0hwS0qTQSBGwyMhXPRb
         */

        private String encryptedData;

        public String getEncryptedData() {
            return encryptedData;
        }

        public void setEncryptedData(String encryptedData) {
            this.encryptedData = encryptedData;
        }
    }

    public static class ResultBean {
        /**
         * code : 0
         * message : 请求成功
         * success : true
         */

        private String code;
        private String message;
        private boolean success;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

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

    public static void sendOssTokenRequest(HttpRequest.HttpCallback callback) {
        HashMap<String,String> value=new HashMap<>();

        HttpRequest.httpGetString(Constants.Api.OSS_COMMON_URL,value,callback);


    }
}
