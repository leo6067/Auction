package com.leo.auction.ui.main.mine.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.ui.main.mine.model.SuperHouseModel;

import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.mine.adapter
 * 作    者： Leo---悠悠球
 * 时    间： 2020/8/5
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class SuperHouseAdapter extends BaseQuickAdapter<SuperHouseModel.DataBean, BaseViewHolder> {
    public SuperHouseAdapter() {
        super(R.layout.item_super_house);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, SuperHouseModel.DataBean dataBean) {

    }
}
