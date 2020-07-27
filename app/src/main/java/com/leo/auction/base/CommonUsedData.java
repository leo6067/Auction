package com.leo.auction.base;

import com.aten.compiler.utils.DateUtil;
import com.leo.auction.R;
import com.leo.auction.model.CommonModel;
import com.leo.auction.net.CustomerJsonCallBack;
import com.leo.auction.ui.order.model.CancelReasonModel;
import com.leo.auction.ui.order.model.CancleOrderModel;
import com.leo.auction.ui.order.model.EvaluationLableModel;
import com.leo.auction.ui.order.model.LotteryAttributeModel;
import com.leo.auction.ui.order.model.OrderPayTypeModel;


import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * ================================================
 * 项目名称：dgonline-android
 * 包    名：com.aten.dgonline_android.ui.Home
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2018/11/13
 * 描    述：常用的数据
 * ================================================
 */
public class CommonUsedData {
    /**
     * 内部类实现单例模式
     * 延迟加载，减少内存开销
     */
    private static class SingletonHolder {
        private static CommonUsedData instance = new CommonUsedData();
    }

    /**
     * 私有的构造函数
     */
    private CommonUsedData() {
    }

    public static CommonUsedData getInstance() {
        return CommonUsedData.SingletonHolder.instance;
    }

