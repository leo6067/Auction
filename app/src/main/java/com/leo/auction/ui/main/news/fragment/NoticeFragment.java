package com.leo.auction.ui.main.news.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseRecyclerView.BaseRecyclerViewFragment;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;


import com.leo.auction.ui.main.news.adapter.NewsAdapter;
import com.leo.auction.ui.main.news.model.NewsModel;
import com.leo.auction.ui.web.AgentWebActivity;

import java.util.HashMap;

import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticeFragment extends BaseRecyclerViewFragment {


    public NoticeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notice, container, false);
    }


    @Override
    protected void initAdapter() {
        super.initAdapter();

        mAdapter = new NewsAdapter();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NewsModel.DataBean json = (NewsModel.DataBean) mAdapter.getData().get(position);

                Bundle bundle = new Bundle();
                bundle.putString("title", "公告详情");
                bundle.putString("url", json.getSkipUrl());
                ActivityManager.JumpActivity(getActivity(), AgentWebActivity.class, bundle);
            }
        });
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        onRefresh(refreshLayout);
    }

    @Override
    public void initData() {
        super.initData();

    }

    @Override
    public void getData() {
        super.getData();

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("type", "2");  // 1-系统消息 2-官方公告
        hashMap.put("pageNum", String.valueOf(mPageNum));
        hashMap.put("pageSize", Constants.Var.LIST_NUMBER);


        boolean loginStatus = BaseSharePerence.getInstance().getLoginStatus();

        if (!loginStatus){
            return;
        }


        HttpRequest.httpGetString(Constants.Api.NEWS_SYS_URL, hashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                NewsModel newsModel = JSONObject.parseObject(resultData, NewsModel.class);
                if (mPageNum == 1) {
                    mAdapter.setNewData(newsModel.getData());
                } else {
                    mAdapter.addData(newsModel.getData());
                    mAdapter.loadMoreComplete();
                }

                if (newsModel.getData().isEmpty()) {
                    mPageNum = 1;
                } else if (newsModel.getData().size() > Constants.Var.LIST_NUMBER_INT) {
                    mAdapter.loadMoreEnd(true);
                } else {
                    mAdapter.loadMoreEnd();
                }
            }
        });
    }
}
