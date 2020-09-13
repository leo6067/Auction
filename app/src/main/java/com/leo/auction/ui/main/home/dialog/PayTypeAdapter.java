package com.leo.auction.ui.main.home.dialog;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.aten.compiler.utils.EmptyUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.ui.order.model.OrderPayTypeModel;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.utils.payDialog
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/11/14 0014
 * 描    述：
 * ================================================
 */
public class PayTypeAdapter extends BaseQuickAdapter<OrderPayTypeModel, BaseViewHolder> {
    private PayPwdBoardUtils.IPayType iPayType;
    private int currentChoosePos=0;//记录当前选中的postion
    private CheckBox currentChooseCB;//记录当前选中的checkbox

    public PayTypeAdapter(PayPwdBoardUtils.IPayType iPayType) {
        super(R.layout.layout_pay_type_item, null);
        this.iPayType=iPayType;
    }

    @Override
    protected void convert(@NonNull final BaseViewHolder helper, final OrderPayTypeModel item) {
        ImageView ivPayIcon=helper.getView(R.id.iv_pay_icon);
        TextView tvPayName=helper.getView(R.id.tv_pay_name);
        TextView tvPayDescribe=helper.getView(R.id.tv_pay_describe);
        FrameLayout flChoose=helper.getView(R.id.fl_choose);
        final CheckBox cbChoose=helper.getView(R.id.cb_choose);

        Glide.with(ivPayIcon.getContext()).load(item.getPayIcon()).fitCenter().
                placeholder(R.color.color_f0f0f0).error(R.color.color_f0f0f0).into(ivPayIcon);
        tvPayName.setText(EmptyUtils.strEmpty(item.getPayName()));
        tvPayDescribe.setText(EmptyUtils.strEmpty(item.getPayDescribe()));

        if (item.isChoose()){
            cbChoose.setChecked(true);
            currentChooseCB=cbChoose;
            currentChoosePos=helper.getAdapterPosition();
        }else {
            cbChoose.setChecked(false);
        }

        flChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //如果当前点击的pos跟记录的当前选中的是一样的 则不处理
                if (currentChoosePos==helper.getAdapterPosition()||!item.isChooseEnable()){
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

    //获取选中的pos
    public int getChoosePos(){
        return currentChoosePos;
    }
}
