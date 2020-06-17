package com.leo.auction.common.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.leo.auction.R;
import com.leo.auction.utils.GlideUtils;

import com.leo.auction.model.GridViewInfo;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;


/**
 * Created by qwe on 2017/12/18.
 */

public class GridAdapter extends CommonAdapter<GridViewInfo> {

    private Context mContext;

    public GridAdapter (Context context, List<GridViewInfo> datas) {
        super(context, R.layout.adapter_grid_item, datas);
        mContext = context;
    }

    @Override
    protected void convert(ViewHolder holder, GridViewInfo json, int position) {

        TextView textView = holder.getView(R.id.tv_classify_name);

        ImageView imageView = holder.getView(R.id.tv_classify_img);


        GlideUtils.loadImg(mContext,json.getTabImg(),imageView);

        textView.setText(json.getTabStr());



    }
}
