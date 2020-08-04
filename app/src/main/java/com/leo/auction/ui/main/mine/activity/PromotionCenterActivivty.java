package com.leo.auction.ui.main.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;

import com.aten.compiler.base.BaseRecyclerView.BaseRecyclerViewActivity;
import com.aten.compiler.widget.CostomLoadMoreViewNull;
import com.aten.compiler.widget.CustRefreshLayout;
import com.aten.compiler.widget.title.OnTitleBarListener;
import com.aten.compiler.widget.title.TitleBar;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.ui.main.MainActivity;
import com.leo.auction.ui.main.mine.adapter.PromotionCenterAdapter;
import com.leo.auction.ui.main.mine.model.PromotionCenterModel;
import com.leo.auction.widget.PromotionCenterDividerDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/*推广中心*/
public class PromotionCenterActivivty extends BaseRecyclerViewActivity {


    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    CustRefreshLayout mRefreshLayout;

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_promotion_center_activivty);
    }



    @Override
    public void initData() {
        super.initData();
        mTitleBar.setTitle("推广中心");
        recyclerView.addItemDecoration(new PromotionCenterDividerDecoration(this));
        onRefresh(refreshLayout);

        mTitleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                ActivityManager.JumpActivity(PromotionCenterActivivty.this, MainActivity.class);
                ActivityManager.mainActivity.recreateActivity();
                finish();
            }

            @Override
            public void onTitleClick(View v) {
            }

            @Override
            public void onRightClick(View v) {

            }
        });

    }

    @Override
    public void initAdapter() {
        mAdapter = new PromotionCenterAdapter();
        mAdapter.setLoadMoreView(new CostomLoadMoreViewNull());
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
    }

    @Override
    public void initEvent() {
        super.initEvent();

        ((PromotionCenterAdapter) mAdapter).setOnItemListener(mOnItemListener);
    }

    @Override
    public void getData() {
        ArrayList<PromotionCenterModel> promotionCenterModels = new ArrayList<>();

        PromotionCenterModel promotionCenterModel01 = new PromotionCenterModel(
                R.drawable.ic_store_qr_code_icon, "推广二维码");
        PromotionCenterModel promotionCenterModel02 = new PromotionCenterModel(
                -1, "" );
        PromotionCenterModel promotionCenterModel03 = new PromotionCenterModel(
                -1, "");
        PromotionCenterModel promotionCenterModel04 = new PromotionCenterModel(
                -1, " ");
        PromotionCenterModel promotionCenterModel05 = new PromotionCenterModel(
                -1, " ");
        PromotionCenterModel promotionCenterModel06 = new PromotionCenterModel(
                -1, "");
//
//        if (UserTypeUtils.getInstance().isAgent()){
//            promotionCenterModels.add(promotionCenterModel01);promotionCenterModels.add(promotionCenterModel06);
//            promotionCenterModels.add(promotionCenterModel06);promotionCenterModels.add(promotionCenterModel06);
//            promotionCenterModels.add(promotionCenterModel06);promotionCenterModels.add(promotionCenterModel06);
//        }else if(UserTypeUtils.getInstance().isSeller()){
//            promotionCenterModels.add(promotionCenterModel01);promotionCenterModels.add(promotionCenterModel02);
//            promotionCenterModels.add(promotionCenterModel03);promotionCenterModels.add(promotionCenterModel04);
//            promotionCenterModels.add(promotionCenterModel05);promotionCenterModels.add(promotionCenterModel06);
//        }else if (UserTypeUtils.getInstance().isCommon()){
//            promotionCenterModels.add(promotionCenterModel01);promotionCenterModels.add(promotionCenterModel06);
//            promotionCenterModels.add(promotionCenterModel06);promotionCenterModels.add(promotionCenterModel06);
//            promotionCenterModels.add(promotionCenterModel06);promotionCenterModels.add(promotionCenterModel06);
//        }
        promotionCenterModels.add(promotionCenterModel01);
        promotionCenterModels.add(promotionCenterModel02);
        promotionCenterModels.add(promotionCenterModel03);
        promotionCenterModels.add(promotionCenterModel04);
        promotionCenterModels.add(promotionCenterModel05);
        promotionCenterModels.add(promotionCenterModel06);

        mAdapter.setNewData(promotionCenterModels);
    }

    //item点击时间
    private View.OnClickListener mOnItemListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch ((String) view.getTag()) {
                case "推广二维码":
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            ActivityManager.JumpActivity(PromotionCenterActivivty.this, MainActivity.class);
            ActivityManager.mainActivity.recreateActivity();
            ActivityManager.mainActivity.setCurrent(4);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



}