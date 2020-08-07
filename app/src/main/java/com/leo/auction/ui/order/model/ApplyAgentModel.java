package com.leo.auction.ui.order.model;

import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;

import java.util.HashMap;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.order.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/8/7
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class ApplyAgentModel {

    /**
     * data : {"bssCode":"601","tradeNo":"16316735163hdsd","money":12,"scene":"agent"}
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1593833490633}
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
         * bssCode : 601
         * tradeNo : 16316735163hdsd
         * money : 12
         * scene : agent
         */

        private String bssCode;
        private String tradeNo;
        private int money;
        private String scene;

        public String getBssCode() {
            return bssCode;
        }

        public void setBssCode(String bssCode) {
            this.bssCode = bssCode;
        }

        public String getTradeNo() {
            return tradeNo;
        }

        public void setTradeNo(String tradeNo) {
            this.tradeNo = tradeNo;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public String getScene() {
            return scene;
        }

        public void setScene(String scene) {
            this.scene = scene;
        }
    }

    public static class ResultBean {
        /**
         * code : 0
         * message : 请求成功
         * success : true
         * timestamp : 1593833490633
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


    public static void httpGetApplyAgent(HttpRequest.HttpCallback httpCallback){

        HashMap<String, String> hashMap = new HashMap<>();

        HttpRequest.httpGetString(Constants.Api.APPLYAGENT_URL,hashMap,httpCallback);


    }
}
