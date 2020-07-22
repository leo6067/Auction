package com.aten.compiler.base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.aten.compiler.R;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.widget.customWebview.X5AdvancedWebView;
import com.aten.compiler.widget.title.TitleBar;
import com.blankj.utilcode.util.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseWebActivity extends BaseActivity implements X5AdvancedWebView.Listener{


    TitleBar mTitleBar;

    X5AdvancedWebView x5Webview;
    private String mTitle;

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_base_web);
        x5Webview =(X5AdvancedWebView) findViewById(R.id.x5_webview);
        mTitleBar = findViewById(R.id.title_bar);
    }


    @Override
    public void initView() {

//        mWebview.setWebChromeClient(new WebChromeClient());
//        mWebview.setWebViewClient(new WebViewClient());
//        WebSettings settings = mWebview.getSettings();
//        settings.setDefaultTextEncodingName("utf-8");// 避免中文乱码
//
//
//        settings.setDomStorageEnabled(true);//设置适应Html5 //重点是这个设置
//        settings.setUseWideViewPort(true);
//        settings.setLoadWithOverviewMode(true);
//        settings.setDefaultTextEncodingName("UTF-8");
//        settings.setAllowContentAccess(true); // 是否可访问Content Provider的资源，默认值 true
//        settings.setAllowFileAccess(true);    // 是否可访问本地文件，默认值 true
//        // 是否允许通过file url加载的Javascript读取本地文件，默认值 false
//        settings.setAllowFileAccessFromFileURLs(false);
//        // 是否允许通过file url加载的Javascript读取全部资源(包括文件,http,https)，默认值 false
//        settings.setAllowUniversalAccessFromFileURLs(false);
//
//        settings.setJavaScriptEnabled(true);
//
//        settings.setSupportMultipleWindows(true);
//
//        settings.setAppCacheEnabled(true);
//        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        //自适应屏幕
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        settings.setLoadWithOverviewMode(true);
//        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);


    }

    @Override
    public void initData() {
        //网页中的视频，上屏幕的时候，可能出现闪烁的情况，需要如下设置：Activity在onCreate时需要设置
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        x5Webview.setGeolocationEnabled(false);
        x5Webview.setMixedContentAllowed(true);
        x5Webview.setCookiesEnabled(true);
        x5Webview.setThirdPartyCookiesEnabled(true);
        //先阻塞加载图片
        x5Webview.getSettings().setBlockNetworkImage(true);

        mTitle = getIntent().getStringExtra("title");
        String url = getIntent().getStringExtra("url");
        boolean hasNeedTitleBar = getIntent().getBooleanExtra("hasNeedTitleBar", false);
        boolean hasNeedRightView = getIntent().getBooleanExtra("hasNeedRightView", false);
        setTitle(EmptyUtils.strEmpty(mTitle));
        if (hasNeedTitleBar) {
            mTitleBar.setVisibility(View.VISIBLE);
        } else {
            mTitleBar.setVisibility(View.GONE);
        }

        if (hasNeedRightView) {
            setRightIcon(getResources().getDrawable(com.aten.compiler.R.drawable.ic_launcher));
        }

        x5Webview.loadUrl(url);
    }




