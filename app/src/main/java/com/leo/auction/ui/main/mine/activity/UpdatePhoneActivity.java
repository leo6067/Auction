package com.leo.auction.ui.main.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.allen.library.SuperButton;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.BroadCastReceiveUtils;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.countDownTime.CountdownView;
import com.leo.auction.R;
import com.leo.auction.base.BaseModel;
import com.leo.auction.base.Constants;
import com.leo.auction.net.CustomerJsonCallBack;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.login.model.LoginVerModel;
import com.leo.auction.ui.login.model.SmsCodeModel;
import com.leo.auction.utils.Globals;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class UpdatePhoneActivity extends BaseActivity implements CountdownView.OnCountdownEndListener {


    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.fl_verif_code)
    LinearLayout flVerifCode;
    @BindView(R.id.et_verification_code)
    EditText etVerificationCode;
    @BindView(R.id.tv_verif_code)
    TextView tvVerifCode;
    @BindView(R.id.cv_verif_code)
    CountdownView cvVerifCode;
    @BindView(R.id.stb_sure_update)
    SuperButton stbSureUpdate;
    @BindView(R.id.webview)
    WebView testWebview;
    @BindView(R.id.view_view)
    View viewView;


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
                viewView.setVisibility(View.VISIBLE);
            } else {
                viewView.setVisibility(View.GONE);
            }
        }
    };

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_update_phone);
    }


    @Override
    public void initEvent() {
        super.initEvent();
        etPhone.addTextChangedListener(textWatcher);
        cvVerifCode.setOnCountdownEndListener(this);
    }

    @OnClick({R.id.stb_sure_update, R.id.fl_verif_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_verif_code:
                if (EmptyUtils.isEmpty(etPhone.getText().toString().trim())) {
                    ToastUtils.showShort("请输入手机号码");
                    return;
                }

                if (etPhone.getText().toString().trim().length() < 11) {
                    ToastUtils.showShort("请输入正确的手机号码");
                    return;
                }
//                showWaitDialog();
//                getVerifCode();
                break;
            case R.id.stb_sure_update:
                sureUpdate();
                break;
        }
    }

    //获取短信验证码
    public void getVerifCode(LoginVerModel loginVerModel) {
        showWaitDialog();
        SmsCodeModel.sendSmsCodeRequest("2", etPhone.getText().toString().trim(), loginVerModel, new HttpRequest.HttpCallback() {
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
                flVerifCode.setEnabled(false);
                cvVerifCode.start(60000); // 毫秒
            }
        });
    }

    //确认更新
    private void sureUpdate() {
        if (EmptyUtils.isEmpty(etPhone.getText().toString().trim())) {
            ToastUtils.showShort("请输入手机号码");
            return;
        }

        if (etPhone.getText().toString().trim().length() < 11) {
            ToastUtils.showShort("请输入正确的手机号码");
            return;
        }
        if (EmptyUtils.isEmpty(etVerificationCode.getText().toString().trim())) {
            ToastUtils.showShort("请输入验证码");
            return;
        }

        showWaitDialog();

        BaseModel.httpUserPhone(etPhone.getText().toString().trim(), etVerificationCode.getText().toString().trim(), new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                ToastUtils.showShort("设置成功");
                Intent intent = new Intent();
                intent.putExtra("phone", etPhone.getText().toString().trim());
                setResult(RESULT_OK, intent);
                goFinish();
            }
        });

    }

    @Override
    public void onEnd(CountdownView cv) {
        flVerifCode.setEnabled(true);
        tvVerifCode.setVisibility(View.GONE);
        cvVerifCode.setVisibility(View.GONE);
        setWebView();
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


    public static void newIntance(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, UpdatePhoneActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }


}
