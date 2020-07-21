package com.leo.auction.ui.main.home.model;

import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;

import java.util.HashMap;
import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.home.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/17
 * 描    述： 百亿补贴
 * 修    改：
 * ===============================================
 */
public class SubsidyModel {


    /**
     * data : [{"currentPrice":"60","firstPic":"","interceptTime":"\u201c\u201d","productInstanceCode":"498a9ee3c8746158551344a918b9aa1f","productInstanceId":625,"subsidyMoney":"30","subsidyProduct":true}]
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1594304896896}
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
         * timestamp : 1594304896896
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
         * currentPrice : 60
         * firstPic :
         * interceptTime : “”
         * productInstanceCode : 498a9ee3c8746158551344a918b9aa1f
         * productInstanceId : 625
         * subsidyMoney : 30
         * subsidyProduct : true
         */

        private String currentPrice;
        private String firstPic;
        private long interceptTime;
        private String productInstanceCode;
        private int productInstanceId;
        private String subsidyMoney;
        private boolean subsidyProduct;

        public String getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(String currentPrice) {
            this.currentPrice = currentPrice;
        }

        public String getFirstPic() {
            return firstPic;
        }

        public void setFirstPic(String firstPic) {
            this.firstPic = firstPic;
        }

        public long getInterceptTime() {
            return interceptTime;
        }

        public void setInterceptTime(long interceptTime) {
            this.interceptTime = interceptTime;
        }

        public String getProductInstanceCode() {
            return productInstanceCode;
        }

        public void setProductInstanceCode(String productInstanceCode) {
            this.productInstanceCode = productInstanceCode;
        }

        public int getProductInstanceId() {
            return productInstanceId;
        }

        public void setProductInstanceId(int productInstanceId) {
            this.productInstanceId = productInstanceId;
        }

        public String getSubsidyMoney() {
            return subsidyMoney;
        }

        public void setSubsidyMoney(String subsidyMoney) {
            this.subsidyMoney = subsidyMoney;
        }

        public boolean isSubsidyProduct() {
            return subsidyProduct;
        }

        public void setSubsidyProduct(boolean subsidyProduct) {
            this.subsidyProduct = subsidyProduct;
        }
    }




    public static void httpGetSubsily(HttpRequest.HttpCallback httpCallback){

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNum","1");
        hashMap.put("pageSize","200");

        HttpRequest.httpGetString(Constants.Api.SUBSIDY_LIST_URL,hashMap,httpCallback);

    }


}
