package com.leo.auction.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leo.auction.net.CustomerJsonCallBack;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.home.model.SortLeftModel;
import com.leo.auction.ui.main.mine.model.ReleaseAuctionAttrModel;
import com.leo.auction.ui.main.mine.model.ReleaseEditModel;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.utils.Globals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Leo on 2019/4/10.
 */

public class BaseModel {
    /**
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

        @Override
        public String toString() {
            return "ResultBean{" +
                    "success=" + success +
                    ", message='" + message + '\'' +
                    '}';
        }
    }


    //密码
    //设置支付密码
    public static void sendUserAddpaypwdRequest(final String payPwd,
                                                final CustomerJsonCallBack<BaseModel> callback) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("payPwd", payPwd);
        HttpRequest.httpPostString(Constants.Api.PAY_PWD_URL, jsonObject, callback);
    }

    //重置支付密码
    public static void sendUserResetpaypwdRequest(String oldPayPwd, String payPwd,
                                                  final CustomerJsonCallBack<BaseModel> callback) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("payPwd", oldPayPwd);
        jsonObject.put("newPayPwd", payPwd);
        HttpRequest.httpPostString(Constants.Api.RESET_PWD_URL, jsonObject, callback);
    }

    //忘记支付密码
    public static void sendUserForgetpaypwdRequest(final String TAG, String payPwd, String phone,
                                                   final CustomerJsonCallBack<BaseModel> callback) {
        JSONObject value = new JSONObject();
        value.put("newPayPwd", payPwd);
        value.put("phone", phone);
        HttpRequest.httpPostString(Constants.Api.RESET_PWD_URL, value, callback);
    }


    //用户信息
    public static void httpUserName(String nickName, HttpRequest.HttpCallback httpCallback) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nickname", nickName);
        HttpRequest.httpPostString(Constants.Api.USER_URL, jsonObject, httpCallback);

    }

    public static void httpUserHeadImg(String headImg, HttpRequest.HttpCallback httpCallback) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("headImg", headImg);
        HttpRequest.httpPostString(Constants.Api.USER_URL, jsonObject, httpCallback);
    }


    public static void httpUserPhone(String phone, String code, HttpRequest.HttpCallback httpCallback) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phone", phone);
        jsonObject.put("code", code);
        HttpRequest.httpPostString(Constants.Api.USER_URL, jsonObject, httpCallback);
    }


    //发布拍品
    public static void httpReleaseGoods(String title, String content,
                                        String categoryId, String comment,
                                        String startPrice, String markupRange,
                                        ArrayList<String> video, String cutPic,
                                        String sourceType, String actionType,
                                        String distributeType, String timeNodeId,
                                        String type, ArrayList<ReleaseAuctionAttrModel.DataBean> attributes,
                                        ArrayList<String> images,
                                        String goodsId,
                                        HttpRequest.HttpCallback httpCallback) {

        JSONObject params = new JSONObject();
        params.put("title", title);
        params.put("content", content);//商品描述
        params.put("categoryId", categoryId);//'所属分类'
        params.put("comment", comment);//'备注'
        params.put("startPrice", startPrice);//'起拍价'
        params.put("markupRange", markupRange);//'加价幅度'
        if (video.size() > 0) {
            params.put("video", video);//'视频路径'
            params.put("cutPic", cutPic);//''视频首帧''
        }
        params.put("sourceType", sourceType);//''来源类型 1-自行发布 2-产品库',''
        params.put("actionType", actionType);//''动作类型 1-保存 2-发拍',''

        params.put("distributeType", distributeType);//1-包邮  2-到付
        params.put("refund", "1");//
        params.put("goodsId", goodsId);//商品标识  如果引用的产品库的商品  要将商品标识传递过来


        JSONObject time = new JSONObject();
        time.put("timeNodeId", timeNodeId);//
        time.put("type", type);//

        params.put("time", time);//'时间节点标识'
        params.put("attributes", attributes);//
        params.put("images", images);//
        HttpRequest.httpPostString(Constants.Api.PRODUCT_URL, params, httpCallback);
    }


    //发布拍品 ---修改编辑拍品
    public static void httpReleaseEditGoods(String title, String content,
                                            String categoryId, String comment,
                                            String startPrice, String markupRange,
                                            ArrayList<String> video, String cutPic,
                                            String sourceType, String actionType,
                                            String distributeType, String timeNodeId,
                                            String type, ArrayList<ReleaseAuctionAttrModel.DataBean> attributes,
                                            ArrayList<String> images,
                                            String goodsId, String productId,
                                            HttpRequest.HttpCallback httpCallback) {

        JSONObject params = new JSONObject();
        params.put("title", title);
        params.put("content", content);//商品描述
        params.put("categoryId", categoryId);//'所属分类'
        params.put("comment", comment);//'备注'
        params.put("startPrice", startPrice);//'起拍价'
        params.put("markupRange", markupRange);//'加价幅度'
        params.put("productId", productId);//'商品id'
        if (video.size() > 0) {
            params.put("video", video);//'视频路径'
            params.put("cutPic", cutPic);//''视频首帧''
        }

        params.put("sourceType", sourceType);//''来源类型 1-自行发布 2-产品库',''
        params.put("actionType", actionType);//''动作类型 1-保存 2-发拍',''

        params.put("distributeType", distributeType);//1-包邮  2-到付
        params.put("refund", "1");//
        params.put("goodsId", goodsId);//商品标识  如果引用的产品库的商品  要将商品标识传递过来


        JSONObject time = new JSONObject();
        time.put("timeNodeId", timeNodeId);//
        time.put("type", type);//

        params.put("time", time);//'时间节点标识'
        params.put("attributes", attributes);//
        params.put("images", images);//

        Globals.log("xxxxxxxx修改 商品" + params.toString());


        HttpRequest.httpPutString(Constants.Api.PRODUCT_URL, params, httpCallback);
    }


    //拍品管理--上架
    public static void httpUpper(List<ReleaseEditModel.DataBean.AttributesBean> attributes, String categoryId, String comment, String content,
                                 String cutPic, int distributeType, String goodsId, List<String> images,
                                 String markupRange, String sourceType, String startPrice, String timeNode,
                                 String timeNodeId, String type, String title, String video,
                                 HttpRequest.HttpCallback httpCallback) {

        JSONObject time = new JSONObject();

        time.put("timeNode", timeNode);
        time.put("timeNodeId", timeNodeId);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("actionType", 2);
        jsonObject.put("attributes", attributes);
        jsonObject.put("categoryId", categoryId);
        jsonObject.put("comment", comment);
        jsonObject.put("content", content);
//        if (video.size() > 0) {
//            params.put("video", video);//'视频路径'
//            params.put("cutPic", cutPic);//''视频首帧''
//        }


        jsonObject.put("cutPic", cutPic);
        jsonObject.put("video", video);
        jsonObject.put("distributeType", distributeType);

        if (sourceType.equals("2")) {
            jsonObject.put("goodsId", goodsId);
        }


        jsonObject.put("images", images);
        jsonObject.put("markupRange", markupRange);
        jsonObject.put("sourceType", sourceType);
        jsonObject.put("startPrice", startPrice);
        jsonObject.put("time", time);
        jsonObject.put("type", type);
        jsonObject.put("title", title);


        HttpRequest.httpPostString(Constants.Api.GOODS_UPPER_URL, jsonObject, httpCallback);

    }


    //下架--
    public static void httpDownAuction(String productInstanceCode, HttpRequest.HttpCallback httpCallback) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("productInstanceCode", productInstanceCode);
        HttpRequest.httpPutString(Constants.Api.GOODS_LOWER_URL, jsonObject, httpCallback);
    }

    //删除
    public static void httpDeleteAuction(String productId, HttpRequest.HttpCallback httpCallback) {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("productId", productId);
        HttpRequest.httpDeleteString(Constants.Api.PRODUCT_DRAFT_URL, hashMap, httpCallback);
    }


    //删除文件
    public static void httpDeteleFile(String filePath, ArrayList<String> filePaths, HttpRequest.HttpCallback httpCallback) {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("file", filePath);
        if (filePaths != null && filePaths.size() > 0) {
            jsonObject.put("files", filePaths);
        }

        HttpRequest.httpPostString(Constants.Api.FILE_DEL, jsonObject, httpCallback);

    }
}
