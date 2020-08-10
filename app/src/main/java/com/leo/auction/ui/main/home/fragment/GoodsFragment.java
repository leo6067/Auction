package com.leo.auction.ui.main.home.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseRecyclerView.BaseRecyclerViewFragment;
import com.aten.compiler.utils.BroadCastReceiveUtils;
import com.aten.compiler.utils.ScreenUtils;
import com.aten.compiler.widget.CustRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.home.activity.AuctionDetailActivity;
import com.leo.auction.ui.main.home.adapter.HomeSearchAdapter;
import com.leo.auction.ui.main.home.model.HomeListModel;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoodsFragment extends BaseRecyclerViewFragment {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    CustRefreshLayout mRefreshLayout;
    @BindView(R.id.radio_a)
    RadioButton mRadioA;
    @BindView(R.id.radio_b)
    RadioButton mRadioB;
    @BindView(R.id.radio_c)
    RadioButton mRadioC;
    @BindView(R.id.radio_group)
    RadioGroup mRadioGroup;

    @BindView(R.id.iv_to_top)
    ImageView mIvToTop;

    private int totalDy = 0;


    private String mUrl = "";
    private String keyWord = "";

    private int listType = 0;

    BroadCastReceiveUtils mReceiveUtils = new BroadCastReceiveUtils() {
        @Override
        public void onReceive(Context context, Intent intent) {
            keyWord = intent.getStringExtra("value");
            onRefresh(refreshLayout);
        }
    };


    public GoodsFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_goods;
    }


    @Override
    public void initData() {
        super.initData();
        BroadCastReceiveUtils.registerLocalReceiver(getActivity(), Constants.Action.ACTION_HOME_SEARCH, mReceiveUtils);

        mRadioA.setChecked(true);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == mRadioA.getId()) {
                    listType = 0;
                } else if (checkedId == mRadioB.getId()) {
                    listType = 1;
                } else if (checkedId == mRadioC.getId()) {
                    listType = 2;
                }
                onRefresh(refreshLayout);
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
    public void onResume() {
        super.onResume();
        onRefresh(refreshLayout);
        BroadCastReceiveUtils.registerLocalReceiver(getActivity(), Constants.Action.ACTION_HOME_SEARCH, mReceiveUtils);
    }



    @Override
    protected void initAdapter() {
        super.initAdapter();

        DisplayMetrics dm = getResources().getDisplayMetrics();
        mAdapter = new HomeSearchAdapter(dm.widthPixels - ((int) getResources().getDimension(R.dimen.dp_10)) * 2);
        mAdapter.setHeaderAndEmpty(true);


        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeListModel.DataBean json = (HomeListModel.DataBean) mAdapter.getData().get(position);
                Bundle bundle = new Bundle();
                bundle.putString("goodsCode", json.getProductInstanceCode());
                ActivityManager.JumpActivity(getActivity(), AuctionDetailActivity.class, bundle);
            }
        });
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        GridLayoutManager staggeredGridLayoutManager = new GridLayoutManager(getActivity(), 2);
        return staggeredGridLayoutManager;
    }

    @Override
    public void getData() {
        super.getData();

        if (listType == 0) {
            mUrl = Constants.Api.HOME_SEARCH_MU_URL;
        } else if (listType == 1) {
            mUrl = Constants.Api.HOME_SEARCH_NEW_URL;
        } else {
            mUrl = Constants.Api.HOME_SEARCH_INT_URL;
        }

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("keyword", keyWord);
        hashMap.put("pageNum", String.valueOf(mPageNum));
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
                } else  {
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        BroadCastReceiveUtils.unregisterLocalReceiver(getActivity(), mReceiveUtils);
    }

}
