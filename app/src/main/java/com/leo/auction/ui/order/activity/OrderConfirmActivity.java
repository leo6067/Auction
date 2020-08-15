package com.leo.auction.ui.order.activity;

import android.os.Bundle;
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
import com.aten.compiler.widget.glide.GlideUtils;
import com.aten.compiler.widget.title.TitleBar;
import com.leo.auction.R;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.CommonUsedData;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.login.UserActionUtils;
import com.leo.auction.ui.main.home.dialog.PayPwdBoardUtils;
import com.leo.auction.ui.main.home.model.PayModel;
import com.leo.auction.ui.main.mine.model.AddressModel;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.ui.order.model.OrderListModel;
import com.leo.auction.ui.order.model.OrderPayTypeModel;
import com.leo.auction.utils.SetPaypwdUtils;
import com.leo.auction.utils.wxPay.WXPay;
import com.leo.auction.utils.wxPay.WXPayBean;

import java.math.BigDecimal;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class OrderConfirmActivity extends BaseActivity implements SetPaypwdUtils.IComplete, PayPwdBoardUtils.IPayType {


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
    TextView mItemTime;
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
    private String payment;
    private String orderCode;
    private OrderListModel.DataBean dataBean;




    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_order_confirm);
    }


    @Override
    public void initData() {
        super.initData();
        payInputPwdBoardUtils = new PayPwdBoardUtils();
        getAddress();

        Bundle bundle = getIntent().getExtras();
        payment = bundle.getString("payment");
        orderCode = bundle.getString("orderCode");
        dataBean = bundle.getParcelable("payItemTag");
        initUI();
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
        GlideUtils.loadImg(dataBean.getHeadImg(), mOrderHead);
        mOrderShopName.setText(dataBean.getNickname());
        OrderListModel.DataBean.ItemsBean itemsBean = dataBean.getItems().get(0);
        mTvProductTitle.setText(itemsBean.getTitle());
        GlideUtils.loadImg(itemsBean.getFirstPic(), mRivProductPic);
//        mItemTime.setText("付款剩余："+ itemsBean.getPrice());
        mItemPrice.setText("成交金额：￥" + itemsBean.getPrice());


        mTvRealPrice.setText("￥ " + itemsBean.getPrice());
    }


    @OnClick({R.id.address_lin, R.id.tv_sure_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.address_lin:
                break;
            case R.id.tv_sure_pay:
                pay();
                break;
        }
    }

    //付款
    private void pay() {
        if (EmptyUtils.isEmpty(orderCode) || EmptyUtils.isEmpty(payment)) {
            ToastUtils.showShort("订单数据有误");
            return;
        }

        UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();
        ArrayList<OrderPayTypeModel> orderPayTypeModels = CommonUsedData.getInstance().getOrderPayTypeData(userJson.getBalance(), payment);
        payInputPwdBoardUtils.showPayTypeDialog(OrderConfirmActivity.this, payment,
                orderPayTypeModels, this);
    }


    @Override
    public void choosePayType(int pos) {
        UserModel.DataBean userInfoModel = BaseSharePerence.getInstance().getUserJson();
        payInputPwdBoardUtils.dismissPayTypeDialog();
        if (pos == 0) {
            double moneyTag = new BigDecimal(userInfoModel.getBalance()).subtract(new BigDecimal(payment)).doubleValue();
            if (moneyTag >= 0) {
                //说明余额够支付
                if (Double.valueOf(payment) > Constants.Var.MONEY_NO_PWD || EmptyUtils.isEmpty(userInfoModel.getBalanceExempt())) {
                    //余额密码支付
                    payInputPwdBoardUtils.showPayPasswordDialog(OrderConfirmActivity.this,
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
        for (OrderListModel.DataBean.ItemsBean item : dataBean.getItems()) {
//            UserActionUtils.actionLog("0","3",item.getGoodsId(),payItemTag.getShopUri(),"1");
            UserActionUtils.actionLog("0", "3", item.getInstanceCode(), "1");
        }
        payInputPwdBoardUtils.dismissPayPasswordDialog();
        PayModel.httpPay(2, "order", payment, orderCode, null, payPwd, exempt, null, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {
                PayModel payModel = JSONObject.parseObject(resultData, PayModel.class);
                if (payModel.getResult().isSuccess()) {
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
        for (OrderListModel.DataBean.ItemsBean item : dataBean.getItems()) {
            UserActionUtils.actionLog("0", "3", item.getInstanceCode(), "1");
        }


        payInputPwdBoardUtils.dismissPayPasswordDialog();
        PayModel.httpPay(1, "order", Integer.valueOf(payment), orderCode, null, "", "", null, new HttpRequest.HttpCallback() {
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
                EasyPay.pay(wxPay, OrderConfirmActivity.this, wxPayBean, new IPayCallback() {
                    @Override
                    public void success() {

//                  OrderStatusActivity.newIntance(getContext(),shopUri,"0");
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


}
