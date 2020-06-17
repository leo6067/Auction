package com.aten.compiler.widget.customerDialog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;

import com.aten.compiler.widget.dialog.base.BottomBaseDialog;

/**
 * project:PJHAndroidFrame
 * package:com.frame.pjhandroidframe.widget
 * Created by 彭俊鸿 on 2018/6/21.
 * e-mail : 1031028399@qq.com
 * 底部弹框 自定义view的封装
 */

public class BottomDialog_Anim extends BottomBaseDialog<BottomDialog_Anim> {
    private final View view;
    private final RecyclerView recyclerView;
    private LayoutAnimationController mLac;

    public BottomDialog_Anim(Context context, View view, RecyclerView recyclerView) {
        super(context);
        this.view=view;
        this.recyclerView=recyclerView;

        init();
    }

    private void init() {
        /** LayoutAnimation */
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
                0f, Animation.RELATIVE_TO_SELF, 6f, Animation.RELATIVE_TO_SELF, 0);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.setDuration(350);
        animation.setStartOffset(150);

        mLac = new LayoutAnimationController(animation, 0.12f);
        mLac.setInterpolator(new DecelerateInterpolator());

        recyclerView.setLayoutAnimation(mLac);
    }


    @Override
    public View onCreateView() {
        return view;
    }

    @Override
    public void setUiBeforShow() {}
}
