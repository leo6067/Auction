package com.leo.auction.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.BroadCastReceiveUtils;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.RxTool;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.countDownTime.CountdownView;
import com.gyf.immersionbar.ImmersionBar;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.net.CustomerJsonCallBack;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.login.model.LoginModel;
import com.leo.auction.ui.login.model.LoginVerModel;
import com.leo.auction.ui.login.model.SmsCodeModel;

import com.leo.auction.ui.main.MainActivity;
import com.leo.auction.ui.main.home.activity.AuctionDetailActivity;
import com.leo.auction.ui.main.home.activity.ShopActivity;
import com.leo.auction.ui.main.home.model.SceneModel;
import com.leo.auction.ui.main.mine.dialog.RuleProtocolDialog;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.ui.web.AgentWebActivity;
import com.leo.auction.utils.DialogUtils;
import com.leo.auction.utils.Globals;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;


import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class LoginActivity extends BaseActivity implements View.OnClickListener, CountdownView.OnCountdownEndListener {
    private double exitTime;

    @BindView(R.id.afl_title)
    FrameLayout aflTitle;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.iv_name_delete)
    ImageView ivNameDelete;
    @BindView(R.id.et_verif_code)
    EditText etVerifCode;
    @BindView(R.id.tv_verif_code)
    TextView tvVerifCode;
    @BindView(R.id.cv_verif_code)
    CountdownView cvVerifCode;
    @BindView(R.id.fl_verif_code)
    LinearLayout flVerifCode;
    @BindView(R.id.cb_check)
    CheckBox cbCheck;
    @BindView(R.id.tv_agree)
    TextView tvAgree;

    WebView testWebview;
    @BindView(R.id.view_view)
    View viewView;
    private boolean isError = false;
    private int backPager;

    //账号输入监听
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (EmptyUtils.isEmpty(editable.toString())) {
                ivNameDelete.setVisibility(View.GONE);
            } else {
                ivNameDelete.setVisibility(View.VISIBLE);
            }
            if (editable.toString().length() != 11) {
                viewView.setVisibility(View.VISIBLE);
            } else {
                viewView.setVisibility(View.GONE);
            }
        }
    };
    private DialogUtils dialogUtils;


    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_login);

    }


    @Override
    public void initData() {
        super.initData();
        dialogUtils = new DialogUtils();
        setWebView();
    }


    @Override
    public void onResume() {
        super.onResume();
        Constants.Var.FOCUS_TYPE = -1;  //  关注片段 防止预加载
        ActivityManager.loginActivity = this;
        backPager = getIntent().getIntExtra("backPager", 0);
        BaseSharePerence.getInstance().setUserJson("");
        BaseSharePerence.getInstance().setLoginStatus(false);

    }


    @Override
    protected void onStop() {
        super.onStop();

        if (testWebview != null) {
            testWebview.stopLoading();
            ViewGroup parent = (ViewGroup) testWebview.getParent();
            if (parent != null) {
                parent.removeView(testWebview);
            }
            testWebview.clearHistory();
            testWebview.destroy();
            testWebview = null;
        }

    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this).statusBarDarkFont(true, 0.2f)
                .statusBarColor(R.color.white)
                .titleBarMarginTop(aflTitle)
                .keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    public void initEvent() {
        super.initEvent();
        etName.addTextChangedListener(textWatcher);
        cvVerifCode.setOnCountdownEndListener(this);
    }


    public void setWebView() {
        testWebview = findViewById(R.id.webview);
        WebSettings settings = testWebview.getSettings();
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


        // 页面布局。

        // 设置屏幕自适应。
        testWebview.getSettings().setUseWideViewPort(true);
        testWebview.getSettings().setLoadWithOverviewMode(true);
        // 建议禁止缓存加载，以确保在攻击发生时可快速获取最新的滑动验证组件进行对抗。
        testWebview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 设置不使用默认浏览器，而直接使用WebView组件加载页面。
        testWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Globals.log("xxxxxxxxxxxxxxxshouldOverrideUrlLoading  -------" + url);
                if (url.contains("protocol://android?code=hdyz&data=")) {
                    String jsonStr = url.substring(url.indexOf("data="));
                    jsonStr = jsonStr.substring(5);
                    /* Globals.log("xxxxxxxxxxxxxxxshouldOverrideUrlLoading  "  +  jsonStr );*/
                    LoginVerModel loginVerModel = JSONObject.parseObject(jsonStr, LoginVerModel.class);
                    BaseSharePerence.getInstance().setLoginView(jsonStr);
                    getVerifCode(loginVerModel);
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (!isError) {  //成功了，此时可以消失弹窗dissLoading()
//                    viewView.setVisibility(View.GONE);
                } else {
                    viewView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                isError = true;
            }
        });
        // 设置WebView组件支持加载JavaScript。
        testWebview.getSettings().setJavaScriptEnabled(true);
        // 建立JavaScript调用Java接口的桥梁。
