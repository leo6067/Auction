package com.leo.auction.ui.main.home.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.BroadCastReceiveUtils;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.glide.GlideUtils;
import com.aten.compiler.widget.title.TitleBar;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.gyf.immersionbar.ImmersionBar;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.BaseModel;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.CommonUsedData;
import com.leo.auction.base.Constants;

import com.leo.auction.net.HttpRequest;
import com.leo.auction.net.ResultModel;
import com.leo.auction.ui.login.LoginActivity;
import com.leo.auction.ui.login.model.CommonModel;
import com.leo.auction.ui.main.MainActivity;
import com.leo.auction.ui.main.SharedActvity;
import com.leo.auction.ui.main.home.fragment.ShopAllFragment;
import com.leo.auction.ui.main.home.model.ShopModel;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.utils.Globals;
import com.leo.auction.utils.shared_dailog.SharedModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class ShopActivity extends BaseActivity {


    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.shop_head)
    ImageView mShopHead;
    @BindView(R.id.shop_level)
    ImageView mShopLevel;
    @BindView(R.id.shop_name)
    TextView mShopName;
    @BindView(R.id.shop_rz)
    ImageView mShopRz;
    @BindView(R.id.shop_status)
    TextView mShopStatus;
    @BindView(R.id.shop_mark)
    TextView mShopMark;
    @BindView(R.id.shop_fan)
    TextView mShopFan;
    @BindView(R.id.appbar_layout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.tab_layout)
    SlidingTabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tab_home)
    TextView mTabHome;
    @BindView(R.id.tab_sort)
    TextView mTabSort;
    @BindView(R.id.tab_focus)
    TextView mTabFocus;
    @BindView(R.id.tab_news)
    TextView mTabNews;
    @BindView(R.id.tab_mine)
    TextView mTabMine;

    @BindView(R.id.shop_focus)
    TextView mShopFocus;


    private String[] mTitleStr = {"热门", "捡漏", "最新发布", "即将截拍"};


    private String shopName = "";
    private String shopUri = "";


    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ShopModel mShopModel;
    private UserModel.DataBean mUserJson;

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_shop);
        initImmersionBar(R.color.home_title_bg);
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    public void initImmersionBar(int color) {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .statusBarColor(color)
                .keyboardEnable(true);
        mImmersionBar.init();
    }


    @Override
    public void initView() {
        super.initView();
        initImmersionBar();
        mUserJson = BaseSharePerence.getInstance().getUserJson();
        shopUri = getIntent().getExtras().getString("shopUri");
        shopName = getIntent().getExtras().getString("shopName");
        mShopName.setText(shopName);
        Constants.Var.SHOP_TYPE = 0;
        mTitleBar.getTitleView().setText(shopName);
        mTitleBar.setRightIcon(R.drawable.right_more);

        for (int i = 0; i < mTitleStr.length; i++) {
            mFragments.add(ShopAllFragment.newIntance(shopUri));
        }


        ShopViewAdapter shopViewAdapter = new ShopViewAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(shopViewAdapter);
        mTabLayout.setViewPager(mViewPager);

        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                Constants.Var.SHOP_TYPE = position;
                mViewPager.setCurrentItem(position);
                BroadCastReceiveUtils.sendLocalBroadCast(ShopActivity.this, Constants.Action.ACTION_SHOP_TYPE, position + "");
//                Globals.log("xxxxxxxxxxxxxxxx  shopType  mViewPager " + position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int Offset = Math.abs(verticalOffset); //目的是将负数转换为绝对正数；
                if (Offset <= getResources().getDimension(R.dimen.dp_260)) {
                    float alpha = (float) Offset / getResources().getDimension(R.dimen.dp_260);
                    mTitleBar.getTitleView().setTextColor(ColorUtils.blendARGB(Color.TRANSPARENT
                            , ContextCompat.getColor(ShopActivity.this, R.color.white), alpha));
                } else {
                    mTitleBar.getTitleView().setTextColor(ColorUtils.blendARGB(Color.TRANSPARENT
                            , ContextCompat.getColor(ShopActivity.this, R.color.white), 1));
                }
            }
        });


        mShopFocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserJson = BaseSharePerence.getInstance().getUserJson();

                if (mUserJson == null) {
                    LoginActivity.newIntance(ShopActivity.this, 0);
                    return;
                }
                httpFocus();
            }
        });
    }


    public void httpFocus() {

        showWaitDialog();
        BaseModel.httpPostFocus(mShopModel.getData().getUserAccountId() + "", "1", new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                BaseModel baseModel = JSONObject.parseObject(resultData, BaseModel.class);
                if (baseModel.getResult().isSuccess()) {
                    ToastUtils.showShort("关注成功");
                    mShopFocus.setVisibility(View.GONE);
                } else {
                    ToastUtils.showShort(baseModel.getResult().getMessage());
                }
            }
        });


    }


    @Override
    public void initData() {
        super.initData();

        Constants.Action.ACTION_ACTION = "3";
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("shopUri", shopUri);
        showWaitDialog();
        HttpRequest.httpGetString(Constants.Api.SHOP_URL, hashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                mShopModel = JSONObject.parseObject(resultData, ShopModel.class);
                upUi(mShopModel.getData());
            }
        });


        HttpRequest.httpGetString(Constants.Api.SHOP_FOCUS_URL, hashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {
                ResultModel resultModel = JSONObject.parseObject(resultData, ResultModel.class);
                if (resultModel.getData().equals("true")) {
                    mShopFocus.setVisibility(View.GONE);
                }
            }
        });


    }


    private void upUi(ShopModel.DataBean dataBean) {

        GlideUtils.loadImg(dataBean.getHeadImg(), mShopHead);
//        dataBean.getLevel();  mShopLevel
//        mShopName.setText(dataBean.g);


        CommonModel.DataBean commonJson = BaseSharePerence.getInstance().getCommonJson();
        String[] myLevelVPicS = commonJson.getSeller_level_pic().get(0).split("seller_level_");
        String vipUrl = myLevelVPicS[0] + "seller_level_" + dataBean.getLevel() + ".png";
        GlideUtils.loadImgDefault(vipUrl, mShopLevel);


        mShopMark.setText(dataBean.getRate());
        mShopFan.setText(dataBean.getFansNum());


        if (dataBean.isCompanyAuth()) {
            mShopStatus.setText("企业认证");
        } else {
            mShopStatus.setText("个人认证");
        }



        ArrayList<String> imgStr = new ArrayList<>();
        imgStr.add(dataBean.getHeadImg());


        String type = "2";//1-推荐粉丝  2-推荐商家  3-拍品详情 4-超级仓库商品详情

        mTitleBar.getRightView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserJson == null) {
                    LoginActivity.newIntance(ShopActivity.this, 0);
                    return;
                }


                ArrayList<String> shareShopTitlelList = CommonUsedData.getInstance().getShareShopTitlelList();
                Random ra = new Random();
                int anInt = ra.nextInt(shareShopTitlelList.size());
                String shareTitle = "【锤定】" + shopName + shareShopTitlelList.get(anInt);
                String path = Constants.WebApi.SHARE_SHOP_URL + dataBean.getShopUri()
                        + "&tpm_shareAgentId=" + mUserJson.getUserId();
                SharedModel sharedModel = new SharedModel(shareTitle, shareTitle, dataBean.getHeadImg(),
                        "0.00", dataBean.getHeadImg(), type, path, dataBean.getShopUri(), mUserJson.getUserId(),
                        Constants.Action.ACTION_ACTION);
                SharedActvity.newIntance(ShopActivity.this, sharedModel, imgStr, shareTitle, "");
            }
        });


    }


    @OnClick({R.id.tab_home, R.id.tab_sort, R.id.tab_focus, R.id.tab_news, R.id.tab_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tab_home:
                MainActivity.newIntance(ShopActivity.this, 0);
                break;
            case R.id.tab_sort:
                ActivityManager.JumpActivity(ShopActivity.this, MainActivity.class);
//                MainActivity.newIntance(AuctionDetailActivity.this, 4);
                ActivityManager.mainActivity.setCurrent(1);
                break;
            case R.id.tab_focus:
                if (mUserJson == null) {
                    LoginActivity.newIntance(ShopActivity.this, 0);
                    return;
                }
                ActivityManager.JumpActivity(ShopActivity.this, MainActivity.class);
//                MainActivity.newIntance(AuctionDetailActivity.this, 4);
                ActivityManager.mainActivity.setCurrent(2);

                break;
            case R.id.tab_news:
                if (mUserJson == null) {
                    LoginActivity.newIntance(ShopActivity.this, 0);
                    return;
                }
                ActivityManager.JumpActivity(ShopActivity.this, MainActivity.class);
//                MainActivity.newIntance(AuctionDetailActivity.this, 4);
                ActivityManager.mainActivity.setCurrent(3);

                break;
            case R.id.tab_mine:
                if (mUserJson == null) {
                    LoginActivity.newIntance(ShopActivity.this, 0);
                    return;
                }
                ActivityManager.JumpActivity(ShopActivity.this, MainActivity.class);
//                MainActivity.newIntance(AuctionDetailActivity.this, 4);
                ActivityManager.mainActivity.setCurrent(4);
                break;
        }
    }


    private class ShopViewAdapter extends FragmentPagerAdapter {
        public ShopViewAdapter(FragmentManager fm) {
            super(fm);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleStr[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }


}
