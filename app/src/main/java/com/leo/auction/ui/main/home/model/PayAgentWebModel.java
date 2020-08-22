package com.leo.auction.ui.main.home.model;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.home.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/8/22
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class PayAgentWebModel {


    /**
     * data : {"wx":{"appid":"wx1d28c57710e5403d","codeUrl":"weixin://wxpay/bizpayurl?pr=d6diAoU","mchId":"1375906802","nonceStr":"XBmkRouXKeaLgf66","prepayId":"wx22103110810731ee226e37c0447d940000","resultCode":"SUCCESS","returnCode":"SUCCESS","returnMsg":"OK","sign":"43604CBD15D4321D4A4071028A84D050","tradeNo":"4569011535435993PuxjS","tradeType":"NATIVE"}}
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1598063470925}
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
         * wx : {"appid":"wx1d28c57710e5403d","codeUrl":"weixin://wxpay/bizpayurl?pr=d6diAoU","mchId":"1375906802","nonceStr":"XBmkRouXKeaLgf66","prepayId":"wx22103110810731ee226e37c0447d940000","resultCode":"SUCCESS","returnCode":"SUCCESS","returnMsg":"OK","sign":"43604CBD15D4321D4A4071028A84D050","tradeNo":"4569011535435993PuxjS","tradeType":"NATIVE"}
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
             * appid : wx1d28c57710e5403d
             * codeUrl : weixin://wxpay/bizpayurl?pr=d6diAoU
             * mchId : 1375906802
             * nonceStr : XBmkRouXKeaLgf66
             * prepayId : wx22103110810731ee226e37c0447d940000
             * resultCode : SUCCESS
             * returnCode : SUCCESS
             * returnMsg : OK
             * sign : 43604CBD15D4321D4A4071028A84D050
             * tradeNo : 4569011535435993PuxjS
             * tradeType : NATIVE
             */

            private String appid;
            private String codeUrl;
            private String mchId;
            private String nonceStr;
            private String prepayId;
            private String resultCode;
            private String returnCode;
            private String returnMsg;
            private String sign;
            private String tradeNo;
            private String tradeType;

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getCodeUrl() {
                return codeUrl;
            }

            public void setCodeUrl(String codeUrl) {
                this.codeUrl = codeUrl;
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
        }
    }

    public static class ResultBean {
        /**
         * code : 0
         * message : 请求成功
         * success : true
         * timestamp : 1598063470925
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
}
