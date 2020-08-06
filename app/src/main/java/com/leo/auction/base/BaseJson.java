package com.leo.auction.base;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.base
 * 作    者： Leo---悠悠球
 * 时    间： 2020/8/6
 * 描    述：  基础类型。主要用于适配器，多类型共用
 * 修    改：
 * ===============================================
 */
public class BaseJson {


    private String name;

    private String itemId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
