package com.leo.auction.utils.shared_dailog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aten.compiler.widget.CustomeRecyclerView;
import com.aten.compiler.widget.customerDialog.BottomDialog_Anim;
import com.leo.auction.R;


import java.util.ArrayList;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.widget
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/11/3 0003
 * 描    述：分享弹框
 * ================================================
 */
public class ServiceDailogUtils {
    private BottomDialog_Anim customerBottomDialog;
    private ImageView ivServicePic;

    //显示分享弹框 type:0则是普通页面分享 不带朋友圈分享  1：是商品详情或者抽奖详情 带朋友圈分享
    public void showServiceDialog(Context context,final ISharedDialog iSharedDialog) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_service_dailog, null);
        ivServicePic = view.findViewById(R.id.iv_service_pic);
        CustomeRecyclerView crlBtn = view.findViewById(R.id.crl_btn);
        TextView tvCancle = view.findViewById(R.id.tv_cancle);

        //设置recycleview的一些配置
        crlBtn.setHasFixedSize(true);
        crlBtn.setLayoutManager(new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false));
        //初始化按钮的数据
        ArrayList<CrlBtnModel> crlBtnModels = new ArrayList<>();
        CrlBtnModel crlBtnModel01 = new CrlBtnModel(1, R.drawable.ic_shared_linked, "复制公众号");
        CrlBtnModel crlBtnModel02 = new CrlBtnModel(2, R.drawable.ic_shared_save, "保存图片");
        crlBtnModels.add(crlBtnModel01);
        crlBtnModels.add(crlBtnModel02);
        //设置recycleview的适配器
        CrlBtnAdapter crlBtnAdapter = new CrlBtnAdapter(crlBtnModels);
        crlBtn.setAdapter(crlBtnAdapter);

        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dissServiceDialog();
                if (iSharedDialog != null) {
                    iSharedDialog.dissmiss();
                }
            }
        });

        crlBtnAdapter.setOnItemListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iSharedDialog != null) {
                    int pos = (int) v.getTag();
                    switch (pos) {
                        case 1:
                            iSharedDialog.onCopyLink();
                            break;
                        case 2:
                            iSharedDialog.onDowload(ivServicePic);
                            break;
                    }
                }
            }
        });

        customerBottomDialog = new BottomDialog_Anim(context, view, crlBtn);
        customerBottomDialog.dimEnabled(false);
        customerBottomDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (iSharedDialog != null) {
                    iSharedDialog.dissmiss();
                }
            }
        });
        customerBottomDialog.show();
    }

    public void dissServiceDialog() {
        if (customerBottomDialog != null && customerBottomDialog.isShowing()) {
            customerBottomDialog.dismiss();
            customerBottomDialog = null;
        }
    }

    public interface ISharedDialog {
        void dissmiss();

        void onCopyLink();

        void onDowload(ImageView ivServicePic);
    }
}
