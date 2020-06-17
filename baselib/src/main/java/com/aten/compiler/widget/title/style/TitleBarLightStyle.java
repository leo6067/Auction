package com.aten.compiler.widget.title.style;

import com.aten.compiler.R;
import com.aten.compiler.widget.title.ITitleBarStyle;

/**
 *    author : HJQ
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2018/08/20
 *    desc   : 默认日间主题样式实现（布局属性：app:bar_style="light"）
 */
public class TitleBarLightStyle implements ITitleBarStyle {

    @Override
    public int getTitleBarHeight() {
        return 0;
    }

    @Override
    public int getBackgroundColor() {
        return 0xFFFFFFFF;
    }

    @Override
    public int getBackIconResource() {
        return R.mipmap.bar_icon_back_black;
    }

    @Override
    public int getLeftViewColor() {
        return 0xFF666666;
    }

    @Override
    public int getTitleViewColor() {
        return 0xFF222222;
    }

    @Override
    public int getRightViewColor() {
        return 0xFFA4A4A4;
    }

    @Override
    public float getLeftViewSize() {
        return 14;
    }

    @Override
    public float getTitleViewSize() {
        return 16;
    }

    @Override
    public float getRightViewSize() {
        return 14;
    }

    @Override
    public boolean isLineVisible() {
        return true;
    }

    @Override
    public int getLineColor() {
        return 0xFFECECEC;
    }

    @Override
    public int getLineSize() {
        return 1;
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