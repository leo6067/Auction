package com.leo.auction.ui.main.mine.model;

import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.mine.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/2
 * 描    述： 拍品管理列表
 * 修    改：
 * ===============================================
 */
public class ProductListModel {


    /**
     * data : [{"productInstanceId":1,"productInstanceCode":"12jsdhdhsd","productId":1,"title":"这边是商品标题","firstPic":"主图","createTime":"5月31日 21:02","currentPrice":"当前价","status":"状态","statusName":"状态名称","sourceType":"来源类型  1-自行发拍  2-产品库","agentPrice":"供货价","bidNum":"出价次数","goodsId":"商品标识","markupRange":10,"startPrice":20}]
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1591252464267}
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
         * timestamp : 1591252464267
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
         * productInstanceId : 1
         * productInstanceCode : 12jsdhdhsd
         * productId : 1
         * title : 这边是商品标题
         * firstPic : 主图
         * createTime : 5月31日 21:02
         * currentPrice : 当前价
         * status : 状态
         * statusName : 状态名称
         * sourceType : 来源类型  1-自行发拍  2-产品库
         * agentPrice : 供货价
         * bidNum : 出价次数
         * goodsId : 商品标识
         * markupRange : 10
         * startPrice : 20
         */

        private int productInstanceId;
        private String productInstanceCode;
        private int productId;
        private String title;
        private String firstPic;
        private String createTime;
        private String currentPrice;
        private String status;
        private String statusName;
        private String sourceType;
        private String agentPrice;
        private String bidNum;
        private String goodsId;
        private int markupRange;
        private int startPrice;

        public int getProductInstanceId() {
            return productInstanceId;
        }

        public void setProductInstanceId(int productInstanceId) {
            this.productInstanceId = productInstanceId;
        }

        public String getProductInstanceCode() {
            return productInstanceCode;
        }

        public void setProductInstanceCode(String productInstanceCode) {
            this.productInstanceCode = productInstanceCode;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getFirstPic() {
            return firstPic;
        }

        public void setFirstPic(String firstPic) {
            this.firstPic = firstPic;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(String currentPrice) {
            this.currentPrice = currentPrice;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }

        public String getSourceType() {
            return sourceType;
        }

        public void setSourceType(String sourceType) {
            this.sourceType = sourceType;
        }

        public String getAgentPrice() {
            return agentPrice;
        }

        public void setAgentPrice(String agentPrice) {
            this.agentPrice = agentPrice;
        }

        public String getBidNum() {
            return bidNum;
        }

        public void setBidNum(String bidNum) {
            this.bidNum = bidNum;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public int getMarkupRange() {
            return markupRange;
        }

        public void setMarkupRange(int markupRange) {
            this.markupRange = markupRange;
        }

        public int getStartPrice() {
            return startPrice;
        }

        public void setStartPrice(int startPrice) {
            this.startPrice = startPrice;
        }
    }
}
