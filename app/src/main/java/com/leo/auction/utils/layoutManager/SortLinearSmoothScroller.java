package com.leo.auction.utils.layoutManager;

import android.content.Context;
import android.support.v7.widget.LinearSmoothScroller;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.utils.layoutManager
 * 作    者： Leo---悠悠球
 * 时    间： 2020/6/29
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class SortLinearSmoothScroller  extends LinearSmoothScroller {
    public SortLinearSmoothScroller(Context context) {
        super(context);
    }
    @Override
    protected int getVerticalSnapPreference() {
        return SNAP_TO_START;
    }
}
