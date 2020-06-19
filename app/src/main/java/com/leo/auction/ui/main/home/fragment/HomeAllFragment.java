package com.leo.auction.ui.main.home.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseRecyclerView.BaseRecyclerViewFragment;
import com.aten.compiler.base.BaseRecyclerView.SpaceItemDecoration;
import com.aten.compiler.utils.BroadCastReceiveUtils;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.widget.banner.listener.OnBannerClickListener;
import com.leo.auction.R;
import com.leo.auction.base.Constants;
import com.leo.auction.net.CustomerJsonCallBack;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.login.model.UserInfoModel;
import com.leo.auction.ui.main.home.adapter.HomeAdapter;
import com.leo.auction.ui.main.home.adapter.HomeBAdapter;
import com.leo.auction.ui.main.home.model.HomeListModel;
import com.leo.auction.utils.Globals;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */

public class HomeAllFragment extends BaseRecyclerViewFragment {

    //Constants.Var.HOME_TYPE 0: "全部", 1:  "一元拍", "捡漏", "最新发布", "即将截拍"

    private String mUrl;

    BroadCastReceiveUtils mBroadCastReceiveUtils = new BroadCastReceiveUtils() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mPageNum = 1;
            Globals.log("xxxxxxxxxxxxx  mBroadCastReceiveUtils"   );
            onRefresh(refreshLayout);
        }
    };

    public HomeAllFragment() {
    }




    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_all;
    }




    @Override
    public void onResume() {
        super.onResume();
        mPageNum = 1;
        BroadCastReceiveUtils.registerLocalReceiver(getActivity(),Constants.Action.SEND_REFRESH_HOME_ALL,mBroadCastReceiveUtils);
        Globals.log("xxxxxxxxxxxxx  onResume"   );
        onRefresh(refreshLayout);

    }

    @Override
    public void initData() {
        UserInfoModel userInfoModel = LitePal.findFirst(UserInfoModel.class);
        super.initData();
        recyclerView.addItemDecoration(new SpaceItemDecoration((int) getResources().getDimension(R.dimen.dp_20), 2));
    }

    @Override
    protected void initAdapter() {

        DisplayMetrics dm = getResources().getDisplayMetrics();
        mAdapter = new HomeAdapter(dm.widthPixels - ((int)getResources().getDimension(R.dimen.dp_20)) * 4);
        mAdapter.setHeaderAndEmpty(true);
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        mAdapter.setHasStableIds(true);
    }
    @Override
    public RecyclerView.LayoutManager getLayoutManager() {

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        return staggeredGridLayoutManager;
    }

    @Override
    protected void getData() {
        super.getData();

        HashMap<String, String> hashMap = new HashMap<>();

        int homeType = Constants.Var.HOME_TYPE;

        if (homeType == 0) {
            mUrl = Constants.Api.HOME_ALL_URL;
        } else if (homeType == 1) {
            mUrl = Constants.Api.HOME_UNITARY_URL;
        } else if (homeType == 2) {
            mUrl = Constants.Api.HOME_PICK_URL;
        } else if (homeType == 3) {
            mUrl = Constants.Api.HOME_NEWEST_URL;
        } else if (homeType == 4) {
            mUrl = Constants.Api.HOME_ABOUT_URL;
        }

        hashMap.put("keyword", "");
        hashMap.put("pageNum", "" + mPageNum);
        hashMap.put("pageSize", "20");

        HttpRequest.httpGetString(mUrl, hashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideRefreshView();
            }

            @Override
            public void httpResponse(String resultData) {
                HomeListModel homeListModel = JSONObject.parseObject(resultData, HomeListModel.class);
                hideRefreshView();
                if (mPageNum == 1) {
                    mAdapter.setNewData(homeListModel.getData());
                } else {
                    mAdapter.addData(homeListModel.getData());
                    mAdapter.loadMoreComplete();
                }

                if (homeListModel.getData().isEmpty()) {
                    mPageNum = 0;
                } else if (mAdapter.getData().size() < 20) {
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
        BroadCastReceiveUtils.unregisterLocalReceiver(getActivity(),mBroadCastReceiveUtils);
    }
}
