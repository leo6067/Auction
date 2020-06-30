package com.leo.auction.ui.main.mine.model;

import com.leo.auction.net.CustomerJsonCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.ui.home.model
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/12/19 0019
 * 描    述：
 * ================================================
 */
public class UpWaterPicModel {
    /**
     * savePath : 带水印的图片地址
     * result : {"success":true,"message":"请求成功"}
     */

    private String savePath;
    private ResultBean result;

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

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

    //获取更新数据
    public static void sendVersionRequest(final String TAG, File file,String type,final CustomerJsonCallBack<UpWaterPicModel> callback) {
//        OkHttpUtils.post()
//                .url(Constants_Api.Api.HOMEPAGE_UPLOAD_USER_WATERMARK_URL)
//                .addFile("file",file.getName(),file)
//                .addParams("type",type)
//                .tag(TAG)
//                .build()
//                .execute(callback);
    }
}
