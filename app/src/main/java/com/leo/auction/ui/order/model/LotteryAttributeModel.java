package com.leo.auction.ui.order.model;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.ui.seller.model
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/12/4
 * 描    述：
 * ================================================
 */
public class LotteryAttributeModel {
    private String data;//用于展示的数据
    private String relData;//实际的数据
    private boolean select;

    public LotteryAttributeModel(String data, String relData, boolean select) {
        this.data = data;
        this.relData = relData;
        this.select = select;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getRelData() {
        return relData;
    }

    public void setRelData(String relData) {
        this.relData = relData;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
