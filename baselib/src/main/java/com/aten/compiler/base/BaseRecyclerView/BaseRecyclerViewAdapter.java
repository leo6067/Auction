package com.aten.compiler.base.BaseRecyclerView;

import android.content.Context;

import com.aten.compiler.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;


/**
 *  兼容 zhy
 */

public class BaseRecyclerViewAdapter<D> extends CommonAdapter<Object> {

    public BaseRecyclerViewAdapter(Context context,int layoutId, List<Object> datas) {
        super(context, R.layout.layout_empty_base_recyclerview, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Object o, int position) {

    }


}
