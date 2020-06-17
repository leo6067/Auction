package com.leo.auction.ui.main.home.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;

import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名：
 * 作    者： Leo---悠悠球
 * 时    间： 2020/6/10
 * 描    述： 分类左侧
 * 修    改：
 * ===============================================
 */
public class SortAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public SortAdapter(@Nullable List<String> data) {
        super(R.layout.item_sort_left, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {

    }
}
