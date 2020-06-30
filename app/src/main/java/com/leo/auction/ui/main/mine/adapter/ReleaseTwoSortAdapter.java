package com.leo.auction.ui.main.mine.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aten.compiler.utils.EmptyUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.ui.main.mine.IReleaseSortChoose;
import com.leo.auction.ui.main.mine.model.ReleaseSortModel;


public class ReleaseTwoSortAdapter extends BaseQuickAdapter<ReleaseSortModel.DataBean, BaseViewHolder> {
    private final IReleaseSortChoose iReleaseSortChoose;
    //记录选中的View
    private View mSelectedView;
    private ReleaseSortModel.DataBean mSelectedReleaseSortData;
    private boolean isFixedSize=true;

    public ReleaseTwoSortAdapter(IReleaseSortChoose iReleaseSortChoose) {
        super(R.layout.layout_release_sort_item, null);
        this.iReleaseSortChoose=iReleaseSortChoose;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, final ReleaseSortModel.DataBean item) {
        TextView tvAttrTag=helper.getView(R.id.tv_attr_tag);
        ViewGroup.LayoutParams layoutParams=(ViewGroup.LayoutParams)tvAttrTag.getLayoutParams();
        if (isFixedSize){
            layoutParams.width= (int) mContext.getResources().getDimension(R.dimen.dp_136);
        }else {
            layoutParams.width= ViewGroup.LayoutParams.MATCH_PARENT;
        }
        tvAttrTag.setLayoutParams(layoutParams);

        tvAttrTag.setText(EmptyUtils.strEmpty(item.getName()));

        if (item.isSelect()){
            tvAttrTag.setSelected(true);
            //初始化选中 默认第一个
            mSelectedView = tvAttrTag;
            mSelectedReleaseSortData=item;
        }else {
            tvAttrTag.setSelected(false);
        }

        tvAttrTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!view.isSelected()){
                    if (mSelectedView != null) {
                        mSelectedView.setSelected(false);
                        mSelectedReleaseSortData.setSelect(false);
                    }

                    item.setSelect(true);
                    view.setSelected(true);
                    mSelectedView = view;
                    mSelectedReleaseSortData=item;
                    iReleaseSortChoose.onTwoSortChoose(item,helper.getAdapterPosition());
                }
            }
        });
    }

    //设置item的宽度 isFixedSize是否是固定大小
    public void setItemWidth(boolean isFixedSize){
        this.isFixedSize=isFixedSize;
    }

    public ReleaseSortModel.DataBean getmSelectedReleaseSortData() {
        return mSelectedReleaseSortData;
    }
}
