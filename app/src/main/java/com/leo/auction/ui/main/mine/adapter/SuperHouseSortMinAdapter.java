package com.leo.auction.ui.main.mine.adapter;

import android.support.annotation.NonNull;
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
public class SuperHouseSortMinAdapter extends BaseQuickAdapter<SortLeftModel.DataBean.ChildrenBean, BaseViewHolder> {

    //记录选中的View
    private View mSelectedView;
    private boolean isFixedSize = true;
    SortLeftModel.DataBean.ChildrenBean mSelectedReleaseSortData;

    private   IReleaseSortChoose iReleaseSortChoose;
    public SuperHouseSortMinAdapter(IReleaseSortChoose iReleaseSortChoose) {
        super(R.layout.item_super_sort);
        this.iReleaseSortChoose = iReleaseSortChoose;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SortLeftModel.DataBean.ChildrenBean item) {

        TextView tvAttrTag = helper.getView(R.id.tv_attr_tag);
        ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) tvAttrTag.getLayoutParams();
        if (isFixedSize) {
            layoutParams.width = (int) mContext.getResources().getDimension(R.dimen.dp_136);
        } else {
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        }
        tvAttrTag.setLayoutParams(layoutParams);

        tvAttrTag.setText(EmptyUtils.strEmpty(item.getName()));
        if (item.isSelect()) {
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
                        mSelectedReleaseSortData.setSelect(false);
                    }

                    item.setSelect(true);
                    view.setSelected(true);
                    mSelectedView = view;
                    mSelectedReleaseSortData = item;
                    iReleaseSortChoose.onTwoSortChoose(item, helper.getAdapterPosition());
                }
            }
        });
    }
}
