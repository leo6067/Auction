package com.leo.auction.widget.customDialog;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.aten.compiler.utils.SizeUtils;
import com.aten.compiler.widget.CustomeRecyclerView;
import com.aten.compiler.widget.dialog.animation.ZoomEnter.ZoomInEnter;
import com.aten.compiler.widget.dialog.base.BaseDialog;
import com.aten.compiler.widget.dialog.utils.CornerUtils;
import com.aten.compiler.widget.switchButton.SwitchButton;
import com.leo.auction.R;
import com.leo.auction.ui.main.mine.adapter.LotteryTimeAdapter;
import com.leo.auction.ui.order.model.LotteryAttributeModel;

import java.util.ArrayList;


/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.widget
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/10/28 0028
 * 描    述：开奖时间选择弹框
 * ================================================
 */
public class LotteryTimeDialog extends BaseDialog<LotteryTimeDialog> {
    private final ArrayList<LotteryAttributeModel> lotteryAttributeModels01,lotteryAttributeModels02;
    private final ILotteryTime iLotteryTime;

    private FrameLayout flClose;
    private CustomeRecyclerView crlDate,crlTime;
    private SwitchButton sbDefault;
    private SuperButton sbtnSure;
    private TextView tvLotteryTime;
    private FrameLayout flAwardsNumberReduce,flAwardsNumberAdd,flContinuousPeriodReduce,flContinuousPeriodAdd;
    private EditText etAwardsNumber,etContinuousPeriod;
    private LotteryAttributeModel item01,item02;
    private LinearLayout llAwardsNumber;

    public LotteryTimeDialog(Context context, ArrayList<LotteryAttributeModel> lotteryAttributeModels01,
                             ArrayList<LotteryAttributeModel> lotteryAttributeModels02,ILotteryTime iLotteryTime) {
        super(context);
        this.lotteryAttributeModels01=lotteryAttributeModels01;
        this.lotteryAttributeModels02=lotteryAttributeModels02;
        this.iLotteryTime=iLotteryTime;
        item01=lotteryAttributeModels01.get(0);
        item02=lotteryAttributeModels02.get(0);
    }

    @Override
    public View onCreateView() {
        widthScale(0.8f);
        showAnim(new ZoomInEnter());
        dismissAnim(null);
        setCanceledOnTouchOutside(false);
        View view=View.inflate(mContext, R.layout.layout_lottery_time_dialog, null);
        flClose=view.findViewById(R.id.fl_close);
        crlDate=view.findViewById(R.id.crl_date);
        crlTime=view.findViewById(R.id.crl_time);
        tvLotteryTime=view.findViewById(R.id.tv_lottery_time);
        sbDefault=view.findViewById(R.id.sb_default);
        llAwardsNumber=view.findViewById(R.id.ll_awards_number);
        flAwardsNumberReduce=view.findViewById(R.id.fl_awards_number_reduce);
        etAwardsNumber=view.findViewById(R.id.et_awards_number);
        flAwardsNumberAdd=view.findViewById(R.id.fl_awards_number_add);
        flContinuousPeriodReduce=view.findViewById(R.id.fl_continuous_period_reduce);
        etContinuousPeriod=view.findViewById(R.id.et_continuous_period);
        flContinuousPeriodAdd=view.findViewById(R.id.fl_continuous_period_add);
        sbtnSure=view.findViewById(R.id.sbtn_sure);

        view.setBackgroundDrawable(CornerUtils.cornerDrawable(Color.parseColor("#ffffff"), SizeUtils.dp2px(10)));
        return view;
    }

    @Override
    public void setUiBeforShow() {
        crlDate.setHasFixedSize(true);
        crlDate.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        LotteryTimeAdapter lotteryTimeAdapter01 = new LotteryTimeAdapter(lotteryAttributeModels01, new LotteryTimeAdapter.ILotteryAttribute() {
            @Override
            public void onAttributeChoose(LotteryAttributeModel item) {
                item01=item;
                setLotteryTime();
            }
        });
        crlDate.setAdapter(lotteryTimeAdapter01);

        crlTime.setHasFixedSize(true);
        crlTime.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        LotteryTimeAdapter lotteryTimeAdapter02 = new LotteryTimeAdapter(lotteryAttributeModels02, new LotteryTimeAdapter.ILotteryAttribute() {
            @Override
            public void onAttributeChoose(LotteryAttributeModel item) {
                item02=item;
                setLotteryTime();
            }
        });
        crlTime.setAdapter(lotteryTimeAdapter02);
        setLotteryTime();

        flClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        sbDefault.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    llAwardsNumber.setVisibility(View.VISIBLE);
                }else {
                    llAwardsNumber.setVisibility(View.GONE);
                }
            }
        });

        //次数开奖减少
        flAwardsNumberReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int etAwardsNum=Integer.valueOf(etAwardsNumber.getText().toString().trim());
                if (etAwardsNum>1){
                    etAwardsNumber.setText(String.valueOf(etAwardsNum-1));
                }
            }
        });
        //次数开奖增加
        flAwardsNumberAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int etAwardsNum=Integer.valueOf(etAwardsNumber.getText().toString().trim());
                etAwardsNumber.setText(String.valueOf(etAwardsNum+1));
            }
        });

        //连续期数减少
        flContinuousPeriodReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int etContinuousNum=Integer.valueOf(etContinuousPeriod.getText().toString().trim());
                if (etContinuousNum>1){
                    etContinuousPeriod.setText(String.valueOf(etContinuousNum-1));
                }
            }
        });
        //连续期数增加
        flContinuousPeriodAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int etContinuousNum=Integer.valueOf(etContinuousPeriod.getText().toString().trim());
                etContinuousPeriod.setText(String.valueOf(etContinuousNum+1));
            }
        });

        etAwardsNumber.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode==KeyEvent.KEYCODE_DEL){
                    if ("1".equals(etAwardsNumber.getText().toString().trim())){
                        return true;
                    }
                }
                return false;
            }
        });

        etContinuousPeriod.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode==KeyEvent.KEYCODE_DEL){
                    if ("1".equals(etContinuousPeriod.getText().toString().trim())){
                        return true;
                    }
                }
                return false;
            }
        });

        sbtnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iLotteryTime.onSure(tvLotteryTime.getText().toString(),
                        sbDefault.isChecked(),
                        etAwardsNumber.getText().toString().trim(),
                        etContinuousPeriod.getText().toString().trim());
            }
        });

    }

    //设置开奖时间
    private void setLotteryTime(){
        tvLotteryTime.setText(item01.getRelData()+" "+item02.getRelData());
    }

    public interface ILotteryTime{
        void onSure(String lotteryTime, boolean checked, String awardsNumber, String continuousPeriod);
    }
}
