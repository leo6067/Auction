package com.leo.auction.ui.main;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.leo.auction.R;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.home.MyUtils;
import com.leo.auction.ui.main.home.adapter.SortAdapter;
import com.leo.auction.ui.main.home.adapter.SortRightAdapter;
import com.leo.auction.ui.main.home.model.SortLeftModel;
import com.leo.auction.utils.Globals;

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

        mSortLeftList = new ArrayList<>();
        mSortRightList = new ArrayList<>();

        mHomeSortMax.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL, false));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
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
                Globals.log("xxxxxxxxxx 00  indexMap" + position);
//                 左侧选中并滑到中间位置
                mSortAdapter.setSelectedPosition(position);

//                mSortLeftList.get(position).setSelected(true);
                mSortAdapter.notifyDataSetChanged();
                MyUtils.moveToMiddle(mHomeSortMax, position);
                // 右侧滑到对应位置
                ((GridLayoutManager)mHomeSortMin.getLayoutManager())
                        .scrollToPositionWithOffset(position,0);

//                mSortAdapter.setSelectedPosition(position);
//                mHomeSortMin.scrollToPosition(position);

            }
        });


        mSortRightAdapter.setRightInter(new SortRightAdapter.RightItemClick() {
            @Override
            public void ItemClick(SortLeftModel.DataBean.ChildrenBean item) {
                Globals.log("xxxxxxxxxx 0 indexMap" + item);
            }
        });

//        mHomeSortMax.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
//                    mHomeSortMin.scrollBy(dx, dy);
//                }
//            }
//        });

        mHomeSortMin.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //获取右侧列表的第一个可见Item的position
                int topPosition = ((LinearLayoutManager) mHomeSortMax.getLayoutManager()).findFirstVisibleItemPosition();
                // 如果此项对应的是左边的大类的index
                if (mSortRightList.get(topPosition).getPosition() != -1) {
                    MyUtils.moveToMiddle(mHomeSortMax, mSortRightList.get(topPosition).getPosition());
                    mSortAdapter.setSelectedPosition(mSortRightList.get(topPosition).getPosition());
                }
            }
        });
    }


    @Override
    public void initData() {
        super.initData();

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("type", "1");
        showWaitDialog();
        HttpRequest.httpGetString(Constants.Api.SORT_ABOUT_URL, hashMap, new HttpRequest.HttpCallback() {
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
                    childrenBean.setId(0);
                    childrenBean.setName(mSortLeftList.get(i).getName());
                    childrenBean.setItemType(Constants.Var.LAYOUT_TYPE_HEAD);
                    childrenBean.setPosition(i);
                    mSortRightList.add(childrenBean);
                    for (int j = 0; j <mSortLeftList.get(i).getChildren().size() ; j++) {
                        SortLeftModel.DataBean.ChildrenBean childrenBeanB = new SortLeftModel.DataBean.ChildrenBean();
                        childrenBeanB.setIcon(mSortLeftList.get(i).getChildren().get(j).getIcon());
                        childrenBeanB.setId(mSortLeftList.get(i).getChildren().get(j).getId());
                        childrenBeanB.setName( mSortLeftList.get(i).getChildren().get(j).getName());
                        childrenBeanB.setItemType( Constants.Var.LAYOUT_TYPE);
                        childrenBeanB.setPosition(-1);
                        mSortRightList.add(childrenBeanB);
                    }
                }

                mSortAdapter.notifyDataSetChanged();
                mSortRightAdapter.notifyDataSetChanged();

                // 点击左侧需要知道对应右侧的位置，用map先保存起来
//                for (int i = 0; i < mSortRightList.size(); i++) {
//                    if (mSortRightList.get(i).getPosition() != -1) {
//                        indexMap.put(mSortRightList.get(i).getPosition(), i);
//
//                        Globals.log("xxxxxxxxxx  indexMap" +mSortRightList.get(i).getPosition());
//                    }
//                }
            }
        });




    }


}
