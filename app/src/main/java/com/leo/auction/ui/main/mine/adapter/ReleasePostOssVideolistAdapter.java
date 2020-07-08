package com.leo.auction.ui.main.mine.adapter;

import android.view.View;
import android.widget.ImageView;

import com.aten.compiler.widget.glide.GlideUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huantansheng.easyphotos.utils.video.ReleaseVideoModel;
import com.leo.auction.R;


/**
 * project:yfqc_android
 * package:com.aten.yuncar.ui.community.adapter
 * Created by 彭俊鸿 on 2018/5/3.
 * e-mail : 1031028399@qq.com
 */

public class ReleasePostOssVideolistAdapter extends BaseQuickAdapter<ReleaseVideoModel, BaseViewHolder> {
    private View.OnClickListener mOnLastImgListener;
    private View.OnClickListener mOnImgsItemListener;
    private View.OnClickListener mOnImgsItemDeleteListener;

    public void setmOnLastImgListener(View.OnClickListener mOnLastImgListener) {
        this.mOnLastImgListener = mOnLastImgListener;
    }

    public void setmOnImgsItemListener(View.OnClickListener mOnImgsItemListener) {
        this.mOnImgsItemListener = mOnImgsItemListener;
    }

    public void setmOnImgsItemDeleteListener(View.OnClickListener mOnImgsItemDeleteListener) {
        this.mOnImgsItemDeleteListener = mOnImgsItemDeleteListener;
    }

    public ReleasePostOssVideolistAdapter() {
        super(R.layout.layout_postimglist_item, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ReleaseVideoModel item) {
        ImageView ivPhoto = (ImageView) helper.getView(R.id.iv_photo);
        ImageView ivDelete = (ImageView) helper.getView(R.id.iv_delete);



        if ("1".equals(item.getTag())){
            GlideUtils.loadImg(R.drawable.ic_add_video_icon,ivPhoto);
            ivDelete.setVisibility(View.GONE);
        }else {
            GlideUtils.loadImg(item.getImgPath()==null?item.getImgPath2():item.getImgPath(),ivPhoto);
            ivDelete.setVisibility(View.VISIBLE);
        }

        if (helper.getAdapterPosition()==1){
            ivPhoto.setVisibility(View.GONE);
        }else {
            ivPhoto.setVisibility(View.VISIBLE);
        }

        ivPhoto.setTag(R.id.tag_1,helper.getAdapterPosition());
        ivPhoto.setTag(R.id.tag_2,item);
        ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("1".equals(item.getTag())){
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
}
