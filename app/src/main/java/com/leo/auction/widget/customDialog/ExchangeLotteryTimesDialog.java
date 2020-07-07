package com.leo.auction.widget.customDialog;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.dialog.animation.ZoomEnter.ZoomInEnter;
import com.aten.compiler.widget.dialog.base.BaseDialog;
import com.leo.auction.R;
import com.leo.auction.utils.TextLightUtils;

import java.math.BigDecimal;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.widget
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/10/25
 * 描    述：兑换抽奖次数弹框
 * ================================================
 */

public class ExchangeLotteryTimesDialog extends BaseDialog<ExchangeLotteryTimesDialog> {
    private IExchangLottery iExchangLottery;

    private int balanceCoin, hasLotteryNum;
    private FrameLayout flClose;
    private TextView tvSuperCoin, tvRafflesRemainingNum;
    private EditText etExchangeLotteryTimes;
    private SuperButton sbtnAll, sbtnSureExchang;

    public ExchangeLotteryTimesDialog(Context context, int balanceCoin, int hasLotteryNum, IExchangLottery iExchangLottery) {
        super(context);
        this.balanceCoin = balanceCoin;
        this.hasLotteryNum = hasLotteryNum;
        this.iExchangLottery = iExchangLottery;
    }

    @Override
    public View onCreateView() {
        showAnim(new ZoomInEnter());
        dismissAnim(null);
        setCanceledOnTouchOutside(false);
        View view = View.inflate(mContext, R.layout.layout_exchange_lottery_times_dialog, null);
        tvSuperCoin = view.findViewById(R.id.tv_super_coin);
        tvRafflesRemainingNum = view.findViewById(R.id.tv_raffles_remaining_num);
        etExchangeLotteryTimes = view.findViewById(R.id.et_exchange_lottery_times);
        sbtnAll = view.findViewById(R.id.sbtn_all);
        sbtnSureExchang = view.findViewById(R.id.sbtn_sure_exchang);
        flClose = view.findViewById(R.id.fl_close);
        return view;
    }

    @Override
    public void initView() {
        tvSuperCoin.setText(EmptyUtils.strEmpty("" + balanceCoin));
        tvRafflesRemainingNum.setText(EmptyUtils.strEmpty("" + hasLotteryNum));
        //设置兑换次数的监听
        TextLightUtils textLightUtils=new TextLightUtils();
        textLightUtils.onTextChangedListener(etExchangeLotteryTimes, "999");
        sbtnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1.算出金币一共最大可以兑换多少次抽奖次数 若最大次数为0 则提示超级币不足
                //2.对比剩余抽奖次数 若最大次数大于剩余次数 则按剩余次数 反之 予然
                int canAllExchangNum = new BigDecimal(balanceCoin).divide(new BigDecimal(100)).intValue();
                if (canAllExchangNum == 0) {
                    ToastUtils.showShort("超级币不足");
                    return;
                }
                etExchangeLotteryTimes.setText("" + canAllExchangNum);
            }
        });

        sbtnSureExchang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int canAllExchangNum = new BigDecimal(balanceCoin).divide(new BigDecimal(100)).intValue();
                if (canAllExchangNum == 0) {
                    ToastUtils.showShort("超级币不足");
                    return;
                }
                dismiss();
                iExchangLottery.sureExchang(etExchangeLotteryTimes.getText().toString().trim());
            }
        });

        flClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }


    public interface IExchangLottery {
        void sureExchang(String exchangeLotteryTimes);
    }
}
