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
 * 创建日期：2019/10/28 0028
 * 描    述：
 * ================================================
 */
public class CancleOrderModel implements Parcelable {
    private boolean isChoose;
    private String text;

    public CancleOrderModel(boolean isChoose, String text) {
        this.isChoose = isChoose;
        this.text = text;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isChoose ? (byte) 1 : (byte) 0);
        dest.writeString(this.text);
    }

    protected CancleOrderModel(Parcel in) {
        this.isChoose = in.readByte() != 0;
        this.text = in.readString();
    }

    public static final Creator<CancleOrderModel> CREATOR = new Creator<CancleOrderModel>() {
        @Override
        public CancleOrderModel createFromParcel(Parcel source) {
            return new CancleOrderModel(source);
        }

        @Override
        public CancleOrderModel[] newArray(int size) {
            return new CancleOrderModel[size];
        }
    };
}
