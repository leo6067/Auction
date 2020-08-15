package com.leo.auction.ui.main.mine.model;

import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;

import java.util.HashMap;
import java.util.List;

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
public class FanModel {


    /**
     * data : [{"follow":false,"followEnable":true,"user":{"auctionCoinBalance":"0.00","auctionCoinNum":0,"auctionFansNum":0,"balance":"0.00","dialogConnect":false,"fansNum":0,"followNum":0,"headImg":"https://file.taojianlou.com/Android/user/img20200803/1596434438608.png?image=250,250","id":1998,"level":0,"nickname":"支付宝小店","online":false,"payPwd":false,"rate":0,"score":0,"sellerLevel":0,"sellerScore":0,"storeEnable":false,"warrant":"0.00"}},{"follow":true,"followEnable":true,"user":{"auctionCoinBalance":"0.00","auctionCoinNum":0,"auctionFansNum":0,"balance":"0.00","dialogConnect":false,"fansNum":0,"followNum":0,"headImg":"https://file.taojianlou.com/ut/user/a0ca63942b933ffffca8b73f0ba005f2.jpg","id":1969,"level":1,"nickname":"哈哈哈有意思","online":false,"payPwd":false,"rate":0,"score":0,"sellerLevel":2,"sellerScore":0,"storeEnable":false,"warrant":"0.00"}},{"follow":false,"followEnable":true,"user":{"auctionCoinBalance":"0.00","auctionCoinNum":0,"auctionFansNum":0,"balance":"0.00","dialogConnect":false,"fansNum":0,"followNum":0,"headImg":"https://file.taojianlou.com/ut/product/2F8E424D70194899A03C7C6CB52A1B1F.jpg","id":1972,"level":2,"nickname":"这些不是问题","online":false,"payPwd":false,"rate":0,"score":0,"sellerLevel":0,"sellerScore":0,"storeEnable":false,"warrant":"0.00"}},{"follow":true,"followEnable":true,"user":{"auctionCoinBalance":"0.00","auctionCoinNum":0,"auctionFansNum":0,"balance":"0.00","dialogConnect":false,"fansNum":0,"followNum":0,"headImg":"https://file.taojianlou.com/ut/user/2D33BF879AD5417580FA7C6B7AA52E73.jpg","id":69,"level":0,"nickname":"����****������","online":false,"payPwd":false,"rate":0,"score":0,"sellerLevel":1,"sellerScore":0,"storeEnable":false,"warrant":"0.00"}},{"follow":false,"followEnable":true,"user":{"auctionCoinBalance":"0.00","auctionCoinNum":0,"auctionFansNum":0,"balance":"0.00","dialogConnect":false,"fansNum":0,"followNum":0,"headImg":"https://file.taojianlou.com/Android/user/img20200730/1596095682994.png?image=430,430","id":1964,"level":0,"nickname":"修改过的名","online":false,"payPwd":false,"rate":0,"score":0,"sellerLevel":2,"sellerScore":0,"storeEnable":false,"warrant":"0.00"}},{"follow":true,"followEnable":true,"user":{"auctionCoinBalance":"0.00","auctionCoinNum":0,"auctionFansNum":0,"balance":"0.00","dialogConnect":false,"fansNum":0,"followNum":0,"headImg":"https://file.taojianlou.com/ut/product/06CF8B3E32B54DDA81CB7E21E44C22E4.jpg","id":71,"level":10,"nickname":"众恒老根1","online":false,"payPwd":false,"rate":0,"score":0,"sellerLevel":0,"sellerScore":0,"storeEnable":false,"warrant":"0.00"}}]
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1597218831969}
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
         * timestamp : 1597218831969
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
         * follow : false
         * followEnable : true
         * user : {"auctionCoinBalance":"0.00","auctionCoinNum":0,"auctionFansNum":0,"balance":"0.00","dialogConnect":false,"fansNum":0,"followNum":0,"headImg":"https://file.taojianlou.com/Android/user/img20200803/1596434438608.png?image=250,250","id":1998,"level":0,"nickname":"支付宝小店","online":false,"payPwd":false,"rate":0,"score":0,"sellerLevel":0,"sellerScore":0,"storeEnable":false,"warrant":"0.00"}
         */

