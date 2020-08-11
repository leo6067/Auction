package com.aten.compiler.base.BaseRecyclerView;

import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.aten.compiler.R;
import com.aten.compiler.base.BaseFragment;
import com.aten.compiler.widget.CostomLoadMoreView;
import com.aten.compiler.widget.CustRefreshLayout;
import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

/**
 * project:PJHAndroidFrame
 * package:com.frame.pjh_core.base.BaseRecyclerView
 * Created by 彭俊鸿 on 2018/6/4.
 * e-mail : 1031028399@qq.com
 * recyclerview fragment 基类
 */

public class BaseRecyclerViewFragment extends BaseFragment implements OnRefreshLoadMoreListener,BaseQuickAdapter.RequestLoadMoreListener {

    protected SmartRefreshLayout refreshLayout;
    protected RecyclerView recyclerView;
    protected BaseQuickAdapter mAdapter;

    public int mPageNum = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_base_recyclerview;
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
        mAdapter.setLoadMoreView(new CostomLoadMoreView());

        recyclerView.setHasFixedSize(true);
        if (getLayoutManager()==null){
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        }else {
            recyclerView.setLayoutManager(getLayoutManager());
        }

        setEmptyView();
        recyclerView.setAdapter(mAdapter);
        setRefreshInfo();
        setSmartHasRefreshOrLoadMore();
        setLoadMore();
    }
    //RecyclerView的LayoutManager
    public RecyclerView.LayoutManager getLayoutManager(){
        return null;
    }

    //设置recyclervie的空页面
    public void setEmptyView() {
        mAdapter.setEmptyView(R.layout.layout_empty_view,recyclerView);
    }

    //初始话适配器
    protected void initAdapter() {
        mAdapter=new BaseAdapterRecyclerview();
    }

    //是否开启item加载动画
    public boolean isOpenAnim() {
        return false;
    }

    //设置SmartRefreshLayout的刷新 加载样式
    public void setRefreshInfo() {
        refreshLayout.setPrimaryColorsId(R.color.windowbackground, R.color.txt_color_666);
        refreshLayout.setRefreshFooter(new FalsifyFooter(getContext()));
        refreshLayout.setHeaderMaxDragRate(5);//最大显示下拉高度/Header标准高度  头部下拉高度的比例
    }

    //加载列表数据
    public void getData() {}

    @Override
    public void initEvent() {
        super.initEvent();
    }

    //设置smartrefresh是否需要下拉刷新以及加载更多
    public void setSmartHasRefreshOrLoadMore() {
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setEnableLoadMore(true);
    }

    //设置smartrefresh是否需要下拉刷新以及加载更多
    public void setLoadMore() {
        mAdapter.setEnableLoadMore(true);
        mAdapter.setOnLoadMoreListener(BaseRecyclerViewFragment.this,recyclerView);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout RefreshLayout) {
        mPageNum=1;
        getData();
        if (refreshLayout !=null){
            Log.e("xxxxxx","refresh 00");
            refreshLayout.finishRefresh(800);
        }
    }

    @Override
    public void onLoadMoreRequested() {
        mPageNum++;
        getData();
        if (refreshLayout !=null){
            refreshLayout.finishLoadMore(800);
        }


    }

    //关闭刷新的view
    public void hideRefreshView(){
        if (refreshLayout.getState() == RefreshState.Refreshing) {
            refreshLayout.finishRefresh();
        } else {
            hideWaitDialog();
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if (refreshLayout !=null){
            refreshLayout.finishLoadMore(800);
        }
        LogUtils.e("refresh ");
    }
}