//        testWebview.addJavascriptInterface(new testJsInterface(), "testInterface");
        // 加载业务页面。
        testWebview.loadUrl(Constants.WebApi.YZM_URL);

        Globals.log("XXXXXXXXXX testWebview 02" + Constants.WebApi.YZM_URL);
    }


    @OnClick({R.id.tv_agree, R.id.fl_verif_code, R.id.iv_common_login, R.id.iv_close, R.id.iv_wx_login, R.id.iv_name_delete})
    public void onViewClicked(View view) {
        if (!RxTool.isFastClick(RxTool.MIN_CLICK_DELAY_TIME_500)) {
            return;
        }
        switch (view.getId()) {

            case R.id.tv_agree:
                showAgreeDialog("1");
                break;
            case R.id.iv_common_login:
                login();
                break;
            case R.id.iv_close:
//                ActivityManager.JumpActivity(LoginActivity.this, MainActivity.class);
//                ActivityManager.mainActivity.setCurrent(0);
                if (backPager == 0) {
                    finish();
                } else {
                    ActivityManager.JumpActivity(LoginActivity.this, MainActivity.class);
                    ActivityManager.mainActivity.setCurrent(0);
                    finish();
                }
                break;
            case R.id.iv_wx_login:
                if (!cbCheck.isChecked()) {
                    ToastUtils.showShort("请勾选用户协议");
                    return;
                }
                wxLogin();
                break;
            case R.id.iv_name_delete:
                nameDelete();
                break;
        }
    }


    //出价 隐私 协议 政策
    private void showAgreeDialog(String type) {

        showWaitDialog();
        SceneModel.httpGetScene(type, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                SceneModel sceneModel = JSONObject.parseObject(resultData, SceneModel.class);
                if (sceneModel.getData() == null) {
                    return;
                }
                int redirectType = sceneModel.getData().getRedirectType(); //1-富文本  2-H5页面

                if (redirectType == 1) {
                    dialogUtils.showRuleProtocolDialog(LoginActivity.this,
                            sceneModel.getData().getContent(), new RuleProtocolDialog.IButtonListener() {
                                @Override
                                public void onClose() {
                                    dialogUtils.dissRuleProtocolDialog();
                                }
                            });
                } else {

                    String url = sceneModel.getData().getH5Url();
                    if (sceneModel.getData().getH5Url().contains("?")) {
                        url += "&isMargin=4";
                    } else {
                        url += "?isMargin=4";
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("title", "协议");
                    bundle.putString("url",url);
                    com.aten.compiler.base.ActivityManager.JumpActivity(LoginActivity.this, AgentWebActivity.class, bundle);
                }
            }
        });
    }

    //获取短信验证码
    public void getVerifCode(LoginVerModel loginVerModel) {


        if (etName.getText().toString().trim().length() < 11) {
            ToastUtils.showShort("请输入正确的手机号码");
            return;
        }

        showWaitDialog();
        SmsCodeModel.sendSmsCodeRequest("4", etName.getText().toString().trim(), loginVerModel, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                ToastUtils.showShort("发送成功");
                tvVerifCode.setVisibility(View.GONE);
                cvVerifCode.setVisibility(View.VISIBLE);
                flVerifCode.setVisibility(View.VISIBLE);
                flVerifCode.setEnabled(false);
                cvVerifCode.start(60000); // 毫秒
            }
        });

    }

    //验证码登录
    private void login() {
        if (EmptyUtils.isEmpty(etName.getText().toString().trim())) {
            ToastUtils.showShort("手机号码不能为空");
            return;
        }

        if (etName.getText().toString().trim().length() < 11) {
            ToastUtils.showShort("请输入正确的手机号码");
            return;
        }

        if (EmptyUtils.isEmpty(etVerifCode.getText().toString().trim())) {
            ToastUtils.showShort("验证码不能为空");
            return;
        }

        if (etVerifCode.getText().toString().trim().length() != 4) {
            ToastUtils.showShort("请输入正确的验证码");
            return;
        }

        if (!cbCheck.isChecked()) {
            ToastUtils.showShort("请勾选用户协议");
            return;
        }

        showWaitDialog();

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("number", etName.getText().toString().trim());
        hashMap.put("code", etVerifCode.getText().toString().trim());

        HttpRequest.httpPostString(Constants.Api.HOMEPAGE_USER_PHONE_LOGIN_URL, hashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();

                LoginModel loginModel = JSONObject.parseObject(resultData, LoginModel.class);
                if (loginModel.getResult().isSuccess()) {
                    ToastUtils.showShort("登录成功");
                    BaseSharePerence.getInstance().setLoginJson(resultData);
                    BaseSharePerence.getInstance().setLoginStatus(true);
                    UserModel.httpUpdateUser(LoginActivity.this);
                    if (backPager == 1) {
                        ActivityManager.JumpActivity(LoginActivity.this, MainActivity.class);
                        ActivityManager.mainActivity.setCurrent(0);
                    }
                    finish();
                } else {
                    ToastUtils.showShort(loginModel.getResult().getMessage());
                }
            }
        });
    }

    @Override
    public void onEnd(CountdownView cv) {
        flVerifCode.setEnabled(true);
        tvVerifCode.setVisibility(View.VISIBLE);
        cvVerifCode.setVisibility(View.GONE);
        flVerifCode.setVisibility(View.GONE);
        testWebview.loadUrl(Constants.WebApi.YZM_URL);
    }

    //微信登录
    private void wxLogin() {
        UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, new UMAuthListener() {

            @Override
            public void onStart(SHARE_MEDIA share_media) {
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                showWaitDialog();
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("unionId", map.get("unionid"));
                hashMap.put("openId", map.get("openid"));
                hashMap.put("nickname", map.get("name"));
                hashMap.put("headImg", map.get("iconurl"));
                HttpRequest.httpPostString(Constants.Api.HOMEPAGE_USER_WX_LOGIN_URL, hashMap, new HttpRequest.HttpCallback() {
                    @Override
                    public void httpError(Call call, Exception e) {
                        hideWaitDialog();
                    }

                    @Override
                    public void httpResponse(String resultData) {
                        hideWaitDialog();
                        LoginModel loginModel = JSONObject.parseObject(resultData, LoginModel.class);
                        if (loginModel.getResult().isSuccess()) {
                            ToastUtils.showShort("登录成功");
                            BaseSharePerence.getInstance().setLoginJson(resultData);
                            BaseSharePerence.getInstance().setLoginStatus(true);
                            UserModel.httpUpdateUser(LoginActivity.this);
                            if (backPager == 0) {
                                finish();
                            } else {
                                ActivityManager.JumpActivity(LoginActivity.this, MainActivity.class);
                                ActivityManager.mainActivity.setCurrent(0);
                                finish();
                            }

                        } else {
                            ToastUtils.showShort(loginModel.getResult().getMessage());
                        }
                    }
                });
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                if (!UMShareAPI.get(LoginActivity.this).isInstall(LoginActivity.this, SHARE_MEDIA.WEIXIN)) {
                    ToastUtils.showShort("请安装微信");
                } else {
                    ToastUtils.showShort("授权失败");
                }
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                ToastUtils.showShort("取消授权");
            }
        });
    }





    //清空登录号码
    private void nameDelete() {
        etName.setText("");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注意WebView的处理要写在super.onDestroy()之前
        if (testWebview != null) {
            testWebview.stopLoading();
            ViewGroup parent = (ViewGroup) testWebview.getParent();
            if (parent != null) {
                parent.removeView(testWebview);
            }
            testWebview.clearHistory();
            testWebview.destroy();
            testWebview = null;
        }


        UMShareAPI.get(this).release();
    }

    //backPager 0:代表返回上一页 1：代表回到首页
    public static void newIntance(Context context, int backPager) {
//        CookieSyncManager.createInstance(context);
//        CookieManager cookieManager = CookieManager.getInstance();
//        cookieManager.removeAllCookie();
//        CookieSyncManager.getInstance().sync();
        BaseSharePerence.getInstance().setLoginStatus(false);
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("backPager", backPager);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                // 构建Runnable对象，在runnable中更新界面
                Runnable runnableUi = new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showShort("锤定：再次点击退出");
                    }
                };
                runnableUi.run();
                exitTime = System.currentTimeMillis();
            } else {
                ActivityManager.exitAPP();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
