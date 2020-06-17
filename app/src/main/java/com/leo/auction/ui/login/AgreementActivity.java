package com.leo.auction.ui.login;

import android.content.Context;
import android.content.Intent;

import com.aten.compiler.base.BaseWebActivity;

public class AgreementActivity extends BaseWebActivity {


    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public void initData() {
        super.initData();
//        x5Webview.addJavascriptInterface(this, "ShareElec");//代理名
    }

    public static void newIntance(Context context, String title, String url, boolean hasNeedTitleBar, boolean hasNeedRightView) {
        Intent intent = new Intent(context, AgreementActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("url",url);
        intent.putExtra("hasNeedTitleBar",hasNeedTitleBar);
        intent.putExtra("hasNeedRightView",hasNeedRightView);
        context.startActivity(intent);
    }
}

