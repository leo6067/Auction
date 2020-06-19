package com.leo.auction.ui.main.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.ui.main.home.model.SortLeftModel;
import com.leo.auction.utils.GlideUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.home.adapter
 * 作    者： Leo---悠悠球
 * 时    间： 2020/6/19
 * 描    述： 图片文字适配器
 * 修    改：
 * ===============================================
 */
public class ImageTextAdapter extends BaseQuickAdapter<SortLeftModel.DataBean.ChildrenBean,BaseViewHolder> {


    public ImageTextAdapter( @Nullable List<SortLeftModel.DataBean.ChildrenBean> data) {
        super(R.layout.item_image_text, data);
    }


    @Override
    protected void convert(@NonNull BaseViewHolder helper, SortLeftModel.DataBean.ChildrenBean item) {
        ImageView imageView = helper.getView(R.id.imageView);
        TextView textView = helper.getView(R.id.textView);
        GlideUtils.loadImg(mContext,item.getIcon(),imageView);
        textView.setText(item.getName());
    }

}
