package com.leo.auction.ui.order.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.ui.order.model.CancleOrderModel;


/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.ui.order.adapter
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/10/28 0028
 * 描    述：
 * ================================================
 */
public class CancleOrderAdapter extends BaseQuickAdapter<CancleOrderModel, BaseViewHolder> {
    private int currentChoosePos=0;//记录当前选中的postion
    private CheckBox currentChooseCB;//记录当前选中的checkbox

    public CancleOrderAdapter() {
        super(R.layout.layout_cancle_order_item, null);
    }

    @Override
    protected void convert(@NonNull final BaseViewHolder helper, final CancleOrderModel item) {
        LinearLayout allCancleOrderItem=helper.getView(R.id.all_cancle_order_item);
        final CheckBox cbChoose=helper.getView(R.id.cb_choose);
        TextView tvDescribe=helper.getView(R.id.tv_describe);

        if (item.isChoose()){
            cbChoose.setChecked(true);
            currentChooseCB=cbChoose;
        }else {
            cbChoose.setChecked(false);
        }

        tvDescribe.setText(item.getText());

        allCancleOrderItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //如果当前点击的pos跟记录的当前选中的是一样的 则不处理
                if (currentChoosePos==helper.getAdapterPosition()){
                    return;
                }
                //清除上一次选中的
                getItem(currentChoosePos).setChoose(false);
                currentChooseCB.setChecked(false);

                //选中当前点击的
                cbChoose.setChecked(true);
                item.setChoose(true);
                //记录当前点击的pos
                currentChoosePos=helper.getAdapterPosition();
                currentChooseCB=cbChoose;
            }
        });
    }

    //获取当前选中的数据
    public CancleOrderModel getChooseItem(){
        return getItem(currentChoosePos);
    }

    public int getCurrentChoosePos() {
        return currentChoosePos;
    }
}
