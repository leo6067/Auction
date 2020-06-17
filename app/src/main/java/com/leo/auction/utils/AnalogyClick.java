package com.leo.auction.utils;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Leo on 2017/8/26.
 *
 *
 * 用于模拟点击
 * setSimulateClick(passwordView, 100, 160);
 */

public class AnalogyClick {


    //模拟点击
    private void setSimulateClick(View view, float x, float y) {
        long downTime = SystemClock.uptimeMillis();
        final MotionEvent downEvent = MotionEvent.obtain(downTime, downTime,
                MotionEvent.ACTION_DOWN, x, y, 0);
        downTime += 1000;
        final MotionEvent upEvent = MotionEvent.obtain(downTime, downTime,
                MotionEvent.ACTION_UP, x, y, 0);
        view.onTouchEvent(downEvent);
        view.onTouchEvent(upEvent);
        downEvent.recycle();
        upEvent.recycle();
    }



}
