package com.leo.auction.ui.main.home.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.aten.compiler.widget.dialog.base.BottomBaseDialog;
import com.leo.auction.R;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.CommonlyUsedData;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.ui.order.model.OrderPayTypeModel;
import com.leo.auction.utils.SpannableStringUtils;
import com.ruffian.library.widget.RLinearLayout;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.home.dialog
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/9
 * 描    述： 保证金
 * 修    改：
 * ===============================================
 */
public class EarnestDialog extends BottomBaseDialog {

    TextView mDialogKnow;

    RLinearLayout mDialogRule;

    TextView mDialogTvRule;

    TextView mDialogPay;

    TextView mDialogClose;

    private Context mContext;

    HashMap<String, String> mHashMap = new HashMap<String, String>();


    private String payPrice;

    private   InterEarnestPay mInterEarnestPay;

    public EarnestDialog(Context context, HashMap<String, String> hashMap,InterEarnestPay interEarnestPay) {
        super(context);
        mContext = context;
        mHashMap = hashMap;
        mInterEarnestPay = interEarnestPay;
    }

    @Override
    public View onCreateView() {
        View inflate = View.inflate(mContext, R.layout.dialog_earnest, null);
        mDialogKnow = inflate.findViewById(R.id.dialog_know);
        mDialogRule = inflate.findViewById(R.id.dialog_rule);
        mDialogTvRule = inflate.findViewById(R.id.dialog_tv_rule);
        mDialogPay = inflate.findViewById(R.id.dialog_pay);
        mDialogClose = inflate.findViewById(R.id.dialog_close);


        mDialogTvRule.setText(SpannableStringUtils.getBuilder("商家已设置出价保护，本次出价需支付保证金。如违约，保证金将扣除并赔偿给商家。")
                .append("保证金规则❓").setForegroundColor(mContext.getResources().getColor(R.color.home_blue)).create());

        mDialogTvRule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialogRule.setVisibility(View.VISIBLE);
            }
        });


        mDialogKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialogRule.setVisibility(View.GONE);
            }
        });

        mDialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });



        mDialogPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInterEarnestPay.earnestPay();
            }
        });

        return inflate;
    }

    @Override
    public void initView() {

    }


    public interface InterEarnestPay{
        void earnestPay();
    }
}
