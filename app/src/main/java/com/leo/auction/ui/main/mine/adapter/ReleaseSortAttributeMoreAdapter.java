package com.leo.auction.ui.main.mine.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.aten.compiler.utils.EmptyUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.ui.main.mine.model.ReleaseAuctionAttrModel;
import com.leo.auction.ui.main.mine.model.ReleaseSortModel;


public class ReleaseSortAttributeMoreAdapter extends BaseQuickAdapter<ReleaseAuctionAttrModel.DataBean.TagsBean, BaseViewHolder> {
    private ISortAttributeMore iSortAttributeMore;
    //记录选中的View
    private View mSelectedView;
    private ReleaseAuctionAttrModel.DataBean.TagsBean mSelectedReleaseSortData;

    public ReleaseSortAttributeMoreAdapter() {
        super(R.layout.layout_release_sort_item, null);

    }

    //设置数据之前调用
    public void setChooseListener(ISortAttributeMore iSortAttributeMore) {
        this.iSortAttributeMore = iSortAttributeMore;
    }

    @Override
    protected void convert(@NonNull final BaseViewHolder helper, final ReleaseAuctionAttrModel.DataBean.TagsBean item) {
        TextView tvAttrTag = helper.getView(R.id.tv_attr_tag);

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
                    if (iSortAttributeMore!=null){
                        iSortAttributeMore.sortAttributeMore(helper.getAdapterPosition());
                    }
                }
            }
        });
    }

    //获取被选中的数据
    public ReleaseAuctionAttrModel.DataBean.TagsBean getmSelectedReleaseSortData() {
        return mSelectedReleaseSortData;
    }
    //获取被选中的view
    public View getmSelectedView() {
        return mSelectedView;
    }

    //设置选中
    public void setSelectPos(int pos) {
        notifyItemChanged(pos);
    }

    public interface ISortAttributeMore {
        void sortAttributeMore(int pos);
    }
}
