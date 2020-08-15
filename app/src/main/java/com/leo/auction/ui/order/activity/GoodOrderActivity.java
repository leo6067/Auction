package com.leo.auction.ui.order.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.allen.library.SuperButton;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.utils.easyPay.EasyPay;
import com.aten.compiler.utils.easyPay.callback.IPayCallback;
import com.aten.compiler.widget.CircleImageView;
import com.aten.compiler.widget.CustomeRecyclerView;
import com.aten.compiler.widget.RadiusImageView;
import com.aten.compiler.widget.countDownTime.CountdownView;
import com.aten.compiler.widget.glide.GlideUtils;
import com.aten.compiler.widget.title.TitleBar;
import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.TimeUtils;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.CommonUsedData;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.login.UserActionUtils;
import com.leo.auction.ui.main.home.dialog.PayPwdBoardUtils;
import com.leo.auction.ui.main.home.model.PayModel;
import com.leo.auction.ui.main.mine.activity.AddressActivity;

import com.leo.auction.ui.main.mine.model.AddressModel;
import com.leo.auction.ui.main.mine.model.UserModel;

import com.leo.auction.ui.order.model.OrderExtendContentJson;
import com.leo.auction.ui.order.model.OrderGoodJson;
import com.leo.auction.ui.order.model.OrderPayTypeModel;
import com.leo.auction.ui.order.model.SubsidyJson;
import com.leo.auction.utils.DateTimeUtils;
import com.leo.auction.utils.SetPaypwdUtils;
import com.leo.auction.utils.wxPay.WXPay;
import com.leo.auction.utils.wxPay.WXPayBean;

import java.math.BigDecimal;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/*
 *
 * 确认订单*/

