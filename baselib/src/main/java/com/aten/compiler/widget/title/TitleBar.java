package com.aten.compiler.widget.title;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aten.compiler.R;
import com.aten.compiler.widget.title.style.TitleBarGreenStyle;
import com.aten.compiler.widget.title.style.TitleBarLightStyle;
import com.aten.compiler.widget.title.style.TitleBarNightStyle;
import com.aten.compiler.widget.title.style.TitleBarTransparentStyle;
import com.aten.compiler.widget.title.style.TitleBarWhiteStyle;
import com.aten.compiler.widget.title.style.TitleGrayStyle;


/**
 * author : HJQ
 * github : https://github.com/getActivity/TitleBar
 * time   : 2018/08/17
 * desc   : Android 通用标题栏
 */
public class TitleBar extends FrameLayout implements View.OnClickListener, Runnable {

    private static ITitleBarStyle sDefaultStyle = new TitleBarLightStyle();

    private OnTitleBarListener mListener;

    private LinearLayout mMainLayout;

    private TextView mLeftView;
    private TextView mTitleView;
    private TextView mRightView;

    private View mLineView;

    public TitleBar(Context context) {
        this(context, null, 0);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initStyle(attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 设置TitleBar默认的宽度
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST
                || MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.EXACTLY);
        }

