package com.leo.auction.common.adapter;

import android.content.Context;

import com.leo.auction.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by Leo on 2018/7/12.
 *
 * 云采集 不限
 */

public class CollectGatherAdapter extends CommonAdapter<String> {
    public CollectGatherAdapter(Context context, List<String> datas) {
        super(context, R.layout.item_collect_collect, datas);
    }

    @Override
    protected void convert(ViewHolder holder, String s, int position) {

    }


}
