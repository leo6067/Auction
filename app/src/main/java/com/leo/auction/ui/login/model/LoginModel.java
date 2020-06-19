package com.leo.auction.ui.login.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leo.auction.base.Constants;
import com.leo.auction.net.CustomerJsonCallBack;
import com.leo.auction.net.HttpRequest;

import java.util.HashMap;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.ui.mine.model
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/10/31 0031
 * 描    述：
 * ================================================
 */
public class LoginModel {


    /**
     * data : {"token":"6b66da91a8db3f47608dad2893b487ff","user":{"address":{"shipAddress":{"address":"福建省莆田市仙游县榜头镇天易世博","code":"000000","id":271,"linkman":"谢伟杰","phone":"17750656067","type":0},"returnAddress":{"address":"福建省莆田市仙游县六月","code":"000000","id":273,"linkman":"六月","phone":"17750656067","type":1}},"auctionCoinBalance":"0","auctionCoinNum":0,"balance":"0","balanceExempt":"","dialogConnect":false,"fansNum":6,"followNum":0,"headImg":"https://file.taojianlou.com/ut/product/151E0BC510964962BCB5ECEDD03E5A59.jpg","hxId":"e64cbf24999560e9ce88726005cac2e5","id":1998,"idCard":"3****************8","level":0,"limitType":0,"nickname":"伟龙珠","payPwd":false,"phone":"17750656067","rate":0,"score":0,"sellerLevel":0,"sellerScore":0,"storeEnable":true,"type":2,"userId":"2004271657rdLApc","username":"leo","warrant":"0"}}
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1592399660978}
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
         * token : 6b66da91a8db3f47608dad2893b487ff
         * user : {"address":{"shipAddress":{"address":"福建省莆田市仙游县榜头镇天易世博","code":"000000","id":271,"linkman":"谢伟杰","phone":"17750656067","type":0},"returnAddress":{"address":"福建省莆田市仙游县六月","code":"000000","id":273,"linkman":"六月","phone":"17750656067","type":1}},"auctionCoinBalance":"0","auctionCoinNum":0,"balance":"0","balanceExempt":"","dialogConnect":false,"fansNum":6,"followNum":0,"headImg":"https://file.taojianlou.com/ut/product/151E0BC510964962BCB5ECEDD03E5A59.jpg","hxId":"e64cbf24999560e9ce88726005cac2e5","id":1998,"idCard":"3****************8","level":0,"limitType":0,"nickname":"伟龙珠","payPwd":false,"phone":"17750656067","rate":0,"score":0,"sellerLevel":0,"sellerScore":0,"storeEnable":true,"type":2,"userId":"2004271657rdLApc","username":"leo","warrant":"0"}
         */

        private String token;
        private UserBean user;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * address : {"shipAddress":{"address":"福建省莆田市仙游县榜头镇天易世博","code":"000000","id":271,"linkman":"谢伟杰","phone":"17750656067","type":0},"returnAddress":{"address":"福建省莆田市仙游县六月","code":"000000","id":273,"linkman":"六月","phone":"17750656067","type":1}}
             * auctionCoinBalance : 0
             * auctionCoinNum : 0
             * balance : 0
             * balanceExempt :
             * dialogConnect : false
             * fansNum : 6
             * followNum : 0
             * headImg : https://file.taojianlou.com/ut/product/151E0BC510964962BCB5ECEDD03E5A59.jpg
             * hxId : e64cbf24999560e9ce88726005cac2e5
             * id : 1998
             * idCard : 3****************8
             * level : 0
             * limitType : 0
             * nickname : 伟龙珠
             * payPwd : false
             * phone : 17750656067
             * rate : 0.0
             * score : 0
             * sellerLevel : 0
             * sellerScore : 0
             * storeEnable : true
             * type : 2
             * userId : 2004271657rdLApc
             * username : leo
             * warrant : 0
             */

