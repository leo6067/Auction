package com.leo.auction.ui.main.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;

import com.aten.compiler.base.BaseActivity;

import com.aten.compiler.widget.CustRefreshLayout;
import com.aten.compiler.widget.title.OnTitleBarListener;
import com.aten.compiler.widget.title.TitleBar;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.ui.main.MainActivity;
import com.leo.auction.ui.main.mine.adapter.PromotionCenterAdapter;
import com.leo.auction.ui.main.mine.model.PromotionCenterModel;
import com.leo.auction.utils.Globals;
import com.leo.auction.widget.PromotionCenterDividerDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/*推广中心*/
public class PromotionCenterActivivty extends BaseActivity {


    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    CustRefreshLayout mRefreshLayout;

    private PromotionCenterAdapter mAdapter;

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_promotion_center_activivty);
    }


    @Override
    public void initData() {
        super.initData();
        mTitleBar.setTitle("推广中心");

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));

        mRecyclerView.addItemDecoration(new PromotionCenterDividerDecoration(this));

        initAdapter();
    }


    public void initAdapter() {


        mAdapter = new PromotionCenterAdapter();

        ArrayList<PromotionCenterModel> promotionCenterModels = new ArrayList<>();
        PromotionCenterModel promotionCenterModel01 = new PromotionCenterModel(
                R.drawable.ic_store_qr_code_icon, "推广二维码");
        PromotionCenterModel promotionCenterModel02 = new PromotionCenterModel(
                -1, "");
        PromotionCenterModel promotionCenterModel03 = new PromotionCenterModel(
                -1, "");
        PromotionCenterModel promotionCenterModel04 = new PromotionCenterModel(
                -1, " ");
        PromotionCenterModel promotionCenterModel05 = new PromotionCenterModel(
                -1, " ");
        PromotionCenterModel promotionCenterModel06 = new PromotionCenterModel(
                -1, "");
        promotionCenterModels.add(promotionCenterModel01);
        promotionCenterModels.add(promotionCenterModel02);
        promotionCenterModels.add(promotionCenterModel03);
        promotionCenterModels.add(promotionCenterModel04);
        promotionCenterModels.add(promotionCenterModel05);
        promotionCenterModels.add(promotionCenterModel06);

        mAdapter.addData(promotionCenterModels);

        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                if (position == 0) {
                    StoreQRCodeActivity.newIntance(PromotionCenterActivivty.this);
                }
            }
        });

    }




    @Override
    public void initEvent() {
        super.initEvent();

        ((PromotionCenterAdapter) mAdapter).setOnItemListener(mOnItemListener);


//        mAdapter.loadMoreComplete();
//        mRefreshLayout.finishLoadMore();
//        mRefreshLayout.finishRefresh();
//        mAdapter.closeLoadAnimation();
//        mAdapter.setLoadMoreView(new CostomLoadMoreViewNull());
//        mAdapter.removeAllFooterView();
    }


    //item点击时间
    private View.OnClickListener mOnItemListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch ((String) view.getTag()) {

                case "推广二维码":

                    Globals.log("xxxxxx 推广二维码 01 ");
                    StoreQRCodeActivity.newIntance(PromotionCenterActivivty.this);
                    break;
//                case "抽奖":
//                    PrizeListActivity.newIntance(PromotionCenterActivivty.this);
//                    break;
//                case "超级购":
//                    SuperPurchaseActivity.newIntance(PromotionCenterActivivty.this);
//                    break;
//                case "精选好货":
//                    ChooseBestGoodsActivity.newIntance(PromotionCenterActivivty.this);
//                    break;
//                case "直播供货":
//                    LiveLoanActivity.newIntance(PromotionCenterActivivty.this,0);
//                    break;
            }
        }
    };

    public static void newIntance(Context context) {
        Intent intent = new Intent(context, PromotionCenterActivivty.class);
        context.startActivity(intent);
    }




}