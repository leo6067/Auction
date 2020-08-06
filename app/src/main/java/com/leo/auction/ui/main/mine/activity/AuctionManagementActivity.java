package com.leo.auction.ui.main.mine.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.BroadCastReceiveUtils;
import com.aten.compiler.widget.tabLayout.SlidingTabLayout;
import com.aten.compiler.widget.title.TitleBar;
import com.leo.auction.R;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.mine.fragment.AuctionAFragment;
import com.leo.auction.ui.main.mine.fragment.AuctionBFragment;
import com.leo.auction.ui.main.mine.fragment.AuctionCFragment;
import com.leo.auction.ui.main.mine.fragment.AuctionDFragment;

import com.leo.auction.ui.main.mine.model.CateProductModel;
import com.leo.auction.utils.Globals;
import com.leo.auction.utils.viewPage.BaseViewPagerAndTabsAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class AuctionManagementActivity extends BaseActivity {

    String[] titles = new String[]{"竞拍中", "已截拍", "已失效", "草稿箱"};
    @BindView(R.id.stb_order)
    SlidingTabLayout stbOrder;
    @BindView(R.id.vp_order)
    ViewPager vpOrder;
    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    private List<CateProductModel.DataBean> mDataBeanList;

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_auction_management);
    }

    @Override
    public void initData() {
        super.initData();
        mTitleBar.setTitle("拍品管理");
        httpTab();
    }


    private void httpTab() {
        HashMap<String, String> mHash = new HashMap<>();
        showWaitDialog();
        HttpRequest.httpGetString(Constants.Api.CATE_PRODUCT_URL, mHash, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                CateProductModel cateProductModel = JSONObject.parseObject(resultData, CateProductModel.class);
                mDataBeanList = cateProductModel.getData();
                initTabLayout();
            }
        });

    }


    private void initTabLayout() {

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new AuctionAFragment());
        fragments.add(new AuctionBFragment());
        fragments.add(new AuctionCFragment());  //失效
        fragments.add(new AuctionDFragment()); //草稿箱


        BaseViewPagerAndTabsAdapter baseViewPagerAndTabsAdapter = new BaseViewPagerAndTabsAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
        };


        vpOrder.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0://竞拍中
                        Constants.Var.PPGL_SORT_TYPE = 0;

                        break;
                    case 1://已截拍
                        Constants.Var.PPGL_SORT_TYPE = 1;

                        break;
                    case 2://已失效
                        Constants.Var.PPGL_SORT_TYPE = 2;

                        break;
                    case 3://草稿箱
                        Constants.Var.PPGL_SORT_TYPE = 3;
                        break;
                }
                BroadCastReceiveUtils.sendLocalBroadCast(AuctionManagementActivity.this,Constants.Action.ACTION_MANAGEMENT_TYPE);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        Constants.Var.PPGL_SORT_TYPE = 0;
        baseViewPagerAndTabsAdapter.setData(Arrays.asList(titles));
        vpOrder.setAdapter(baseViewPagerAndTabsAdapter);
        stbOrder.setViewPager(vpOrder);
    }



}
