package com.leo.auction.ui.order.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;

import java.util.HashMap;
import java.util.List;

public class OrderListModel {


    /**
     * data : [{"orderCode":"2826325325635265362","nickname":"我是昵称","headImg":"我是头像","status":1,"payment":"1.00","delayed":true,"expire":2837266352,"delayMinute":8,"faceTrade":true,"rejectFaceTrade":true,"delaySend":true,"remindSend":true,"rejectDelaySend":true,"buyerCancelRefund":true,"paymentTime":617267162,"createTime":23131313,"consignTime":1231313,"faceTradeMinute":5,"distributeType":1,"addressUpdate":0,"expressUpdate":0,"remindSendViewMinute":1,"noSendRefundViewMinute":2,"sendRefundViewMinute":2,"delayConfirmTakeViewMinute":2,"items":[{"orderId":1,"title":"标题","firstPic":"主图","num":12,"price":"单价","total":"总价","instanceCode":"131jhdsjhdjsdsd"}],"refund":{"orderId":1,"status":1,"refundReason":"退款原因","refundTime":1231311}}]
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

    public static class DataBean implements Parcelable {
        /**
         * orderCode : 2826325325635265362
         * nickname : 我是昵称
         * headImg : 我是头像
         * status : 1
         * payment : 1.00
         * delayed : true
         * expire : 2837266352
         * delayMinute : 8
         * faceTrade : true
         * rejectFaceTrade : true
         * delaySend : true
         * remindSend : true
         * rejectDelaySend : true
         * buyerCancelRefund : true
         * paymentTime : 617267162
         * createTime : 23131313
         * consignTime : 1231313
         * faceTradeMinute : 5
         * distributeType : 1
         * addressUpdate : 0
         * expressUpdate : 0
         * remindSendViewMinute : 1
         * noSendRefundViewMinute : 2
         * sendRefundViewMinute : 2
         * delayConfirmTakeViewMinute : 2
         * items : [{"orderId":1,"title":"标题","firstPic":"主图","num":12,"price":"单价","total":"总价","instanceCode":"131jhdsjhdjsdsd"}]
         * refund : {"orderId":1,"status":1,"refundReason":"退款原因","refundTime":1231311}
         */

        private String orderCode;
        private String nickname;
        private String headImg;
        private String status;
        private String payment;
        private boolean delayed;
        private long expire;
        private int delayMinute;
        private boolean faceTrade;
        private boolean rejectFaceTrade;
        private boolean delaySend;
        private boolean remindSend;
        private boolean rejectDelaySend;
        private boolean buyerCancelRefund;
        private boolean delayConfirmTake;



        private int paymentTime;
        private long createTime;
        private int consignTime;
        private int faceTradeMinute;
        private int distributeType;
        private int addressUpdate;
        private int expressUpdate;
        private int remindSendViewMinute;
        private int noSendRefundViewMinute;
        private int sendRefundViewMinute;
        private int delayConfirmTakeViewMinute;
        private RefundBean refund;
        private List<ItemsBean> items;

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            orderCode = in.readString();
            nickname = in.readString();
            headImg = in.readString();
            status = in.readString();
            payment = in.readString();
            delayed = in.readByte() != 0;
            expire = in.readLong();
            delayMinute = in.readInt();
            faceTrade = in.readByte() != 0;
            rejectFaceTrade = in.readByte() != 0;
            delaySend = in.readByte() != 0;
            remindSend = in.readByte() != 0;
            rejectDelaySend = in.readByte() != 0;
            buyerCancelRefund = in.readByte() != 0;
            delayConfirmTake = in.readByte() != 0;
            paymentTime = in.readInt();
            createTime = in.readLong();
            consignTime = in.readInt();
            faceTradeMinute = in.readInt();
            distributeType = in.readInt();
            addressUpdate = in.readInt();
            expressUpdate = in.readInt();
            remindSendViewMinute = in.readInt();
            noSendRefundViewMinute = in.readInt();
            sendRefundViewMinute = in.readInt();
            delayConfirmTakeViewMinute = in.readInt();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public boolean isDelayConfirmTake() {
            return delayConfirmTake;
        }

        public void setDelayConfirmTake(boolean delayConfirmTake) {
            this.delayConfirmTake = delayConfirmTake;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }

        public boolean isDelayed() {
            return delayed;
        }

        public void setDelayed(boolean delayed) {
            this.delayed = delayed;
        }

        public long getExpire() {
            return expire;
        }

        public void setExpire(long expire) {
            this.expire = expire;
        }

        public int getDelayMinute() {
            return delayMinute;
        }

        public void setDelayMinute(int delayMinute) {
            this.delayMinute = delayMinute;
        }

        public boolean isFaceTrade() {
            return faceTrade;
        }

        public void setFaceTrade(boolean faceTrade) {
            this.faceTrade = faceTrade;
        }

        public boolean isRejectFaceTrade() {
            return rejectFaceTrade;
        }

        public void setRejectFaceTrade(boolean rejectFaceTrade) {
            this.rejectFaceTrade = rejectFaceTrade;
        }

        public boolean isDelaySend() {
            return delaySend;
        }

        public void setDelaySend(boolean delaySend) {
            this.delaySend = delaySend;
        }

        public boolean isRemindSend() {
            return remindSend;
        }

        public void setRemindSend(boolean remindSend) {
            this.remindSend = remindSend;
        }

        public boolean isRejectDelaySend() {
            return rejectDelaySend;
        }

