package com.leo.auction.ui.main.mine.model;

import android.os.Parcel;
import android.os.Parcelable;


import com.google.gson.annotations.SerializedName;
import com.leo.auction.net.CustomerJsonCallBack;

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
public class MineModel {
    /**
     * data : {"info":{"balance":"0.00","coinNum":9327,"followNum":0,"headimg":"https://w.taojianlou.com/image/default/head.jpg","id":1966,"level":1,"nickname":"150****8985","openId":"","payPwd":0,"phone":"15060338985","score":0,"supplierId":141,"supplierStatus":"00B","type":"0","unionid":false,"usableBalance":"0.00","shopUri":"","balanceExempt":"","fixedSupplier":"","warrantBalance":"","newUser":"","userId":"1910311042T8Zvq7"},"orderInfo":{"1":{"noPayNum":0,"rateNum":0,"receiveNum":0,"sendNum":9,"serviceNum":0},"2":{"noPayNum":0,"rateNum":0,"receiveNum":0,"sendNum":0,"serviceNum":0}},"supplierInfo":{"exclusiveNum":0,"fansNum":0,"goodsManagerNum":0,"supplierLevel":1,"supplierScore":0}}
     * result : {"code":"0","message":"请求成功","success":true}
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
         * info : {"balance":"0.00","coinNum":9327,"followNum":0,"headimg":"https://w.taojianlou.com/image/default/head.jpg","id":1966,"level":1,"nickname":"150****8985","openId":"","payPwd":0,"phone":"15060338985","score":0,"supplierId":141,"supplierStatus":"00B","type":"0","unionid":false,"usableBalance":"0.00","shopUri":"","balanceExempt":"","fixedSupplier":"","warrantBalance":"","newUser":"","userId":"1910311042T8Zvq7"}
         * orderInfo : {"1":{"noPayNum":0,"rateNum":0,"receiveNum":0,"sendNum":9,"serviceNum":0},"2":{"noPayNum":0,"rateNum":0,"receiveNum":0,"sendNum":0,"serviceNum":0}}
         * supplierInfo : {"exclusiveNum":0,"fansNum":0,"goodsManagerNum":0,"supplierLevel":1,"supplierScore":0}
         */

        private InfoBean info;
        private OrderInfoBean orderInfo;
        private SupplierInfoBean supplierInfo;

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

        public static class InfoBean implements Parcelable {
            /**
             * balance : 0.00
             * coinNum : 9327
             * followNum : 0
             * headimg : https://w.taojianlou.com/image/default/head.jpg
             * id : 1966
             * level : 1
             * nickname : 150****8985
             * openId :
             * payPwd : 0
             * phone : 15060338985
             * score : 0
             * supplierId : 141
             * supplierStatus : 00B
             * type : 0
             * unionid : false
             * usableBalance : 0.00
             * shopUri :
             * balanceExempt :
             * fixedSupplier :
             * warrantBalance :
             * newUser :
             * userId : 1910311042T8Zvq7
             */

            private String balance;
            private String coinNum;
            private int followNum;
            private String headimg;
            private String id;
            private int level;
            private String nickname;
            private String openId;
            private String payPwd;
            private String phone;
            private int score;
            private String supplierId;
            private String supplierStatus;
            private String type;
            private boolean unionid;
            private String usableBalance;
            private String shopUri;
            private String balanceExempt;
            private String fixedSupplier;
            private String warrantBalance;
            private String newUser;
            private String userId;
            private AddressBean address;
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

            public int getFollowNum() {
                return followNum;
            }

