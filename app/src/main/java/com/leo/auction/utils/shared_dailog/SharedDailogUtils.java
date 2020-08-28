package com.leo.auction.utils.shared_dailog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.aten.compiler.utils.DecimalFormatUtils;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.widget.CircleImageView;
import com.aten.compiler.widget.CustomeRecyclerView;
import com.aten.compiler.widget.autoTextView.AutofitTextView;
import com.aten.compiler.widget.customerDialog.BottomDialog_Anim;
import com.aten.compiler.widget.glide.GlideUtils;
import com.leo.auction.R;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.net.ResultModel;
import com.leo.auction.ui.main.home.model.QcodeModel;
import com.leo.auction.utils.shared.SharedMessageUtils;


import java.util.ArrayList;

import okhttp3.Call;

/**
 * ===============================================
 * ================================================
 */
public class SharedDailogUtils {
    private BottomDialog_Anim customerBottomDialog;


    //显示分享弹框 type:0则是普通页面分享 不带朋友圈分享  1：是商品详情 带朋友圈分享  2抽奖详情
    public void showSharedDialog(Context context, SharedModel sharedModel, ShareDialogInter iSharedDialog) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_shared_dailog, null);

        CustomeRecyclerView crlBtn = view.findViewById(R.id.crl_btn);
        TextView tvCancle = view.findViewById(R.id.tv_cancle);


        //设置recycleview的一些配置
        crlBtn.setHasFixedSize(true);

        //初始化按钮的数据
        ArrayList<CrlBtnModel> crlBtnModels = new ArrayList<>();
        CrlBtnModel crlBtnModel01 = new CrlBtnModel(1, R.drawable.ic_shared_linked, "复制链接");
        CrlBtnModel crlBtnModel02 = new CrlBtnModel(2, R.drawable.ic_shared_weixin, "微信");
        CrlBtnModel crlBtnModel03 = new CrlBtnModel(3, R.drawable.ic_wx_circle_b, "朋友圈图文");
        CrlBtnModel crlBtnModel04 = new CrlBtnModel(4, R.drawable.ic_wx_circle_a, "朋友圈");
        CrlBtnModel crlBtnModel05 = new CrlBtnModel(5, R.drawable.ic_shared_save, "保存图片");
//        CrlBtnModel crlBtnModel06 = new CrlBtnModel(6, R.drawable.ic_xianyu_icon, "闲鱼");
//        CrlBtnModel crlBtnModel07 = new CrlBtnModel(7, R.drawable.ic_wwdz_icon, "玩物得志");
        CrlBtnModel crlBtnModel08 = new CrlBtnModel(8, R.drawable.share_qq, "QQ");
        CrlBtnModel crlBtnModel09 = new CrlBtnModel(9, R.drawable.ic_share_code_save, "二维码");


        if (sharedModel.getChannelType().equals("2")) {//百亿分享  //// 店铺分享
            crlBtn.setLayoutManager(new GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false));
            crlBtnModels.add(crlBtnModel01);
            crlBtnModels.add(crlBtnModel02);
            crlBtnModels.add(crlBtnModel04);
            crlBtnModels.add(crlBtnModel08);

        } else if (sharedModel.getChannelType().equals("0")) {  // 商品详情
            crlBtn.setLayoutManager(new GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false));
            crlBtnModels.add(crlBtnModel01);
            crlBtnModels.add(crlBtnModel02);
            crlBtnModels.add(crlBtnModel03);
            crlBtnModels.add(crlBtnModel04);
            crlBtnModels.add(crlBtnModel08);
            crlBtnModels.add(crlBtnModel09);

        }  else if (sharedModel.getChannelType().equals("1")) {
            crlBtn.setLayoutManager(new GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false));
            crlBtnModels.add(crlBtnModel01);
            crlBtnModels.add(crlBtnModel02);
            crlBtnModels.add(crlBtnModel03);
            crlBtnModels.add(crlBtnModel04);
//            crlBtnModels.add(crlBtnModel05);
            crlBtnModels.add(crlBtnModel08);

        } else {
            crlBtn.setLayoutManager(new GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false));
            crlBtnModels.add(crlBtnModel01);
            crlBtnModels.add(crlBtnModel02);
            crlBtnModels.add(crlBtnModel03);
            crlBtnModels.add(crlBtnModel04);
            crlBtnModels.add(crlBtnModel08);

        }


        //设置recycleview的适配器
        CrlBtnAdapter crlBtnAdapter = new CrlBtnAdapter(crlBtnModels);
        crlBtn.setAdapter(crlBtnAdapter);

        customerBottomDialog = new BottomDialog_Anim(context, view, crlBtn);

        customerBottomDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (iSharedDialog != null) {
                    iSharedDialog.dissmiss();
                }
            }
        });
        customerBottomDialog.show();


        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dissSharedDialog();
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
                            iSharedDialog.dissmiss();
//                            dissSharedDialog();
                            break;
                        case 2:
                            iSharedDialog.onSharedWX();
//                            iSharedDialog.dissmiss();
//                            dissSharedDialog();
                            break;
                        case 3:

                            iSharedDialog.onSharedWXCircleNine();
//                            iSharedDialog.dissmiss();
//                            dissSharedDialog();
                            break;
                        case 4:
                            iSharedDialog.onSharedWXCircle();
//                            iSharedDialog.dissmiss();
                            break;
                        case 5:
//                            iSharedDialog.onDowload(llContain);
                            iSharedDialog.dissmiss();
//                            dissSharedDialog();
                            break;
                        case 6:
                            iSharedDialog.onXYShared();
//                            iSharedDialog.dissmiss();
//                            dissSharedDialog();
                            break;
                        case 7:
                            iSharedDialog.onWWDZShared();
//                            iSharedDialog.dissmiss();
//                            dissSharedDialog();
                            break;
                        case 8:
                            iSharedDialog.onQQShared();
                            iSharedDialog.dissmiss();
//                            dissSharedDialog();
                            break;
                        case 9:

                            iSharedDialog.onShowShareDialog();
                            break;
                    }
                }
            }
        });


    }






    public void dissSharedDialog() {
        if (customerBottomDialog != null && customerBottomDialog.isShowing()) {
            customerBottomDialog.dismiss();
            customerBottomDialog = null;
        }
    }


}
