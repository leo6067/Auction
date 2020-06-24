package com.leo.auction.ui.main.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.home.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/6/23
 * 描    述： 商家列表
 * 修    改：
 * ===============================================
 */
public class SupplierModel {

    /**
     * data : [{"newestNum":1,"productUser":{"fansNum":2,"headImg":"https://file.taojianlou.com/ut/user/a41231034004a0ed4e133d3a9bbcaf52.jpg","nickname":"修改过的名","rate":5,"sellerLevel":0,"userId":"1910291733xcdYAz"}}]
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1592210840302}
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
         * timestamp : 1592210840302
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
         * newestNum : 1
         * productUser : {"fansNum":2,"headImg":"https://file.taojianlou.com/ut/user/a41231034004a0ed4e133d3a9bbcaf52.jpg","nickname":"修改过的名","rate":5,"sellerLevel":0,"userId":"1910291733xcdYAz"}
         */

        private int newestNum;
        private ProductUserBean productUser;

        public int getNewestNum() {
            return newestNum;
        }

        public void setNewestNum(int newestNum) {
            this.newestNum = newestNum;
        }

        public ProductUserBean getProductUser() {
            return productUser;
        }

        public void setProductUser(ProductUserBean productUser) {
            this.productUser = productUser;
        }

        public static class ProductUserBean implements Parcelable {
            /**
             * fansNum : 2
             * headImg : https://file.taojianlou.com/ut/user/a41231034004a0ed4e133d3a9bbcaf52.jpg
             * nickname : 修改过的名
             * rate : 5.0
             * sellerLevel : 0
             * userId : 1910291733xcdYAz
             */

            private int fansNum;
            private String headImg;
            private String nickname;
            private double rate;
            private int sellerLevel;
            private String userId;

            public ProductUserBean() {
            }

            protected ProductUserBean(Parcel in) {
                fansNum = in.readInt();
                headImg = in.readString();
                nickname = in.readString();
                rate = in.readDouble();
                sellerLevel = in.readInt();
                userId = in.readString();
            }

            public static final Creator<ProductUserBean> CREATOR = new Creator<ProductUserBean>() {
                @Override
                public ProductUserBean createFromParcel(Parcel in) {
                    return new ProductUserBean(in);
                }

                @Override
                public ProductUserBean[] newArray(int size) {
                    return new ProductUserBean[size];
                }
            };

            public int getFansNum() {
                return fansNum;
            }

            public void setFansNum(int fansNum) {
                this.fansNum = fansNum;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public double getRate() {
                return rate;
            }

            public void setRate(double rate) {
                this.rate = rate;
            }

            public int getSellerLevel() {
                return sellerLevel;
            }

            public void setSellerLevel(int sellerLevel) {
                this.sellerLevel = sellerLevel;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(fansNum);
                dest.writeString(headImg);
                dest.writeString(nickname);
                dest.writeDouble(rate);
                dest.writeInt(sellerLevel);
                dest.writeString(userId);
            }
        }
    }
}
