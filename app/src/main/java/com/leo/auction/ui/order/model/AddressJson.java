package com.leo.auction.ui.order.model;

import com.leo.auction.base.BaseModel;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.order.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/8/3
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class AddressJson     {


    /**
     * receiver : 收货人
     * receiverAreaName : 收货地址
     * receiverCode : 收货邮编
     * receiverMobile : 收货人手机号
     */

    private String receiver;
    private String receiverAreaName;
    private String receiverCode;
    private String receiverMobile;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverAreaName() {
        return receiverAreaName;
    }

    public void setReceiverAreaName(String receiverAreaName) {
        this.receiverAreaName = receiverAreaName;
    }

    public String getReceiverCode() {
        return receiverCode;
    }

    public void setReceiverCode(String receiverCode) {
        this.receiverCode = receiverCode;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    @Override
    public String toString() {
        return "AddressJson{" +
                "receiver='" + receiver + '\'' +
                ", receiverAreaName='" + receiverAreaName + '\'' +
                ", receiverCode='" + receiverCode + '\'' +
                ", receiverMobile='" + receiverMobile + '\'' +
                '}';
    }
}
