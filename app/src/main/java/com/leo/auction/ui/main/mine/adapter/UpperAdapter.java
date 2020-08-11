package com.leo.auction.ui.main.mine.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.ui.main.mine.model.ReleaseEditModel;

import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.mine.adapter
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/7
 * 描    述： 上架属性展示
 * 修    改：
 * ===============================================
 */
public class UpperAdapter extends BaseQuickAdapter<ReleaseEditModel.DataBean.AttributesBean, BaseViewHolder> {
    public UpperAdapter() {
        super(R.layout.item_upper_attribute);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, ReleaseEditModel.DataBean.AttributesBean attributesBean) {
//        baseViewHolder.setText(R.id.tv_attri_name, );

        TextView attriTitle= baseViewHolder.getView(R.id.tv_attri_name);
        TextView attriText = baseViewHolder.getView(R.id.et_attri_value);
        if (attributesBean.getTitle().equals("描述内容")){
            attriTitle.setText(attributesBean.getValue());
        }else {
            attriTitle.setText("【" + attributesBean.getTitle() + "】");
            attriText.setText( attributesBean.getValue());
        }


    }
}
