package com.leo.auction.utils;

import android.view.View;
import android.widget.ListView;

/**
 * Created by qwe on 2017/5/2.
 *
 * 用于页面暂无数据显示 默认布局
 * 用于listview 暂无数据
 *
 *
 */

public class ViewDataUtils {

    public static void noListData(ListView mList, View view){
        if (mList != null && mList.getChildCount()==0){
            mList.setVisibility(View.GONE);
            view.setVisibility(View.VISIBLE);
        }else if (mList != null){
            mList.setVisibility(View.VISIBLE);
            view.setVisibility(View.GONE);
        }
    }



    public static void noData(View rootView, View noData){
        rootView.setVisibility(View.GONE);
        noData.setVisibility(View.VISIBLE);

    }

}
