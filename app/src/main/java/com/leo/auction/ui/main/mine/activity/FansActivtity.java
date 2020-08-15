package com.leo.auction.ui.main.mine.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.widget.CostomLoadMoreView;
import com.aten.compiler.widget.CustRefreshLayout;
import com.aten.compiler.widget.CustomeRecyclerView;
import com.aten.compiler.widget.search.SearchTextView;
import com.aten.compiler.widget.title.TitleBar;
import com.leo.auction.R;
import com.leo.auction.base.Constants;
import com.leo.auction.common.widget.LinearLayoutDivider;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.mine.adapter.FanAdapter;
import com.leo.auction.ui.main.mine.model.FanCountModel;
import com.leo.auction.ui.main.mine.model.FanModel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class FansActivtity extends BaseActivity {


    @BindView(R.id.fan_left)
    TextView mFanLeft;
    @BindView(R.id.fan_left_tv)
    TextView mFanLeftTv;
    @BindView(R.id.fan_right)
    TextView mFanRight;
    @BindView(R.id.fan_right_tv)
    TextView mFanRightTv;
    @BindView(R.id.search_edit)
    SearchTextView mSearchEdit;
    @BindView(R.id.recyclerView)
    CustomeRecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    CustRefreshLayout mRefreshLayout;
    @BindView(R.id.title_bar)
    TitleBar mTitleBar;

    private int mPageNum = 1;


    int searchType = 1;
    String keyWord = "";
    private FanAdapter mFanAdapter;


    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_fans_activtity);
    }


    @Override
    public void initView() {
        super.initView();
        mTitleBar.setTitle("粉丝");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(FansActivtity.this));
        mRecyclerView.addItemDecoration(new LinearLayoutDivider(FansActivtity.this, 2, getResources().getColor(R.color.color_f2f2f2)));
        mFanAdapter = new FanAdapter();
        mFanAdapter.setLoadMoreView(new CostomLoadMoreView());
        mRecyclerView.setAdapter(mFanAdapter);
    }

    @Override
    public void initData() {
        super.initData();
        httpGetFanCount();
        httpGetFan();
    }


    @Override
    public void initEvent() {
        super.initEvent();

        mSearchEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == 0 || actionId == 3) {
                    httpGetFan();
                }

                return false;
            }
        });


        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                mRefreshLayout.finishLoadMore(600);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout.finishRefresh(600);
            }
        });
    }


    private void httpGetFanCount() {

        FanCountModel.httpGetFanCount(new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {
                FanCountModel fanCountModel = JSONObject.parseObject(resultData, FanCountModel.class);
                mFanLeft.setText("专属粉丝 （" + fanCountModel.getData().getExclusiveFansNum() + ")");
                mFanRight.setText("粉丝 （" + fanCountModel.getData().getAuctionFansNum() + ")");
            }
        });


    }

    private void httpGetFan() {


        keyWord = mSearchEdit.getText().toString().trim();

        FanModel.httpGetFan(searchType, keyWord, mPageNum, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {
                FanModel fanModel = JSONObject.parseObject(resultData, FanModel.class);
                if (mPageNum == 1) {
                    mFanAdapter.setNewData(fanModel.getData());
                } else {
                    mFanAdapter.addData(fanModel.getData());
                    mFanAdapter.loadMoreComplete();
                }

                if (fanModel.getData().isEmpty()) {
                    mPageNum = 1;
                } else if (fanModel.getData().size() > Constants.Var.LIST_NUMBER_INT) {
                    mFanAdapter.loadMoreEnd(true);
                } else {
                    mFanAdapter.loadMoreEnd();
                }

            }
        });

    }


    @OnClick({R.id.fan_left, R.id.fan_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fan_left:
                searchType = 1;
                mFanLeft.setTextColor(Color.parseColor("#7c1313"));
                mFanRight.setTextColor(Color.parseColor("#666666"));
                mFanLeftTv.setVisibility(View.VISIBLE);
                mFanRightTv.setVisibility(View.INVISIBLE);
                httpGetFan();

                break;
            case R.id.fan_right:
                searchType = 2;
                mFanRight.setTextColor(Color.parseColor("#7c1313"));
                mFanLeft.setTextColor(Color.parseColor("#666666"));
                mFanLeftTv.setVisibility(View.INVISIBLE);
                mFanRightTv.setVisibility(View.VISIBLE);
                httpGetFan();
                break;
        }
    }


}
