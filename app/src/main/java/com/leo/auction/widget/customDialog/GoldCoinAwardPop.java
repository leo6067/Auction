package com.leo.auction.widget.customDialog;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.aten.compiler.widget.AwardRotateAnimation;
import com.leo.auction.R;

import razerdp.basepopup.BasePopupWindow;


/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.widget
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/10/28 0028
 * 描    述：金币奖励弹框(=背景可以触发事件)
 * ================================================
 */
public class GoldCoinAwardPop extends BasePopupWindow {
    private TextView tvCoinNum;
    private ImageView ivGoldCoin;
    private View view;
    private AwardRotateAnimation animation;

    public GoldCoinAwardPop(Context context) {
        super(context);
    }

    @Override
    public View onCreateContentView() {
        view=createPopupById(R.layout.layout_gold_coin_award_dialog);
        if (view!=null){
            ivGoldCoin = view.findViewById(R.id.iv_gold_coin);
            tvCoinNum = view.findViewById(R.id.tv_coin_num);

            initData(view);
        }
        return view;
    }

    private void initData(View view) {
        startAnim();

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
                stopAnim();
            }
        },1000);
    }

    @Override
    protected Animator onCreateShowAnimator() {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(500));
        return super.onCreateShowAnimator();
    }

    @Override
    protected Animator onCreateDismissAnimator() {
        AnimatorSet animatorSet = new AnimatorSet();
        DisplayMetrics dm = view.getContext().getResources().getDisplayMetrics();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(view, "translationY", 0, -250 * dm.density), //
                ObjectAnimator.ofFloat(view, "alpha", 1, 0));
        animatorSet.setDuration(500);
        return animatorSet;
    }

    public GoldCoinAwardPop setText(String rewardNum) {
        if (tvCoinNum!=null){
            tvCoinNum.setText("金币+" + rewardNum);
        }
        return this;
    }

    //开启动画
    public void startAnim(){
        animation = new AwardRotateAnimation();
        animation.setRepeatCount(Animation.INFINITE);
        ivGoldCoin.startAnimation(animation);
    }

    //结束动画
    public void stopAnim(){
        if (animation!=null){
            animation.cancel();
        }

        if (ivGoldCoin!=null){
            ivGoldCoin.clearAnimation();
        }
    }

    //系统回收
    public void onRecycle(){
        ivGoldCoin=null;
        view=null;
    }
}