        public void setRejectDelaySend(boolean rejectDelaySend) {
            this.rejectDelaySend = rejectDelaySend;
        }

        public boolean isBuyerCancelRefund() {
            return buyerCancelRefund;
        }

        public void setBuyerCancelRefund(boolean buyerCancelRefund) {
            this.buyerCancelRefund = buyerCancelRefund;
        }

        public int getPaymentTime() {
            return paymentTime;
        }

        public void setPaymentTime(int paymentTime) {
            this.paymentTime = paymentTime;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getConsignTime() {
            return consignTime;
        }

        public void setConsignTime(int consignTime) {
            this.consignTime = consignTime;
        }

        public int getFaceTradeMinute() {
            return faceTradeMinute;
        }

        public void setFaceTradeMinute(int faceTradeMinute) {
            this.faceTradeMinute = faceTradeMinute;
        }

        public int getDistributeType() {
            return distributeType;
        }

        public void setDistributeType(int distributeType) {
            this.distributeType = distributeType;
        }

        public int getAddressUpdate() {
            return addressUpdate;
        }

        public void setAddressUpdate(int addressUpdate) {
            this.addressUpdate = addressUpdate;
        }

        public int getExpressUpdate() {
            return expressUpdate;
        }

        public void setExpressUpdate(int expressUpdate) {
            this.expressUpdate = expressUpdate;
        }

        public int getRemindSendViewMinute() {
            return remindSendViewMinute;
        }

        public void setRemindSendViewMinute(int remindSendViewMinute) {
            this.remindSendViewMinute = remindSendViewMinute;
        }

        public int getNoSendRefundViewMinute() {
            return noSendRefundViewMinute;
        }

        public void setNoSendRefundViewMinute(int noSendRefundViewMinute) {
            this.noSendRefundViewMinute = noSendRefundViewMinute;
        }

        public int getSendRefundViewMinute() {
            return sendRefundViewMinute;
        }

        public void setSendRefundViewMinute(int sendRefundViewMinute) {
            this.sendRefundViewMinute = sendRefundViewMinute;
        }

        public int getDelayConfirmTakeViewMinute() {
            return delayConfirmTakeViewMinute;
        }

        public void setDelayConfirmTakeViewMinute(int delayConfirmTakeViewMinute) {
            this.delayConfirmTakeViewMinute = delayConfirmTakeViewMinute;
        }

        public RefundBean getRefund() {
            return refund;
        }

        public void setRefund(RefundBean refund) {
            this.refund = refund;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(orderCode);
            dest.writeString(nickname);
            dest.writeString(headImg);
            dest.writeString(status);
            dest.writeString(payment);
            dest.writeByte((byte) (delayed ? 1 : 0));
            dest.writeLong(expire);
            dest.writeInt(delayMinute);
            dest.writeByte((byte) (faceTrade ? 1 : 0));
            dest.writeByte((byte) (rejectFaceTrade ? 1 : 0));
            dest.writeByte((byte) (delaySend ? 1 : 0));
            dest.writeByte((byte) (remindSend ? 1 : 0));
            dest.writeByte((byte) (rejectDelaySend ? 1 : 0));
            dest.writeByte((byte) (buyerCancelRefund ? 1 : 0));
            dest.writeByte((byte) (delayConfirmTake ? 1 : 0));
            dest.writeInt(paymentTime);
            dest.writeLong(createTime);
            dest.writeInt(consignTime);
            dest.writeInt(faceTradeMinute);
            dest.writeInt(distributeType);
            dest.writeInt(addressUpdate);
            dest.writeInt(expressUpdate);
            dest.writeInt(remindSendViewMinute);
            dest.writeInt(noSendRefundViewMinute);
            dest.writeInt(sendRefundViewMinute);
            dest.writeInt(delayConfirmTakeViewMinute);
        }

        public static class RefundBean {
            /**
             * orderId : 1
             * status : 1
             * refundReason : 退款原因
             * refundTime : 1231311
             */

            private int orderId;
            private int status;
            private String refundReason;
            private int refundTime;

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getRefundReason() {
                return refundReason;
            }

            public void setRefundReason(String refundReason) {
                this.refundReason = refundReason;
            }

            public int getRefundTime() {
                return refundTime;
            }

            public void setRefundTime(int refundTime) {
                this.refundTime = refundTime;
            }
        }

        public static class ItemsBean {
            /**
             * orderId : 1
             * title : 标题
             * firstPic : 主图
             * num : 12
             * price : 单价
             * total : 总价
             * instanceCode : 131jhdsjhdjsdsd
             */

            private int orderId;
            private String title;
            private String firstPic;
            private int num;
            private String price;
            private String total;
            private String instanceCode;

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
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

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getInstanceCode() {
                return instanceCode;
            }

            public void setInstanceCode(String instanceCode) {
                this.instanceCode = instanceCode;
            }
        }
    }




    public static void httpOrderList(  //type 买家 卖家
                                       int pageNum, String keyword, String type, String status,
                                       HttpRequest.HttpCallback httpCallback){

        HashMap<String, String> params = new HashMap<>();

        params.put("pageNum",pageNum+"");
        params.put("pageSize",Constants.Var.LIST_NUMBER);
        params.put("keyword",keyword);
        params.put("type",type);//  1为商家身份查询订单，非1或者不传即为用户身份查询订单
        params.put("status",status);

        HttpRequest.httpGetString(Constants.Api.ORDER_LIST_URL,params,httpCallback);



    }
}
