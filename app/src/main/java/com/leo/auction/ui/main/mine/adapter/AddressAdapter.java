package com.leo.auction.ui.main.mine.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.allen.library.shape.ShapeTextView;
import com.aten.compiler.widget.swipeMenuLayout.SwipeMenuLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.ui.main.mine.model.AddressModel;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.ui.mine.adapter
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/10/28 0028
 * 描    述：
 * ================================================
 */
public class AddressAdapter extends BaseQuickAdapter<AddressModel.AddressBean, BaseViewHolder> {
    private final IAddressOption iAddressOption;
    private final String itemClickBackType;

    public AddressAdapter(IAddressOption iAddressOption, String itemClickBackType) {
        super(R.layout.layout_address_item, null);
        this.iAddressOption=iAddressOption;
        this.itemClickBackType=itemClickBackType;
    }

    @Override
    protected void convert(@NonNull final BaseViewHolder helper, final AddressModel.AddressBean item) {
        SwipeMenuLayout smlLayout=helper.getView(R.id.sml_layout);
        RelativeLayout rlItem=helper.getView(R.id.rl_item);
        ShapeTextView stvDefault=helper.getView(R.id.stv_default);
        Button btnDelete=helper.getView(R.id.btn_delete);

        //0：默认直接修改地址 1：直接把item的数据带返回
        if ("0".equals(itemClickBackType)) {
            smlLayout.setSwipeEnable(true);
        } else {
            smlLayout.setSwipeEnable(false);
        }

        helper.setText(R.id.tv_person_info,item.getLinkman()+"  "+item.getPhone());
        helper.setText(R.id.tv_address,item.getAddr1Name()+" "+item.getAddr2Name()+" "+item.getAddr2Name()+" "+item.getAddress());

        if ("00B".equals(item.getStatus())){
            stvDefault.setVisibility(View.VISIBLE);
        }else {
            stvDefault.setVisibility(View.GONE);
        }

        rlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iAddressOption.onItemListener(item);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iAddressOption.onDeleteListener(item.getId());
            }
        });
    }

    public interface IAddressOption{
        void onItemListener(AddressModel.AddressBean item);
        void onDeleteListener(String id);
    }
}
