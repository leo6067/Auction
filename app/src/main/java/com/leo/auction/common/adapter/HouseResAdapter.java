package com.leo.auction.common.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.leo.auction.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by Leo on 2018/7/11.
 *
 * 个人房源
 */

public class HouseResAdapter extends CommonAdapter<String> {


    private Context mContext;

    public HouseResAdapter(Context context, List<String> datas) {
        super(context, R.layout.item_house_res, datas);
        mContext = context;
    }

    @Override
    protected void convert(ViewHolder holder, String json, int position) {

        ImageView  mResNew = (ImageView) holder.getView(R.id.res_new);
        TextView mResTitle = (TextView) holder.getView(R.id.res_title);
        TextView mResImgs = (TextView) holder.getView(R.id.res_imgs);
        TextView mResInfo = (TextView) holder.getView(R.id.res_info);
        TextView mResAddress = (TextView) holder.getView(R.id.res_address);
        TextView mResTime = (TextView) holder.getView(R.id.res_time);
        TextView mResMoney = (TextView) holder.getView(R.id.res_money);

        mResTitle.setText(json);


    }
}
