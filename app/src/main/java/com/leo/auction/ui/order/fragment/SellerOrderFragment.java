package com.leo.auction.ui.order.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseRecyclerView.BaseRecyclerViewFragment;
import com.aten.compiler.utils.BroadCastReceiveUtils;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.RxTool;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.utils.easyPay.EasyPay;
import com.aten.compiler.utils.easyPay.callback.IPayCallback;
import com.aten.compiler.widget.customerDialog.BottomDialogUtils;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.BaseModel;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.CommonUsedData;
import com.leo.auction.base.Constants;
import com.leo.auction.common.dialog.WarningDialog;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.login.UserActionUtils;
import com.leo.auction.ui.main.home.dialog.PayPwdBoardUtils;
import com.leo.auction.ui.main.home.model.PayModel;
import com.leo.auction.ui.main.mine.activity.AddressActivity;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.ui.order.activity.OrderCompleteEvaluationActivity;
import com.leo.auction.ui.order.activity.OrderConfirmActivity;
import com.leo.auction.ui.order.activity.OrderDetailActivity;
import com.leo.auction.ui.order.activity.OrderEvaluationActivity;
import com.leo.auction.ui.order.adapter.OrderAdapter;
import com.leo.auction.ui.order.adapter.SellerOrderAdapter;
import com.leo.auction.ui.order.model.OrderListModel;
import com.leo.auction.ui.order.model.OrderPayTypeModel;
import com.leo.auction.utils.DialogUtils;
import com.leo.auction.utils.SetPaypwdUtils;
import com.leo.auction.utils.wxPay.WXPay;
import com.leo.auction.utils.wxPay.WXPayBean;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class SellerOrderFragment extends BaseRecyclerViewFragment implements SetPaypwdUtils.IComplete, PayPwdBoardUtils.IPayType, WarningDialog.OnWarningClickListener {


//    public SellerOrderFragment() {
//        // Required empty public constructor
//    }
//
//

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_seller_order, container, false);
//    }


    private String status = "";//页面状态
    private int isSeller = 2;// 1 买家 2 卖家
    private boolean isneedlazy, neetTime;//是否需要开启懒加载 是否需要开启定时器
    private BottomDialogUtils bottomDialogUtils;
    private UserModel.DataBean userInfoModel;
    private String payment, orderCode = "";
    private int pos = -1;
    private boolean isGoWxPay = false;//是否进入微信支付
    private PayPwdBoardUtils payInputPwdBoardUtils;
    private DialogUtils dialogUtils;
    private OrderListModel.DataBean payItemTag;


    private String warnString = "";  //定义提示弹窗是什么类型


    private View statusView;  //状态点击按钮，拒绝当面交易等，点击之后消失隐藏

    //刷新订单列表页面
    private BroadCastReceiveUtils refreshOrderList = new BroadCastReceiveUtils() {
        @Override
        public void onReceive(Context context, Intent intent) {
            onRefresh(refreshLayout);
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        isneedlazy = getArguments().getBoolean("isneedlazy", false);
//        if (isneedlazy) {
//            enableLazyLoad();
//        }
    }

    @Override
    public void enableDataInitialized() {
        isDataInitialized = false;
    }

    @Override
    public void initTitle(View view) {
        super.initTitle(view);
        mTitleBar.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        status = getArguments().getString("status");
//        neetTime = getArguments().getBoolean("neetTime", false);
        neetTime = false;

        dialogUtils = new DialogUtils();
        super.initData();
        BroadCastReceiveUtils.registerLocalReceiver(getContext(), Constants.Action.SEND_REFRESH_ORDER_LIST + status, refreshOrderList);
        payInputPwdBoardUtils = new PayPwdBoardUtils();
        showWaitDialog();
        onRefresh(refreshLayout);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isGoWxPay) {
//            getWXPayStatus();
        }
    }

    @Override
    protected void initAdapter() {
        mAdapter = new SellerOrderAdapter(neetTime, status);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        setSmartHasRefreshOrLoadMore();
        setLoadMore();

        ((SellerOrderAdapter) mAdapter).setOnItemListsner(mOnItemListsner);
        ((SellerOrderAdapter) mAdapter).setOnBtnListsner(mOnBtnListsner);
    }


    @Override
    public void getData() {

        OrderListModel.httpOrderList(mPageNum, "", isSeller + "", status, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {
                OrderListModel returnData = JSONObject.parseObject(resultData, OrderListModel.class);
                if (returnData.getData() == null) {
                    return;
                }
//                ((OrderAdapter) mAdapter).setServerCurrentTime(returnData.getResult().getTimestamp());
                List<OrderListModel.DataBean> orderDatas = returnData.getData();

                if (mPageNum == 1) {
                    mAdapter.setNewData(orderDatas);
                } else {
                    mAdapter.addData(orderDatas);
                    mAdapter.loadMoreComplete();
                }


                if (returnData.getData().isEmpty()) {
                    mPageNum = 1;
                } else if (returnData.getData().size() > Constants.Var.LIST_NUMBER_INT) {
                    mAdapter.loadMoreEnd(true);
                } else {
                    mAdapter.loadMoreEnd();
                }
            }
        });
    }

    //列表的item点击事件
    private View.OnClickListener mOnItemListsner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!RxTool.isFastClick(RxTool.MIN_CLICK_DELAY_TIME_500)) {
                return;
            }

            OrderListModel.DataBean item = (OrderListModel.DataBean) v.getTag();
