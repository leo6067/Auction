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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseRecyclerView.BaseRecyclerViewFragment;
import com.aten.compiler.base.BaseRecyclerView.SpaceItemDecoration;
import com.aten.compiler.base.BaseRecyclerView.StaggeredDividerItemDecoration;
import com.aten.compiler.utils.BroadCastReceiveUtils;
import com.aten.compiler.utils.ScreenUtils;
import com.aten.compiler.widget.CustRefreshLayout;
import com.aten.compiler.widget.safeKeyBoard.utils.DisplayUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.login.LoginActivity;

import com.leo.auction.ui.main.home.activity.AuctionDetailActivity;
import com.leo.auction.ui.main.home.adapter.HomeAdapter;
import com.leo.auction.ui.main.home.adapter.HomeTitleAdapter;
import com.leo.auction.ui.main.home.model.HomeListModel;
import com.leo.auction.ui.main.home.model.SubsidyModel;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.ui.web.AgentWebActivity;
import com.leo.auction.utils.Globals;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 * <p>
 * 百亿补贴
 */

public class HomeBYFragment extends BaseRecyclerViewFragment {


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
    private View mInflate;


    public HomeBYFragment() {
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_all;
    }


    @Override
    public void initData() {
        super.initData();

        onRefresh(refreshLayout);
        BroadCastReceiveUtils.registerLocalReceiver(getActivity(), Constants.Action.ACTION_HOME_TYPE, mBroadCastReceiveUtils);
        Constants.Action.ACTION_ACTION = "1";
    }


    @Override
    public void initAdapter() {


        recyclerView.addItemDecoration(new StaggeredDividerItemDecoration(getActivity(),(int) getResources().getDimension(R.dimen.dp_15)));
        DisplayMetrics dm = getResources().getDisplayMetrics();


        mAdapter = new HomeAdapter(dm.widthPixels );
        mAdapter.setHeaderAndEmpty(true);
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        mAdapter.setHasStableIds(true);
        initHeadView();

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


    public void initHeadView(){

        //百亿补贴
        mInflate = LayoutInflater.from(getActivity()).inflate(R.layout.include_home_title, null);
        mAdapter.addHeaderView(mInflate);

        RecyclerView mTitleRecyclerView = mInflate.findViewById(R.id.title_recyclerView);
        mTitleHint = mInflate.findViewById(R.id.home_title_hint);
        mTitlePrice = mInflate.findViewById(R.id.home_title_price);
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

        mTitleHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();
                if (userJson == null) {
                    LoginActivity.newIntance(getActivity(), 0);
                    return;
                }

                UserModel.httpUpdateUser(new HttpRequest.HttpCallback() {
                    @Override
                    public void httpError(Call call, Exception e) {

                    }

                    @Override
                    public void httpResponse(String resultData) {
                        UserModel userModel = JSONObject.parseObject(resultData, UserModel.class);
                        BaseSharePerence.getInstance().setUserJson(JSON.toJSONString(userModel.getData()));
                        Bundle bundle = new Bundle();
                        bundle.putString("title", "TOP百亿补贴");
                        bundle.putString("url", Constants.WebApi.HOMEPAGE_SUBSIDY_URL + userModel.getData().getNestedToken());
                        ActivityManager.JumpActivity(getActivity(), AgentWebActivity.class, bundle);
                    }
                });
            }
        });
    }

    @Override
    public void initEvent() {
        super.initEvent();
        setSmartHasRefreshOrLoadMore();
        setLoadMore();



    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        return staggeredGridLayoutManager;
    }

    @Override
    public void getData() {
        if (refreshLayout !=null){
           Globals.log("xxxxxx","refresh 00");
            refreshLayout.finishRefresh(800);
        }

        HashMap<String, String> hashMap = new HashMap<>();
        String mUrl = Constants.Api.HOME_SUBSIDY_URL;
        hashMap.put("keyword", "");
        hashMap.put("pageNum", "" + mPageNum);
        hashMap.put("pageSize", Constants.Var.LIST_NUMBER);

        HttpRequest.httpGetString(mUrl, hashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
            }

            @Override
            public void httpResponse(String resultData) {
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


        //百亿补贴列表
        SubsidyModel.httpGetSubsily(new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {
                SubsidyModel subsidyModel = JSONObject.parseObject(resultData, SubsidyModel.class);
                if (subsidyModel.getData().size() < 4) {
                    mInflate.setVisibility(View.GONE);  //隐藏百亿补贴
                    return;
                }
                //百亿补贴金额
                httpTitlePrice();
                mHomeTitleAdapter.setNewData(subsidyModel.getData());
            }
        });


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
