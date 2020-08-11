package com.leo.auction.ui.main.home.model;

import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;

import java.util.HashMap;
import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.home.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/8/10
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class HotModel {


    /**
     * data : [{"headImg":"https://file.taojianlou.com/ut/product/gQr7imxhORXr0d80fMTcx04kpDLmg7VX.png?image=1200,1200","level":1,"nickname":"进炜的店铺1","shopUri":"1908091936M3Z6sI","productList":[{"currentPrice":"0","firstPic":"https://file.taojianlou.com/ut/product/vpQEeoLe52wNfJNrb8Uz6gfHmdLEhyJ8.png?image=300,300","productInstanceCode":"e0468cbd523ba00264bb6a5dda339bb5","productInstanceId":1310},{"currentPrice":"0","firstPic":"https://file.taojianlou.com/Android/goods/img20200804/1596527830251.png?image=640,853","productInstanceCode":"f7bb9a643431821f23fd9a2f6953792d","productInstanceId":1311},{"currentPrice":"0","firstPic":"https://file.taojianlou.com/ut/product/1596523005336.png?image=1080,720","productInstanceCode":"81f8ea58752930826ad7323febae709c","productInstanceId":1312}]}]
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1596984635041}
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
         * timestamp : 1596984635041
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
         * headImg : https://file.taojianlou.com/ut/product/gQr7imxhORXr0d80fMTcx04kpDLmg7VX.png?image=1200,1200
         * level : 1
         * nickname : 进炜的店铺1
         * shopUri : 1908091936M3Z6sI
         * productList : [{"currentPrice":"0","firstPic":"https://file.taojianlou.com/ut/product/vpQEeoLe52wNfJNrb8Uz6gfHmdLEhyJ8.png?image=300,300","productInstanceCode":"e0468cbd523ba00264bb6a5dda339bb5","productInstanceId":1310},{"currentPrice":"0","firstPic":"https://file.taojianlou.com/Android/goods/img20200804/1596527830251.png?image=640,853","productInstanceCode":"f7bb9a643431821f23fd9a2f6953792d","productInstanceId":1311},{"currentPrice":"0","firstPic":"https://file.taojianlou.com/ut/product/1596523005336.png?image=1080,720","productInstanceCode":"81f8ea58752930826ad7323febae709c","productInstanceId":1312}]
         */

        private String headImg;
        private int level;
        private String nickname;
        private String shopUri;
        private List<ProductListBean> productList;

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
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

        public String getShopUri() {
            return shopUri;
        }

        public void setShopUri(String shopUri) {
            this.shopUri = shopUri;
        }

        public List<ProductListBean> getProductList() {
            return productList;
        }

        public void setProductList(List<ProductListBean> productList) {
            this.productList = productList;
        }

        public static class ProductListBean {
            /**
             * currentPrice : 0
             * firstPic : https://file.taojianlou.com/ut/product/vpQEeoLe52wNfJNrb8Uz6gfHmdLEhyJ8.png?image=300,300
             * productInstanceCode : e0468cbd523ba00264bb6a5dda339bb5
             * productInstanceId : 1310
             */

            private String currentPrice;
            private String firstPic;
            private String productInstanceCode;
            private int productInstanceId;

            public String getCurrentPrice() {
                return currentPrice;
            }

            public void setCurrentPrice(String currentPrice) {
                this.currentPrice = currentPrice;
            }

            public String getFirstPic() {
                return firstPic;
            }

            public void setFirstPic(String firstPic) {
                this.firstPic = firstPic;
            }

            public String getProductInstanceCode() {
                return productInstanceCode;
            }

            public void setProductInstanceCode(String productInstanceCode) {
                this.productInstanceCode = productInstanceCode;
            }

            public int getProductInstanceId() {
                return productInstanceId;
            }

            public void setProductInstanceId(int productInstanceId) {
                this.productInstanceId = productInstanceId;
            }
        }
    }




    public static void httpGetHot(int pageNum,HttpRequest.HttpCallback httpCallback){


        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNum",pageNum+"");
        hashMap.put("pageSize", Constants.Var.LIST_NUMBER);

        HttpRequest.httpGetString(Constants.Api.HOT_URL,hashMap,httpCallback);


    }



}
