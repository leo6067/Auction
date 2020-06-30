package com.leo.auction.ui.common.logisticsDialog;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.widget.TextView;

import com.aten.compiler.utils.ConvertUtils;
import com.aten.compiler.utils.RxTool;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.ui.common.model.LogisticsTrajectoryModel;



import java.util.List;


/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.widget.logisticsDialog
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/10/27
 * 描    述：
 * ================================================
 */
public class LogisticsAdapter extends BaseQuickAdapter<LogisticsTrajectoryModel.DataBean.ListBean, BaseViewHolder> {
    public LogisticsAdapter(@Nullable List<LogisticsTrajectoryModel.DataBean.ListBean> data) {
        super(R.layout.layout_logistics_item, data);
    }
    
    @Override
    protected void convert(@NonNull BaseViewHolder helper, LogisticsTrajectoryModel.DataBean.ListBean item) {
        TextView itemMsg= helper.getView(R.id.itemMsg);
        TextView itemDate= helper.getView(R.id.itemDate);
//        itemMsg.addAutoLinkMode(MODE_PHONE.INSTANCE);

        itemMsg.setText(item.getStatus());
        itemDate.setText(item.getTime());
        
        if (helper.getAdapterPosition()==0){
            itemMsg.setTextColor(Color.parseColor("#373737"));
            itemDate.setTextColor(Color.parseColor("#6B6B6B"));
            itemMsg.setTextSize(TypedValue.COMPLEX_UNIT_SP, ConvertUtils.px2sp(mContext.getResources().getDimension(R.dimen.sp_24)));
            itemDate.setTextSize(TypedValue.COMPLEX_UNIT_SP, ConvertUtils.px2sp(mContext.getResources().getDimension(R.dimen.sp_20)));
        }else {
            itemMsg.setTextColor(Color.parseColor("#A6A6A6"));
            itemDate.setTextColor(Color.parseColor("#A6A6A6"));
            itemMsg.setTextSize(TypedValue.COMPLEX_UNIT_SP, ConvertUtils.px2sp(mContext.getResources().getDimension(R.dimen.sp_20)));
            itemDate.setTextSize(TypedValue.COMPLEX_UNIT_SP, ConvertUtils.px2sp(mContext.getResources().getDimension(R.dimen.sp_20)));
        }

//        itemMsg.onAutoLinkClick(new Function1<AutoLinkItem, Unit>() {
//            @Override
//            public Unit invoke(AutoLinkItem autoLinkItem) {
//                if (autoLinkItem.getMode() instanceof MODE_PHONE){
//                    RxTool.showPhone(mContext,autoLinkItem.getOriginalText());
//                }
//                return null;
//            }
//        });
    }
}