        // 设置TitleBar默认的高度
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST
                || MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED) {

            int titleBarHeight = sDefaultStyle.getTitleBarHeight();
            if (titleBarHeight <= 0) {
                titleBarHeight = ViewBuilder.getActionBarHeight(getContext());
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(titleBarHeight, MeasureSpec.EXACTLY);
            }

            final Drawable background = getBackground();
            // 如果当前背景是一张图片的话
            if (background instanceof BitmapDrawable) {
                mMainLayout.getLayoutParams().height = MeasureSpec.getSize(heightMeasureSpec);
                // 算出标题栏的宽度和图片的宽度之比例
                final double ratio = (double) MeasureSpec.getSize(widthMeasureSpec) / (double) background.getIntrinsicWidth();
                heightMeasureSpec = MeasureSpec.makeMeasureSpec((int) (ratio * background.getIntrinsicHeight()), MeasureSpec.EXACTLY);
            }
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void initView(Context context) {
        ViewBuilder builder = new ViewBuilder(context);
        mMainLayout = builder.getMainLayout();
        mLineView = builder.getLineView();
        mTitleView = builder.getTitleView();
        mLeftView = builder.getLeftView();
        mRightView = builder.getRightView();

        mLeftView.setEnabled(false);
        mTitleView.setEnabled(false);
        mRightView.setEnabled(false);

        mMainLayout.addView(mLeftView);
        mMainLayout.addView(mTitleView);
        mMainLayout.addView(mRightView);

        addView(mMainLayout, 0);
        addView(mLineView, 1);
    }

    private void initStyle(AttributeSet attrs) {

        final TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.TitleBar);

        final ITitleBarStyle style;
        // 样式设置
        switch (ta.getInt(R.styleable.TitleBar_bar_style, 0)) {
            case 0x10:
                style = new TitleBarLightStyle();
                break;
            case 0x20:
                style = new TitleBarNightStyle();
                break;
            case 0x30:
                style = new TitleBarTransparentStyle();
                break;
            case 0x40:
                style = new TitleBarGreenStyle();
                break;
            case 0x50:
                style = new TitleBarWhiteStyle();
                break;
            case 0x60:
                style = new TitleGrayStyle();
                break;
            default:
                style = TitleBar.sDefaultStyle;
                break;
        }

        // 标题设置
        if (ta.hasValue(R.styleable.TitleBar_title_left)) {
            setLeftTitle(ta.getString(R.styleable.TitleBar_title_left));
        }

        if (ta.hasValue(R.styleable.TitleBar_title)) {
            setTitle(ta.getString(R.styleable.TitleBar_title));
        } else {
            // 如果当前上下文对象是Activity，就获取Activity的标题
            if (getContext() instanceof Activity) {
                CharSequence label = ViewBuilder.getActivityLabel((Activity) getContext());
                if (label != null && !"".equals(label.toString())) {
                    setTitle(label);
                }
            }
        }

        // 图标设置
        if (ta.hasValue(R.styleable.TitleBar_title_right)) {
            setRightTitle(ta.getString(R.styleable.TitleBar_title_right));
        }

        if (ta.hasValue(R.styleable.TitleBar_icon_left)) {
            setLeftIcon(getContext().getResources().getDrawable(ta.getResourceId(R.styleable.TitleBar_icon_left, 0)));
        } else {
            if (ta.getBoolean(R.styleable.TitleBar_icon_back, style.getBackIconResource() > 0)) {
                // 显示返回图标
                setLeftIcon(getContext().getResources().getDrawable(style.getBackIconResource()));
            }
        }

        if (ta.hasValue(R.styleable.TitleBar_icon_right)) {
            setRightIcon(getContext().getResources().getDrawable(ta.getResourceId(R.styleable.TitleBar_icon_right, 0)));
        }

        // 文字颜色设置
        setLeftColor(ta.getColor(R.styleable.TitleBar_color_left, style.getLeftViewColor()));
        setTitleColor(ta.getColor(R.styleable.TitleBar_color_title, style.getTitleViewColor()));
        setRightColor(ta.getColor(R.styleable.TitleBar_color_right, style.getRightViewColor()));

        // 文字大小设置
        setLeftSize(TypedValue.COMPLEX_UNIT_PX, ta.getDimensionPixelSize(R.styleable.TitleBar_size_left, (int) style.getLeftViewSize()));
        setTitleSize(TypedValue.COMPLEX_UNIT_PX, ta.getDimensionPixelSize(R.styleable.TitleBar_size_title, (int) style.getTitleViewSize()));
        setRightSize(TypedValue.COMPLEX_UNIT_PX, ta.getDimensionPixelSize(R.styleable.TitleBar_size_right, (int) style.getRightViewSize()));

        // 背景设置
        setLeftBackground(ta.getResourceId(R.styleable.TitleBar_background_left, style.getLeftViewBackground()));
        setRightBackground(ta.getResourceId(R.styleable.TitleBar_background_right, style.getRightViewBackground()));

        // 分割线设置
        setLineVisible(ta.getBoolean(R.styleable.TitleBar_line_visible, style.isLineVisible()));
        setLineColor(ta.getColor(R.styleable.TitleBar_line_color, style.getLineColor()));
        setLineSize(ta.getDimensionPixelSize(R.styleable.TitleBar_title_line_size, style.getLineSize()));

        // 回收TypedArray
        ta.recycle();

        // 设置默认背景
        if (getBackground() == null) {
            setBackgroundColor(style.getBackgroundColor());
        }
    }

    /**
     * {@link Runnable#run()}
     */
    @Override
    public void run() {
        // 更新中间标题的内边距，避免向左或者向右偏移
        final int leftSize = mLeftView.getWidth();
        final int rightSize = mRightView.getWidth();
        if (leftSize != rightSize) {
            if (leftSize > rightSize) {
                mTitleView.setPadding(0, 0, leftSize - rightSize, 0);
            } else {
                mTitleView.setPadding(rightSize - leftSize, 0, 0, 0);
            }
        }

        // 更新 View 状态
        if (!"".equals(mLeftView.getText().toString()) || ViewBuilder.hasCompoundDrawables(mLeftView)) {
            mLeftView.setEnabled(true);
        }
        if (!"".equals(mTitleView.getText().toString()) || ViewBuilder.hasCompoundDrawables(mTitleView)) {
            mTitleView.setEnabled(true);
        }
        if (!"".equals(mRightView.getText().toString()) || ViewBuilder.hasCompoundDrawables(mRightView)) {
            mRightView.setEnabled(true);
        }
    }

    /**
     * {@link View.OnClickListener}
     */
    @Override
    public void onClick(View v) {
        if (getOnTitleBarListener() == null) return;

        final int id = v.getId();
        if (id == R.id.bar_id_left_view) {
            getOnTitleBarListener().onLeftClick(v);
        } else if (id == R.id.bar_id_title_view) {
            getOnTitleBarListener().onTitleClick(v);
        } else if (id == R.id.bar_id_right_view) {
            getOnTitleBarListener().onRightClick(v);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // 设置监听
        mTitleView.setOnClickListener(this);
        mLeftView.setOnClickListener(this);
        mRightView.setOnClickListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        // 移除监听
        mTitleView.setOnClickListener(null);
        mLeftView.setOnClickListener(null);
        mRightView.setOnClickListener(null);
        super.onDetachedFromWindow();
    }

    /**
     * 获取点击监听器
     */
    public OnTitleBarListener getOnTitleBarListener() {
        return mListener;
    }

    /**
     * 设置点击监听器
     */
    public void setOnTitleBarListener(OnTitleBarListener l) {
        mListener = l;
    }

    /**
     * 获取标题
     */
    public CharSequence getTitle() {
        return mTitleView.getText();
    }

    /**
     * 设置标题
     */
    public void setTitle(int stringId) {
        setTitle(getResources().getString(stringId));
    }

    public void setTitle(CharSequence text) {
        mTitleView.setText(text);
        post(this);
    }

    /**
     * 设置左边的标题
     */
    public void setLeftTitle(int stringId) {
        setLeftTitle(getResources().getString(stringId));
    }

    public void setLeftTitle(CharSequence text) {
        mLeftView.setText(text);
        post(this);
    }

    /**
     * 设置右边的标题
     */
    public void setRightTitle(int stringId) {
        setRightTitle(getResources().getString(stringId));
    }

    public void setRightTitle(CharSequence text) {
        mRightView.setText(text);
        post(this);
    }

    /**
     * 设置左边的图标
     */
    public void setLeftIcon(int iconId) {
        if (iconId > 0) {
            setLeftIcon(getContext().getResources().getDrawable(iconId));
        }
    }

    public void setLeftIcon(Drawable drawable) {
        drawable.setBounds(0, 0, (int) getResources().getDimension(R.dimen.dp_40), (int) getResources().getDimension(R.dimen.dp_40));
        mLeftView.setCompoundDrawables(drawable, null, null, null);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins((int) getResources().getDimension(R.dimen.dp_30), 0, 0, 0);//4个参数按顺序分别是左上右下
        mLeftView.setLayoutParams(layoutParams);


//        mLeftView.setMinWidth((int) getResources().getDimension(R.dimen.dp_80));
//        mLeftView.setWidth((int) getResources().getDimension(R.dimen.dp_130));
        post(this);
    }

    /**
     * 设置右边的图标
     */
    public void setRightIcon(int iconId) {
        if (iconId > 0) {
            setRightIcon(getContext().getResources().getDrawable(iconId));
        }
    }

    public void setRightIcon(Drawable drawable) {
        mRightView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        post(this);
    }

    /**
     * 设置标题颜色
     */
    public void setTitleColor(int color) {
        mTitleView.setTextColor(color);
    }

    /**
     * 设置左标题颜色
     */
    public void setLeftColor(int color) {
        mLeftView.setTextColor(color);
    }

    /**
     * 设置右标题颜色
     */
    public void setRightColor(int color) {
        mRightView.setTextColor(color);
    }

    /**
     * 设置左标题状态选择器
     */
    public void setLeftBackground(int bgId) {
        if (bgId > 0) {
            mLeftView.setBackgroundResource(bgId);
        }
    }

    public void setLeftBackground(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mLeftView.setBackground(drawable);
        } else {
            mLeftView.setBackgroundDrawable(drawable);
        }
    }

    /**
     * 设置右标题状态选择器
     */
    public void setRightBackground(int bgId) {
        if (bgId > 0) {
            mRightView.setBackgroundResource(bgId);
        }
    }

    public void setRightBackground(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mRightView.setBackground(drawable);
        } else {
            mRightView.setBackgroundDrawable(drawable);
        }
    }

    /**
     * 设置左标题的文本大小
     */
    public void setLeftSize(int unit, float size) {
        mLeftView.setTextSize(unit, size);
    }

    /**
     * 设置标题的文本大小
     */
    public void setTitleSize(int unit, float size) {
        mTitleView.setTextSize(unit, size);
    }

    /**
     * 设置右标题的文本大小
     */
    public void setRightSize(int unit, float size) {
        mRightView.setTextSize(unit, size);
    }

    /**
     * 设置分割线是否显示
     */
    public void setLineVisible(boolean visible) {
        mLineView.setVisibility(visible ? VISIBLE : GONE);
    }

    /**
     * 设置分割线的颜色
     */
    public void setLineColor(int color) {
        mLineView.setBackgroundColor(color);
    }

    /**
     * 设置分割线的大小
     */
    public void setLineSize(int size) {
        ViewGroup.LayoutParams layoutParams = mLineView.getLayoutParams();
        layoutParams.height = size;
        mLineView.setLayoutParams(layoutParams);
    }

    /**
     * 获取主要的布局对象
     */
    public LinearLayout getMainLayout() {
        return mMainLayout;
    }

    /**
     * 获取左标题View对象
     */
    public TextView getLeftView() {
        return mLeftView;
    }

    /**
     * 获取标题View对象
     */
    public TextView getTitleView() {
        return mTitleView;
    }

    /**
     * 获取右标题View对象
     */
    public TextView getRightView() {
        return mRightView;
    }

    /**
     * 获取分割线View对象
     */
    public View getLineView() {
        return mLineView;
    }

    /**
     * 统一全局的TitleBar样式，建议在{@link android.app.Application#onCreate()}中初始化
     *
     * @param style 样式实现类，框架已经实现三种不同的样式
     *              日间模式：{@link TitleBarLightStyle}
     *              夜间模式：{@link TitleBarNightStyle}
     *              透明模式：{@link TitleBarTransparentStyle}
     */
    public static void initStyle(ITitleBarStyle style) {
        TitleBar.sDefaultStyle = style;
    }
}