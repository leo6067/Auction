package com.leo.auction.ui.main.mine.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aten.compiler.base.BaseRecyclerView.BaseRecyclerViewActivity;
import com.aten.compiler.widget.CustRefreshLayout;
import com.aten.compiler.widget.search.SearchTextView;
import com.aten.compiler.widget.title.TitleBar;
import com.billy.android.swipe.SmartSwipe;
import com.billy.android.swipe.SwipeConsumer;
import com.billy.android.swipe.consumer.DrawerConsumer;
import com.leo.auction.R;
import com.leo.auction.ui.main.mine.adapter.SuperHouseAdapter;
import com.leo.auction.ui.main.mine.model.SuperHouseModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SuperHouseActivity extends BaseRecyclerViewActivity {


    private static final String TAG = "SuperHouseActivity";
    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.live_search)
    SearchTextView mLiveSearch;
    @BindView(R.id.tv_num)
    TextView mTvNum;
    @BindView(R.id.iv_time)
    ImageView mIvTime;
    @BindView(R.id.ll_time)
    LinearLayout mLlTime;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.iv_price)
    ImageView mIvPrice;
    @BindView(R.id.ll_price)
    LinearLayout mLlPrice;
    @BindView(R.id.ll_screent)
    LinearLayout mLlScreent;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    CustRefreshLayout mRefreshLayout;



    private String sortField = "intime", sort = "1", keyword = "",startPrice="",endPrice="";
    private SwipeConsumer mCurrentDrawerConsumer;
    private EditText etMinPrice,etMaxPrice;

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_super_house);
    }


    @Override
    public void initAdapter() {
        super.initAdapter();

        mAdapter = new SuperHouseAdapter();
    }



    //初始化侧滑菜单
    private void initDrawerLayout() {
//        llScreen.setTag(false);
//        rightDrawerLayout = LayoutInflater.from(this).inflate(R.layout.layout_sort_search_screen, null);
//        rightDrawerLayout.setLayoutParams(new ViewGroup.LayoutParams((int) getResources().getDimension(R.dimen.dp_600), ViewGroup.LayoutParams.MATCH_PARENT));
//        rightDrawerLayout.setClickable(true);
//
//        etMinPrice=rightDrawerLayout.findViewById(R.id.et_min_price);
//        etMaxPrice=rightDrawerLayout.findViewById(R.id.et_max_price);
//        sbtnSure=rightDrawerLayout.findViewById(R.id.sbtn_sure);
//        sbtnReset=rightDrawerLayout.findViewById(R.id.sbtn_reset);
//
////        SmartSwipeWrapper rightMenuWrapper = SmartSwipe.wrap(horizontalMenu).addConsumer(new StretchConsumer()).enableVertical().getWrapper();
//        DrawerConsumer mDrawerConsumer = new DrawerConsumer()
//                .setRightDrawerView(rightDrawerLayout)
//                .setScrimColor(0x7F000000)
//                .setShadowColor(0x80000000)
//                .setShadowSize(SmartSwipe.dp2px(10, this))
//                .addListener(listener)
//                .as(DrawerConsumer.class);
//        mCurrentDrawerConsumer = SmartSwipe.wrap(this)
//                .addConsumer(mDrawerConsumer)
//                .lockRight();
    }





    @Override
    public void getData() {
        super.getData();
        String string = mLiveSearch.getText().toString();

//        SuperHouseModel.httpGetSuperHouse(string){
//
//        }


    }

    @OnClick({R.id.ll_time, R.id.ll_price, R.id.ll_screent})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_time:
//                mIvTime

                break;
            case R.id.ll_price:
                break;
            case R.id.ll_screent:
                break;
        }
    }
}
