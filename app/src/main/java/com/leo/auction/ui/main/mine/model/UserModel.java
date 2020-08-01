package com.leo.auction.ui.main.mine.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.utils.Globals;

import java.util.HashMap;

import okhttp3.Call;

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
public class UserModel {


    /**
     * data : {"address":{"shipAddress":{"address":"北京市北京市东城区ggg","code":"jjjj","id":5,"linkman":"ghhh","phone":"13489973950","type":0},"returnAddress":{"address":"北京市北京市东城区横坑村","code":"000000","id":268,"linkman":"翁忠杰","phone":"13489973950","type":1}},"auctionCoinBalance":"0.00","auctionCoinNum":0,"balance":"1.00","balanceExempt":"XuMu","buyerOrderCount":{"noPayNum":0,"receiveNum":0,"sendNum":0,"serviceNum":0,"type":1},"dialogConnect":true,"fansNum":4,"exclusiveFansNum":12,"limitProductFansNum":50,"followNum":1,"headImg":"https://file.taojianlou.com/ut/user/2D33BF879AD5417580FA7C6B7AA52E73.jpg","hxId":"3c50b86a051ec63ccf66d56f6282f935","id":69,"idCard":"3****************X","instanceNum":0,"level":0,"limitType":0,"nickname":"翁小杰","payPwd":true,"phone":"13489973950","rate":0,"score":0,"sellerLevel":1,"sellerOrderCount":{"noPayNum":0,"receiveNum":0,"sendNum":0,"serviceNum":0,"type":2},"sellerScore":0,"storeEnable":false,"type":1,"userId":"1908091213AS4weP","username":"翁忠杰","warrant":"0.00","subsidyMoney":"12","appToken":"akhdajdhakjda","mpToken":"akhdajdhakjda","h5Token":"akhdajdhakjda","webToken":"akhdajdhakjda"}
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1593396313969}
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
         * address : {"shipAddress":{"address":"北京市北京市东城区ggg","code":"jjjj","id":5,"linkman":"ghhh","phone":"13489973950","type":0},"returnAddress":{"address":"北京市北京市东城区横坑村","code":"000000","id":268,"linkman":"翁忠杰","phone":"13489973950","type":1}}
         * auctionCoinBalance : 0.00
         * auctionCoinNum : 0
         * balance : 1.00
         * balanceExempt : XuMu
         * buyerOrderCount : {"noPayNum":0,"receiveNum":0,"sendNum":0,"serviceNum":0,"type":1}
         * dialogConnect : true
         * fansNum : 4
         * exclusiveFansNum : 12
         * limitProductFansNum : 50
         * followNum : 1
         * headImg : https://file.taojianlou.com/ut/user/2D33BF879AD5417580FA7C6B7AA52E73.jpg
         * hxId : 3c50b86a051ec63ccf66d56f6282f935
         * id : 69
         * idCard : 3****************X
         * instanceNum : 0
         * level : 0
         * limitType : 0
         * nickname : 翁小杰
         * payPwd : true
         * phone : 13489973950
         * rate : 0
         * score : 0
         * sellerLevel : 1
         * sellerOrderCount : {"noPayNum":0,"receiveNum":0,"sendNum":0,"serviceNum":0,"type":2}
         * sellerScore : 0
         * storeEnable : false
         * type : 1
         * userId : 1908091213AS4weP
         * username : 翁忠杰
         * warrant : 0.00
         * subsidyMoney : 12
         * appToken : akhdajdhakjda
         * mpToken : akhdajdhakjda
         * h5Token : akhdajdhakjda
         * webToken : akhdajdhakjda
         */

