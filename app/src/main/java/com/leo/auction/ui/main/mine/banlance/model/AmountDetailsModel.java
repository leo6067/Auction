package com.leo.auction.ui.main.mine.banlance.model;

import com.alibaba.fastjson.JSONObject;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;

import java.util.HashMap;
import java.util.List;

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
public class AmountDetailsModel {


    /**
     * data : [{"logId":1,"balanceId":2,"changeType":12,"changeName":"变动名称","changeNum":"变动金额","changeTime":"变动时间","flag":true,"payTypeName":"支付类型名称","comment":"备注"}]
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1592205298452}
     */

    private ResultBean result;
    private List<DataBean> data;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class ResultBean {
        /**
         * code : 0
         * message : 请求成功
         * success : true
         * timestamp : 1592205298452
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

    public static class DataBean {
        /**
         * logId : 1
         * balanceId : 2
         * changeType : 12
         * changeName : 变动名称
         * changeNum : 变动金额
         * changeTime : 变动时间
         * flag : true
         * payTypeName : 支付类型名称
         * comment : 备注
         */

        private int logId;
        private int balanceId;
        private int changeType;
        private String changeName;
        private String changeNum;
        private String changeTime;
        private boolean flag;
        private String payTypeName;
        private String comment;

        public int getLogId() {
            return logId;
        }

        public void setLogId(int logId) {
            this.logId = logId;
        }

        public int getBalanceId() {
            return balanceId;
        }

        public void setBalanceId(int balanceId) {
            this.balanceId = balanceId;
        }

        public int getChangeType() {
            return changeType;
        }

        public void setChangeType(int changeType) {
            this.changeType = changeType;
        }

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

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public String getPayTypeName() {
            return payTypeName;
        }

        public void setPayTypeName(String payTypeName) {
            this.payTypeName = payTypeName;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }

    public static void httpAmountDetailsModel(int pageNum, String parentType,
                                              String changeType, String startTime, String endTime,
                                              HttpRequest.HttpCallback httpCallback) {


        HashMap<String, String> params = new HashMap<>();
        params.put("pageNum", pageNum + "");
        params.put("pageSize", Constants.Var.LIST_NUMBER);
        params.put("parentType", parentType);
        params.put("changeType", changeType);
        params.put("startTime", startTime);
        params.put("endTime", endTime);


        HttpRequest.httpGetString(Constants.Api.BALANCE_DETAIL_URL, params, httpCallback);


    }
}
