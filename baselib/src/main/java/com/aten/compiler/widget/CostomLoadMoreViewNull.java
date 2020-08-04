package com.aten.compiler.widget;

import com.aten.compiler.R;
import com.chad.library.adapter.base.loadmore.LoadMoreView;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.aten.compiler.widget
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/11/7 0007
 * 描    述：
 * ================================================
 */
public class CostomLoadMoreViewNull extends LoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.layout_custom_quick_view_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}