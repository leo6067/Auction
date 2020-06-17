package com.leo.auction.ui.main.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;

import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.app.ui.main.home.adapter
 * 作    者： Leo---悠悠球
 * 时    间： 2020/6/10
 * 描    述： 具体分类
 * 修    改：
 * ===============================================
 */
public class SortRightAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Context mContext;

    public SortRightAdapter(Context context, @Nullable List<String> data) {
        super(R.layout.item_sort_right, data);
        mContext = context;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,3));

    }

}
