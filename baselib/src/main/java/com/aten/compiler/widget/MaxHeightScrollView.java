package com.aten.compiler.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.WindowManager;
import com.aten.compiler.R;

/**
 * project:yfpw-android
 * package:${PACKACE_NAME}
 * Created by 彭俊鸿 on 2018/7/20.
 * e-mail : 1031028399@qq.com
 * 具有最大高度的scrollview
 */
public class MaxHeightScrollView extends ReboundNetScrollView{

    private static final float DEFAULT_MAX_RATIO_WITHOUT_ARGU = 1.0f;
    private static final float DEFAULT_MAX_RATIO = 0f;
    private static final float DEFAULT_MAX_DIMEN = 0f;

    private float mMaxRatio = DEFAULT_MAX_RATIO;
    private float mMaxDimen = DEFAULT_MAX_DIMEN;
    private float mMaxHeight = 0;

    public MaxHeightScrollView(Context context) {
        super(context);
    }

    public MaxHeightScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MaxHeightView);
        final int count = a.getIndexCount();
        for (int i = 0; i < count; ++i) {
            int attr = a.getIndex(i);
            if(attr == R.styleable.MaxHeightView_mhv_HeightRatio){
                mMaxRatio = a.getFloat(attr, DEFAULT_MAX_RATIO);
            }else if(attr == R.styleable.MaxHeightView_mhv_HeightDimen){
                mMaxDimen = a.getDimension(attr, DEFAULT_MAX_DIMEN);
            }
        }
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {

            if(mMaxDimen <= 0 && mMaxRatio <= 0){
                mMaxHeight = DEFAULT_MAX_RATIO_WITHOUT_ARGU * (float) getScreenHeight(getContext());
            } else if (mMaxDimen <= 0 && mMaxRatio > 0) {
                mMaxHeight = mMaxRatio * (float) getScreenHeight(getContext());
            } else if(mMaxDimen > 0 && mMaxRatio <= 0) {
                mMaxHeight = mMaxDimen;
            } else{
                mMaxHeight = Math.min(mMaxDimen, mMaxRatio * (float) getScreenHeight(getContext()));
            }


            heightMeasureSpec = MeasureSpec.makeMeasureSpec((int) mMaxHeight, MeasureSpec.AT_MOST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 重新计算控件的宽高
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }
}
