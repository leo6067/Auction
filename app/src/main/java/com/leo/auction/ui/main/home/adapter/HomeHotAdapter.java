package com.leo.auction.ui.main.home.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.aten.compiler.widget.glide.GlideUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.ui.login.model.CommonModel;
import com.leo.auction.ui.main.home.model.HomeListModel;
import com.leo.auction.ui.main.home.model.HotModel;
import com.leo.auction.utils.SpannableStringUtils;


/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.home.adapter
 * 作    者： Leo---悠悠球
 * 时    间： 2020/8/10
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class HomeHotAdapter extends BaseQuickAdapter<HotModel.DataBean, BaseViewHolder> {


    public HomeHotAdapter() {
        super(R.layout.item_home_hot, null);

    }


    @Override
    protected void convert(@NonNull BaseViewHolder helper, final HotModel.DataBean item) {
        ImageView mItemHead = helper.getView(R.id.item_head);
        ImageView mItemLevel = helper.getView(R.id.item_level);
        ImageView mItemImg = helper.getView(R.id.item_img);
        ImageView mItemImgA = helper.getView(R.id.item_img_a);
        ImageView mItemImgB = helper.getView(R.id.item_img_b);
        TextView mItemName = helper.getView(R.id.item_name);
        TextView mItemShop = helper.getView(R.id.item_shop);
        TextView mItemPrice = helper.getView(R.id.item_price);


        mItemName.setText(item.getNickname());



        CommonModel.DataBean commonJson = BaseSharePerence.getInstance().getCommonJson();
        String[] myLevelVPicS = commonJson.getSeller_level_pic().get(0).split("seller_level_");
        String vipUrl = myLevelVPicS[0] + "seller_level_" + item.getLevel() + ".png";
        GlideUtils.loadImgDefault(vipUrl, mItemLevel);


        GlideUtils.loadImg(item.getHeadImg(),mItemHead);
        GlideUtils.loadImg(item.getProductList().get(0).getFirstPic(),mItemImg);

        mItemPrice.setText(SpannableStringUtils.getBuilder("￥").append(item.getProductList().get(0).getCurrentPrice()+"  ").setProportion((float)1.3)
        .create());

//        mItemPrice.setText(+item.getProductList().get(0).getCurrentPrice()+" ");
        GlideUtils.loadImg(item.getProductList().get(1).getFirstPic(),mItemImgA);
        GlideUtils.loadImg(item.getProductList().get(2).getFirstPic(),mItemImgB);


    }


}

