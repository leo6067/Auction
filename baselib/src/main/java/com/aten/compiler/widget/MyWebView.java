package com.aten.compiler.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.aten.compiler.widget
 * 作    者： Leo---悠悠球
 * 时    间： 2020/6/17
 * 描    述： 可监听触摸滑动
 * 修    改：
 * ===============================================
 */

public class MyWebView extends WebView {

    private OnTouchEventCallback mOnTouchEventCallback;

    public MyWebView(Context context) {
        super(context);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (mOnTouchEventCallback!=null){
                    mOnTouchEventCallback.onActionDown();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mOnTouchEventCallback!=null){
                    mOnTouchEventCallback.onActionMove();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    public OnTouchEventCallback getOnTouchEventCallback() {
        return mOnTouchEventCallback;
    }

    public void setOnTouchEventCallback(
            final OnTouchEventCallback onTouchEventCallback) {
        mOnTouchEventCallback = onTouchEventCallback;
    }

    public static interface OnTouchEventCallback {
        public void onActionDown();
        public void onActionMove();
    }
}
