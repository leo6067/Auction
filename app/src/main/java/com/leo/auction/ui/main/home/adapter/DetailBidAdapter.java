package com.leo.auction.ui.main.home.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.ui.main.home.model.GoodsDetailModel;
import com.ruffian.library.widget.RTextView;

import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.home.adapter
 * 作    者： Leo---悠悠球
 * 时    间： 2020/6/22
 * 描    述： 商品详情出价列表
 * 修    改：
 * ===============================================
 */
public class DetailBidAdapter extends BaseQuickAdapter<GoodsDetailModel.DataBean.BidBean, BaseViewHolder> {

    private int mBidStaus = 0;

    public DetailBidAdapter() {
        super(R.layout.item_detail_bid,null);
    }



    public void setBidStaus(int bidStaus){
        mBidStaus = bidStaus;
    }


    @Override
    protected void convert(@NonNull BaseViewHolder helper, GoodsDetailModel.DataBean.BidBean item) {
        helper.setText(R.id.item_name,item.getNickname());
        RTextView itemStatus = helper.getView(R.id.item_status);
        //第一条，且状态是未成交。显示领先， 成交显示成交
//        if(get)bidStaus

        int parentPosition = getParentPosition(item);

        if (parentPosition == 0 && mBidStaus == 1){
            itemStatus.setText( "领先");
        }else {
            itemStatus.setText( "出局");
            itemStatus.setClickable(false);
            itemStatus.setTextColor(Color.parseColor("#a0a0a0"));
        }

        helper.setText(R.id.item_price,item.getBidPrice()+"");
        helper.setText(R.id.item_time,item.getCreateTime()+"");

    }
}
