package com.leo.auction.ui.main.mine.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.allen.library.SuperButton;
import com.aten.compiler.base.BaseRecyclerView.BaseRecyclerViewFragment;
import com.aten.compiler.utils.BroadCastReceiveUtils;
import com.aten.compiler.utils.KeyboardUtils;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.CustRefreshLayout;
import com.billy.android.swipe.SmartSwipe;
import com.billy.android.swipe.SmartSwipeWrapper;
import com.billy.android.swipe.SwipeConsumer;
import com.billy.android.swipe.consumer.DrawerConsumer;
import com.billy.android.swipe.listener.SimpleSwipeListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.BaseModel;
import com.leo.auction.base.Constants;
import com.leo.auction.common.dialog.WarningDialog;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.mine.activity.AuctionUpperActivity;
import com.leo.auction.ui.main.mine.activity.CommodityEditActivity;
import com.leo.auction.ui.main.mine.adapter.AuctionManagementAdapter;
import com.leo.auction.ui.main.mine.model.ProductListModel;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuctionDFragment extends BaseRecyclerViewFragment  {



    @BindView(R.id.iv_time)
    ImageView mIvTime;
    @BindView(R.id.ll_time)
    LinearLayout mLlTime;
    @BindView(R.id.iv_price)
    ImageView mIvPrice;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.ll_price)
    LinearLayout mLlPrice;
    @BindView(R.id.ll_screent)
    LinearLayout mLlScreent;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    CustRefreshLayout mRefreshLayout;



    private EditText mEtMinPrice;
    private EditText mEtMaxPrice;
    private SwipeConsumer mCurrentDrawerConsumer;

    private String startPrice = "", endPrice = "", sortField = "", timeStr = "", priceStr = "";

    BroadCastReceiveUtils mBroadCastReceiveUtils = new BroadCastReceiveUtils() {
        @Override
        public void onReceive(Context context, Intent intent) {

            onRefresh(refreshLayout);

        }
    };


    @Override
    public int getLayoutId() {
        return R.layout.fragment_auction_d;
    }




    @Override
    public void initView(View view) {
        super.initView(view);
        initDrawerLayout();
        BroadCastReceiveUtils.registerLocalReceiver(getActivity(),Constants.Action.ACTION_MANAGEMENT_TYPE,mBroadCastReceiveUtils);
    }

    @Override
    protected void initAdapter() {
        super.initAdapter();

        mAdapter = new AuctionManagementAdapter(3,new AuctionManagementAdapter.InterAuctionManage() {
            @Override
            public void setOnAuctionUpListener(ProductListModel.DataBean item) {

                Bundle bundle = new Bundle();

//                if (Constants.Var.PPGL_SORT_TYPE ==3){
//                    bundle.putString("value", item.getProductId()+"");
//                }else {
//                    if ("2".equals(item.getSourceType())) {
//                        bundle.putString("value", item.getGoodsId());
//                    } else {
//                        bundle.putString("value", item.getProductInstanceCode());
//                    }
//                }

                bundle.putString("value", item.getProductId()+"");
                bundle.putString("type", item.getSourceType());
                ActivityManager.JumpActivity(getActivity(), AuctionUpperActivity.class, bundle);
            }

            @Override
            public void setOnAutioDownListsner(ProductListModel.DataBean item) {

            }

            @Override
            public void setOnAuctionDeleteListener(ProductListModel.DataBean item) {
                HashMap<String, Object> mHash = new HashMap<>();
                mHash.put("content", "是否确认删除该拍品");
                WarningDialog warningDialog = new WarningDialog(getActivity(), mHash);
                warningDialog.show();
                warningDialog.setWarningClickListener(new WarningDialog.OnWarningClickListener() {
                    @Override
                    public void onWarningOk() {
                        httpDeleteAuction(item.getProductId() + "");

                    }

                    @Override
                    public void onWaringCancel() {

                    }
                });
            }
        });

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                ProductListModel.DataBean item = (ProductListModel.DataBean) mAdapter.getData().get(position);
                Bundle bundle = new Bundle();
                bundle.putString("value", item.getProductId()+"");
                bundle.putString("type", item.getSourceType());
                ActivityManager.JumpActivity(getActivity(), CommodityEditActivity.class,bundle);
            }
        });
    }



    @Override
    public void initData() {
        super.initData();
        Constants.Var.PPGL_SORT_TYPE = 3;
        timeStr = "modify_time";//时间-modify_time    价格-  markup_range
        priceStr = "markup_range";//时间-modify_time    价格-  markup_range
        sortField = timeStr;
        onRefresh(refreshLayout);
    }





    //监听侧滑菜单的关闭和打开
    SimpleSwipeListener listener = new SimpleSwipeListener() {
        @Override
        public void onSwipeOpened(SmartSwipeWrapper wrapper, SwipeConsumer consumer, int direction) {
            super.onSwipeOpened(wrapper, consumer, direction);
            mLlScreent.setTag(true);
            mEtMinPrice.post(new Runnable() {
                @Override
                public void run() {
                    mEtMinPrice.setText(startPrice);
                    mEtMaxPrice.setText(endPrice);
                }
            });
        }

        @Override
        public void onSwipeClosed(SmartSwipeWrapper wrapper, SwipeConsumer consumer, int direction) {
            super.onSwipeClosed(wrapper, consumer, direction);
            mLlScreent.setTag(false);
            KeyboardUtils.hideSoftInput(mEtMinPrice);
        }
    };

    //初始化侧滑菜单
    private void initDrawerLayout() {
        mLlScreent.setTag(false);
        View rightDrawerLayout = LayoutInflater.from(getActivity()).inflate(R.layout.layout_sort_search_screen, null);
        rightDrawerLayout.setLayoutParams(new ViewGroup.LayoutParams((int) getResources().getDimension(R.dimen.dp_600), ViewGroup.LayoutParams.MATCH_PARENT));
        rightDrawerLayout.setClickable(true);

        mEtMinPrice = rightDrawerLayout.findViewById(R.id.et_min_price);
        mEtMaxPrice = rightDrawerLayout.findViewById(R.id.et_max_price);
        SuperButton sbtnSure = rightDrawerLayout.findViewById(R.id.sbtn_sure);
        SuperButton sbtnReset = rightDrawerLayout.findViewById(R.id.sbtn_reset);

//        SmartSwipeWrapper rightMenuWrapper = SmartSwipe.wrap(horizontalMenu).addConsumer(new StretchConsumer()).enableVertical().getWrapper();
        DrawerConsumer mDrawerConsumer = new DrawerConsumer()
                .setRightDrawerView(rightDrawerLayout)
                .setScrimColor(0x7F000000)
                .setShadowColor(0x80000000)
                .setShadowSize(SmartSwipe.dp2px(10, getActivity()))
                .addListener(listener)
                .as(DrawerConsumer.class);
        mCurrentDrawerConsumer = SmartSwipe.wrap(getActivity())
                .addConsumer(mDrawerConsumer)
                .lockRight();
    }


    @Override
    public void getData() {
        super.getData();


        //竞拍中 时间排序按 createTime
        //已截拍 时间排序按 intercept_time
        //已失败 时间排序按 modify_time
        //价格排序 按 currentPrice
        Constants.Var.PPGL_SORT_TYPE = 3;
        HashMap<String, String> mhash = new HashMap<>();

        mhash.put("status", Constants.Var.PPGL_SORT_VALUE + "");
        mhash.put("pageNum", mPageNum + "");
        mhash.put("pageSize", Constants.Var.LIST_NUMBER);
        mhash.put("sort", "1");
        mhash.put("startPrice", startPrice);
        mhash.put("endPrice", endPrice);
        mhash.put("sortField", sortField);
        if (Constants.Var.PPGL_SORT_TYPE == 3) {  //草稿箱
            HttpRequest.httpGetString(Constants.Api.PRODUCT_DRAFT_URL, mhash, new HttpRequest.HttpCallback() {
                @Override
                public void httpError(Call call, Exception e) {

                }

                @Override
                public void httpResponse(String resultData) {
                    ProductListModel productListModel = JSONObject.parseObject(resultData, ProductListModel.class);

                    if (mPageNum == 1) {
                        mAdapter.setNewData(productListModel.getData());
                    } else {
                        mAdapter.addData(productListModel.getData());
                        mAdapter.loadMoreComplete();
                    }

                    if (productListModel.getData().isEmpty()) {
                        mPageNum = 1;
                    } else if (productListModel.getData().size() > Constants.Var.LIST_NUMBER_INT) {
                        mAdapter.loadMoreEnd(true);
                    } else {
                        mAdapter.loadMoreEnd();
                    }
                }
            });
        } else {
            HttpRequest.httpGetString(Constants.Api.PRODUCT_URL, mhash, new HttpRequest.HttpCallback() {
                @Override
                public void httpError(Call call, Exception e) {

                }

                @Override
                public void httpResponse(String resultData) {

                    ProductListModel productListModel = JSONObject.parseObject(resultData, ProductListModel.class);
                    if (mPageNum == 1) {
                        mAdapter.setNewData(productListModel.getData());
                    } else {
                        mAdapter.addData(productListModel.getData());
                        mAdapter.loadMoreComplete();
                    }

                    if (productListModel.getData().isEmpty()) {
                        mPageNum = 1;
                    } else if (productListModel.getData().size() < Constants.Var.LIST_NUMBER_INT) {
                        mAdapter.loadMoreEnd(true);
                    } else  {
                        mAdapter.loadMoreEnd();
                    }
                }
            });
        }


    }


    @OnClick({R.id.ll_time, R.id.ll_price, R.id.ll_screent})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_time:
                sortField = timeStr;
                onRefresh(refreshLayout);
                mTvTime.setTextColor(Color.parseColor("#7c1313"));
                mTvPrice.setTextColor(Color.parseColor("#525252"));
                mIvTime.setBackgroundResource(R.drawable.tip_top);
                mIvPrice.setBackgroundResource(R.drawable.tip_tip);
                break;
            case R.id.ll_price:
                sortField = priceStr;
                onRefresh(refreshLayout);
                mTvPrice.setTextColor(Color.parseColor("#7c1313"));
                mTvTime.setTextColor(Color.parseColor("#525252"));
                mIvPrice.setBackgroundResource(R.drawable.tip_top);
                mIvTime.setBackgroundResource(R.drawable.tip_tip);
                break;
            case R.id.ll_screent:
                if ((boolean) mLlScreent.getTag()) {
                    mCurrentDrawerConsumer.smoothClose();
                } else {
                    mCurrentDrawerConsumer.smoothRightOpen();
                }
                break;
            case R.id.sbtn_sure:
                startPrice = mEtMinPrice.getText().toString().trim();
                endPrice = mEtMaxPrice.getText().toString().trim();
                mCurrentDrawerConsumer.smoothClose();
                onRefresh(refreshLayout);
                break;
            case R.id.sbtn_reset:
                mEtMinPrice.setText("");
                mEtMaxPrice.setText("");
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        BroadCastReceiveUtils.unregisterLocalReceiver(getActivity() ,mBroadCastReceiveUtils);
    }

    private void httpDeleteAuction(String productId) {
        showWaitDialog();
        BaseModel.httpDeleteAuction(productId, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                BaseModel baseModel = JSONObject.parseObject(resultData, BaseModel.class);
                ToastUtils.showShort(baseModel.getResult().getMessage());
            }
        });
    }
}
