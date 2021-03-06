package com.leo.auction.ui.main;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.aten.compiler.base.BaseFragment;
import com.aten.compiler.utils.BroadCastReceiveUtils;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.ui.login.LoginActivity;
import com.leo.auction.ui.main.home.activity.HomeSearchActivity;
import com.leo.auction.ui.main.home.fragment.FocusAllFragment;
import com.leo.auction.ui.main.home.fragment.FocusCollectFragment;
import com.leo.auction.ui.main.home.fragment.FocusFootmarkFragment;
import com.leo.auction.ui.main.home.fragment.FocusJoinFragment;
import com.leo.auction.ui.main.home.fragment.FocusShopFragment;
import com.leo.auction.ui.main.home.fragment.HomeAllFragment;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.utils.Globals;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFocusFragment extends BaseFragment {


    @BindView(R.id.title_bg)
    ImageView mTitleBg;
    @BindView(R.id.title_lin)
    LinearLayout mTitleLin;
    @BindView(R.id.common_tab)
    SegmentTabLayout mSegmentTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private String[] mTitlesStr = {"拍品", "店铺", "收藏", "参拍", "足迹"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();


    public MainFocusFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_main_focus;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

    }

    @Override
    public void initView(View view) {
        super.initView(view);

        mSegmentTabLayout.setTabData(mTitlesStr);
        mFragments.add(new FocusAllFragment());
        mFragments.add(new FocusShopFragment());
        mFragments.add(new FocusCollectFragment());  //收藏
        mFragments.add(new FocusJoinFragment());
        mFragments.add(new FocusFootmarkFragment());
        TitlePagerAdapter titlePagerAdapter = new TitlePagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(titlePagerAdapter);
        mViewPager.setOffscreenPageLimit(5);

        mSegmentTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
                BroadCastReceiveUtils.sendLocalBroadCast(getActivity(),Constants.Action.ACTION_FOCUS_TYPE);
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
                BroadCastReceiveUtils.sendLocalBroadCast(getActivity(),Constants.Action.ACTION_FOCUS_TYPE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Globals.log("xxxxxx Constants.Var.FOCUS_TYPE" + Constants.Var.FOCUS_TYPE);

        mViewPager.setCurrentItem(Constants.Var.FOCUS_TYPE);

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
            return mFragments.get(position);
        }
    }

    @OnClick(R.id.title_lin)
    public void onViewClicked() {
        ActivityManager.JumpActivity(getActivity(), HomeSearchActivity.class);
    }
}
