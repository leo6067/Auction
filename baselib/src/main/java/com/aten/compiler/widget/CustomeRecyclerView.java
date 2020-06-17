package com.aten.compiler.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.aten.compiler.widget
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/10/29 0029
 * 描    述：自定义RecyclerView 不能滑动
 * ================================================
 */
public class CustomeRecyclerView extends RecyclerView {
    public CustomeRecyclerView(@NonNull Context context) {
        super(context);
    }

    public CustomeRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomeRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean canScrollHorizontally(int direction) {
        return false;
    }

    @Override
    public boolean canScrollVertically(int direction) {
        return false;
    }
}
