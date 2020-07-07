package com.leo.auction.ui.main.mine;


import com.leo.auction.ui.main.home.model.SortLeftModel;
import com.leo.auction.ui.main.mine.model.ReleaseSortModel;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.ui.seller
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/11/29 0029
 * 描    述：
 * ================================================
 */
public interface IReleaseSortChoose {

    void onOneSortChoose(SortLeftModel.DataBean oneSortData, int position);
    void onTwoSortChoose(SortLeftModel.DataBean.ChildrenBean oneSortData, int position);
}
