package com.leo.auction.ui.login.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.annotations.SerializedName;
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
     * data : {"info":{"balance":"0.00","coinNum":0,"followNum":0,"headimg":"https://w.taojianlou.com/image/default/head.jpg","id":1966,"level":1,"nickname":"150****8985","openId":"","payPwd":0,"phone":"15060338985","score":0,"type":"0","unionid":false,"usableBalance":"0.00","userId":"1910311042T8Zvq7"},"orderInfo":{"1":{"noPayNum":0,"receiveNum":0,"sendNum":0,"serviceNum":0},"2":{"noPayNum":0,"receiveNum":0,"sendNum":0,"serviceNum":0}},"supplierInfo":{"exclusiveNum":0,"fansNum":0,"goodsManagerNum":0,"supplierLevel":0,"supplierScore":0},"token":"iwPTNSKR3oWaoxISZ458i8vjBKEREneP"}
     * result : {"message":"请求成功","success":true}
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
         * info : {"balance":"0.00","coinNum":0,"followNum":0,"headimg":"https://w.taojianlou.com/image/default/head.jpg","id":1966,"level":1,"nickname":"150****8985","openId":"","payPwd":0,"phone":"15060338985","score":0,"type":"0","unionid":false,"usableBalance":"0.00","userId":"1910311042T8Zvq7"}
         * orderInfo : {"1":{"noPayNum":0,"receiveNum":0,"sendNum":0,"serviceNum":0},"2":{"noPayNum":0,"receiveNum":0,"sendNum":0,"serviceNum":0}}
         * supplierInfo : {"exclusiveNum":0,"fansNum":0,"goodsManagerNum":0,"supplierLevel":0,"supplierScore":0}
         * token : iwPTNSKR3oWaoxISZ458i8vjBKEREneP
         */

        private InfoBean info;
        private OrderInfoBean orderInfo;
        private SupplierInfoBean supplierInfo;
        private String token;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public OrderInfoBean getOrderInfo() {
            return orderInfo;
        }

        public void setOrderInfo(OrderInfoBean orderInfo) {
            this.orderInfo = orderInfo;
        }

        public SupplierInfoBean getSupplierInfo() {
            return supplierInfo;
        }

        public void setSupplierInfo(SupplierInfoBean supplierInfo) {
            this.supplierInfo = supplierInfo;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class InfoBean {
            /**
             * balance : 0.00
             * coinNum : 0
             * followNum : 0
             * headimg : https://w.taojianlou.com/image/default/head.jpg
             * id : 1966
             * level : 1
             * nickname : 150****8985
             * openId :
             * payPwd : 0
             * phone : 15060338985
             * score : 0
             * type : 0
             * unionid : false
             * usableBalance : 0.00
             * userId : 1910311042T8Zvq7
             */

            private String balance;
            private String coinNum;
            private String followNum;
            private String headimg;
            private String id;
            private String level;
            private String nickname;
            private String openId;
            private String payPwd;
            private String phone;
            private String score;
            private String type;
            private boolean unionid;
            private String usableBalance;
            private String userId;
            private String balanceExempt;
            private String supplierId;
            private String shopUri;
            private String fixedSupplier;
            private String warrantBalance;
            private MineModel.DataBean.InfoBean.AddressBean address;
            private boolean companyAuth;
            private boolean partner;
            private boolean dialogConnect;

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public String getCoinNum() {
                return coinNum;
            }

            public void setCoinNum(String coinNum) {
                this.coinNum = coinNum;
            }

            public String getFollowNum() {
                return followNum;
            }

            public void setFollowNum(String followNum) {
                this.followNum = followNum;
            }

            public String getHeadimg() {
                return headimg;
            }

            public void setHeadimg(String headimg) {
                this.headimg = headimg;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getOpenId() {
                return openId;
            }

            public void setOpenId(String openId) {
                this.openId = openId;
            }

            public String getPayPwd() {
                return payPwd;
            }

            public void setPayPwd(String payPwd) {
                this.payPwd = payPwd;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public boolean isUnionid() {
                return unionid;
            }

            public void setUnionid(boolean unionid) {
                this.unionid = unionid;
            }

            public String getUsableBalance() {
                return usableBalance;
            }

            public void setUsableBalance(String usableBalance) {
                this.usableBalance = usableBalance;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getBalanceExempt() {
                return balanceExempt;
            }

            public void setBalanceExempt(String balanceExempt) {
                this.balanceExempt = balanceExempt;
            }

            public String getSupplierId() {
                return supplierId;
            }

            public void setSupplierId(String supplierId) {
                this.supplierId = supplierId;
            }

            public String getShopUri() {
                return shopUri;
            }

            public void setShopUri(String shopUri) {
                this.shopUri = shopUri;
            }

            public String getFixedSupplier() {
                return fixedSupplier;
            }

            public void setFixedSupplier(String fixedSupplier) {
                this.fixedSupplier = fixedSupplier;
            }

            public String getWarrantBalance() {
                return warrantBalance;
            }

            public void setWarrantBalance(String warrantBalance) {
                this.warrantBalance = warrantBalance;
            }

            public MineModel.DataBean.InfoBean.AddressBean getAddress() {
                return address;
            }

            public void setAddress(MineModel.DataBean.InfoBean.AddressBean address) {
                this.address = address;
            }

            public boolean isCompanyAuth() {
                return companyAuth;
            }

            public void setCompanyAuth(boolean companyAuth) {
                this.companyAuth = companyAuth;
            }

            public boolean getPartner() {
                return partner;
            }

            public void setPartner(boolean partner) {
                this.partner = partner;
            }

            public boolean isDialogConnect() {
                return dialogConnect;
            }

            public void setDialogConnect(boolean dialogConnect) {
                this.dialogConnect = dialogConnect;
            }
        }

        public static class OrderInfoBean {//订单统计
            /**
             * 1 : {"noPayNum":0,"receiveNum":0,"sendNum":0,"serviceNum":0}
             * 2 : {"noPayNum":0,"receiveNum":0,"sendNum":0,"serviceNum":0}
             */

            @SerializedName("1")
            private OrderStatisticsBean01 orderStatisticsBean01;
            @SerializedName("2")
            private OrderStatisticsBean02 orderStatisticsBean02;

            public OrderStatisticsBean01 getOrderStatisticsBean01() {
                return orderStatisticsBean01;
            }

            public void setOrderStatisticsBean01(OrderStatisticsBean01 orderStatisticsBean01) {
                this.orderStatisticsBean01 = orderStatisticsBean01;
            }

            public OrderStatisticsBean02 getOrderStatisticsBean02() {
                return orderStatisticsBean02;
            }

            public void setOrderStatisticsBean02(OrderStatisticsBean02 orderStatisticsBean02) {
                this.orderStatisticsBean02 = orderStatisticsBean02;
            }

            public static class OrderStatisticsBean01 {
                /**
                 * noPayNum : 0
                 * receiveNum : 0
                 * sendNum : 0
                 * serviceNum : 0
                 */

                private int noPayNum;
                private int receiveNum;
                private int sendNum;
                private int serviceNum;

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
            }

            public static class OrderStatisticsBean02 {
                /**
                 * noPayNum : 0
                 * receiveNum : 0
                 * sendNum : 0
                 * serviceNum : 0
                 */

                private int noPayNum;
                private int receiveNum;
                private int sendNum;
                private int serviceNum;

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
            }
        }

        public static class SupplierInfoBean {
            /**
             * exclusiveNum : 0
             * fansNum : 0
             * goodsManagerNum : 0
             * supplierLevel : 0
             * supplierScore : 0
             */

            private int exclusiveNum;
            private int fansNum;
            private int goodsManagerNum;
            private int supplierLevel;
            private int supplierScore;
            private String qrcode;

            public int getExclusiveNum() {
                return exclusiveNum;
            }

            public void setExclusiveNum(int exclusiveNum) {
                this.exclusiveNum = exclusiveNum;
            }

            public int getFansNum() {
                return fansNum;
            }

            public void setFansNum(int fansNum) {
                this.fansNum = fansNum;
            }

            public int getGoodsManagerNum() {
                return goodsManagerNum;
            }

            public void setGoodsManagerNum(int goodsManagerNum) {
                this.goodsManagerNum = goodsManagerNum;
            }

            public int getSupplierLevel() {
                return supplierLevel;
            }

            public void setSupplierLevel(int supplierLevel) {
                this.supplierLevel = supplierLevel;
            }

            public int getSupplierScore() {
                return supplierScore;
            }

            public void setSupplierScore(int supplierScore) {
                this.supplierScore = supplierScore;
            }

            public String getQrcode() {
                return qrcode;
            }

            public void setQrcode(String qrcode) {
                this.qrcode = qrcode;
            }
        }
    }

    public static class ResultBean {
        /**
         * message : 请求成功
         * success : true
         */

        private String message;
        private boolean success;

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
    }

    //用户登录
    public static void sendLoginRequest(final String TAG, String number, String code, final CustomerJsonCallBack<LoginModel> callback) {
        HashMap<String,String> value=new HashMap<>();
        value.put("number",number);//使用场景码 1:商家审核 2：绑定手机号 4-app端登录使用(选择此选项)
        value.put("code",code);
        HttpRequest.requesNetWork(TAG, Constants.Api.HOMEPAGE_USER_PHONE_LOGIN_URL, JSON.toJSONString(value), callback);
    }

    //静默登录
    public static void sendDefaulltLoginRequest(final String TAG, String token,final CustomerJsonCallBack<LoginModel> callback) {
        HashMap<String,String> value=new HashMap<>();
        value.put("token",token);//使用场景码 1:商家审核 2：绑定手机号 4-app端登录使用(选择此选项)
        HttpRequest.requesNetWork(TAG, Constants.Api.HOMEPAGE_USER_DEFAULT_LOGIN_URL, JSON.toJSONString(value), callback);
    }

    //微信登录
    public static void sendWXLoginRequest(final String TAG, String unionId,String openId,String nickname,String headImg,
                                          final CustomerJsonCallBack<LoginModel> callback) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("unionId",unionId);
        jsonObject.put("openId",openId);
        jsonObject.put("nickname",nickname);
        jsonObject.put("headImg",headImg);

        HttpRequest.requesNetWork(TAG, Constants.Api.HOMEPAGE_USER_WX_LOGIN_URL, jsonObject.toJSONString(), callback);
    }
}
