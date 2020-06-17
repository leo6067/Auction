package com.aten.compiler.widget.marquee;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.aten.compiler.R;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.RxTool;

import java.util.List;

/**
 * Created by shenjj on 2016/12/26.
 */

public class MarqueeTextView<T> extends AppCompatTextView {
    private boolean isVerticalSwitch = true;
    private boolean isHorizontalScroll = true;

    private int DEFAULT_VERTICAL_SPEED = 1000;
    private int DEFAULT_VERTICAL_INTERVAL = 1000;
    private int DEFAULT_HORIZONTAL_SPEED = 300;     //滚动每个字的时间
    private int DEFAULT_HORIZONTAL_INTERVAL = 4000;
    private int DEFAULT_HORIZONTAL_LOOP_SPEED = 300;  //滚动每个字的时间

    private int verticalSwitchSpeed;
    private int verticalSwitchInterval;
    private int horizontalScrollSpeed;
    private int horizontalScrollInterval;
    private int horizontalLoopSpeed;

    private int viewHeight;
    private int viewWidth;
    private int paddingLeft;
    private int paddingTop;
    private int paddingRight;
    private int paddingBottom;

    private int contentColor = Color.WHITE; //内容的颜色
    private int contentTextSize = 100; //内容文字大小

    private boolean hasInited = false;
    private int currentY = 0; //文字的Y坐标
    private int currnetX = 0; //文字的Y坐标
    private int xOffset = 0;//文字和view宽度差
    private int yStartPos = 0;//文字初始位置
    private int xStartPos = 0;//文字初始位置
    private int currnetIndex = 0;
    private Paint contentPaint;//当前数据的画笔
    private Paint nextContentPaint;//下一条数据的画笔
    private int contentWidth;
    private int contentHeight;
    private int maxContentWidth = 0;
    private int maxContentHeight = 0;
    private boolean isHorizontalRunning = false;
    private boolean isVerticalRunning = false;
    private boolean isTextAtMiddle = true;
    private boolean horizontalOriLeft = true;
    private MarqueeItemListener mOnMarqueeItemListener;
    private List<T> datas;
    private IMarqueeModel singleModel;

    public void setContentList(List<T> datas) {
        this.datas = datas;
        this.singleModel=null;
        hasInited=false;
        requestLayout();
        invalidate();
    }

    public void setSingleText(List<T> datas) {
        if (datas.get(0) instanceof IMarqueeModel) {
            this.datas = datas;
            this.singleModel = (IMarqueeModel) datas.get(0);
            hasInited=false;
            requestLayout();
        }
    }

    public void setVerticalSwitch(boolean verticalSwitch) {
        isVerticalSwitch = verticalSwitch;
        invalidate();
    }

    public void setHorizontalScroll(boolean horizontalScroll) {
        isHorizontalScroll = horizontalScroll;
        invalidate();
    }

    public void setVerticalSwitchSpeed(int verticalSwitchSpeed) {
        this.verticalSwitchSpeed = verticalSwitchSpeed;
        invalidate();
    }

    public void setVerticalSwitchInterval(int verticalSwitchInterval) {
        this.verticalSwitchInterval = verticalSwitchInterval;
        invalidate();
    }

    public void setHorizontalScrollSpeed(int horizontalScrollSpeed) {
        this.horizontalScrollSpeed = horizontalScrollSpeed;
        invalidate();
    }

    public void setHorizontalScrollInterval(int horizontalScrollInterval) {
        this.horizontalScrollInterval = horizontalScrollInterval;
        invalidate();
    }

    public MarqueeTextView(Context context) {
        super(context);
        init();
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        init();
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        init();
    }

    public void setContentColor(int contentColor) {
        this.contentColor = contentColor;
    }

