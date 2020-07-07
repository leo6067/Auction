package com.leo.auction.ui.main.mine.model;

import com.alibaba.fastjson.JSONObject;
import com.leo.auction.base.Constants;
import com.leo.auction.net.CustomerJsonCallBack;
import com.leo.auction.net.HttpRequest;

import java.util.HashMap;
import java.util.List;

/***
 * 回写 最新发布*/

public class NewestReleaseProductModel {


    /**
     * productId : 拍品标识
     * categoryId : 二级分类标识
     * parentCategoryId : 一级分类标识
     * markupRange : 1200
     * attributes : [{"id":"关联标识","title":"属性名称","tab":"描述","length":"长度","option":"是否必选","value":"属性值"}]
     */

    private String productId;
    private String categoryId;
    private String parentCategoryId;
    private int markupRange;
    private List<AttributesBean> attributes;



    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(String parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public int getMarkupRange() {
        return markupRange;
    }

    public void setMarkupRange(int markupRange) {
        this.markupRange = markupRange;
    }

    public List<AttributesBean> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributesBean> attributes) {
        this.attributes = attributes;
    }

    public static class AttributesBean {
        /**
         * id : 关联标识
         * title : 属性名称
         * tab : 描述
         * length : 长度
         * option : 是否必选
         * value : 属性值
         */

        private String id;
        private String title;
        private String tab;
        private String length;
        private String option;
        private String value;

        public String getId() {
            return id;
        }

        public void setId(String id) {
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

        public String getLength() {
            return length;
        }

        public void setLength(String length) {
            this.length = length;
        }

        public String getOption() {
            return option;
        }

        public void setOption(String option) {
            this.option = option;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }


    public static void httpNewestRelease(HttpRequest.HttpCallback httpCallback) {

        HashMap<String,String> mHash = new HashMap<>();
        HttpRequest.httpGetString(Constants.Api.GOODS_NEWEST_URL, mHash, httpCallback);
    }
}
