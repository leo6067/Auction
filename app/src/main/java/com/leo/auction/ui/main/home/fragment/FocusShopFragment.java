package com.leo.auction.ui.main.home.fragment;


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


    private boolean visibleToUser = false;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        visibleToUser = isVisibleToUser;

    }

    @Override
    public void onResume() {
        super.onResume();

        if (visibleToUser) {
            onRefresh(refreshLayout);
        }

    }

    @Override
    public void initData() {
        super.initData();

    }

    @Override
    protected void getData() {
        super.getData();

        UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();
        if (userJson == null) {
            return;
        }

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


}
