package com.leo.auction.ui.main.mine.model;

import com.alibaba.fastjson.JSONObject;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;

import java.util.HashMap;


/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.ui.seller.model
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/12/5
 * 描    述：
 * ================================================
 */
public class GenerateQrcodeModel {


    /**
     * data : https://file.taojianlou.com/ut/product/bbd79d77de2fa8243dfedf1ccb6dac4d.png
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1595833903712}
     */

    private String data;
    private ResultBean result;


    public String getData() {
        return data;
    }

    public void setData(String data) {
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
         * timestamp : 1595833903712
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

    public static void sendGenerateQrcodeRequest(String type, String page,
                                                 HttpRequest.HttpCallback callback) {


        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("type", type);// 1-推荐粉丝  2-推荐商家  3-拍品详情    4-超级仓库商品详情 5 永久二维码
        hashMap.put("page", page);
        HttpRequest.httpGetString(Constants.Api.SPREAD_QRCODE_URL, hashMap, callback);
    }


    public static void httpGetQrcodeRequest(String type, String shopUri, String userId,
                                            HttpRequest.HttpCallback callback) {


        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("type", type);// 1-推荐粉丝  2-推荐商家  3-拍品详情    4-超级仓库商品详情 5 永久二维码

        JSONObject page = new JSONObject();
        page.put("shopUri", shopUri);
        page.put("userId", userId);

        hashMap.put("page", page.toString());

        HttpRequest.httpGetString(Constants.Api.SPREAD_QRCODE_URL, hashMap, callback);
    }


    public static void httpGetQrcodeRequest(String type, String shopUri, String userId,String sourceId,
                                            HttpRequest.HttpCallback callback) {


        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("type", type);// 1-推荐粉丝  2-推荐商家  3-拍品详情    4-超级仓库商品详情 5 永久二维码

        JSONObject page = new JSONObject();
        page.put("shopUri", shopUri);
        page.put("userId", userId);

        hashMap.put("page", page.toString());
        hashMap.put("sourceId", sourceId);

        HttpRequest.httpGetString(Constants.Api.SPREAD_QRCODE_URL, hashMap, callback);
    }

}
