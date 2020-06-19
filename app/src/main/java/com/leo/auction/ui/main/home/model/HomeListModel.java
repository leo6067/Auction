package com.leo.auction.ui.main.home.model;

import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.home.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/6/17
 * 描    述： 首页全部一元拍列表
 * 修    改：
 * ===============================================
 */
public class HomeListModel {

    /**
     * data : [{"productInstanceId":"拍品实例标识","productInstanceCode":"sahsansahs121212","title":"品名","bidNum":"出价次数","firstPic":"主图","currentPrice":"当前价格","distributeType":"1-包邮 2-到付","refund":"1-包退 0-不包退"}]
     * result : {"success":true,"message":"请求成功"}
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

    public static class DataBean {
        /**
         * productInstanceId : 拍品实例标识
         * productInstanceCode : sahsansahs121212
         * title : 品名
         * bidNum : 出价次数
         * firstPic : 主图
         * currentPrice : 当前价格
         * distributeType : 1-包邮 2-到付
         * refund : 1-包退 0-不包退
         */

        private String productInstanceId;
        private String productInstanceCode;
        private String title;
        private String bidNum;
        private String firstPic;
        private String currentPrice;
        private String distributeType;
        private String refund;

        public String getProductInstanceId() {
            return productInstanceId;
        }

        public void setProductInstanceId(String productInstanceId) {
            this.productInstanceId = productInstanceId;
        }

        public String getProductInstanceCode() {
            return productInstanceCode;
        }

        public void setProductInstanceCode(String productInstanceCode) {
            this.productInstanceCode = productInstanceCode;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBidNum() {
            return bidNum;
        }

        public void setBidNum(String bidNum) {
            this.bidNum = bidNum;
        }

        public String getFirstPic() {
            return firstPic;
        }

        public void setFirstPic(String firstPic) {
            this.firstPic = firstPic;
        }

        public String getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(String currentPrice) {
            this.currentPrice = currentPrice;
        }

        public String getDistributeType() {
            return distributeType;
        }

        public void setDistributeType(String distributeType) {
            this.distributeType = distributeType;
        }

        public String getRefund() {
            return refund;
        }

        public void setRefund(String refund) {
            this.refund = refund;
        }
    }
}
