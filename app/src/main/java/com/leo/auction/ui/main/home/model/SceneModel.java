package com.leo.auction.ui.main.home.model;

import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;

import java.util.HashMap;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.home.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/24
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class SceneModel {


    /**
     * data : {"h5Url":"www.baidu.com","content":"xxxx","redirectType":1}
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1594633580240}
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
         * h5Url : www.baidu.com
         * content : xxxx
         * redirectType : 1
         */

        private String h5Url;
        private String content;
        private int redirectType;

        public String getH5Url() {
            return h5Url;
        }

        public void setH5Url(String h5Url) {
            this.h5Url = h5Url;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getRedirectType() {
            return redirectType;
        }

        public void setRedirectType(int redirectType) {
            this.redirectType = redirectType;
        }
    }

    public static class ResultBean {
        /**
         * code : 0
         * message : 请求成功
         * success : true
         * timestamp : 1594633580240
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





    public static void httpGetScene(String scene, HttpRequest.HttpCallback httpCallback){


        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("scene",scene);  //1-锤定交易服务用户协议  2-锤定交易服务协议   3-隐私保护政策 4-出价补贴说明 5-购买补贴说明 6-增加粉丝说明

        HttpRequest.httpGetString(Constants.Api.SCENE_URL,hashMap,httpCallback);
    }
}
