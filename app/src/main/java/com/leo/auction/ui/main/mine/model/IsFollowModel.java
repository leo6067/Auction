package com.leo.auction.ui.main.mine.model;

import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;

import java.util.HashMap;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.mine.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/8/12
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class IsFollowModel {


    /**
     * data : false
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1591769525387}
     */

    private boolean data;
    private ResultBean result;

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * code : 0
         * message : 请求成功
         * success : true
         * timestamp : 1591769525387
         */

        private String code;
        private String message;
        private boolean success;
        private long timestamp;

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

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }
    }

    public static void httpGetIsFoller(String shopUri,HttpRequest.HttpCallback httpCallback){

        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("shopUri",shopUri);

        HttpRequest.httpGetString(Constants.Api.FANS_IS_URL,hashMap,httpCallback);


    }
}
