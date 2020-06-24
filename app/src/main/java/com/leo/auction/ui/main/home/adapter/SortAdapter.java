package com.leo.auction.ui.main.home.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.ui.main.home.model.SortLeftModel;

import java.util.ArrayList;
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
public class SortAdapter extends BaseQuickAdapter<SortLeftModel.DataBean, BaseViewHolder> {

    private int mSelectedPosition;
    List<SortLeftModel.DataBean> mListData = new ArrayList<>();

    public SortAdapter(@Nullable List<SortLeftModel.DataBean> data) {
        super(R.layout.item_sort_left, data);
        mListData = data;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SortLeftModel.DataBean item) {
        TextView textView = helper.getView(R.id.textView);
        TextView nameTv = helper.getView(R.id.sort_name);
        nameTv.setText(item.getName());
        if (item.isSelected()) {
            textView.setVisibility(View.VISIBLE);
            nameTv.setTextColor(Color.parseColor("#7c1313"));
        } else {
            textView.setVisibility(View.INVISIBLE);
            nameTv.setTextColor(Color.parseColor("#020202"));
        }

    }

    public void setSelectedPosition(int position) {
        mListData.get(mSelectedPosition).setSelected(false);
        notifyItemChanged(mSelectedPosition);
        mListData.get(position).setSelected(true);
        notifyItemChanged(position);
        mSelectedPosition = position;
    }

    public int getSelectPosition(){

        return mSelectedPosition;
    }
}
