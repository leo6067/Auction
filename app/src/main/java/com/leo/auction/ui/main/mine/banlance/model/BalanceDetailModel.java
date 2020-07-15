package com.leo.auction.ui.main.mine.banlance.model;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.mine.banlance.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/11
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class BalanceDetailModel {


    /**
     * data : {"changeName":"退款-拍品货款","changeNum":"1.00","changeTime":"2020-06-29 18:20:32","changeType":512,"comment":"成功","flag":false,"logId":119,"payType":2,"payTypeName":"余额","skipType":1,"skipTypeName":"查看拍品详情","sourceCode":"dc9fcafc23d66899af8783c4b590f010","sourceId":94,"tradeNo":"9bf7618aef701c2187fa7f3fd8308b6a"}
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1593480426874}
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
         * changeName : 退款-拍品货款
         * changeNum : 1.00
         * changeTime : 2020-06-29 18:20:32
         * changeType : 512
         * comment : 成功
         * flag : false
         * logId : 119
         * payType : 2
         * payTypeName : 余额
         * skipType : 1
         * skipTypeName : 查看拍品详情
         * sourceCode : dc9fcafc23d66899af8783c4b590f010
         * sourceId : 94
         * tradeNo : 9bf7618aef701c2187fa7f3fd8308b6a
         */

        private String changeName;
        private String changeNum;
        private String changeTime;
        private int changeType;
        private String comment;
        private boolean flag;
        private int logId;
        private int payType;
        private String payTypeName;
        private int skipType;
        private String skipTypeName;
        private String sourceCode;
        private int sourceId;
        private String tradeNo;

        public String getChangeName() {
            return changeName;
        }

        public void setChangeName(String changeName) {
            this.changeName = changeName;
        }

        public String getChangeNum() {
            return changeNum;
        }

        public void setChangeNum(String changeNum) {
            this.changeNum = changeNum;
        }

        public String getChangeTime() {
            return changeTime;
        }

        public void setChangeTime(String changeTime) {
            this.changeTime = changeTime;
        }

        public int getChangeType() {
            return changeType;
        }

        public void setChangeType(int changeType) {
            this.changeType = changeType;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public int getLogId() {
            return logId;
        }

        public void setLogId(int logId) {
            this.logId = logId;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public String getPayTypeName() {
            return payTypeName;
        }

        public void setPayTypeName(String payTypeName) {
            this.payTypeName = payTypeName;
        }

        public int getSkipType() {
            return skipType;
        }

        public void setSkipType(int skipType) {
            this.skipType = skipType;
        }

        public String getSkipTypeName() {
            return skipTypeName;
        }

        public void setSkipTypeName(String skipTypeName) {
            this.skipTypeName = skipTypeName;
        }

        public String getSourceCode() {
            return sourceCode;
        }

        public void setSourceCode(String sourceCode) {
            this.sourceCode = sourceCode;
        }

        public int getSourceId() {
            return sourceId;
        }

        public void setSourceId(int sourceId) {
            this.sourceId = sourceId;
        }

        public String getTradeNo() {
            return tradeNo;
        }

        public void setTradeNo(String tradeNo) {
            this.tradeNo = tradeNo;
        }
    }

    public static class ResultBean {
        /**
         * code : 0
         * message : 请求成功
         * success : true
         * timestamp : 1593480426874
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
