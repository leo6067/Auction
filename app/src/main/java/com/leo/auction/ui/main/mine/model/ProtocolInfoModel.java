package com.leo.auction.ui.main.mine.model;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.utils.EmptyUtils;
import com.leo.auction.net.CustomerJsonCallBack;


public class ProtocolInfoModel {
    /**
     * data : {"content":"协议内容"}
     * result : {"success":true,"message":"请求成功"}
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
         * content : 协议内容
         */

        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
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

    public static void sendProtocolInfoRequest(final String TAG, String protocolId, String type,
                                               final CustomerJsonCallBack<ProtocolInfoModel> callback) {
        JSONObject params=new JSONObject();
        if (!EmptyUtils.isEmpty(protocolId)){
            params.put("protocolId",protocolId);
        }
        if (!EmptyUtils.isEmpty(type)){
            params.put("type",type);
        }

//        JsonRequestData.requesNetWork(TAG, Constants_Api.Api.HOMEPAGE_PROTOCOL_INFO_URL, params.toJSONString(), callback);
    }
}
