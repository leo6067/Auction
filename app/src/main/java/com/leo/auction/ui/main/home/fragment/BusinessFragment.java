package com.leo.auction.ui.main.home.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseRecyclerView.BaseRecyclerViewFragment;
import com.aten.compiler.utils.BroadCastReceiveUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.home.activity.ShopActivity;
import com.leo.auction.ui.main.home.adapter.BusinessAdapter;
import com.leo.auction.ui.main.home.model.SupplierModel;
import com.leo.auction.utils.Globals;

import java.util.HashMap;

import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusinessFragment extends BaseRecyclerViewFragment {


    private String keyWord = "";

    BroadCastReceiveUtils mReceiveUtils = new BroadCastReceiveUtils() {
        @Override
        public void onReceive(Context context, Intent intent) {
            keyWord = intent.getStringExtra("value");
            onRefresh(refreshLayout);
        }
    };


    public BusinessFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_business;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
// TODO Auto-generated method stub
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            onRefresh(refreshLayout);
            BroadCastReceiveUtils.registerLocalReceiver(getActivity(), Constants.Action.ACTION_HOME_SEARCH, mReceiveUtils);
        }
    }


    @Override
    protected void initAdapter() {
        super.initAdapter();
        mAdapter = new BusinessAdapter();

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SupplierModel.DataBean dataBean = (SupplierModel.DataBean) mAdapter.getData().get(position);
                Bundle bundle = new Bundle();
                bundle.putString("shopUri", dataBean.getProductUser().getUserId());
                bundle.putString("shopName", dataBean.getProductUser().getNickname());
                ActivityManager.JumpActivity(getActivity(), ShopActivity.class, bundle);
            }
        });
    }


    @Override
    public void getData() {
        super.getData();

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("keyword", keyWord);
        hashMap.put("pageNum", String.valueOf(mPageNum));
        hashMap.put("pageSize", Constants.Var.LIST_NUMBER);
        showWaitDialog();
        HttpRequest.httpGetString(Constants.Api.HOME_SEARCH_SUPPLIER_URL, hashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                SupplierModel supplierModel = JSONObject.parseObject(resultData, SupplierModel.class);
                if (mPageNum == 1) {
                    mAdapter.setNewData(supplierModel.getData());
                } else {
                    mAdapter.addData(supplierModel.getData());
                    mAdapter.loadMoreComplete();
                }


                if (supplierModel.getData().isEmpty()) {
                    mPageNum = 1;
                } else if (mAdapter.getData().size() > Constants.Var.LIST_NUMBER_INT) {
                    mAdapter.loadMoreEnd(true);
                } else {
                    mAdapter.loadMoreEnd();
                }

            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        BroadCastReceiveUtils.unregisterLocalReceiver(getActivity(), mReceiveUtils);
    }
}
