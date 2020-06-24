package com.leo.auction.ui.main.home.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseRecyclerView.BaseRecyclerViewFragment;
import com.aten.compiler.base.BaseRecyclerView.SpaceItemDecoration;
import com.aten.compiler.utils.BroadCastReceiveUtils;
import com.aten.compiler.widget.CustRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.home.activity.AuctionDetailActivity;
import com.leo.auction.ui.main.home.adapter.HomeAdapter;
import com.leo.auction.ui.main.home.model.HomeListModel;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
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
                if (checkedId == mRadioA.getId()){
                    listType = 0;
                }else if(checkedId == mRadioB.getId()){
                    listType = 1;
                }else if(checkedId == mRadioC.getId()){
                    listType = 2;
                }
                onRefresh(refreshLayout);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh(refreshLayout);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //可见
            onRefresh(refreshLayout);
        } else {
            //不可见
        }
    }

    @Override
    protected void initAdapter() {
        super.initAdapter();
        recyclerView.addItemDecoration(new SpaceItemDecoration((int) getResources().getDimension(R.dimen.dp_20), 2));
        DisplayMetrics dm = getResources().getDisplayMetrics();
        mAdapter = new HomeAdapter(dm.widthPixels - ((int) getResources().getDimension(R.dimen.dp_20)) * 4);
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
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        return staggeredGridLayoutManager;
    }

    @Override
    protected void getData() {
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


                if (mPageNum > 1 && homeListModel.getData().isEmpty()) {
                    if (mAdapter.getData().size() < 10) {
                        mAdapter.loadMoreEnd(true);
                    } else {
                        mAdapter.loadMoreEnd();
                    }
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
