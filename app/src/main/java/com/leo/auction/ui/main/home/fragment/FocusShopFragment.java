package com.leo.auction.ui.main.home.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseRecyclerView.BaseRecyclerViewFragment;
import com.aten.compiler.utils.BroadCastReceiveUtils;
import com.aten.compiler.utils.ScreenUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.home.activity.ShopActivity;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.ui.main.sort.SortShopAdapter;
import com.leo.auction.ui.main.sort.SortShopModel;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 * <p>
 * <p>
 * 首页---关注  ----店铺
 */
public class FocusShopFragment extends BaseRecyclerViewFragment {

    @BindView(R.id.iv_to_top)
    ImageView mIvToTop;

    private int totalDy = 0;

    public FocusShopFragment() {
        // Required empty public constructor
    }



    BroadCastReceiveUtils mBroadCastReceiveUtils = new BroadCastReceiveUtils() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mPageNum = 1;

            onRefresh(refreshLayout);
        }
    };


    @Override
    public int getLayoutId() {
        return R.layout.fragment_focus_shop;
    }



    @Override
    protected void initAdapter() {
        super.initAdapter();
        mAdapter = new SortShopAdapter(new SortShopAdapter.SortOnListener() {
            @Override
            public void soreOnListener() {
                onRefresh(refreshLayout);
            }

            @Override
            public void soreItemListener(int layoutPosition) {
                SortShopModel.DataBean json = (SortShopModel.DataBean) mAdapter.getData().get(layoutPosition);
                Bundle bundle = new Bundle();
                bundle.putString("shopUri", json.getProductUser().getUserId());
                bundle.putString("shopName", json.getProductUser().getNickname());
                ActivityManager.JumpActivity(getActivity(), ShopActivity.class, bundle);
            }

            @Override
            public void soreCancel() {
                onRefresh(refreshLayout);
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                SortShopModel.DataBean json = (SortShopModel.DataBean) mAdapter.getData().get(i);
                Bundle bundle = new Bundle();
                bundle.putString("shopUri", json.getProductUser().getUserId());
                bundle.putString("shopName", json.getProductUser().getNickname());
                ActivityManager.JumpActivity(getActivity(), ShopActivity.class, bundle);


            }
        });

    }


    @Override
    public void initEvent() {
        super.initEvent();
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
        BroadCastReceiveUtils.registerLocalReceiver(getActivity(), Constants.Action.ACTION_FOCUS_TYPE, mBroadCastReceiveUtils);
    }


    @Override
    public void initData() {
        super.initData();

    }

    @Override
    public void getData() {
        super.getData();


        int focusType = Constants.Var.FOCUS_TYPE;  //防止预加载
        if (focusType == -1) {
            return;
        }


        UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();
        if (userJson == null) {
            return;
        }


        SortShopModel.httpSort("", mPageNum + "", new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {

                SortShopModel sortShopModel = JSONObject.parseObject(resultData, SortShopModel.class);
                if (mPageNum == 1) {
                    mAdapter.setNewData(sortShopModel.getData());
                } else {
                    mAdapter.addData(sortShopModel.getData());
                    mAdapter.loadMoreComplete();
                }

                if (sortShopModel.getData().isEmpty()) {
                    mPageNum = 1;
                } else if (sortShopModel.getData().size() > Constants.Var.LIST_NUMBER_INT) {
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


    @Override
    public void onDestroy() {
        super.onDestroy();
        BroadCastReceiveUtils.unregisterLocalReceiver(getActivity(),mBroadCastReceiveUtils);
    }
}
