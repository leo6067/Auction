package com.leo.auction.ui.main.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.ActivityManager;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.widget.title.TitleBar;
import com.leo.auction.R;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.mine.model.AssetDetailModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;


/**
 * 资产明细
 */
public class AssetDetailActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.ye_tv)
    TextView mYeTv;
    @BindView(R.id.zf_tv)
    TextView mZfTv;

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_asset_detail);
        mTitleBar.setTitle("余额明细");
    }


    @Override
    public void initData() {
        super.initData();
        showWaitDialog();
        HttpRequest.httpGetString(Constants.Api.BALANCE_URL, mHashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                AssetDetailModel assetDetailModel = JSONObject.parseObject(resultData, AssetDetailModel.class);
                mYeTv.setText(assetDetailModel.getData().getBalance() + "");
            }
        });
    }

    @OnClick({R.id.title_bar, R.id.ye_tv, R.id.zf_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar:
                finish();
                break;
            case R.id.ye_tv:
                ActivityManager.JumpActivity(AssetDetailActivity.this,BalanceActivity.class);
                break;
            case R.id.zf_tv:
                ActivityManager.JumpActivity(AssetDetailActivity.this,PaySafeActivity.class);
                break;
        }
    }
}
