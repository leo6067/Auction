package com.leo.auction.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.Toast;

import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.ToastUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.gyf.immersionbar.ImmersionBar;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.Constants;
import com.leo.auction.ui.login.LoginActivity;
import com.leo.auction.ui.main.home.model.TabEntity;
import com.leo.auction.utils.Globals;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.common_bottom)
    CommonTabLayout mCommonBottom;
    private double exitTime;

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private String[] mBottomStr = {"首页", "分类", "关注", "我的"};
    private int[] mIconUnselectIds = {
            R.drawable.home_a, R.drawable.sort_a,
            R.drawable.focus_a, R.drawable.mine_a};
    private int[] mIconSelectIds = {
            R.drawable.home_b, R.drawable.sort_b,
            R.drawable.focus_b, R.drawable.mine_b};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();



    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_main);
    }


    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this).statusBarDarkFont(true, 0.2f)
                .statusBarColor(R.color.home_title_bg)
                .titleBarMarginTop(mTitleBar)
                .keyboardEnable(true);
        mImmersionBar.init();
    }



    public void initData()  {
        ActivityManager.addActivity(this);
        initImmersionBar();
        ActivityManager.mainActivity = this;
        mViewPager.setAdapter(new TitlePagerAdapter(getSupportFragmentManager()));
        mViewPager.setOffscreenPageLimit(0);

        mFragments.add(new HomeFragment());
        mFragments.add(new MainSortFragment());
        mFragments.add(new MainFocusFragment());
        mFragments.add(new MainMeFragment());

        for (int i = 0; i <mBottomStr.length ; i++) {
            mTabEntities.add(new TabEntity(mBottomStr[i],mIconSelectIds[i],mIconUnselectIds[i]));
        }

        mCommonBottom.setTabData(mTabEntities);

        mCommonBottom.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position == 2 || position ==3){
                    if (! Constants.Var.ISLOGIN) {
                        ToastUtils.showShort("请先登录");
                        LoginActivity.newIntance(MainActivity.this);
                        return;
                    }
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
                mCommonBottom.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setCurrentItem(0);

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

            Globals.log("xxxxxxxxxxxxx  getItem" +position  );
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
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再次点击退出", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                ActivityManager.exitAPP();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
