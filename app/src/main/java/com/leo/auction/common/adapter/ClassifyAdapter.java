package com.leo.auction.common.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leo.auction.R;
import com.leo.auction.model.home.HomeIconInfo;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;


/**
 * Created by qwe on 2017/12/18.
 */

public class ClassifyAdapter extends CommonAdapter<HomeIconInfo> {

    private Context mContext;

    public ClassifyAdapter(Context context, List<HomeIconInfo> datas) {
        super(context, R.layout.item_classify, datas);
        mContext = context;
    }

    @Override
    protected void convert(ViewHolder holder, HomeIconInfo json, int position) {

        LinearLayout linearLayout = holder.getView(R.id.home_item_bg);
        TextView iconTv = holder.getView(R.id.tv_classify_name);

        ImageView iconImg = holder.getView(R.id.tv_classify_img);

        iconTv.setText(json.getIconStr());
        iconImg.setBackgroundResource(json.getIconImg());
    }
}