public class GoodOrderActivity extends BaseActivity implements SetPaypwdUtils.IComplete, PayPwdBoardUtils.IPayType {


    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.address_name)
    TextView mAddressName;
    @BindView(R.id.address_address)
    TextView mAddressAddress;
    @BindView(R.id.address_lin)
    LinearLayout mAddressLin;
    @BindView(R.id.order_recycler)
    CustomeRecyclerView mOrderRecycler;
    @BindView(R.id.order_head)
    CircleImageView mOrderHead;
    @BindView(R.id.order_shop_name)
    TextView mOrderShopName;
    @BindView(R.id.riv_product_pic)
    RadiusImageView mRivProductPic;
    @BindView(R.id.tv_product_title)
    TextView mTvProductTitle;
    @BindView(R.id.tv_order_price)
    TextView mTvOrderPrice;
    @BindView(R.id.item_time)
    CountdownView mItemTime;
    @BindView(R.id.item_price)
    TextView mItemPrice;
    @BindView(R.id.item_time_num)
    TextView mItemTimeNum;
    @BindView(R.id.tv_freight)
    TextView mTvFreight;
    @BindView(R.id.tv_express_infor)
    TextView mTvExpressInfor;
    @BindView(R.id.tv_copy_express_id)
    TextView mTvCopyExpressId;
    @BindView(R.id.tv_order_leaving_message)
    TextView mTvOrderLeavingMessage;
    @BindView(R.id.tv_real_price)
    TextView mTvRealPrice;
    @BindView(R.id.tv_tag)
    TextView mTvTag;
    @BindView(R.id.order_daofu)
    TextView mOrderDaofu;
    @BindView(R.id.ac_price)
    TextView mAcPrice;
    @BindView(R.id.tv_total_price)
    TextView mTvTotalPrice;
    @BindView(R.id.tv_sure_pay)
    SuperButton mTvSurePay;
    AddressModel.DataBean addressInfo;

    private PayPwdBoardUtils payInputPwdBoardUtils;
    private String paymoney;
    private String orderCode;
    private String productInstanceCode;
    private String scene;
    private String subsidyLimit = "";
    private OrderGoodJson.DataBean mOrderGoodJsonData;

    private OrderExtendContentJson mOrderExtendContentJson;


    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_good_order);
    }


    @Override
    public void initData() {
        super.initData();
        mOrderExtendContentJson = new OrderExtendContentJson();

        payInputPwdBoardUtils = new PayPwdBoardUtils();
        Bundle bundle = getIntent().getExtras();
        productInstanceCode = bundle.getString("productInstanceCode");
        orderCode = bundle.getString("orderCode");
        scene = bundle.getString("scene");
        subsidyLimit = bundle.getString("subsidyLimit");

        if (subsidyLimit.length() > 0) {  //单订单--补贴---参数
            ArrayList<OrderExtendContentJson.SubsidyBean> subsidyBeans = new ArrayList<>();
            OrderExtendContentJson.SubsidyBean subsidyBean = new OrderExtendContentJson.SubsidyBean();
            subsidyBean.setOrderCode(orderCode);
            subsidyBean.setSubsidyLimit(subsidyLimit);
            subsidyBeans.add(subsidyBean);
            mOrderExtendContentJson.setSubsidy(subsidyBeans);
        }
        getAddress();
        getOrderData();
    }


    private void getOrderData() {


        OrderGoodJson.httpGetOrderModel(orderCode, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {
                OrderGoodJson mOrderGoodJson = JSONObject.parseObject(resultData, OrderGoodJson.class);
                mOrderGoodJsonData = mOrderGoodJson.getData();
                paymoney = mOrderGoodJsonData.getPayment();
                initUI();
            }
        });

    }


    //获取联系人地址
    private void getAddress() {
        showWaitDialog();
        AddressModel.httpAddressList(1, Constants.Var.BB, "0", new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                AddressModel addressModel = JSONObject.parseObject(resultData, AddressModel.class);
                if (addressModel.getData() != null && !addressModel.getData().isEmpty()) {
                    addressInfo = addressModel.getData().get(0);
                    mAddressName.setVisibility(View.VISIBLE);
                    mAddressName.setText(addressInfo.getLinkman() + "  " + addressInfo.getPhone());
                    mAddressAddress.setText(addressInfo.getAddr1Name() + " " + addressInfo.getAddr2Name() + " " + addressInfo.getAddr3Name() + " " + addressInfo.getAddress());

                    mOrderExtendContentJson.setReceiver(addressInfo.getLinkman());
                    mOrderExtendContentJson.setReceiverAreaName(mAddressAddress.getText().toString());
                    mOrderExtendContentJson.setReceiverCode(addressInfo.getCode());
                    mOrderExtendContentJson.setReceiverMobile(addressInfo.getPhone());
                } else {
                    addressInfo = null;
                    mAddressName.setVisibility(View.GONE);
                    mAddressAddress.setText("未设置收货地址");
                }
            }
        });
    }

    public void initUI() {
        mTitleBar.setTitle("确认订单");
        GlideUtils.loadImg(mOrderGoodJsonData.getHeadImg(), mOrderHead);
        mOrderShopName.setText(mOrderGoodJsonData.getNickname());

        mTvProductTitle.setText(mOrderGoodJsonData.getItems().get(0).getTitle());
        GlideUtils.loadImg(mOrderGoodJsonData.getItems().get(0).getFirstPic(), mRivProductPic);

        long closeTimeSpan = TimeUtils.getTimeSpanByNow(mOrderGoodJsonData.getExpire(), TimeConstants.MSEC);
        mItemTime.start(closeTimeSpan);


        int distributeType = mOrderGoodJsonData.getDistributeType();//  1-快递包邮  2-快递运费 4-到付 8-当面交易',

        if (distributeType == 1) {
            mOrderDaofu.setText("包邮");
        } else if (distributeType == 4) {
            mOrderDaofu.setText("到付");
        }

        mItemPrice.setText("成交金额：￥" + paymoney);
        mTvRealPrice.setText("￥ " + paymoney);
        mAcPrice.setText("实付： ￥ " + paymoney);
        mTvTotalPrice.setText("￥ " + paymoney);
    }


    @OnClick({R.id.address_lin, R.id.tv_sure_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.address_lin:

                AddressActivity.newIntance(GoodOrderActivity.this, "0", "1", Constants.RequestCode.RETURNREQUEST_REFRESH_SETTING_ADDRESS);
                break;
            case R.id.tv_sure_pay:
                pay();
                break;
        }
    }

    //付款
    private void pay() {
        if (EmptyUtils.isEmpty(orderCode) || EmptyUtils.isEmpty(paymoney)) {
            ToastUtils.showShort("订单数据有误");
            return;
        }


        String timeToString = DateTimeUtils.timeToString(mOrderGoodJsonData.getExpire() + "", "MM月dd日 HH:mm");
        String timeHint = "为避免订单违约，建议您在" + timeToString +"前支付";

        UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();
        ArrayList<OrderPayTypeModel> orderPayTypeModels = CommonUsedData.getInstance().getOrderPayTypeData(userJson.getBalance(), paymoney);
        payInputPwdBoardUtils.showPayTypeDialogTime(GoodOrderActivity.this, paymoney, timeHint,
                orderPayTypeModels, this);
    }


    @Override
    public void choosePayType(int pos) {
        UserModel.DataBean userInfoModel = BaseSharePerence.getInstance().getUserJson();
        payInputPwdBoardUtils.dismissPayTypeDialogTime();
        if (pos == 0) {
            double moneyTag = new BigDecimal(userInfoModel.getBalance()).subtract(new BigDecimal(paymoney)).doubleValue();
            if (moneyTag >= 0) {
                //说明余额够支付
                if (Double.valueOf(paymoney) > Constants.Var.MONEY_NO_PWD || EmptyUtils.isEmpty(userInfoModel.getBalanceExempt())) {
                    //余额密码支付
                    payInputPwdBoardUtils.showPayPasswordDialog(GoodOrderActivity.this,
                            paymoney, this);
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
        for (OrderGoodJson.DataBean.ItemsBean item : mOrderGoodJsonData.getItems()) {
//            UserActionUtils.actionLog("0","3",item.getGoodsId(),payItemTag.getShopUri(),"1");
            UserActionUtils.actionLog(Constants.Action.ACTION_ACTION, "3", item.getProductInstanceId() + "", "1");
        }


        payInputPwdBoardUtils.dismissPayPasswordDialog();

        ArrayList<String> tradeNoList = new ArrayList<>();
        tradeNoList.add(orderCode);
        //exempt 免密标识
        PayModel.httpPay(2, "order", paymoney, "", tradeNoList, payPwd, exempt, mOrderExtendContentJson, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
            }

            @Override
            public void httpResponse(String resultData) {
                PayModel payModel = JSONObject.parseObject(resultData, PayModel.class);
                if (payModel.getResult().isSuccess()) {
                    ToastUtils.showShort("支付成功");


                    Bundle bundle = new Bundle();
                    bundle.putString("productInstanceCode", productInstanceCode);
                    bundle.putString("price", paymoney);
                    ActivityManager.JumpActivity(GoodOrderActivity.this, GoodOrederStatusActivity.class, bundle);
                    finish();
                } else {
                    ToastUtils.showShort(payModel.getResult().getMessage());
                }
//                OrderStatusActivity.newIntance(getContext(),shopUri,"0");
            }
        });
    }

    //微信支付
    private void wxPay() {
        for (OrderGoodJson.DataBean.ItemsBean item : mOrderGoodJsonData.getItems()) {
            UserActionUtils.actionLog("1", "3", item.getProductInstanceId() + "", "1");
        }

        payInputPwdBoardUtils.dismissPayPasswordDialog();


        ArrayList<String> tradeNoList = new ArrayList<>();
        tradeNoList.add(orderCode);


        PayModel.httpPay(1, "order", paymoney, "", tradeNoList, "", "", mOrderExtendContentJson, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
            }

            @Override
            public void httpResponse(String resultData) {
                PayModel returnData = JSONObject.parseObject(resultData, PayModel.class);
                PayModel.DataBean.WxBean data = returnData.getData().getWx();
                //实例化微信支付策略
                WXPay wxPay = WXPay.getInstance();
                //构造微信订单实体。一般都是由服务端直接返回。
                WXPayBean wxPayBean = new WXPayBean(Constants.Nouns.WEIXINAPPID, data.getMchId(), data.getPrepayId(), data.getPackageX(),
                        data.getNonceStr(), data.getTimeStamp(), data.getPaySign());

                //策略场景类调起支付方法开始支付，以及接收回调。
                //策略场景类调起支付方法开始支付，以及接收回调。
                EasyPay.pay(wxPay, GoodOrderActivity.this, wxPayBean, new IPayCallback() {
                    @Override
                    public void success() {
//                  OrderStatusActivity.newIntance(getContext(),shopUri,"0");

                        Bundle bundle = new Bundle();
                        bundle.putString("productInstanceCode", productInstanceCode);
                        bundle.putString("price", paymoney);
                        ActivityManager.JumpActivity(GoodOrderActivity.this, GoodOrederStatusActivity.class, bundle);
                        finish();
                    }

                    @Override
                    public void failed(int code, String message) {
                        ToastUtils.showShort("支付失败");
                    }

                    @Override
                    public void cancel() {
                        ToastUtils.showShort("支付取消");
                    }
                });
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //更改地址
        if (requestCode == Constants.RequestCode.RETURNREQUEST_REFRESH_SETTING_ADDRESS && resultCode == RESULT_OK) {
            AddressModel.DataBean address = data.getParcelableExtra("address");
            addressInfo = address;
            if (address.getAddr3Name() != null && address.getAddr3Name().length() > 0 && !address.getAddr3Name().equals("null")) {
                mAddressAddress.setText(address.getAddr1Name() + address.getAddr2Name() + address.getAddr3Name() + address.getAddress());
            } else {
                mAddressAddress.setText(address.getAddr1Name() + address.getAddr2Name() + address.getAddress());
            }
            mAddressName.setText(addressInfo.getLinkman() + "  " + addressInfo.getPhone());
            mOrderExtendContentJson.setReceiver(addressInfo.getLinkman());
            mOrderExtendContentJson.setReceiverAreaName(mAddressAddress.getText().toString());
            mOrderExtendContentJson.setReceiverCode(addressInfo.getCode());
            mOrderExtendContentJson.setReceiverMobile(addressInfo.getPhone());
        }
    }

}
