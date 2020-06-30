package com.leo.auction.ui.main.mine.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.JSON;
import com.aten.compiler.widget.wheel.entity.IWheelEntity;
import com.leo.auction.net.CustomerJsonCallBack;


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
     * districts : [{"addr":110000,"level":1,"name":"北京市"},{"addr":120000,"level":1,"name":"天津市"},{"addr":130000,"level":1,"name":"河北省"},{"addr":140000,"level":1,"name":"山西省"},{"addr":150000,"level":1,"name":"内蒙古自治区"},{"addr":210000,"level":1,"name":"辽宁省"},{"addr":220000,"level":1,"name":"吉林省"},{"addr":230000,"level":1,"name":"黑龙江省"},{"addr":310000,"level":1,"name":"上海市"},{"addr":320000,"level":1,"name":"江苏省"},{"addr":330000,"level":1,"name":"浙江省"},{"addr":340000,"level":1,"name":"安徽省"},{"addr":350000,"level":1,"name":"福建省"},{"addr":360000,"level":1,"name":"江西省"},{"addr":370000,"level":1,"name":"山东省"},{"addr":410000,"level":1,"name":"河南省"},{"addr":420000,"level":1,"name":"湖北省"},{"addr":430000,"level":1,"name":"湖南省"},{"addr":440000,"level":1,"name":"广东省"},{"addr":450000,"level":1,"name":"广西壮族自治区"},{"addr":460000,"level":1,"name":"海南省"},{"addr":500000,"level":1,"name":"重庆市"},{"addr":510000,"level":1,"name":"四川省"},{"addr":520000,"level":1,"name":"贵州省"},{"addr":530000,"level":1,"name":"云南省"},{"addr":540000,"level":1,"name":"西藏自治区"},{"addr":610000,"level":1,"name":"陕西省"},{"addr":620000,"level":1,"name":"甘肃省"},{"addr":630000,"level":1,"name":"青海省"},{"addr":640000,"level":1,"name":"宁夏回族自治区"},{"addr":650000,"level":1,"name":"新疆维吾尔自治区"},{"addr":710000,"level":1,"name":"台湾省"},{"addr":810000,"level":1,"name":"香港特别行政区"},{"addr":820000,"level":1,"name":"澳门特别行政区"}]
     * result : {"code":"0","message":"请求成功","success":true}
     */

    private ResultBean result;
    private List<DistrictsBean> data;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public List<DistrictsBean> getDistricts() {
        return data;
    }

    public void setDistricts(List<DistrictsBean> districts) {
        this.data = districts;
    }

    public static class ResultBean {
        /**
         * code : 0
         * message : 请求成功
         * success : true
         */

        private String code;
        private String message;
        private boolean success;

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
    }

    public static class DistrictsBean implements Parcelable , IWheelEntity {
        /**
         * addr : 110000
         * level : 1
         * name : 北京市
         */

        private String addr;
        private String level;
        private String name;

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
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
            dest.writeString(this.addr);
            dest.writeString(this.level);
            dest.writeString(this.name);
        }

        public DistrictsBean() {
        }

        protected DistrictsBean(Parcel in) {
            this.addr = in.readString();
            this.level = in.readString();
            this.name = in.readString();
        }

        public static final Creator<DistrictsBean> CREATOR = new Creator<DistrictsBean>() {
            @Override
            public DistrictsBean createFromParcel(Parcel source) {
                return new DistrictsBean(source);
            }

            @Override
            public DistrictsBean[] newArray(int size) {
                return new DistrictsBean[size];
            }
        };

        @Override
        public String getWheelText() {
            return getName();
        }
    }

    //静默登录
    public static void sendDistrictListRequest(String TAG, String id, String level, CustomerJsonCallBack<DistrictListModel> callback) {
        HashMap<String,String> value=new HashMap<>();
        value.put("id",id);
        value.put("level",level);
//        JsonRequestData.requesNetWork(TAG, Constants_Api.Api.HOMEPAGE_DISTRICT_CHILDREN_LIST_URL, JSON.toJSONString(value), callback);
    }
}
