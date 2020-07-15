package com.leo.auction.ui.order.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.aten.compiler.utils.EmptyUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.model.CommonModel;
import com.leo.auction.ui.order.model.EvaluationLableModel;


public class EvaluationLableAdapter extends BaseQuickAdapter<CommonModel, BaseViewHolder> {

    public EvaluationLableAdapter() {
        super(R.layout.layout_evaluation_lable_item, null);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, final CommonModel item) {
        TextView tvAttrTag=helper.getView(R.id.tv_attr_tag);

        tvAttrTag.setText(EmptyUtils.strEmpty(item.getCommonStr()));

        if (item.isCommonSelect()){
            tvAttrTag.setSelected(true);
        }else {
            tvAttrTag.setSelected(false);
        }

        tvAttrTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.isSelected()){
                    item.setCommonSelect(false);
                    view.setSelected(false);
                }else {
                    item.setCommonSelect(true);
                    view.setSelected(true);
                }
            }
        });
    }
}