        private AddressBean address;
        private String auctionCoinBalance;
        private int auctionCoinNum;
        private String balance;
        private String balanceExempt;
        private BuyerOrderCountBean buyerOrderCount;
        private boolean dialogConnect;
        private int fansNum;
        private int exclusiveFansNum;
        private int limitProductFansNum;
        private int followNum;
        private String headImg;
        private String hxId;
        private int id;
        private String idCard;
        private int instanceNum;
        private int level;
        private int limitType;
        private String nickname;
        private boolean payPwd;
        private String phone;
        private int rate;
        private int score;
        private int sellerLevel;
        private SellerOrderCountBean sellerOrderCount;
        private int sellerScore;
        private boolean storeEnable;
        private int type;
        private String userId;
        private String username;
        private String warrant;
        private String subsidyMoney;
        private String appToken;
        private String mpToken;
        private String h5Token;
        private String webToken;

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

        public int getFansNum() {
            return fansNum;
        }

        public void setFansNum(int fansNum) {
            this.fansNum = fansNum;
        }

        public int getExclusiveFansNum() {
            return exclusiveFansNum;
        }

        public void setExclusiveFansNum(int exclusiveFansNum) {
            this.exclusiveFansNum = exclusiveFansNum;
        }

        public int getLimitProductFansNum() {
            return limitProductFansNum;
        }

        public void setLimitProductFansNum(int limitProductFansNum) {
            this.limitProductFansNum = limitProductFansNum;
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

        public String getSubsidyMoney() {
            return subsidyMoney;
        }

        public void setSubsidyMoney(String subsidyMoney) {
            this.subsidyMoney = subsidyMoney;
        }

        public String getAppToken() {
            return appToken;
        }

        public void setAppToken(String appToken) {
            this.appToken = appToken;
        }

        public String getMpToken() {
            return mpToken;
        }

        public void setMpToken(String mpToken) {
            this.mpToken = mpToken;
        }

        public String getH5Token() {
            return h5Token;
        }

        public void setH5Token(String h5Token) {
            this.h5Token = h5Token;
        }

        public String getWebToken() {
            return webToken;
        }

        public void setWebToken(String webToken) {
            this.webToken = webToken;
        }

        public static class AddressBean {
            /**
             * shipAddress : {"address":"北京市北京市东城区ggg","code":"jjjj","id":5,"linkman":"ghhh","phone":"13489973950","type":0}
             * returnAddress : {"address":"北京市北京市东城区横坑村","code":"000000","id":268,"linkman":"翁忠杰","phone":"13489973950","type":1}
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
                 * address : 北京市北京市东城区ggg
                 * code : jjjj
                 * id : 5
                 * linkman : ghhh
                 * phone : 13489973950
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
                 * address : 北京市北京市东城区横坑村
                 * code : 000000
                 * id : 268
                 * linkman : 翁忠杰
                 * phone : 13489973950
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

    public static class ResultBean {
        /**
         * code : 0
         * message : 请求成功
         * success : true
         * timestamp : 1593396313969
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

    public static void httpUserInfo(HttpRequest.HttpCallback httpCallback){

        HashMap<String, String> hashMap = new HashMap<>();

        HttpRequest.httpGetString(Constants.Api.USER_URL,hashMap ,httpCallback);

    }

    public static void httpUpdateUser( ){
        HashMap<String, String> hashMap = new HashMap<>();
        HttpRequest.httpGetString(Constants.Api.USER_URL, hashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {
                UserModel userModel = JSONObject.parseObject(resultData, UserModel.class);
                BaseSharePerence.getInstance().setUserJson(JSON.toJSONString(userModel.getData()));
                Globals.log("xxxxxxxx首页 03  token" + userModel.getData().getH5Token());
            }
        });
    }


    public static void httpUpdateUser(HttpRequest.HttpCallback httpCallback){
        HashMap<String, String> hashMap = new HashMap<>();

        HttpRequest.httpGetString(Constants.Api.USER_URL, hashMap,httpCallback);




        HttpRequest.httpGetString(Constants.Api.USER_URL, hashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {
                UserModel userModel = JSONObject.parseObject(resultData, UserModel.class);
                BaseSharePerence.getInstance().setUserJson(JSON.toJSONString(userModel.getData()));
                Globals.log("xxxxxxxx首页 03  token" + userModel.getData().getH5Token());
            }
        });
    }

}
