package com.leo.auction.ui.main.mine.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.shape.ShapeTextView;
import com.aten.compiler.utils.DateUtil;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.widget.RadiusImageView;
import com.aten.compiler.widget.glide.GlideUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.ui.main.mine.model.CommodityManagementModel;


public class CommodityManagementAdapter extends BaseQuickAdapter<CommodityManagementModel.DataBean, BaseViewHolder> {
    private View.OnClickListener onItemListsner;
    private View.OnClickListener onBtnListsner;

    public void setOnItemListsner(View.OnClickListener onItemListsner) {
        this.onItemListsner = onItemListsner;
    }

    public void setOnBtnListsner(View.OnClickListener onBtnListsner) {
        this.onBtnListsner = onBtnListsner;
    }

    public CommodityManagementAdapter() {
        super(R.layout.layout_commodity_manager_item, null);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CommodityManagementModel.DataBean item) {
        RelativeLayout rlTopContain= helper.getView(R.id.rl_top_contain);
        RadiusImageView rivProductPic = helper.getView(R.id.riv_product_pic);
        TextView tvProductTitle = helper.getView(R.id.tv_product_title);
        TextView tvSellerPrice = helper.getView(R.id.tv_seller_price);
        TextView tvStock = helper.getView(R.id.tv_stock);
        TextView tvSupplyPrice = helper.getView(R.id.tv_supply_price);
        TextView tvSalesVolume = helper.getView(R.id.tv_sales_volume);
        TextView tvTime= helper.getView(R.id.tv_time);
        TextView tvNum= helper.getView(R.id.tv_num);
        ShapeTextView tv01= helper.getView(R.id.tv_01);
        ShapeTextView tv02= helper.getView(R.id.tv_02);
        ShapeTextView tv03= helper.getView(R.id.tv_03);

        GlideUtils.loadImg(item.getUrl(), rivProductPic);
        tvProductTitle.setText(EmptyUtils.strEmpty(item.getGoodsTitle()));
        tvSellerPrice.setText("销售价：¥"+item.getPrice());
        tvStock.setText("库存："+item.getStock());
        tvSupplyPrice.setText("供货价：¥" + item.getAgentPrice());
        tvSalesVolume.setText("销量：" + item.getSales());
        tvTime.setText("创建时间：" + DateUtil.TimeStamp2Date2(item.getCreateTime(),"yyyy年MM月dd日 HH:mm:ss"));
        tvNum.setText(String.valueOf(helper.getAdapterPosition()+1));
        //状态 00A-上架 00B-下架  00C-草稿箱 00Z-失效
        switch (item.getStatus()){
            case "00A":
                setBtnVivsibility(helper,1,1,0);
                tv01.setText("下架");
                tv02.setText("编辑");
                break;
            case "00B":
                setBtnVivsibility(helper,1,1,1);
                tv01.setText("上架");
                tv02.setText("编辑");
                tv03.setText("删除");
                break;
            case "00C":
                setBtnVivsibility(helper,1,1,1);
                tv01.setText("上架");
                tv02.setText("编辑");
                tv03.setText("删除");
                break;
            case "00Z":
                break;
        }

        tv01.setTag(R.id.tag_1,tv01.getText().toString());
        tv01.setTag(R.id.tag_2,item);
        tv01.setTag(R.id.tag_3,helper.getAdapterPosition());
        tv01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnListsner.onClick(v);
            }
        });

        tv02.setTag(R.id.tag_1,tv02.getText().toString());
        tv02.setTag(R.id.tag_2,item);
        tv02.setTag(R.id.tag_3,helper.getAdapterPosition());
        tv02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnListsner.onClick(v);
            }
        });

        tv03.setTag(R.id.tag_1,tv03.getText().toString());
        tv03.setTag(R.id.tag_2,item);
        tv03.setTag(R.id.tag_3,helper.getAdapterPosition());
        tv03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnListsner.onClick(v);
            }
        });

        rlTopContain.setTag(R.id.tag_1,item.getId());
        rlTopContain.setTag(R.id.tag_2,item.getStatus());
        rlTopContain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemListsner.onClick(view);
            }
        });
    }

    //设置按钮得显示
    private void setBtnVivsibility(BaseViewHolder helper, int tv01Visibility, int tv02Visibility, int tv03Visibility){
        ShapeTextView tv01= helper.getView(R.id.tv_01);
        ShapeTextView tv02= helper.getView(R.id.tv_02);
        ShapeTextView tv03= helper.getView(R.id.tv_03);

        tv01.setVisibility(tv01Visibility==1? View.VISIBLE:View.GONE);
        tv02.setVisibility(tv02Visibility==1? View.VISIBLE:View.GONE);
        tv03.setVisibility(tv03Visibility==1? View.VISIBLE:View.GONE);
    }
}
