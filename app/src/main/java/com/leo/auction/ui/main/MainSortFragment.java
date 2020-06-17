package com.leo.auction.ui.main;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.aten.compiler.base.BaseFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.leo.auction.R;
import com.leo.auction.ui.main.home.adapter.SortAdapter;
import com.leo.auction.ui.main.home.adapter.SortRightAdapter;

import java.util.ArrayList;

import butterknife.BindView;

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


    public MainSortFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        enableLazyLoad();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_sort;
    }


    @Override
    public void initView(View view) {
        super.initView(view);

        mHomeSortMax.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayout.VERTICAL,false));
        mHomeSortMin.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayout.VERTICAL,false));


        ArrayList<String> objects = new ArrayList<>();

        mSortAdapter = new SortAdapter(objects);

        mSortRightAdapter = new SortRightAdapter(getActivity(),objects);

        mHomeSortMax.setAdapter(mSortAdapter);
        mHomeSortMin.setAdapter(mSortRightAdapter);


        mSortAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        mSortRightAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

    }


    @Override
    public void initData() {
        super.initData();


    }



}
