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
    private String title;
    private String price;
    private String shopName;
    private String shopHeadImg;

    //获取二维码需求的
    private String id;
    private String type;
    private String sharePage;
    private String shareGoodsId;
    private String shareShopUri;
    private String shareAgentId;
    private String channelType;//0-频道类型 1-超级购 2-超人气 3-精选 4-首页 5-分类 6-店铺首页 7-收藏关注',
    private String sourceId;
    private String page;

    public SharedModel(String picPath, String title, String price, String shopName, String shopHeadImg,
                       String id, String type, String sharePage, String shareGoodsId, String shareShopUri,
                       String shareAgentId, String channelType, String sourceId, String page) {
        this.picPath = picPath;
        this.title = title;
        this.price = price;
        this.shopName = shopName;
        this.shopHeadImg = shopHeadImg;
        this.id = id;
        this.type = type;
        this.sharePage = sharePage;
        this.shareGoodsId = shareGoodsId;
        this.shareShopUri = shareShopUri;
        this.shareAgentId = shareAgentId;
        this.channelType = channelType;
        this.sourceId = sourceId;
        this.page = page;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSharePage() {
        return sharePage;
    }

    public void setSharePage(String sharePage) {
        this.sharePage = sharePage;
    }

    public String getShareGoodsId() {
        return shareGoodsId;
    }

    public void setShareGoodsId(String shareGoodsId) {
        this.shareGoodsId = shareGoodsId;
    }

    public String getShareShopUri() {
        return shareShopUri;
    }

    public void setShareShopUri(String shareShopUri) {
        this.shareShopUri = shareShopUri;
    }

    public String getShareAgentId() {
        return shareAgentId;
    }

    public void setShareAgentId(String shareAgentId) {
        this.shareAgentId = shareAgentId;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.picPath);
        dest.writeString(this.title);
        dest.writeString(this.price);
        dest.writeString(this.shopName);
        dest.writeString(this.shopHeadImg);
        dest.writeString(this.type);
        dest.writeString(this.sharePage);
        dest.writeString(this.shareGoodsId);
        dest.writeString(this.shareShopUri);
        dest.writeString(this.shareAgentId);
        dest.writeString(this.channelType);
        dest.writeString(this.sourceId);
        dest.writeString(this.page);
    }

    protected SharedModel(Parcel in) {
        this.id = in.readString();
        this.picPath = in.readString();
        this.title = in.readString();
        this.price = in.readString();
        this.shopName = in.readString();
        this.shopHeadImg = in.readString();
        this.type = in.readString();
        this.sharePage = in.readString();
        this.shareGoodsId = in.readString();
        this.shareShopUri = in.readString();
        this.shareAgentId = in.readString();
        this.channelType = in.readString();
        this.sourceId = in.readString();
        this.page = in.readString();
    }

    public static final Creator<SharedModel> CREATOR = new Creator<SharedModel>() {
        @Override
        public SharedModel createFromParcel(Parcel source) {
            return new SharedModel(source);
        }

        @Override
        public SharedModel[] newArray(int size) {
            return new SharedModel[size];
        }
    };
}
