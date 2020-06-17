package com.aten.compiler.widget.title.style;

import com.aten.compiler.R;
import com.aten.compiler.widget.title.ITitleBarStyle;

/**
 * ================================================
 * 项目名称：PJHAndroidFrame
 * 包    名：com.frame.pjh_core.utils
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2018/11/05
 * 描    述：绿色主题样式实现（布局属性：app:bar_style="green"）
 * ================================================
 */
public class TitleBarGreenStyle extends TitleBarLightStyle {
    @Override
    public int getBackgroundColor() {
        return 0xFF13B05C;
    }

    @Override
    public int getBackIconResource() {
        return R.drawable.ic_general_return_bg;
    }

    @Override
    public float getLeftViewSize() {
        return 32f;
    }

    @Override
    public int getLeftViewColor() {
        return 0xFFFFFFFF;
    }

    @Override
    public int getTitleViewColor() {
        return 0xFFFFFFFF;
    }

    @Override
    public float getRightViewSize() {
        return 32f;
    }

    @Override
    public int getRightViewColor() {
        return 0xFFFFFFFF;
    }

    @Override
    public boolean isLineVisible() {
        return false;
    }

    @Override
    public int getLineColor() {
        return 0xFFEEEEE;
    }

    @Override
    public int getLeftViewBackground() {
        return R.drawable.bar_selector_selectable_white;
    }

    @Override
    public int getRightViewBackground() {
        return R.drawable.bar_selector_selectable_white;
    }
}