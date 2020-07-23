package com.leo.auction.ui.main;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;

import com.aten.compiler.base.BaseWebActivity;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.customWebview.X5AdvancedWebView;
import com.aten.compiler.widget.title.TitleBar;
import com.leo.auction.R;
import com.leo.auction.utils.Globals;
import com.tencent.smtt.sdk.WebView;

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
    @Override
    public void initData() {
        super.initData();

        String  mTitle = getIntent().getStringExtra("title");

        x5Webview.addJavascriptInterface(new AndroidInterface(),"android");
    }



    public class AndroidInterface {

        private Handler deliver = new Handler(Looper.getMainLooper());


        /**
         * 与js交互时用到的方法，在js里直接调用的
         */



        @JavascriptInterface
        public void invitationAction() {



                    ToastUtils.showShort("xxxxxxxxxxxxxxxxxx在js里直接调用的 ");

                    Globals.log("xxxxxxxxxxxxxxxxxx在js里直接调用的");


        }


        @android.webkit.JavascriptInterface
        public void groupHireActivity() {


            deliver.post(new Runnable() {
                @Override
                public void run() {
//                    Bundle bundle = new Bundle();
//                    String httpUrl = "http://m.tuifang123.com/CloudRelease/HouseList.aspx?posttype=1";  //出租
//                    bundle.putString("httpUrl", httpUrl);
//                    IntentUtils.JumpActivity(WebActivity.this, WebActivity.class, bundle);
                }
            });


        }


    }


}
