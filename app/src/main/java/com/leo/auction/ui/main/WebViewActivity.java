package com.leo.auction.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.aten.compiler.base.BaseWebActivity;
import com.aten.compiler.widget.title.TitleBar;
import com.leo.auction.R;

public class WebViewActivity extends BaseWebActivity {

//
//    TitleBar mTitleBar;
//
//    WebView mWebview;
//    @Override
//    public void setContentViewLayout() {
//        setContentView(R.layout.activity_web_view);
//        mWebview = findViewById( R.id.webview);
//        mTitleBar = findViewById( R.id.title_bar);
//    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }
}
