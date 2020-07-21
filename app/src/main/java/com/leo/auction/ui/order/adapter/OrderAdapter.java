package com.leo.auction.ui.order.adapter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
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
public class OrderAdapter extends BaseQuickAdapter<OrderListModel.DataBean, OrderHolder> {
    private final boolean neetTime;
    private View.OnClickListener onItemListsner;
    private View.OnClickListener onBtnListsner;

    private Set<OrderHolder> orderTimeSets;
    private ScheduledExecutorService mExecutorService;
    private long serverCurrentTime;
    private long elapsedRealtime;



    private String status = "";

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

    public void setmExecutorService(ScheduledExecutorService mExecutorService) {
        this.mExecutorService = mExecutorService;
    }

    public void setOnItemListsner(View.OnClickListener onItemListsner) {
        this.onItemListsner = onItemListsner;
    }

    public void setOnBtnListsner(View.OnClickListener onBtnListsner) {
        this.onBtnListsner = onBtnListsner;
    }

    public OrderAdapter(boolean neetTime,  String status) {
        super(R.layout.layout_order_item, null);
        this.neetTime = neetTime;

        this.status = status;
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

        long fitTimeSpan;
        long oddsTime;
        long timeSpan;
        long createTime;
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
        TextView tvRealPrice01 = helper.getView(R.id.tv_real_price);
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


        //status 不传, 待付款： status=1 待发货: status=2 待收货 : status=4 待评价 : status=8 售后: status = 192 (64-售后 + 128-退款导致关闭 )
        switch (item.getStatus()) {
            case "1"://待付款
                civOrderStatus.setText("待付款");
                tv01.setText("立即付款");
                //获取某时间与当前时间的  时间差 TimeConstants.MSEC单位毫秒
                fitTimeSpan = TimeUtils.getTimeSpanByNow(item.getCreateTime(), TimeConstants.MSEC);
                oddsTime = item.getDelayMinute() * 60 * 1000;
                timeSpan = TimeUtils.getTimeSpan(fitTimeSpan, oddsTime, TimeConstants.MSEC);

                if (!item.isDelayed() && timeSpan > 0) {  //meiyou申请过延迟，出现
                    tv02.setText("延迟付款");
                }

                try {
                    if (item.isFaceTrade() && !item.isRejectFaceTrade()) { //卖家发起申请 //mei有值 买家拒绝过
                        tv03.setText("同意当面");
                        tv04.setText("拒绝当面");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                setLayoutVisibility(helper);
                break;
            case "2"://待发货
                civOrderStatus.setText("待发货");
                try {
                    if (item.isDelaySend() && !item.isRejectDelaySend()) { //拒绝过延迟发货--   ||已经申请过延迟发货
                        tv01.setText("同意延迟发货");
                        tv02.setText("拒绝延迟发货");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //获取某时间与当前时间的  时间差 TimeConstants.MSEC单位毫秒
                fitTimeSpan = TimeUtils.getTimeSpanByNow(item.getCreateTime(), TimeConstants.MSEC);
                oddsTime = item.getRemindSendViewMinute() * 60 * 1000;
                timeSpan = TimeUtils.getTimeSpan(fitTimeSpan, oddsTime, TimeConstants.MSEC);
                if (!item.isRemindSend() && timeSpan > 0) {
                    tv03.setText("提醒发货");
                }

                long refundTime = item.getNoSendRefundViewMinute() * 60 * 1000;
                long refundTimeSpan = TimeUtils.getTimeSpan(fitTimeSpan, refundTime, TimeConstants.MSEC);
                if (refundTimeSpan > 0 && item.getRefund().getStatus() == 16 || item.getRefund().getStatus() == 32) {
                    tv04.setText("申请退款");
                }

                if (item.getAddressUpdate() == 0) {
                    tv05.setText("修改地址");
                }

                setLayoutVisibility(helper);
                break;
            case "4"://待收货
                civOrderStatus.setText("待收货");
                tv01.setText("确认收货");
                try {
                    if (item.getConsignTime() > 0) {
                        tv02.setText("申请退货");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //获取某时间与当前时间的  时间差 TimeConstants.MSEC单位毫秒
                fitTimeSpan = TimeUtils.getTimeSpanByNow(item.getCreateTime(), TimeConstants.MSEC);
                oddsTime = item.getDelayConfirmTakeViewMinute() * 60 * 1000;
                timeSpan = TimeUtils.getTimeSpan(fitTimeSpan, oddsTime, TimeConstants.MSEC);
                if (!item.isDelayConfirmTake() && timeSpan > 0) {
                    tv03.setText("延迟收货");
                }

                if (item.getRefund().getStatus() == 1 || item.getRefund().getStatus() ==2
                ||item.getRefund().getStatus() ==16 ||item.getRefund().getStatus() ==128){
                    tv04.setText("取消退款");
                }



                setLayoutVisibility(helper);
                break;
            case "8"://待评价
                civOrderStatus.setText("待评价");
                tv01.setText("立即评价");
                try {
                    if (item.getConsignTime() > 0) {
                        tv02.setText("申请退货");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                setLayoutVisibility(helper);
                break;

//            case ""://全部
//                civOrderStatus.setText("交易完成");
//                String status = item.getStatus();
//                switch (status){
//                }
//                break;

        }


        allOrderProductContain.removeAllViews();
        for (OrderListModel.DataBean.ItemsBean itemsBean : item.getItems()) {
            View productLayout = LayoutInflater.from(mContext).inflate(R.layout.layout_order_product_item, allOrderProductContain, false);
            RadiusImageView rivProductPic = productLayout.findViewById(R.id.riv_product_pic);
            TextView tvProductTitle = productLayout.findViewById(R.id.tv_product_title);
            TextView itemTimeTV = productLayout.findViewById(R.id.item_time);
            TextView itemTimePrice = productLayout.findViewById(R.id.item_price);

            GlideUtils.loadImg(itemsBean.getFirstPic(),rivProductPic);
            tvProductTitle.setText(itemsBean.getTitle());
            itemTimeTV.setText("付款时间 ："+item.getPaymentTime());
            itemTimePrice.setText("成交金额 ￥"+item.getPayment());

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
        fl05.setTag(R.id.tag_1, tv05.getText().toString());
        fl05.setTag(R.id.tag_2, item);
        fl05.setTag(R.id.tag_3, helper.getAdapterPosition());
        fl05.setOnClickListener(new View.OnClickListener() {
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
//        if (countdownTime <= 0) {
//            helper.setText(R.id.tv_count_down,"00分00秒");
//            return;
//        }
//
//        helper.setText(R.id.tv_count_down, DateUtil.longToString(countdownTime));
    }

    public void setServerCurrentTime(long serverCurrentTime) {
        this.serverCurrentTime = serverCurrentTime;
        this.elapsedRealtime = SystemClock.elapsedRealtime();
    }
}