//    private void webLoad(String httpUrl) {
//
//        WebViewClient wvc = new WebViewClient() {
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
////                Globals.log("log  leo  shouldInterceptRequest  onPageStarted " + url);
//                super.onPageStarted(view, url, favicon);
//            }
//
//            @Nullable
//            @Override
//            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
////                setCookie(request.getUrl().toString(), userCooike);
//                return super.shouldInterceptRequest(view, request);
//            }
//
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
////                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////                    shouldUrl = request.getUrl().toString() ;
////                } else {
////                    shouldUrl = request.toString() ;
////                }
////
////                if (shouldUrl.contains("live/room")) {
////                    mTextBreach.setVisibility(View.VISIBLE);
////                    mTextBack.setVisibility(View.GONE);
////                } else {
////                    mTextBreach.setVisibility(View.GONE);
////                    mTextBack.setVisibility(View.VISIBLE);
////                }
//
//                return true;
//            }
//        };
//
//
//        mWebview.setWebViewClient(wvc);
//        mWebview.setWebChromeClient(new WebChromeClient() {
//            /*** 视频播放相关的方法 **/
//            @Override
//            public View getVideoLoadingProgressView() {
//                FrameLayout frameLayout = new FrameLayout(BaseWebActivity.this);
//                frameLayout.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT));
//                return frameLayout;
//            }
//
//            @Override
//            public void onShowCustomView(View view, CustomViewCallback callback) {
//
//            }
//
//            @Override
//            public void onHideCustomView() {
//
//            }
//
//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//                super.onReceivedTitle(view, title);
////                String substring = title.substring(title.length() - 3, title.length());
////                if ("直播间".equals(substring)) {
////                    mTextBreach.setVisibility(View.VISIBLE);
////                    mTextBack.setVisibility(View.GONE);
////                } else {
////                    mTextBreach.setVisibility(View.GONE);
////                    mTextBack.setVisibility(View.VISIBLE);
////                }
//
//                mTitleBar.setTitle(title);
//            }
//        });
//
//        // 加载Web地址
//        mWebview.loadUrl(httpUrl);
//
////        CookieManager cookieManager = CookieManager.getInstance();
////        String CookieStr = cookieManager.getCookie(httpUrl);
////        Globals.log("log  leo  shouldOverrideUrlLoading  赋值之后 xxx " + CookieStr);
//    }



    private void setCookie(String httpUrl, String cookieStr) {

        try {
            String aesDncode ="";

            String cookie = "userinfo_cookie=" + aesDncode;
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.setCookie(httpUrl, cookie);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cookieManager.flush();
            } else {
                cookieManager.removeSessionCookie();
                CookieSyncManager.getInstance().sync();
            }
            CookieSyncManager.getInstance().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        cookieStr = "oFjT7MMSj4RkdWMYb2BDHv3I0RX2gKTpeM18%2BOst4bUi8BIskxezx3tWDrSW9tc%2BPGnbafcMVI%2BJsyQujQFVE00MnZM87i8Jj3guZ%2FTiHNr1ydxaiTjWIQROFxQHus8Vx5MeGbh4tndvR1DPtvz5sEiqvY1P6wa9G8oYJZVky819fbkgOxfL771IR7qq4iwoMUDx8tTuHUKOB%2Fw5DfIetnVe5b%2BrNn53%2F4dLcB1cZJeDShKMlROFPifEg2WyZZtWdpvNi3njBx%2BrZHmUfo8f6qlDGj3JkcZW5cSMCYgROkaduq2FgcUIK2i%2FGfQhUBreTbVzkaTZYP5X3wePzHIXHg%3D%3D";

    }

    @Override
    public void initEvent() {
        super.initEvent();
        x5Webview.setListener(this, this);
    }

    @SuppressLint("NewApi")
    @Override
    public void onResume() {
        super.onResume();
        x5Webview.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    public void onPause() {
        x5Webview.onPause();
        // ...
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (x5Webview != null) {
            x5Webview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            x5Webview.clearHistory();
            x5Webview.destroy();
            x5Webview = null;
        }
        if (hud != null&&hud.isShowing()) {
            hud.dismiss();
            hud=null;
        }
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        x5Webview.onActivityResult(requestCode, resultCode, intent);
        // ...
    }

    @Override
    public void onBackPressed() {
        if (!x5Webview.onBackPressed()) {
            return;
        }
        // ...
        super.onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //如果按下的是回退键且历史记录里确实还有页面
        if ((keyCode == KeyEvent.KEYCODE_BACK) && x5Webview.canGoBack()) {
            x5Webview.goBack();
            return true;
        } else {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {
        showWaitDialog();
    }

    @Override
    public void onPageFinished(String url) {
        hideWaitDialog();
        x5Webview.getSettings().setBlockNetworkImage(false);
        //判断webview是否加载了，图片资源
        if (!x5Webview.getSettings().getLoadsImagesAutomatically()) {
            //设置wenView加载图片资源
            x5Webview.getSettings().setLoadsImagesAutomatically(true);
        }
    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {

    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }

    @Override
    public void onExternalPageRequest(String url) {

    }

    @Override
    public void onReceivedTitle(com.tencent.smtt.sdk.WebView view, String title) {
        if (TextUtils.isEmpty(mTitle)) {
            setTitle(title);
        }
    }
}
