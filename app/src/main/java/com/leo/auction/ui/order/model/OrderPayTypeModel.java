package com.leo.auction.ui.order.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.ui.order.model
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/11/15 0015
 * 描    述：订单支付类型
 * ================================================
 */
public class OrderPayTypeModel implements Parcelable {
    private int payIcon;
    private String payName;
    private String payDescribe;
    private boolean isChoose;
    private boolean chooseEnable;//是否可以选中

    public OrderPayTypeModel(int payIcon, String payName, String payDescribe, boolean isChoose, boolean chooseEnable) {
        this.payIcon = payIcon;
        this.payName = payName;
        this.payDescribe = payDescribe;
        this.isChoose = isChoose;
        this.chooseEnable = chooseEnable;
    }

    public int getPayIcon() {
        return payIcon;
    }

    public void setPayIcon(int payIcon) {
        this.payIcon = payIcon;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getPayDescribe() {
        return payDescribe;
    }

    public void setPayDescribe(String payDescribe) {
        this.payDescribe = payDescribe;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    public boolean isChooseEnable() {
        return chooseEnable;
    }

    public void setChooseEnable(boolean chooseEnable) {
        this.chooseEnable = chooseEnable;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.payIcon);
        dest.writeString(this.payName);
        dest.writeString(this.payDescribe);
        dest.writeByte(this.isChoose ? (byte) 1 : (byte) 0);
        dest.writeByte(this.chooseEnable ? (byte) 1 : (byte) 0);
    }

    protected OrderPayTypeModel(Parcel in) {
        this.payIcon = in.readInt();
        this.payName = in.readString();
        this.payDescribe = in.readString();
        this.isChoose = in.readByte() != 0;
        this.chooseEnable = in.readByte() != 0;
    }

    public static final Creator<OrderPayTypeModel> CREATOR = new Creator<OrderPayTypeModel>() {
        @Override
        public OrderPayTypeModel createFromParcel(Parcel source) {
            return new OrderPayTypeModel(source);
        }

        @Override
        public OrderPayTypeModel[] newArray(int size) {
            return new OrderPayTypeModel[size];
        }
    };
}
