package com.leo.auction.common.widget;

import android.content.Context;
import android.support.annotation.ColorInt;


public class LinearLayoutDivider extends DividerItemDecoration {

        public LinearLayoutDivider(Context context, int lineWidthDp, @ColorInt int colorRGB) {
            super(context, lineWidthDp, colorRGB);
        }
        @Override
        public boolean[] getItemSidesIsHaveOffsets(int itemPosition) {
            //顺时针顺序:left, top, right, bottom
            boolean[] isOffset = {false, false, false, true};//true为有分割
            return isOffset;
        }
}
