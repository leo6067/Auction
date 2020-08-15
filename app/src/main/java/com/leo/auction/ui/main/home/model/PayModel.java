package com.leo.auction.ui.main.home.model;

import com.alibaba.fastjson.JSONObject;


import com.google.gson.annotations.SerializedName;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.order.model.OrderExtendContentJson;

import java.util.ArrayList;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.home.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/10
 * 描    述： 余额，微信支付接口
 * 修    改：
 * ===============================================
 */
public class PayModel {


    /**
     * data : {"wx":{"appid":"wx083c4267d81c8961","mchId":"1375906802","nonceStr":"e38TMsOUQGJZitbl","package":"Sign=WXPay","paySign":"5A78C63830E47725BBB6070E0617F9CF","prepayId":"wx1510042327700258f09d0799563da50000","resultCode":"SUCCESS","returnCode":"SUCCESS","returnMsg":"OK","sign":"2823DC40F036796275B78822F9E65275","timeStamp":"1597457063","tradeNo":"e0910f5e683adf586825a17f58f0250d","tradeType":"APP"}}
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1597457063343}
     */

    private DataBean data;
    private ResultBean result;

    /*
*  payType 1-微信支付  2-余额支付
*  scene 订单-order  出价缴纳保证金-bid
*
* 多业务单号  业务场景为 订单-order 时使用
* */
// extendContent 订单支付的情况下 需传递该字段,用于接收收货地址。前端需将收货地址信息序列化成json格式的字符串。
    public static void httpPay(int payType, String scene, String money, String tradeNo, ArrayList<String> tradeNoList, String payPwd,
                               String exempt, OrderExtendContentJson extendContent, HttpRequest.HttpCallback httpCallback) {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("payType", payType);
        jsonObject.put("scene", scene);
        jsonObject.put("money", money);
        if (tradeNo.length() > 0) {
            jsonObject.put("tradeNo", tradeNo);
        } else {
            jsonObject.put("tradeNoList", tradeNoList);
        }
        jsonObject.put("payPwd", payPwd);
        jsonObject.put("exempt", exempt);


//        Gson gson = new Gson();
//        String jsonStr = gson.toJson(extendContent);

//        String jsonString = JSON.toJSONString(extendContent);


        jsonObject.put("extendContent",extendContent );

//        Globals.log("xxxxxxxxxx" + jsonStr);

//        Globals.log("xxxxxxxxxx 02 " + jsonString);

        HttpRequest.httpPostString(Constants.Api.PAY_ORDER_URL, jsonObject, httpCallback);
    }



    //废弃待修改
    public static void httpPay(int payType, String scene, int money, String tradeNo, ArrayList<String> tradeNoList, String payPwd,
                               String exempt, String extendContent, HttpRequest.HttpCallback httpCallback) {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("payType", payType);
        jsonObject.put("scene", scene);
        jsonObject.put("money", money);
        if (tradeNo.length() > 0) {
            jsonObject.put("tradeNo", tradeNo);
        } else {
            jsonObject.put("tradeNoList", tradeNoList);
        }
        jsonObject.put("payPwd", payPwd);
        jsonObject.put("exempt", exempt);



        jsonObject.put("extendContent", extendContent);


        HttpRequest.httpPostString(Constants.Api.PAY_ORDER_URL, jsonObject, httpCallback);


    }

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
         * wx : {"appid":"wx083c4267d81c8961","mchId":"1375906802","nonceStr":"e38TMsOUQGJZitbl","package":"Sign=WXPay","paySign":"5A78C63830E47725BBB6070E0617F9CF","prepayId":"wx1510042327700258f09d0799563da50000","resultCode":"SUCCESS","returnCode":"SUCCESS","returnMsg":"OK","sign":"2823DC40F036796275B78822F9E65275","timeStamp":"1597457063","tradeNo":"e0910f5e683adf586825a17f58f0250d","tradeType":"APP"}
         */

        private WxBean wx;

        public WxBean getWx() {
            return wx;
        }

        public void setWx(WxBean wx) {
            this.wx = wx;
        }

        public static class WxBean {
            /**
             * appid : wx083c4267d81c8961
             * mchId : 1375906802
             * nonceStr : e38TMsOUQGJZitbl
             * package : Sign=WXPay
             * paySign : 5A78C63830E47725BBB6070E0617F9CF
             * prepayId : wx1510042327700258f09d0799563da50000
             * resultCode : SUCCESS
             * returnCode : SUCCESS
             * returnMsg : OK
             * sign : 2823DC40F036796275B78822F9E65275
             * timeStamp : 1597457063
             * tradeNo : e0910f5e683adf586825a17f58f0250d
             * tradeType : APP
             */

            private String appid;
            private String mchId;
            private String nonceStr;
            @SerializedName("package")
            private String packageX;
            private String paySign;
            private String prepayId;
            private String resultCode;
            private String returnCode;
            private String returnMsg;
            private String sign;
            private String timeStamp;
            private String tradeNo;
            private String tradeType;

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getMchId() {
                return mchId;
            }

            public void setMchId(String mchId) {
                this.mchId = mchId;
            }

            public String getNonceStr() {
                return nonceStr;
            }

            public void setNonceStr(String nonceStr) {
                this.nonceStr = nonceStr;
            }

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getPaySign() {
                return paySign;
            }

            public void setPaySign(String paySign) {
                this.paySign = paySign;
            }

            public String getPrepayId() {
                return prepayId;
            }

            public void setPrepayId(String prepayId) {
                this.prepayId = prepayId;
            }

            public String getResultCode() {
                return resultCode;
            }

            public void setResultCode(String resultCode) {
                this.resultCode = resultCode;
            }

            public String getReturnCode() {
                return returnCode;
            }

            public void setReturnCode(String returnCode) {
                this.returnCode = returnCode;
            }

            public String getReturnMsg() {
                return returnMsg;
            }

            public void setReturnMsg(String returnMsg) {
                this.returnMsg = returnMsg;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getTimeStamp() {
                return timeStamp;
            }

            public void setTimeStamp(String timeStamp) {
                this.timeStamp = timeStamp;
            }

            public String getTradeNo() {
                return tradeNo;
            }

            public void setTradeNo(String tradeNo) {
                this.tradeNo = tradeNo;
            }

            public String getTradeType() {
                return tradeType;
            }

            public void setTradeType(String tradeType) {
                this.tradeType = tradeType;
            }

            @Override
            public String toString() {
                return "WxBean{" +
                        "appid='" + appid + '\'' +
                        ", mchId='" + mchId + '\'' +
                        ", nonceStr='" + nonceStr + '\'' +
                        ", packageX='" + packageX + '\'' +
                        ", paySign='" + paySign + '\'' +
                        ", prepayId='" + prepayId + '\'' +
                        ", resultCode='" + resultCode + '\'' +
                        ", returnCode='" + returnCode + '\'' +
                        ", returnMsg='" + returnMsg + '\'' +
                        ", sign='" + sign + '\'' +
                        ", timeStamp='" + timeStamp + '\'' +
                        ", tradeNo='" + tradeNo + '\'' +
                        ", tradeType='" + tradeType + '\'' +
                        '}';
            }
        }
    }

    public static class ResultBean {
        /**
         * code : 0
         * message : 请求成功
         * success : true
         * timestamp : 1597457063343
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

        @Override
        public String toString() {
            return "ResultBean{" +
                    "code='" + code + '\'' +
                    ", message='" + message + '\'' +
                    ", success=" + success +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }
}
