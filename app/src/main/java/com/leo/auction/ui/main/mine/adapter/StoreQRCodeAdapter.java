package com.leo.auction.ui.main.mine.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.glide.GlideUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.ui.main.mine.model.StoreQRCodeModel;

public class StoreQRCodeAdapter extends BaseQuickAdapter<StoreQRCodeModel, BaseViewHolder> {
    private View.OnClickListener onItemListener;

    public void setOnItemListener(View.OnClickListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    public StoreQRCodeAdapter() {
        super(R.layout.layout_store_qrcode_item, null);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, StoreQRCodeModel item) {
        ImageView ivQrCodeBg=helper.getView(R.id.iv_qr_code_bg);
        TextView tvQrCodeType=helper.getView(R.id.tv_qr_code_type);
        SuperButton sbtnGenerate=helper.getView(R.id.sbtn_generate);

        GlideUtils.loadImg(item.getFourImgs(),ivQrCodeBg);
        tvQrCodeType.setText(EmptyUtils.strEmpty(item.getQrCodeType()));

        sbtnGenerate.setTag(R.id.tag_1,helper.getAdapterPosition());
        sbtnGenerate.setTag(R.id.tag_2,item);
        sbtnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onItemListener.onClick(v);
            }
        });
    }
}
