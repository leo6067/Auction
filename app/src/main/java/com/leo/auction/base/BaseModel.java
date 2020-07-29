package com.leo.auction.base;

import com.alibaba.fastjson.JSONObject;
import com.leo.auction.net.CustomerJsonCallBack;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.mine.model.ReleaseAuctionAttrModel;
import com.leo.auction.ui.main.mine.model.ReleaseEditModel;
import com.leo.auction.ui.order.model.CommitEvaluationModel;
import com.leo.auction.utils.Globals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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


    //用户记录
    public static void sendActionLogRequest(String channelType, String actionType, String productInstanceId,
                                            String status,
                                            HttpRequest.HttpCallback httpCallback) {

        HashMap<String, String> params = new HashMap<>();
        params.put("channelType", channelType);//'频道类型  1-超级购  2-超人气 3-精选 4-首页 5-分类 6-店铺首页 7-收藏关注 8-抽奖活动'
        params.put("actionType", actionType);//'动作类型   1-点击  2-收藏 3-付款  4-分享 5-分享新用户 6-分享到朋友圈 7-加入到购物车'
        params.put("productInstanceId", productInstanceId);//'商品标识'

        params.put("status", status);//'1-非取消动作取1   -1-类似取消这种动作用-1'
        HttpRequest.httpPostForm(Constants.Api.ACTION_USER, params, httpCallback);
    }


    //密码
    //设置支付密码
    public static void sendUserAddpaypwdRequest(final String payPwd,
                                                HttpRequest.HttpCallback httpCallback) {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("payPwd", payPwd);
        HttpRequest.httpPostForm(Constants.Api.PAY_PWD_URL, hashMap, httpCallback);
    }

    //重置支付密码
    public static void sendUserResetpaypwdRequest(String oldPayPwd, String payPwd,
                                                  HttpRequest.HttpCallback callback) {

        HashMap<String, String> jsonObject = new HashMap<>();
        jsonObject.put("payPwd", oldPayPwd);
        jsonObject.put("newPayPwd", payPwd);
        HttpRequest.httpPostForm(Constants.Api.RESET_PWD_URL, jsonObject, callback);
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
        HashMap<String, String> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("nickname", nickName);
        HttpRequest.httpPostForm(Constants.Api.USER_URL, stringObjectHashMap, httpCallback);

    }

    public static void httpUserHeadImg(String headImg, HttpRequest.HttpCallback httpCallback) {
        HashMap<String, String> jsonObject = new HashMap<>();
        jsonObject.put("headImg", headImg);
        HttpRequest.httpPostForm(Constants.Api.USER_URL, jsonObject, httpCallback);
    }


    public static void httpUserPhone(String phone, String code, HttpRequest.HttpCallback httpCallback) {
        HashMap<String, String> jsonObject = new HashMap<>();
        jsonObject.put("phone", phone);
        jsonObject.put("code", code);
        HttpRequest.httpPostForm(Constants.Api.USER_URL, jsonObject, httpCallback);
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
            if (video.size() == 1) {
                params.put("video", video.get(0));
            } else {
                params.put("video", video);//'视频路径'
            }
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

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("productInstanceCode", productInstanceCode);
        HttpRequest.httpPostForm(Constants.Api.GOODS_LOWER_URL, hashMap, httpCallback);
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


    //收藏商品
    public static void httpCollectGood(int productInstanceId, int type, HttpRequest.HttpCallback httpCallback) {

        HashMap<String, String> jsonObject = new HashMap<>();

        jsonObject.put("productInstanceId", productInstanceId + "");
        jsonObject.put("type", type + "");//类型  0-取消 1-收藏

        HttpRequest.httpPostForm(Constants.Api.SORT_COLLECT_URL, jsonObject, httpCallback);

    }


    //提醒发货
    public static void httpSendGoodN(String orderCode, HttpRequest.HttpCallback httpCallback) {
        HashMap<String, String> jsonObject = new HashMap<>();
        jsonObject.put("orderCode", orderCode);
        HttpRequest.httpPostString(Constants.Api.ORDER_REMIND_SEND_URL, jsonObject, httpCallback);

    }


    //当面交易
    public static void httpFaceTrade(String orderCode, int type, HttpRequest.HttpCallback httpCallback) {
        HashMap<String, String> jsonObject = new HashMap<>();
        jsonObject.put("orderCode", orderCode);
        jsonObject.put("type", type + ""); //操作类型  1-申请当面交易  2-同意当面交易  4-拒绝当面交易
        HttpRequest.httpPostForm(Constants.Api.ORDER_FACE_TRADE_URL, jsonObject, httpCallback);

    }


    //确认收货
    public static void httpTakeGood(String orderCode, HttpRequest.HttpCallback httpCallback) {
        HashMap<String, String> jsonObject = new HashMap<>();
        jsonObject.put("orderCode", orderCode);
        HttpRequest.httpPostForm(Constants.Api.ORDER_CONFIRM_TAKE_URL, jsonObject, httpCallback);

    }

    //延迟发货
    public static void httpDelaySendGood(String orderCode, int type, HttpRequest.HttpCallback httpCallback) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderCode", orderCode);
        jsonObject.put("type", type);  //操作类型  1-申请延迟发货  2-同意延迟发货  4-拒绝延迟发货
        HttpRequest.httpPostString(Constants.Api.ORDER_DELAY_SEND_URL, jsonObject, httpCallback);

    }

    //确认发货
    public static void httpSendGood(String orderCode, String shippingCode, String shippingNum, HttpRequest.HttpCallback httpCallback) {
        HashMap<String, String> jsonObject = new HashMap<>();
        jsonObject.put("orderCode", orderCode);
        jsonObject.put("shippingCode", orderCode);
        jsonObject.put("shippingNum", shippingNum);
        HttpRequest.httpPostForm(Constants.Api.ORDER_CONFIRM_SEND_URL, jsonObject, httpCallback);

    }

    //延迟收货
    public static void httpDelayTake(String orderCode, HttpRequest.HttpCallback httpCallback) {
        HashMap<String, String> jsonObject = new HashMap<>();
        jsonObject.put("orderCode", orderCode);
        HttpRequest.httpPostForm(Constants.Api.ORDER_DELAY_CONFIRM_TAKE_URL, jsonObject, httpCallback);
    }

    //申请延迟付款
    public static void httpDelayPay(String orderCode, HttpRequest.HttpCallback httpCallback) {
        HashMap<String, String> jsonObject = new HashMap<>();
        jsonObject.put("orderCode", orderCode);
        HttpRequest.httpPostForm(Constants.Api.ORDER_DELAY_PAY_URL, jsonObject, httpCallback);

    }

    //修改单号
    public static void httpExpress(String orderCode, HttpRequest.HttpCallback httpCallback) {
        HashMap<String, String> jsonObject = new HashMap<>();
        jsonObject.put("orderCode", orderCode);
        jsonObject.put("shippingNum", orderCode);//快递单号
        jsonObject.put("shippingCode", orderCode);//快递编码
        HttpRequest.httpPostForm(Constants.Api.ORDER_EXPRESS_URL, jsonObject, httpCallback);

    }


    //提交评价
    public static void httpOrderEvaluate(String orderCode, CommitEvaluationModel.DataBean dataList,
                                         HttpRequest.HttpCallback httpCallback) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderCode", orderCode);
        jsonObject.put("data", dataList);
        HttpRequest.httpPostString(Constants.Api.ORDER_ESTIMATE_URL, jsonObject, httpCallback);
    }


    //关注店铺
    public static void httpPostFocus(String productAccountId, String type,
                                     HttpRequest.HttpCallback httpCallback) {

        HashMap<String, String> jsonObject = new HashMap<>();
        jsonObject.put("productAccountId", productAccountId);
        jsonObject.put("type", type);//类型 0-取消  1-关注   2-置顶  4-取消置顶
        HttpRequest.httpPostForm(Constants.Api.FOCUS_URL, jsonObject, httpCallback);
    }


}
