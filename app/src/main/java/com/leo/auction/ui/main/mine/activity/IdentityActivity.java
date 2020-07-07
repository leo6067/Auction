package com.leo.auction.ui.main.mine.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.countDownTime.CountdownView;
import com.aten.compiler.widget.title.TitleBar;
import com.leo.auction.R;
import com.leo.auction.base.BaseModel;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.login.model.LoginVerModel;
import com.leo.auction.ui.login.model.SmsCodeModel;
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
    @BindView(R.id.status_lin)
    LinearLayout mStatusLin;

    @BindView(R.id.cv_verif_code)
    CountdownView cvVerifCode;

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_identity);
        mTitleBar.setTitle("实名认证");
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
        UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();
        if ("".equals(userJson.getIdCard())) {
            mAuthLin.setVisibility(View.VISIBLE);
            mStatusLin.setVisibility(View.GONE);
            setWebView();
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

        if (nameStr.length() > 1) {
            ToastUtils.showShort("请输入正确的名字");
            return;
        }
        if (cardStr.length() < 15) {
            ToastUtils.showShort("请输入正确的身份证号");
            return;
        }
        if (phoneStr.length() != 11) {
            ToastUtils.showShort("请输入正确的手机号");
            return;
        }


        mHashMap.clear();
        mHashMap.put("idCard", cardStr);
        mHashMap.put("username", nameStr);
        mHashMap.put("phone", phoneStr);
        mHashMap.put("code", codeStr);
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
                ToastUtils.showShort(baseModel.getResult().getMessage());
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
}
