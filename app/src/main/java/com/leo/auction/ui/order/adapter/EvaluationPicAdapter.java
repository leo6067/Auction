package com.leo.auction.ui.order.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.aten.compiler.widget.RadiusImageView;
import com.aten.compiler.widget.glide.GlideUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;


import java.util.ArrayList;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.ui.order.adapter
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/10/31
 * 描    述：
 * ================================================
 */
public class EvaluationPicAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private View.OnClickListener mOnItemListener;
    private ArrayList<ImageView> imgViews=new ArrayList<>();

    public void setmOnItemListener(View.OnClickListener mOnItemListener) {
        this.mOnItemListener = mOnItemListener;
    }

    public EvaluationPicAdapter() {
        super(R.layout.layout_evaluation_pic_item, null);
    }


    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        RadiusImageView rivImg=helper.getView(R.id.riv_img);
        GlideUtils.loadImg(item,rivImg);

        imgViews.add(rivImg);
        rivImg.setTag(R.id.tag_1,helper.getAdapterPosition());
        rivImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemListener.onClick(view);
            }
        });
    }

    public ArrayList<ImageView> getImgViews() {
        return imgViews;
    }

    public void clearImgViews(){
        imgViews.clear();
    }
}
