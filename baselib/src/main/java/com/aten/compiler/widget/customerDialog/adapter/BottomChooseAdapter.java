package com.aten.compiler.widget.customerDialog.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.aten.compiler.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * project:YunPiao-master
 * package:com.aiten.yunticketing.ui.TrainTickets.adapter
 * Created by 彭俊鸿 on 2018/1/4.
 * e-mail : 1031028399@qq.com
 */

public class BottomChooseAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    private final int choosePosition;//选中的position
    private View.OnClickListener mOnBottomChooseItemListener;

    public View.OnClickListener getmOnBottomChooseItemListener() {
        return mOnBottomChooseItemListener;
    }

    public void setmOnBottomChooseItemListener(View.OnClickListener mOnBottomChooseItemListener) {
        this.mOnBottomChooseItemListener = mOnBottomChooseItemListener;
    }

    public BottomChooseAdapter(@Nullable List<String> data, int choosePosition) {
        super(R.layout.layout_bottom_choose_item, data);
        this.choosePosition=choosePosition;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView tvChoose=(TextView)helper.getView(R.id.tv_choose);
        tvChoose.setText(item);
        if (helper.getAdapterPosition()==choosePosition){
            tvChoose.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        }else {
            tvChoose.setTextColor(mContext.getResources().getColor(R.color.txt_color_1e1e1e));
        }

        tvChoose.setTag(R.id.tag_1,item);
        tvChoose.setTag(R.id.tag_2,helper.getAdapterPosition());
        tvChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getmOnBottomChooseItemListener().onClick(view);
            }
        });
    }
}
