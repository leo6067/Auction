package com.leo.auction.ui.order.model;

import com.alibaba.fastjson.JSONObject;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * 提交订单  --提交评价
 */
public class CommitEvaluationModel {
    /**
     * orderCode : 订单编号
     * data : {"content":"评价内容","score":"12","label":63,"anonymous":1,"images":["图片"]}
     */

    private String orderCode;
    private DataBean data;

    public CommitEvaluationModel() {

    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * content : 评价内容
         * score : 12
         * label : 63
         * anonymous : 1
         * images : ["图片"]
         */

        private String content;
        private String score;
        private String label;
        private String anonymous;
        private List<String> images;

        public DataBean(String content, String anonymous, String score, ArrayList<String> images, String label) {
            this.content = content;
            this.score = score;
            this.label = label;
            this.anonymous = anonymous;
            this.images = images;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getAnonymous() {
            return anonymous;
        }

        public void setAnonymous(String anonymous) {
            this.anonymous = anonymous;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }



    /*{
    orderCode:"订单编号",
    data:{
        content:"评价内容",
        score:"12",
        label:63,
        anonymous:1,
        images:["图片"]
    }
}*/
//    public static void httpCommitEvaluation(CommitEvaluationModel dataBean, HttpRequest.HttpCallback httpCallback){
//
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("orderCode",dataBean.getOrderCode());
//        jsonObject.put("data",dataBean.getData());
//
//        HttpRequest.httpPostString(Constants.Api,jsonObject,httpCallback);
//
//
//    }
}
