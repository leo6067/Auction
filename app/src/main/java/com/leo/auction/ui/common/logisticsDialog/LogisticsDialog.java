package com.leo.auction.ui.common.logisticsDialog;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aten.compiler.widget.RadiusImageView;
import com.aten.compiler.widget.dialog.animation.ZoomEnter.ZoomInEnter;
import com.aten.compiler.widget.dialog.base.BaseDialog;
import com.aten.compiler.widget.glide.GlideUtils;
import com.aten.compiler.widget.itemDecoration.timeLine.LinearDividerItemDecoration;
import com.aten.compiler.widget.itemDecoration.timeLine.StepNodeItemDecoration;
import com.leo.auction.R;
import com.leo.auction.ui.common.model.LogisticsTrajectoryModel;


/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.widget
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/10/25
 * 描    述：物流弹框
 * ================================================
 */

public class LogisticsDialog extends BaseDialog<LogisticsDialog> {
    private RelativeLayout alLogisticsHeadInfo;
    private RecyclerView rlLogistics;
    private ImageView ivClose;
    private RadiusImageView rivOrderPic;
    private TextView tvOrderId,tvOrderCreateTime,tvLogisticsCompany,tvWaybillNumber;

    private LogisticsTrajectoryModel.DataBean contents;
    private String orderCode,createTiem;

    public LogisticsDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        widthScale(0.8f);
        showAnim(new ZoomInEnter());
        dismissAnim(null);
        setCanceledOnTouchOutside(false);
        View view=View.inflate(mContext, R.layout.layout_logistics_dialog, null);
        alLogisticsHeadInfo=view.findViewById(R.id.al_logistics_head_info);
        rivOrderPic=view.findViewById(R.id.riv_order_pic);
        tvOrderId=view.findViewById(R.id.tv_order_id);
        tvOrderCreateTime=view.findViewById(R.id.tv_order_create_time);
        tvLogisticsCompany=view.findViewById(R.id.tv_logistics_company);
        tvWaybillNumber=view.findViewById(R.id.tv_waybill_number);
        rlLogistics=view.findViewById(R.id.rl_logistics);

        ivClose=view.findViewById(R.id.iv_close);
        return view;
    }

    @Override
    public void initView() {
        GlideUtils.loadImg(contents.getLogo(),rivOrderPic);
        tvOrderId.setText("订单编号："+orderCode);
        tvOrderCreateTime.setText("下单时间："+createTiem);
        tvLogisticsCompany.setText("快递公司："+contents.getExpName());
        tvWaybillNumber.setText("运单编号："+contents.getNumber());
        rlLogistics.setHasFixedSize(true);
        rlLogistics.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        LogisticsAdapter logisticsAdapter=new LogisticsAdapter(contents.getList());
        rlLogistics.setAdapter(logisticsAdapter);
        // 添加左侧节点item装饰
        StepNodeItemDecoration stepNodeItemDecoration=new StepNodeItemDecoration.Builder(getContext())
                        .setLineColor(Color.parseColor("#E9E9E9"))
                        .setLineWidth((int) mContext.getResources().getDimension(R.dimen.dp_2))
                        .setLeftMargin((int) mContext.getResources().getDimension(R.dimen.dp_28))
                        .setRightMargin((int) mContext.getResources().getDimension(R.dimen.dp_23))
                        .setDefaultDotDrawable(mContext.getResources().getDrawable(R.drawable.ic_dot_normal))
                        .setHighDotDrawable(mContext.getResources().getDrawable(R.drawable.ic_dot_high))
                        .setDotPosition(StepNodeItemDecoration.POSITION_CENTER)
                        .build();
        
        // 添加底部分隔线
        LinearDividerItemDecoration linearDividerItemDecoration=new LinearDividerItemDecoration.Builder()
                        .setOrientation(LinearLayoutManager.VERTICAL)
                        .setDividerColor(Color.parseColor("#DDDDDD"))
                        .setDividerHeight((int) mContext.getResources().getDimension(R.dimen.dp_2))
                        .isShowLastDivider(false)
                        .build();

        rlLogistics.addItemDecoration(stepNodeItemDecoration);
        rlLogistics.addItemDecoration(linearDividerItemDecoration);
        
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
    
    public LogisticsDialog setContent(String orderCode,String createTiem,LogisticsTrajectoryModel.DataBean contents) {
        this.orderCode=orderCode;
        this.createTiem=createTiem;
        this.contents=contents;
        return this;
    }
}
