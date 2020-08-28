package com.leo.auction.ui.main.mine.model;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.mine.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/8/28
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class UpperModel {


    /**
     * data : {"interceptTime":1598603170662,"markupRange":"0","productInstanceCode":"601640e1578b28c196387d63dd3e6b1f","productInstanceId":1639,"refund":false,"startPrice":"0"}
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1598602870754}
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
         * interceptTime : 1598603170662
         * markupRange : 0
         * productInstanceCode : 601640e1578b28c196387d63dd3e6b1f
         * productInstanceId : 1639
         * refund : false
         * startPrice : 0
         */

        private long interceptTime;
        private String markupRange;
        private String productInstanceCode;
        private int productInstanceId;
        private boolean refund;
        private String startPrice;

        public long getInterceptTime() {
            return interceptTime;
        }

        public void setInterceptTime(long interceptTime) {
            this.interceptTime = interceptTime;
        }

        public String getMarkupRange() {
            return markupRange;
        }

        public void setMarkupRange(String markupRange) {
            this.markupRange = markupRange;
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

        public boolean isRefund() {
            return refund;
        }

        public void setRefund(boolean refund) {
            this.refund = refund;
        }

        public String getStartPrice() {
            return startPrice;
        }

        public void setStartPrice(String startPrice) {
            this.startPrice = startPrice;
        }
    }

    public static class ResultBean {
        /**
         * code : 0
         * message : 请求成功
         * success : true
         * timestamp : 1598602870754
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