    public void setContentTextSize(int contentTextSize) {
        this.contentTextSize = contentTextSize;
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.MarqueeTextView);
        verticalSwitchSpeed = array.getInt(R.styleable.MarqueeTextView_vertical_switch_speed, DEFAULT_VERTICAL_SPEED);
        verticalSwitchInterval = array.getInt(R.styleable.MarqueeTextView_vertical_switch_interval, DEFAULT_VERTICAL_INTERVAL);
        horizontalScrollSpeed = array.getInt(R.styleable.MarqueeTextView_horizontal_scroll_speed, DEFAULT_HORIZONTAL_SPEED);
        horizontalScrollInterval = array.getInt(R.styleable.MarqueeTextView_horizontal_scroll_interval, DEFAULT_HORIZONTAL_INTERVAL);
        horizontalLoopSpeed = array.getInt(R.styleable.MarqueeTextView_horizontal_loop_speed, DEFAULT_HORIZONTAL_LOOP_SPEED);
        contentColor = array.getColor(R.styleable.MarqueeTextView_content_text_color, Color.BLACK);
        contentTextSize = (int) array.getDimension(R.styleable.MarqueeTextView_content_text_size, 14);//px
        array.recycle();
    }

    private void init() {
        contentPaint = new Paint();
        contentPaint.setAntiAlias(true);
        contentPaint.setDither(true);
        contentPaint.setTextSize(contentTextSize);
        contentPaint.setColor(contentColor);

        nextContentPaint = new Paint();
        nextContentPaint.setAntiAlias(true);
        nextContentPaint.setDither(true);
        nextContentPaint.setTextSize(contentTextSize);
        nextContentPaint.setColor(contentColor);

        if (verticalIntervalAnimator!=null){
            verticalIntervalAnimator.cancel();
            verticalIntervalAnimator=null;
        }

        if (verticalSwitchAnimator!=null){
            verticalSwitchAnimator.cancel();
            verticalSwitchAnimator=null;
        }
        if (horizontalScrollAnimator!=null){
            horizontalScrollAnimator.cancel();
            horizontalScrollAnimator=null;
        }
        if (horizontalScrollAnimatorLoop!=null){
            horizontalScrollAnimatorLoop.cancel();
            horizontalScrollAnimatorLoop=null;
        }

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!RxTool.isFastClick(RxTool.MIN_CLICK_DELAY_TIME_500)) {
                    return;
                }
                if (mOnMarqueeItemListener != null) {
                    mOnMarqueeItemListener.onItemClick(currnetIndex, datas.get(currnetIndex));
                }
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = getMeasuredWidth();
        viewHeight = getMeasuredHeight();

        paddingLeft = getPaddingLeft();
        paddingTop = getPaddingTop();
        paddingRight = getPaddingRight();
        paddingBottom = getPaddingBottom();

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (null != datas && datas.size() > 1) {
            for (int i = 0; i < datas.size(); i++) {
                if (datas.get(i) instanceof IMarqueeModel) {
                    String contentString = ((IMarqueeModel) datas.get(i)).getMarqueeText();
                    Rect contentBound = new Rect();
                    contentPaint.getTextBounds(contentString, 0, contentString.length(), contentBound);
                    int tempWidth = contentBound.width();
                    int tempHeight = contentBound.height();
                    maxContentHeight = Math.max(maxContentHeight, tempHeight);
                    maxContentWidth = Math.max(maxContentWidth, tempWidth);
                }
            }
        } else if (singleModel != null) {
            Rect contentBound = new Rect();
            contentPaint.getTextBounds(singleModel.getMarqueeText(), 0, singleModel.getMarqueeText().length(), contentBound);
            maxContentWidth = contentBound.width();
            maxContentHeight = contentBound.height();
        }

        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            setMeasuredDimension(widthSize, heightSize);
        } else if (widthMode == MeasureSpec.EXACTLY) {
            setMeasuredDimension(widthSize, maxContentHeight);
        } else if (heightMode == MeasureSpec.EXACTLY) {
            setMeasuredDimension(maxContentWidth, heightSize);
        } else {
            setMeasuredDimension(maxContentWidth, maxContentHeight);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null != datas && datas.size() > 1) {
            if (currnetIndex >= datas.size()) {
                currnetIndex = 0;                        //播放完，循环
            }
            viewHeight = getMeasuredHeight();
            viewWidth = getMeasuredWidth();
            String currentString = getMarqueeText(contentPaint, currnetIndex);

            int nextIndex = currnetIndex + 1;
            if (currnetIndex + 1 >= datas.size()) {
                nextIndex = 0;
            }

            String nextString = getMarqueeText(nextContentPaint, nextIndex);

            Rect contentBound = new Rect();
            contentPaint.getTextBounds(currentString, 0, currentString.length(), contentBound);
            contentWidth = contentBound.width();
            xOffset = contentWidth - viewWidth;                 //文字超出View的部分。需要水平播放

            Paint.FontMetrics fontMetrics = contentPaint.getFontMetrics();
            int textHeight = (int) ((-fontMetrics.ascent - fontMetrics.descent) / 2);
            int textWholeHeight = (int) ((-fontMetrics.top - fontMetrics.bottom) / 2);

            yStartPos = viewHeight / 2 + maxContentHeight / 4 + textHeight / 4;
            if (!hasInited) {
                hasInited = true;
                currentY = yStartPos;
            }

            if (xOffset > 0) {
                xOffset += contentTextSize * 2;   //另外加点留白.设留白两个字宽
                if (!isHorizontalRunning && !isVerticalRunning) {
                    isHorizontalRunning = true;
                    startHorizontalScroll();
                    currnetX = 0;
                }
            } else {
                if (!isVerticalRunning) {
                    isVerticalRunning = true;
                    startVerticalInterval();
                    currnetX = 0;
                }
            }

            canvas.drawText(currentString, currnetX, currentY, contentPaint);
            canvas.drawText(nextString, 0, currentY + viewHeight, nextContentPaint);
        } else if (singleModel!=null) {
            viewHeight = getMeasuredHeight();
            viewWidth = getMeasuredWidth();
            Rect contentBound = new Rect();
            getMarqueeText(contentPaint,0);
            contentPaint.getTextBounds(singleModel.getMarqueeText(), 0, singleModel.getMarqueeText().length(), contentBound);
            contentWidth = contentBound.width();
            if (contentWidth<=viewWidth){
                xOffset =contentWidth-viewWidth;
            }else {
                xOffset =contentWidth;
            }
            Paint.FontMetrics fontMetrics = contentPaint.getFontMetrics();
            int textHeight = (int) ((-fontMetrics.ascent - fontMetrics.descent) / 2);
            int textWholeHeight = (int) ((-fontMetrics.top - fontMetrics.bottom) / 2);
            yStartPos = viewHeight / 2 + maxContentHeight / 4 + textHeight / 4;

            if (!hasInited) {
                hasInited = true;
                currnetX = 0;
                xStartPos = currnetX;
            }

            if (xOffset > 0) {
                xOffset += contentTextSize * 10;
                if (!isHorizontalRunning) {
                    isHorizontalRunning = true;
                    startHorizontalLoop();
                }
                canvas.drawText(singleModel.getMarqueeText(), currnetX+contentWidth+contentTextSize * 10, yStartPos, contentPaint);
            }

            canvas.drawText(singleModel.getMarqueeText(), currnetX, yStartPos, contentPaint);

        }
    }

    //获取跑马灯的指定index的文字 以及设置颜色
    private String getMarqueeText(Paint marqueeContentPaint, int index) {
        String string = "";
        if (datas.get(index) instanceof IMarqueeModel) {
            string = EmptyUtils.strEmpty(((IMarqueeModel) datas.get(index)).getMarqueeText());
            switch (((IMarqueeModel)datas.get(index)).getType()){
                case "1":
                    marqueeContentPaint.setColor(getResources().getColor(R.color.color_f17f42));
                    break;
                case "2":
                    marqueeContentPaint.setColor(getResources().getColor(R.color.color_333333));
                    break;
                case "3":
                    marqueeContentPaint.setColor(getResources().getColor(R.color.color_f16b6f));
                    break;
            }
        }

        return string;
    }

    private ValueAnimator verticalIntervalAnimator;
    private ValueAnimator verticalSwitchAnimator;
    private ValueAnimator horizontalScrollAnimator;
    private ValueAnimator horizontalScrollAnimatorLoop;
    private void startVerticalInterval() {
        verticalIntervalAnimator = ValueAnimator.ofFloat(0, 1);
        verticalIntervalAnimator.setDuration(verticalSwitchInterval);
        verticalIntervalAnimator.setInterpolator(new LinearInterpolator());
        verticalIntervalAnimator.start();
        verticalIntervalAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startVerticalSwitch();
            }
        });
    }

    private void startVerticalSwitch() {
        verticalSwitchAnimator = ValueAnimator.ofFloat(0, 1);
        verticalSwitchAnimator.setDuration(verticalSwitchSpeed);
        verticalSwitchAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        verticalSwitchAnimator.start();
        verticalSwitchAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                currentY = (int) (yStartPos - value * viewHeight * 1);
                invalidate();
            }
        });
        verticalSwitchAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                currnetIndex++;
                currentY = yStartPos;
                isVerticalRunning = false;
                invalidate();
            }
        });
    }

    private void startHorizontalScroll() {
        horizontalScrollAnimator = ValueAnimator.ofFloat(0, 1);
        //在崩溃统计上看到值<0的bug
        if (horizontalScrollSpeed * xOffset / contentTextSize < 0) {
            isHorizontalRunning = false;
            return;
        }
        horizontalScrollAnimator.setDuration(horizontalScrollSpeed * xOffset / contentTextSize);
        horizontalScrollAnimator.setInterpolator(new LinearInterpolator());
        horizontalScrollAnimator.start();
        horizontalScrollAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                currnetX = (int) (-xOffset * value);
                invalidate();
            }
        });
        horizontalScrollAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isHorizontalRunning = false;
                isVerticalRunning = true;
                startVerticalInterval();
                invalidate();
            }
        });
    }

    private void startHorizontalLoop() {
        if (horizontalOriLeft) {
            horizontalScrollAnimatorLoop = ValueAnimator.ofFloat(0, 1);
        } else {
            horizontalScrollAnimatorLoop = ValueAnimator.ofFloat(0, -1);
        }
        if (horizontalScrollSpeed * xOffset / contentTextSize < 0) {
            isHorizontalRunning = false;
            return;
        }
        horizontalScrollAnimatorLoop.setDuration(horizontalLoopSpeed * xOffset / contentTextSize);
        horizontalScrollAnimatorLoop.setInterpolator(new LinearInterpolator());
        horizontalScrollAnimatorLoop.start();
        horizontalScrollAnimatorLoop.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                currnetX = (int) (xStartPos - xOffset * value);
                invalidate();
            }
        });
        horizontalScrollAnimatorLoop.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isHorizontalRunning = false;
//                horizontalOriLeft = !horizontalOriLeft;
                currnetX = xStartPos;
                invalidate();
            }
        });
    }

    public void setItemListener(MarqueeItemListener onMarqueeItemListener) {
        this.mOnMarqueeItemListener = onMarqueeItemListener;
    }

    public interface MarqueeItemListener<T> {
        void onItemClick(int pos, T t);
    }
}
