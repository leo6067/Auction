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
 * 创建日期：2019/12/7
 * 描    述：
 * ================================================
 */
public class EvaluationLableModel implements Parcelable {
    private int lableId;
    private String text;
    private boolean select;

    public EvaluationLableModel(int lableId, String text, boolean select) {
        this.lableId = lableId;
        this.text = text;
        this.select=select;
    }

    public int getLableId() {
        return lableId;
    }

    public void setLableId(int lableId) {
        this.lableId = lableId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.lableId);
        dest.writeString(this.text);
        dest.writeByte(this.select ? (byte) 1 : (byte) 0);
    }

    protected EvaluationLableModel(Parcel in) {
        this.lableId = in.readInt();
        this.text = in.readString();
        this.select = in.readByte() != 0;
    }

    public static final Creator<EvaluationLableModel> CREATOR = new Creator<EvaluationLableModel>() {
        @Override
        public EvaluationLableModel createFromParcel(Parcel source) {
            return new EvaluationLableModel(source);
        }

        @Override
        public EvaluationLableModel[] newArray(int size) {
            return new EvaluationLableModel[size];
        }
    };
}
