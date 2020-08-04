package com.leo.auction.ui.order.model;

import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.order.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/8/3
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class OrderExtendContentJson {


    /**
     * receiver : 沙朗
     * receiverAreaName : 广东省中山市中山市西区街道
     * receiverCode : 000000
     * receiverMobile : 15800180086
     * subsidy : [{"orderCode":"1302085527446238407766","subsidyLimit":"1"}]
     */

    private String receiver;
    private String receiverAreaName;
    private String receiverCode;
    private String receiverMobile;
    private List<SubsidyBean> subsidy;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverAreaName() {
        return receiverAreaName;
    }

    public void setReceiverAreaName(String receiverAreaName) {
        this.receiverAreaName = receiverAreaName;
    }

    public String getReceiverCode() {
        return receiverCode;
    }

    public void setReceiverCode(String receiverCode) {
        this.receiverCode = receiverCode;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public List<SubsidyBean> getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(List<SubsidyBean> subsidy) {
        this.subsidy = subsidy;
    }

    public static class SubsidyBean {
        /**
         * orderCode : 1302085527446238407766
         * subsidyLimit : 1
         */

        private String orderCode;
        private String subsidyLimit;

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public String getSubsidyLimit() {
            return subsidyLimit;
        }

        public void setSubsidyLimit(String subsidyLimit) {
            this.subsidyLimit = subsidyLimit;
        }
    }
}
