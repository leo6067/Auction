package com.aten.compiler.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;


/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.aten.compiler.widget
 * 作    者： Leo---悠悠球
 * 时    间： 2020/6/20
 * 描    述： 用于左右两个recycle 联动
 * 修    改：
 * ===============================================
 */
public class TouchCheckView extends RelativeLayout {

    private float originY,currentY;

    public TouchCheckView(Context context) {
        this(context, null);
    }

    public TouchCheckView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                originY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                currentY = ev.getY();
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
            default:
                break;
        }
        return false;
    }
    //获取滚动方向，向下为-1，向上为1
    public int getScrollDirection() {
        if(currentY > originY) {
            return -1;
        } else {
            return 1;
        }
    }
    //获取滚动方向下拉为true，上滑为false
    public boolean scrollChangeUp() {
        return (currentY > originY) ? true : false;
    }
}

