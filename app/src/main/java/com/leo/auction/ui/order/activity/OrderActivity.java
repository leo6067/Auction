package com.leo.auction.ui.order.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.base.BaseRecyclerView.BaseRecyclerViewFragment;
import com.aten.compiler.widget.tabLayout.SlidingTabLayout;
import com.aten.compiler.widget.title.TitleBar;
import com.leo.auction.R;
import com.leo.auction.ui.order.fragment.OrderFragment;
import com.leo.auction.ui.order.fragment.SellerOrderFragment;
import com.leo.auction.utils.viewPage.BaseViewPagerAndTabsAdapter;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends BaseActivity {


    String[] titles = new String[]{"全部", "待付款", "待发货", "待收货", "待评价"};
    @BindView(R.id.stb_order)
    SlidingTabLayout stbOrder;
    @BindView(R.id.vp_order)
    ViewPager vpOrder;
    @BindView(R.id.title_bar)
    TitleBar mTitleBar;

    private int currentTab;
    private int isSeller;
    private String status = "";

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_order);
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public void initData() {
        currentTab = getIntent().getIntExtra("currentTab", 0);
        isSeller = getIntent().getIntExtra("isSeller", 0);
        status = getIntent().getStringExtra("status");


        if (isSeller == 1) {
            mTitleBar.setTitle("买家订单");
        } else {
            mTitleBar.setTitle("卖家订单");
        }

        super.initData();
        initTabLayout();
    }

    private void initTabLayout() {
        BaseViewPagerAndTabsAdapter baseViewPagerAndTabsAdapter_new = new BaseViewPagerAndTabsAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {  ////全部订单: status 不传,  待付款status=1    待发货: status=2 待收货 : status=4 待评价 : status=8   售后: status = 192 (64-售后 + 128-退款导致关闭 )
                //在售中
                BaseRecyclerViewFragment orderFragment = null;

                if (isSeller == 1) {    // 1：买家  2 卖家
                    orderFragment = OrderFragment.newIntance(status);   //买家订单
                } else {
                    orderFragment = SellerOrderFragment.newIntance(status); //卖家订单
                }
//                switch (position) {
//                    case 0:////全部
//                        if (isSeller == 1) {
//                            orderFragment = OrderFragment.newIntance(status, isSeller);
//                        } else {
//                            orderFragment = SellerOrderFragment.newIntance(status, isSeller);
//                        }
//                        break;
//                    case 1://待付款
//                        if (isSeller == 1) {
//                            orderFragment = OrderFragment.newIntance(status, isSeller);
//                        } else {
//                            orderFragment = SellerOrderFragment.newIntance(status, isSeller);
//                        }
//                        break;
//                    case 2://待发货
//                        if (isSeller == 1) {
//                            orderFragment = OrderFragment.newIntance(status, isSeller);
//                        } else {
//                            orderFragment = SellerOrderFragment.newIntance(status, isSeller);
//                        }
//                        break;
//                    case 3://待收货
//                        if (isSeller == 1) {
//                            orderFragment = OrderFragment.newIntance(status, isSeller);
//                        } else {
//                            orderFragment = SellerOrderFragment.newIntance(status, isSeller);
//                        }
//                        break;
//                    case 4://待评价
//                        if (isSeller == 1) {
//                            orderFragment = OrderFragment.newIntance(status, isSeller);
//                        } else {
//                            orderFragment = SellerOrderFragment.newIntance(status, isSeller);
//                        }
//                        break;
//                }
                return orderFragment;
            }
        };

        baseViewPagerAndTabsAdapter_new.setData(Arrays.asList(titles));
        vpOrder.setAdapter(baseViewPagerAndTabsAdapter_new);
        stbOrder.setViewPager(vpOrder);

        stbOrder.setCurrentTab(currentTab);
    }

    public static void newIntance(Context context, int currentTab, int isSeller, String status) {  //角度  1-买家角度(默认1)   2-卖家角度
        Intent intent = new Intent(context, OrderActivity.class);
        intent.putExtra("currentTab", currentTab);
        intent.putExtra("isSeller", isSeller);
        intent.putExtra("status", status);
        context.startActivity(intent);
    }


}
