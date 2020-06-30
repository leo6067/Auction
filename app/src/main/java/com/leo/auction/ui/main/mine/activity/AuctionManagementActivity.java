package com.leo.auction.ui.main.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.widget.tabLayout.SlidingTabLayout;
import com.leo.auction.R;
import com.leo.auction.ui.main.mine.fragment.CommodityManagementFragment;
import com.leo.auction.utils.viewPage.BaseViewPagerAndTabsAdapter;

import java.util.Arrays;

import butterknife.BindView;

public class AuctionManagementActivity extends BaseActivity {



    String[] titles = new String[]{"在售中", "已下架", "草稿箱"};
    @BindView(R.id.stb_order)
    SlidingTabLayout stbOrder;
    @BindView(R.id.vp_order)
    ViewPager vpOrder;


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
        initTabLayout();
    }

    private void initTabLayout() {
        BaseViewPagerAndTabsAdapter baseViewPagerAndTabsAdapter_new=new BaseViewPagerAndTabsAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                CommodityManagementFragment commodityManagementFragment=null;
                switch (position){
                    case 0://在售中
                        commodityManagementFragment=CommodityManagementFragment.newIntance("00A",false);
                        break;
                    case 1://已下架
                        commodityManagementFragment=CommodityManagementFragment.newIntance("00B",false);
                        break;
                    case 2://草稿箱
                        commodityManagementFragment=CommodityManagementFragment.newIntance("00C",true);
                        break;
                }
                return commodityManagementFragment;
            }
        };

        baseViewPagerAndTabsAdapter_new.setData(Arrays.asList(titles));
        vpOrder.setAdapter(baseViewPagerAndTabsAdapter_new);
        stbOrder.setViewPager(vpOrder);
    }

    public static void newIntance(Context context) {
        Intent intent = new Intent(context, AuctionManagementActivity.class);
        context.startActivity(intent);
    }
}
