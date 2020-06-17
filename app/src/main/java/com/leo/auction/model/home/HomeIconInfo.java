package com.leo.auction.model.home;

/**
 * Created by Leo on 2018/7/4.
 */

public class HomeIconInfo {

    private String iconStr;
    private int iconImg;

    public HomeIconInfo(String iconStr, int iconImg) {
        this.iconStr = iconStr;
        this.iconImg = iconImg;
    }

    public int getIconImg() {
        return iconImg;
    }

    public void setIconImg(int iconImg) {
        this.iconImg = iconImg;
    }

    public String getIconStr() {
        return iconStr;
    }

    public void setIconStr(String iconStr) {
        this.iconStr = iconStr;
    }


}
