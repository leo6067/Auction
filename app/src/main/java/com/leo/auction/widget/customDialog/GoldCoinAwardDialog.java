package com.leo.auction.widget.customDialog;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.aten.compiler.widget.AwardRotateAnimation;
import com.aten.compiler.widget.dialog.animation.FadeEnter.FadeEnter;
import com.aten.compiler.widget.dialog.animation.SlideExit.SlideTopExit;
import com.aten.compiler.widget.dialog.base.BaseDialog;
import com.leo.auction.R;


/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.widget
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/10/28 0028
 * 描    述：金币奖励弹框
 * ================================================
 */
public class GoldCoinAwardDialog extends BaseDialog<GoldCoinAwardDialog> {

    private final String rewardNum;
    private TextView tvCoinNum;
    private ImageView ivGoldCoin;

    public GoldCoinAwardDialog(Context context, String rewardNum) {
        super(context);
        this.rewardNum=rewardNum;
    }

    @Override
    public View onCreateView() {
//        showAnim(new SlideBottomEnter());
        showAnim(new FadeEnter());
        dismissAnim(new SlideTopExit());
        dimEnabled(false);
        autoDismiss(true);
        autoDismissDelay(500);
        setCanceledOnTouchOutside(false);
        View view=View.inflate(mContext, R.layout.layout_gold_coin_award_dialog, null);
        ivGoldCoin=view.findViewById(R.id.iv_gold_coin);
        tvCoinNum=view.findViewById(R.id.tv_coin_num);
        return view;
    }

    @Override
    public void setUiBeforShow() {
        startAnim();
        tvCoinNum.setText("金币+"+rewardNum);
    }

    //开启动画
    public void startAnim(){
        AwardRotateAnimation animation = new AwardRotateAnimation();
        animation.setRepeatCount(Animation.INFINITE);
        ivGoldCoin.startAnimation(animation);
    }

    //结束动画
    public void stopAnim(){
        ivGoldCoin.clearAnimation();
    }
}
