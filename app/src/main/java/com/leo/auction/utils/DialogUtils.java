package com.leo.auction.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.aten.compiler.utils.RxTool;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.customerDialog.BottomDialogUtils;
import com.aten.compiler.widget.loadingView.SpinView02;
import com.leo.auction.R;
import com.leo.auction.common.dialog.WarningDialog;
import com.leo.auction.ui.common.logisticsDialog.LogisticsDialog;
import com.leo.auction.ui.common.model.LogisticsTrajectoryModel;
import com.leo.auction.ui.main.mine.dialog.ReleaseProtocolDialog;
import com.leo.auction.ui.main.mine.dialog.RuleProtocolDialog;
import com.leo.auction.ui.order.adapter.CancleOrderAdapter;
import com.leo.auction.ui.order.model.CancleOrderModel;
import com.leo.auction.ui.order.model.LotteryAttributeModel;
import com.leo.auction.widget.customDialog.ExchangeLotteryTimesDialog;
import com.leo.auction.widget.customDialog.FriendshipTipsDialog;
import com.leo.auction.widget.customDialog.GoldCoinAwardPop;
import com.leo.auction.widget.customDialog.LotteryTimeDialog;
import com.leo.auction.widget.customDialog.SupRuleDialog;
import com.leo.auction.widget.customDialog.SureReceiveGoodDialog;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.utils
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/10/27
 * 描    述：
 * ================================================
 */
public class DialogUtils {



    private WarningDialog mWarningDialog;


    public void showWarnDialog(Context context,HashMap<String,Object> hashMap){

        mWarningDialog = new WarningDialog(context, hashMap);
        mWarningDialog.show();
    }








    /*********************************************   LogisticsDialog   start ****************************************************/
    private LogisticsDialog logisticsDialog;
    //显示materisDialog
    public void showLogisticsDialog(Context context, String orderCode, String createTiem, LogisticsTrajectoryModel.DataBean contents){
        if (contents!=null){
            logisticsDialog=new LogisticsDialog(context);
            logisticsDialog.setContent(orderCode,createTiem,contents)
                    .show();
        }else {
            ToastUtils.showShort("物流数据有误，请重新进入页面");
        }
    }

    //关闭aterisDialogm
    public void dissLogisticsDialog(){
        if (logisticsDialog!=null&&logisticsDialog.isShowing()){
            logisticsDialog.dismiss();
            logisticsDialog=null;
        }
    }



    /*********************************************   SureReceiveGoodDialog   start ****************************************************/
    private SureReceiveGoodDialog sureReceiveGoodDialog;

    //显示materisDialog
    public void showSureReceiveGoodDialog(Context context, SureReceiveGoodDialog.IReceiveGoods iReceiveGoods){
        sureReceiveGoodDialog=new SureReceiveGoodDialog(context,iReceiveGoods);
        sureReceiveGoodDialog.show();
    }


    //关闭aterisDialogm
    public void dissSureReceiveGoodDialog(){
        if (sureReceiveGoodDialog!=null&&sureReceiveGoodDialog.isShowing()){
            sureReceiveGoodDialog.dismiss();
            sureReceiveGoodDialog=null;
        }
    }


    /*********************************************   GoldCoinAwardDialog   start ****************************************************/
    private GoldCoinAwardPop goldCoinAwardDialog;
    //显示获得金币弹框
    public void showGoldCoinAwardDialog(Context context, String rewardNum){
//        goldCoinAwardDialog=new GoldCoinAwardDialog(context,rewardNum);
//        goldCoinAwardDialog.show();

        goldCoinAwardDialog = new GoldCoinAwardPop(RxTool.getContext()).setText(rewardNum);
        goldCoinAwardDialog
                .setOutSideTouchable(true)
                .setOutSideDismiss(false)
                .setBackground(null)
                .setPopupGravity(Gravity.CENTER)
                .showPopupWindow();
    }

    //关闭aterisDialogm
    public void dissGoldCoinAwardDialog(){
        if (goldCoinAwardDialog!=null){
            goldCoinAwardDialog.stopAnim();
            goldCoinAwardDialog.dismiss();
            goldCoinAwardDialog.onRecycle();
            goldCoinAwardDialog=null;
        }
    }


    /*********************************************   ExchangeLotteryTimesDialog   start ****************************************************/
    private ExchangeLotteryTimesDialog exchangeLotteryTimesDialog;
    //显示获得金币弹框
    public void showExchangeLotteryTimesDialog(Context context, int balanceCoin, int hasLotteryNum, ExchangeLotteryTimesDialog.IExchangLottery iExchangLottery){
        exchangeLotteryTimesDialog=new ExchangeLotteryTimesDialog(context,balanceCoin,hasLotteryNum,iExchangLottery);
        exchangeLotteryTimesDialog.show();
    }

