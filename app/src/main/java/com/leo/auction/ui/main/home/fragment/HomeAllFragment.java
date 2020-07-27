package com.leo.auction.ui.main.home.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
import com.aten.compiler.base.BaseWebActivity;
import com.aten.compiler.utils.BroadCastReceiveUtils;
import com.aten.compiler.utils.ScreenUtils;
import com.aten.compiler.widget.CustRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.login.AgreementActivity;
import com.leo.auction.ui.main.WebViewActivity;
import com.leo.auction.ui.main.home.activity.AuctionDetailActivity;
import com.leo.auction.ui.main.home.adapter.HomeAdapter;
import com.leo.auction.ui.main.home.adapter.HomeTitleAdapter;
import com.leo.auction.ui.main.home.model.HomeListModel;
import com.leo.auction.ui.main.home.model.SubsidyModel;
import com.leo.auction.ui.main.mine.model.ProductListModel;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.utils.Globals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */

public class HomeAllFragment extends BaseRecyclerViewFragment {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    CustRefreshLayout mRefreshLayout;
    @BindView(R.id.iv_to_top)
    ImageView mIvToTop;

    private int totalDy = 0;
    //Constants.Var.HOME_TYPE -0 百亿补贴  1:  "一元拍", "捡漏", "最新发布", "即将截拍"


    private ArrayList<HomeListModel.DataBean> mArrayList = new ArrayList<>();


    BroadCastReceiveUtils mBroadCastReceiveUtils = new BroadCastReceiveUtils() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mPageNum = 1;
            onRefresh(refreshLayout);
        }
    };
    private HomeTitleAdapter mHomeTitleAdapter;
    private TextView mTitleHint;
    private TextView mTitlePrice;


    public HomeAllFragment() {
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_all;
    }


    @Override
    public void initData() {
        super.initData();

        if (Constants.Var.HOME_TYPE == 0) {
            onRefresh(refreshLayout);
        }
        BroadCastReceiveUtils.registerLocalReceiver(getActivity(), Constants.Action.ACTION_HOME_TYPE, mBroadCastReceiveUtils);


        Constants.Action.ACTION_ACTION = "1";
    }

    @Override
    protected void initAdapter() {


        recyclerView.addItemDecoration(new SpaceItemDecoration((int) getResources().getDimension(R.dimen.dp_20), 2));
        DisplayMetrics dm = getResources().getDisplayMetrics();
        mAdapter = new HomeAdapter(dm.widthPixels - ((int) getResources().getDimension(R.dimen.dp_10)) * 2);
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


        //百亿补贴
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.include_home_title, null);
        mAdapter.addHeaderView(inflate);

        RecyclerView mTitleRecyclerView = inflate.findViewById(R.id.title_recyclerView);
        mTitleHint = inflate.findViewById(R.id.home_title_hint);
        mTitlePrice = inflate.findViewById(R.id.home_title_price);
        mTitleRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mHomeTitleAdapter = new HomeTitleAdapter();
        mTitleRecyclerView.setAdapter(mHomeTitleAdapter);


        mHomeTitleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                SubsidyModel.DataBean item = (SubsidyModel.DataBean) mHomeTitleAdapter.getData().get(position);
                Bundle bundle = new Bundle();

                bundle.putString("goodsCode", item.getProductInstanceCode());

                ActivityManager.JumpActivity(getActivity(), AuctionDetailActivity.class, bundle);
            }
        });


        UserModel.DataBean mUserJsonn = BaseSharePerence.getInstance().getUserJson();

        mTitleHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("title", "TOP百亿补贴");
                if (mUserJsonn == null) {
                    intent.putExtra("url", Constants.WebApi.HOMEPAGE_SUBSIDY_URL );
                } else {
                    intent.putExtra("url", Constants.WebApi.HOMEPAGE_SUBSIDY_URL+ mUserJsonn.getH5Token());
                }
                intent.putExtra("hasNeedTitleBar", true);
                intent.putExtra("hasNeedRightView", false);
                intent.putExtra("hasNeedLeftView", true);
                startActivity(intent);
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
        String mUrl = "";
        HashMap<String, String> hashMap = new HashMap<>();

        int homeType = Constants.Var.HOME_TYPE;

        if (homeType == 0) {//首页--百亿
            mUrl = Constants.Api.HOME_SUBSIDY_URL;
        } else if (homeType == 1) {//首页--全部
            mUrl = Constants.Api.HOME_UNITARY_URL;
        } else if (homeType == 2) {//首页--全部
            mUrl = Constants.Api.HOME_PICK_URL;
        } else if (homeType == 3) {//首页-- 二手
            mUrl = Constants.Api.HOME_SECOND_URL;
        } else if (homeType == 4) {//首页--万物拍
            mUrl = Constants.Api.HOME_ALL_PRODUCT_URL;
        } else {
            return;
        }




        hashMap.put("keyword", "");
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


        //百亿补贴列表
        SubsidyModel.httpGetSubsily(new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {
                SubsidyModel subsidyModel = JSONObject.parseObject(resultData, SubsidyModel.class);
                mHomeTitleAdapter.setNewData(subsidyModel.getData());

            }
        });


        //百亿补贴金额
        httpTitlePrice();

    }


    public void httpTitlePrice() {

        HashMap<String, String> hashMap = new HashMap<>();

        HttpRequest.httpGetString(Constants.Api.SUBSIDY_LIST_PRICE_URL, hashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {
                JSONObject jsonObject = JSONObject.parseObject(resultData);
                int data = jsonObject.getInteger("data");
                mTitlePrice.setText("￥" + data);
            }
        });

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        BroadCastReceiveUtils.unregisterLocalReceiver(getActivity(), mBroadCastReceiveUtils);
    }


    @OnClick(R.id.iv_to_top)
    public void onViewClicked() {
        //平滑滚动
        totalDy = 0;
        recyclerView.scrollToPosition(0);
    }
}