    //获取保存到相册
    public ArrayList<String> getSavePhotoData() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("保存到相册");
        return arrayList;
    }

    //获取图片的方式数据
    public ArrayList<String> getPhotoChooseData() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("手机相册");
        arrayList.add("拍照");
        return arrayList;
    }

    //获取视频的方式数据
    public ArrayList<String> getVideoChooseData() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("手机相册");
        arrayList.add("拍摄");
        return arrayList;
    }

    //时间筛选条件的数据
    public ArrayList<String> getScreenTimeData() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("起始时间");
        arrayList.add("结束时间");
        return arrayList;
    }

    //支付方式数据
    public ArrayList<OrderPayTypeModel> getOrderPayTypeData(String usableBalance,String totalPrice) {
        ArrayList<OrderPayTypeModel> orderPayTypeModels = new ArrayList<>();
        double moneyTag=new BigDecimal(usableBalance).subtract(new BigDecimal(totalPrice)).doubleValue();
        OrderPayTypeModel orderPayTypeModel01 = new OrderPayTypeModel(R.drawable.icon_ye, "余额支付",
                "余额：" + usableBalance + "元", moneyTag>=0?true:false,moneyTag>=0?true:false);
        OrderPayTypeModel orderPayTypeModel02 = new OrderPayTypeModel(R.drawable.ic_pay_weixin, "微信支付",
                "推荐安装微信5.0及以上版本用户使用", moneyTag>=0?false:true,true);
        orderPayTypeModels.add(orderPayTypeModel01);
        orderPayTypeModels.add(orderPayTypeModel02);
        return orderPayTypeModels;
    }

    //获取取消订单的数据(不带选中)
    public void getCancleData(String TAG,String type,IGetCancleData iGetCancleData) {
        getCancleData(TAG,0,type,iGetCancleData);
    }

    //获取取消订单的数据
    public void getCancleData(String TAG,int defaultPos,String type,IGetCancleData iGetCancleData) {
        iGetCancleData.showWait();
        CancelReasonModel.sendCancelReasonRequest(TAG, type, new CustomerJsonCallBack<CancelReasonModel>() {
            @Override
            public void onRequestError(CancelReasonModel returnData, String msg) {
                iGetCancleData.hideWait();

            }

            @Override
            public void onRequestSuccess(CancelReasonModel returnData) {
                iGetCancleData.hideWait();

                ArrayList<CancleOrderModel> cancleOrderModels = new ArrayList<>();
                if (returnData.getData()!=null){
                    for (int i = 0; i < returnData.getData().size(); i++) {
                        CancleOrderModel cancleOrderModel =null;
                        if (i==defaultPos){
                            cancleOrderModel= new CancleOrderModel(true, returnData.getData().get(i));
                        }else {
                            cancleOrderModel= new CancleOrderModel(false, returnData.getData().get(i));
                        }
                        cancleOrderModels.add(cancleOrderModel);
                    }
                }
                iGetCancleData.onSuccess(cancleOrderModels);
            }
        });
    }

    public interface IGetCancleData{
        void showWait();
        void hideWait();
        void onSuccess(ArrayList<CancleOrderModel> cancleOrderModels);
    }

    //今天 明天的日期数据
    public ArrayList<LotteryAttributeModel> getTodayAndTomorrowData() {
        ArrayList<LotteryAttributeModel> lotteryAttributeModels = new ArrayList<>();
        LotteryAttributeModel lotteryAttributeModel01 = new LotteryAttributeModel("今天", DateUtil.getStringDate("yyyy-MM-dd"), true);
        LotteryAttributeModel lotteryAttributeModel02 = new LotteryAttributeModel("明天", DateUtil.getStringTomorrowDate("yyyy-MM-dd"), false);
        lotteryAttributeModels.add(lotteryAttributeModel01);
        lotteryAttributeModels.add(lotteryAttributeModel02);
        return lotteryAttributeModels;
    }

    //20点 21点 22点
    public ArrayList<LotteryAttributeModel> getTimeData() {
        ArrayList<LotteryAttributeModel> lotteryAttributeModels = new ArrayList<>();
        LotteryAttributeModel lotteryAttributeModel01 = new LotteryAttributeModel("20点", "20:00:00", true);
        LotteryAttributeModel lotteryAttributeModel02 = new LotteryAttributeModel("21点", "21:00:00", false);
        LotteryAttributeModel lotteryAttributeModel03 = new LotteryAttributeModel("22点", "22:00:00", false);
        lotteryAttributeModels.add(lotteryAttributeModel01);
        lotteryAttributeModels.add(lotteryAttributeModel02);
        lotteryAttributeModels.add(lotteryAttributeModel03);
        return lotteryAttributeModels;
    }

    //评价标签
    public ArrayList<EvaluationLableModel> getEvaluationLableData() {
        ArrayList<EvaluationLableModel> evaluationLableModels = new ArrayList<>();
        EvaluationLableModel evaluationLableModel01 = new EvaluationLableModel(1, "物超所值", false);
        EvaluationLableModel evaluationLableModel02 = new EvaluationLableModel(2, "物流给力", false);
        EvaluationLableModel evaluationLableModel03 = new EvaluationLableModel(4, "服务贴心", false);
        EvaluationLableModel evaluationLableModel04 = new EvaluationLableModel(8, "包装精美", false);
        EvaluationLableModel evaluationLableModel05 = new EvaluationLableModel(16, "捡到漏了", false);
        EvaluationLableModel evaluationLableModel06 = new EvaluationLableModel(32, "值得信赖", false);

        evaluationLableModels.add(evaluationLableModel01);evaluationLableModels.add(evaluationLableModel02);
        evaluationLableModels.add(evaluationLableModel03);evaluationLableModels.add(evaluationLableModel04);
        evaluationLableModels.add(evaluationLableModel05);evaluationLableModels.add(evaluationLableModel06);
        return evaluationLableModels;
    }

    //加价类型的数据
    public ArrayList<String> getAddPriceTypeData() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("销售价");
        arrayList.add("供货价");
        return arrayList;
    }

    //加价类型的运算符号数据
    public ArrayList<String> getAddPriceOperationData() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("加");
        arrayList.add("乘");
        return arrayList;
    }



    public ArrayList<CommonModel> getCommonLabelList(){
        //标签列表
        ArrayList<CommonModel> commonModels = new ArrayList<>();
        commonModels.add(new CommonModel("物超所值", 1, false));
        commonModels.add(new CommonModel("物流给力", 2, false));
        commonModels.add(new CommonModel("服务贴心", 4, false));
        commonModels.add(new CommonModel("包装精美", 8, false));
        commonModels.add(new CommonModel("捡到漏了", 16, false));
        commonModels.add(new CommonModel("值得信赖", 32, false));
        return commonModels;
    }


    public ArrayList<String> getShareTitlelList(){
        //【店铺名字】派发福利，点击查看！【拍品标题】
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("派发福利，点击查看！");
        arrayList.add("价格超乎寻常的低！");
        arrayList.add("震惊了！竟然这么多人在拍这个拍品！");
        arrayList.add("硬菜来了，不是每件宝贝都叫极品！");
        arrayList.add("疯狂放漏中！");
        arrayList.add("不是大漏你打我！");
        arrayList.add("快来看看我的眼光如何！");
        arrayList.add("开眼界了！");
        return arrayList;
    }


    public ArrayList<String> getShareShopTitlelList(){
        //【店铺名字】派发福利，点击查看！【拍品标题】
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("我发现一家好店，邀你一起看看！");
        arrayList.add("只推荐好产品给你，是我分享的初衷！");
        arrayList.add("这个店铺难得一见，快来看看！");
        arrayList.add("推荐一家匠心之作店铺，特别有意思！");
        arrayList.add("疯狂放漏中！");
        arrayList.add("不是大漏你打我！");
        arrayList.add("快来看看我的眼光如何！");
        arrayList.add("开眼界了！");
        return arrayList;
    }

}
