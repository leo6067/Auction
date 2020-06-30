package com.leo.auction.ui.common.model;

import com.alibaba.fastjson.JSONObject;
import com.leo.auction.net.CustomerJsonCallBack;


import java.util.List;

public class LogisticsTrajectoryModel {
    /**
     * data : {"courier":"","courierPhone":"","deliverystatus":"3","expName":"圆通速递","expPhone":"95554","expSite":"www.yto.net.cn ","issign":"1","list":[{"status":"客户 签收人: 本人签收 已签收 感谢使用圆通速递，期待再次为您服务","time":"2019-11-20 03:28:43"},{"status":"新度镇白埕村下马桥妈妈驿站已发出自提短信,请上门自提,联系电话17720853054","time":"2019-11-19 16:00:58"},{"status":"快件已到达新度镇白埕村下马桥妈妈驿站,联系电话17720853054","time":"2019-11-19 16:00:57"},{"status":"【福建省莆田市新度分部公司】 派件人: 戴春华 派件中 派件员电话15080176352","time":"2019-11-19 10:12:35"},{"status":"【福建省莆田市】 已发出 下一站 【福建省莆田市新度分部公司】","time":"2019-11-19 03:12:29"},{"status":"【福建省莆田市公司】 已收入","time":"2019-11-18 22:47:19"},{"status":"【福州转运中心】 已发出 下一站 【福建省莆田市公司】","time":"2019-11-18 18:42:11"},{"status":"【福州转运中心】 已收入","time":"2019-11-18 18:39:42"},{"status":"【佛山转运中心】 已发出 下一站 【福州转运中心】","time":"2019-11-15 18:16:49"},{"status":"【佛山转运中心】 已发出 下一站 【厦门转运中心】","time":"2019-11-15 16:48:52"},{"status":"【佛山转运中心】 已收入","time":"2019-11-15 16:45:20"},{"status":"【广东省佛山市顺德区乐从公司】 已收件","time":"2019-11-15 07:05:46"},{"status":"【广东省佛山市顺德区乐从公司】 取件人: 罗金玉 已收件","time":"2019-11-13 13:40:32"}],"logo":"http://img3.fegine.com/express/yto.jpg","number":"YT4206364635823","takeTime":"6天13小时48分","type":"yto","updateTime":"2019-11-20 03:28:43"}
     * result : {"message":"请求成功","success":true}
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
         * courier :
         * courierPhone :
         * deliverystatus : 3
         * expName : 圆通速递
         * expPhone : 95554
         * expSite : www.yto.net.cn
         * issign : 1
         * list : [{"status":"客户 签收人: 本人签收 已签收 感谢使用圆通速递，期待再次为您服务","time":"2019-11-20 03:28:43"},{"status":"新度镇白埕村下马桥妈妈驿站已发出自提短信,请上门自提,联系电话17720853054","time":"2019-11-19 16:00:58"},{"status":"快件已到达新度镇白埕村下马桥妈妈驿站,联系电话17720853054","time":"2019-11-19 16:00:57"},{"status":"【福建省莆田市新度分部公司】 派件人: 戴春华 派件中 派件员电话15080176352","time":"2019-11-19 10:12:35"},{"status":"【福建省莆田市】 已发出 下一站 【福建省莆田市新度分部公司】","time":"2019-11-19 03:12:29"},{"status":"【福建省莆田市公司】 已收入","time":"2019-11-18 22:47:19"},{"status":"【福州转运中心】 已发出 下一站 【福建省莆田市公司】","time":"2019-11-18 18:42:11"},{"status":"【福州转运中心】 已收入","time":"2019-11-18 18:39:42"},{"status":"【佛山转运中心】 已发出 下一站 【福州转运中心】","time":"2019-11-15 18:16:49"},{"status":"【佛山转运中心】 已发出 下一站 【厦门转运中心】","time":"2019-11-15 16:48:52"},{"status":"【佛山转运中心】 已收入","time":"2019-11-15 16:45:20"},{"status":"【广东省佛山市顺德区乐从公司】 已收件","time":"2019-11-15 07:05:46"},{"status":"【广东省佛山市顺德区乐从公司】 取件人: 罗金玉 已收件","time":"2019-11-13 13:40:32"}]
         * logo : http://img3.fegine.com/express/yto.jpg
         * number : YT4206364635823
         * takeTime : 6天13小时48分
         * type : yto
         * updateTime : 2019-11-20 03:28:43
         */

        private String courier;
        private String courierPhone;
        private String deliverystatus;
        private String expName;
        private String expPhone;
        private String expSite;
        private String issign;
        private String logo;
        private String number;
        private String takeTime;
        private String type;
        private String updateTime;
        private List<ListBean> list;

        public String getCourier() {
            return courier;
        }

        public void setCourier(String courier) {
            this.courier = courier;
        }

        public String getCourierPhone() {
            return courierPhone;
        }

        public void setCourierPhone(String courierPhone) {
            this.courierPhone = courierPhone;
        }

        public String getDeliverystatus() {
            return deliverystatus;
        }

        public void setDeliverystatus(String deliverystatus) {
            this.deliverystatus = deliverystatus;
        }

        public String getExpName() {
            return expName;
        }

        public void setExpName(String expName) {
            this.expName = expName;
        }

        public String getExpPhone() {
            return expPhone;
        }

        public void setExpPhone(String expPhone) {
            this.expPhone = expPhone;
        }

        public String getExpSite() {
            return expSite;
        }

        public void setExpSite(String expSite) {
            this.expSite = expSite;
        }

        public String getIssign() {
            return issign;
        }

        public void setIssign(String issign) {
            this.issign = issign;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getTakeTime() {
            return takeTime;
        }

        public void setTakeTime(String takeTime) {
            this.takeTime = takeTime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * status : 客户 签收人: 本人签收 已签收 感谢使用圆通速递，期待再次为您服务
             * time : 2019-11-20 03:28:43
             */

            private String status;
            private String time;

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }

    public static class ResultBean {
        /**
         * message : 请求成功
         * success : true
         */

        private String message;
        private boolean success;

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
    }

    public static void sendLogisticsTrajectoryRequest(final String TAG, String number,
                                                      final CustomerJsonCallBack<LogisticsTrajectoryModel> callback) {
        JSONObject params = new JSONObject();
        params.put("number",number);
//        JsonRequestData.requesNetWork(TAG, Constants_Api.Api.HOMEPAGE_ORDER_DESC_EXPRESS_ALIQUERY_URL, params.toJSONString(), callback);
    }
}
