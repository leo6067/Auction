package com.leo.auction.ui.login.model;

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
     * data : {"token":"45169a62a734256318fcf52bb3e4e1a9","user":{"address":{"shipAddress":{"address":"北京市北京市东城区榜头镇天易世博","code":"000000","id":271,"linkman":"谢伟杰","phone":"17750656067","type":0},"returnAddress":{"address":"吉林省长春市南关区2632362212","code":"000000","id":357,"linkman":"6667","phone":"17750657777","type":1}},"appToken":"90d32ca7ac15052426289621fefea620","auctionCoinBalance":"0.00","auctionCoinNum":0,"auctionFansNum":2,"balance":"185.00","balanceExempt":"","buyerOrderCount":{"noPayNum":0,"receiveNum":0,"sendNum":5,"serviceNum":0,"type":1},"dialogConnect":true,"exclusiveFansNum":6,"fansNum":8,"followNum":3,"h5Token":"1960f56a378cb9ba0a83a226a28b72a5","headImg":"https://file.taojianlou.com/Android/user/img20200803/1596434438608.png?image=250,250","hxId":"e64cbf24999560e9ce88726005cac2e5","id":1998,"idCard":"3****************8","instanceNum":0,"level":0,"limitProductFansNum":50,"limitType":0,"mpToken":"9cbec1b0c60c699415d1e843966f011b","nestedToken":"b084a212d4779662d848dcabf5ed8b91","nickname":"支付宝小店","online":false,"payPwd":true,"phone":"17750656067","rate":0,"score":0,"sellerLevel":0,"sellerOrderCount":{"noPayNum":0,"receiveNum":0,"sendNum":0,"serviceNum":0,"type":2},"sellerScore":0,"storeEnable":true,"subsidyMoney":"83","toolToken":"4d2a6588055c639e930a5cfda6c206fb","type":2,"userId":"2004271657rdLApc","username":"谢伟杰","warrant":"0.00","webToken":"3339b6114e84be24832e1d8369498ebd"}}
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1597483061545}
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
         * token : 45169a62a734256318fcf52bb3e4e1a9
         * user : {"address":{"shipAddress":{"address":"北京市北京市东城区榜头镇天易世博","code":"000000","id":271,"linkman":"谢伟杰","phone":"17750656067","type":0},"returnAddress":{"address":"吉林省长春市南关区2632362212","code":"000000","id":357,"linkman":"6667","phone":"17750657777","type":1}},"appToken":"90d32ca7ac15052426289621fefea620","auctionCoinBalance":"0.00","auctionCoinNum":0,"auctionFansNum":2,"balance":"185.00","balanceExempt":"","buyerOrderCount":{"noPayNum":0,"receiveNum":0,"sendNum":5,"serviceNum":0,"type":1},"dialogConnect":true,"exclusiveFansNum":6,"fansNum":8,"followNum":3,"h5Token":"1960f56a378cb9ba0a83a226a28b72a5","headImg":"https://file.taojianlou.com/Android/user/img20200803/1596434438608.png?image=250,250","hxId":"e64cbf24999560e9ce88726005cac2e5","id":1998,"idCard":"3****************8","instanceNum":0,"level":0,"limitProductFansNum":50,"limitType":0,"mpToken":"9cbec1b0c60c699415d1e843966f011b","nestedToken":"b084a212d4779662d848dcabf5ed8b91","nickname":"支付宝小店","online":false,"payPwd":true,"phone":"17750656067","rate":0,"score":0,"sellerLevel":0,"sellerOrderCount":{"noPayNum":0,"receiveNum":0,"sendNum":0,"serviceNum":0,"type":2},"sellerScore":0,"storeEnable":true,"subsidyMoney":"83","toolToken":"4d2a6588055c639e930a5cfda6c206fb","type":2,"userId":"2004271657rdLApc","username":"谢伟杰","warrant":"0.00","webToken":"3339b6114e84be24832e1d8369498ebd"}
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
             * address : {"shipAddress":{"address":"北京市北京市东城区榜头镇天易世博","code":"000000","id":271,"linkman":"谢伟杰","phone":"17750656067","type":0},"returnAddress":{"address":"吉林省长春市南关区2632362212","code":"000000","id":357,"linkman":"6667","phone":"17750657777","type":1}}
             * appToken : 90d32ca7ac15052426289621fefea620
             * auctionCoinBalance : 0.00
             * auctionCoinNum : 0
             * auctionFansNum : 2
             * balance : 185.00
             * balanceExempt :
             * buyerOrderCount : {"noPayNum":0,"receiveNum":0,"sendNum":5,"serviceNum":0,"type":1}
             * dialogConnect : true
             * exclusiveFansNum : 6
             * fansNum : 8
             * followNum : 3
             * h5Token : 1960f56a378cb9ba0a83a226a28b72a5
             * headImg : https://file.taojianlou.com/Android/user/img20200803/1596434438608.png?image=250,250
             * hxId : e64cbf24999560e9ce88726005cac2e5
             * id : 1998
             * idCard : 3****************8
             * instanceNum : 0
             * level : 0
             * limitProductFansNum : 50
             * limitType : 0
             * mpToken : 9cbec1b0c60c699415d1e843966f011b
             * nestedToken : b084a212d4779662d848dcabf5ed8b91
             * nickname : 支付宝小店
             * online : false
             * payPwd : true
             * phone : 17750656067
             * rate : 0.0
             * score : 0
             * sellerLevel : 0
             * sellerOrderCount : {"noPayNum":0,"receiveNum":0,"sendNum":0,"serviceNum":0,"type":2}
             * sellerScore : 0
             * storeEnable : true
             * subsidyMoney : 83
             * toolToken : 4d2a6588055c639e930a5cfda6c206fb
             * type : 2
             * userId : 2004271657rdLApc
             * username : 谢伟杰
             * warrant : 0.00
             * webToken : 3339b6114e84be24832e1d8369498ebd
             */

            private AddressBean address;
            private String appToken;
            private String auctionCoinBalance;
            private int auctionCoinNum;
            private int auctionFansNum;
            private String balance;
            private String balanceExempt;
            private BuyerOrderCountBean buyerOrderCount;
            private boolean dialogConnect;
            private int exclusiveFansNum;
            private int fansNum;
            private int followNum;
            private String h5Token;
            private String headImg;
            private String hxId;
            private int id;
            private String idCard;
            private int instanceNum;
            private int level;
            private int limitProductFansNum;
            private int limitType;
            private String mpToken;
            private String nestedToken;
            private String nickname;
            private boolean online;
            private boolean payPwd;
            private String phone;
            private double rate;
            private int score;
            private int sellerLevel;
            private SellerOrderCountBean sellerOrderCount;
            private int sellerScore;
            private boolean storeEnable;
            private String subsidyMoney;
            private String toolToken;
            private int type;
            private String userId;
            private String username;
            private String warrant;
            private String webToken;

            public AddressBean getAddress() {
                return address;
            }

            public void setAddress(AddressBean address) {
                this.address = address;
            }

            public String getAppToken() {
                return appToken;
            }

            public void setAppToken(String appToken) {
                this.appToken = appToken;
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

            public String getBalanceExempt() {
                return balanceExempt;
            }

            public void setBalanceExempt(String balanceExempt) {
                this.balanceExempt = balanceExempt;
            }

            public BuyerOrderCountBean getBuyerOrderCount() {
                return buyerOrderCount;
            }

            public void setBuyerOrderCount(BuyerOrderCountBean buyerOrderCount) {
                this.buyerOrderCount = buyerOrderCount;
            }

            public boolean isDialogConnect() {
                return dialogConnect;
            }

            public void setDialogConnect(boolean dialogConnect) {
                this.dialogConnect = dialogConnect;
            }

            public int getExclusiveFansNum() {
                return exclusiveFansNum;
            }

            public void setExclusiveFansNum(int exclusiveFansNum) {
                this.exclusiveFansNum = exclusiveFansNum;
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

            public String getH5Token() {
                return h5Token;
            }

            public void setH5Token(String h5Token) {
                this.h5Token = h5Token;
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

            public int getInstanceNum() {
                return instanceNum;
            }

            public void setInstanceNum(int instanceNum) {
                this.instanceNum = instanceNum;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getLimitProductFansNum() {
                return limitProductFansNum;
            }

            public void setLimitProductFansNum(int limitProductFansNum) {
                this.limitProductFansNum = limitProductFansNum;
            }

            public int getLimitType() {
                return limitType;
            }

            public void setLimitType(int limitType) {
                this.limitType = limitType;
            }

            public String getMpToken() {
                return mpToken;
            }

            public void setMpToken(String mpToken) {
                this.mpToken = mpToken;
            }

            public String getNestedToken() {
                return nestedToken;
            }

            public void setNestedToken(String nestedToken) {
                this.nestedToken = nestedToken;
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

            public SellerOrderCountBean getSellerOrderCount() {
                return sellerOrderCount;
            }

            public void setSellerOrderCount(SellerOrderCountBean sellerOrderCount) {
                this.sellerOrderCount = sellerOrderCount;
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

            public String getSubsidyMoney() {
                return subsidyMoney;
            }

            public void setSubsidyMoney(String subsidyMoney) {
                this.subsidyMoney = subsidyMoney;
            }

            public String getToolToken() {
                return toolToken;
            }

            public void setToolToken(String toolToken) {
                this.toolToken = toolToken;
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

            public String getWebToken() {
                return webToken;
            }

            public void setWebToken(String webToken) {
                this.webToken = webToken;
            }

            public static class AddressBean {
                /**
                 * shipAddress : {"address":"北京市北京市东城区榜头镇天易世博","code":"000000","id":271,"linkman":"谢伟杰","phone":"17750656067","type":0}
                 * returnAddress : {"address":"吉林省长春市南关区2632362212","code":"000000","id":357,"linkman":"6667","phone":"17750657777","type":1}
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
                     * address : 北京市北京市东城区榜头镇天易世博
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
                     * address : 吉林省长春市南关区2632362212
                     * code : 000000
                     * id : 357
                     * linkman : 6667
                     * phone : 17750657777
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

            public static class BuyerOrderCountBean {
                /**
                 * noPayNum : 0
                 * receiveNum : 0
                 * sendNum : 5
                 * serviceNum : 0
                 * type : 1
                 */

                private int noPayNum;
                private int receiveNum;
                private int sendNum;
                private int serviceNum;
                private int type;

                public int getNoPayNum() {
                    return noPayNum;
                }

                public void setNoPayNum(int noPayNum) {
                    this.noPayNum = noPayNum;
                }

                public int getReceiveNum() {
                    return receiveNum;
                }

                public void setReceiveNum(int receiveNum) {
                    this.receiveNum = receiveNum;
                }

                public int getSendNum() {
                    return sendNum;
                }

                public void setSendNum(int sendNum) {
                    this.sendNum = sendNum;
                }

                public int getServiceNum() {
                    return serviceNum;
                }

                public void setServiceNum(int serviceNum) {
                    this.serviceNum = serviceNum;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }
            }

            public static class SellerOrderCountBean {
                /**
                 * noPayNum : 0
                 * receiveNum : 0
                 * sendNum : 0
                 * serviceNum : 0
                 * type : 2
                 */

                private int noPayNum;
                private int receiveNum;
                private int sendNum;
                private int serviceNum;
                private int type;

                public int getNoPayNum() {
                    return noPayNum;
                }

                public void setNoPayNum(int noPayNum) {
                    this.noPayNum = noPayNum;
                }

                public int getReceiveNum() {
                    return receiveNum;
                }

                public void setReceiveNum(int receiveNum) {
                    this.receiveNum = receiveNum;
                }

                public int getSendNum() {
                    return sendNum;
                }

                public void setSendNum(int sendNum) {
                    this.sendNum = sendNum;
                }

                public int getServiceNum() {
                    return serviceNum;
                }

                public void setServiceNum(int serviceNum) {
                    this.serviceNum = serviceNum;
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

    public static class ResultBean {
        /**
         * code : 0
         * message : 请求成功
         * success : true
         * timestamp : 1597483061545
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
