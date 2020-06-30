package com.leo.auction.ui.main.mine.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.utils.EmptyUtils;
import com.leo.auction.base.Constants;
import com.leo.auction.net.CustomerJsonCallBack;

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
     * address : [{"addr1":350000,"addr1Name":"福建省","addr2":350300,"addr2Name":"莆田市","addr3":350304,"addr3Name":"荔城区","address":"新度镇港利村","code":"351142","id":89,"linkman":"彭俊鸿","phone":"15060338985","status":"00B","userAccountId":1966}]
     * result : {"code":"0","message":"请求成功","success":true}
     */

    private ResultBean result;
    private List<AddressBean> address;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public List<AddressBean> getAddress() {
        return address;
    }

    public void setAddress(List<AddressBean> address) {
        this.address = address;
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

    public static class AddressBean implements Parcelable {
        /**
         * addr1 : 350000
         * addr1Name : 福建省
         * addr2 : 350300
         * addr2Name : 莆田市
         * addr3 : 350304
         * addr3Name : 荔城区
         * address : 新度镇港利村
         * code : 351142
         * id : 89
         * linkman : 彭俊鸿
         * phone : 15060338985
         * status : 00B
         * userAccountId : 1966
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
        private boolean isChoose;

        public AddressBean(String addr1, String addr1Name, String addr2, String addr2Name, String addr3,
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

        public boolean isChoose() {
            return isChoose;
        }

        public void setChoose(boolean choose) {
            isChoose = choose;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.addr1);
            dest.writeString(this.addr1Name);
            dest.writeString(this.addr2);
            dest.writeString(this.addr2Name);
            dest.writeString(this.addr3);
            dest.writeString(this.addr3Name);
            dest.writeString(this.address);
            dest.writeString(this.code);
            dest.writeString(this.id);
            dest.writeString(this.linkman);
            dest.writeString(this.phone);
            dest.writeString(this.status);
            dest.writeString(this.userAccountId);
        }

        public AddressBean() {
        }

        protected AddressBean(Parcel in) {
            this.addr1 = in.readString();
            this.addr1Name = in.readString();
            this.addr2 = in.readString();
            this.addr2Name = in.readString();
            this.addr3 = in.readString();
            this.addr3Name = in.readString();
            this.address = in.readString();
            this.code = in.readString();
            this.id = in.readString();
            this.linkman = in.readString();
            this.phone = in.readString();
            this.status = in.readString();
            this.userAccountId = in.readString();
        }

        public static final Creator<AddressBean> CREATOR = new Creator<AddressBean>() {
            @Override
            public AddressBean createFromParcel(Parcel source) {
                return new AddressBean(source);
            }

            @Override
            public AddressBean[] newArray(int size) {
                return new AddressBean[size];
            }
        };
    }

    //获取地址列表数据
    public static void sendAddressRequest(final String TAG,final String pageNum,String addressId,String status,String type,
                                          final CustomerJsonCallBack<AddressModel> callback) {
        JSONObject params=new JSONObject();
        JSONObject value=new JSONObject();
        value.put("pageNum",pageNum);
        value.put("pageSize","10");
        params.put("page",value);
        if (!EmptyUtils.isEmpty(addressId)){
            params.put("addressId",addressId);
        }
        if (!EmptyUtils.isEmpty(status)){
            params.put("status",status);
        }
        params.put("type",type);

//        JsonRequestData.requesNetWork(TAG, Constants.Api.HOMEPAGE_ADDRESS_LIST_URL, params.toJSONString(), callback);
    }
}
