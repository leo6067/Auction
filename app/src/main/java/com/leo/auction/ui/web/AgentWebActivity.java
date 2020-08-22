package com.leo.auction.ui.web;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.utils.easyPay.EasyPay;
import com.aten.compiler.utils.easyPay.callback.IPayCallback;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.CommonUsedData;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.login.LoginActivity;
import com.leo.auction.ui.login.UserActionUtils;
import com.leo.auction.ui.main.MainActivity;
import com.leo.auction.ui.main.home.activity.AuctionDetailActivity;
import com.leo.auction.ui.main.home.activity.ShopActivity;
import com.leo.auction.ui.main.home.dialog.PayPwdBoardUtils;
import com.leo.auction.ui.main.home.model.PayAgentWebModel;
import com.leo.auction.ui.main.home.model.PayModel;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.ui.order.activity.GoodOrderActivity;
import com.leo.auction.ui.order.model.ApplyAgentModel;
import com.leo.auction.ui.order.model.OrderPayTypeModel;
import com.leo.auction.utils.DateTimeUtils;
import com.leo.auction.utils.Globals;
import com.leo.auction.utils.SetPaypwdUtils;
import com.leo.auction.utils.wxPay.WXPay;
import com.leo.auction.utils.wxPay.WXPayBean;

import java.math.BigDecimal;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class AgentWebActivity extends AppCompatActivity implements SetPaypwdUtils.IComplete, PayPwdBoardUtils.IPayType {

    FrameLayout mFrameLayout;
    @BindView(R.id.container_framelayout)
    FrameLayout mContainerFramelayout;
    @BindView(R.id.tab_home)
    TextView mTabHome;
    @BindView(R.id.tab_sort)
    TextView mTabSort;
    @BindView(R.id.tab_focus)
    TextView mTabFocus;
    @BindView(R.id.tab_news)
    TextView mTabNews;
    @BindView(R.id.tab_mine)
    TextView mTabMine;
    @BindView(R.id.pay_way)
    TextView mPayPay;


    @BindView(R.id.guide_bottom)
    LinearLayout mGuideBottomLin;
    private FragmentManager mFragmentManager;
    private AgentWebFragment mAgentWebFragment;
    Bundle mBundle;
    String mTitleStr;
    String mUrlStr;
    UserModel.DataBean mUserJson;

    private PayPwdBoardUtils payInputPwdBoardUtils;


    private boolean isShop = false;
    private int paymoney = 0;
    private String tradeNo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_web);
        ButterKnife.bind(this);

        ActivityManager.agentWebActivity = this;
        ActivityManager.addActivity(this);


//        mFrameLayout = (FrameLayout) this.findViewById(R.id.container_framelayout);
        mFragmentManager = this.getSupportFragmentManager();
        mUserJson = BaseSharePerence.getInstance().getUserJson();
        mTitleStr = getIntent().getStringExtra("title");
        mUrlStr = getIntent().getStringExtra("url");
        loadWebFragment(mTitleStr);


        payInputPwdBoardUtils = new PayPwdBoardUtils();
    }


    private void loadWebFragment(String mTitleStr) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        switch (mTitleStr) {


            case "TOP百亿补贴":
                mGuideBottomLin.setVisibility(View.VISIBLE);
                mBundle = new Bundle();
                mBundle.putString(AgentWebFragment.URL_KEY, mUrlStr);
                ft.add(R.id.container_framelayout, mAgentWebFragment = AgentWebFragment.getInstance(mBundle), AgentWebFragment.class.getName());
                break;

            case "开店申请":
                httpGetApplyAgent();
                mPayPay.setVisibility(View.VISIBLE);
                mBundle = new Bundle();
                mBundle.putString(AgentWebFragment.URL_KEY, mUrlStr);
                ft.add(R.id.container_framelayout, mAgentWebFragment = AgentWebFragment.getInstance(mBundle), AgentWebFragment.class.getName());
                break;

            default:
                mBundle = new Bundle();
                mBundle.putString(AgentWebFragment.URL_KEY, mUrlStr);
                ft.add(R.id.container_framelayout, mAgentWebFragment = AgentWebFragment.getInstance(mBundle), AgentWebFragment.class.getName());
        }


        ft.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //一定要保证 mAentWebFragemnt 回调
