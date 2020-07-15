package com.leo.auction.ui.main.mine.banlance;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.KeyboardUtils;
import com.aten.compiler.utils.RxTool;
import com.aten.compiler.widget.text.MoneyValueFilter;
import com.leo.auction.R;
import com.leo.auction.base.BaseModel;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.home.dialog.PayPwdBoardUtils;
import com.leo.auction.ui.main.mine.banlance.model.WithdrawNumModel;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.utils.SetPaypwdUtils;
import com.leo.auction.utils.TextLightUtils;
import com.leo.auction.widget.customDialog.GoSettingPaypwdDialog;

import org.litepal.LitePal;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class WithdrawalActivity extends BaseActivity implements SetPaypwdUtils.IComplete {
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_max_money)
    TextView tvMaxMoney;
    @BindView(R.id.tv_single_max_money)
    TextView tvSingleMaxMoney;

    private UserModel.DataBean userInfoModel;
    private int residueNum = 0;
    private String maxMoney = "0";
    private PayPwdBoardUtils payInputPwdBoardUtils;
    private GoSettingPaypwdDialog goSettingPaypwdDialog;


    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_withdrawal);
    }


    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public void initData() {

        mTitleBar.setTitle("提现");
        userInfoModel = BaseSharePerence.getInstance().getUserJson();
        super.initData();
        tvMaxMoney.setText("最多可提现：" + userInfoModel.getBalance() + "元");
        payInputPwdBoardUtils = new PayPwdBoardUtils();
        getWithdrawNum();
    }

    @Override
    public void initEvent() {
        super.initEvent();
        //设置价格的监听
        TextLightUtils textLightUtils = new TextLightUtils();
        textLightUtils.onTextChangedListener(etMoney, userInfoModel.getBalance());
        etMoney.setFilters(new InputFilter[]{new MoneyValueFilter()});
    }

    //获取提现次
    private void getWithdrawNum() {

        showWaitDialog();
        mHashMap.clear();
        HttpRequest.httpGetString(Constants.Api.BALANCE_WITHDRAWNUM_URL, mHashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                WithdrawNumModel returnData = JSONObject.parseObject(resultData, WithdrawNumModel.class);
                residueNum = returnData.getData().getResidueNum();
                maxMoney = EmptyUtils.isEmpty(returnData.getData().getMaxMoney()) ? "0" : returnData.getData().getMaxMoney();
                tvNum.setText("今日剩余" + returnData.getData().getResidueNum() + "次");
                tvSingleMaxMoney.setText("单笔最高¥" + maxMoney);
            }
        });

    }

    @OnClick({R.id.tv_01, R.id.stb_withdrawal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_01:
                etMoney.setText(userInfoModel.getBalance());
                RxTool.setEditTextCursorLocation(etMoney);
                break;
            case R.id.stb_withdrawal:
                KeyboardUtils.hideSoftInput(etMoney);
                if (residueNum <= 0) {
                    showShortToast("当日提现次数已用完");
                    return;
                }
                if (EmptyUtils.isEmpty(etMoney.getText().toString().trim()) || "0".equals(etMoney.getText().toString().trim())) {
                    showShortToast("请输入正确金额");
                    return;
                }

                if (new BigDecimal(etMoney.getText().toString().trim()).subtract(new BigDecimal(userInfoModel.getBalance())).doubleValue() > 0) {
                    showShortToast("提现金额大于余额");
                    return;
                }

                if (new BigDecimal(etMoney.getText().toString().trim()).doubleValue() < 1) {
                    showShortToast("最小提现金额为1.00元");
                    return;
                }

                if (new BigDecimal(maxMoney).subtract
                        (new BigDecimal(etMoney.getText().toString().trim())).doubleValue() < 0) {
                    showShortToast("已超过每单笔提现的金额");
                    return;
                }

                payInputPwdBoardUtils.showPayPasswordDialog(WithdrawalActivity.this,
                        etMoney.getText().toString().trim(), WithdrawalActivity.this);

                break;
        }
    }

    @Override
    public void onComplete(String text) {
        payInputPwdBoardUtils.dismissPayPasswordDialog();
        showWaitDialog();
        withdrawal(text);
    }

    //提现
    private void withdrawal(String payPwd) {

        mHashMap.put("money", etMoney.getText().toString().trim());
        mHashMap.put("payPwd", payPwd);

        showWaitDialog();
        HttpRequest.httpPostString(Constants.Api.BALANCE_WITHDRAW_URL, mHashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                BaseModel baseModel = JSONObject.parseObject(resultData, BaseModel.class);
                if (baseModel.getResult().isSuccess()) {
                    showShortToast("提现成功");
                } else {
                    showShortToast(baseModel.getResult().getMessage());
                }
                UserModel.httpUpdateUser();
                goFinish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (goSettingPaypwdDialog != null) {
            goSettingPaypwdDialog.dismiss();
            goSettingPaypwdDialog = null;
        }
        if (payInputPwdBoardUtils != null) {
            payInputPwdBoardUtils.dismissPayPasswordDialog();
            payInputPwdBoardUtils = null;
        }
    }


    public static void newIntance(Context context) {
        Intent intent = new Intent(context, WithdrawalActivity.class);
        context.startActivity(intent);
    }
}
