package com.leo.auction.ui.main.mine.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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
import com.aten.compiler.base.BaseFragment;
import com.aten.compiler.base.BaseRecyclerView.BaseRecyclerViewFragment;
import com.aten.compiler.base.BaseRecyclerView.SpaceItemDecoration;
import com.aten.compiler.utils.BroadCastReceiveUtils;
import com.aten.compiler.utils.KeyboardUtils;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.CustRefreshLayout;
import com.aten.compiler.widget.itemDecoration.timeLine.LinearDividerItemDecoration;
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
import com.leo.auction.common.widget.LinearLayoutDivider;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.home.activity.AuctionDetailActivity;
import com.leo.auction.ui.main.mine.activity.AuctionUpperActivity;
import com.leo.auction.ui.main.mine.activity.CommodityEditActivity;
import com.leo.auction.ui.main.mine.adapter.AuctionManagementAdapter;
import com.leo.auction.ui.main.mine.model.ProductListModel;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;


//竞拍中
public class AuctionAFragment extends BaseRecyclerViewFragment {


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
        return R.layout.fragment_auction_a;
    }


    @Override
    public void initView(View view) {
        super.initView(view);
        initDrawerLayout();
        BroadCastReceiveUtils.registerLocalReceiver(getActivity(), Constants.Action.ACTION_MANAGEMENT_TYPE, mBroadCastReceiveUtils);
    }

    @Override
    protected void initAdapter() {
        super.initAdapter();


        mAdapter = new AuctionManagementAdapter(0, new AuctionManagementAdapter.InterAuctionManage() {
            @Override
            public void setOnAuctionUpListener(ProductListModel.DataBean item) {
                Bundle bundle = new Bundle();

                if ("2".equals(item.getSourceType())) {
                    bundle.putString("value", item.getGoodsId());
                } else {
                    bundle.putString("value", item.getProductInstanceCode());
                }

                bundle.putString("type", item.getSourceType());
                ActivityManager.JumpActivity(getActivity(), AuctionUpperActivity.class, bundle);
            }

            @Override
            public void setOnAutioDownListsner(ProductListModel.DataBean item) {
                HashMap<String, Object> mHash = new HashMap<>();
                mHash.put("content", "是否确认下架该拍品");
                WarningDialog warningDialog = new WarningDialog(getActivity(), mHash);
                warningDialog.show();
                warningDialog.setWarningClickListener(new WarningDialog.OnWarningClickListener() {
                    @Override
                    public void onWarningOk() {
                        httpDownAuction(item.getProductInstanceCode());

                    }

                    @Override
                    public void onWaringCancel() {

                    }
                });

            }

            @Override
            public void setOnAuctionDeleteListener(ProductListModel.DataBean item) {

            }
        });

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                ProductListModel.DataBean item = (ProductListModel.DataBean) mAdapter.getData().get(position);
                Bundle bundle = new Bundle();
                if ("2".equals(item.getSourceType())) {
                    bundle.putString("goodsCode", item.getGoodsId());
                } else {
                    bundle.putString("goodsCode", item.getProductInstanceCode());
                }
                bundle.putString("type", item.getSourceType());
                ActivityManager.JumpActivity(getActivity(), AuctionDetailActivity.class,bundle);
            }
        });
    }


    @Override
    public void initData() {
        super.initData();
        Constants.Var.PPGL_SORT_TYPE = 0;
        timeStr = "createTime";
        priceStr = "currentPrice";
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
    protected void getData() {
        super.getData();


        //竞拍中 时间排序按 createTime
        //已截拍 时间排序按 intercept_time
        //已失败 时间排序按 modify_time
        //价格排序 按 currentPrice
        Constants.Var.PPGL_SORT_TYPE = 0;
        HashMap<String, String> mhash = new HashMap<>();

        mhash.put("status", Constants.Var.PPGL_SORT_VALUE + "");
        mhash.put("pageNum", mPageNum + "");
        mhash.put("pageSize", Constants.Var.LIST_NUMBER);
        mhash.put("sort", "1");
        mhash.put("startPrice", startPrice);
        mhash.put("endPrice", endPrice);
        mhash.put("sortField", sortField);

        HttpRequest.httpGetString(Constants.Api.PRODUCT_URL, mhash, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {
                hideRefreshView();
                ProductListModel productListModel = JSONObject.parseObject(resultData, ProductListModel.class);
                if (mPageNum == 1) {
                    mAdapter.setNewData(productListModel.getData());
                } else {
                    mAdapter.addData(productListModel.getData());
                    mAdapter.loadMoreComplete();
                }

                if (productListModel.getData().isEmpty()) {
                    mPageNum = 0;
                } else if (mAdapter.getData().size() < Constants.Var.LIST_NUMBER_INT) {
                    mAdapter.loadMoreEnd(true);
                } else {
                    mAdapter.loadMoreEnd();
                }
            }
        });


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


    //设置筛选的数据 newTab:true:切换新的筛选条件
//    private void setSelectStatus(String field, ImageView iv, boolean newTab) {
//        ivTime.setImageResource(R.drawable.ic_no_select);
//        ivSalesNum.setImageResource(R.drawable.ic_no_select);
//        ivPrice.setImageResource(R.drawable.ic_no_select);


    private void httpDownAuction(String productInstanceCode) {
        showWaitDialog();
        BaseModel.httpDownAuction(productInstanceCode, new HttpRequest.HttpCallback() {
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        BroadCastReceiveUtils.unregisterLocalReceiver(getActivity(), mBroadCastReceiveUtils);
    }
}
