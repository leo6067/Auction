package com.leo.auction.ui.order.model;

import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.order.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/8/4
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class SubsidyJson {


    private List<SubsidyBean> subsidy;

    public List<SubsidyBean> getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(List<SubsidyBean> subsidy) {
        this.subsidy = subsidy;
    }

    public static class SubsidyBean {
        /**
         * orderCode : 132123131
         * subsidyLimit : 12
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
