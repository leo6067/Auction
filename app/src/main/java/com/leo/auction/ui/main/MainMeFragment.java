package com.leo.auction.ui.main;


import android.content.Intent;
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
import com.leo.auction.ui.login.AgreementActivity;
import com.leo.auction.ui.login.LoginActivity;
import com.leo.auction.ui.login.LoginWxActivity;
import com.leo.auction.ui.login.model.CommonModel;
import com.leo.auction.ui.main.home.activity.AuctionDetailActivity;
import com.leo.auction.ui.main.home.activity.ShopActivity;
import com.leo.auction.ui.main.home.fragment.MineOrderBuyFragment;
import com.leo.auction.ui.main.home.fragment.MineOrderFragment;
import com.leo.auction.ui.main.home.model.SceneModel;
import com.leo.auction.ui.main.mine.dialog.RuleProtocolDialog;
import com.leo.auction.ui.main.mine.model.CateProductModel;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.ui.web.AgentWebActivity;
import com.leo.auction.utils.DialogUtils;
import com.leo.auction.utils.Globals;
import com.ruffian.library.widget.RImageView;

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
    RImageView mCivHead;
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


    private int orderType = 0;

    ArrayList<BaseFragment> mFragments = new ArrayList<>();
    @BindView(R.id.title_lin)
    LinearLayout mTitleLin;

    private OrderPagerAdapter mOrderPagerAdapter;

    private String shopUri = "";
    private String shopName = "";
    private DialogUtils dialogUtils;
    private UserModel.DataBean mUserJson;


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
        dialogUtils = new DialogUtils();

    }

    @Override
    public void onResume() {
        super.onResume();
        mUserJson = BaseSharePerence.getInstance().getUserJson();
        httpUser();
        httpTab();// 提前加载拍品管理列表 标题数据
    }

    @OnClick({R.id.tv_coin_num, R.id.civ_head, R.id.tv_name, R.id.fl_shop, R.id.ll_follow, R.id.ll_fans})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.civ_head:
            case R.id.tv_name:
            case R.id.fl_shop:
                Bundle bundle = new Bundle();
                bundle.putString("shopUri", mUserJson.getUserId());
                bundle.putString("shopName", mUserJson.getNickname());
                ActivityManager.JumpActivity(getActivity(), ShopActivity.class, bundle);
                break;
            case R.id.ll_follow:
                Constants.Var.FOCUS_TYPE = 1;
                ActivityManager.mainActivity.setCurrent(2);
                break;
            case R.id.ll_fans:

                break;
            case R.id.tv_coin_num:
                if (orderType == 0) {  //7-卖家等级说明  8-买家等级说明
                    showAgreeDialog("8");
                } else {
                    showAgreeDialog("7");
                }

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

        boolean loginStatus = BaseSharePerence.getInstance().getLoginStatus();

        if (!loginStatus) {
            return;
        }
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
                mUserJson = BaseSharePerence.getInstance().getUserJson();

            }
        });
    }


    private void upUI(UserModel.DataBean userInfo) {

        mTitleList.clear();
        if (userInfo.getType() == 1) {   // 1 买家 2 卖家
            mTitleList.add("买入订单");
            mMineLevel.setVisibility(View.GONE);
        } else if (userInfo.getExclusiveFansNum() < 50) {//粉丝数小于50
            mTitleList.add("买入订单");
            mMineLevel.setVisibility(View.GONE);
        } else {
            mTitleList.add("买入订单");
            mTitleList.add("卖出订单");
            mMineLevel.setVisibility(View.VISIBLE);
        }




        mFragments.add(MineOrderBuyFragment.newIntance(1));
        mFragments.add(MineOrderFragment.newIntance(2));


        mOrderPagerAdapter = new OrderPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mOrderPagerAdapter);
        mCommonTab.setViewPager(mViewPager);

        mCommonTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
//                mViewPager.setCurrentItem(position);
                orderType = 0;
                if (position == 0) {

                    upUserLevel(userInfo, false);
                } else {
                    upUserLevel(userInfo, true);
                }

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
            public void onPageSelected(int position) {
//                mCommonTab.setCurrentTab(i);

                orderType = 0;
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


        if (mTitleList.size() > 1) {
            mViewPager.setCurrentItem(BaseSharePerence.getInstance().getMeFragment());
            mCommonTab.setCurrentTab(BaseSharePerence.getInstance().getMeFragment());
            if (BaseSharePerence.getInstance().getMeFragment()==0){
                upUserLevel(userInfo, false);
            }else {
                upUserLevel(userInfo, true);
            }

        } else {
            mViewPager.setCurrentItem(0);
            mCommonTab.setCurrentTab(0);
            upUserLevel(userInfo, false);
        }

    }

    public void upUserLevel(UserModel.DataBean userInfo, boolean isSeller) {

        if (!isSeller) {   //买家
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
            mFlShop.setVisibility(View.INVISIBLE);
            mMineLevel.setVisibility(View.VISIBLE);

            BaseSharePerence.getInstance().setMeFragment(0);
            Globals.log("xxxxxx  mTitleList 04 " +BaseSharePerence.getInstance().getMeFragment());
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
            BaseSharePerence.getInstance().setMeFragment(1);

            Globals.log("xxxxxx  mTitleList 03  " +BaseSharePerence.getInstance().getMeFragment());
        }


    }


    private void httpTab() {
        HashMap<String, String> mHash = new HashMap<>();
        boolean loginStatus = BaseSharePerence.getInstance().getLoginStatus();

        if (!loginStatus) {
            return;
        }
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


    //出价 隐私 协议 政策
    private void showAgreeDialog(String type) {

        showWaitDialog();
        SceneModel.httpGetScene(type, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                SceneModel sceneModel = JSONObject.parseObject(resultData, SceneModel.class);
                if (sceneModel.getData() == null) {
                    return;
                }
                int redirectType = sceneModel.getData().getRedirectType(); //1-富文本  2-H5页面


                if (redirectType == 1) {
                    dialogUtils.showRuleProtocolDialog(getActivity(),
                            sceneModel.getData().getContent(), new RuleProtocolDialog.IButtonListener() {
                                @Override
                                public void onClose() {
                                    dialogUtils.dissRuleProtocolDialog();
                                }
                            });
                } else {

                    String url = sceneModel.getData().getH5Url();
                    if (sceneModel.getData().getH5Url().contains("?")) {
                        url += "&isMargin=4";
                    } else {
                        url += "?isMargin=4";
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("title", "协议");
                    bundle.putString("url", url);
                    ActivityManager.JumpActivity(getActivity(), AgentWebActivity.class, bundle);
                }
            }
        });
    }

}

