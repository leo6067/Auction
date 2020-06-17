package com.leo.auction.mvp;

/**
 * Created by Leo on 2019/4/9.
 */

public interface BaseView  < T extends BaseModel>{

    //将 M 与 V 绑定
    void bindModel(T baseModel);
    //通用方法接口
    void showToast(String msg);

    void showLoading();

    void closeLoading();

    void setTittle();

}
