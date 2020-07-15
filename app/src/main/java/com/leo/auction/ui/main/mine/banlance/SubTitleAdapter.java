package com.leo.auction.ui.main.mine.banlance;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.aten.compiler.utils.EmptyUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.ui.main.mine.banlance.model.BalanceCategoryModel;


/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.ui.mine.adapter
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/11/13 0013
 * 描    述：
 * ================================================
 */
public class SubTitleAdapter extends BaseQuickAdapter<BalanceCategoryModel.DataBean.ChildrenBean, BaseViewHolder> {
    private final ISubTitleListener iSubTitleListener;
    private int currentPos=0;
    private TextView currentTvSubTitle;

    public SubTitleAdapter(ISubTitleListener iSubTitleListener) {
        super(R.layout.layout_subtitle_item, null);
        this.iSubTitleListener=iSubTitleListener;
    }

    @Override
    protected void convert(@NonNull final BaseViewHolder helper, final BalanceCategoryModel.DataBean.ChildrenBean item) {
        final TextView tvSubtitle=helper.getView(R.id.tv_subtitle);
        tvSubtitle.setText(EmptyUtils.strEmpty(item.getName()));

        if (item.isSelect()){
            tvSubtitle.setTextColor(mContext.getResources().getColor(R.color.color_fe7f67));
            currentTvSubTitle=tvSubtitle;
        }else {
            tvSubtitle.setTextColor(mContext.getResources().getColor(R.color.color_1a1a1a));
        }

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPos!=helper.getAdapterPosition()){
                    if (currentTvSubTitle!=null){
                        currentTvSubTitle.setTextColor(mContext.getResources().getColor(R.color.color_1a1a1a));
                    }

                    tvSubtitle.setTextColor(mContext.getResources().getColor(R.color.color_fe7f67));
                    getItem(currentPos).setSelect(false);
                    item.setSelect(true);

                    currentPos=helper.getAdapterPosition();
                    currentTvSubTitle=tvSubtitle;
                    iSubTitleListener.onSubTitleItemListener(item.getId());
                }
            }
        });
    }

    public interface ISubTitleListener{
        void onSubTitleItemListener(String changeType);
    }
}
