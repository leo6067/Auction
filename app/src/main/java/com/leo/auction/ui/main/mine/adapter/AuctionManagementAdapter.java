package com.leo.auction.ui.main.mine.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aten.compiler.widget.glide.GlideUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.base.Constants;
import com.leo.auction.ui.main.mine.model.ProductListModel;
import com.leo.auction.utils.Globals;
import com.ruffian.library.widget.RTextView;


public class AuctionManagementAdapter extends BaseQuickAdapter<ProductListModel.DataBean, BaseViewHolder> {



    private InterAuctionManage mInterAuctionManage;


    private int auction =0 ;

    public AuctionManagementAdapter(int auction,InterAuctionManage interAuctionManage) {
        super(R.layout.item_auction_manager, null);
        this.mInterAuctionManage = interAuctionManage;
        this.auction = auction;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ProductListModel.DataBean item) {


        ImageView houseImage = helper.getView(R.id.item_house);
        helper.setText(R.id.item_title, item.getTitle());

        helper.setText(R.id.item_qi, "起：￥" + item.getStartPrice());
        helper.setText(R.id.item_jia, "加：￥" + item.getMarkupRange());


        helper.setText(R.id.item_price, "￥" + item.getCurrentPrice());
        helper.setText(R.id.item_time, item.getCreateTime());
        helper.setText(R.id.item_status, item.getStatusName());
        TextView price = helper.getView(R.id.item_price);
        ImageView imageView = helper.getView(R.id.riv_product_pic);
        GlideUtils.loadImg(item.getFirstPic(), imageView);

        RTextView up = helper.getView(R.id.tv_01);
        RTextView down = helper.getView(R.id.tv_02);
        RTextView delete = helper.getView(R.id.tv_03);


        try {
            if (item.getSourceType().equals("2")){
                houseImage.setVisibility(View.VISIBLE);
            }else {
                houseImage.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (auction== 0 && item.getBidNum()==0) {  //竞拍中 显示下架
            down.setVisibility(View.VISIBLE);
            up.setVisibility(View.GONE);
            delete.setVisibility(View.GONE);
        }

        if (auction == 1) {  //
            up.setVisibility(View.GONE);
            down.setVisibility(View.GONE);
            delete.setVisibility(View.GONE);
        }

        if (auction == 2) {  //已失效 显示上架
            up.setVisibility(View.VISIBLE);
            down.setVisibility(View.GONE);
            delete.setVisibility(View.GONE);

        }

        if (auction== 3) {  //草稿箱 显示上架
            up.setVisibility(View.VISIBLE);
            delete.setVisibility(View.VISIBLE);
            down.setVisibility(View.GONE);
            price.setVisibility(View.GONE);
        }


        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInterAuctionManage.setOnAuctionUpListener(item);
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInterAuctionManage.setOnAutioDownListsner(item);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInterAuctionManage.setOnAuctionDeleteListener(item);
            }
        });

    }


    public interface InterAuctionManage {
          void setOnAuctionUpListener(ProductListModel.DataBean item);
          void setOnAutioDownListsner(ProductListModel.DataBean item);
          void setOnAuctionDeleteListener(ProductListModel.DataBean item);
    }


}
