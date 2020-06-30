package com.leo.auction.ui.main.mine.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.aten.compiler.widget.glide.GlideUtils;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.ui.main.mine.model.ReleaseImageModel;


import java.util.ArrayList;

/**
 * project:yfqc_android
 * package:com.aten.yuncar.ui.community.adapter
 * Created by 彭俊鸿 on 2018/5/3.
 * e-mail : 1031028399@qq.com
 */

public class ReleasePostOssImglistAdapter extends BaseItemDraggableAdapter<ReleaseImageModel, BaseViewHolder> {
    private View.OnClickListener mOnLastImgListener;
    private View.OnClickListener mOnImgsItemListener;
    private View.OnClickListener mOnImgsItemDeleteListener;
    private ArrayList<ImageView> imgViews=new ArrayList<>();

    public void setmOnLastImgListener(View.OnClickListener mOnLastImgListener) {
        this.mOnLastImgListener = mOnLastImgListener;
    }

    public void setmOnImgsItemListener(View.OnClickListener mOnImgsItemListener) {
        this.mOnImgsItemListener = mOnImgsItemListener;
    }

    public void setmOnImgsItemDeleteListener(View.OnClickListener mOnImgsItemDeleteListener) {
        this.mOnImgsItemDeleteListener = mOnImgsItemDeleteListener;
    }

    public ReleasePostOssImglistAdapter() {
        super(R.layout.layout_postimglist_item, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ReleaseImageModel item) {
        RelativeLayout rlItem= (RelativeLayout) helper.getView(R.id.rl_item);
        ImageView ivPhoto = (ImageView) helper.getView(R.id.iv_photo);
        ImageView ivDelete = (ImageView) helper.getView(R.id.iv_delete);

        imgViews.add(ivPhoto);

        if ("1".equals(item.getTag())){
            GlideUtils.loadImg(R.drawable.ic_general_append_bg,ivPhoto);
            ivDelete.setVisibility(View.GONE);
        }else {
            GlideUtils.loadImg(item.getFile()==null?item.getImgPth():item.getFile(),ivPhoto);
            ivDelete.setVisibility(View.VISIBLE);
        }

        if (helper.getAdapterPosition()==9){
            setVisibility(false,rlItem);
        }else {
            setVisibility(true,rlItem);
        }

        ivPhoto.setTag(R.id.tag_1,helper.getAdapterPosition());
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

    //防止隐藏item出现空白
    public void setVisibility(boolean isVisible, View itemView) {
        RecyclerView.LayoutParams param = (RecyclerView.LayoutParams) itemView.getLayoutParams();
        if (isVisible) {
            param.width = RelativeLayout.LayoutParams.WRAP_CONTENT;// 这里注意使用自己布局的根布局类型
            param.height = (int) mContext.getResources().getDimension(R.dimen.dp_187);// 这里注意使用自己布局的根布局类型
            itemView.setVisibility(View.VISIBLE);
        } else {
            param.width = 0;
            param.height = 0;
            itemView.setVisibility(View.GONE);
        }
        itemView.setLayoutParams(param);
    }

    public ArrayList<ImageView> getImgViews() {
        return imgViews;
    }

    public void clearImgViews(){
        imgViews.clear();
    }
}
