package com.leo.auction.model;

/**
 * Created by Leo on 2018/6/4.
 */

public class GridViewInfo {

    private String tabImg;
    private String tabStr;


    public GridViewInfo(String tabImg, String tabStr) {
        this.tabImg = tabImg;
        this.tabStr = tabStr;
    }

    public String getTabImg() {
        return tabImg;
    }

    public void setTabImg(String tabImg) {
        this.tabImg = tabImg;
    }

    public String getTabStr() {
        return tabStr;
    }

    public void setTabStr(String tabStr) {
        this.tabStr = tabStr;
    }
}
