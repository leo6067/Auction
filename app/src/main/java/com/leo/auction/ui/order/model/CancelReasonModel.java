package com.leo.auction.ui.order.model;

import com.alibaba.fastjson.JSONObject;
import com.leo.auction.net.CustomerJsonCallBack;

import java.util.List;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.ui.order.model
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/12/18 0018
 * 描    述：
 * ================================================
 */
public class CancelReasonModel {
    /**
     * data : ["我不想买了","信息填错重新拍","卖家缺货","同城交易","其他"]
     * result : {"success":true,"message":"请求成功"}
     */

    private ResultBean result;
    private List<String> data;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
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

    public static void sendCancelReasonRequest(final String TAG, String type, final CustomerJsonCallBack<CancelReasonModel> callback) {
        JSONObject params = new JSONObject();
//        JsonRequestData.requesNetWork(TAG, Constants_Api.Api.HOMEPAGE_ORDER_CANCEL_REASON_URL+"?type="+type, params.toJSONString(), callback);
    }
}
