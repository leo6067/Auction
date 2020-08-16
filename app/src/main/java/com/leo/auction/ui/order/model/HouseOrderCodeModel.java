package com.leo.auction.ui.order.model;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.order.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/8/16
 * 描    述： 超级仓库---一键代发
 * 修    改：
 * ===============================================
 */
public class HouseOrderCodeModel {

    public static class ProductParent {
        private ArrayList<Product> list;

        public List<Product> getList() {
            return list;
        }

        public void setList(ArrayList<Product> list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return
                    " " + list
                    ;
        }
    }


    public static class Product {

        /**
         * merchantInfo : {"shopUri":"1908091936M3Z6sI","supplierId":8,"headimg":"https://file.taojianlou.com/ut/user/ZwTDF9P5vwIglGyBJjkgC321SxsCqKK7.png","nickname":"之井的店铺"}
         * list : [{"isyu":"true","img":"https://file.taojianlou.com/ut/goods/P7eZxRKHlsmSqOx1AtnbzQt72Sgsy40y.jpg?image=1024,1390","paynumber":"1","title":"哈哈哈哈哈哈","goodsId":7328,"totalPrice":"25","stock":9,"price":"25","realAgentPrice":"25","realPrice":"30"}]
         */

        private MerchantInfoBean merchantInfo;
        private List<ListBean> list;

        public MerchantInfoBean getMerchantInfo() {
            return merchantInfo;
        }

        public void setMerchantInfo(MerchantInfoBean merchantInfo) {
            this.merchantInfo = merchantInfo;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return " " +
                    merchantInfo +
                    list
                    ;
        }

        public static class MerchantInfoBean {
            /**
             * shopUri : 1908091936M3Z6sI
             * supplierId : 8
             * headimg : https://file.taojianlou.com/ut/user/ZwTDF9P5vwIglGyBJjkgC321SxsCqKK7.png
             * nickname : 之井的店铺
             */

            private String shopUri;
            private int supplierId;
            private String headimg;
            private String nickname;

            public String getShopUri() {
                return shopUri;
            }

            public void setShopUri(String shopUri) {
                this.shopUri = shopUri;
            }

            public int getSupplierId() {
                return supplierId;
            }

            public void setSupplierId(int supplierId) {
                this.supplierId = supplierId;
            }

            public String getHeadimg() {
                return headimg;
            }

            public void setHeadimg(String headimg) {
                this.headimg = headimg;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            @Override
            public String toString() {
                return "MerchantInfoBean{" +
                        "shopUri='" + shopUri + '\'' +
                        ", supplierId=" + supplierId +
                        ", headimg='" + headimg + '\'' +
                        ", nickname='" + nickname + '\'' +
                        '}';
            }
        }


        public static class ListBean {
            /**
             * isyu : true
             * img : https://file.taojianlou.com/ut/goods/P7eZxRKHlsmSqOx1AtnbzQt72Sgsy40y.jpg?image=1024,1390
             * paynumber : 1
             * title : 哈哈哈哈哈哈
             * goodsId : 7328
             * totalPrice : 25
             * stock : 9
             * price : 25
             * realAgentPrice : 25
             * realPrice : 30
             */

            private boolean isyu;
            private String img;
            private String paynumber;
            private String title;
            private int goodsId;
            private String totalPrice;
            private int stock;
            private String price;
            private String realAgentPrice;
            private String realPrice;

            public boolean getIsyu() {
                return isyu;
            }

            public void setIsyu(boolean isyu) {
                this.isyu = isyu;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getPaynumber() {
                return paynumber;
            }

            public void setPaynumber(String paynumber) {
                this.paynumber = paynumber;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public String getTotalPrice() {
                return totalPrice;
            }

            public void setTotalPrice(String totalPrice) {
                this.totalPrice = totalPrice;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getRealAgentPrice() {
                return realAgentPrice;
            }

            public void setRealAgentPrice(String realAgentPrice) {
                this.realAgentPrice = realAgentPrice;
            }

            public String getRealPrice() {
                return realPrice;
            }

            public void setRealPrice(String realPrice) {
                this.realPrice = realPrice;
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "isyu=" + isyu +
                        ", img='" + img + '\'' +
                        ", paynumber='" + paynumber + '\'' +
                        ", title='" + title + '\'' +
                        ", goodsId=" + goodsId +
                        ", totalPrice='" + totalPrice + '\'' +
                        ", stock=" + stock +
                        ", price='" + price + '\'' +
                        ", realAgentPrice='" + realAgentPrice + '\'' +
                        ", realPrice='" + realPrice + '\'' +
                        '}';
            }
        }
    }


    public static class Addressshouhuo {


        /**
         * address : 广东省中山市中山市沙溪镇 详细地址: 隆兴工业区兴工路76号尚品薇薇六楼设计部
         * phone : 13415340779
         * addr1Name :
         * code : 000000
         * linkman : 夏瑜娉
         * addr2Name :
         * addr3Name :
         */

        private String address;
        private String phone;
        private String addr1Name;
        private String code;
        private String linkman;
        private String addr2Name;
        private String addr3Name;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddr1Name() {
            return addr1Name;
        }

        public void setAddr1Name(String addr1Name) {
            this.addr1Name = addr1Name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getLinkman() {
            return linkman;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
        }

        public String getAddr2Name() {
            return addr2Name;
        }

        public void setAddr2Name(String addr2Name) {
            this.addr2Name = addr2Name;
        }

        public String getAddr3Name() {
            return addr3Name;
        }

        public void setAddr3Name(String addr3Name) {
            this.addr3Name = addr3Name;
        }
    }


    public static class ZhidInfo {


        /**
         * phone : 13599854203
         * name : 河北的
         */

        private String phone;
        private String name;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
