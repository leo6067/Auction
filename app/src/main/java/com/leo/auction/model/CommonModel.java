package com.leo.auction.model;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/15
 * 描    述： 多类型适配
 * 修    改：
 * ===============================================
 */
public class CommonModel {


    private String commonStr;
    private int commonInt;
    private boolean commonSelect;



    public CommonModel() {
    }

    public CommonModel(String commonStr, int commonInt) {
        this.commonStr = commonStr;
        this.commonInt = commonInt;
    }


    public CommonModel(String commonStr, int commonInt, boolean commonSelect) {
        this.commonStr = commonStr;
        this.commonInt = commonInt;
        this.commonSelect = commonSelect;
    }

    public boolean isCommonSelect() {
        return commonSelect;
    }

    public void setCommonSelect(boolean commonSelect) {
        this.commonSelect = commonSelect;
    }


    public String getCommonStr() {
        return commonStr;
    }

    public void setCommonStr(String commonStr) {
        this.commonStr = commonStr;
    }

    public int getCommonInt() {
        return commonInt;
    }

    public void setCommonInt(int commonInt) {
        this.commonInt = commonInt;
    }
}
