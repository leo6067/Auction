package com.leo.auction.ui.main.home.model;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.home.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/8/15
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class WebShopModel {


    /**
     * userAccountId : 69
     * shopUri : 用户编号
     * level : 等级
     * headImg : 头像
     * rate : 评分
     * warrant : 消保金
     * fansNum : 粉丝数
     * companyAuth : true
     */

    private int userAccountId;
    private String shopUri;
    private String level;
    private String headImg;
    private String rate;
    private String warrant;
    private String fansNum;
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    private boolean companyAuth;

    public int getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(int userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getShopUri() {
        return shopUri;
    }

    public void setShopUri(String shopUri) {
        this.shopUri = shopUri;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getWarrant() {
        return warrant;
    }

    public void setWarrant(String warrant) {
        this.warrant = warrant;
    }

    public String getFansNum() {
        return fansNum;
    }

    public void setFansNum(String fansNum) {
        this.fansNum = fansNum;
    }

    public boolean isCompanyAuth() {
        return companyAuth;
    }

    public void setCompanyAuth(boolean companyAuth) {
        this.companyAuth = companyAuth;
    }
}
