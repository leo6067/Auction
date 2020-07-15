package com.leo.auction.ui.main.mine.model;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.mine.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/1
 * 描    述： 余额明细
 * 修    改：
 * ===============================================
 */
public class AssetDetailModel {


    /**
     * data : {"balance":100,"noPayMoney":0,"receiveMoney":0,"sendMoney":0,"freezeMoney":0}
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1590728081536}
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
         * balance : 100
         * noPayMoney : 0
         * receiveMoney : 0
         * sendMoney : 0
         * freezeMoney : 0
         */

        private String balance;
        private String noPayMoney;
        private String receiveMoney;
        private String sendMoney;
        private String freezeMoney;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getNoPayMoney() {
            return noPayMoney;
        }

        public void setNoPayMoney(String noPayMoney) {
            this.noPayMoney = noPayMoney;
        }

        public String getReceiveMoney() {
            return receiveMoney;
        }

        public void setReceiveMoney(String receiveMoney) {
            this.receiveMoney = receiveMoney;
        }

        public String getSendMoney() {
            return sendMoney;
        }

        public void setSendMoney(String sendMoney) {
            this.sendMoney = sendMoney;
        }

        public String getFreezeMoney() {
            return freezeMoney;
        }

        public void setFreezeMoney(String freezeMoney) {
            this.freezeMoney = freezeMoney;
        }
    }

    public static class ResultBean {
        /**
         * code : 0
         * message : 请求成功
         * success : true
         * timestamp : 1590728081536
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




}
