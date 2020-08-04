package com.leo.auction.ui.main.home.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.aten.compiler.widget.RatioImageView;
import com.aten.compiler.widget.glide.GlideUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.ui.main.home.model.PicGridNineModel;


import java.util.ArrayList;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.ui.home.adapter
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/10/30 0030
 * 描    述：
 * ================================================
 */
public class PicGridNineAdapter extends BaseQuickAdapter<PicGridNineModel, BaseViewHolder> {
    private final IGridNine iGridNine;
    private ArrayList<RatioImageView> imgViews=new ArrayList<>();
    private int hasVideoSize=0;

    public PicGridNineAdapter(IGridNine iGridNine) {
        super(R.layout.item_pic_grid_nine, null);
        this.iGridNine=iGridNine;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, PicGridNineModel item) {
        RatioImageView rivGridNineItem=helper.getView(R.id.riv_grid_nine_item);
        ImageView ivVideo=helper.getView(R.id.iv_video);

        GlideUtils.loadImg(item.getImage(),rivGridNineItem,R.drawable.define_shop,R.drawable.define_shop);

        if ("1".equals(item.getIsVideo())){
            ivVideo.setVisibility(View.VISIBLE);
            imgViews.remove(rivGridNineItem);
        }else {
            ivVideo.setVisibility(View.GONE);
            imgViews.add(rivGridNineItem);
        }

        rivGridNineItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("1".equals(item.getIsVideo())){
                    iGridNine.onVideoClick(item.getVideoPath(),item.getImage());
                }else {
                    iGridNine.onImgClick(helper.getAdapterPosition()-hasVideoSize);
                }

            }
        });
    }

    public ArrayList<RatioImageView> getImgViews() {
        return imgViews;
    }

    public void clearImgViews(){
        imgViews.clear();
    }


    public void setHasVideoSize(int hasVideoSize) {
        this.hasVideoSize = hasVideoSize;
    }

    public interface IGridNine{
        void onVideoClick(String videoPath, String thumbImageView);
        void onImgClick(int pos);
    }
}
