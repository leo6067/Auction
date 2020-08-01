package com.leo.auction.ui.order.model;

import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;

import java.util.HashMap;
import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.order.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/31
 * 描    述： 拍品 付款
 * 修    改：
 * ===============================================
 */
public class OrderGoodJson {


    /**
     * data : {"addressUpdate":0,"buyerCancelRefund":false,"createTime":1596270989000,"delayConfirmTake":false,"delayConfirmTakeViewMinute":4,"delayMinute":8,"delayed":false,"distributeType":1,"expire":1596273869000,"expressUpdate":0,"faceTradeMinute":5,"headImg":"https://file.taojianlou.com/ut/product/gQr7imxhORXr0d80fMTcx04kpDLmg7VX.png?image=1200,1200","items":[{"firstPic":"https://file.taojianlou.com/Android/goods/img20200801/1596270668108.png?image=900,598","instanceCode":"09147351e754a7996b8479cdee9611d0","num":1,"price":"6.00","productInstanceId":1225,"title":"安卓测试。。。五分钟","total":"6.00"}],"nickname":"进炜的店铺1","noSendRefundViewMinute":2,"orderCode":"1277944727446346149047","payment":"6.00","productTime":1596270689000,"remindSend":false,"remindSendViewMinute":6,"replaceSend":false,"seller":false,"sendRefundViewMinute":2,"sourceType":1,"status":1,"subsidyMoney":"0","subsidyProduct":false}
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1596271687582}
     */

    private DataBean data;
    private ResultBean result;

    //获取评价详情 或者 订单详情
    public static void httpGetOrderModel(String orderCode, HttpRequest.HttpCallback httpCallback){

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("orderCode",orderCode);
        HttpRequest.httpGetString(Constants.Api.ORDER_INFO_URL,hashMap,httpCallback);

    }

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
         * addressUpdate : 0
         * buyerCancelRefund : false
         * createTime : 1596270989000
         * delayConfirmTake : false
         * delayConfirmTakeViewMinute : 4
         * delayMinute : 8
         * delayed : false
         * distributeType : 1
         * expire : 1596273869000
         * expressUpdate : 0
         * faceTradeMinute : 5
         * headImg : https://file.taojianlou.com/ut/product/gQr7imxhORXr0d80fMTcx04kpDLmg7VX.png?image=1200,1200
         * items : [{"firstPic":"https://file.taojianlou.com/Android/goods/img20200801/1596270668108.png?image=900,598","instanceCode":"09147351e754a7996b8479cdee9611d0","num":1,"price":"6.00","productInstanceId":1225,"title":"安卓测试。。。五分钟","total":"6.00"}]
         * nickname : 进炜的店铺1
         * noSendRefundViewMinute : 2
         * orderCode : 1277944727446346149047
         * payment : 6.00
         * productTime : 1596270689000
         * remindSend : false
         * remindSendViewMinute : 6
         * replaceSend : false
         * seller : false
         * sendRefundViewMinute : 2
         * sourceType : 1
         * status : 1
         * subsidyMoney : 0
         * subsidyProduct : false
         */

        private int addressUpdate;
        private boolean buyerCancelRefund;
        private long createTime;
        private boolean delayConfirmTake;
        private int delayConfirmTakeViewMinute;
        private int delayMinute;
        private boolean delayed;
        private int distributeType;
        private long expire;
        private int expressUpdate;
        private int faceTradeMinute;
        private String headImg;
        private String nickname;
        private int noSendRefundViewMinute;
        private String orderCode;
        private String payment;
        private long productTime;
        private boolean remindSend;
        private int remindSendViewMinute;
        private boolean replaceSend;
        private boolean seller;
        private int sendRefundViewMinute;
        private int sourceType;
        private int status;
        private String subsidyMoney;
        private boolean subsidyProduct;
        private List<ItemsBean> items;

        public int getAddressUpdate() {
            return addressUpdate;
        }

        public void setAddressUpdate(int addressUpdate) {
            this.addressUpdate = addressUpdate;
        }

        public boolean isBuyerCancelRefund() {
            return buyerCancelRefund;
        }

