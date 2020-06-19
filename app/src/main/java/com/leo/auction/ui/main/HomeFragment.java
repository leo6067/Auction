package com.leo.auction.ui.main;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.aten.compiler.base.BaseFragment;
import com.aten.compiler.utils.BroadCastReceiveUtils;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.leo.auction.R;
import com.leo.auction.base.Constants;
import com.leo.auction.ui.login.LoginWxActivity;
import com.leo.auction.ui.main.home.fragment.HomeAllFragment;
import com.leo.auction.utils.Globals;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.title_bg)
    ImageView mTitleBg;
    @BindView(R.id.title_lin)
    LinearLayout mTitleLin;
    @BindView(R.id.common_tab)
    SegmentTabLayout mSegmentTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private String[] mTitlesStr = {"全部", "一元拍", "捡漏", "最新发布", "即将截拍"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    public void initData() {
        super.initData();
        mSegmentTabLayout.setTabData(mTitlesStr);

        TitlePagerAdapter titlePagerAdapter = new TitlePagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(titlePagerAdapter);
//        mViewPager.setOffscreenPageLimit(0);
        mSegmentTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position == 0) {
                    BroadCastReceiveUtils.sendLocalBroadCast(getActivity(), Constants.Action.SEND_REFRESH_HOME_ALL);
                }
                Constants.Var.HOME_TYPE = position;
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });



        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mSegmentTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setCurrentItem(0);
    }


    @OnClick(R.id.title_lin)
    public void onViewClicked() {
    }


    private class TitlePagerAdapter extends FragmentPagerAdapter {

        public TitlePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mTitlesStr.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitlesStr[position];
        }

        @Override
        public Fragment getItem(int position) {
            return new HomeAllFragment();
        }
    }


}
