package com.leo.auction.ui.main.mine.activity;

import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.CustomerDialogUtils;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.widget.customerDialog.BottomDialogUtils;
import com.aten.compiler.widget.dialog.NormalDialog;
import com.aten.compiler.widget.dialog.listener.OnBtnClickL;
import com.aten.compiler.widget.title.TitleBar;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.mine.model.UserModel;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class PaySafeActivity extends BaseActivity {


    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.reset)
    TextView mReset;
    @BindView(R.id.forget)
    TextView mForget;
    @BindView(R.id.identity)
    TextView mIdentity;
    @BindView(R.id.pay_pwd)
    TextView mPayPwd;
    @BindView(R.id.pwd)
    TextView mPwd;
    private UserModel.DataBean mDataBean;

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_pay_safe);
        mTitleBar.setTitle("支付安全");
        mDataBean = BaseSharePerence.getInstance().getUserJson();
        boolean payPwd = mDataBean.isPayPwd();
        if (payPwd){
            mReset.setText("重置支付密码");
        }else {
            mReset.setText("设置支付密码");
        }

    }


    @OnClick({R.id.reset, R.id.forget, R.id.identity, R.id.pay_pwd, R.id.pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reset:
                boolean payPwd = mDataBean.isPayPwd();

                if (payPwd){
                    ActivityManager.JumpActivity(PaySafeActivity.this,SetPayPwdActivity.class);
                }else {
                    ActivityManager.JumpActivity(PaySafeActivity.this,ResetPwdActivity.class);
                }


                break;
            case R.id.forget:

                break;
            case R.id.identity:

                break;
            case R.id.pay_pwd:
                showNoPaymentPwdDialog();
                break;
            case R.id.pwd:
                CustomerDialogUtils.getInstance().showNormalDialog(PaySafeActivity.this, true, "免密说明",
                        "1.余额免密支付单笔限额300元。<br/>2.免密支付之后若对费用有异议，可联系客服处理。", NormalDialog.STYLE_ONE, 1, "知道了",
                        new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                CustomerDialogUtils.getInstance().dissNormalDialog();
                            }
                        });

                break;
        }
    }

    //显示余额支付免密弹框
    private void showNoPaymentPwdDialog() {

        final String balanceExempt = BaseSharePerence.getInstance().getUserJson().getBalanceExempt();
        BottomDialogUtils bottomDialogUtils = new BottomDialogUtils(this);


        String OpenText = "开通后，余额付款的订单（300元以下）可支持自动免密扣款";
        String CloseText = "关闭后，余额付款的订单（300元以下）将无法自动免密扣款";
        bottomDialogUtils.showNoPayPwdDialog(EmptyUtils.isEmpty(balanceExempt) ? OpenText : CloseText,
                EmptyUtils.isEmpty(balanceExempt) ? "确认开通" : "确认关闭",
                new BottomDialogUtils.INoPayPwd() {
                    @Override
                    public void ConfirmOpening() {
                        bottomDialogUtils.dismissNoPayPwdDialog();

                        if (EmptyUtils.isEmpty(balanceExempt)) {
                            openNoPayPwd("1");
                        } else {
                            openNoPayPwd("0");
                        }
                    }

                    @Override
                    public void cancel() {
                        bottomDialogUtils.dismissNoPayPwdDialog();
                    }
                });
    }


    private void openNoPayPwd(String type) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("type", type);
        showWaitDialog();
        HttpRequest.httpPostString(Constants.Api.BALANCE_EXEMPT_URL, hashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                JSONObject jsonObject = JSONObject.parseObject(resultData);
                String data = jsonObject.getString("data");//免密字符串  免密字符串   开启返回四位数  取消返回空字符串
                mDataBean = BaseSharePerence.getInstance().getUserJson();
                mDataBean.setBalanceExempt(data);
                BaseSharePerence.getInstance().setUserJson(JSON.toJSONString(mDataBean));


            }
        });
    }
}
