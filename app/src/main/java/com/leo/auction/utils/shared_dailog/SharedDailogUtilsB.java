package com.leo.auction.utils.shared_dailog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.widget.CircleImageView;
import com.aten.compiler.widget.CustomeRecyclerView;
import com.aten.compiler.widget.autoTextView.AutofitTextView;
import com.aten.compiler.widget.customerDialog.BottomDialog_Anim;
import com.aten.compiler.widget.glide.GlideUtils;
import com.leo.auction.R;

import java.util.ArrayList;

/**
 * ===============================================
 * ================================================
 */
public class SharedDailogUtilsB {
    private BottomDialog_Anim customerBottomDialog;
    private ImageView ivQrCode;
    private LinearLayout llContain;

    //显示分享弹框 type:0则是普通页面分享 不带朋友圈分享  1：是商品详情 带朋友圈分享  2抽奖详情
    public void showSharedDialogB(Context context, SharedModel sharedModel,final ShareDialogInter iSharedDialog) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_shared_dailog_b, null);
        llContain = view.findViewById(R.id.ll_contain);
        ImageView ivStaredPic = view.findViewById(R.id.iv_stared_pic);
        TextView tvProductTitle = view.findViewById(R.id.tv_product_title);
        ivQrCode = view.findViewById(R.id.iv_qr_code);
        CircleImageView civHead = view.findViewById(R.id.civ_head);
        AutofitTextView tvShopName = view.findViewById(R.id.tv_shop_name);
        CustomeRecyclerView crlBtn = view.findViewById(R.id.crl_btn);
        TextView tvCancle = view.findViewById(R.id.tv_cancle);
        TextView tvMoney= view.findViewById(R.id.tv_money);
        TextView tvTag= view.findViewById(R.id.tv_tag);
        LinearLayout moneyLin= view.findViewById(R.id.ll_money);
        tvMoney.setText(sharedModel.getPrice());
        GlideUtils.loadImg(sharedModel.getPicPath(), ivStaredPic);
        tvProductTitle.setText(EmptyUtils.strEmpty(sharedModel.getGoodName()));
        GlideUtils.loadImg(sharedModel.getShopHeadImg(), civHead);
        tvShopName.setText(EmptyUtils.strEmpty(sharedModel.getShopName()));

//        tvMoney.setText(DecimalFormatUtils.getInstance().keepPlaces("#0.00",Double.valueOf(sharedModel.getPrice())));
        //设置recycleview的一些配置
        crlBtn.setHasFixedSize(true);

        //初始化按钮的数据
        ArrayList<CrlBtnModel> crlBtnModels = new ArrayList<>();

        CrlBtnModel crlBtnModel01 = new CrlBtnModel(5, R.drawable.ic_shared_save, "下载");

        CrlBtnModel crlBtnModel02 = new CrlBtnModel(2, R.drawable.ic_shared_weixin, "微信好友");
        CrlBtnModel crlBtnModel03= new CrlBtnModel(3, R.drawable.ic_wx_circle_a, "朋友圈");

        CrlBtnModel crlBtnModel04 = new CrlBtnModel(8, R.drawable.share_qq, "QQ");

        crlBtnModels.add(crlBtnModel01);
        crlBtnModels.add(crlBtnModel02);
        crlBtnModels.add(crlBtnModel03);
        crlBtnModels.add(crlBtnModel04);




        crlBtn.setLayoutManager(new GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false));
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
                            iSharedDialog.onSharedWXCode(llContain);
                            iSharedDialog.dissmiss();
//                            dissSharedDialog();
                            break;
                        case 3:
                            iSharedDialog.onSharedWXCircleCode(llContain);
                            iSharedDialog.dissmiss();
//                            dissSharedDialog();
                            break;
                        case 4:
                            iSharedDialog.onSharedWXCircle();
                            iSharedDialog.dissmiss();
                            break;
                        case 5:
                            iSharedDialog.onDowload(llContain);
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
                            iSharedDialog.onSharedQQCode(llContain);
                            iSharedDialog.dissmiss();
//                            dissSharedDialog();
                            break;
                    }
                }
            }
        });


    }

    //设置二维码图片
    public void setQrCode(String qrCodePath) {
        GlideUtils.loadImg(qrCodePath, ivQrCode);
    }

    public void dissSharedDialog() {
        if (customerBottomDialog != null && customerBottomDialog.isShowing()) {
            customerBottomDialog.dismiss();
            customerBottomDialog = null;
        }
    }


}
