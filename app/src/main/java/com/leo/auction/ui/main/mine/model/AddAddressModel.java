package com.leo.auction.ui.main.mine.model;

import com.alibaba.fastjson.JSONObject;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.ui.order.model
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2020/2/11
 * 描    述：
 * ================================================
 */
public class AddAddressModel {


    /**
     * data : 346
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1595993006793}
     */

    private int data;
    private ResultBean result;



    public int getData() {
        return data;
    }

    public void setData(int data) {
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
         * timestamp : 1595993006793
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

    //添加地址
    public static void sendAddressRequest(final String TAG, String linkman, String addr1, String addr2, String addr3,
                                          String phone, String address, String code, String defaultAddress, String type,
                                          final HttpRequest.HttpCallback callback) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("linkman", linkman);
        jsonObject.put("addr1", addr1);
        jsonObject.put("addr2", addr2);
        jsonObject.put("addr3", addr3);
        jsonObject.put("phone", phone);
        jsonObject.put("address", address);
        jsonObject.put("code", code);
        jsonObject.put("defaultAddress", defaultAddress);
        jsonObject.put("type", type);

        HttpRequest.httpPostString(Constants.Api.ADDRESS_URL, jsonObject, callback);
    }
}
