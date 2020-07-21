package com.leo.auction.ui.main.home.dialog;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.RxTool;
import com.aten.compiler.widget.CustomSafeKeyboard;
import com.aten.compiler.widget.CustomeRecyclerView;
import com.aten.compiler.widget.MNPasswordEditText;
import com.aten.compiler.widget.customerDialog.BottomDialog;
import com.leo.auction.R;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.ui.main.mine.activity.SetPayPwdActivity;
import com.leo.auction.ui.order.model.OrderPayTypeModel;
import com.leo.auction.utils.SetPaypwdUtils;
import com.leo.auction.widget.customDialog.GoSettingPaypwdDialog;

import org.litepal.LitePal;

import java.util.ArrayList;

/**
 * project:PJHAndroidFrame
 * package:${PACKACE_NAME}
 * Created by 彭俊鸿 on 2018/8/24.
 * e-mail : 1031028399@qq.com
 */
public class PayPwdBoardUtils {
    MNPasswordEditText pwdInputview;
    CustomSafeKeyboard viewKeyboard;

    private BottomDialog payTypeBottomDialog;
    private BottomDialog bottomDialog;
    private SetPaypwdUtils setPaypwdUtils;
    private GoSettingPaypwdDialog goSettingPaypwdDialog;
    //-------------------------------------------弹出支付方式选择框  start-------------------------------------------------

    //显示支付方式的dialog
    public void showPayTypeDialog(Context context, String payMoney, ArrayList<OrderPayTypeModel> orderPayTypeModels, final IPayType iPayType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View bottomChooseLayout = inflater.inflate(R.layout.layout_pay_input_type, null);
        TextView tvMoney = (TextView) bottomChooseLayout.findViewById(R.id.tv_money);
        CustomeRecyclerView crlPayTytpe = (CustomeRecyclerView) bottomChooseLayout.findViewById(R.id.crl_pay_tytpe);
        TextView tvSure = (TextView) bottomChooseLayout.findViewById(R.id.tv_sure);
        //设置金额
        tvMoney.setText(EmptyUtils.strEmpty(payMoney));
        //设置支付列表
        crlPayTytpe.setHasFixedSize(true);
        crlPayTytpe.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        final PayTypeAdapter payTypeAdapter = new PayTypeAdapter(iPayType);
        crlPayTytpe.setAdapter(payTypeAdapter);
        payTypeAdapter.setNewData(orderPayTypeModels);

        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!RxTool.isFastClick(RxTool.MIN_CLICK_DELAY_TIME_500)) {
                    return;
                }


                iPayType.choosePayType(payTypeAdapter.getChoosePos());
            }
        });

        payTypeBottomDialog = new BottomDialog(context, bottomChooseLayout);
        payTypeBottomDialog.show();
    }

    //关闭支付方式的dialog
    public void dismissPayTypeDialog() {
        if (payTypeBottomDialog != null) {
            payTypeBottomDialog.dismiss();
            payTypeBottomDialog = null;
        }
    }

    public interface IPayType {
        void choosePayType(int pos);
    }
    //-------------------------------------------弹出支付方式选择框  end-------------------------------------------------


    //-------------------------------------------弹出密码支付框  start-------------------------------------------------

    //显示支付密码的dialog
    public void showPayPasswordDialog(Context context, String money, SetPaypwdUtils.IComplete iComplete) {
        //若选择余额支付，那么需要先判断是否设置支付密码

        boolean payPwd = BaseSharePerence.getInstance().getUserJson().isPayPwd();

        if (!payPwd) {
            goSettingPaypwdDialog = new GoSettingPaypwdDialog(context, new GoSettingPaypwdDialog.IGoSetting() {
                @Override
                public void goSetting() {
                    goSettingPaypwdDialog.dismiss();
                    SetPayPwdActivity.newIntance(context, "");
                }
            });
            goSettingPaypwdDialog.show();
            dismissPayPasswordDialog();
            return;
        }

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View bottomChooseLayout = inflater.inflate(R.layout.layout_pay_input_password, null);
        FrameLayout flClose = (FrameLayout) bottomChooseLayout.findViewById(R.id.fl_close);
        TextView tvMoney = (TextView) bottomChooseLayout.findViewById(R.id.tv_money);
        pwdInputview = (MNPasswordEditText) bottomChooseLayout.findViewById(R.id.pwd_inputview);
        viewKeyboard = (CustomSafeKeyboard) bottomChooseLayout.findViewById(R.id.view_keyboard);

        tvMoney.setText(EmptyUtils.strEmpty(money));

        setPaypwdUtils = new SetPaypwdUtils(pwdInputview, viewKeyboard, iComplete);

        flClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismissPayPasswordDialog();
                setPaypwdUtils.cleanPwd();
            }
        });

        bottomDialog = new BottomDialog(context, bottomChooseLayout);
        bottomDialog.show();
    }

    public SetPaypwdUtils getSetPaypwdUtils() {
        return setPaypwdUtils;
    }

    //关闭支付密码的dialog
    public void dismissPayPasswordDialog() {
        if (bottomDialog != null && bottomDialog.isShowing()) {
            bottomDialog.dismiss();
            bottomDialog = null;
        }
    }

    //关闭设置支付密码的dialog
    public void dismissGoSettingPaypwdDialog() {
        if (goSettingPaypwdDialog != null) {
            goSettingPaypwdDialog.dismiss();
            goSettingPaypwdDialog = null;
        }
    }

    //-------------------------------------------弹出密码支付框  end-------------------------------------------------
}
