package com.aten.compiler.base.BaseRecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aten.compiler.R;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.widget.CostomLoadMoreView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;

/**
 * project:PJHAndroidFrame
 * package:com.frame.pjh_core.base
 * Created by 彭俊鸿 on 2018/6/4.
 * e-mail : 1031028399@qq.com
 * recyclerview activity 基类
 */

public class BaseRecyclerViewActivity extends BaseActivity implements OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    protected SmartRefreshLayout refreshLayout;
    protected RecyclerView recyclerView;
    protected BaseQuickAdapter mAdapter;
    public int mPageNum = 1;


    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_base_recyclerview);
    }

    @Override
    public void initView() {
        super.initView();
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    @Override
    public void initData() {
        super.initData();
        initAdapter();
        mAdapter.setLoadMoreView(new CostomLoadMoreView());
        setSmartHasRefreshOrLoadMore(true);
        if (isOpenAnim()) {
            mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
            mAdapter.isFirstOnly(false);
        }
        recyclerView.setHasFixedSize(true);
        if (getLayoutManager() == null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        } else {
            recyclerView.setLayoutManager(getLayoutManager());
        }


        setEmptyView();
        recyclerView.setAdapter(mAdapter);
        setRefreshInfo();
    }

    //RecyclerView的LayoutManager
    public RecyclerView.LayoutManager getLayoutManager() {
        return null;
    }

    //设置recyclervie的空页面
    public void setEmptyView() {
        mAdapter.setEmptyView(R.layout.layout_empty_view, recyclerView);
    }

    //初始话适配器
    public void initAdapter() {
        mAdapter = new BaseAdapterRecyclerview();
    }

    //是否开启item加载动画
    public boolean isOpenAnim() {
        return false;
    }

    //设置SmartRefreshLayout的刷新 加载样式
    public void setRefreshInfo() {
        refreshLayout.setPrimaryColorsId(R.color.windowbackground, R.color.txt_color_666);
        refreshLayout.setRefreshFooter(new FalsifyFooter(this));
        refreshLayout.setHeaderMaxDragRate(5);//最大显示下拉高度/Header标准高度  头部下拉高度的比例
    }

    //加载列表数据
    public void getData() {
    }

    @Override
    public void initEvent() {
        super.initEvent();
    }

    //设置smartrefresh是否需要下拉刷新以及加载更多
    public void setSmartHasRefreshOrLoadMore(boolean enadble) {
        refreshLayout.setEnableRefresh(enadble);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setEnableLoadMore(enadble);
        mAdapter.setEnableLoadMore(enadble);
        mAdapter.loadMoreEnd(true);
        mAdapter.setOnLoadMoreListener(BaseRecyclerViewActivity.this, recyclerView);
    }



    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPageNum = 1;
        getData();
        if (refreshLayout != null) {
            refreshLayout.finishRefresh(600);
        }
    }

    @Override
    public void onLoadMoreRequested() {
        mPageNum++;
        getData();
        if (refreshLayout != null) {
            refreshLayout.finishRefresh(600);
        }
    }

//    public HashMap<String, String> getHashMap() {
//        HashMap<String, String> hashMap = new HashMap<>();
//        return hashMap;
//    }


    //关闭刷新的view
    public void hideRefreshView() {
        if (refreshLayout.getState() == RefreshState.Refreshing) {
            refreshLayout.finishRefresh();
        } else {
            hideWaitDialog();
        }
    }
}
