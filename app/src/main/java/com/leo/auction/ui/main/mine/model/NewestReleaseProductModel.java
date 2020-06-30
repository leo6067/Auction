package com.leo.auction.ui.main.mine.model;

import com.alibaba.fastjson.JSONObject;
import com.leo.auction.net.CustomerJsonCallBack;

import java.util.List;

public class NewestReleaseProductModel {
    /**
     * info : {"agentPrice":"0.01","attributes":[{"id":9,"option":1,"tab":"","title":"样式","value":"手镯","length":15},{"id":11,"length":15,"option":0,"tab":"单位/g","title":"重量","value":"10"},{"id":12,"length":20,"option":0,"tab":"内径/外径/长*宽*厚(mm)","title":"规格","value":"10"},{"id":10,"option":1,"tab":"","title":"类别","value":"天然翡翠A货"}],"categoryId":3,"categoryParentId":1,"comment":"不错不错不错不错","commentNum":0,"content":"测试商品房子弹头发的时候了吗丁啉本科了knee","freeShip":true,"freight":"0.00","headimg":"https://w.taojianlou.com/image/default/head.jpg","id":6889,"images":["https://file.taojianlou.com/ut/user/1575285090885.png?image=1008,1344"],"nickname":"安卓测试店铺","price":"0.02","saleCode":"1912021911387107","shopUri":"1910311042T8Zvq7","status":"00A","stock":992,"supplierId":141,"title":"测试Android卖家订单商品2","toPay":false,"videos":[]}
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1575292706365}
     */

    private InfoBean info;
    private ResultBean result;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class InfoBean {
        /**
         * agentPrice : 0.01
         * attributes : [{"id":9,"option":1,"tab":"","title":"样式","value":"手镯"},{"id":11,"length":15,"option":0,"tab":"单位/g","title":"重量","value":"10"},{"id":12,"length":20,"option":0,"tab":"内径/外径/长*宽*厚(mm)","title":"规格","value":"10"},{"id":10,"option":1,"tab":"","title":"类别","value":"天然翡翠A货"}]
         * categoryId : 3
         * categoryParentId : 1
         * comment : 不错不错不错不错
         * commentNum : 0
         * content : 测试商品房子弹头发的时候了吗丁啉本科了knee
         * freeShip : true
         * freight : 0.00
         * headimg : https://w.taojianlou.com/image/default/head.jpg
         * id : 6889
         * images : ["https://file.taojianlou.com/ut/user/1575285090885.png?image=1008,1344"]
         * nickname : 安卓测试店铺
         * price : 0.02
         * saleCode : 1912021911387107
         * shopUri : 1910311042T8Zvq7
         * status : 00A
         * stock : 992
         * supplierId : 141
         * title : 测试Android卖家订单商品2
         * toPay : false
         * videos : []
         */

        private String agentPrice;
        private String categoryId;
        private String categoryParentId;
        private String comment;
        private int commentNum;
        private String content;
        private boolean freeShip;
        private String freight;
        private String headimg;
        private String id;
        private String nickname;
        private String price;
        private String saleCode;
        private String shopUri;
        private String status;
        private String stock;
        private String supplierId;
        private String title;
        private boolean toPay;
        private List<AttributesBean> attributes;
        private List<String> images;
        private List<?> videos;

        public String getAgentPrice() {
            return agentPrice;
        }

        public void setAgentPrice(String agentPrice) {
            this.agentPrice = agentPrice;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryParentId() {
            return categoryParentId;
        }

        public void setCategoryParentId(String categoryParentId) {
            this.categoryParentId = categoryParentId;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
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

        public String getFreight() {
            return freight;
        }

        public void setFreight(String freight) {
            this.freight = freight;
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

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSaleCode() {
            return saleCode;
        }

        public void setSaleCode(String saleCode) {
            this.saleCode = saleCode;
        }

        public String getShopUri() {
            return shopUri;
        }

        public void setShopUri(String shopUri) {
            this.shopUri = shopUri;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(String supplierId) {
            this.supplierId = supplierId;
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

        public List<?> getVideos() {
            return videos;
        }

        public void setVideos(List<?> videos) {
            this.videos = videos;
        }

        public static class AttributesBean {
            /**
             * id : 9
             * option : 1
             * tab :
             * title : 样式
             * value : 手镯
             * length : 15
             */

            private String id;
            private String option;
            private String tab;
            private String title;
            private String value;
            private int length;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOption() {
                return option;
            }

            public void setOption(String option) {
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

            public int getLength() {
                return length;
            }

            public void setLength(int length) {
                this.length = length;
            }
        }
    }

    public static class ResultBean {
        /**
         * code : 0
         * message : 请求成功
         * success : true
         * timestamp : 1575292706365
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

    public static void sendNewestReleaseProductRequest(final String TAG, String shopUri,
                                                       final CustomerJsonCallBack<NewestReleaseProductModel> callback) {
        JSONObject params=new JSONObject();
        params.put("shopUri",shopUri);
//        JsonRequestData.requesNetWork(TAG, Constants_Api.Api.HOMEPAGE_GOODS_NEWEST_URL, params.toJSONString(), callback);
    }
}
