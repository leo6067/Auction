package com.leo.auction.ui.main.mine.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.aten.compiler.widget.wheel.entity.IWheelEntity;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;

import java.util.HashMap;
import java.util.List;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.ui.mine.model
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/11/10 0010
 * 描    述：
 * ================================================
 */
public class DistrictListModel {


    /**
     * data : [{"id":110000,"name":"北京市"},{"id":120000,"name":"天津市"},{"id":130000,"name":"河北省"},{"id":140000,"name":"山西省"},{"id":150000,"name":"内蒙古自治区"},{"id":210000,"name":"辽宁省"},{"id":220000,"name":"吉林省"},{"id":230000,"name":"黑龙江省"},{"id":310000,"name":"上海市"},{"id":320000,"name":"江苏省"},{"id":330000,"name":"浙江省"},{"id":340000,"name":"安徽省"},{"id":350000,"name":"福建省"},{"id":360000,"name":"江西省"},{"id":370000,"name":"山东省"},{"id":410000,"name":"河南省"},{"id":420000,"name":"湖北省"},{"id":430000,"name":"湖南省"},{"id":440000,"name":"广东省"},{"id":450000,"name":"广西壮族自治区"},{"id":460000,"name":"海南省"},{"id":500000,"name":"重庆市"},{"id":510000,"name":"四川省"},{"id":520000,"name":"贵州省"},{"id":530000,"name":"云南省"},{"id":540000,"name":"西藏自治区"},{"id":610000,"name":"陕西省"},{"id":620000,"name":"甘肃省"},{"id":630000,"name":"青海省"},{"id":640000,"name":"宁夏回族自治区"},{"id":650000,"name":"新疆维吾尔自治区"},{"id":710000,"name":"台湾省"},{"id":810000,"name":"香港特别行政区"},{"id":820000,"name":"澳门特别行政区"}]
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1596013474266}
     */

    private ResultBean result;
    private List<DataBean> data;

    public static void sendDistrictListRequest(String id, String level, HttpRequest.HttpCallback httpCallback) {
        HashMap<String,String> mHash = new HashMap<>();
        mHash.put("parentId",id);
        mHash.put("level",level);
        HttpRequest.httpGetString(Constants.Api.ADDRESS_DISTRICT_URL, mHash, httpCallback);
    }

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
         * timestamp : 1596013474266
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

    public static class DataBean implements Parcelable, IWheelEntity {
        /**
         * id : 110000
         * name : 北京市
         */

        private String id;
        private String name;

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            id = in.readString();
            name = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(name);
        }

        @Override
        public String getWheelText() {
            return getName();
        }
    }
}
