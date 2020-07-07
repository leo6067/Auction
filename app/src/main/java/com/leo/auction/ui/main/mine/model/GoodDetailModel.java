package com.leo.auction.ui.main.mine.model;

import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.mine.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/7
 * 描    述： 产品库产品详情
 * 修    改：
 * ===============================================
 */
public class GoodDetailModel {


    /**
     * data : {"goodsId":"134","title":"标题","content":"内容","stock":"库存","freeShip":true,"toPay":true,"agentPrice":"12","categoryId":16,"categoryName":"二级分类名称","parentCategoryId":32,"parentCategoryName":"一级分类名称","images":["图片1","图片2"],"video":"视频地址","cutPic":"视频首帧","attributes":[{"id":12,"title":"属性名称","tab":"描述","length":18,"option":1,"value":"属性值"}],"supplier":{"headImg":"https://file.taojianlou.com/ut/user/4E0F4EF593C146A7BA9C6BE8DC98E382.jpg","nickname":"天麒文玩品牌馆","shopUri":"1908101915fAfwBO","supplierId":11}}
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1591067862177}
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
         * goodsId : 134
         * title : 标题
         * content : 内容
         * stock : 库存
         * freeShip : true
         * toPay : true
         * agentPrice : 12
         * categoryId : 16
         * categoryName : 二级分类名称
         * parentCategoryId : 32
         * parentCategoryName : 一级分类名称
         * images : ["图片1","图片2"]
         * video : 视频地址
         * cutPic : 视频首帧
         * attributes : [{"id":12,"title":"属性名称","tab":"描述","length":18,"option":1,"value":"属性值"}]
         * supplier : {"headImg":"https://file.taojianlou.com/ut/user/4E0F4EF593C146A7BA9C6BE8DC98E382.jpg","nickname":"天麒文玩品牌馆","shopUri":"1908101915fAfwBO","supplierId":11}
         */

        private String goodsId;
        private String title;
        private String content;
        private String stock;
        private boolean freeShip;
        private boolean toPay;
        private String agentPrice;
        private int categoryId;
        private String categoryName;
        private int parentCategoryId;
        private String parentCategoryName;
        private String video;
        private String cutPic;
        private SupplierBean supplier;
        private List<String> images;
        private List<AttributesBean> attributes;

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public boolean isFreeShip() {
            return freeShip;
        }

        public void setFreeShip(boolean freeShip) {
            this.freeShip = freeShip;
        }

        public boolean isToPay() {
            return toPay;
        }

        public void setToPay(boolean toPay) {
            this.toPay = toPay;
        }

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

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getCutPic() {
            return cutPic;
        }

        public void setCutPic(String cutPic) {
            this.cutPic = cutPic;
        }

        public SupplierBean getSupplier() {
            return supplier;
        }

        public void setSupplier(SupplierBean supplier) {
            this.supplier = supplier;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public List<AttributesBean> getAttributes() {
            return attributes;
        }

        public void setAttributes(List<AttributesBean> attributes) {
            this.attributes = attributes;
        }

        public static class SupplierBean {
            /**
             * headImg : https://file.taojianlou.com/ut/user/4E0F4EF593C146A7BA9C6BE8DC98E382.jpg
             * nickname : 天麒文玩品牌馆
             * shopUri : 1908101915fAfwBO
             * supplierId : 11
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
             * id : 12
             * title : 属性名称
             * tab : 描述
             * length : 18
             * option : 1
             * value : 属性值
             */

            private int id;
            private String title;
            private String tab;
            private int length;
            private int option;
            private String value;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTab() {
                return tab;
            }

            public void setTab(String tab) {
                this.tab = tab;
            }

            public int getLength() {
                return length;
            }

            public void setLength(int length) {
                this.length = length;
            }

            public int getOption() {
                return option;
            }

            public void setOption(int option) {
                this.option = option;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }

    public static class ResultBean {
        /**
         * code : 0
         * message : 请求成功
         * success : true
         * timestamp : 1591067862177
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
