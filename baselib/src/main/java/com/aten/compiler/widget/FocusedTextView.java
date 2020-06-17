package com.aten.compiler.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * project:PJHAndroidFrame
 * package:${PACKACE_NAME}
 * Created by 彭俊鸿 on 2018/8/2.
 * e-mail : 1031028399@qq.com
 */
public class FocusedTextView extends AppCompatTextView {
    public FocusedTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public FocusedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public FocusedTextView(Context context) {
        super(context);
    }
    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        if(focused)
            super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }
    @Override
    public void onWindowFocusChanged(boolean focused) {
        if(focused)
            super.onWindowFocusChanged(focused);
    }
    @Override
    public boolean isFocused() {
        return true;
    }
}
