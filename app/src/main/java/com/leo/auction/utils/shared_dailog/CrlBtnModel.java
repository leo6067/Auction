package com.leo.auction.utils.shared_dailog;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.utils.shared_dailog
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/11/3 0003
 * 描    述：
 * ================================================
 */
public class CrlBtnModel {
    private int id;
    private int imgRes;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CrlBtnModel(int id, int imgRes, String title) {


        this.id = id;
        this.imgRes = imgRes;
        this.title = title;
    }
}
