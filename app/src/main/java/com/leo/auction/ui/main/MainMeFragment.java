package com.leo.auction.ui.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseFragment;
import com.aten.compiler.widget.CircleImageView;
import com.aten.compiler.widget.glide.GlideUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.login.LoginActivity;
import com.leo.auction.ui.login.model.CommonModel;
import com.leo.auction.ui.main.home.activity.AuctionDetailActivity;
import com.leo.auction.ui.main.home.activity.ShopActivity;
import com.leo.auction.ui.main.home.fragment.MineOrderFragment;
import com.leo.auction.ui.main.mine.model.CateProductModel;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.utils.Globals;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
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
    @BindView(R.id.title_lin)
    LinearLayout mTitleLin;

    private OrderPagerAdapter mOrderPagerAdapter;

    private String shopUri = "";
    private String shopName = "";


    public MainMeFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_main_me;
    }




    @Override
    public void onResume() {
        super.onResume();
        httpUser();
        httpTab();// 提前加载拍品管理列表 标题数据
    }

    @OnClick({R.id.civ_head, R.id.tv_name, R.id.fl_shop, R.id.ll_follow, R.id.ll_fans})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.civ_head:
            case R.id.tv_name:
            case R.id.fl_shop:
                Bundle bundle = new Bundle();
                bundle.putString("shopUri", shopUri);
                bundle.putString("shopName", shopName);
                ActivityManager.JumpActivity(getActivity(), ShopActivity.class);
                break;
            case R.id.ll_follow:
                ActivityManager.mainActivity.setCurrent(2);
                break;
            case R.id.ll_fans:
                break;
        }
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
                BaseSharePerence.getInstance().setUserJson(JSON.toJSONString(userModel.getData()));

                shopUri = userModel.getData().getUserId();
                shopName = userModel.getData().getNickname();

            }
        });
    }


    private void upUI(UserModel.DataBean userInfo) {

        mTitleList.clear();
        if (userInfo.getType() == 1) {   // 1 买家 2 卖家
            mTitleList.add("买入订单");
            mMineLevel.setVisibility(View.GONE);
        } else {
            mTitleList.add("买入订单");
            mTitleList.add("卖出订单");
            mMineLevel.setVisibility(View.VISIBLE);
        }
        upUserLevel(userInfo, false);

        mFragments.add(MineOrderFragment.newIntance(1));
        mFragments.add(MineOrderFragment.newIntance(2));

        Constants.Var.MINE_TYPE = 0;
        mOrderPagerAdapter = new OrderPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mOrderPagerAdapter);
        mCommonTab.setViewPager(mViewPager);

        mCommonTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
//                mViewPager.setCurrentItem(position);
                Constants.Var.MINE_TYPE = position;
                if (position == 0) {
                    upUserLevel(userInfo, false);
                } else {
                    upUserLevel(userInfo, true);
                }


            }

            @Override
            public void onTabReselect(int position) {
                Constants.Var.MINE_TYPE = position;
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
//                mCommonTab.setCurrentTab(i);
                Constants.Var.MINE_TYPE = position;

                if (position == 0) {
                    upUserLevel(userInfo, false);
                } else {
                    upUserLevel(userInfo, true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mViewPager.setCurrentItem(0);
    }

    public void upUserLevel(UserModel.DataBean userInfo, boolean isSeller) {

        if (!isSeller) {   //买家
            CommonModel.DataBean commonJson = BaseSharePerence.getInstance().getCommonJson();
            String[] myLevelVPicS = commonJson.getMy_level_v_pic().get(0).split("my_level_v_pic_");
            String vipUrl = myLevelVPicS[0] + "my_level_v_pic_" + userInfo.getLevel() + ".png";
            String[] myLevelPicS = commonJson.getMy_level_pic().get(0).split("auction_level_hd_");
            String LevelUrl = myLevelPicS[0] + "auction_level_hd_" + userInfo.getLevel() + ".png";

            Globals.log("xxxxxx vipUrl" +  vipUrl);
            Globals.log("xxxxxx LevelUrl" +  LevelUrl);
            GlideUtils.loadImg(userInfo.getHeadImg(), mCivHead);
            GlideUtils.loadImgDefault(vipUrl, mMineVip);
            GlideUtils.loadImgDefault(LevelUrl, mMineLevel);

            mTvName.setText(userInfo.getNickname());
            mTvFollowNum.setText(String.valueOf(userInfo.getFollowNum()));
            mTvFansNum.setText(String.valueOf(userInfo.getFansNum()));
            mTvCoinNum.setText(String.valueOf(userInfo.getScore()));
            mFlShop.setVisibility(View.INVISIBLE);
            mMineLevel.setVisibility(View.VISIBLE);

        } else {
            CommonModel.DataBean commonJson = BaseSharePerence.getInstance().getCommonJson();
            String[] myLevelVPicS = commonJson.getSeller_level_pic().get(0).split("seller_level_");
            String vipUrl = myLevelVPicS[0] + "seller_level_" + userInfo.getSellerLevel() + ".png";
            GlideUtils.loadImg(userInfo.getHeadImg(), mCivHead);
            GlideUtils.loadImgDefault(vipUrl, mMineVip);
//            GlideUtils.loadImgDefault(LevelUrl, mMineLevel);
            mMineLevel.setVisibility(View.GONE);
            mFlShop.setVisibility(View.VISIBLE);
            mTvName.setText(userInfo.getNickname());
            mTvFollowNum.setText(String.valueOf(userInfo.getFollowNum()));
            mTvFansNum.setText(String.valueOf(userInfo.getFansNum()));
            mTvCoinNum.setText(String.valueOf(userInfo.getSellerScore()));
        }
    }


    private void httpTab() {
        HashMap<String, String> mHash = new HashMap<>();

        HttpRequest.httpGetString(Constants.Api.CATE_PRODUCT_URL, mHash, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {

//                CateProductModel cateProductModel = JSONObject.parseObject(resultData, CateProductModel.class);
                BaseSharePerence.getInstance().setAuctionManager(resultData);
            }
        });

    }

}

