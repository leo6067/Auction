package com.leo.auction.ui.main.mine.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.widget.title.TitleBar;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.leo.auction.R;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.MainActivity;
import com.leo.auction.ui.version.VersionDialog;
import com.leo.auction.ui.version.VersionDownDialog;
import com.leo.auction.ui.version.VersionModel;
import com.leo.auction.utils.Globals;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class AboutActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.version_tv)
    TextView mVersionTv;
    @BindView(R.id.version_info)
    TextView mVersionInfo;

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_about);
    }

    @Override
    public void initData() {
        super.initData();
        mTitleBar.setTitle("关于我们");
        AppUtils.AppInfo appInfo = AppUtils.getAppInfo();
        mVersionTv.setText("当前版本：v"  +  appInfo.getVersionName());
    }

    @OnClick(R.id.version_info)
    public void onViewClicked() {

        httpVerison();
    }


    public void httpVerison() {
        VersionModel.httpGetVersion(new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
            }

            @Override
            public void httpResponse(String resultData) {
                VersionModel returnData = JSONObject.parseObject(resultData, VersionModel.class);
                HashMap<String, String> mHashMap = new HashMap<>();
                mHashMap.put("isForce", returnData.getData().isForce() + "");
                mHashMap.put("downUrl", returnData.getData().getDownload());
                if (Integer.parseInt(returnData.getData().getVersion()) == AppUtils.getAppVersionCode()) {
                    ToastUtils.showShort("当前版本已是最新版本");
                    return;
                }


                VersionDialog versionDialog = new VersionDialog(AboutActivity.this, mHashMap, new VersionDialog.VersionInter() {
                    @Override
                    public void versionOK() {
//                        VersionDownDialog downDialog = new VersionDownDialog(MainActivity.this,"https://imtt.dd.qq.com/16891/apk/C831AEEA8BCC274A9EBA11DB22BBC375.apk");
                        VersionDownDialog downDialog = new VersionDownDialog(AboutActivity.this, returnData.getData().getDownload());
                        downDialog.show();
                        downDialog.setCanceledOnTouchOutside(false);
                    }

                    @Override
                    public void versionCancel() {
                    }
                });
                versionDialog.show();
                versionDialog.setCanceledOnTouchOutside(false);
            }
        });
    }

}
