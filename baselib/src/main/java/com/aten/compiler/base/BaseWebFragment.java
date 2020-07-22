package com.aten.compiler.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;

import com.aten.compiler.R;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.widget.customWebview.X5AdvancedWebView;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebView;


/**
 * ================================================
 * 项目名称：PJHAndroidFrame
 * 包    名：com.frame.pjh_core.base
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2018/10/26
 * 描    述：
 * ================================================
 */
public class BaseWebFragment extends BaseFragment implements X5AdvancedWebView.Listener{
    public X5AdvancedWebView x5Webview;
    private String title;

    @Override
    public int getLayoutId() {
        return R.layout.activity_base_web;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        x5Webview=(X5AdvancedWebView)view.findViewById(R.id.x5_webview);
    }

    @Override
    public void initData() {
        //网页中的视频，上屏幕的时候，可能出现闪烁的情况，需要如下设置：Activity在onCreate时需要设置
        getActivity().getWindow().setFormat(PixelFormat.TRANSLUCENT);
        x5Webview.setGeolocationEnabled(false);
        x5Webview.setMixedContentAllowed(true);
        x5Webview.setCookiesEnabled(true);
        x5Webview.setThirdPartyCookiesEnabled(true);
        //先阻塞加载图片
        x5Webview.getSettings().setBlockNetworkImage(true);



        title=getArguments().getString("title");
        String url=getArguments().getString("url");
        boolean hasNeedTitleBar=getArguments().getBoolean("hasNeedTitleBar",false);
        boolean hasNeedRightView=getArguments().getBoolean("hasNeedRightView",false);

        setTitle(EmptyUtils.strEmpty(title));
        if (hasNeedTitleBar){
            mTitleBar.setVisibility(View.VISIBLE);
        }else {
            mTitleBar.setVisibility(View.GONE);
        }

        if (hasNeedRightView) {
            setRightIcon(getActivity().getResources().getDrawable(R.drawable.ic_launcher));
        }

        removeCookie(getActivity());
        x5Webview.loadUrl(url);
    }


        private void removeCookie(Context context) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }



    @Override
    public void initEvent() {
        super.initEvent();
        x5Webview.setListener(getActivity(), this);
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
    public void onDestroy() {
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
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        x5Webview.onActivityResult(requestCode, resultCode, intent);
        // ...
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
    public void onPageError(int errorCode, String description, String failingUrl) {}

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength,
                                    String contentDisposition, String userAgent) {}

    @Override
    public void onExternalPageRequest(String url) {}

    @Override
    public void onReceivedTitle(WebView view, String title) {
        if (TextUtils.isEmpty(title)){
            setTitle(title);
        }
    }
}
