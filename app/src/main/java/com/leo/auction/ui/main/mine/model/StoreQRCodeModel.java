package com.leo.auction.ui.main.mine.model;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.ui.seller.model
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/12/5
 * 描    述：
 * ================================================
 */
public class StoreQRCodeModel {
    private String fourImgs;
    private String fourImgsBg;
    private String qrCodeType;

    public StoreQRCodeModel(String fourImgs, String fourImgsBg, String qrCodeType) {
        this.fourImgs = fourImgs;
        this.fourImgsBg = fourImgsBg;
        this.qrCodeType = qrCodeType;
    }

    public String getFourImgs() {
        return fourImgs;
    }

    public void setFourImgs(String fourImgs) {
        this.fourImgs = fourImgs;
    }

    public String getFourImgsBg() {
        return fourImgsBg;
    }

    public void setFourImgsBg(String fourImgsBg) {
        this.fourImgsBg = fourImgsBg;
    }

    public String getQrCodeType() {
        return qrCodeType;
    }

    public void setQrCodeType(String qrCodeType) {
        this.qrCodeType = qrCodeType;
    }
}