//            OrderDetailActivity.newIntance(SellerOrderFragment.this, item.getOrderCode(), isSeller,
//                    Constants.RequestCode.RETURNREQUEST_REFRESH_ORDER_LIST);

            Bundle bundle = new Bundle();
            bundle.putString("orderCode", item.getOrderCode());
            bundle.putBoolean("isSeller", false);
            bundle.putString("actionType", "查看详情");
            ActivityManager.JumpActivity(getActivity(), OrderDetailActivity.class, bundle);
        }
    };

    //底部按钮点击事件
    private View.OnClickListener mOnBtnListsner = new View.OnClickListener() {
        @Override
        public void onClick(View viw) {
            if (!RxTool.isFastClick(RxTool.MIN_CLICK_DELAY_TIME_500)) {
                return;
            }
            OrderListModel.DataBean item = (OrderListModel.DataBean) viw.getTag(R.id.tag_2);
            orderCode = item.getOrderCode();
            pos = (int) viw.getTag(R.id.tag_3);
            statusView = viw;
            HashMap<String, Object> hashMap = new HashMap<>();
            Bundle bundle = new Bundle();
            switch ((String) viw.getTag(R.id.tag_1)) {
                case "当面交易":
//                    AddressActivity.newIntance(getActivity(), "2", "0");


                    break;
                case "立即付款":

                    bundle.clear();
                    bundle.putString("payment", payment);
                    bundle.putString("orderCode", orderCode);
                    bundle.putParcelable("payItemTag", payItemTag);
                    ActivityManager.JumpActivity(getActivity(), OrderConfirmActivity.class, bundle);
                    break;
                case "查看详情":

                    bundle.clear();
                    bundle.putString("orderCode", orderCode);
                    bundle.putBoolean("isSeller", true);
                    bundle.putString("actionType", "查看详情");
                    ActivityManager.JumpActivity(getActivity(), OrderDetailActivity.class, bundle);
//                    OrderDetailActivity.newIntance(SellerOrderFragment.this, item.getOrderCode(), isSeller, Constants.RequestCode.RETURNREQUEST_REFRESH_ORDER_LIST);
                    break;

                case "确认收货":
                    hashMap.clear();
                    hashMap.put("content", getString(R.string.warn_sure_good));
                    dialogUtils.showWarnDialog(getActivity(), hashMap);
                    warnString = "确认收货";
                    break;
                case "立即评价":
                    bundle.clear();
                    bundle.putString("orderCode", orderCode);
                    ActivityManager.JumpActivity(getActivity(), OrderEvaluationActivity.class, bundle);
                    break;
                case "查看评价":
                    bundle.clear();
                    bundle.putString("orderCode", orderCode);
                    ActivityManager.JumpActivity(getActivity(), OrderCompleteEvaluationActivity.class, bundle);
                    break;
                case "立即发货":
                    bundle.clear();
                    bundle.putString("orderCode", orderCode);
                    bundle.putBoolean("isSeller", true);
                    bundle.putString("actionType", "立即发货");
                    ActivityManager.JumpActivity(getActivity(), OrderDetailActivity.class, bundle);
//                    OrderDetailActivity.newIntance(SellerOrderFragment.this, item.getOrderCode(), isSeller, Constants.RequestCode.RETURNREQUEST_REFRESH_ORDER_LIST);
                    break;
                case "修改地址":
                    AddressActivity.newIntance(getActivity(), "2", "0");
                    break;
                case "延迟发货":
                    httpDelaySendGood(1);
                    break;
                case "一键代发":

                    break;
                case "修改单号":
                    bundle.clear();
                    bundle.putString("orderCode", orderCode);
                    bundle.putBoolean("isSeller", true);
                    bundle.putString("actionType", "修改单号");
                    ActivityManager.JumpActivity(getActivity(), OrderDetailActivity.class, bundle);
//                    OrderDetailActivity.newIntance(SellerOrderFragment.this, item.getOrderCode(), isSeller, Constants.RequestCode.RETURNREQUEST_REFRESH_ORDER_LIST);
                    break;
                case "客服介入":
//                    customerServiceIntervention(item.getRefundId());
                    break;
            }
        }
    };


    //同意 拒绝 延迟发货
    private void httpDelaySendGood(int type) {
        showWaitDialog();
        BaseModel.httpDelaySendGood(orderCode, type, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                BaseModel baseModel = JSONObject.parseObject(resultData, BaseModel.class);
                if (baseModel.getResult().isSuccess()) {
                    ToastUtils.showShort("操作成功");
                    statusView.setVisibility(View.GONE);
                } else {
                    ToastUtils.showShort(baseModel.getResult().getMessage());
                }
            }
        });
    }


    //付款
    private void pay() {
        if (EmptyUtils.isEmpty(orderCode) || EmptyUtils.isEmpty(payment)) {
            ToastUtils.showShort("订单数据有误");
            return;
        }
        userInfoModel = BaseSharePerence.getInstance().getUserJson();
        ArrayList<OrderPayTypeModel> orderPayTypeModels = CommonUsedData.getInstance().getOrderPayTypeData(userInfoModel.getBalance(), payment);

        payInputPwdBoardUtils.showPayTypeDialog(getContext(), payment,
                orderPayTypeModels, this);
    }


    @Override
    public void choosePayType(int pos) {
        userInfoModel = BaseSharePerence.getInstance().getUserJson();
        payInputPwdBoardUtils.dismissPayTypeDialog();
        if (pos == 0) {
            double moneyTag = new BigDecimal(userInfoModel.getBalance()).subtract(new BigDecimal(payment)).doubleValue();
            if (moneyTag >= 0) {
                //说明余额够支付
                if (Double.valueOf(payment) > Constants.Var.MONEY_NO_PWD || EmptyUtils.isEmpty(userInfoModel.getBalanceExempt())) {
                    //余额密码支付
                    payInputPwdBoardUtils.showPayPasswordDialog(getContext(),
                            payment, this);
                } else {
                    //免密支付
                    balance("", userInfoModel.getBalanceExempt());
                }
            } else {
                //余额不够支付
                ToastUtils.showShort("余额不足");
                showWaitDialog();
                wxPay();
            }
        } else if (pos == 1) {
            showWaitDialog();
            wxPay();
        }
    }

    @Override
    public void onComplete(String text) {
        showWaitDialog();
        balance(text, "");
    }

    //余额支付
    public void balance(String payPwd, String exempt) {
        for (OrderListModel.DataBean.ItemsBean item : payItemTag.getItems()) {
//            UserActionUtils.actionLog("0","3",item.getGoodsId(),payItemTag.getShopUri(),"1");
            UserActionUtils.actionLog("0", "3", item.getInstanceCode(), "1");
        }

        payInputPwdBoardUtils.dismissPayPasswordDialog();
        PayModel.httpPay(2, "order", Integer.valueOf(payment), orderCode, null, payPwd, exempt, null, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
            }

            @Override
            public void httpResponse(String resultData) {
                PayModel payModel = JSONObject.parseObject(resultData, PayModel.class);
                if (payModel.getResult().isSuccess()) {
                    mAdapter.remove(pos);
                    ToastUtils.showShort("支付成功");
                } else {
                    ToastUtils.showShort(payModel.getResult().getMessage());
                }
//                OrderStatusActivity.newIntance(getContext(),shopUri,"0");
            }
        });

    }

    //微信支付
    private void wxPay() {
        for (OrderListModel.DataBean.ItemsBean item : payItemTag.getItems()) {
            UserActionUtils.actionLog("0", "3", item.getInstanceCode(), "1");
        }


        PayModel.httpPay(1, "order", Integer.valueOf(payment), orderCode, null, "", "", null, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                payInputPwdBoardUtils.dismissPayPasswordDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                PayModel returnData = JSONObject.parseObject(resultData, PayModel.class);
                PayModel.DataBean.WxBean data = returnData.getData().getWx();
                //实例化微信支付策略
                WXPay wxPay = WXPay.getInstance();
                //构造微信订单实体。一般都是由服务端直接返回。
                WXPayBean wxPayBean = new WXPayBean(Constants.Nouns.WEIXINAPPID, data.getMchId(), data.getPrepayId(), data.getPackages(),
                        data.getNonceStr(), data.getTimeStamp(), data.getPaySign());
                isGoWxPay = true;
                //策略场景类调起支付方法开始支付，以及接收回调。
                //策略场景类调起支付方法开始支付，以及接收回调。
                EasyPay.pay(wxPay, getActivity(), wxPayBean, new IPayCallback() {
                    @Override
                    public void success() {
                        isGoWxPay = false;
                        mAdapter.remove(pos);
//                        OrderStatusActivity.newIntance(getContext(),shopUri,"0");
                    }

                    @Override
                    public void failed(int code, String message) {
                        isGoWxPay = false;
                        ToastUtils.showShort("支付失败");
                    }

                    @Override
                    public void cancel() {
                        isGoWxPay = false;
                        ToastUtils.showShort("支付取消");
                    }
                });
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.RequestCode.RETURNREQUEST_REFRESH_ORDER_LIST && resultCode == -1) {
            String OperationType = data.getStringExtra("OperationType");
            String id = data.getStringExtra("id");
            switch (OperationType) {
                case "cancle"://取消订单
                case "balancePay"://余额支付
                case "sureReceiveGoods"://确认收货
                    deleteItem(id);
                    break;
                case "refund"://申请售后
                    if (!"64".equals(status)) {
                        deleteItem(id);
                    }
                    break;
                case "cancleRefund":
                    if ("64".equals(status)) {
                        deleteItem(id);
                    }
                    break;
            }

        }
    }

    //删除指定的列表item
    private void deleteItem(String id) {
        for (int i = 0; i < mAdapter.getData().size(); i++) {
            OrderListModel.DataBean data = ((OrderAdapter) mAdapter).getData().get(i);
//            if (id.equals(data.getId())) {
//                mAdapter.remove(i);
//                return;
//            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (bottomDialogUtils != null) {
            bottomDialogUtils.dismissCustomerDialog();
        }

        if (dialogUtils != null) {
            dialogUtils.dissSureReceiveGoodDialog();
        }

        if (((OrderAdapter) mAdapter) != null && ((OrderAdapter) mAdapter).getOrderTimeSets() != null && !((OrderAdapter) mAdapter).getOrderTimeSets().isEmpty()) {
            ((OrderAdapter) mAdapter).getOrderTimeSets().clear();
        }

        if (((OrderAdapter) mAdapter) != null && ((OrderAdapter) mAdapter).getmExecutorService() != null) {
            ((OrderAdapter) mAdapter).getmExecutorService().shutdownNow();
            ((OrderAdapter) mAdapter).setmExecutorService(null);

        }
        if (payInputPwdBoardUtils != null) {
            payInputPwdBoardUtils.dismissPayTypeDialog();
            payInputPwdBoardUtils.dismissGoSettingPaypwdDialog();
            payInputPwdBoardUtils.dismissPayPasswordDialog();
        }
    }


    @Override
    public void onWarningOk() {

    }

    @Override
    public void onWaringCancel() {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        BroadCastReceiveUtils.unregisterLocalReceiver(getContext(), refreshOrderList);
    }


    public static SellerOrderFragment newIntance(String status) {   //角度  1-买家角度(默认1)   2-卖家角度
        SellerOrderFragment orderFragment = new SellerOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putString("status", status);
        orderFragment.setArguments(bundle);
        return orderFragment;
    }
}
