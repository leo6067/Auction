package com.leo.auction.ui.main.mine.model;

import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;

import java.util.HashMap;
import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.mine.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/3
 * 描    述： 上拍拍品属性
 * 修    改：
 * ===============================================
 */
public class ReleaseAuctionAttrModel {


    /**
     * data : [{"id":9,"length":18,"option":1,"tab":"","tags":[{"name":"手镯","tagId":15},{"name":"挂件","tagId":7},{"name":"戒指/戒面","tagId":16},{"name":"摆件","tagId":10},{"name":"手串","tagId":18},{"name":"耳饰","tagId":19},{"name":"其他","tagId":20}],"title":"样式"},{"id":10,"length":18,"option":1,"tab":"","tags":[{"name":"天然翡翠A货","tagId":21},{"name":"经处理翡翠（B货及其他）","tagId":22}],"title":"类别"},{"id":11,"length":15,"option":0,"tab":"单位/g","tags":[],"title":"重量"},{"id":12,"length":20,"option":0,"tab":"内径/外径/长*宽*厚(mm)","tags":[],"title":"规格"}]
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1591173664938}
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
         * timestamp : 1591173664938
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
         * id : 9
         * length : 18
         * option : 1
         * tab :
         * tags : [{"name":"手镯","tagId":15},{"name":"挂件","tagId":7},{"name":"戒指/戒面","tagId":16},{"name":"摆件","tagId":10},{"name":"手串","tagId":18},{"name":"耳饰","tagId":19},{"name":"其他","tagId":20}]
         * title : 样式
         */

        private int id;
        private String length;
        private int option;
        private String tab;
        private String title;
        private List<TagsBean> tags;

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        private boolean select;

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLength() {
            return length;
        }

        public void setLength(String length) {
            this.length = length;
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

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public static class TagsBean {
            /**
             * name : 手镯
             * tagId : 15
             */

            private String name;
            private int tagId;

            private boolean select;

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getTagId() {
                return tagId;
            }

            public void setTagId(int tagId) {
                this.tagId = tagId;
            }
        }
    }


    public static void httpReleaseAttr(String categoryId, HttpRequest.HttpCallback httpCall) {

        HashMap<String, String> mhash = new HashMap<>();
        mhash.put("categoryId", categoryId);
        HttpRequest.httpGetString(Constants.Api.GOODS_DETAIL_ATTR_URL, mhash, httpCall);


    }
}