    //关闭aterisDialogm
    public void dissExchangeLotteryTimesDialog(){
        if (exchangeLotteryTimesDialog!=null&&exchangeLotteryTimesDialog.isShowing()){
            exchangeLotteryTimesDialog.dismiss();
            exchangeLotteryTimesDialog=null;
        }
    }



    /*********************************************   showRefundReason   start ****************************************************/
    private BottomDialogUtils bottomDialogUtils;
    //显示退款原因的弹框
    public void showRefundReasonDialog(Context context, ArrayList<CancleOrderModel> cancleOrderModels, final IRefundReason iRefundReason) {
        View view= View.inflate(context, R.layout.layout_return_order,null);
        RecyclerView rlCancleOrder=view.findViewById(R.id.rl_cancle_order);
        SpinView02 ivSpinView= view.findViewById(R.id.iv_spinView);
        TextView tvSure=view.findViewById(R.id.tv_sure);

        final CancleOrderAdapter cancleOrderAdapter=new CancleOrderAdapter();
        rlCancleOrder.setHasFixedSize(true);
        rlCancleOrder.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
        rlCancleOrder.setAdapter(cancleOrderAdapter);
        cancleOrderAdapter.setNewData(cancleOrderModels);

        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bottomDialogUtils!=null){
                    bottomDialogUtils.dismissCustomerDialog();
                }
                CancleOrderModel cancleOrderModel=cancleOrderAdapter.getChooseItem();
                iRefundReason.RefundReasonTrue(cancleOrderModel);
            }
        });

        bottomDialogUtils = new BottomDialogUtils(context);
        bottomDialogUtils.showCustomerDialog(view,"退款原因");
    }

    //关闭退款原因
    public void dissRefundReasonDialog(){
        if (bottomDialogUtils!=null){
            bottomDialogUtils.dismissCustomerDialog();
        }
    }

    public interface IRefundReason{
        void RefundReasonTrue(CancleOrderModel cancleOrderModel);
    }


    /*********************************************   ReleaseProtocolDialog   start ****************************************************/
    private ReleaseProtocolDialog releaseProtocolDialoge;
    //显示materisDialog
    public void showReleaseProtocolDialog(Context context, String content, ReleaseProtocolDialog.IButtonListener iButtonListener){
        releaseProtocolDialoge=new ReleaseProtocolDialog(context,content,iButtonListener);
        releaseProtocolDialoge.show();
    }

    //关闭aterisDialogm
    public void dissReleaseProtocolDialog(){
        if (releaseProtocolDialoge!=null&&releaseProtocolDialoge.isShowing()){
            releaseProtocolDialoge.dismiss();
            releaseProtocolDialoge=null;
        }
    }




    //发布协议弹框
  private SupRuleDialog SupRuleDialogDialog;

    public void showSupRuleDialog(Context context, String content,  SupRuleDialog.IButtonListener iButtonListener){
        SupRuleDialogDialog=new SupRuleDialog(context,content,iButtonListener);
        SupRuleDialogDialog.show();
    }

    //关闭aterisDialogm
    public void dissSupRuleDialog(){
        if (SupRuleDialogDialog!=null&&SupRuleDialogDialog.isShowing()){
            SupRuleDialogDialog.dismiss();
            SupRuleDialogDialog=null;
        }
    }


    /*********************************************   LotteryTimeDialog   start ****************************************************/

    //物流信息
    private LotteryTimeDialog lotteryTimeDialog;
    public void showLotteryTimeDialog(Context context, ArrayList<LotteryAttributeModel> lotteryAttributeModels01,
                                      ArrayList<LotteryAttributeModel> lotteryAttributeModels02, LotteryTimeDialog.ILotteryTime iLotteryTime){
        lotteryTimeDialog=new LotteryTimeDialog(context,lotteryAttributeModels01,lotteryAttributeModels02,iLotteryTime);
        lotteryTimeDialog.show();
    }

    //关闭aterisDialogm
    public void dissLotteryTimeDialog(){
        if (lotteryTimeDialog!=null&&lotteryTimeDialog.isShowing()){
            lotteryTimeDialog.dismiss();
            lotteryTimeDialog=null;
        }
    }


    /*********************************************   RuleProtocolDialog   start ****************************************************/
    //规则
    private RuleProtocolDialog ruleProtocolDialog;
    public void showRuleProtocolDialog(Context context, String content, RuleProtocolDialog.IButtonListener iButtonListener){
        ruleProtocolDialog=new RuleProtocolDialog(context,content,iButtonListener);
        ruleProtocolDialog.show();
    }

    //关闭aterisDialogm
    public void dissRuleProtocolDialog(){
        if (ruleProtocolDialog!=null&&ruleProtocolDialog.isShowing()){
            ruleProtocolDialog.dismiss();
            ruleProtocolDialog=null;
        }
    }





    /**
     * 订单温馨提示
     * */


















}