            private AddressBean address;
            private String auctionCoinBalance;
            private int auctionCoinNum;
            private String balance;
            private String balanceExempt;
            private boolean dialogConnect;
            private int fansNum;
            private int followNum;
            private String headImg;
            private String hxId;
            private int id;
            private String idCard;
            private int level;
            private int limitType;
            private String nickname;
            private boolean payPwd;
            private String phone;
            private double rate;
            private int score;
            private int sellerLevel;
            private int sellerScore;
            private boolean storeEnable;
            private int type;
            private String userId;
            private String username;
            private String warrant;

            public AddressBean getAddress() {
                return address;
            }

            public void setAddress(AddressBean address) {
                this.address = address;
            }

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

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public String getBalanceExempt() {
                return balanceExempt;
            }

            public void setBalanceExempt(String balanceExempt) {
                this.balanceExempt = balanceExempt;
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

            public String getHxId() {
                return hxId;
            }

            public void setHxId(String hxId) {
                this.hxId = hxId;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIdCard() {
                return idCard;
            }

            public void setIdCard(String idCard) {
                this.idCard = idCard;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getLimitType() {
                return limitType;
            }

            public void setLimitType(int limitType) {
                this.limitType = limitType;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public boolean isPayPwd() {
                return payPwd;
            }

            public void setPayPwd(boolean payPwd) {
                this.payPwd = payPwd;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
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

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getWarrant() {
                return warrant;
            }

            public void setWarrant(String warrant) {
                this.warrant = warrant;
            }

            public static class AddressBean {
                /**
                 * shipAddress : {"address":"福建省莆田市仙游县榜头镇天易世博","code":"000000","id":271,"linkman":"谢伟杰","phone":"17750656067","type":0}
                 * returnAddress : {"address":"福建省莆田市仙游县六月","code":"000000","id":273,"linkman":"六月","phone":"17750656067","type":1}
                 */

                private ShipAddressBean shipAddress;
                private ReturnAddressBean returnAddress;

                public ShipAddressBean getShipAddress() {
                    return shipAddress;
                }

                public void setShipAddress(ShipAddressBean shipAddress) {
                    this.shipAddress = shipAddress;
                }

                public ReturnAddressBean getReturnAddress() {
                    return returnAddress;
                }

                public void setReturnAddress(ReturnAddressBean returnAddress) {
                    this.returnAddress = returnAddress;
                }

                public static class ShipAddressBean {
                    /**
                     * address : 福建省莆田市仙游县榜头镇天易世博
                     * code : 000000
                     * id : 271
                     * linkman : 谢伟杰
                     * phone : 17750656067
                     * type : 0
                     */

                    private String address;
                    private String code;
                    private int id;
                    private String linkman;
                    private String phone;
                    private int type;

                    public String getAddress() {
                        return address;
                    }

                    public void setAddress(String address) {
                        this.address = address;
                    }

                    public String getCode() {
                        return code;
                    }

                    public void setCode(String code) {
                        this.code = code;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getLinkman() {
                        return linkman;
                    }

                    public void setLinkman(String linkman) {
                        this.linkman = linkman;
                    }

                    public String getPhone() {
                        return phone;
                    }

                    public void setPhone(String phone) {
                        this.phone = phone;
                    }

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }
                }

                public static class ReturnAddressBean {
                    /**
                     * address : 福建省莆田市仙游县六月
                     * code : 000000
                     * id : 273
                     * linkman : 六月
                     * phone : 17750656067
                     * type : 1
                     */

                    private String address;
                    private String code;
                    private int id;
                    private String linkman;
                    private String phone;
                    private int type;

                    public String getAddress() {
                        return address;
                    }

                    public void setAddress(String address) {
                        this.address = address;
                    }

                    public String getCode() {
                        return code;
                    }

                    public void setCode(String code) {
                        this.code = code;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getLinkman() {
                        return linkman;
                    }

                    public void setLinkman(String linkman) {
                        this.linkman = linkman;
                    }

                    public String getPhone() {
                        return phone;
                    }

                    public void setPhone(String phone) {
                        this.phone = phone;
                    }

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }
                }
            }
        }
    }

    public static class ResultBean {
        /**
         * code : 0
         * message : 请求成功
         * success : true
         * timestamp : 1592399660978
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
