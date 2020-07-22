package com.leo.auction.ui.main;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aten.compiler.base.BaseFragment;
import com.aten.compiler.widget.tabLayout.SlidingTabLayout;
import com.aten.compiler.widget.title.TitleBar;
import com.gyf.immersionbar.ImmersionBar;
import com.leo.auction.R;
import com.leo.auction.base.Constants;
import com.leo.auction.ui.main.news.fragment.NoticeFragment;
import com.leo.auction.ui.main.news.fragment.ServiceFragment;
import com.leo.auction.ui.main.news.fragment.SystemNewsFragment;
import com.leo.auction.utils.viewPage.BaseViewPagerAndTabsAdapter;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends BaseFragment {


//    String[] titles = new String[]{"官方公告", "系统消息", "客服消息"};
    String[] titles = new String[]{"官方公告", "系统消息"};
    @BindView(R.id.stb_order)
    SlidingTabLayout stbOrder;
    @BindView(R.id.vp_order)
    ViewPager vpOrder;


    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_news;
    }


    @Override
    public void initData() {
        super.initData();
        initTabLayout();
    }

    private void initTabLayout() {
        BaseViewPagerAndTabsAdapter baseViewPagerAndTabsAdapter_new = new BaseViewPagerAndTabsAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0://官方消息

                        fragment = new NoticeFragment();
                        Bundle bundle = new Bundle();
//                        bundle.putString("status", status);
//                        bundle.putBoolean("isShowTrash", isShowTrash);
                        fragment.setArguments(bundle);
                        break;
                    case 1://系统消息
                        fragment = new SystemNewsFragment();
                        break;
                    case 2://客服消息
                        fragment = new ServiceFragment();
                        break;
                }
                return fragment;
            }
        };

        baseViewPagerAndTabsAdapter_new.setData(Arrays.asList(titles));
        vpOrder.setAdapter(baseViewPagerAndTabsAdapter_new);
        stbOrder.setViewPager(vpOrder);
    }


}
