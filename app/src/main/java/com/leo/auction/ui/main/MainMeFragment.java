package com.leo.auction.ui.main;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aten.compiler.base.BaseFragment;
import com.aten.compiler.widget.CircleImageView;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.leo.auction.R;
import com.leo.auction.ui.main.home.fragment.MineOrderFragment;
import com.ruffian.library.widget.RTextView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainMeFragment extends BaseFragment {


    @BindView(R.id.civ_head)
    CircleImageView mCivHead;
    @BindView(R.id.mine_vip)
    RTextView mMineVip;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.fl_shop)
    LinearLayout mFlShop;
    @BindView(R.id.tv_balance)
    TextView mTvBalance;
    @BindView(R.id.tv_super_coin)
    TextView mTvSuperCoin;
    @BindView(R.id.tv_follow_num)
    TextView mTvFollowNum;
    @BindView(R.id.ll_follow)
    LinearLayout mLlFollow;
    @BindView(R.id.tv_fans_num)
    TextView mTvFansNum;
    @BindView(R.id.ll_fans)
    LinearLayout mLlFans;
    @BindView(R.id.tv_coin_num)
    TextView mTvCoinNum;
    @BindView(R.id.common_tab)
    SegmentTabLayout mCommonTab;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;



    String[] mTitles =  {"买入订单","卖出订单"};



    ArrayList<BaseFragment> mFragments = new ArrayList<>();


    public MainMeFragment() {
        // Required empty public constructor
    }



    @Override
    public int getLayoutId() {
        return R.layout.fragment_main_me;
    }


    @Override
    public void initData() {
        super.initData();


        mFragments.add(new MineOrderFragment());
        mFragments.add(new MineOrderFragment());

        OrderPagerAdapter orderPagerAdapter = new OrderPagerAdapter(getChildFragmentManager());

        mCommonTab.setTabData(mTitles);
        mViewPager.setAdapter(orderPagerAdapter);


        mCommonTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mCommonTab.setCurrentTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        mViewPager.setCurrentItem(0);
    }



    private class OrderPagerAdapter extends FragmentPagerAdapter{
        public OrderPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return mFragments.get(i);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}

