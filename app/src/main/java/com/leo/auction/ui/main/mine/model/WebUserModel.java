package com.leo.auction.ui.main.mine.model;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.mine.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/6/27
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class WebUserModel {


    /**
     * data : {"token":"a581c05f5c5048b9f0127f24a66127e4","user":{"address":{"shipAddress":{"address":"广东省中山市中山市西区街道","code":"000000","id":206,"linkman":"沙朗","phone":"15800180086","type":0},"returnAddress":{"address":"北京市北京市丰台区45645454","code":"000000","id":176,"linkman":"管先生","phone":"13904458487","type":1}},"appToken":"a581c05f5c5048b9f0127f24a66127e4","auctionCoinBalance":"0.00","auctionCoinNum":0,"auctionFansNum":6,"balance":"314.00","balanceExempt":"","buyerOrderCount":{"noPayNum":0,"receiveNum":0,"sendNum":0,"serviceNum":3,"type":1},"dialogConnect":true,"exclusiveFansNum":54,"fansNum":60,"followNum":6,"h5Token":"a2babf7dd80b4d3c98f20a21fbb7a1c8","headImg":"https://file.taojianlou.com/ut/product/gQr7imxhORXr0d80fMTcx04kpDLmg7VX.png?image=1200,1200","hxId":"4453b82b2cf6298ddc76e8bdc8ebe999","id":68,"idCard":"3****************2","instanceNum":0,"level":0,"limitProductFansNum":50,"limitType":0,"mpToken":"19f99edf7878b84fec5c52fd2623b4e9","nestedToken":"ab10c790f97476b4022f8f17356d0955","nickname":"进炜的店铺1","online":false,"payPwd":true,"phone":"18760502325","rate":0,"score":0,"sellerLevel":1,"sellerOrderCount":{"noPayNum":0,"receiveNum":0,"sendNum":6,"serviceNum":4,"type":2},"sellerScore":11,"storeEnable":true,"subsidyMoney":"26","toolToken":"95d756546e05906eea645142a5880491","type":2,"userId":"1908091936M3Z6sI","username":"郑进炜","warrant":"0.00","webToken":"a63c231b887eb5dfe42259f1236ea297"}}
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1597483296736}
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
         * token : a581c05f5c5048b9f0127f24a66127e4
         * user : {"address":{"shipAddress":{"address":"广东省中山市中山市西区街道","code":"000000","id":206,"linkman":"沙朗","phone":"15800180086","type":0},"returnAddress":{"address":"北京市北京市丰台区45645454","code":"000000","id":176,"linkman":"管先生","phone":"13904458487","type":1}},"appToken":"a581c05f5c5048b9f0127f24a66127e4","auctionCoinBalance":"0.00","auctionCoinNum":0,"auctionFansNum":6,"balance":"314.00","balanceExempt":"","buyerOrderCount":{"noPayNum":0,"receiveNum":0,"sendNum":0,"serviceNum":3,"type":1},"dialogConnect":true,"exclusiveFansNum":54,"fansNum":60,"followNum":6,"h5Token":"a2babf7dd80b4d3c98f20a21fbb7a1c8","headImg":"https://file.taojianlou.com/ut/product/gQr7imxhORXr0d80fMTcx04kpDLmg7VX.png?image=1200,1200","hxId":"4453b82b2cf6298ddc76e8bdc8ebe999","id":68,"idCard":"3****************2","instanceNum":0,"level":0,"limitProductFansNum":50,"limitType":0,"mpToken":"19f99edf7878b84fec5c52fd2623b4e9","nestedToken":"ab10c790f97476b4022f8f17356d0955","nickname":"进炜的店铺1","online":false,"payPwd":true,"phone":"18760502325","rate":0,"score":0,"sellerLevel":1,"sellerOrderCount":{"noPayNum":0,"receiveNum":0,"sendNum":6,"serviceNum":4,"type":2},"sellerScore":11,"storeEnable":true,"subsidyMoney":"26","toolToken":"95d756546e05906eea645142a5880491","type":2,"userId":"1908091936M3Z6sI","username":"郑进炜","warrant":"0.00","webToken":"a63c231b887eb5dfe42259f1236ea297"}
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
             * address : {"shipAddress":{"address":"广东省中山市中山市西区街道","code":"000000","id":206,"linkman":"沙朗","phone":"15800180086","type":0},"returnAddress":{"address":"北京市北京市丰台区45645454","code":"000000","id":176,"linkman":"管先生","phone":"13904458487","type":1}}
             * appToken : a581c05f5c5048b9f0127f24a66127e4
             * auctionCoinBalance : 0.00
             * auctionCoinNum : 0
             * auctionFansNum : 6
             * balance : 314.00
             * balanceExempt :
             * buyerOrderCount : {"noPayNum":0,"receiveNum":0,"sendNum":0,"serviceNum":3,"type":1}
             * dialogConnect : true
             * exclusiveFansNum : 54
             * fansNum : 60
             * followNum : 6
             * h5Token : a2babf7dd80b4d3c98f20a21fbb7a1c8
             * headImg : https://file.taojianlou.com/ut/product/gQr7imxhORXr0d80fMTcx04kpDLmg7VX.png?image=1200,1200
             * hxId : 4453b82b2cf6298ddc76e8bdc8ebe999
             * id : 68
             * idCard : 3****************2
             * instanceNum : 0
             * level : 0
             * limitProductFansNum : 50
             * limitType : 0
             * mpToken : 19f99edf7878b84fec5c52fd2623b4e9
             * nestedToken : ab10c790f97476b4022f8f17356d0955
             * nickname : 进炜的店铺1
             * online : false
             * payPwd : true
             * phone : 18760502325
             * rate : 0
             * score : 0
             * sellerLevel : 1
             * sellerOrderCount : {"noPayNum":0,"receiveNum":0,"sendNum":6,"serviceNum":4,"type":2}
             * sellerScore : 11
             * storeEnable : true
             * subsidyMoney : 26
             * toolToken : 95d756546e05906eea645142a5880491
             * type : 2
             * userId : 1908091936M3Z6sI
             * username : 郑进炜
             * warrant : 0.00
             * webToken : a63c231b887eb5dfe42259f1236ea297
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
            private int rate;
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

            public int getRate() {
                return rate;
            }

            public void setRate(int rate) {
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
                 * shipAddress : {"address":"广东省中山市中山市西区街道","code":"000000","id":206,"linkman":"沙朗","phone":"15800180086","type":0}
                 * returnAddress : {"address":"北京市北京市丰台区45645454","code":"000000","id":176,"linkman":"管先生","phone":"13904458487","type":1}
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
                     * address : 广东省中山市中山市西区街道
                     * code : 000000
                     * id : 206
                     * linkman : 沙朗
                     * phone : 15800180086
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
                     * address : 北京市北京市丰台区45645454
                     * code : 000000
                     * id : 176
                     * linkman : 管先生
                     * phone : 13904458487
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
                 * sendNum : 0
                 * serviceNum : 3
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
                 * sendNum : 6
                 * serviceNum : 4
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
         * timestamp : 1597483296736
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