            public void setFollowNum(int followNum) {
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

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public String getSupplierId() {
                return supplierId;
            }

            public void setSupplierId(String supplierId) {
                this.supplierId = supplierId;
            }

            public String getSupplierStatus() {
                return supplierStatus;
            }

            public void setSupplierStatus(String supplierStatus) {
                this.supplierStatus = supplierStatus;
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

            public String getShopUri() {
                return shopUri;
            }

            public void setShopUri(String shopUri) {
                this.shopUri = shopUri;
            }

            public String getBalanceExempt() {
                return balanceExempt;
            }

            public void setBalanceExempt(String balanceExempt) {
                this.balanceExempt = balanceExempt;
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

            public String getNewUser() {
                return newUser;
            }

            public void setNewUser(String newUser) {
                this.newUser = newUser;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public AddressBean getAddress() {
                return address;
            }

            public void setAddress(AddressBean address) {
                this.address = address;
            }

            public boolean getCompanyAuth() {
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

            public static class AddressBean implements Parcelable {
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

                public static class ShipAddressBean implements Parcelable {
                    private String address;
                    private String addressId;
                    private String code;
                    private String linkPhone;
                    private String linkman;
                    private String type;

                    public String getAddress() {
                        return address;
                    }

                    public void setAddress(String address) {
                        this.address = address;
                    }

                    public String getAddressId() {
                        return addressId;
                    }

                    public void setAddressId(String addressId) {
                        this.addressId = addressId;
                    }

                    public String getCode() {
                        return code;
                    }

                    public void setCode(String code) {
                        this.code = code;
                    }

                    public String getLinkPhone() {
                        return linkPhone;
                    }

                    public void setLinkPhone(String linkPhone) {
                        this.linkPhone = linkPhone;
                    }

                    public String getLinkman() {
                        return linkman;
                    }

                    public void setLinkman(String linkman) {
                        this.linkman = linkman;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        dest.writeString(this.address);
                        dest.writeString(this.addressId);
                        dest.writeString(this.code);
                        dest.writeString(this.linkPhone);
                        dest.writeString(this.linkman);
                        dest.writeString(this.type);
                    }

                    public ShipAddressBean() {
                    }

                    protected ShipAddressBean(Parcel in) {
                        this.address = in.readString();
                        this.addressId = in.readString();
                        this.code = in.readString();
                        this.linkPhone = in.readString();
                        this.linkman = in.readString();
                        this.type = in.readString();
                    }

                    public static final Creator<ShipAddressBean> CREATOR = new Creator<ShipAddressBean>() {
                        @Override
                        public ShipAddressBean createFromParcel(Parcel source) {
                            return new ShipAddressBean(source);
                        }

                        @Override
                        public ShipAddressBean[] newArray(int size) {
                            return new ShipAddressBean[size];
                        }
                    };
                }

                public static class ReturnAddressBean implements Parcelable {
                    private String address;
                    private String addressId;
                    private String code;
                    private String linkPhone;
                    private String linkman;
                    private String type;

                    public String getAddress() {
                        return address;
                    }

                    public void setAddress(String address) {
                        this.address = address;
                    }

                    public String getAddressId() {
                        return addressId;
                    }

                    public void setAddressId(String addressId) {
                        this.addressId = addressId;
                    }

                    public String getCode() {
                        return code;
                    }

                    public void setCode(String code) {
                        this.code = code;
                    }

                    public String getLinkPhone() {
                        return linkPhone;
                    }

                    public void setLinkPhone(String linkPhone) {
                        this.linkPhone = linkPhone;
                    }

                    public String getLinkman() {
                        return linkman;
                    }

                    public void setLinkman(String linkman) {
                        this.linkman = linkman;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        dest.writeString(this.address);
                        dest.writeString(this.addressId);
                        dest.writeString(this.code);
                        dest.writeString(this.linkPhone);
                        dest.writeString(this.linkman);
                        dest.writeString(this.type);
                    }

                    public ReturnAddressBean() {
                    }

                    protected ReturnAddressBean(Parcel in) {
                        this.address = in.readString();
                        this.addressId = in.readString();
                        this.code = in.readString();
                        this.linkPhone = in.readString();
                        this.linkman = in.readString();
                        this.type = in.readString();
                    }

                    public static final Creator<ReturnAddressBean> CREATOR = new Creator<ReturnAddressBean>() {
                        @Override
                        public ReturnAddressBean createFromParcel(Parcel source) {
                            return new ReturnAddressBean(source);
                        }

                        @Override
                        public ReturnAddressBean[] newArray(int size) {
                            return new ReturnAddressBean[size];
                        }
                    };
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeParcelable(this.shipAddress, flags);
                    dest.writeParcelable(this.returnAddress, flags);
                }

                public AddressBean() {
                }

                protected AddressBean(Parcel in) {
                    this.shipAddress = in.readParcelable(ShipAddressBean.class.getClassLoader());
                    this.returnAddress = in.readParcelable(ReturnAddressBean.class.getClassLoader());
                }

                public static final Creator<AddressBean> CREATOR = new Creator<AddressBean>() {
                    @Override
                    public AddressBean createFromParcel(Parcel source) {
                        return new AddressBean(source);
                    }

                    @Override
                    public AddressBean[] newArray(int size) {
                        return new AddressBean[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.balance);
                dest.writeString(this.coinNum);
                dest.writeInt(this.followNum);
                dest.writeString(this.headimg);
                dest.writeString(this.id);
                dest.writeInt(this.level);
                dest.writeString(this.nickname);
                dest.writeString(this.openId);
                dest.writeString(this.payPwd);
                dest.writeString(this.phone);
                dest.writeInt(this.score);
                dest.writeString(this.supplierId);
                dest.writeString(this.supplierStatus);
                dest.writeString(this.type);
                dest.writeByte(this.unionid ? (byte) 1 : (byte) 0);
                dest.writeString(this.usableBalance);
                dest.writeString(this.shopUri);
                dest.writeString(this.balanceExempt);
                dest.writeString(this.fixedSupplier);
                dest.writeString(this.warrantBalance);
                dest.writeString(this.newUser);
                dest.writeString(this.userId);
                dest.writeParcelable(this.address, flags);
                dest.writeByte(this.companyAuth ? (byte) 1 : (byte) 0);
                dest.writeByte(this.partner ? (byte) 1 : (byte) 0);
                dest.writeByte(this.dialogConnect ? (byte) 1 : (byte) 0);
            }

            public InfoBean() {}

            protected InfoBean(Parcel in) {
                this.balance = in.readString();
                this.coinNum = in.readString();
                this.followNum = in.readInt();
                this.headimg = in.readString();
                this.id = in.readString();
                this.level = in.readInt();
                this.nickname = in.readString();
                this.openId = in.readString();
                this.payPwd = in.readString();
                this.phone = in.readString();
                this.score = in.readInt();
                this.supplierId = in.readString();
                this.supplierStatus = in.readString();
                this.type = in.readString();
                this.unionid = in.readByte() != 0;
                this.usableBalance = in.readString();
                this.shopUri = in.readString();
                this.balanceExempt = in.readString();
                this.fixedSupplier = in.readString();
                this.warrantBalance = in.readString();
                this.newUser = in.readString();
                this.userId = in.readString();
                this.address = in.readParcelable(AddressBean.class.getClassLoader());
                this.companyAuth = in.readByte() != 0;
                this.partner = in.readByte() != 0;
                this.dialogConnect = in.readByte() != 0;
            }

            public static final Creator<InfoBean> CREATOR = new Creator<InfoBean>() {
                @Override
                public InfoBean createFromParcel(Parcel source) {
                    return new InfoBean(source);
                }

                @Override
                public InfoBean[] newArray(int size) {
                    return new InfoBean[size];
                }
            };
        }

        public static class OrderInfoBean {
            /**
             * 1 : {"noPayNum":0,"rateNum":0,"receiveNum":0,"sendNum":9,"serviceNum":0}
             * 2 : {"noPayNum":0,"rateNum":0,"receiveNum":0,"sendNum":0,"serviceNum":0}
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
                 * rateNum : 0
                 * receiveNum : 0
                 * sendNum : 9
                 * serviceNum : 0
                 */

                private int noPayNum;
                private int rateNum;
                private int receiveNum;
                private int sendNum;
                private int serviceNum;

                public int getNoPayNum() {
                    return noPayNum;
                }

                public void setNoPayNum(int noPayNum) {
                    this.noPayNum = noPayNum;
                }

                public int getRateNum() {
                    return rateNum;
                }

                public void setRateNum(int rateNum) {
                    this.rateNum = rateNum;
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
                 * rateNum : 0
                 * receiveNum : 0
                 * sendNum : 0
                 * serviceNum : 0
                 */

                private int noPayNum;
                private int rateNum;
                private int receiveNum;
                private int sendNum;
                private int serviceNum;

                public int getNoPayNum() {
                    return noPayNum;
                }

                public void setNoPayNum(int noPayNum) {
                    this.noPayNum = noPayNum;
                }

                public int getRateNum() {
                    return rateNum;
                }

                public void setRateNum(int rateNum) {
                    this.rateNum = rateNum;
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
             * supplierLevel : 1
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
         * code : 0
         * message : 请求成功
         * success : true
         */

        private String code;
        private String message;
        private boolean success;

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
    }

    //获取用户个人信息
    public static void sendMineRequest(final String TAG, final CustomerJsonCallBack<MineModel> callback) {
        HashMap<String,String> params = new HashMap<>();
//        JsonRequestData.requesNetWork(TAG, Constants.Api.HOMEPAGE_USER_INFO_URL, JSON.toJSONString(params), callback);
    }
}
