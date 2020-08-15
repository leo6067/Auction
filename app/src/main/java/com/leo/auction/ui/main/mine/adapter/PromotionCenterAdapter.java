package com.leo.auction.ui.main.mine.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.widget.glide.GlideUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.ui.main.mine.model.PromotionCenterModel;
import com.leo.auction.utils.Globals;


public class PromotionCenterAdapter extends BaseQuickAdapter<PromotionCenterModel, BaseViewHolder> {
    private View.OnClickListener onItemListener;

    public void setOnItemListener(View.OnClickListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    public PromotionCenterAdapter() {
        super(R.layout.layout_promotion_center_item, null);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, PromotionCenterModel item) {
        ImageView ivIcon=helper.getView(R.id.iv_icon);
        TextView tvTitle=helper.getView(R.id.tv_title);

        if (item.getIcon()==-1){
            ivIcon.setVisibility(View.GONE);
        }else {
            ivIcon.setVisibility(View.VISIBLE);
            GlideUtils.loadImg(item.getIcon(),ivIcon);
        }


        tvTitle.setText(EmptyUtils.strEmpty(item.getTitle()));

//        helper.itemView.setTag(item.getTitle());
//        helper.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Globals.log("xxxxxx 推广二维码 02 ");
//                onItemListener.onClick(view);
//            }
//        });
    }
}
