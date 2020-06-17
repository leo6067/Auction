package com.aten.compiler.base.BaseRecyclerView;

import com.aten.compiler.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


/**
 * project:PJHAndroidFrame
 * package:com.frame.pjh_core.base.BaseRecyclerView
 * Created by 彭俊鸿 on 2018/6/4.
 * e-mail : 1031028399@qq.com
 * base类中adapter的替代品 没有任何功能 需要自己重新实现adapter继承BaseQuickAdapter
 */

public class BaseAdapterRecyclerview extends BaseQuickAdapter<Object,BaseViewHolder>  {

    public BaseAdapterRecyclerview() {
        super(R.layout.layout_empty_base_recyclerview, null);
    }


    @Override
    protected void convert(BaseViewHolder helper, Object item) {

    }
}
