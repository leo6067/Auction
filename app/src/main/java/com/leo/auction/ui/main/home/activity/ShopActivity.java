package com.leo.auction.ui.main.home.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.aten.compiler.widget.glide.GlideUtils;
import com.aten.compiler.widget.title.TitleBar;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.leo.auction.R;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.home.fragment.HomeAllFragment;
import com.leo.auction.ui.main.home.model.ShopModel;
import com.leo.auction.ui.main.home.model.SupplierModel;
import com.ruffian.library.widget.RImageView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class ShopActivity extends BaseActivity {


    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.shop_head)
    RImageView mShopHead;
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



    private String[] mTitleStr = {"热门", "捡漏", "最新发布", "即将截拍"};


    private String shopName = "";
    private String shopUri = "";


    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_shop);
    }

    @Override
    public void initView() {
        super.initView();


        SupplierModel.DataBean.ProductUserBean dataBean = (SupplierModel.DataBean.ProductUserBean)getIntent().getParcelableExtra("value");
        shopName = dataBean.getNickname();
        shopUri = dataBean.getUserId();




       mShopName.setText(shopName);

        Constants.Var.SHOP_TYPE = 0;

        mTitleBar.getTitleView().setText(shopName);


        for (int i = 0; i < mTitleStr.length; i++) {
            mFragments.add(new HomeAllFragment());
        }


        ShopViewAdapter shopViewAdapter = new ShopViewAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(shopViewAdapter);
        mTabLayout.setViewPager(mViewPager);


        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                Constants.Var.SHOP_TYPE = position;
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
            public void onPageSelected(int position) {
                Constants.Var.SHOP_TYPE = position;
                mTabLayout.setCurrentTab(position);
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



    }


    @Override
    public void initData() {
        super.initData();
        Constants.Var.HOME_TYPE = 0;
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
                ShopModel shopModel = JSONObject.parseObject(resultData, ShopModel.class);
                upUi(shopModel.getData());
            }
        });

    }


    private void upUi(ShopModel.DataBean dataBean) {

        GlideUtils.loadImg(dataBean.getHeadImg(),mShopHead);

//        dataBean.getLevel();  mShopLevel


//        mShopName.setText(dataBean.g);


        mShopMark.setText(dataBean.getRate());
        mShopFan.setText(dataBean.getFansNum());

        mShopRz.setVisibility(dataBean.isCompanyAuth() ? View.VISIBLE :View.GONE);
        mShopStatus.setVisibility(dataBean.isCompanyAuth() ? View.VISIBLE :View.GONE);


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
        public Fragment getItem(int i) {
            return mFragments.get(i);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }


}
