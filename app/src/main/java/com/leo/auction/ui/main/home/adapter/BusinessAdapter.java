package com.leo.auction.ui.main.home.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.aten.compiler.widget.glide.GlideUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.ui.main.home.model.SupplierModel;

import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.home.adapter
 * 作    者： Leo---悠悠球
 * 时    间： 2020/6/23
 * 描    述： 搜索商家列表
 * 修    改：
 * ===============================================
 */
public class BusinessAdapter extends BaseQuickAdapter<SupplierModel.DataBean, BaseViewHolder> {
    public BusinessAdapter() {
        super(R.layout.item_supplier, null);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SupplierModel.DataBean item) {

        ImageView headImage = helper.getView(R.id.item_head);

        GlideUtils.loadImg(item.getProductUser().getHeadImg(),headImage);

        ImageView imageView = helper.getView(R.id.item_level);

        helper.setText(R.id.item_name,item.getProductUser().getNickname());
        helper.setText(R.id.item_mark,"评分 " +item.getProductUser().getSellerLevel());
        helper.setText(R.id.item_fan,"粉丝 " +item.getProductUser().getFansNum());
        helper.setText(R.id.item_new,"上新 " +item.getNewestNum() + "件");

    }
}
