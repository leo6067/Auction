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
 * 时    间： 2020/8/5
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class SuperHouseModel {


    /**
     * data : [{"agentPrice":"55","firstPic":"https://file.taojianlou.com/ut/goods/1F51331B06F84EE89E1B36D6E0FB28A9.jpg?image=828,1104","freeShip":true,"goodsId":19,"stock":999,"title":"金狗旺福班章古树生茶","toPay":false},{"agentPrice":"39","firstPic":"https://file.taojianlou.com/ut/goods/B6FCA4BED752403EB8BB6B449D19DBF2.jpg?image=828,828","freeShip":true,"goodsId":20,"stock":999,"title":"昔归古树生茶","toPay":false},{"agentPrice":"888","firstPic":"https://file.taojianlou.com/ut/goods/D4429FA27CB943998061266E3F04D3A4.jpg","freeShip":true,"goodsId":24,"stock":1,"title":"和田碧玉呱呱来财","toPay":false}]
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1596713015885}
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
         * timestamp : 1596713015885
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
         * agentPrice : 55
         * firstPic : https://file.taojianlou.com/ut/goods/1F51331B06F84EE89E1B36D6E0FB28A9.jpg?image=828,1104
         * freeShip : true
         * goodsId : 19
         * stock : 999
         * title : 金狗旺福班章古树生茶
         * toPay : false
         */

        private String agentPrice;
        private String firstPic;
        private boolean freeShip;
        private int goodsId;
        private int stock;
        private String title;
        private boolean toPay;

        public String getAgentPrice() {
            return agentPrice;
        }

        public void setAgentPrice(String agentPrice) {
            this.agentPrice = agentPrice;
        }

        public String getFirstPic() {
            return firstPic;
        }

        public void setFirstPic(String firstPic) {
            this.firstPic = firstPic;
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

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
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
    }

    public static void httpGetSuperHouse(String keyword, String startPrice, String endPrice,
                                         String startStock, String endStock, String pageNum, String sort,
                                         String sortField, String categoryId,
                                         HttpRequest.HttpCallback httpCallback) {
        HashMap<String, String> hashMap = new HashMap<>();

        if (keyword.length() > 0) {
            hashMap.put("keyword", keyword);
        }

        if (startPrice.length() > 0) {
            hashMap.put("startPrice", startPrice);
        }
        if (endPrice.length()>0){
            hashMap.put("endPrice", endPrice);
        }

        if (startStock.length()>0){
            hashMap.put("startStock", startStock);
        }

        if (endStock.length()>0){
            hashMap.put("endStock", endStock);
        }


        if (sort.length()>0){
            hashMap.put("sort", sort);
        }


        if (sortField.length()>0){
            hashMap.put("sortField", sortField);
        }


        if (categoryId.length()>0){
            hashMap.put("categoryId", categoryId);
        }



        hashMap.put("pageNum", pageNum);
        hashMap.put("pageSize", Constants.Var.LIST_NUMBER);

        HttpRequest.httpGetString(Constants.Api.STOREHOUSE_URL, hashMap, httpCallback);


    }
}
