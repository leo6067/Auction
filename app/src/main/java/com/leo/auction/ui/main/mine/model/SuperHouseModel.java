package com.leo.auction.ui.main.mine.model;

import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;

import java.util.HashMap;

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
     * data : {"goodsId":"134","title":"标题","stock":"库存","freeShip":true,"agentPrice":"12","firstPic":"主图"}
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1591067862177}
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
         * goodsId : 134
         * title : 标题
         * stock : 库存
         * freeShip : true
         * agentPrice : 12
         * firstPic : 主图
         */

        private String goodsId;
        private String title;
        private String stock;
        private boolean freeShip;
        private String agentPrice;
        private String firstPic;

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public boolean isFreeShip() {
            return freeShip;
        }

        public void setFreeShip(boolean freeShip) {
            this.freeShip = freeShip;
        }

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
    }

    public static class ResultBean {
        /**
         * code : 0
         * message : 请求成功
         * success : true
         * timestamp : 1591067862177
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



    public static void httpGetSuperHouse( String keyword, String  startPrice ,String endPrice,
                      String startStock, String endStock, String pageNum,String sort,
            String sortField,String categoryId,
            HttpRequest.HttpCallback httpCallback){
        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("keyword",keyword);
        hashMap.put("startPrice",startPrice);
        hashMap.put("endPrice",endPrice);
        hashMap.put("startStock",startStock);
        hashMap.put("endStock",endStock);
        hashMap.put("pageNum",pageNum);
        hashMap.put("pageSize", Constants.Var.LIST_NUMBER);
        hashMap.put("sort",sort);
        hashMap.put("sortField",sortField);
        hashMap.put("categoryId",categoryId);

        HttpRequest.httpGetString(Constants.Api.STOREHOUSE_URL,hashMap,httpCallback);


    }
}
