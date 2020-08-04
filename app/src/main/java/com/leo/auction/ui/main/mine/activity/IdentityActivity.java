package com.leo.auction.ui.main.mine.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.countDownTime.CountdownView;
import com.aten.compiler.widget.title.TitleBar;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.BaseModel;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.login.model.LoginVerModel;
import com.leo.auction.ui.login.model.SmsCodeModel;
import com.leo.auction.ui.main.MainActivity;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.utils.Globals;
import com.ruffian.library.widget.RTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class IdentityActivity extends BaseActivity implements CountdownView.OnCountdownEndListener {

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.name_edit)
    EditText mNameEdit;
    @BindView(R.id.sfzh_edit)
    EditText mSfzhEdit;
    @BindView(R.id.phone_edit)
    EditText mPhoneEdit;
    @BindView(R.id.yzm_edit)
    EditText mYzmEdit;
    @BindView(R.id.webview)
    WebView testWebview;
    @BindView(R.id.webview_view)
    TextView mWebviewView;
    @BindView(R.id.commit_tv)
    RTextView mCommitTv;
    @BindView(R.id.auth_lin)
    LinearLayout mAuthLin;
    @BindView(R.id.name_tv)
    TextView mNameTv;
    @BindView(R.id.sfzh_tv)
    TextView mSfzhTv;
    @BindView(R.id.shop_name)
    TextView mShopName;
    @BindView(R.id.status_lin)
    LinearLayout mStatusLin;
    @BindView(R.id.phone_lin)
    LinearLayout mPhoneLin;
    @BindView(R.id.yzm_lin)
    LinearLayout mYzmLin;
    @BindView(R.id.web_rlin)
    RelativeLayout mWebRlin;

    @BindView(R.id.cv_verif_code)
    CountdownView cvVerifCode;

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_identity);

    }


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

            if (editable.toString().length() != 11) {
                mWebviewView.setVisibility(View.VISIBLE);
            } else {
                mWebviewView.setVisibility(View.GONE);
            }
        }
    };

    @Override
    public void initData() {
        super.initData();
        mTitleBar.setTitle("实名认证");
        UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();
        if (userJson == null || EmptyUtils.isEmpty(userJson.getIdCard())) {

            if (EmptyUtils.isEmpty(userJson.getPhone())) {   //手机号为空
                mAuthLin.setVisibility(View.VISIBLE);
                mStatusLin.setVisibility(View.GONE);
                mPhoneLin.setVisibility(View.VISIBLE);
                mYzmLin.setVisibility(View.VISIBLE);
                mWebRlin.setVisibility(View.VISIBLE);
                setWebView();
            } else {
                mAuthLin.setVisibility(View.VISIBLE);
                mPhoneLin.setVisibility(View.GONE);
                mYzmLin.setVisibility(View.GONE);
                mStatusLin.setVisibility(View.GONE);
                mWebRlin.setVisibility(View.GONE);
            }
        } else {
            mAuthLin.setVisibility(View.GONE);
            mStatusLin.setVisibility(View.VISIBLE);
            mNameTv.setText(userJson.getUsername());
            String cardStr = userJson.getIdCard();
            // 用于显示的加*身份证
            String show_id = cardStr.substring(0, 3) + "********" + cardStr.substring(11);
            mSfzhTv.setText(show_id);
        }

    }

    @Override
    public void initEvent() {
        super.initEvent();
        mNameEdit.addTextChangedListener(textWatcher);
        cvVerifCode.setOnCountdownEndListener(this);
    }

    private void setWebView() {

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
                    getVerifCode(loginVerModel);
                    return true;
                } else {
                    return false;
                }

            }
        });
        // 设置WebView组件支持加载JavaScript。
        testWebview.getSettings().setJavaScriptEnabled(true);
        // 建立JavaScript调用Java接口的桥梁。
//        testWebview.addJavascriptInterface(new testJsInterface(), "testInterface");
        // 加载业务页面。
        testWebview.loadUrl("https://w.taojianlou.com/super-store/hd2.html");
    }


    //获取短信验证码
    public void getVerifCode(LoginVerModel loginVerModel) {


        if (mNameEdit.getText().toString().trim().length() < 11) {
            ToastUtils.showShort("请输入正确的手机号码");
            return;
        }

        showWaitDialog();
        SmsCodeModel.sendSmsCodeRequest("6", mNameEdit.getText().toString().trim(), loginVerModel, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                ToastUtils.showShort("发送成功");
                cvVerifCode.setVisibility(View.VISIBLE);
                cvVerifCode.start(60000); // 毫秒
            }
        });

    }

    private void httpName() {
        String nameStr = mNameEdit.getText().toString();
        String cardStr = mSfzhEdit.getText().toString();
        String phoneStr = mPhoneEdit.getText().toString();
        String codeStr = mYzmEdit.getText().toString();
        String shopName = mShopName.getText().toString();

        if (nameStr.length() == 0) {
            ToastUtils.showShort("请输入正确的名字");
            return;
        }
        if (cardStr.length() != 18) {
            ToastUtils.showShort("请输入正确的身份证号");
            return;
        }
        if (shopName.length() == 0 ||shopName.length() > 6) {
            ToastUtils.showShort("请输入不超过6个字的店铺名字");
            return;
        }


        mHashMap.clear();
        mHashMap.put("idCard", cardStr);
        mHashMap.put("username", nameStr);
        if (phoneStr.length() > 0) {
            mHashMap.put("phone", phoneStr);
        }
        if (codeStr.length() > 0) {
            mHashMap.put("code", codeStr);
        }
        mHashMap.put("shopName", shopName);
        showWaitDialog();
        HttpRequest.httpPostString(Constants.Api.REAL_NAME_URL, mHashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                BaseModel baseModel = JSONObject.parseObject(resultData, BaseModel.class);
                if (baseModel.getResult().isSuccess()) {
                    ToastUtils.showShort("认证成功");
                    UserModel.httpUpdateUser();
                    finish();
                } else {
                    ToastUtils.showShort(baseModel.getResult().getMessage());
                }
            }
        });

    }

    @OnClick(R.id.commit_tv)
    public void onViewClicked() {
        httpName();
    }

    @Override
    public void onEnd(CountdownView cv) {
        cvVerifCode.setVisibility(View.GONE);
        testWebview.loadUrl("https://w.taojianlou.com/super-store/hd2.html");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            ActivityManager.JumpActivity(IdentityActivity.this, MainActivity.class);
            ActivityManager.mainActivity.recreateActivity();
            ActivityManager.mainActivity.setCurrent(4);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
