package com.leo.auction.ui.main;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseFragment;
import com.aten.compiler.widget.CircleImageView;
import com.aten.compiler.widget.glide.GlideUtils;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.leo.auction.R;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.login.model.CommonModel;
import com.leo.auction.ui.main.home.fragment.MineOrderFragment;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.utils.Globals;
import com.ruffian.library.widget.RTextView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainMeFragment extends BaseFragment {


    @BindView(R.id.civ_head)
    CircleImageView mCivHead;
    @BindView(R.id.mine_vip)
    ImageView mMineVip;
    @BindView(R.id.mine_level)
    ImageView mMineLevel;
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
    @BindView(R.id.sliding_tab)
    SlidingTabLayout mCommonTab;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;


    ArrayList<String> mTitleList = new ArrayList<>();
    String[] mTitles = {"买入订单", "卖出订单"};


    ArrayList<BaseFragment> mFragments = new ArrayList<>();
    private OrderPagerAdapter mOrderPagerAdapter;


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
        httpUser();
    }


    private class OrderPagerAdapter extends FragmentPagerAdapter {
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
            return mTitleList.get(position);
        }

        @Override
        public int getCount() {
            return mTitleList.size();
        }
    }


    private void httpUser() {
        HashMap<String, String> hashMap = new HashMap<>();
        showWaitDialog();
        HttpRequest.httpGetString(Constants.Api.USER_URL, hashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                UserModel userModel = JSONObject.parseObject(resultData, UserModel.class);
                upUI(userModel.getData());
                BaseSharePerence.getInstance().setUserJson( JSON.toJSONString(userModel.getData()));
            }
        });
    }


    private void upUI(UserModel.DataBean userInfo) {

        CommonModel.DataBean commonJson = BaseSharePerence.getInstance().getCommonJson();

        String[] myLevelVPicS = commonJson.getMy_level_v_pic().get(0).split("my_level_v_pic_");
        String vipUrl = myLevelVPicS[0] + "my_level_v_pic_" + userInfo.getLevel() + ".png";


        String[] myLevelPicS = commonJson.getMy_level_pic().get(0).split("auction_level_hd_");
        String LevelUrl = myLevelPicS[0] + "auction_level_hd_" + userInfo.getLevel() + ".png";

        GlideUtils.loadImg(userInfo.getHeadImg(), mCivHead);
        GlideUtils.loadImgDefault(vipUrl, mMineVip);
        GlideUtils.loadImgDefault(LevelUrl, mMineLevel);

        mTvName.setText(userInfo.getNickname());
        mTvFollowNum.setText(String.valueOf(userInfo.getFollowNum()));
        mTvFansNum.setText(String.valueOf(userInfo.getFansNum()));
        mTvCoinNum.setText(String.valueOf(userInfo.getScore()));

        mTitleList.clear();
        if (userInfo.getType() == 1) {   // 1 买家 2 卖家
            mTitleList.add("买入订单");
            mMineLevel.setVisibility(View.GONE);
        } else {
            mTitleList.add("买入订单");
            mTitleList.add("卖出订单");
        }


        mFragments.add(new MineOrderFragment());
        mFragments.add(new MineOrderFragment());



        mOrderPagerAdapter = new OrderPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mOrderPagerAdapter);
        mCommonTab.setViewPager(mViewPager);

        mCommonTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
//                mViewPager.setCurrentItem(position);
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
//                mCommonTab.setCurrentTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        mViewPager.setCurrentItem(0);

    }


}

