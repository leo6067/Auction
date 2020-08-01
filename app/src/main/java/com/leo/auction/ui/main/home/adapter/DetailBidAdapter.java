package com.leo.auction.ui.main.home.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.widget.glide.GlideUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.ui.login.model.CommonModel;
import com.leo.auction.ui.main.home.model.GoodsDetailModel;
import com.ruffian.library.widget.RTextView;

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
        super(R.layout.item_detail_bid, null);
    }


    public void setBidStaus(int bidStaus) {
        mBidStaus = bidStaus;
    }


    @Override
    protected void convert(@NonNull BaseViewHolder helper, GoodsDetailModel.DataBean.BidBean item) {
        helper.setText(R.id.item_name, item.getNickname());
        TextView itemBail = helper.getView(R.id.item_bail);
        RTextView itemStatus = helper.getView(R.id.item_status);
        ImageView headIV = helper.getView(R.id.item_head);
        ImageView ImgIV = helper.getView(R.id.item_img);
        //第一条，且状态是未成交。显示领先， 成交显示成交
//        if(get)bidStaus

        int position = helper.getAdapterPosition();

//        Globals.log("xxxxx   mDetailBidAdapter  000 LevelUrl" + mBidStaus  +"     " +position);
        if (position == 0) {
            if (mBidStaus == 1){
                itemStatus.setText("领先");
                itemStatus.setTextColor(Color.parseColor("#7c1313"));
                itemStatus.getHelper().setBorderColorNormal(Color.parseColor("#7c1313"));
            }else {
                itemStatus.setText("成交");
                itemStatus.setTextColor(Color.parseColor("#7c1313"));
                itemStatus.getHelper().setBorderColorNormal(Color.parseColor("#7c1313"));
            }
        }  else{
            itemStatus.setText( "出局");
            itemStatus.setTextColor(Color.parseColor("#a0a0a0"));
            itemStatus.getHelper().setBorderColorNormal(Color.parseColor("#a0a0a0"));
        }

        helper.setText(R.id.item_price, "￥" + item.getBidPrice());
        helper.setText(R.id.item_time, TimeUtils.millis2String(item.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));


        GlideUtils.loadImg(item.getHeadImg(), headIV);

        CommonModel.DataBean commonJson = BaseSharePerence.getInstance().getCommonJson();
        String[] myLevelPicS = commonJson.getMy_level_pic().get(0).split("auction_level_hd_");
        String LevelUrl = myLevelPicS[0] + "auction_level_hd_" + item.getLevel() + ".png";

        GlideUtils.loadImg(LevelUrl, ImgIV);

        itemBail.setVisibility(EmptyUtils.isEmpty(item.getEnsureMoney()) ? View.GONE : View.VISIBLE);
        itemBail.setText("已缴纳保证金￥" + EmptyUtils.strEmpty(item.getEnsureMoney()));
    }
}
