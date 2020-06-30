package com.leo.auction.ui.main.mine.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.aten.compiler.utils.EmptyUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.ui.order.model.LotteryAttributeModel;

import java.util.List;


public class LotteryTimeAdapter extends BaseQuickAdapter<LotteryAttributeModel, BaseViewHolder> {
    private final ILotteryAttribute iLotteryAttribute;
    //记录选中的View
    private View mSelectedView;
    private LotteryAttributeModel mSelectedReleaseSortData;

    public LotteryTimeAdapter(@Nullable List<LotteryAttributeModel> data, ILotteryAttribute iLotteryAttribute) {
        super(R.layout.layout_lottery_time_item, data);
        this.iLotteryAttribute=iLotteryAttribute;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, final LotteryAttributeModel item) {
        TextView tvAttrTag=helper.getView(R.id.tv_attr_tag);

        tvAttrTag.setText(EmptyUtils.strEmpty(item.getData()));
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
                    iLotteryAttribute.onAttributeChoose(item);
                }
            }
        });
    }

    public LotteryAttributeModel getmSelectedReleaseSortData() {
        return mSelectedReleaseSortData;
    }

    public interface ILotteryAttribute{
        void onAttributeChoose(LotteryAttributeModel item);
    }
}
