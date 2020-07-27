package com.leo.auction.ui.main.home.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseRecyclerView.BaseRecyclerViewFragment;
import com.aten.compiler.base.BaseRecyclerView.SpaceItemDecoration;
import com.aten.compiler.utils.BroadCastReceiveUtils;
import com.aten.compiler.utils.ScreenUtils;
import com.aten.compiler.widget.CustRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;

import com.leo.auction.ui.main.home.activity.AuctionDetailActivity;
import com.leo.auction.ui.main.home.adapter.HomeAdapter;

import com.leo.auction.ui.main.home.adapter.HomeSearchAdapter;
import com.leo.auction.ui.main.home.model.HomeListModel;
import com.leo.auction.utils.Globals;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopAllFragment extends BaseRecyclerViewFragment {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    CustRefreshLayout mRefreshLayout;
    @BindView(R.id.iv_to_top)
    ImageView mIvToTop;

    private int totalDy = 0;
    //Constants.Var.HOME_TYPE -0 百亿补贴  1:  "一元拍", "捡漏", "最新发布", "即将截拍"


    BroadCastReceiveUtils mBroadCastReceiveUtils = new BroadCastReceiveUtils() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String value = intent.getStringExtra("value");
            onRefresh(refreshLayout);
        }
    };


    public ShopAllFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop_all;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        enableLazyLoad();
    }


//
//    @Override
//    public void onResume() {
//        super.onResume();
//        onRefresh(refreshLayout);
//    }
//
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser) {
//            //可见
//            onRefresh(refreshLayout);
//        } else {
//            //不可见
//        }
//    }


    @Override
    public void initData() {
        super.initData();
        if (Constants.Var.SHOP_TYPE == 0) {
            onRefresh(refreshLayout);
        }

        BroadCastReceiveUtils.registerLocalReceiver(getActivity(), Constants.Action.ACTION_SHOP_TYPE, mBroadCastReceiveUtils);

    }

    @Override
    protected void initAdapter() {


        DisplayMetrics dm = getResources().getDisplayMetrics();
        mAdapter = new HomeSearchAdapter(dm.widthPixels - ((int) getResources().getDimension(R.dimen.dp_10)) * 2);
        mAdapter.setHeaderAndEmpty(true);
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        mAdapter.setHasStableIds(true);


        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                Globals.log("XXXXXX LEO  LOG" + position);
                HomeListModel.DataBean json = (HomeListModel.DataBean) mAdapter.getData().get(position);
                Bundle bundle = new Bundle();
                bundle.putString("goodsCode", json.getProductInstanceCode());
                ActivityManager.JumpActivity(getActivity(), AuctionDetailActivity.class, bundle);
                BroadCastReceiveUtils.sendLocalBroadCast(getActivity(),Constants.Action.ACTION_DETAIL_REFRESH);
            }
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {


            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalDy += dy;
                if (totalDy <= ScreenUtils.getScreenHeight()) {
                    mIvToTop.setVisibility(View.GONE);
                } else {
                    mIvToTop.setVisibility(View.VISIBLE);
                }
            }
        });


        //百亿补贴
//        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.include_home_title, null);
//        mAdapter.addHeaderView(inflate);
//
//        RecyclerView mTitleRecyclerView = inflate.findViewById(R.id.title_recyclerView);
//        mTitleHint = inflate.findViewById(R.id.home_title_hint);
//        mTitlePrice = inflate.findViewById(R.id.home_title_price);
//        mTitleRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
//        mHomeTitleAdapter = new HomeTitleAdapter();
//        mTitleRecyclerView.setAdapter(mHomeTitleAdapter);
//
//
//        mHomeTitleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
//                SubsidyModel.DataBean item = (SubsidyModel.DataBean) mHomeTitleAdapter.getData().get(position);
//                Bundle bundle = new Bundle();
//
//                bundle.putString("goodsCode", item.getProductInstanceCode());
//
//                ActivityManager.JumpActivity(getActivity(), AuctionDetailActivity.class, bundle);
//            }
//        });
//
//
//        mTitleHint.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), WebViewActivity.class);
//                intent.putExtra("title", "TOP百亿补贴");
//                intent.putExtra("url", Constants.WebApi.HOMEPAGE_SUBSIDY_URL);
//                intent.putExtra("hasNeedTitleBar", true);
//                intent.putExtra("hasNeedRightView", false);
//                intent.putExtra("hasNeedLeftView", true);
//                startActivity(intent);
//            }
//        });


    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        GridLayoutManager staggeredGridLayoutManager = new GridLayoutManager(getActivity(), 2);
        return staggeredGridLayoutManager;
    }

    @Override
    protected void getData() {
        super.getData();

        String shopUri = getArguments().getString("shopUri", "");

        HashMap<String, String> hashMap = new HashMap<>();
        String mUrl = "";
        int shopType = Constants.Var.SHOP_TYPE;

        if (shopType == 0) {  //店铺---热门
            mUrl = Constants.Api.SHOP_HOT_URL;
        } else if (shopType == 1) {
            mUrl = Constants.Api.SHOP_PICK_URL;
        } else if (shopType == 2) {//最新发布
            mUrl = Constants.Api.SHOP_NEWEST_URL;
        } else if (shopType == 3) {//
            mUrl = Constants.Api.SHOP_INTERCEPT_URL;
        } else {
            return;
        }

        hashMap.put("shopUri", shopUri);
        hashMap.put("pageNum", "" + mPageNum);
        hashMap.put("pageSize", Constants.Var.LIST_NUMBER);

        showWaitDialog();
        HttpRequest.httpGetString(mUrl, hashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                HomeListModel homeListModel = JSONObject.parseObject(resultData, HomeListModel.class);

                if (mPageNum == 1) {
                    mAdapter.setNewData(homeListModel.getData());
                } else {
                    mAdapter.addData(homeListModel.getData());
                    mAdapter.loadMoreComplete();
                }

                if (homeListModel.getData().isEmpty()) {
                    mPageNum = 0;
                } else if (homeListModel.getData().size() > Constants.Var.LIST_NUMBER_INT) {
                    mAdapter.loadMoreEnd(true);
                } else {
                    mAdapter.loadMoreEnd();
                }

            }
        });


    }


    @OnClick(R.id.iv_to_top)
    public void onViewClicked() {
        //平滑滚动
        totalDy = 0;
        recyclerView.scrollToPosition(0);
    }

    public static ShopAllFragment newIntance(String shopUri) {  //角度  1-买家角度(默认1)   2-卖家角度
        ShopAllFragment shopAllFragment = new ShopAllFragment();
        Bundle bundle = new Bundle();
        bundle.putString("shopUri", shopUri);
        shopAllFragment.setArguments(bundle);
        return shopAllFragment;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        BroadCastReceiveUtils.unregisterLocalReceiver(getActivity(),mBroadCastReceiveUtils);
    }
}
