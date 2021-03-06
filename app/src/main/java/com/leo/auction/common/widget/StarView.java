package com.leo.auction.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.leo.auction.R;


/**
 * 简单实现星级评选控件：内部填充多个ImageView
 * 继承LinearLayout 设置水平方向
 * Created by qiuchunjia on 2016/4/12.
 */
public class StarView extends LinearLayout {
    private int mImageWidth = 20;  //图片设置默认的宽度
    private int mImageHeight = 20; //图片设置默认的高度
    private int mDefaultImageId = R.drawable.logo;
    private int mClickImageId = R.drawable.logo;
    private int mMargin = 5;   //图片之间默认的margin
    private int mStarNum = 5;  //星星默认的个数
    private int mStarChoose = 3;  //默认默认是三颗星
    private boolean isClick = true;
    private boolean isItemClick = false;

    private OnStarItemClickListener mStarItemClickListener;

    public StarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context, attrs);
    }

    public StarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context, attrs);
    }

    /**
     * 初始化数据
     *
     * @param context
     * @param attrs
     */
    private void initData(Context context, AttributeSet attrs) {
        this.setOrientation(HORIZONTAL);  //设置水平
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.StarView, 0, 0);
        int n = a.getIndexCount();

        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.StarView_mImageWidth:
                    mImageWidth = (int) a.getDimension(attr, mImageWidth);
                    break;
                case R.styleable.StarView_mImageHeight:
                    mImageHeight = (int) a.getDimension(attr, mImageHeight);
                    break;
                case R.styleable.StarView_mDefaultImageId:
                    mDefaultImageId = a.getResourceId(attr, mDefaultImageId);
                    break;
                case R.styleable.StarView_mClickImageId:
                    mClickImageId = a.getResourceId(attr, mClickImageId);
                    break;
                case R.styleable.StarView_mMargin:
                    mMargin = (int) a.getDimension(attr, mMargin);
                    break;
                case R.styleable.StarView_mStarNum:
                    mStarNum = a.getInt(attr, mStarNum);
                    break;
                case R.styleable.StarView_mStarChoose:
                    mStarChoose = a.getInt(attr, mStarChoose);
                    break;
                case R.styleable.StarView_isItemClick:
                    isItemClick = a.getBoolean(attr, isItemClick);
                    break;
            }
        }
        a.recycle();

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setStarNum(mStarNum);  //设置个数
    }

    /**
     * 设置星星数量
     *
     * @param number
     */
    private void setStarNum(int number) {
        if (number <= 0) {
            try {
                throw new Exception("设置的数据不能小于等于零");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.removeAllViews(); //清空所有view
        for (int i = 0; i < number; i++) {
            ImageView mImageView = new ImageView(getContext());
            final LayoutParams layoutParams = new LayoutParams(mImageWidth, mImageHeight);
            layoutParams.leftMargin = mMargin;
            layoutParams.rightMargin = mMargin;
            mImageView.setLayoutParams(layoutParams);
            this.addView(mImageView);
            mImageView.setImageResource(mDefaultImageId);
            setStarOnClick(mImageView, i);
        }
//        setCurrentChoose(mStarChoose);  //设置当前选择
    }

    /**
     * 设置点击事件
     *
     * @param imageView
     * @param i
     */
    public void setStarOnClick(final ImageView imageView, final int i) {
        if (isItemClick){
            if (imageView != null) {
                imageView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        resetDefaultImage();
                        setCurrentChoose(i+1);
                        if (mStarItemClickListener != null) {
                            mStarItemClickListener.onItemClick(i+1);
                        }
                    }
                });
            }
        }
    }

    /**
     * 设置当前选择
     *
     * @param index
     */
    public void setCurrentChoose(int index) {
        if(isClick){
            for (int i = 0; i < index; i++) {
                ImageView imageView = (ImageView) getChildAt(i);
                imageView.setImageResource(mClickImageId);
            }
            invalidate();
        }
    }

    /**
     * 重置默认为默认的图片
     */
    private void resetDefaultImage() {
        int cNum = getChildCount();
        for (int i = 0; i < cNum; i++) {
            ImageView imageView = (ImageView) getChildAt(i);
            imageView.setImageResource(mDefaultImageId);
        }
    }

    public int getImageWidth() {
        return mImageWidth;
    }

    public void setImageWidth(int mImageWidht) {
        this.mImageWidth = mImageWidht;
    }

    public int getImageHeight() {
        return mImageHeight;
    }

    public void setImageHeight(int mImageHeight) {
        this.mImageHeight = mImageHeight;
    }

    public int getDefaultImageId() {
        return mDefaultImageId;
    }

    public void setDefaultImageId(int resouceId) {
        this.mDefaultImageId = mDefaultImageId;
    }

    public int getClickImageId() {
        return mClickImageId;
    }

    public void setClickImageId(int mClickImageId) {
        this.mClickImageId = mClickImageId;
    }

    public OnStarItemClickListener getStarItemClickListener() {
        return mStarItemClickListener;
    }

    public void setmStarItemClickListener(OnStarItemClickListener mStarItemClickListener) {
        this.mStarItemClickListener = mStarItemClickListener;
    }

    /**
     * 设置是否可点击
     */
    public void setIsClick(boolean isItemClick){
        this.isItemClick = isItemClick;
    }


    /**
     * 星星点击事件
     */
    public interface OnStarItemClickListener {
        void onItemClick(int pos);
    }


}
