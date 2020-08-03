package com.leo.auction.ui.order.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseRecyclerView.BaseRecyclerViewActivity;
import com.aten.compiler.base.BaseRecyclerView.SpaceItemDecoration;
import com.gyf.immersionbar.ImmersionBar;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.MainActivity;
import com.leo.auction.ui.main.home.adapter.HomeAdapter;
import com.leo.auction.ui.main.home.model.HomeListModel;
import com.leo.auction.utils.DialogUtils;

import okhttp3.Call;

/****
 *
 * 支付成功
 *
 * */
public class GoodOrederStatusActivity extends BaseRecyclerViewActivity {

    private String instanceCode, price;
    private DialogUtils dialogUtils;


    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_good_oreder_status);
    }


    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this).statusBarDarkFont(false)
                .keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    public void initData() {
        instanceCode = getIntent().getStringExtra("instanceCode");
        price = getIntent().getStringExtra("price");
        dialogUtils = new DialogUtils();
        super.initData();

        ImmersionBar.setTitleBarMarginTop(this, mTitleBar);
        initHeadView();

        showWaitDialog();
        onRefresh(refreshLayout);


    }

    @Override
    public void initAdapter() {
        recyclerView.addItemDecoration(new SpaceItemDecoration((int) getResources().getDimension(R.dimen.dp_20), 2));
        DisplayMetrics dm = getResources().getDisplayMetrics();
        mAdapter = new HomeAdapter(dm.widthPixels - ((int) getResources().getDimension(R.dimen.dp_10)) * 2);
        mAdapter.setHeaderAndEmpty(true);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
    }

    //初始化列表头部
    private void initHeadView() {
        View mHeadView = LayoutInflater.from(this).inflate(R.layout.layout_order_status_head, recyclerView, false);
        ImageView ivStatus = mHeadView.findViewById(R.id.iv_status);
        TextView tvStatus = mHeadView.findViewById(R.id.tv_status);
        TextView tvToHome = mHeadView.findViewById(R.id.tv_to_home);
        TextView tvLookOrder = mHeadView.findViewById(R.id.tv_look_order);

        setTitle("购买成功");
        ivStatus.setImageResource(R.drawable.ic_hook_press);
        tvStatus.setText("购买成功");
        tvToHome.setVisibility(View.VISIBLE);
        tvLookOrder.setVisibility(View.VISIBLE);


        tvToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ActivityManager.JumpActivity(GoodOrederStatusActivity.this,MainActivity.class);
                finish();

//                MainActivity.newIntance(GoodOrderActivity.this, 0);
//                goFinish();
            }
        });

        tvLookOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goFinish();
//                OrderActivity.newIntance(GoodOrderActivity.this, 2);
            }
        });

        mAdapter.addHeaderView(mHeadView);
    }

    @Override
    public void getData() {
        HomeListModel.httpGetOrderLike(instanceCode, price, mPageNum + "", new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {
                HomeListModel homeListModel = JSONObject.parseObject(resultData, HomeListModel.class);
                if (mPageNum == 1) {
                    mAdapter.setNewData(homeListModel.getData());
                } else {
                    mAdapter.addData(homeListModel.getData());
                    mAdapter.loadMoreComplete();
                }
                if (homeListModel.getData().isEmpty()) {
                    mPageNum = 1;
                } else if (homeListModel.getData().size() > Constants.Var.LIST_NUMBER_INT) {
                    mAdapter.loadMoreEnd(true);
                } else {
                    mAdapter.loadMoreEnd();
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
//        MainActivity.newIntance(GoodOrderActivity.this,0);
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        if (dialogUtils != null) {
            dialogUtils.dissGoldCoinAwardDialog();
        }
        super.onDestroy();
    }

    //status:状态名称 0代表成功
    public static void newIntance(Context context, String shopUri, String status) {
        Intent intent = new Intent(context, GoodOrderActivity.class);
        intent.putExtra("shopUri", shopUri);
        intent.putExtra("status", status);
        context.startActivity(intent);
    }
}
