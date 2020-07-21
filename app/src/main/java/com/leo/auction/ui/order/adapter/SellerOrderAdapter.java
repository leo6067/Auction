package com.leo.auction.ui.order.adapter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aten.compiler.utils.DateUtil;
import com.aten.compiler.utils.DecimalFormatUtils;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.widget.CircleImageView;
import com.aten.compiler.widget.RadiusImageView;
import com.aten.compiler.widget.glide.GlideUtils;
import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.ui.order.WeakRefHandler;
import com.leo.auction.ui.order.model.OrderHolder;
import com.leo.auction.ui.order.model.OrderListModel;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;


/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.ui.order.adapter
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/10/25
 * 描    述：
 * ================================================
 */
public class SellerOrderAdapter extends BaseQuickAdapter<OrderListModel.DataBean, OrderHolder> {
    private View.OnClickListener onItemListsner;
    private View.OnClickListener onBtnListsner;

    private final boolean neetTime;
    private Set<OrderHolder> orderTimeSets;
    private ScheduledExecutorService mExecutorService;
    private long serverCurrentTime;
    private long elapsedRealtime;

    long fitTimeSpan;
    long oddsTime;
    long timeSpan;


    private String orderType = "ssssssssssssssss";

    private Handler.Callback mCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            long time = msg.getData().getLong("time", 0);
            TextView tv = (TextView) msg.obj;
            switch (msg.what) {
                case 0:
                    tv.setText("00分00秒");
                    break;
                case 1:
                    tv.setText(DateUtil.longToString(time));
                    break;
            }
            return true;
        }
    };
    private Handler mHandler = new WeakRefHandler(mCallback);

    public Set<OrderHolder> getOrderTimeSets() {
        return orderTimeSets;
    }

    public ScheduledExecutorService getmExecutorService() {
        return mExecutorService;
    }

    public void setOnItemListsner(View.OnClickListener onItemListsner) {
        this.onItemListsner = onItemListsner;
    }

    public void setOnBtnListsner(View.OnClickListener onBtnListsner) {
        this.onBtnListsner = onBtnListsner;
    }

    public SellerOrderAdapter(boolean neetTime, String orderType) {
        super(R.layout.layout_order_item, null);
        this.neetTime = neetTime;
        this.orderType = orderType;
        if (neetTime) {
            orderTimeSets = new HashSet<>();
            mExecutorService = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
                @Override
                public Thread newThread(@NonNull Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setName("countdown");
                    return thread;
                }
            });
            mExecutorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    for (OrderHolder helper : orderTimeSets) {
                        updateTime(helper, helper.getHasTime());
                    }
                }
            }, 0, 1000, TimeUnit.MILLISECONDS);
        }
    }

    @Override
    protected void convert(@NonNull OrderHolder helper, OrderListModel.DataBean item) {
        if (neetTime) {
            helper.setHasTime(item.getExpire());
            orderTimeSets.add(helper);
            updateTime(helper, helper.getHasTime());
        }

        CircleImageView civOrderHead = helper.getView(R.id.civ_order_head);
        TextView civOrderShopName = helper.getView(R.id.civ_order_shop_name);
        TextView civOrderStatus = helper.getView(R.id.civ_order_status);
        LinearLayout allOrderProductContain = helper.getView(R.id.all_order_product_contain);

        TextView tvLogisticsCompany = helper.getView(R.id.tv_logistics_company);
        TextView tvFreight01 = helper.getView(R.id.tv_freight);
        TextView tvRealPriceAll = helper.getView(R.id.tv_real_price);
        FrameLayout fl01 = helper.getView(R.id.fl_01);
        TextView tv01 = helper.getView(R.id.tv_01);
        FrameLayout fl02 = helper.getView(R.id.fl_02);
        TextView tv02 = helper.getView(R.id.tv_02);
        FrameLayout fl03 = helper.getView(R.id.fl_03);
        TextView tv03 = helper.getView(R.id.tv_03);
        FrameLayout fl04 = helper.getView(R.id.fl_04);
        TextView tv04 = helper.getView(R.id.tv_04);

        FrameLayout fl05 = helper.getView(R.id.fl_05);
        TextView tv05 = helper.getView(R.id.tv_05);

        GlideUtils.loadImg(item.getHeadImg(), civOrderHead);
        civOrderShopName.setText(EmptyUtils.strEmpty(item.getNickname()));

        tvLogisticsCompany.setVisibility(View.GONE);

        tvFreight01.setVisibility(View.GONE);

//        1-待付款 2-已付款 4-已发货 8-交易成功 16-交易关闭 32-已评价 64-售后 128-退款导致关闭
        switch (item.getStatus()) {
            case "1"://待付款
                civOrderStatus.setText("等待买家付款");
                fitTimeSpan = TimeUtils.getTimeSpanByNow(item.getCreateTime(), TimeConstants.MSEC);
                oddsTime = item.getFaceTradeMinute() * 60 * 1000;
                timeSpan = TimeUtils.getTimeSpan(fitTimeSpan, oddsTime, TimeConstants.MSEC);
                if (!item.isFaceTrade() && timeSpan > 0) {
                    tv01.setText("当面交易");
                } else {
                    tv01.setText("查看详情");
                }
//                long oddsDelaySendTime = item.getDelayMinute() * 60 * 1000;
//                long DelaytimeSpan = TimeUtils.getTimeSpan(fitTimeSpan, oddsDelaySendTime, TimeConstants.MSEC);
//                if (!item.isDelaySend() && DelaytimeSpan > 0) {
//                    tv02.setText("延迟付款");
//                }
                setLayoutVisibility(helper);
                break;
            case "2"://待发货
                civOrderStatus.setText("待发货");
                tv01.setText("立即发货");
//                if (item.getAddressUpdate() == 0) {
//                    tv02.setText("修改地址");
//                }
                //获取某时间与当前时间的  时间差 TimeConstants.MSEC单位毫秒
                fitTimeSpan = TimeUtils.getTimeSpanByNow(item.getCreateTime(), TimeConstants.MSEC);
                oddsTime = item.getDelayConfirmTakeViewMinute() * 60 * 1000;
                timeSpan = TimeUtils.getTimeSpan(fitTimeSpan, oddsTime, TimeConstants.MSEC);
                if (!item.isDelayConfirmTake() && timeSpan > 0) {
                    tv03.setText("延迟发货");
                }

                tv04.setText("一键代发");
                setLayoutVisibility(helper);
                break;
            case "4"://待收货
                civOrderStatus.setText("已发货");
//                tv01.setText("查看详情");
                if (item.getExpressUpdate() == 0) {
                    tv02.setText("修改单号");
                }
                setLayoutVisibility(helper);
                break;
            case "8"://待评价
                civOrderStatus.setText("");
                tv01.setText("查看详情");
                setLayoutVisibility(helper);
                break;

            case "64"://售后
                if ("".equals(orderType)) { //全部订单的时候才可以出现
                    if (item.getRefund().getStatus() == 1) {
                        tv02.setText("同意退款");
                        tv02.setText("拒绝退款");
                    }
                }
                break;
        }

        allOrderProductContain.removeAllViews();
        boolean numTag = false;
        for (OrderListModel.DataBean.ItemsBean itemsBean : item.getItems()) {
            if (itemsBean.getNum() > 1) {
                numTag = true;
                break;
            }
        }

        for (OrderListModel.DataBean.ItemsBean itemsBean : item.getItems()) {
            View productLayout = LayoutInflater.from(mContext).inflate(R.layout.layout_order_product_item, allOrderProductContain, false);
            RadiusImageView rivProductPic = productLayout.findViewById(R.id.riv_product_pic);
            TextView tvProductTitle = productLayout.findViewById(R.id.tv_product_title);
            TextView itemTimeTV = productLayout.findViewById(R.id.item_time);
            TextView itemTimePrice = productLayout.findViewById(R.id.item_price);

            GlideUtils.loadImg(itemsBean.getFirstPic(), rivProductPic);
            tvProductTitle.setText(itemsBean.getTitle());
            itemTimeTV.setText("付款时间 ：" + item.getPaymentTime());
            itemTimePrice.setText("成交金额 ￥" + item.getPayment());

            allOrderProductContain.addView(productLayout);
        }

        helper.itemView.setTag(item);
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemListsner.onClick(v);
            }
        });

        fl01.setTag(R.id.tag_1, tv01.getText().toString());
        fl01.setTag(R.id.tag_2, item);
        fl01.setTag(R.id.tag_3, helper.getAdapterPosition());
        fl01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnListsner.onClick(v);
            }
        });

        fl02.setTag(R.id.tag_1, tv02.getText().toString());
        fl02.setTag(R.id.tag_2, item);
        fl02.setTag(R.id.tag_3, helper.getAdapterPosition());
        fl02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnListsner.onClick(v);
            }
        });

        fl03.setTag(R.id.tag_1, tv03.getText().toString());
        fl03.setTag(R.id.tag_2, item);
        fl03.setTag(R.id.tag_3, helper.getAdapterPosition());
        fl03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnListsner.onClick(v);
            }
        });

        fl04.setTag(R.id.tag_1, tv04.getText().toString());
        fl04.setTag(R.id.tag_2, item);
        fl04.setTag(R.id.tag_3, helper.getAdapterPosition());
        fl04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnListsner.onClick(v);
            }
        });
    }


    //设置不同场景下 页面的展示不同
    private void setLayoutVisibility(@NonNull BaseViewHolder helper) {
        LinearLayout allAggregatePayment = helper.getView(R.id.all_aggregate_payment);
        TextView tvCountDown = helper.getView(R.id.tv_count_down);
        LinearLayout llBtn = helper.getView(R.id.ll_btn);
        FrameLayout fl01 = helper.getView(R.id.fl_01);
        FrameLayout fl02 = helper.getView(R.id.fl_02);
        FrameLayout fl03 = helper.getView(R.id.fl_03);
        FrameLayout fl04 = helper.getView(R.id.fl_04);
        FrameLayout fl05 = helper.getView(R.id.fl_05);

        TextView tv01 = helper.getView(R.id.tv_01);
        TextView tv02 = helper.getView(R.id.tv_02);
        TextView tv03 = helper.getView(R.id.tv_03);
        TextView tv04 = helper.getView(R.id.tv_04);
        TextView tv05 = helper.getView(R.id.tv_05);

        fl01.setVisibility(tv01.getText().toString().trim().length() == 0 ? View.GONE : View.VISIBLE);
        fl02.setVisibility(tv02.getText().toString().trim().length() == 0 ? View.GONE : View.VISIBLE);
        fl03.setVisibility(tv03.getText().toString().trim().length() == 0 ? View.GONE : View.VISIBLE);
        fl04.setVisibility(tv04.getText().toString().trim().length() == 0 ? View.GONE : View.VISIBLE);
        fl05.setVisibility(tv05.getText().toString().trim().length() == 0 ? View.GONE : View.VISIBLE);
    }


    public void setServerCurrentTime(long serverCurrentTime) {
        this.serverCurrentTime = serverCurrentTime;
        this.elapsedRealtime = SystemClock.elapsedRealtime();
    }

    //刷新倒计时
    private void updateTime(final BaseViewHolder helper, final long time) {
        long countdownTime = (time - serverCurrentTime - (SystemClock.elapsedRealtime() - elapsedRealtime)) / 1000;
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putLong("time", countdownTime);
        msg.setData(bundle);
        msg.obj = helper.getView(R.id.tv_count_down);

        if (countdownTime <= 0) {
            msg.what = 0;
        } else {
            msg.what = 1;
        }

        mHandler.sendMessage(msg);
    }
}
