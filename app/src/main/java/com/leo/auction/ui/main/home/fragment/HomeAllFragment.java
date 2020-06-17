package com.leo.auction.ui.main.home.fragment;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseRecyclerView.BaseRecyclerViewFragment;
import com.leo.auction.R;
import com.leo.auction.base.Constants;
import com.leo.auction.net.CustomerJsonCallBack;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.login.model.UserInfoModel;

import org.litepal.LitePal;

import java.util.HashMap;

import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */

public class HomeAllFragment extends BaseRecyclerViewFragment {



    public HomeAllFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        enableLazyLoad();
    }

    //Constants.Var.HOME_TYPE 0: "全部", 1:  "一元拍", "捡漏", "最新发布", "即将截拍"


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_all;
    }


    @Override
    public void initTitle(View view) {
        super.initTitle(view);
        mTitleBar.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
       UserInfoModel userInfoModel= LitePal.findFirst(UserInfoModel.class);
        super.initData();

        showWaitDialog();
        onRefresh(refreshLayout);
    }

    @Override
    protected void initAdapter() {

    }


    @Override
    protected void getData() {
        super.getData();


        HashMap<String, String> hashMap = new HashMap<>();



        hashMap.put("keyword","");
        hashMap.put("pageNum",""+mPageNum);
        hashMap.put("pageSize","50");

        showWaitDialog();
        HttpRequest.httpGetString(Constants.Api.HOME_ALL_URL, hashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {

                JSONObject.parse(resultData);
                hideWaitDialog();

            }
        });

    }






}
