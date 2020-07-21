package com.leo.auction.ui.main.home.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aten.compiler.widget.countDownTime.CountdownView;
import com.aten.compiler.widget.glide.GlideUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.ui.main.home.model.SubsidyModel;

import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.home.adapter
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/17
 * 描    述： 百亿补贴
 * 修    改：
 * ===============================================
 */
public class HomeTitleAdapter extends BaseQuickAdapter<SubsidyModel.DataBean, BaseViewHolder> {

    public HomeTitleAdapter() {
        super(R.layout.item_title_home, null);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, SubsidyModel.DataBean json) {

        ImageView itemImage = baseViewHolder.getView(R.id.item_img);
        TextView itemPrice = baseViewHolder.getView(R.id.item_price);
        TextView itemHint = baseViewHolder.getView(R.id.item_hint);
        CountdownView itemTime = baseViewHolder.getView(R.id.item_time);

        itemTime.start(json.getInterceptTime());

        GlideUtils.loadImg(json.getFirstPic(), itemImage);

        itemPrice.setText("￥" + json.getCurrentPrice());
        itemHint.setText("补贴￥" + json.getSubsidyMoney());
    }


}
