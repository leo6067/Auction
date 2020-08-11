package com.leo.auction.ui.main.home.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseRecyclerView.BaseRecyclerViewFragment;
import com.aten.compiler.base.BaseRecyclerView.StaggeredDividerItemDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.Constants;
import com.leo.auction.common.widget.LinearLayoutDivider;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.home.activity.ShopActivity;
import com.leo.auction.ui.main.home.adapter.HomeAdapter;
import com.leo.auction.ui.main.home.adapter.HomeHotAdapter;
import com.leo.auction.ui.main.home.model.HotModel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


public class HomeHotFragment extends BaseRecyclerViewFragment {


    public HomeHotFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_hot;
    }


    @Override
    public void initAdapter() {
        recyclerView.addItemDecoration(new LinearLayoutDivider(getActivity(),10,getActivity().getResources().getColor(R.color.color_f2f2f2)));
        mAdapter = new HomeHotAdapter();
        mAdapter.setHasStableIds(true);


        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
              List<HotModel.DataBean> arrayList = mAdapter.getData();
                Bundle bundle = new Bundle();

                bundle.putString("shopUri",arrayList.get(position).getShopUri());
                bundle.putString("shopName",arrayList.get(position).getNickname());
                ActivityManager.JumpActivity(getActivity(), ShopActivity.class,bundle);

            }
        });
    }


    @Override
    public void initData() {
        super.initData();
        onRefresh(refreshLayout);
    }


    @Override
    public void getData() {
        super.getData();


        HotModel.httpGetHot(mPageNum, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {
                HotModel hotModel = JSONObject.parseObject(resultData, HotModel.class);

                if (mPageNum == 1) {
                    mAdapter.setNewData(hotModel.getData());
                } else {
                    mAdapter.addData(hotModel.getData());
                    mAdapter.loadMoreComplete();
                }

                if (hotModel.getData().isEmpty()) {
                    mPageNum = 1;
                } else if (hotModel.getData().size() > Constants.Var.LIST_NUMBER_INT) {
                    mAdapter.loadMoreEnd(true);
                } else {
                    mAdapter.loadMoreEnd();
                }

            }
        });

    }
}
