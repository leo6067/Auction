package com.leo.auction.utils.shared_dailog;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.ui.home.model
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/12/12
 * 描    述：
 * ================================================
 */
public class SharedModel implements Parcelable {
    //页面展示需要的
    private String picPath;
    private String content;
    private String price;
    private String shopName;
    private String shopHeadImg;

    //获取二维码需求的

    private String type;
    private String shareUrl;
    private String shareGoodsCode;

    private String shareUserId;
    private String channelType;//0-频道类型 1-超级购 2-超人气 3-精选 4-首页 5-分类 6-店铺首页 7-收藏关注',

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopHeadImg() {
        return shopHeadImg;
    }

    public void setShopHeadImg(String shopHeadImg) {
        this.shopHeadImg = shopHeadImg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getShareGoodsCode() {
        return shareGoodsCode;
    }

    public void setShareGoodsCode(String shareGoodsCode) {
        this.shareGoodsCode = shareGoodsCode;
    }

    public String getShareUserId() {
        return shareUserId;
    }

    public void setShareUserId(String shareUserId) {
        this.shareUserId = shareUserId;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public SharedModel(String shopName, String content, String picPath, String price, String shopHeadImg,
                       String type, String shareUrl, String shareGoodsCode,
                       String shareUserId, String channelType ) {
        this.picPath = picPath;
        this.content = content;
        this.price = price;
        this.shopName = shopName;
        this.shopHeadImg = shopHeadImg;

        this.type = type;
        this.shareUrl = shareUrl;
        this.shareGoodsCode = shareGoodsCode;
        this.shareUserId = shareUserId;
        this.channelType = channelType;

    }


    protected SharedModel(Parcel in) {
        picPath = in.readString();
        content = in.readString();
        price = in.readString();
        shopName = in.readString();
        shopHeadImg = in.readString();
        type = in.readString();
        shareUrl = in.readString();
        shareGoodsCode = in.readString();
        shareUserId = in.readString();
        channelType = in.readString();
    }

    public static final Creator<SharedModel> CREATOR = new Creator<SharedModel>() {
        @Override
        public SharedModel createFromParcel(Parcel in) {
            return new SharedModel(in);
        }

        @Override
        public SharedModel[] newArray(int size) {
            return new SharedModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(picPath);
        dest.writeString(content);
        dest.writeString(price);
        dest.writeString(shopName);
        dest.writeString(shopHeadImg);
        dest.writeString(type);
        dest.writeString(shareUrl);
        dest.writeString(shareGoodsCode);
        dest.writeString(shareUserId);
        dest.writeString(channelType);
    }
}
