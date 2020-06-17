package com.aten.compiler.widget.banner.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * by y on 2016/10/25
 */
class BannerTipsLayout extends RelativeLayout {

    private TextView textView = null;
    private LinearLayout linearLayout = null;
    private DotsInterface dotsInterface;

    public BannerTipsLayout(Context context) {
        super(context);
    }

    public BannerTipsLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BannerTipsLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * Initialize the dots
     */
    void setDots(DotsInterface dotsInterface) {
        this.dotsInterface=dotsInterface;
        if (linearLayout != null) {
            removeView(linearLayout);
            linearLayout = null;
        }
        linearLayout = new LinearLayout(getContext());
        linearLayout.removeAllViews();
        for (int i = 0; i < dotsInterface.dotsCount(); i++) {
            View view = new View(getContext());
            //noinspection deprecation
            view.setBackgroundDrawable(dotsInterface.dotsSelector());
            if (i == 0) {
                view.setEnabled(true);
            } else {
                view.setEnabled(false);
            }
            LinearLayout.LayoutParams params;


            if (view.isEnabled()){
                params=new LinearLayout.LayoutParams(dotsInterface.dotsSelectWidth(), dotsInterface.dotsHeight());
            }else {
                params = new LinearLayout.LayoutParams(dotsInterface.dotsWidth(), dotsInterface.dotsHeight());
            }
            params.leftMargin = dotsInterface.dotsLeftMargin();
            params.rightMargin = dotsInterface.dotsRightMargin();
            view.setLayoutParams(params);

            linearLayout.addView(view);
        }

        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.CENTER_VERTICAL);
        params.addRule(dotsInterface.dotsSite());
        addView(linearLayout, params);
    }

    FrameLayout.LayoutParams setBannerTips(TipsInterface tipsInterface) {
        FrameLayout.LayoutParams tipsParams = new FrameLayout.LayoutParams(tipsInterface.tipsWidth(), tipsInterface.tipsHeight());
        switch (tipsInterface.tipsSite()) {
            case BannerLayout.BOTTOM:
                tipsParams.gravity = Gravity.BOTTOM;
                break;
            case BannerLayout.TOP:
                tipsParams.gravity = Gravity.TOP;
                break;
            case BannerLayout.CENTER:
                tipsParams.gravity = Gravity.CENTER;
                break;
        }
        if (tipsInterface.isBackgroundColor()) {
            setBackgroundColor(tipsInterface.tipsLayoutBackgroundColor());
        }
        return tipsParams;
    }

    /**
     * Update the dot position
     */
    void changeDotsPosition(int position, int newPosition) {
        if (linearLayout != null) {
            View oldView = linearLayout.getChildAt(position);
            View newView = linearLayout.getChildAt(newPosition);
            oldView.setEnabled(false);
            newView.setEnabled(true);
            if (dotsInterface!=null){
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(dotsInterface.dotsWidth(), dotsInterface.dotsHeight());
                params1.leftMargin = dotsInterface.dotsLeftMargin();
                params1.rightMargin = dotsInterface.dotsRightMargin();
                oldView.setLayoutParams(params1);

                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(dotsInterface.dotsSelectWidth(), dotsInterface.dotsHeight());
                params2.leftMargin = dotsInterface.dotsLeftMargin();
                params2.rightMargin = dotsInterface.dotsRightMargin();
                newView.setLayoutParams(params2);
            }
        }
    }


    /**
     * Update title, the default on the left
     */
    void setTitle(TitleInterface titleInterface) {
        if (textView != null) {
            removeView(textView);
            textView = null;
        }
        textView = new TextView(getContext());
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setTextColor(titleInterface.titleColor());
        textView.setTextSize(titleInterface.titleSize());
        textView.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        LayoutParams params = new LayoutParams(titleInterface.titleWidth(), titleInterface.titleHeight());
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        params.leftMargin = titleInterface.titleLeftMargin();
        params.rightMargin = titleInterface.titleRightMargin();
        params.addRule(titleInterface.titleSite());
        addView(textView, params);
    }

    void setTitle(String title) {
        clearText();
        if (textView != null && !TextUtils.isEmpty(title)) {
            textView.setText(title);
        }
    }

    void clearText() {
        if (textView != null) {
            textView.setText("");
        }
    }

    interface TipsInterface {

        int tipsSite();

        int tipsWidth();

        int tipsHeight();

        int tipsLayoutBackgroundColor();

        boolean isBackgroundColor();
    }

    interface TitleInterface {
        int titleColor();

        float titleSize();

        int titleLeftMargin();

        int titleRightMargin();

        int titleWidth();

        int titleHeight();

        int titleSite();

    }

    interface DotsInterface {
        int dotsCount();

        Drawable dotsSelector();

        int dotsHeight();

        int dotsWidth();

        int dotsSelectWidth();

        int dotsLeftMargin();

        int dotsRightMargin();

        int dotsBottomMargin();

        int dotsSite();
    }
}
