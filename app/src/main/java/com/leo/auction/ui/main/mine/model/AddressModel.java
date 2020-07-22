package com.leo.auction.ui.main.mine.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.SPUtils;
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
 * 创建日期：2019/10/28 0028
 * 描    述：
 * ================================================
 */
public class AddressModel {

    /**
     * data : [{"addr1":350000,"addr1Name":"福建省","addr2":350300,"addr2Name":"莆田市","addr3":350322,"addr3Name":"仙游县","address":"榜头镇天易世博","code":"000000","id":271,"linkman":"谢伟杰","phone":"17750656067","status":"00B","userAccountId":1998}]
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1593508262289}
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
         * timestamp : 1593508262289
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

    public static class DataBean implements Parcelable {
        /**
         * addr1 : 350000
         * addr1Name : 福建省
         * addr2 : 350300
         * addr2Name : 莆田市
         * addr3 : 350322
         * addr3Name : 仙游县
         * address : 榜头镇天易世博
         * code : 000000
         * id : 271
         * linkman : 谢伟杰
         * phone : 17750656067
         * status : 00B
         * userAccountId : 1998
         */

        private String addr1;
        private String addr1Name;
        private String addr2;
        private String addr2Name;
        private String addr3;
        private String addr3Name;
        private String address;
        private String code;
        private String id;
        private String linkman;
        private String phone;
        private String status;
        private String userAccountId;

        public DataBean() {
        }

        public DataBean(String addr1, String addr1Name, String addr2, String addr2Name, String addr3,
                        String addr3Name, String address, String code, String id, String linkman,
                        String phone, String status, String userAccountId) {
            this.addr1 = addr1;
            this.addr1Name = addr1Name;
            this.addr2 = addr2;
            this.addr2Name = addr2Name;
            this.addr3 = addr3;
            this.addr3Name = addr3Name;
            this.address = address;
            this.code = code;
            this.id = id;
            this.linkman = linkman;
            this.phone = phone;
            this.status = status;
            this.userAccountId = userAccountId;
        }

        protected DataBean(Parcel in) {
            addr1 = in.readString();
            addr1Name = in.readString();
            addr2 = in.readString();
            addr2Name = in.readString();
            addr3 = in.readString();
            addr3Name = in.readString();
            address = in.readString();
            code = in.readString();
            id = in.readString();
            linkman = in.readString();
            phone = in.readString();
            status = in.readString();
            userAccountId = in.readString();
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

        public String getAddr1() {
            return addr1;
        }

        public void setAddr1(String addr1) {
            this.addr1 = addr1;
        }

        public String getAddr1Name() {
            return addr1Name;
        }

        public void setAddr1Name(String addr1Name) {
            this.addr1Name = addr1Name;
        }

        public String getAddr2() {
            return addr2;
        }

        public void setAddr2(String addr2) {
            this.addr2 = addr2;
        }

        public String getAddr2Name() {
            return addr2Name;
        }

        public void setAddr2Name(String addr2Name) {
            this.addr2Name = addr2Name;
        }

        public String getAddr3() {
            return addr3;
        }

        public void setAddr3(String addr3) {
            this.addr3 = addr3;
        }

        public String getAddr3Name() {
            return addr3Name;
        }

        public void setAddr3Name(String addr3Name) {
            this.addr3Name = addr3Name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLinkman() {
            return linkman;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUserAccountId() {
            return userAccountId;
        }

        public void setUserAccountId(String userAccountId) {
            this.userAccountId = userAccountId;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(addr1);
            dest.writeString(addr1Name);
            dest.writeString(addr2);
            dest.writeString(addr2Name);
            dest.writeString(addr3);
            dest.writeString(addr3Name);
            dest.writeString(address);
            dest.writeString(code);
            dest.writeString(id);
            dest.writeString(linkman);
            dest.writeString(phone);
            dest.writeString(status);
            dest.writeString(userAccountId);
        }

    }


    public static void httpAddressList(int mPageNum, String status, String type, HttpRequest.HttpCallback httpCallback) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNum", String.valueOf(mPageNum));
        hashMap.put("pageSize", Constants.Var.LIST_NUMBER);
        if (status.length() > 0) {
            hashMap.put("status", status);//00A-普通地址  00B-默认地址
        }
        hashMap.put("type", type);//0-收货地址  1-退货地址
        HttpRequest.httpGetString(Constants.Api.ADDRESS_URL, hashMap, httpCallback);
    }
}
