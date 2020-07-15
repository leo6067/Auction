package com.leo.auction.ui.main.sort;

import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;

import java.util.HashMap;
import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.sort
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/10
 * 描    述： 关注店铺列表
 * 修    改：
 * ===============================================
 */
public class SortShopModel  {


    /**
     * data : [{"id":1969,"newestNum":7,"productUser":{"fansNum":56,"headImg":"https://file.taojianlou.com/ut/user/a0ca63942b933ffffca8b73f0ba005f2.jpg","id":1969,"level":0,"nickname":"贝贝佛珠\u2014转过来","rate":5,"sellerLevel":2,"userId":"1911181748xBeu8P"},"top":false}]
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1594371621957}
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
         * timestamp : 1594371621957
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
         * id : 1969
         * newestNum : 7
         * productUser : {"fansNum":56,"headImg":"https://file.taojianlou.com/ut/user/a0ca63942b933ffffca8b73f0ba005f2.jpg","id":1969,"level":0,"nickname":"贝贝佛珠\u2014转过来","rate":5,"sellerLevel":2,"userId":"1911181748xBeu8P"}
         * top : false
         */

        private int id;
        private int newestNum;
        private ProductUserBean productUser;
        private boolean top;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNewestNum() {
            return newestNum;
        }

        public void setNewestNum(int newestNum) {
            this.newestNum = newestNum;
        }

        public ProductUserBean getProductUser() {
            return productUser;
        }

        public void setProductUser(ProductUserBean productUser) {
            this.productUser = productUser;
        }

        public boolean isTop() {
            return top;
        }

        public void setTop(boolean top) {
            this.top = top;
        }

        public static class ProductUserBean {
            /**
             * fansNum : 56
             * headImg : https://file.taojianlou.com/ut/user/a0ca63942b933ffffca8b73f0ba005f2.jpg
             * id : 1969
             * level : 0
             * nickname : 贝贝佛珠—转过来
             * rate : 5.0
             * sellerLevel : 2
             * userId : 1911181748xBeu8P
             */

            private String fansNum;
            private String headImg;
            private int id;
            private int level;
            private String nickname;
            private String rate;
            private int sellerLevel;
            private String userId;

            public String getFansNum() {
                return fansNum;
            }

            public void setFansNum(String fansNum) {
                this.fansNum = fansNum;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getRate() {
                return rate;
            }

            public void setRate(String rate) {
                this.rate = rate;
            }

            public int getSellerLevel() {
                return sellerLevel;
            }

            public void setSellerLevel(int sellerLevel) {
                this.sellerLevel = sellerLevel;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }
        }
    }

    public static void httpSort(String keyword, String pageNum, HttpRequest.HttpCallback httpCallback){

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("keyword",keyword);
        hashMap.put("pageNum",pageNum);
        hashMap.put("pageSize",Constants.Var.LIST_NUMBER);
        HttpRequest.httpGetString(Constants.Api.SORT_FOLLOW_SHOP_URL,hashMap,httpCallback);
    }

}
