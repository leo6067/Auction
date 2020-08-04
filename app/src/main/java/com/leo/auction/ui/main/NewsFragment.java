package com.leo.auction.ui.main;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aten.compiler.base.BaseFragment;

import com.aten.compiler.widget.title.TitleBar;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.gyf.immersionbar.ImmersionBar;
import com.leo.auction.R;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.ui.login.LoginActivity;
import com.leo.auction.ui.main.home.fragment.BusinessFragment;
import com.leo.auction.ui.main.home.fragment.GoodsFragment;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.ui.main.news.fragment.NoticeFragment;
import com.leo.auction.ui.main.news.fragment.ServiceFragment;
import com.leo.auction.ui.main.news.fragment.SystemNewsFragment;
import com.leo.auction.utils.Globals;
import com.leo.auction.utils.viewPage.BaseViewPagerAndTabsAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends BaseFragment {


//    String[] titles = new String[]{"官方公告", "系统消息", "客服消息"};
    String[] mTitles = new String[]{"官方公告", "系统消息"};
    @BindView(R.id.stb_order)
    SlidingTabLayout mSlidingTabLayout;
    @BindView(R.id.vp_order)
    ViewPager mViewPager;

    ArrayList<Fragment> mFragments;




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
       mFragments = new ArrayList<>();
        mFragments.add(new NoticeFragment());
        mFragments.add(new SystemNewsFragment());
//        mFragments.add(new ServiceFragment());
        mViewPager.setAdapter(new CviewPage(getChildFragmentManager()));

        mSlidingTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });



        mViewPager.setCurrentItem(0);
        mSlidingTabLayout.setViewPager(mViewPager);



    }

    private class CviewPage extends FragmentPagerAdapter {

        public CviewPage(FragmentManager fm) {
            super(fm);
        }


        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }


}
