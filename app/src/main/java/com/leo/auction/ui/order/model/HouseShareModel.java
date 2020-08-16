package com.leo.auction.ui.order.model;

import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.order.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/8/16
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class HouseShareModel {


    /**
     * agentPrice : 5.11
     * attributes : [{"id":37,"option":1,"tab":"","title":"类型","value":"金丝楠"}]
     * categoryId : 14
     * categoryName : 木质珠串
     * content : 蛤
     * freeShip : false
     * goodsId : 7304
     * images : ["https://file.taojianlou.com/goods/18D8C35E7FCB44799A059B39AD2FF707.png?image=640,640"]
     * parentCategoryId : 13
     * parentCategoryName : 工艺作品
     * price : 0.12
     * realAgentPrice : 0.11
     * stock : 10
     * supplier : {"headImg":"https://file.taojianlou.com/ut/user/ewR5VDgZn3wZDs2RK0VqpCPYM0jNDisn.jpg","nickname":"超级无敌好","shopUri":"1911261024EWXDDW","supplierId":147}
     * title : 超级万平方米
     * toPay : false
     */

    private String agentPrice;
    private int categoryId;
    private String categoryName;
    private String content;
    private boolean freeShip;
    private int goodsId;
    private int parentCategoryId;
    private String parentCategoryName;
    private String price;
    private String realAgentPrice;
    private int stock;
    private SupplierBean supplier;
    private String title;
    private boolean toPay;
    private List<AttributesBean> attributes;
    private List<String> images;

    public String getAgentPrice() {
        return agentPrice;
    }

    public void setAgentPrice(String agentPrice) {
        this.agentPrice = agentPrice;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFreeShip() {
        return freeShip;
    }

    public void setFreeShip(boolean freeShip) {
        this.freeShip = freeShip;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(int parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getParentCategoryName() {
        return parentCategoryName;
    }

    public void setParentCategoryName(String parentCategoryName) {
        this.parentCategoryName = parentCategoryName;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public SupplierBean getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierBean supplier) {
        this.supplier = supplier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isToPay() {
        return toPay;
    }

    public void setToPay(boolean toPay) {
        this.toPay = toPay;
    }

    public List<AttributesBean> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributesBean> attributes) {
        this.attributes = attributes;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public static class SupplierBean {
        /**
         * headImg : https://file.taojianlou.com/ut/user/ewR5VDgZn3wZDs2RK0VqpCPYM0jNDisn.jpg
         * nickname : 超级无敌好
         * shopUri : 1911261024EWXDDW
         * supplierId : 147
         */

        private String headImg;
        private String nickname;
        private String shopUri;
        private int supplierId;

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
    }

    public static class AttributesBean {
        /**
         * id : 37
         * option : 1
         * tab :
         * title : 类型
         * value : 金丝楠
         */

        private int id;
        private int option;
        private String tab;
        private String title;
        private String value;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOption() {
            return option;
        }

        public void setOption(int option) {
            this.option = option;
        }

        public String getTab() {
            return tab;
        }

        public void setTab(String tab) {
            this.tab = tab;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
