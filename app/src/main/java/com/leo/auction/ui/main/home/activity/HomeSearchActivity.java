package com.leo.auction.ui.main.home.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.BroadCastReceiveUtils;
import com.aten.compiler.widget.CustomViewPager;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.leo.auction.R;
import com.leo.auction.base.Constants;
import com.leo.auction.ui.main.home.fragment.BusinessFragment;
import com.leo.auction.ui.main.home.fragment.GoodsFragment;
import com.leo.auction.utils.Globals;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeSearchActivity extends BaseActivity {


    @BindView(R.id.segment_bottom)
    SlidingTabLayout mSegmentBottom;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.search_back)
    ImageView mSearchBack;
    @BindView(R.id.search_search)
    EditText mSearchSearch;
    @BindView(R.id.tv_search)
    TextView mSearchSearchTV;

    private String[] mTitles = {"拍品", "商家"};

    ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_home_search);
    }

    @Override
    public void initView() {
        super.initView();

        mFragments.add(new GoodsFragment());
        mFragments.add(new BusinessFragment());
        mViewPager.setAdapter(new CviewPage(getSupportFragmentManager()));
        mSegmentBottom.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });


        mViewPager.setCurrentItem(0);
        mSegmentBottom.setViewPager(mViewPager);

        mSearchSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String keyWord = mSearchSearch.getText().toString().trim();
                Globals.log("log xxxx actionId" + actionId + "  keyWord " + keyWord);

                if ((actionId == 1 || actionId == 3) && event != null) {
//                    String keyWord = mSearchSearch.getText().toString().trim();
                    BroadCastReceiveUtils.sendLocalBroadCast(HomeSearchActivity.this, Constants.Action.ACTION_HOME_SEARCH, keyWord);
                }
                return false;
            }
        });


        mSearchSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String keyWord = mSearchSearch.getText().toString().trim();
                if (!hasFocus) {
                    BroadCastReceiveUtils.sendLocalBroadCast(HomeSearchActivity.this, Constants.Action.ACTION_HOME_SEARCH, keyWord);
                }
            }
        });

        mSearchSearchTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyWord = mSearchSearch.getText().toString().trim();

                BroadCastReceiveUtils.sendLocalBroadCast(HomeSearchActivity.this, Constants.Action.ACTION_HOME_SEARCH, keyWord);

            }
        });

    }


    @OnClick(R.id.search_back)
    public void onViewClicked() {
        finish();
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
