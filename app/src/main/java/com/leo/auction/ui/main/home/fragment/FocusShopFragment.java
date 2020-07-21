package com.leo.auction.ui.main.home.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseRecyclerView.BaseRecyclerViewFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.home.activity.ShopActivity;
import com.leo.auction.ui.main.sort.SortShopAdapter;
import com.leo.auction.ui.main.sort.SortShopModel;

import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 *
 *
 * 首页---关注  ----店铺
 */
public class FocusShopFragment extends BaseRecyclerViewFragment {


    public FocusShopFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_focus_shop;
    }

    @Override
    protected void initAdapter() {
        super.initAdapter();
        mAdapter = new SortShopAdapter();


        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                SortShopModel.DataBean json = ( SortShopModel.DataBean )mAdapter.getData().get(i);
                Bundle bundle = new Bundle();
                bundle.putString("shopUri",json.getProductUser().getUserId());
                bundle.putString("shopName",json.getProductUser().getNickname());
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
    protected void getData() {
        super.getData();

        showWaitDialog();
        SortShopModel.httpSort("", mPageNum + "", new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                SortShopModel sortShopModel = JSONObject.parseObject(resultData, SortShopModel.class);
                if (mPageNum == 1) {
                    mAdapter.setNewData(sortShopModel.getData());
                } else {
                    mAdapter.addData(sortShopModel.getData());
                    mAdapter.loadMoreComplete();
                }

                if (sortShopModel.getData().isEmpty()) {
                    mPageNum = 0;
                } else if (mAdapter.getData().size() > Constants.Var.LIST_NUMBER_INT) {
                    mAdapter.loadMoreEnd(true);
                } else {
                    mAdapter.loadMoreEnd();
                }
            }
        });

    }


}
