package com.leo.auction.ui.main.mine.model;

import com.alibaba.fastjson.JSONObject;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;

import java.util.HashMap;
import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.mine.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/6
 * 描    述： 发布商品编辑
 * 修    改：
 * ===============================================
 */
public class ReleaseEditModel {


    /**
     * data : {"attributes":[{"id":3,"length":18,"option":0,"tab":"羊脂白玉,白玉,青白玉,青玉,等等","tags":[],"title":"品种","value":"android"},{"id":1,"length":18,"option":1,"tab":"","tags":[{"name":"籽料","tagId":4},{"name":"山流水料","tagId":2},{"name":"戈壁料","tagId":3},{"name":"山料","tagId":1}],"title":"产状","value":"籽料"},{"id":8,"length":18,"option":0,"tab":"龙龟,貔貅,财神等","tags":[],"title":"题材","value":"android"},{"id":7,"length":18,"option":1,"tab":"","tags":[{"name":"挂件","tagId":7},{"name":"戒指/戒面","tagId":16},{"name":"手串/手镯","tagId":9},{"name":"玉牌","tagId":6},{"name":"器皿","tagId":11},{"name":"把件","tagId":8},{"name":"原石","tagId":5},{"name":"耳饰/配饰","tagId":17},{"name":"摆件","tagId":10}],"title":"类型","value":"摆件"},{"id":6,"length":18,"option":0,"tab":"张克山,侯晓峰","tags":[],"title":"雕刻师","value":"之井"}],"categoryId":2,"categoryName":"和田玉","content":"heheheehhehe","cutPic":"https://file.taojianlou.com/ut/product/1594201862077.mp4?x-oss-process=video/snapshot,t_1,f_jpg,w_1080,h_1920,m_fast","distributeType":2,"images":["https://file.taojianlou.com/ut/product/vpQEeoLe52wNfJNrb8Uz6gfHmdLEhyJ8.png?image=300,300","https://file.taojianlou.com/ut/product/NNEIjzNaSWcV5yt0sjZUKk8lEL3orFqL.png?image=300,300","https://file.taojianlou.com/ut/product/E9ApatoPcPRrjn0Ruz2iJQZxvaW6ix6c.png?image=300,300","https://file.taojianlou.com/ut/product/NeFa34W7RqH7qymfcTF4I4XmctCLTKYA.png?image=300,300","https://file.taojianlou.com/ut/product/SDOE1lhQIJb1yjbl75foyBg3fiASwDdg.png?image=96,48","https://file.taojianlou.com/ut/product/Nq4wNAvvVETfGqKKC2ul8wYEWUUjvdvC.png?image=96,48","https://file.taojianlou.com/https://cd.taojianlou.com/ut/Android/goods/img20200708/1594198527398.png?image=1080,1920"],"markupRange":"122","parentCategoryId":1,"parentCategoryName":"玉翠珠宝","productId":99,"refund":true,"startPrice":"0","time":{"interceptTimeDay":2,"showText":"23:00","systemTime":1596695674000,"timeNode":82800,"timeNodeId":14,"type":"after_tomorrow"},"title":"安卓修改kkk","video":"https://file.taojianlou.com/ut/product/1594201862077.mp4"}
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1596695673547}
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
         * attributes : [{"id":3,"length":18,"option":0,"tab":"羊脂白玉,白玉,青白玉,青玉,等等","tags":[],"title":"品种","value":"android"},{"id":1,"length":18,"option":1,"tab":"","tags":[{"name":"籽料","tagId":4},{"name":"山流水料","tagId":2},{"name":"戈壁料","tagId":3},{"name":"山料","tagId":1}],"title":"产状","value":"籽料"},{"id":8,"length":18,"option":0,"tab":"龙龟,貔貅,财神等","tags":[],"title":"题材","value":"android"},{"id":7,"length":18,"option":1,"tab":"","tags":[{"name":"挂件","tagId":7},{"name":"戒指/戒面","tagId":16},{"name":"手串/手镯","tagId":9},{"name":"玉牌","tagId":6},{"name":"器皿","tagId":11},{"name":"把件","tagId":8},{"name":"原石","tagId":5},{"name":"耳饰/配饰","tagId":17},{"name":"摆件","tagId":10}],"title":"类型","value":"摆件"},{"id":6,"length":18,"option":0,"tab":"张克山,侯晓峰","tags":[],"title":"雕刻师","value":"之井"}]
         * categoryId : 2
         * categoryName : 和田玉
         * content : heheheehhehe
         * cutPic : https://file.taojianlou.com/ut/product/1594201862077.mp4?x-oss-process=video/snapshot,t_1,f_jpg,w_1080,h_1920,m_fast
         * distributeType : 2
         * images : ["https://file.taojianlou.com/ut/product/vpQEeoLe52wNfJNrb8Uz6gfHmdLEhyJ8.png?image=300,300","https://file.taojianlou.com/ut/product/NNEIjzNaSWcV5yt0sjZUKk8lEL3orFqL.png?image=300,300","https://file.taojianlou.com/ut/product/E9ApatoPcPRrjn0Ruz2iJQZxvaW6ix6c.png?image=300,300","https://file.taojianlou.com/ut/product/NeFa34W7RqH7qymfcTF4I4XmctCLTKYA.png?image=300,300","https://file.taojianlou.com/ut/product/SDOE1lhQIJb1yjbl75foyBg3fiASwDdg.png?image=96,48","https://file.taojianlou.com/ut/product/Nq4wNAvvVETfGqKKC2ul8wYEWUUjvdvC.png?image=96,48","https://file.taojianlou.com/https://cd.taojianlou.com/ut/Android/goods/img20200708/1594198527398.png?image=1080,1920"]
         * markupRange : 122
         * parentCategoryId : 1
         * parentCategoryName : 玉翠珠宝
         * productId : 99
         * refund : true
         * startPrice : 0
         * time : {"interceptTimeDay":2,"showText":"23:00","systemTime":1596695674000,"timeNode":82800,"timeNodeId":14,"type":"after_tomorrow"}
         * title : 安卓修改kkk
         * video : https://file.taojianlou.com/ut/product/1594201862077.mp4
         */