        public void setBuyerCancelRefund(boolean buyerCancelRefund) {
            this.buyerCancelRefund = buyerCancelRefund;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public boolean isDelayConfirmTake() {
            return delayConfirmTake;
        }

        public void setDelayConfirmTake(boolean delayConfirmTake) {
            this.delayConfirmTake = delayConfirmTake;
        }

        public int getDelayConfirmTakeViewMinute() {
            return delayConfirmTakeViewMinute;
        }

        public void setDelayConfirmTakeViewMinute(int delayConfirmTakeViewMinute) {
            this.delayConfirmTakeViewMinute = delayConfirmTakeViewMinute;
        }

        public int getDelayMinute() {
            return delayMinute;
        }

        public void setDelayMinute(int delayMinute) {
            this.delayMinute = delayMinute;
        }

        public boolean isDelayed() {
            return delayed;
        }

        public void setDelayed(boolean delayed) {
            this.delayed = delayed;
        }

        public int getDistributeType() {
            return distributeType;
        }

        public void setDistributeType(int distributeType) {
            this.distributeType = distributeType;
        }

        public long getExpire() {
            return expire;
        }

        public void setExpire(long expire) {
            this.expire = expire;
        }

        public int getExpressUpdate() {
            return expressUpdate;
        }

        public void setExpressUpdate(int expressUpdate) {
            this.expressUpdate = expressUpdate;
        }

        public int getFaceTradeMinute() {
            return faceTradeMinute;
        }

        public void setFaceTradeMinute(int faceTradeMinute) {
            this.faceTradeMinute = faceTradeMinute;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getNoSendRefundViewMinute() {
            return noSendRefundViewMinute;
        }

        public void setNoSendRefundViewMinute(int noSendRefundViewMinute) {
            this.noSendRefundViewMinute = noSendRefundViewMinute;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }

        public long getProductTime() {
            return productTime;
        }

        public void setProductTime(long productTime) {
            this.productTime = productTime;
        }

        public boolean isRemindSend() {
            return remindSend;
        }

        public void setRemindSend(boolean remindSend) {
            this.remindSend = remindSend;
        }

        public int getRemindSendViewMinute() {
            return remindSendViewMinute;
        }

        public void setRemindSendViewMinute(int remindSendViewMinute) {
            this.remindSendViewMinute = remindSendViewMinute;
        }

        public boolean isReplaceSend() {
            return replaceSend;
        }

        public void setReplaceSend(boolean replaceSend) {
            this.replaceSend = replaceSend;
        }

        public boolean isSeller() {
            return seller;
        }

        public void setSeller(boolean seller) {
            this.seller = seller;
        }

        public int getSendRefundViewMinute() {
            return sendRefundViewMinute;
        }

        public void setSendRefundViewMinute(int sendRefundViewMinute) {
            this.sendRefundViewMinute = sendRefundViewMinute;
        }

        public int getSourceType() {
            return sourceType;
        }

        public void setSourceType(int sourceType) {
            this.sourceType = sourceType;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * firstPic : https://file.taojianlou.com/Android/goods/img20200801/1596270668108.png?image=900,598
             * instanceCode : 09147351e754a7996b8479cdee9611d0
             * num : 1
             * price : 6.00
             * productInstanceId : 1225
             * title : 安卓测试。。。五分钟
             * total : 6.00
             */

            private String firstPic;
            private String instanceCode;
            private int num;
            private String price;
            private int productInstanceId;
            private String title;
            private String total;

            public String getFirstPic() {
                return firstPic;
            }

            public void setFirstPic(String firstPic) {
                this.firstPic = firstPic;
            }

            public String getInstanceCode() {
                return instanceCode;
            }

            public void setInstanceCode(String instanceCode) {
                this.instanceCode = instanceCode;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getProductInstanceId() {
                return productInstanceId;
            }

            public void setProductInstanceId(int productInstanceId) {
                this.productInstanceId = productInstanceId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }
        }
    }

    public static class ResultBean {
        /**
         * code : 0
         * message : 请求成功
         * success : true
         * timestamp : 1596271687582
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
