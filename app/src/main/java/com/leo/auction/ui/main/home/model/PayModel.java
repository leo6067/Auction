package com.leo.auction.ui.main.home.model;

import com.alibaba.fastjson.JSONObject;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;

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
     * data : {"wx":{"nonceStr":"随机值","sign":"签名","prepayId":"会话ID","paySign":"支付签名","timeStamp":"时间戳","packages":"数据包","mchId":"商户号","tradeNo":"业务单号"}}
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
         * wx : {"nonceStr":"随机值","sign":"签名","prepayId":"会话ID","paySign":"支付签名","timeStamp":"时间戳","packages":"数据包","mchId":"商户号","tradeNo":"业务单号"}
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
             * nonceStr : 随机值
             * sign : 签名
             * prepayId : 会话ID
             * paySign : 支付签名
             * timeStamp : 时间戳
             * packages : 数据包
             * mchId : 商户号
             * tradeNo : 业务单号
             */

            private String nonceStr;
            private String sign;
            private String prepayId;
            private String paySign;
            private String timeStamp;
            private String packages;
            private String mchId;
            private String tradeNo;



            public String getNonceStr() {
                return nonceStr;
            }

            public void setNonceStr(String nonceStr) {
                this.nonceStr = nonceStr;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getPrepayId() {
                return prepayId;
            }

            public void setPrepayId(String prepayId) {
                this.prepayId = prepayId;
            }

            public String getPaySign() {
                return paySign;
            }

            public void setPaySign(String paySign) {
                this.paySign = paySign;
            }

            public String getTimeStamp() {
                return timeStamp;
            }

            public void setTimeStamp(String timeStamp) {
                this.timeStamp = timeStamp;
            }

            public String getPackages() {
                return packages;
            }

            public void setPackages(String packages) {
                this.packages = packages;
            }

            public String getMchId() {
                return mchId;
            }

            public void setMchId(String mchId) {
                this.mchId = mchId;
            }

            public String getTradeNo() {
                return tradeNo;
            }

            public void setTradeNo(String tradeNo) {
                this.tradeNo = tradeNo;
            }
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


/*
*  payType 1-微信支付  2-余额支付
*  scene 订单-order  出价缴纳保证金-bid
*
* 多业务单号  业务场景为 订单-order 时使用
* */
// extendContent 订单支付的情况下 需传递该字段,用于接收收货地址。前端需将收货地址信息序列化成json格式的字符串。
    public static void httpPay(int payType, String scene, String money, String tradeNo, ArrayList<String> tradeNoList, String payPwd,
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
}
