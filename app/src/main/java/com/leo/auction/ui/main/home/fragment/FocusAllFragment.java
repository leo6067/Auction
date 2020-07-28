package com.leo.auction.ui.main.home.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
import com.leo.auction.ui.main.home.adapter.FocusAdapter;
import com.leo.auction.ui.main.home.adapter.HomeAdapter;
import com.leo.auction.ui.main.home.model.HomeListModel;
import com.leo.auction.utils.Globals;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class FocusAllFragment extends BaseRecyclerViewFragment {



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
            mPageNum = 1;
            Globals.log("xxxxxxxxxlog  Constants.Var.FOCUS_TYPE  00   " + Constants.Var.FOCUS_TYPE);
            onRefresh(refreshLayout);
        }
    };



    public FocusAllFragment() {
        // Required empty public constructor
    }





    @Override
    public int getLayoutId() {
        return R.layout.fragment_focus_all;
    }




    @Override
    public void initData() {
        super.initData();

        if (Constants.Var.FOCUS_TYPE == 0) {
            onRefresh(refreshLayout);
        }


        BroadCastReceiveUtils.registerLocalReceiver(getActivity(), Constants.Action.ACTION_FOCUS_TYPE, mBroadCastReceiveUtils);
    }

    @Override
    protected void initAdapter() {


        recyclerView.addItemDecoration(new SpaceItemDecoration((int) getResources().getDimension(R.dimen.dp_20), 2));
        DisplayMetrics dm = getResources().getDisplayMetrics();
        mAdapter = new FocusAdapter(dm.widthPixels - ((int) getResources().getDimension(R.dimen.dp_20)) * 4);
        mAdapter.setHeaderAndEmpty(true);
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        mAdapter.setHasStableIds(true);


        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeListModel.DataBean json = (HomeListModel.DataBean) mAdapter.getData().get(position);
                Bundle bundle = new Bundle();
                bundle.putString("goodsCode", json.getProductInstanceCode());
                ActivityManager.JumpActivity(getActivity(), AuctionDetailActivity.class, bundle);
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

    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        return staggeredGridLayoutManager;
    }

    @Override
    protected void getData() {
        super.getData();

        String  mUrl = "";

        HashMap<String, String> hashMap = new HashMap<>();

        int focusType = Constants.Var.FOCUS_TYPE;

        Globals.log("xxxxxxxxxlog  Constants.Var.FOCUS_TYPE" + Constants.Var.FOCUS_TYPE);


        if (focusType == 0) {  //关注--拍品
            mUrl = Constants.Api.SORT_FOLLOW_URL;
            Constants.Action.ACTION_ACTION = "4";
        } else if (focusType == 2) {  //收藏
            mUrl = Constants.Api.SORT_COLLECT_URL;
            Constants.Action.ACTION_ACTION = "7";
        } else if (focusType == 3) {//参拍
            mUrl = Constants.Api.SORT_PARTAKE_URL;
            Constants.Action.ACTION_ACTION = "5";
        } else if (focusType == 4) {//
            mUrl = Constants.Api.SORT_FOOT_PARTAKE_URL;
            Constants.Action.ACTION_ACTION = "6";
        }else {
            return;
        }



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
                    mPageNum = 1;
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

    public static FocusAllFragment newIntance(String shopUri) {  //角度  1-买家角度(默认1)   2-卖家角度
        FocusAllFragment shopAllFragment = new FocusAllFragment();
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