//		mAgentWebFragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        AgentWebFragment mAgentWebFragment = this.mAgentWebFragment;
        if (mAgentWebFragment != null) {
            FragmentKeyDown mFragmentKeyDown = mAgentWebFragment;
            if (mFragmentKeyDown.onFragmentKeyDown(keyCode, event)) {
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @OnClick({R.id.pay_way, R.id.tab_home, R.id.tab_sort, R.id.tab_focus, R.id.tab_news, R.id.tab_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tab_home:
                MainActivity.newIntance(AgentWebActivity.this, 0);
                break;
            case R.id.tab_sort:
                ActivityManager.JumpActivity(AgentWebActivity.this, MainActivity.class);
                ActivityManager.mainActivity.setCurrent(1);
                break;
            case R.id.tab_focus:
                if (mUserJson == null) {
                    LoginActivity.newIntance(AgentWebActivity.this, 0);
                    return;
                }
                ActivityManager.JumpActivity(AgentWebActivity.this, MainActivity.class);
                ActivityManager.mainActivity.setCurrent(2);
                break;
            case R.id.tab_news:
                if (mUserJson == null) {
                    LoginActivity.newIntance(AgentWebActivity.this, 0);
                    return;
                }
                ActivityManager.JumpActivity(AgentWebActivity.this, MainActivity.class);
                ActivityManager.mainActivity.setCurrent(3);
                break;
            case R.id.tab_mine:
                if (mUserJson == null) {
                    LoginActivity.newIntance(AgentWebActivity.this, 0);
                    return;
                }
                ActivityManager.JumpActivity(AgentWebActivity.this, MainActivity.class);
                ActivityManager.mainActivity.setCurrent(4);
                break;
            case R.id.pay_way:
                if (!isShop) {  //
                    UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();
                    ArrayList<OrderPayTypeModel> orderPayTypeModels = CommonUsedData.getInstance().getOrderPayTypeData(userJson.getBalance(), "" + paymoney);
                    payInputPwdBoardUtils.showPayTypeDialogTime(AgentWebActivity.this, paymoney + "", "",
                            orderPayTypeModels, this);
                }

                break;
        }
    }


    private void httpGetApplyAgent() {

        ApplyAgentModel.httpGetApplyAgent(new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {

                ApplyAgentModel applyAgentModel = JSON.parseObject(resultData, ApplyAgentModel.class);

                if (resultData.contains("money")){
                    isShop = false;
                    mPayPay.setText("支付" + applyAgentModel.getData().getMoney() + "元技术服务开通费");
                    paymoney = applyAgentModel.getData().getMoney();
                    tradeNo = applyAgentModel.getData().getTradeNo();
                }else {
                    mPayPay.setText("已开通服务");
                    isShop = true;
                }

            }
        });

    }

    @Override
    public void choosePayType(int pos) {


        payInputPwdBoardUtils.dismissPayTypeDialog();
        UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();

        if (pos == 0) {
            double moneyTag = new BigDecimal(userJson.getBalance()).subtract(new BigDecimal(paymoney)).doubleValue();
            if (moneyTag >= 0) {
                //说明余额够支付
                if (paymoney > 300 || EmptyUtils.isEmpty(userJson.getBalanceExempt())) {
                    //余额密码支付
                    payInputPwdBoardUtils.showPayPasswordDialog(AgentWebActivity.this,
                            String.valueOf(paymoney), this);
                } else {
                    //免密支付
                    balance("", userJson.getBalanceExempt());
                }
            } else {
                //余额不够支付
                ToastUtils.showShort("余额不足");
                wxPay();
            }
        } else if (pos == 1) {

            wxPay();
        }

    }

    @Override
    public void onComplete(String text) {  //余额支付

        balance(text, "");
    }


    //微信支付
    private void wxPay() {

        payInputPwdBoardUtils.dismissPayPasswordDialog();

        PayModel.httpPay(1, "agent", paymoney + "", tradeNo, null, "", "", null, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {
                PayAgentWebModel parseObject = JSONObject.parseObject(resultData, PayAgentWebModel.class);
                PayAgentWebModel.DataBean.WxBean data = parseObject.getData().getWx();


                //实例化微信支付策略
                WXPay wxPay = WXPay.getInstance();



                JSONObject jsonObject = JSONObject.parseObject(resultData);
                String dataStr = jsonObject.getString("data");
                JSONObject dataJson = JSONObject.parseObject(dataStr);
                String wxStr = dataJson.getString("wx");
                JSONObject wxJson = JSONObject.parseObject(wxStr);
                String packageStr = wxJson.getString("package");

//
//                //构造微信订单实体。一般都是由服务端直接返回。
////                WXPayBean wxPayBean = new WXPayBean(Constants.Nouns.WEIXINAPPID, data.getMchId(), data.getPrepayId(), packageStr,
////                        data.getNonceStr(), data.getTimeStamp(), data.getPaySign());
//
//                WXPayBean wxPayBean = new WXPayBean(data.getAppid(), data.getMchId(), data.getPrepayId(), packageStr,
//                        data.getNonceStr(), data.getTimeStamp(), data.getSign());
//
//                Globals.log("xxxxx wxPay failed 01 " + packageStr);
//                Globals.log("xxxxx wxPay failed 010 " + wxPayBean.toString());
//
//                //策略场景类调起支付方法开始支付，以及接收回调。
//                //策略场景类调起支付方法开始支付，以及接收回调。
//                EasyPay.pay(wxPay, AgentWebActivity.this, wxPayBean, new IPayCallback() {
//                    @Override
//                    public void success() {
//                        finish();
//                    }
//
//                    @Override
//                    public void failed(int code, String message) {
//                        Globals.log("xxxxx wxPay failed" +message  +code);
//
//                        ToastUtils.showShort("支付失败");
//                    }
//
//                    @Override
//                    public void cancel() {
//                        ToastUtils.showShort("支付取消");
//                    }
//                });
            }
        });
    }


    //余额支付
    public void balance(String payPwd, String exempt) {
        payInputPwdBoardUtils.dismissPayPasswordDialog();

        PayModel.httpPay(2, "agent", paymoney, tradeNo, null, payPwd, exempt, null, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {
                PayModel payModel = JSONObject.parseObject(resultData, PayModel.class);
                if (payModel.getResult().isSuccess()) {
                    finish();
                } else {
                    ToastUtils.showShort(payModel.getResult().getMessage());
                }
            }
        });
    }


}
