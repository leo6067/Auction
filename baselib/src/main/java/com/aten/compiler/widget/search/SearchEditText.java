package com.aten.compiler.widget.search;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.aten.compiler.R;


/**
 * Created by Leo on 2017/10/31.
 */

public class SearchEditText extends AppCompatEditText implements View.OnFocusChangeListener, TextWatcher {


    private Drawable clearDrawable;
    private Drawable searchDrawable;
    private boolean hasFocus;

    public SearchEditText(Context context) {
        this(context, null);
    }

    public SearchEditText(Context context, AttributeSet attrs) {
// 这里构造方法也很重要，不加这个很多属性不能再XML里面定义
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public SearchEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    //初始化刪除按鈕
    public void init() {
        searchDrawable = getCompoundDrawables()[0];//left top right bottom 所以right的索引為2
        clearDrawable = getCompoundDrawables()[2];//left top right bottom 所以right的索引為2
        if (clearDrawable == null) {
            clearDrawable = getResources().getDrawable(R.drawable.clear);
        }


        if (searchDrawable == null) {
            searchDrawable = getResources().getDrawable(R.drawable.search);
        }


//設置圖標的寬高
        searchDrawable.setBounds(0, 0, searchDrawable.getIntrinsicWidth(), searchDrawable.getIntrinsicHeight());
        clearDrawable.setBounds(0, 0, clearDrawable.getIntrinsicWidth(), clearDrawable.getIntrinsicHeight());
        setSearchVisible(true);
        setClearIconVisible(false);//設置圖標是否可見
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    //設置圖標是否可見
    public void setSearchVisible(boolean visible) {
        Drawable left = visible ? searchDrawable : null;
        setCompoundDrawables(left, getCompoundDrawables()[1], getCompoundDrawables()[2], getCompoundDrawables()[3]);


    }


    //設置圖標是否可見
    public void setClearIconVisible(boolean visible) {
        Drawable right = visible ? clearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);


    }

    //點擊事件
//圖標的坐標位置範圍在（EditText的寬度-圖標的寬度-右邊距,EditText的寬度-右邊距）之間
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                if (event.getX() > (getWidth() - getTotalPaddingRight()) && event.getX() < (getWidth() - getPaddingRight())) {
                    this.setText("");
                    if (onClearListener != null) {
                        onClearListener.onClickClear();
                    }
                }
            }
        }
        return super.onTouchEvent(event);
    }

    private OnClearListener onClearListener;

    public interface OnClearListener {
        void onClickClear();
    }

    //监听删除
    public void setOnClearListener(OnClearListener onClearListener) {
        this.onClearListener = onClearListener;
    }

    //文本改變的時候調用
    @Override
    public void onTextChanged(CharSequence text, int start,
                              int lengthBefore, int lengthAfter) {
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        }
    }

    @Override
    public void afterTextChanged(Editable arg0) {
    }

    @Override
    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {
    }

    //獲得焦點的時候調用
    @Override
    public void onFocusChange(View arg0, boolean hasFocus) {
        this.hasFocus = hasFocus;
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);

        } else {
            setClearIconVisible(false);
        }

        if (mOnSearchEdit != null) {
            mOnSearchEdit.onFocusChange(hasFocus);
        }
    }


    private onSearchEdit mOnSearchEdit;


    public interface onSearchEdit {
        void onFocusChange(boolean hasFocus);
    }

    public void setOnSearchEdit(onSearchEdit onSearchEdit) {
        mOnSearchEdit = onSearchEdit;
    }


}
