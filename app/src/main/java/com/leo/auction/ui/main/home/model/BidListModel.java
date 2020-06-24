package com.leo.auction.ui.main.home.model;

import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.home.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/6/23
 * 描    述： 出价列表
 * 修    改：
 * ===============================================
 */
public class BidListModel {


    /**
     * data : [{"userAccountId":"用户标识","nickname":"昵称","headImg":"头像","level":"等级","bidPrice":"出价价格","createTime":"出价时间","ensureMoney":"12.00"}]
     * result : {"success":true,"message":"请求成功"}
     */

    private ResultBean result;
    private List<GoodsDetailModel.DataBean.BidBean> data;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public List<GoodsDetailModel.DataBean.BidBean> getData() {
        return data;
    }

    public void setData(List<GoodsDetailModel.DataBean.BidBean> data) {
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
         * userAccountId : 用户标识
         * nickname : 昵称
         * headImg : 头像
         * level : 等级
         * bidPrice : 出价价格
         * createTime : 出价时间
         * ensureMoney : 12.00
         */

        private String userAccountId;
        private String nickname;
        private String headImg;
        private String level;
        private String bidPrice;
        private String createTime;
        private String ensureMoney;

        public String getUserAccountId() {
            return userAccountId;
        }

        public void setUserAccountId(String userAccountId) {
            this.userAccountId = userAccountId;
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

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getBidPrice() {
            return bidPrice;
        }

        public void setBidPrice(String bidPrice) {
            this.bidPrice = bidPrice;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getEnsureMoney() {
            return ensureMoney;
        }

        public void setEnsureMoney(String ensureMoney) {
            this.ensureMoney = ensureMoney;
        }
    }
}
