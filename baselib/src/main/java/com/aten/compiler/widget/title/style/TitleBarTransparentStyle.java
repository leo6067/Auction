package com.aten.compiler.widget.title.style;


import com.aten.compiler.R;

/**
 *    author : HJQ
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2018/08/20
 *    desc   : 默认透明主题样式实现（布局属性：app:bar_style="transparent"）
 */
public class TitleBarTransparentStyle extends TitleBarLightStyle {

    @Override
    public int getBackgroundColor() {
        return 0x00000000;
    }

    @Override
    public int getBackIconResource() {
        return R.drawable.ic_general_white_return_bg;
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
    public int getRightViewColor() {
        return 0xFFFFFFFF;
    }

    @Override
    public float getRightViewSize() {
        return 36f;
    }

    @Override
    public boolean isLineVisible() {
        return false;
    }

    @Override
    public int getLineColor() {
        return 0x00000000;
    }

    @Override
    public int getLeftViewBackground() {
        return R.drawable.bar_selector_selectable_transparent;
    }

    @Override
    public int getRightViewBackground() {
        return R.drawable.bar_selector_selectable_transparent;
    }
}