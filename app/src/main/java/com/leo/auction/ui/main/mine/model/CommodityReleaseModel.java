package com.leo.auction.ui.main.mine.model;

import com.alibaba.fastjson.JSONObject;
import com.leo.auction.net.CustomerJsonCallBack;


import java.util.ArrayList;

public class CommodityReleaseModel {
    /**
     * data : {"saleCode":"商品编号"}
     * result : {"success":true,"message":"请求成功"}
     */

    private ResultBean result;



    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }


    public static class ResultBean {
        /**
         * success : true
         * message : 请求成功
         */

        private boolean success;
        private String message;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static void sendCommodityReleaseRequest(final String TAG, String categoryId, String shopUri, String title,
                                                   String content, String price, String stock, String agentPrice,
                                                   String freeShip, String toPay, String freight, ArrayList<String> images, ArrayList<String> videos,
                                                   String cutPic, ArrayList<ReleaseSortModel.DataBean.AttributesBean> attributes, String isPublish, String comment,
                                                   final CustomerJsonCallBack<CommodityReleaseModel> callback) {
        JSONObject params = new JSONObject();
        params.put("categoryId", categoryId);
        params.put("shopUri", shopUri);//店铺ID
        params.put("title", title);//商品标题
        params.put("content", content);//商品描述
        params.put("price", price);//销售价格
        params.put("stock", stock);//商品库存
        params.put("agentPrice", agentPrice);//代理价格
        params.put("freeShip", freeShip);//是否包邮
        params.put("toPay", toPay);//是否到付
        params.put("freight", freight);//运费
        params.put("images", images);//图片路径
        params.put("videos", videos);//视频路径
        params.put("cutPic", cutPic);//视频首帧图片地址
        params.put("attributes", attributes);
        params.put("isPublish", isPublish);//是否发布
        params.put("comment", comment);//商品备注

//        JsonRequestData.requesNetWork(TAG, Constants_Api.Api.HOMEPAGE_GOODS_PUBLISH_URL, params.toJSONString(), callback);
    }


    //商品修改
    public static void sendCommodityEditRequest(final String TAG, String id, String categoryId, String shopUri, String title,
                                                String content, String price, String stock, String agentPrice,
                                                String freeShip, String toPay, String freight, ArrayList<String> images, ArrayList<String> videos,
                                                String cutPic, ArrayList<ReleaseSortModel.DataBean.AttributesBean> attributes, String type, String comment,
                                                ArrayList<String> old,
                                                final CustomerJsonCallBack<CommodityReleaseModel> callback) {
        JSONObject params = new JSONObject();
        params.put("id", id);
        params.put("categoryId", categoryId);
        params.put("shopUri", shopUri);//店铺ID
        params.put("title", title);//商品标题
        params.put("content", content);//商品描述
        params.put("price", price);//销售价格
        params.put("stock", stock);//商品库存
        params.put("agentPrice", agentPrice);//代理价格
        params.put("freeShip", freeShip);//是否包邮
        params.put("toPay", toPay);//是否到付
        params.put("freight", freight);//运费
        params.put("images", images);//图片路径
        params.put("videos", videos);//视频路径
        params.put("cutPic", cutPic);//视频首帧图片地址
        params.put("attributes", attributes);
        params.put("type", type);//是否发布
        params.put("comment", comment);//商品备注
        params.put("old", old);//删除被替换得图片 或者视频链接

//        JsonRequestData.requesNetWork(TAG, Constants_Api.Api.HOMEPAGE_GOODS_EDIT_URL, params.toJSONString(), callback);
    }
}
