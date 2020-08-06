package com.leo.auction.ui.main.news.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.widget.glide.GlideUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.ui.main.news.model.NewsModel;
import com.leo.auction.utils.Globals;

import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.news.adapter
 * 作    者： Leo---悠悠球
 * 时    间： 2020/6/29
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class NewsAdapter extends BaseQuickAdapter<NewsModel.DataBean, BaseViewHolder> {


    public NewsAdapter() {
        super(R.layout.item_news_sys, null);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NewsModel.DataBean item) {
        helper.setIsRecyclable(false);
        helper.setText(R.id.item_time, item.getCreateTime());
        helper.setText(R.id.item_title, item.getTitle());
        helper.setText(R.id.item_content, item.getContent());

        Globals.log("xxxxxxx NewsModel"  + item);
        ImageView imageView = helper.getView(R.id.item_imageview);
        if (EmptyUtils.isEmpty(item.getPic())) {
            imageView.setVisibility(View.GONE);
            Globals.log("xxxxxxx NewsModel 01 "  + item);
        } else {

            imageView.setVisibility(View.VISIBLE);

            com.leo.auction.utils.GlideUtils.loadImg(imageView.getContext(),item.getPic(),imageView);

            Globals.log("xxxxxxx NewsModel 02 "  + item);
        }
    }
}
