package com.leo.auction.ui.main.mine.banlance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.widget.title.TitleBar;
import com.leo.auction.R;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.mine.banlance.model.BalanceDetailModel;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class BalanceDetailActivity extends BaseActivity {


    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.trade_money)
    TextView mTradeMoney;
    @BindView(R.id.trade_about)
    TextView mTradeAbout;
    @BindView(R.id.trade_type)
    TextView mTradeType;
    @BindView(R.id.trade_num)
    TextView mTradeNum;
    @BindView(R.id.trade_time)
    TextView mTradeTime;
    @BindView(R.id.trade_pay)
    TextView mTradePay;
    @BindView(R.id.trade_state)
    TextView mTradeState;

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_balance_detail);
    }


    @Override
    public void initData() {
        super.initData();
        mTitleBar.setTitle("余额明细");

        String id = getIntent().getStringExtra("id");


        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("logId", id);

        HttpRequest.httpGetString(Constants.Api.BALANCE_LOG_URL, hashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {

                BalanceDetailModel balanceDetailModel = JSONObject.parseObject(resultData, BalanceDetailModel.class);

                BalanceDetailModel.DataBean data = balanceDetailModel.getData();

                mTradeMoney.setText(data.getChangeNum());
                mTradeType.setText(data.getChangeName());
                mTradeNum.setText(data.getTradeNo());
                mTradeTime.setText(data.getChangeTime());
                mTradePay.setText(data.getPayTypeName());
                mTradeState.setText(data.getComment());

            }
        });

        mTradeAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                data.getLogId()
            }
        });


    }

    @OnClick(R.id.trade_about)
    public void onViewClicked() {
    }

    public static void newIntance(Context context, String id, String type) {
        Intent intent = new Intent(context, BalanceDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }
}
