package com.aten.compiler.base.BaseRecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.aten.compiler.R;
import com.aten.compiler.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.umeng.commonsdk.debug.D;

import java.util.ArrayList;

/**
 * project:PJHAndroidFrame
 * package:com.frame.pjh_core.base.BaseRecyclerView
 * Created by 彭俊鸿 on 2018/6/4.
 * e-mail : 1031028399@qq.com
 * recyclerview fragment 基类
 */

public class BaseRecyclerViewFragmentZHY extends BaseFragment implements OnRefreshLoadMoreListener {

    protected SmartRefreshLayout refreshLayout;
    protected RecyclerView recyclerView;
    protected RecyclerView.Adapter mAdapter ;


    public int mPageNum = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_base_recyclerview_zhy;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        refreshLayout=(SmartRefreshLayout)view.findViewById(R.id.refreshLayout);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);
    }

    @Override
    public void initData() {
        super.initData();
        initAdapter();
        if (getLayoutManager()==null){
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        }else {
            recyclerView.setLayoutManager(getLayoutManager());
        }

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
        setEmptyView();
        setRefreshInfo();
        setSmartHasRefreshOrLoadMore(true,true);
    }
    //RecyclerView的LayoutManager
    public RecyclerView.LayoutManager getLayoutManager(){
        return null;
    }

    //设置recyclervie的空页面
    public void setEmptyView() {
//        recyclerView.emp
//        mAdapter.setEmptyView(R.layout.layout_empty_view,recyclerView);
    }

    //初始话适配器
    public void initAdapter() {

    }
    @Override
    public void initEvent() {
        super.initEvent();
    }

    //设置SmartRefreshLayout的刷新 加载样式
    public void setRefreshInfo() {
        refreshLayout.setPrimaryColorsId(R.color.windowbackground, R.color.txt_color_666);
        refreshLayout.setRefreshFooter(new FalsifyFooter(getContext()));
        refreshLayout.setHeaderMaxDragRate(5);//最大显示下拉高度/Header标准高度  头部下拉高度的比例
    }

    //加载列表数据
    public void getData() {}



    //设置smartrefresh是否需要下拉刷新以及加载更多
    public void setSmartHasRefreshOrLoadMore(boolean refresh,boolean loadmore) {
        refreshLayout.setEnableRefresh(refresh);
        refreshLayout.setEnableLoadMore(loadmore);
        refreshLayout.autoLoadMore();
    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPageNum=1;
        getData();
        Log.e("xxxxxxxxxxxxx","onRefresh");
        if (refreshLayout!=null){
            refreshLayout.finishRefresh(800);
        }
    }


    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        mPageNum++;
        getData();
        Log.e("xxxxxxxxxxxxx","onLoadMore");
        if (refreshLayout!=null){
            refreshLayout.finishLoadMore(800);
        }
    }
}
