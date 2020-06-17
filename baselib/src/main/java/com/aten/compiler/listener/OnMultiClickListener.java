package com.aten.compiler.listener;

import android.view.View;

/**
 * project:PJHAndroidFrame
 * package:com.frame.pjh_core.listener
 * Created by 彭俊鸿 on 2018/6/21.
 * e-mail : 1031028399@qq.com
 * 防止多次点击事件
 */

public abstract class OnMultiClickListener implements View.OnClickListener{
    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime;

    @Override
    public void onClick(View v) {
        long curClickTime = System.currentTimeMillis();
        if((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            // 超过点击间隔后再将lastClickTime重置为当前点击时间
            lastClickTime = curClickTime;
            onNoDoubleClick(v);
        }
    }

    public abstract void onNoDoubleClick(View v);
}
