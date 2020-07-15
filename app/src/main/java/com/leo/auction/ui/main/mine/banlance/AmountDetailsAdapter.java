package com.leo.auction.ui.main.mine.banlance;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.aten.compiler.utils.EmptyUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.ui.main.mine.banlance.model.AmountDetailsModel;


/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.ui.mine.adapter
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/11/13 0013
 * 描    述：
 * ================================================
 */
public class AmountDetailsAdapter extends BaseQuickAdapter<AmountDetailsModel.DataBean, BaseViewHolder> {
    private View.OnClickListener onItemListener;

    public void setOnItemListener(View.OnClickListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    public AmountDetailsAdapter() {
        super(R.layout.layout_amount_detail_list_item, null);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, AmountDetailsModel.DataBean item) {
        TextView tvTitle=helper.getView(R.id.tv_title);
        TextView tvTime=helper.getView(R.id.tv_time);
        TextView tvMoney=helper.getView(R.id.tv_money);

        tvTitle.setText(EmptyUtils.strEmpty(item.getChangeName()));
        tvTime.setText(EmptyUtils.strEmpty(item.getChangeTime()));
        tvMoney.setText((item.isFlag()?"-":"+")+item.getChangeNum());

//        helper.itemView.setTag(item.getOrderId());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemListener!=null){
                    onItemListener.onClick(v);
                }
            }
        });
    }
}
