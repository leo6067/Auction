package com.leo.auction.utils.shared_dailog;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.aten.compiler.widget.glide.GlideUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;


import java.util.List;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.utils.shared_dailog
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/11/3 0003
 * 描    述：
 * ================================================
 */
public class CrlBtnAdapter extends BaseQuickAdapter<CrlBtnModel, BaseViewHolder> {
    private View.OnClickListener onItemListener;

    public void setOnItemListener(View.OnClickListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    public CrlBtnAdapter(@Nullable List<CrlBtnModel> data) {
        super(R.layout.layout_crlbtn_item, data);
        Log.e("66666","data.size()="+data.size());
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CrlBtnModel item) {
        LinearLayout llLink=helper.getView(R.id.ll_link);
        ImageView iv=helper.getView(R.id.iv);

        if (helper.getAdapterPosition()==0){
            llLink.setBackgroundResource(R.drawable.item_topleft_corner_selector);
        }else if (helper.getAdapterPosition()==getItemCount()-1){
            llLink.setBackgroundResource(R.drawable.item_topright_corner_selector);
        }else {
            llLink.setBackgroundResource(R.drawable.item_selector);
        }

        Glide.with(mContext.getApplicationContext())
                .load(item.getImgRes())
                .into(iv);
        helper.setText(R.id.tv,item.getTitle());

        helper.itemView.setTag(item.getId());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemListener.onClick(v);
            }
        });
    }
}
