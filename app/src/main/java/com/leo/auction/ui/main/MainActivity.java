package com.leo.auction.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.ToastUtils;
import com.blankj.utilcode.util.AppUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.gyf.immersionbar.ImmersionBar;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.login.LoginActivity;
import com.leo.auction.ui.main.home.model.TabEntity;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.ui.version.VersionDialog;
import com.leo.auction.ui.version.VersionDownDialog;
import com.leo.auction.ui.version.VersionModel;
import com.leo.auction.ui.web.AgentWebActivity;
import com.leo.auction.ui.web.AgentWebFragment;
import com.leo.auction.utils.Globals;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import okhttp3.Call;

public class MainActivity extends BaseActivity {


    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.common_bottom)
    CommonTabLayout mCommonBottom;
    private double exitTime;

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private String[] mBottomStr = {"首页", "分类", "关注", "消息", "我的"};
    private int[] mIconUnselectIds = {
            R.drawable.home_a, R.drawable.sort_a,
            R.drawable.focus_a, R.drawable.news_b, R.drawable.mine_a};
    private int[] mIconSelectIds = {
            R.drawable.home_b, R.drawable.sort_b,
            R.drawable.focus_b, R.drawable.news_a, R.drawable.mine_b};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();


    private int current = 0;
    private MineWebFragment mMineFragment;

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_main);
        initImmersionBar(R.color.home_title_bg);
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    public void initImmersionBar(int color) {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this)
                .statusBarColor(color)
                .keyboardEnable(true);
        mImmersionBar.init();
    }


    public void initData() {
        ActivityManager.addActivity(this);
        initImmersionBar();
        ActivityManager.mainActivity = this;
        mViewPager.setAdapter(new TitlePagerAdapter(getSupportFragmentManager()));
        mViewPager.setOffscreenPageLimit(0);

        mFragments.add(new HomeFragment());
        mFragments.add(new MainSortFragment());
        mFragments.add(new MainFocusFragment());
        mFragments.add(new NewsFragment());
//        mFragments.add(new MainMeFragment());

        mMineFragment = new MineWebFragment();
        mFragments.add(mMineFragment);



//        mFragments.add( MineFragment.newIntance("",httpUrl,true,false) );
        initImmersionBar(R.color.home_title_bg);

        for (int i = 0; i < mBottomStr.length; i++) {
            mTabEntities.add(new TabEntity(mBottomStr[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        mCommonBottom.setTabData(mTabEntities);

        mCommonBottom.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

                UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();
                if (position == 2 || position == 3 || position == 4) {
                    if (userJson ==null) {
                        ToastUtils.showShort("请先登录");
                        finish();
                        LoginActivity.newIntance(MainActivity.this, 1);
                        return;
                    }
                }

                if (position == 0 || position == 2 || position == 4) {
                    initImmersionBar(R.color.home_title_bg);
                } else {
                    initImmersionBar(R.color.white);
                }




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
                Globals.log("xxxxxx mViewPager"+ position );
                UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();
                if (position == 2 || position == 3 || position == 4) {
                    if (userJson ==null) {
                        ToastUtils.showShort("请先登录");
                        finish();
                        LoginActivity.newIntance(MainActivity.this, 1);
                        return;
                    }
                }

                if (position == 0 || position == 2 || position == 4) {
                    initImmersionBar(R.color.home_title_bg);
                } else {
                    initImmersionBar(R.color.white);
                }
                mCommonBottom.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


        current = getIntent().getIntExtra("currentTab", 0);
        mViewPager.setCurrentItem(current);
        mCommonBottom.setCurrentTab(current);
        httpVerison();
    }


    public void setCurrent(int current) {
        mViewPager.setCurrentItem(current);
        mCommonBottom.setCurrentTab(current);
    }


    public void httpVerison() {
        VersionModel.httpGetVersion(new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
            }

            @Override
            public void httpResponse(String resultData) {
                VersionModel returnData = JSONObject.parseObject(resultData, VersionModel.class);
                HashMap<String, String> mHashMap = new HashMap<>();
                mHashMap.put("isForce", returnData.getData().isForce() + "");
                mHashMap.put("downUrl", returnData.getData().getDownload());
                if (Integer.parseInt(returnData.getData().getVersion()) == AppUtils.getAppVersionCode()) {
                    return;
                }


                VersionDialog versionDialog = new VersionDialog(MainActivity.this, mHashMap, new VersionDialog.VersionInter() {
                    @Override
                    public void versionOK() {
//                        VersionDownDialog downDialog = new VersionDownDialog(MainActivity.this,"https://imtt.dd.qq.com/16891/apk/C831AEEA8BCC274A9EBA11DB22BBC375.apk");
                        VersionDownDialog downDialog = new VersionDownDialog(MainActivity.this, returnData.getData().getDownload());
                        downDialog.show();
                        downDialog.setCanceledOnTouchOutside(false);
                    }

                    @Override
                    public void versionCancel() {
                    }
                });
                versionDialog.show();
                versionDialog.setCanceledOnTouchOutside(false);
            }
        });
    }


    public void setCurrentItem(int position) {
        mViewPager.setCurrentItem(position);
    }

    public void setBottomGone() {
        mCommonBottom.setVisibility(View.GONE);
    }


    public void setBottomVisible() {
        mCommonBottom.setVisibility(View.VISIBLE);
    }


    private class TitlePagerAdapter extends FragmentPagerAdapter {

        public TitlePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mBottomStr.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mBottomStr[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }


    public static void newIntance(Context context, int currentTab) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("currentTab", currentTab);
        context.startActivity(intent);

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {


            if (mMineFragment != null && mMineFragment.mAgentWeb != null && mMineFragment.mAgentWeb.getWebCreator().getWebView().canGoBack()) {
                mMineFragment.mAgentWeb.getWebCreator().getWebView().goBack();
                return true;
            }


            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtils.showShort("锤定：再次点击退出");
                exitTime = System.currentTimeMillis();
            } else {
                ActivityManager.exitAPP();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
