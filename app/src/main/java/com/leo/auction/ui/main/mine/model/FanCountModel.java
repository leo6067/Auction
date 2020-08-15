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
public class FanCountModel {

    /**
     * data : {"auctionFansNum":3,"exclusiveFansNum":0}
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1597026551706}
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
         * auctionFansNum : 3
         * exclusiveFansNum : 0
         */

        private int auctionFansNum;
        private int exclusiveFansNum;

        public int getAuctionFansNum() {
            return auctionFansNum;
        }

        public void setAuctionFansNum(int auctionFansNum) {
            this.auctionFansNum = auctionFansNum;
        }

        public int getExclusiveFansNum() {
            return exclusiveFansNum;
        }

        public void setExclusiveFansNum(int exclusiveFansNum) {
            this.exclusiveFansNum = exclusiveFansNum;
        }
    }

    public static class ResultBean {
        /**
         * code : 0
         * message : 请求成功
         * success : true
         * timestamp : 1597026551706
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



    public static  void httpGetFanCount(HttpRequest.HttpCallback httpCallback){

        HashMap<String, String> hashMap = new HashMap<>();



        HttpRequest.httpGetString(Constants.Api.FANCOUNT_URL,hashMap,httpCallback);



    }
}