        private String categoryId;
        private String categoryName;
        private String content;
        private String cutPic;
        private int distributeType;
        private String markupRange;
        private String parentCategoryId;
        private String parentCategoryName;
        private int productId;
        private boolean refund;
        private String startPrice;
        private TimeBean time;
        private String title;
        private String video;
        private List<AttributesBean> attributes;
        private List<String> images;

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
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

        public String getCutPic() {
            return cutPic;
        }

        public void setCutPic(String cutPic) {
            this.cutPic = cutPic;
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

        public String getParentCategoryId() {
            return parentCategoryId;
        }

        public void setParentCategoryId(String parentCategoryId) {
            this.parentCategoryId = parentCategoryId;
        }

        public String getParentCategoryName() {
            return parentCategoryName;
        }

        public void setParentCategoryName(String parentCategoryName) {
            this.parentCategoryName = parentCategoryName;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
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

        public static class TimeBean {
            /**
             * interceptTimeDay : 2
             * showText : 23:00
             * systemTime : 1596695674000
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
             * value : android
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
         * timestamp : 1596695673547
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

    public static void httpReleaseEdit(String id, HttpRequest.HttpCallback httpCallback){
        JSONObject jsonObject = new  JSONObject();
        HttpRequest.httpPutString(Constants.Api.PRODUCT_URL+"/"+id,  jsonObject,httpCallback);
    }

    public static void httpReleaseEditGet(String id, HttpRequest.HttpCallback httpCallback){
        HashMap<String, String> hashMap = new HashMap<>();
        HttpRequest.httpGetString(Constants.Api.PRODUCT_URL+"/"+id,  hashMap,httpCallback);
    }
}
