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
     * data : {"qrcode":"首页二维码图片"}
     * result : {"success":true,"message":"请求成功"}
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
         * qrcode : 首页二维码图片
         */

        private String qrcode;

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }
    }

    public static class ResultBean {
        /**
         * success : true
         * message : 请求成功
         */

        private boolean success;
        private String message;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static void sendGenerateQrcodeRequest( String type, String page,  boolean refresh,
                                                 HttpRequest.HttpCallback callback) {


        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("type",type);
        hashMap.put("page",page);  // 1-推荐粉丝  2-推荐商家  3-拍品详情    4-超级仓库商品详情


        hashMap.put("refresh",refresh+"");
        HttpRequest.httpGetString(Constants.Api.SPREAD_QRCODE_URL, hashMap, callback);
    }


}
