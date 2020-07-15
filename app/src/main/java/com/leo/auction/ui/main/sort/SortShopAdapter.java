package com.leo.auction.ui.main.sort;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.aten.compiler.widget.glide.GlideUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.ui.login.model.CommonModel;
import com.ruffian.library.widget.RImageView;

import java.util.List;

import butterknife.BindView;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.sort
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/10
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class SortShopAdapter extends BaseQuickAdapter<SortShopModel.DataBean, BaseViewHolder> {
    public SortShopAdapter() {
        super(R.layout.item_sort_shop, null);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, SortShopModel.DataBean dataBean) {

         ImageView mItemHead = baseViewHolder.getView(R.id.item_head);
        GlideUtils.loadImg(dataBean.getProductUser().getHeadImg(), mItemHead);


        ImageView mItemLevel = baseViewHolder.getView(R.id.item_level);

        CommonModel.DataBean commonJson = BaseSharePerence.getInstance().getCommonJson();
        String[] myLevelPicS = commonJson.getSeller_level_pic().get(0).split("seller_level_");
        String LevelUrl = myLevelPicS[0] + "seller_level_" + dataBean.getProductUser().getSellerLevel() + ".png";
        GlideUtils.loadImg(LevelUrl, mItemLevel);

        TextView mItemName = baseViewHolder.getView(R.id.item_name);
        mItemName.setText(dataBean.getProductUser().getNickname());

        TextView mItemScore = baseViewHolder.getView(R.id.item_score);
        mItemScore.setText("评分  "+dataBean.getProductUser().getRate());

        TextView mItemFan = baseViewHolder.getView(R.id.item_fan);
        mItemFan.setText("粉丝数  "+dataBean.getProductUser().getFansNum());

        TextView mItemNext = baseViewHolder.getView(R.id.item_next);
        mItemNext.setText("上新"+dataBean.getNewestNum() + "件");


    }
}
