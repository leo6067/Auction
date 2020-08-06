package com.leo.auction.ui.main.mine.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.allen.library.shape.ShapeTextView;
import com.aten.compiler.widget.glide.GlideUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.ui.main.mine.model.SuperHouseModel;
import com.leo.auction.utils.Globals;
import com.ruffian.library.widget.RImageView;
import com.ruffian.library.widget.RTextView;

import java.util.List;

import butterknife.BindView;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.mine.adapter
 * 作    者： Leo---悠悠球
 * 时    间： 2020/8/5
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class SuperHouseAdapter extends BaseQuickAdapter<SuperHouseModel.DataBean, BaseViewHolder> {

    private StringInterFace mStringInterFace;
    public SuperHouseAdapter(StringInterFace stringInterFace) {
        super(R.layout.item_super_house);
        mStringInterFace = stringInterFace;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, SuperHouseModel.DataBean dataBean) {
        baseViewHolder.setText(R.id.item_name,dataBean.getTitle());
        baseViewHolder.setText(R.id.item_num,"库存："+dataBean.getStock());
        baseViewHolder.setText(R.id.item_price,"价格：￥"+dataBean.getStock());
        RImageView mItemHead = baseViewHolder.getView(R.id.item_head);
        GlideUtils.loadImg(dataBean.getFirstPic(),mItemHead);

        TextView mItemUp = baseViewHolder.getView(R.id.item_up);
        mItemUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Globals.log("xxxxxx  dataBean.getGoodsId() 01 " +dataBean.getGoodsId());
                mStringInterFace.bindString(dataBean.getGoodsId()+"");
            }
        });
    }



}
