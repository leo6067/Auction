package com.leo.auction.ui.main.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.aten.compiler.base.BaseRecyclerView.BaseRecyclerViewActivity;
import com.aten.compiler.widget.CustRefreshLayout;
import com.aten.compiler.widget.title.TitleBar;
import com.leo.auction.R;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.ui.login.model.CommonModel;
import com.leo.auction.ui.main.mine.adapter.StoreQRCodeAdapter;
import com.leo.auction.ui.main.mine.model.StoreQRCodeModel;
import com.leo.auction.widget.customDialog.StoreQRCodeDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *推广二维码
 *
 * */
public class StoreQRCodeActivity extends BaseRecyclerViewActivity {


    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    CustRefreshLayout mRefreshLayout;

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_store_qrcode);
    }


    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public void initData() {
        super.initData();
        initHeadView();
        mTitleBar.setTitle("推广二维码");
        refreshLayout.setEnableOverScrollDrag(true);
        recyclerView.addItemDecoration(new StoreQRCodeDecoration(this));
        onRefresh(refreshLayout);
    }

    @Override
    public void initAdapter() {
        mAdapter = new StoreQRCodeAdapter();
        mAdapter.setHeaderAndEmpty(true);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
    }

    @Override
    public void initEvent() {
        super.initEvent();

        ((StoreQRCodeAdapter) mAdapter).setOnItemListener(mOnItemListener);
    }

    //初始化列表头部
    private void initHeadView() {
        View mHeadView = LayoutInflater.from(this).inflate(R.layout.layout_store_qrcode_head, recyclerView, false);

        mAdapter.addHeaderView(mHeadView);
    }

    @Override
    public void getData() {
        ArrayList<StoreQRCodeModel> storeQRCodeModels = new ArrayList<>();
//        StoreQRCodeModel storeQRCodeModel01 = new StoreQRCodeModel("https://w.taojianlou.com/image/qrcodeTwo/sj_yl.png",
//                "https://w.taojianlou.com/image/qrcodeTwo/sj.png", "店铺二维码");
//        StoreQRCodeModel storeQRCodeModel02 = new StoreQRCodeModel("https://w.taojianlou.com/image/qrcodeTwo/sy1_yl.png",
//                "https://w.taojianlou.com/image/qrcodeTwo/sy1.png", "首页二维码1");
//        StoreQRCodeModel storeQRCodeModel03 = new StoreQRCodeModel("https://w.taojianlou.com/image/qrcodeTwo/sy2_yl.png",
//                "https://w.taojianlou.com/image/qrcodeTwo/sy2.png", "首页二维码2");
//        StoreQRCodeModel storeQRCodeModel04 = new StoreQRCodeModel("https://w.taojianlou.com/image/qrcodeTwo/sy3_yl.png",
//                "https://w.taojianlou.com/image/qrcodeTwo/sy3.png", "首页二维码3");
//        StoreQRCodeModel storeQRCodeModel05 = new StoreQRCodeModel("https://w.taojianlou.com/image/qrcodeTwo/3_yl.png",
//                "https://w.taojianlou.com/image/qrcodeTwo/3.png", "代理二维码");
//        StoreQRCodeModel storeQRCodeModel06 = new StoreQRCodeModel("https://w.taojianlou.com/image/qrcodeTwo/sjsqbg_yl.png",
//                "https://w.taojianlou.com/image/qrcodeTwo/sjsqbg.png", "推荐供货商");


        CommonModel.DataBean commonJson = BaseSharePerence.getInstance().getCommonJson();


        StoreQRCodeModel storeQRCodeModel01 = new StoreQRCodeModel(commonJson.getSpread().getRecommend_fans_yl(),
                commonJson.getSpread().getRecommend_fans(), "推荐粉丝");


        StoreQRCodeModel storeQRCodeModel02 = new StoreQRCodeModel(commonJson.getSpread().getRecommend_supplier_yl(),
                commonJson.getSpread().getRecommend_supplier(), "推荐商家");


        storeQRCodeModels.add(storeQRCodeModel01);

        storeQRCodeModels.add(storeQRCodeModel02);


        mAdapter.setNewData(storeQRCodeModels);
    }

    //item点击事件
    private View.OnClickListener mOnItemListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int pos = (int) v.getTag(R.id.tag_1);
            StoreQRCodeModel item = (StoreQRCodeModel) v.getTag(R.id.tag_2);
            switch (pos) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    GenerateQRCodeActivity.newIntance(StoreQRCodeActivity.this, item.getFourImgsBg(), item.getQrCodeType());
                    break;
            }
        }
    };

    public static void newIntance(Context context) {
        Intent intent = new Intent(context, StoreQRCodeActivity.class);
        context.startActivity(intent);
    }


}
