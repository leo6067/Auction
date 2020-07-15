package com.leo.auction.ui.order.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.aten.compiler.widget.glide.GlideUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * project:yfqc_android
 * package:com.aten.yuncar.ui.community.adapter
 * Created by 彭俊鸿 on 2018/5/3.
 * e-mail : 1031028399@qq.com
 */

public class PostOssEvaluationImglistAdapter extends BaseQuickAdapter<File, BaseViewHolder> {
    private View.OnClickListener mOnLastImgListener;
    private View.OnClickListener mOnImgsItemListener;
    private View.OnClickListener mOnImgsItemDeleteListener;
    private ArrayList<ImageView> imgViews=new ArrayList<>();

    public PostOssEvaluationImglistAdapter(@Nullable List<File> data) {
        super(R.layout.layout_postimglist_item, data);
    }

    public void setmOnLastImgListener(View.OnClickListener mOnLastImgListener) {
        this.mOnLastImgListener = mOnLastImgListener;
    }

    public void setmOnImgsItemListener(View.OnClickListener mOnImgsItemListener) {
        this.mOnImgsItemListener = mOnImgsItemListener;
    }

    public void setmOnImgsItemDeleteListener(View.OnClickListener mOnImgsItemDeleteListener) {
        this.mOnImgsItemDeleteListener = mOnImgsItemDeleteListener;
    }


    @Override
    protected void convert(BaseViewHolder helper, final File item) {
        ImageView ivPhoto = (ImageView) helper.getView(R.id.iv_photo);
        ImageView ivDelete = (ImageView) helper.getView(R.id.iv_delete);

        imgViews.add(ivPhoto);

        if ("lastImg".equals(item.getPath())){
            GlideUtils.loadImg(R.drawable.ic_general_append_bg,ivPhoto);
            ivDelete.setVisibility(View.GONE);
        }else {
            GlideUtils.loadImg(item,ivPhoto);
            ivDelete.setVisibility(View.VISIBLE);
        }

        if (helper.getAdapterPosition()==6){
            ivPhoto.setVisibility(View.GONE);
        }else {
            ivPhoto.setVisibility(View.VISIBLE);
        }

        ivPhoto.setTag(R.id.tag_1,helper.getAdapterPosition());
        ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("lastImg".equals(item.getPath())){
                    mOnLastImgListener.onClick(v);
                }else {
                    mOnImgsItemListener.onClick(v);
                }
            }
        });

        ivDelete.setTag(R.id.tag_1,item);
        ivDelete.setTag(R.id.tag_2,helper.getAdapterPosition());
        ivDelete.setTag(R.id.tag_3,ivPhoto);
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnImgsItemDeleteListener.onClick(v);
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
