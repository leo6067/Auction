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
 * 时    间： 2020/7/7
 * 描    述： 拍品回写
 * 修    改：
 * ===============================================
 */
public class NewestModel {


    /**
     * data : {"attributes":[{"id":3,"length":18,"option":0,"tab":"羊脂白玉,白玉,青白玉,青玉,等等","tags":[],"title":"品种","value":"品种未知"},{"id":1,"length":18,"option":1,"tab":"","tags":[{"name":"戈壁料","tagId":3},{"name":"山料","tagId":1},{"name":"籽料","tagId":4},{"name":"山流水料","tagId":2}],"title":"产状","value":"籽料"},{"id":8,"length":18,"option":0,"tab":"龙龟,貔貅,财神等","tags":[],"title":"题材","value":"题材无"},{"id":7,"length":18,"option":1,"tab":"","tags":[{"name":"玉牌","tagId":6},{"name":"器皿","tagId":11},{"name":"把件","tagId":8},{"name":"原石","tagId":5},{"name":"耳饰/配饰","tagId":17},{"name":"摆件","tagId":10},{"name":"挂件","tagId":7},{"name":"戒指/戒面","tagId":16},{"name":"手串/手镯","tagId":9}],"title":"类型","value":"手串/手镯"},{"id":6,"length":18,"option":0,"tab":"张克山,侯晓峰","tags":[],"title":"雕刻师","value":"之井"}],"categoryId":2,"distributeType":2,"markupRange":"1","parentCategoryId":1,"productId":99,"refund":true,"startPrice":"0","time":{"interceptTimeDay":2,"showText":"23:00","systemTime":1594135856000,"timeNode":82800,"timeNodeId":14,"type":"after_tomorrow"}}
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1594135855594}
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
         * attributes : [{"id":3,"length":18,"option":0,"tab":"羊脂白玉,白玉,青白玉,青玉,等等","tags":[],"title":"品种","value":"品种未知"},{"id":1,"length":18,"option":1,"tab":"","tags":[{"name":"戈壁料","tagId":3},{"name":"山料","tagId":1},{"name":"籽料","tagId":4},{"name":"山流水料","tagId":2}],"title":"产状","value":"籽料"},{"id":8,"length":18,"option":0,"tab":"龙龟,貔貅,财神等","tags":[],"title":"题材","value":"题材无"},{"id":7,"length":18,"option":1,"tab":"","tags":[{"name":"玉牌","tagId":6},{"name":"器皿","tagId":11},{"name":"把件","tagId":8},{"name":"原石","tagId":5},{"name":"耳饰/配饰","tagId":17},{"name":"摆件","tagId":10},{"name":"挂件","tagId":7},{"name":"戒指/戒面","tagId":16},{"name":"手串/手镯","tagId":9}],"title":"类型","value":"手串/手镯"},{"id":6,"length":18,"option":0,"tab":"张克山,侯晓峰","tags":[],"title":"雕刻师","value":"之井"}]
         * categoryId : 2
         * distributeType : 2
         * markupRange : 1
         * parentCategoryId : 1
         * productId : 99
         * refund : true
         * startPrice : 0
         * time : {"interceptTimeDay":2,"showText":"23:00","systemTime":1594135856000,"timeNode":82800,"timeNodeId":14,"type":"after_tomorrow"}
         */

        private int categoryId;
        private int distributeType;
        private String markupRange;
        private int parentCategoryId;
        private int productId;
        private boolean refund;
        private String startPrice;
        private String comment;
        private TimeBean time;
        private List<AttributesBean> attributes;

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public int getDistributeType() {
            return distributeType;
        }

        public void setDistributeType(int distributeType) {
            this.distributeType = distributeType;
        }

        public String getMarkupRange() {
            return markupRange;
        }

        public void setMarkupRange(String markupRange) {
            this.markupRange = markupRange;
        }

        public int getParentCategoryId() {
            return parentCategoryId;
        }

        public void setParentCategoryId(int parentCategoryId) {
            this.parentCategoryId = parentCategoryId;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public boolean isRefund() {
            return refund;
        }

        public void setRefund(boolean refund) {
            this.refund = refund;
        }

        public String getStartPrice() {
            return startPrice;
        }

        public void setStartPrice(String startPrice) {
            this.startPrice = startPrice;
        }

        public TimeBean getTime() {
            return time;
        }

        public void setTime(TimeBean time) {
            this.time = time;
        }

        public List<AttributesBean> getAttributes() {
            return attributes;
        }

        public void setAttributes(List<AttributesBean> attributes) {
            this.attributes = attributes;
        }

        public static class TimeBean {
            /**
             * interceptTimeDay : 2
             * showText : 23:00
             * systemTime : 1594135856000
             * timeNode : 82800
             * timeNodeId : 14
             * type : after_tomorrow
             */

            private int interceptTimeDay;
            private String showText;
            private long systemTime;
            private int timeNode;
            private int timeNodeId;
            private String type;

            public int getInterceptTimeDay() {
                return interceptTimeDay;
            }

            public void setInterceptTimeDay(int interceptTimeDay) {
                this.interceptTimeDay = interceptTimeDay;
            }

            public String getShowText() {
                return showText;
            }

            public void setShowText(String showText) {
                this.showText = showText;
            }

            public long getSystemTime() {
                return systemTime;
            }

            public void setSystemTime(long systemTime) {
                this.systemTime = systemTime;
            }

            public int getTimeNode() {
                return timeNode;
            }

            public void setTimeNode(int timeNode) {
                this.timeNode = timeNode;
            }

            public int getTimeNodeId() {
                return timeNodeId;
            }

            public void setTimeNodeId(int timeNodeId) {
                this.timeNodeId = timeNodeId;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class AttributesBean {
            /**
             * id : 3
             * length : 18
             * option : 0
             * tab : 羊脂白玉,白玉,青白玉,青玉,等等
             * tags : []
             * title : 品种
             * value : 品种未知
             */

            private int id;
            private int length;
            private int option;
            private String tab;
            private String title;
            private String value;
            private List<?> tags;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public List<?> getTags() {
                return tags;
            }

            public void setTags(List<?> tags) {
                this.tags = tags;
            }
        }
    }

    public static class ResultBean {
        /**
         * code : 0
         * message : 请求成功
         * success : true
         * timestamp : 1594135855594
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

    //已失效上架回写
    public static void httpNewest(HttpRequest.HttpCallback httpCallback){

        HashMap<String, String> hashMap = new HashMap<>();
        HttpRequest.httpGetString(Constants.Api.INSTANCE_NEWEST_URL,hashMap,httpCallback);
    }

    //草稿箱上架回写
    public static void httpNewestDraft(HttpRequest.HttpCallback httpCallback){

        HashMap<String, String> hashMap = new HashMap<>();
        HttpRequest.httpGetString(Constants.Api.GOODS_NEWEST_URL,hashMap,httpCallback);
    }
}
