package com.leo.auction.ui.main.mine.model;

import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;

import java.util.HashMap;
import java.util.List;

/***
 * 回写 最新发布*/

public class NewestReleaseProductModel {


    /**
     * data : {"attributes":[{"id":171,"length":18,"option":1,"tab":"","tags":[],"title":"材质","value":"嗯"},{"id":37,"option":1,"tab":"","tags":[{"name":"桃木","tagId":57},{"name":"酸枝","tagId":54},{"name":"鸡翅木","tagId":56},{"name":"金丝楠","tagId":53},{"name":"其他","tagId":20},{"name":"黄杨木","tagId":55}],"title":"类型","value":"鸡翅木"}],"categoryId":14,"comment":"","distributeType":2,"markupRange":"66","parentCategoryId":13,"productId":258,"refund":true,"startPrice":"0","time":{"interceptTimeDay":1,"showText":"20:00","systemTime":1595831679000,"timeNode":72000,"timeNodeId":11,"type":"tomorrow"}}
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1595831679079}
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
         * attributes : [{"id":171,"length":18,"option":1,"tab":"","tags":[],"title":"材质","value":"嗯"},{"id":37,"option":1,"tab":"","tags":[{"name":"桃木","tagId":57},{"name":"酸枝","tagId":54},{"name":"鸡翅木","tagId":56},{"name":"金丝楠","tagId":53},{"name":"其他","tagId":20},{"name":"黄杨木","tagId":55}],"title":"类型","value":"鸡翅木"}]
         * categoryId : 14
         * comment :
         * distributeType : 2
         * markupRange : 66
         * parentCategoryId : 13
         * productId : 258
         * refund : true
         * startPrice : 0
         * time : {"interceptTimeDay":1,"showText":"20:00","systemTime":1595831679000,"timeNode":72000,"timeNodeId":11,"type":"tomorrow"}
         */

        private String categoryId;
        private String comment;
        private String distributeType;
        private String markupRange;
        private String parentCategoryId;
        private int productId;
        private boolean refund;
        private String startPrice;
        private TimeBean time;
        private List<AttributesBean> attributes;

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getDistributeType() {
            return distributeType;
        }

        public void setDistributeType(String distributeType) {
            this.distributeType = distributeType;
        }

        public String getMarkupRange() {
            return markupRange;
        }

        public void setMarkupRange(String markupRange) {
            this.markupRange = markupRange;
        }

        public String getParentCategoryId() {
            return parentCategoryId;
        }

        public void setParentCategoryId(String parentCategoryId) {
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
             * interceptTimeDay : 1
             * showText : 20:00
             * systemTime : 1595831679000
             * timeNode : 72000
             * timeNodeId : 11
             * type : tomorrow
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
             * id : 171
             * length : 18
             * option : 1
             * tab :
             * tags : []
             * title : 材质
             * value : 嗯
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
         * timestamp : 1595831679079
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


    public static void httpNewestRelease(HttpRequest.HttpCallback httpCallback) {

        HashMap<String,String> mHash = new HashMap<>();
        HttpRequest.httpGetString(Constants.Api.GOODS_NEWEST_URL, mHash, httpCallback);
    }

}
