package com.leo.auction.ui.main;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.aten.compiler.base.BaseFragment;
import com.aten.compiler.base.BaseWebFragment;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.widget.customWebview.X5AdvancedWebView;
import com.aten.compiler.widget.title.TitleBar;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.ui.login.LoginActivity;
import com.leo.auction.ui.login.LoginWxActivity;
import com.leo.auction.ui.main.mine.activity.CommodityReleaseActivity;
import com.leo.auction.ui.main.mine.activity.SettingActivity;
import com.leo.auction.ui.main.mine.activity.StoreQRCodeActivity;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.utils.Globals;
import com.tencent.smtt.sdk.CookieSyncManager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {

    //
    @BindView(R.id.webview)
    WebView mWebview;
    @BindView(R.id.title_bar)
    TitleBar mTitleBar;


    private String shouldUrl = "";

    String picUrl = "";
    private String mTitle;

    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }





    @Override
    public void initView(View view) {
        super.initView(view);

        WebSettings settings = mWebview.getSettings();
        settings.setDefaultTextEncodingName("utf-8");// 避免中文乱码
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setSupportMultipleWindows(true);
        settings.setAllowFileAccess(false);
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //自适应屏幕
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);


        mWebview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                WebView.HitTestResult hitTestResult = mWebview.getHitTestResult();
                if (null == hitTestResult)
                    return false;
                int type = hitTestResult.getType();
                switch (type) {
                    case WebView.HitTestResult.EDIT_TEXT_TYPE: // 选中的文字类型
                        break;
                    case WebView.HitTestResult.PHONE_TYPE: // 处理拨号
                        break;
                    case WebView.HitTestResult.EMAIL_TYPE: // 处理Email
                        break;
                    case WebView.HitTestResult.GEO_TYPE: // 　地图类型
                        break;
                    case WebView.HitTestResult.SRC_ANCHOR_TYPE: // 超链接
                        break;
                    case WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE: // 带有链接的图片类型
                    case WebView.HitTestResult.IMAGE_TYPE: // 处理长按图片的菜单项

                        // 弹出保存图片的对话框
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("提示");
                        builder.setMessage("保存图片到本地");
                        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                picUrl = hitTestResult.getExtra();//获取图片链接


                                //保存图片到相册
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        url2bitmap(picUrl);
                                    }
                                }).start();
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            // 自动dismiss
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();

                        return true;
                    case WebView.HitTestResult.UNKNOWN_TYPE: //未知
                        break;
                }
                return false;
            }
        });


        mTitleBar.getLeftView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWebview.canGoBack()) {
                    mWebview.goBack();
                }
            }
        });
    }


    //
    @Override
    public void onResume() {
        super.onResume();
        UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();
        if (userJson == null) {
            return;
        }


        try {
            String token = BaseSharePerence.getInstance().getUserJson().getH5Token();
            String userId = BaseSharePerence.getInstance().getUserJson().getUserId();
            String httpUrl = Constants.WebApi.WEB_MINE_URL + token + "&userId="+userId;
            webLoad(httpUrl);
        } catch (Exception e) {
        }


    }

    private void removeCookie(Context context) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }


    public void url2bitmap(String url) {
        Bitmap bm = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;
            int length = http.getContentLength();
            conn.connect();
            // 获得图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
            if (bm != null) {
                save2Album(bm);
            }
        } catch (Exception e) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), "保存失败", Toast.LENGTH_SHORT).show();
                }
            });
            e.printStackTrace();
        }
    }

    private void save2Album(Bitmap bitmap) {
        File appDir = new File(Environment.getExternalStorageDirectory(), "二维码####");
        if (!appDir.exists()) appDir.mkdir();
        String[] str = picUrl.split("/");
        String fileName = str[str.length - 1];
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            onSaveSuccess(file);
        } catch (IOException e) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), "保存失败", Toast.LENGTH_SHORT).show();
                }
            });
            e.printStackTrace();
        }
    }

    private void onSaveSuccess(final File file) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getActivity().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
                Toast.makeText(getActivity(), "已成功保存到：相册-二维码####", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void webLoad(String httpUrl) {

        WebViewClient wvc = new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Globals.log("log  leo  shouldInterceptRequest  onPageStarted " + url);
                super.onPageStarted(view, url, favicon);
            }

            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
//                setCookie(request.getUrl().toString(), userCooike);
//                Globals.log("xxxxxxxxxxxxxx request view" + shouldUrl);
                return super.shouldInterceptRequest(view, request);
            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Globals.log("xxxxxxxxxxxxxx request token" + shouldUrl);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    shouldUrl = request.getUrl().toString();
                } else {
                    shouldUrl = request.toString();
                }
                Globals.log("xxxxxxxxxxxxxx vv token" + shouldUrl);
                if (shouldUrl.contains(Constants.WebApi.WEB_MINE_URL)) {  // 我的
                    mTitleBar.setVisibility(View.GONE);
                } else {
                    mTitleBar.setVisibility(View.VISIBLE);
                }
                if (shouldUrl.contains("pages/sub/set/index")) {  // 我的--设置
                    if (mWebview.canGoBack()) {
                        mWebview.goBack();

                    }
                    ActivityManager.JumpActivity(getActivity(), SettingActivity.class);
                }
                if (shouldUrl.contains("pages/sub/extension/index")) {  // 我的--推广

                    ActivityManager.JumpActivity(getActivity(), SettingActivity.class);
                }
                if (shouldUrl.contains("pages/sub/set/index?kf=1")) {  // 我的--客服
                    ActivityManager.JumpActivity(getActivity(), SettingActivity.class);
                }

                if (shouldUrl.contains("pages/sub/super/detail")) {  // 我的--超级仓库拍品详情
                    ActivityManager.JumpActivity(getActivity(), SettingActivity.class);
                }

                if (shouldUrl.contains("pages/follow/follow")) {  // 我的--关注
                    ActivityManager.JumpActivity(getActivity(), SettingActivity.class);
                }


                return true;
            }
        };


        mWebview.setWebViewClient(wvc);
        mWebview.setWebChromeClient(new WebChromeClient() {
            /*** 视频播放相关的方法 **/
            @Override
            public View getVideoLoadingProgressView() {
                FrameLayout frameLayout = new FrameLayout(getActivity());
                frameLayout.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT));
                return frameLayout;
            }

            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {

            }

            @Override
            public void onHideCustomView() {

            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

                Globals.log("xxxxxxxxxxxxxx cc token" + title);
                if (title.length() == 0) {
                    return;
                }

                mTitleBar.setTitle(title);
//                String substring = title.substring(title.length() - 3, title.length());
//                if ("直播间".equals(substring)) {
//                    mTextBreach.setVisibility(View.VISIBLE);
//                    mTextBack.setVisibility(View.GONE);
//                } else {
//                    mTextBreach.setVisibility(View.GONE);
//                    mTextBack.setVisibility(View.VISIBLE);
//                }


                if (title.equals("我的")) {  // 我的
                    mTitleBar.setVisibility(View.GONE);
                    ActivityManager.mainActivity.setBottomVisible();
                } else {
                    ActivityManager.mainActivity.setBottomGone();
                    mTitleBar.setVisibility(View.VISIBLE);
                }
                if (title.equals("设置")) {  // 我的--设置
                    if (mWebview.canGoBack()) {
                        mWebview.goBack();
                    }
                    ActivityManager.JumpActivity(getActivity(), SettingActivity.class);
                }
                if (title.equals("推广中心")) {  // 我的--推广
                    if (mWebview.canGoBack()) {
                        mWebview.goBack();
                    }
                    ActivityManager.JumpActivity(getActivity(), StoreQRCodeActivity.class);
                }
                if (title.equals("发布拍品")) {  // 我的--推广
                    if (mWebview.canGoBack()) {
                        mWebview.goBack();
                    }
                    ActivityManager.JumpActivity(getActivity(), CommodityReleaseActivity.class);
                }


//                if (title.equals("客服")) {  // 我的--客服
//                    ActivityManager.JumpActivity(getActivity(), SettingActivity.class);
//                }
//
//                if (title.equals("设置")) {  // 我的--超级仓库拍品详情
//                    ActivityManager.JumpActivity(getActivity(), SettingActivity.class);
//                }
//
                if (title.equals("关注")) {  // 我的--关注
                    Constants.Var.FOCUS_TYPE = 1;
                    getActivity().finish();
                    MainActivity.newIntance(getActivity(), 2);
                }

            }
        });

        // 加载Web地址
        mWebview.loadUrl(httpUrl);

//        CookieManager cookieManager = CookieManager.getInstance();
//        String CookieStr = cookieManager.getCookie(httpUrl);
//        Globals.log("log  leo  shouldOverrideUrlLoading  赋值之后 xxx " + CookieStr);
    }



    public static MineFragment newIntance(String title, String url, Boolean hasNeedTitleBar, Boolean hasNeedRightView) {
        MineFragment mineFragment = new MineFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putString("title", title);
        bundle.putBoolean("hasNeedTitleBar", hasNeedTitleBar);
        bundle.putBoolean("hasNeedRightView", hasNeedRightView);
        mineFragment.setArguments(bundle);
        return mineFragment;
    }




}
