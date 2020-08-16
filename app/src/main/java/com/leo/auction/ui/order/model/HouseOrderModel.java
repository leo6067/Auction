package com.leo.auction.ui.order.model;

import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.order.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/8/16
 * 描    述：超级仓库---一键代发  --web
 * 修    改：
 * ===============================================
 */
public class HouseOrderModel {


    /**
     * addressVo : {"addr1Name":"","addr2Name":"","addr3Name":"","address":"北京市北京市东城区榜头镇天易世博","code":"000000","linkman":"谢伟杰","phone":"17750656067"}
     * goods : {"agentPrice":"3","attributes":[{"id":37,"option":1,"tab":"","title":"类型","value":"金丝楠"}],"categoryId":14,"categoryName":"木质珠串","content":"好货不怕鬼，好货怕错过","freeShip":false,"goodsId":6887,"images":["https://file.taojianlou.com/ut/goods/1588993297130.png?image=1080,2250","https://file.taojianlou.com/ut/goods/1588993288779.png?image=1080,1920","https://file.taojianlou.com/ut/goods/1588993289203.png?image=1125,2436","https://file.taojianlou.com/ut/goods/1588993289670.png?image=1125,2436","https://file.taojianlou.com/ut/goods/1588993290163.png?image=1125,2436","https://file.taojianlou.com/ut/goods/1588993290604.png?image=1125,2436","https://file.taojianlou.com/ut/user/1575277841770.png?image=800,800","https://file.taojianlou.com/ut/user/1575277842106.png?image=800,800"],"parentCategoryId":13,"parentCategoryName":"工艺作品","price":"11","realAgentPrice":"1","stock":23,"supplier":{"headImg":"https://file.taojianlou.com/ut/user/zXtxJF0ynbk1OHgkHKtME64fa3jUPOjc.jpg","nickname":"贝贝佛珠","shopUri":"1911181748xBeu8P","supplierId":143},"title":"App专业商品","toPay":false}
     * orderToken : 3a90ee0686820839425efa338783781f
     * sendPhone : 13904458487
     * sender : 管先生
     */

    private AddressVoBean addressVo;
    private GoodsBean goods;
    private String orderToken;
    private String sendPhone;
    private String sender;

    public AddressVoBean getAddressVo() {
        return addressVo;
    }

    public void setAddressVo(AddressVoBean addressVo) {
        this.addressVo = addressVo;
    }

    public GoodsBean getGoods() {
        return goods;
    }

    public void setGoods(GoodsBean goods) {
        this.goods = goods;
    }

    public String getOrderToken() {
        return orderToken;
    }

    public void setOrderToken(String orderToken) {
        this.orderToken = orderToken;
    }

    public String getSendPhone() {
        return sendPhone;
    }

    public void setSendPhone(String sendPhone) {
        this.sendPhone = sendPhone;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public static class AddressVoBean {
        /**
         * addr1Name :
         * addr2Name :
         * addr3Name :
         * address : 北京市北京市东城区榜头镇天易世博
         * code : 000000
         * linkman : 谢伟杰
         * phone : 17750656067
         */

        private String addr1Name;
        private String addr2Name;
        private String addr3Name;
        private String address;
        private String code;
        private String linkman;
        private String phone;

        public String getAddr1Name() {
            return addr1Name;
        }

        public void setAddr1Name(String addr1Name) {
            this.addr1Name = addr1Name;
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
    }

    public static class GoodsBean {
        /**
         * agentPrice : 3
         * attributes : [{"id":37,"option":1,"tab":"","title":"类型","value":"金丝楠"}]
         * categoryId : 14
         * categoryName : 木质珠串
         * content : 好货不怕鬼，好货怕错过
         * freeShip : false
         * goodsId : 6887
         * images : ["https://file.taojianlou.com/ut/goods/1588993297130.png?image=1080,2250","https://file.taojianlou.com/ut/goods/1588993288779.png?image=1080,1920","https://file.taojianlou.com/ut/goods/1588993289203.png?image=1125,2436","https://file.taojianlou.com/ut/goods/1588993289670.png?image=1125,2436","https://file.taojianlou.com/ut/goods/1588993290163.png?image=1125,2436","https://file.taojianlou.com/ut/goods/1588993290604.png?image=1125,2436","https://file.taojianlou.com/ut/user/1575277841770.png?image=800,800","https://file.taojianlou.com/ut/user/1575277842106.png?image=800,800"]
         * parentCategoryId : 13
         * parentCategoryName : 工艺作品
         * price : 11
         * realAgentPrice : 1
         * stock : 23
         * supplier : {"headImg":"https://file.taojianlou.com/ut/user/zXtxJF0ynbk1OHgkHKtME64fa3jUPOjc.jpg","nickname":"贝贝佛珠","shopUri":"1911181748xBeu8P","supplierId":143}
         * title : App专业商品
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
             * headImg : https://file.taojianlou.com/ut/user/zXtxJF0ynbk1OHgkHKtME64fa3jUPOjc.jpg
             * nickname : 贝贝佛珠
             * shopUri : 1911181748xBeu8P
             * supplierId : 143
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
}
