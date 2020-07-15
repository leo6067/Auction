package com.leo.auction.ui.order.adapter;

import android.support.annotation.NonNull;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;



public class CompleteEvaluationLableAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    public CompleteEvaluationLableAdapter() {
        super(R.layout.layout_evaluation_lable_item, null);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, final Integer item) {
        TextView tvAttrTag=helper.getView(R.id.tv_attr_tag);
        switch (item){
            case 1:
                tvAttrTag.setText("物超所值");
                break;
            case 2:
                tvAttrTag.setText("物流给力");
                break;
            case 4:
                tvAttrTag.setText("服务贴心");
                break;
            case 8:
                tvAttrTag.setText("包装精美");
                break;
            case 16:
                tvAttrTag.setText("捡到漏了");
                break;
            case 32:
                tvAttrTag.setText("值得信赖");
                break;
        }
        tvAttrTag.setSelected(true);
        tvAttrTag.setEnabled(false);
    }
}
