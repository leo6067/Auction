package com.leo.auction.ui.version;

import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;

import java.util.HashMap;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.version
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/17
 * 描    述： 版本更新
 * 修    改：
 * ===============================================
 */
public class VersionModel {


    /**
     * data : {"createTime":1593741928000,"download":"","force":false,"version":"100"}
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1593742284294}
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
         * createTime : 1593741928000
         * download :
         * force : false
         * version : 100
         */

        private long createTime;
        private String download;
        private boolean force;
        private String version;

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getDownload() {
            return download;
        }

        public void setDownload(String download) {
            this.download = download;
        }

        public boolean isForce() {
            return force;
        }

        public void setForce(boolean force) {
            this.force = force;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

    public static class ResultBean {
        /**
         * code : 0
         * message : 请求成功
         * success : true
         * timestamp : 1593742284294
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


    public static void httpGetVersion(HttpRequest.HttpCallback httpCall){


        HashMap<String, String> hashMap = new HashMap<>();


        HttpRequest.httpGetStringWeb(Constants.Api.VERSION_URL,hashMap,httpCall);




    }
}
