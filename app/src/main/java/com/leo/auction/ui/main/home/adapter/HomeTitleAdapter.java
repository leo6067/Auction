package com.leo.auction.ui.main.home.adapter;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aten.compiler.utils.DateUtil;
import com.aten.compiler.widget.countDownTime.CountdownView;
import com.aten.compiler.widget.glide.GlideUtils;
import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.ui.main.home.model.SubsidyModel;
import com.leo.auction.ui.order.model.OrderHolder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.home.adapter
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/17
 * 描    述： 百亿补贴
 * 修    改：
 * ===============================================
 */
public class HomeTitleAdapter extends BaseQuickAdapter<SubsidyModel.DataBean, BaseViewHolder> {

    private Set<OrderHolder> orderTimeSets;
    private ScheduledExecutorService mExecutorService;
    private long serverCurrentTime;
    private long elapsedRealtime;


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



    public HomeTitleAdapter() {
        super(R.layout.item_title_home, null);

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
//                    updateTime(helper, helper.getHasTime());
                }
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);

    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, SubsidyModel.DataBean json) {

        ImageView itemImage = baseViewHolder.getView(R.id.item_img);
        TextView itemPrice = baseViewHolder.getView(R.id.item_price);
        TextView itemHint = baseViewHolder.getView(R.id.item_hint);

        CountdownView itemTime = baseViewHolder.getView(R.id.item_time);
        //获取某时间与当前时间的  时间差 TimeConstants.MSEC单位毫秒
        long  fitTimeSpan = TimeUtils.getTimeSpanByNow(json.getInterceptTime(), TimeConstants.MSEC);

        itemTime.start(fitTimeSpan);

        GlideUtils.loadImg(json.getFirstPic(), itemImage);

        itemPrice.setText("￥" + json.getCurrentPrice());
        itemHint.setText("补贴￥" + json.getSubsidyMoney());


//        CountDownTimer  countDownTimer = new CountDownTimer(json.getInterceptTime(), 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//
//
////                    long day = millisUntilFinished / (1000 * 24 * 60 * 60); //单位天
//
////                    long hour = (millisUntilFinished - day * (1000 * 24 * 60 * 60)) / (1000 * 60 * 60);
//                    //单位时
////                    long minute = (millisUntilFinished - day * (1000 * 24 * 60 * 60) - hour * (1000 * 60 * 60)) / (1000 * 60);
//                    //单位分
////                    long second = (millisUntilFinished - day * (1000 * 24 * 60 * 60) - hour * (1000 * 60 * 60) - minute * (1000 * 60)) / 1000;
//                    //单位秒
//
//                long minute =  millisUntilFinished / (1000 * 60);
//                long second = (millisUntilFinished - minute * (1000 * 60)) / 1000;
//                item_time_time.setText( minute + ":" + second );
//
//            }
//
//            /**
//             *倒计时结束后调用的
//             */
//            @Override
//            public void onFinish() {
//
//            }
//
//        };
//        countDownTimer.start();
    }




}
