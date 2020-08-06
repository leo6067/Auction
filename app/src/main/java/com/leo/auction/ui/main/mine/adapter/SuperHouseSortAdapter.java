package com.leo.auction.ui.main.mine.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aten.compiler.utils.EmptyUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.base.BaseJson;
import com.leo.auction.ui.main.home.model.SortLeftModel;
import com.leo.auction.ui.main.mine.IReleaseSortChoose;
import com.leo.auction.utils.Globals;

import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.mine.adapter
 * 作    者： Leo---悠悠球
 * 时    间： 2020/8/6
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class SuperHouseSortAdapter extends BaseQuickAdapter<SortLeftModel.DataBean, BaseViewHolder> {
    //记录选中的View
    private View mSelectedView;
    private boolean isFixedSize = true;
    SortLeftModel.DataBean mSelectedReleaseSortData;

    private   IReleaseSortChoose iReleaseSortChoose;
    public SuperHouseSortAdapter(IReleaseSortChoose iReleaseSortChoose) {
        super(R.layout.item_super_sort);
        this.iReleaseSortChoose = iReleaseSortChoose;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SortLeftModel.DataBean item) {


        TextView tvAttrTag = helper.getView(R.id.tv_attr_tag);
        ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) tvAttrTag.getLayoutParams();
        if (isFixedSize) {
            layoutParams.width = (int) mContext.getResources().getDimension(R.dimen.dp_136);
        } else {
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        }
        tvAttrTag.setLayoutParams(layoutParams);

        tvAttrTag.setText(EmptyUtils.strEmpty(item.getName()));
        if (item.isSelected()) {
            tvAttrTag.setSelected(true);
            //初始化选中 默认第一个
            mSelectedView = tvAttrTag;
            mSelectedReleaseSortData = item;
        } else {
            tvAttrTag.setSelected(false);
        }

        tvAttrTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!view.isSelected()) {
                    if (mSelectedView != null) {
                        mSelectedView.setSelected(false);
                        mSelectedReleaseSortData.setSelected(false);
                    }

                    item.setSelected(true);
                    view.setSelected(true);
                    mSelectedView = view;
                    mSelectedReleaseSortData = item;
                    iReleaseSortChoose.onOneSortChoose(item, helper.getAdapterPosition());
                }
            }
        });


    }
}
