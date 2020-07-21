package com.leo.auction.ui.main;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.ActivityManager;
import com.aten.compiler.base.BaseFragment;
import com.aten.compiler.utils.BroadCastReceiveUtils;
import com.aten.compiler.widget.TouchCheckView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.leo.auction.R;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.home.MyUtils;
import com.leo.auction.ui.main.home.activity.CategoryActivity;
import com.leo.auction.ui.main.home.activity.HomeSearchActivity;
import com.leo.auction.ui.main.home.adapter.SortAdapter;
import com.leo.auction.ui.main.home.adapter.SortRightAdapter;
import com.leo.auction.ui.main.home.model.SortLeftModel;
import com.leo.auction.utils.Globals;
import com.leo.auction.utils.layoutManager.SortLinearSmoothScroller;
import com.ruffian.library.widget.RTextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainSortFragment extends BaseFragment {


    @BindView(R.id.home_sort_max)
    RecyclerView mHomeSortMax;
    @BindView(R.id.home_sort_min)
    RecyclerView mHomeSortMin;
    @BindView(R.id.lin_search)
    RTextView mSearchMin;


    private SortAdapter mSortAdapter;
    private SortRightAdapter mSortRightAdapter;
    private List<SortLeftModel.DataBean> mSortLeftList;
    private List<SortLeftModel.DataBean.ChildrenBean> mSortRightList;


    private final Map<Integer, Integer> indexMap = new HashMap<>();


    public MainSortFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_sort;
    }


    @Override
    public void initView(View view) {
        super.initView(view);


        mSearchMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BroadCastReceiveUtils.sendLocalBroadCast(getActivity(), Constants.Action.ACTION_HOME_SEARCH, "");
                ActivityManager.JumpActivity(getActivity(), HomeSearchActivity.class);
            }
        });


        mSortLeftList = new ArrayList<>();
        mSortRightList = new ArrayList<>();

        mHomeSortMax.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL, false));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (mSortRightList.get(position).getItemType() == Constants.Var.LAYOUT_TYPE) {
                    return 1;
                } else {
                    return 3;
                }
            }
        });
        mHomeSortMin.setLayoutManager(gridLayoutManager);


        mSortAdapter = new SortAdapter(mSortLeftList);
        mSortRightAdapter = new SortRightAdapter(getActivity(), mSortRightList);
        mHomeSortMax.setAdapter(mSortAdapter);
        mHomeSortMin.setAdapter(mSortRightAdapter);


        mSortAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                 左侧选中并滑到中间位置
//                mHomeSortMin.scrollToPosition(indexMap.get(position));
                LinearLayoutManager mLayoutManager =
                        (LinearLayoutManager) mHomeSortMin.getLayoutManager();
//                mLayoutManager.scrollToPositionWithOffset(indexMap.get(position), 0);

                scrollItemToTop(mLayoutManager, indexMap.get(position));
                mSortAdapter.setSelectedPosition(position);
            }
        });


        mSortRightAdapter.setRightInter(new SortRightAdapter.RightItemClick() {
            @Override
            public void ItemClick(SortLeftModel.DataBean.ChildrenBean item) {

                Bundle bundle = new Bundle();
                bundle.putString(Constants.Var.HOME_SORT_TYPE, item.getId());
                ActivityManager.JumpActivity(getActivity(), CategoryActivity.class, bundle);
            }
        });


        mHomeSortMin.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //只有当前已经停止了滚动才需要处理
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int firstCompletelyVisibleItemPosition = gridLayoutManager.findFirstCompletelyVisibleItemPosition();
                    int selectPosition = 0;
                    for (int i = 0; i < firstCompletelyVisibleItemPosition; i++) {
                        if (mSortRightList.get(i).getItemType() == Constants.Var.LAYOUT_TYPE_HEAD) {
                            selectPosition++;
                        }
                    }
                    mSortAdapter.setSelectedPosition(selectPosition);
                }
            }
        });


    }

    void scrollItemToTop(LinearLayoutManager mLayoutManager, int position) {
        SortLinearSmoothScroller smoothScroller = new SortLinearSmoothScroller(getActivity());
        smoothScroller.setTargetPosition(position);
        mLayoutManager.startSmoothScroll(smoothScroller);
    }


    @Override
    public void initData() {
        super.initData();
        Constants.Action.ACTION_ACTION = "2";
        showWaitDialog();
        SortLeftModel.httpSort(new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                SortLeftModel sortLeftModel = JSONObject.parseObject(resultData, SortLeftModel.class);
                mSortLeftList = sortLeftModel.getData();
                mSortAdapter.addData(mSortLeftList);

                for (int i = 0; i < mSortLeftList.size(); i++) {
                    SortLeftModel.DataBean.ChildrenBean childrenBean = new SortLeftModel.DataBean.ChildrenBean();
                    childrenBean.setIcon("");
                    childrenBean.setId("0");
                    childrenBean.setName(mSortLeftList.get(i).getName());
                    childrenBean.setItemType(Constants.Var.LAYOUT_TYPE_HEAD);
                    childrenBean.setPosition(i);
                    mSortRightList.add(childrenBean);
                    for (int j = 0; j < mSortLeftList.get(i).getChildren().size(); j++) {
                        SortLeftModel.DataBean.ChildrenBean childrenBeanB = new SortLeftModel.DataBean.ChildrenBean();
                        childrenBeanB.setIcon(mSortLeftList.get(i).getChildren().get(j).getIcon());
                        childrenBeanB.setId(mSortLeftList.get(i).getChildren().get(j).getId());
                        childrenBeanB.setName(mSortLeftList.get(i).getChildren().get(j).getName());
                        childrenBeanB.setItemType(Constants.Var.LAYOUT_TYPE);
                        childrenBeanB.setPosition(-1);
                        mSortRightList.add(childrenBeanB);
                    }
                }


                // 点击左侧需要知道对应右侧的位置，用map先保存起来
                for (int i = 0; i < mSortRightList.size(); i++) {
                    if (mSortRightList.get(i).getPosition() != -1) {
                        indexMap.put(mSortRightList.get(i).getPosition(), i);
                    }
                }
                mSortAdapter.setSelectedPosition(0);
                mSortAdapter.notifyDataSetChanged();
                mSortRightAdapter.notifyDataSetChanged();
            }
        });
    }


}
