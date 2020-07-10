package com.leo.auction.ui.main.home.model;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.home.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/9
 * 描    述： 出价返回
 * 修    改：
 * ===============================================
 */
public class BidModel {


    /**
     * data : {"bssCode":0,"bssMessage":"OK","money":12,"tradeNo":"123131","scene":"bid"}
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
         * bssCode : 0
         * bssMessage : OK
         * money : 12
         * tradeNo : 123131
         * scene : bid
         */

        private int bssCode;
        private String bssMessage;
        private int money;
        private String tradeNo;
        private String scene;

        public int getBssCode() {
            return bssCode;
        }

        public void setBssCode(int bssCode) {
            this.bssCode = bssCode;
        }

        public String getBssMessage() {
            return bssMessage;
        }

        public void setBssMessage(String bssMessage) {
            this.bssMessage = bssMessage;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public String getTradeNo() {
            return tradeNo;
        }

        public void setTradeNo(String tradeNo) {
            this.tradeNo = tradeNo;
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
}