        private boolean follow;
        private boolean followEnable;
        private UserBean user;

        public boolean isFollow() {
            return follow;
        }

        public void setFollow(boolean follow) {
            this.follow = follow;
        }

        public boolean isFollowEnable() {
            return followEnable;
        }

        public void setFollowEnable(boolean followEnable) {
            this.followEnable = followEnable;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * auctionCoinBalance : 0.00
             * auctionCoinNum : 0
             * auctionFansNum : 0
             * balance : 0.00
             * dialogConnect : false
             * fansNum : 0
             * followNum : 0
             * headImg : https://file.taojianlou.com/Android/user/img20200803/1596434438608.png?image=250,250
             * id : 1998
             * level : 0
             * nickname : 支付宝小店
             * online : false
             * payPwd : false
             * rate : 0.0
             * score : 0
             * sellerLevel : 0
             * sellerScore : 0
             * storeEnable : false
             * warrant : 0.00
             */

            private String auctionCoinBalance;
            private int auctionCoinNum;
            private int auctionFansNum;
            private String balance;
            private boolean dialogConnect;
            private int fansNum;
            private int followNum;
            private String headImg;
            private int id;
            private int level;
            private String nickname;
            private boolean online;
            private boolean payPwd;
            private double rate;
            private int score;
            private int sellerLevel;
            private int sellerScore;
            private boolean storeEnable;
            private String warrant;

            public String getAuctionCoinBalance() {
                return auctionCoinBalance;
            }

            public void setAuctionCoinBalance(String auctionCoinBalance) {
                this.auctionCoinBalance = auctionCoinBalance;
            }

            public int getAuctionCoinNum() {
                return auctionCoinNum;
            }

            public void setAuctionCoinNum(int auctionCoinNum) {
                this.auctionCoinNum = auctionCoinNum;
            }

            public int getAuctionFansNum() {
                return auctionFansNum;
            }

            public void setAuctionFansNum(int auctionFansNum) {
                this.auctionFansNum = auctionFansNum;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public boolean isDialogConnect() {
                return dialogConnect;
            }

            public void setDialogConnect(boolean dialogConnect) {
                this.dialogConnect = dialogConnect;
            }

            public int getFansNum() {
                return fansNum;
            }

            public void setFansNum(int fansNum) {
                this.fansNum = fansNum;
            }

            public int getFollowNum() {
                return followNum;
            }

            public void setFollowNum(int followNum) {
                this.followNum = followNum;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public boolean isOnline() {
                return online;
            }

            public void setOnline(boolean online) {
                this.online = online;
            }

            public boolean isPayPwd() {
                return payPwd;
            }

            public void setPayPwd(boolean payPwd) {
                this.payPwd = payPwd;
            }

            public double getRate() {
                return rate;
            }

            public void setRate(double rate) {
                this.rate = rate;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public int getSellerLevel() {
                return sellerLevel;
            }

            public void setSellerLevel(int sellerLevel) {
                this.sellerLevel = sellerLevel;
            }

            public int getSellerScore() {
                return sellerScore;
            }

            public void setSellerScore(int sellerScore) {
                this.sellerScore = sellerScore;
            }

            public boolean isStoreEnable() {
                return storeEnable;
            }

            public void setStoreEnable(boolean storeEnable) {
                this.storeEnable = storeEnable;
            }

            public String getWarrant() {
                return warrant;
            }

            public void setWarrant(String warrant) {
                this.warrant = warrant;
            }
        }
    }

    public static void httpGetFan(int type, String keyword, int pageNum, HttpRequest.HttpCallback httpCallback){

        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("type",type+"");
        hashMap.put("keyword",keyword);
        hashMap.put("pageNum",pageNum+"");
        hashMap.put("pageSize", Constants.Var.LIST_NUMBER);

        HttpRequest.httpGetString(Constants.Api.FANS_URL,hashMap,httpCallback);


    }
}
