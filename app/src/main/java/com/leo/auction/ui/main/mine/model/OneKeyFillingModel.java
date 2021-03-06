package com.leo.auction.ui.main.mine.model;

import com.alibaba.fastjson.JSONObject;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;

import java.util.List;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.ui.mine.model
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/12/8
 * 描    述：
 * ================================================
 */
public class OneKeyFillingModel {


    /**
     * data : [{"cityId":350100,"cityName":"福州市","cityShortName":"福州","countyId":350181,"countyName":"福清市","countyShortName":"福清","detail":"三山镇横坑村","mobile":"","name":"","original":"福建省福清市三山镇横坑村","phone":"","provinceId":350000,"provinceName":"福建省","provinceShortName":"福建"}]
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1592193242338}
     */

    private ResultBean result;
    private List<DataBean> data;


    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class ResultBean {
        /**
         * code : 0
         * message : 请求成功
         * success : true
         * timestamp : 1592193242338
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

    public static class DataBean {
        /**
         * cityId : 350100
         * cityName : 福州市
         * cityShortName : 福州
         * countyId : 350181
         * countyName : 福清市
         * countyShortName : 福清
         * detail : 三山镇横坑村
         * mobile :
         * name :
         * original : 福建省福清市三山镇横坑村
         * phone :
         * provinceId : 350000
         * provinceName : 福建省
         * provinceShortName : 福建
         */

        private String cityId;
        private String cityName;
        private String cityShortName;
        private String countyId;
        private String countyName;
        private String countyShortName;
        private String detail;
        private String mobile;
        private String name;
        private String original;
        private String phone;
        private String provinceId;
        private String provinceName;
        private String provinceShortName;

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getCityShortName() {
            return cityShortName;
        }

        public void setCityShortName(String cityShortName) {
            this.cityShortName = cityShortName;
        }

        public String getCountyId() {
            return countyId;
        }

        public void setCountyId(String countyId) {
            this.countyId = countyId;
        }

        public String getCountyName() {
            return countyName;
        }

        public void setCountyName(String countyName) {
            this.countyName = countyName;
        }

        public String getCountyShortName() {
            return countyShortName;
        }

        public void setCountyShortName(String countyShortName) {
            this.countyShortName = countyShortName;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOriginal() {
            return original;
        }

        public void setOriginal(String original) {
            this.original = original;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(String provinceId) {
            this.provinceId = provinceId;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getProvinceShortName() {
            return provinceShortName;
        }

        public void setProvinceShortName(String provinceShortName) {
            this.provinceShortName = provinceShortName;
        }
    }


    public static void sendOneKeyFillingRequest( String address,
                                                HttpRequest.HttpCallback callback) {
        JSONObject params=new JSONObject();
        params.put("address",address);
        HttpRequest.httpPostString(Constants.Api.ADDRESS_SMART_URL, params, callback);
    }
}
