package com.leo.auction.ui.login;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.BroadCastReceiveUtils;
import com.aten.compiler.utils.RxTool;
import com.aten.compiler.utils.ToastUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.leo.auction.R;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;

import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.login.model.LoginModel;

import com.leo.auction.ui.main.MainActivity;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class LoginWxActivity extends BaseActivity {

    @BindView(R.id.afl_title)
    FrameLayout aflTitle;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.cb_check)
    CheckBox cbCheck;
    @BindView(R.id.tv_agree)
    TextView tvAgree;

    private int backPager;
    private String loginCloseType="1";

    //操作登录页面
    private BroadCastReceiveUtils mOnLoginOption=new BroadCastReceiveUtils() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String type=intent.getStringExtra("type");
            switch (type){
                case "0"://关闭该页面
                    loginCloseType="0";
                    finish();
                    break;
            }
        }
    };



    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_login_wx);
    }


    @Override
    public void initData() {
        backPager = getIntent().getIntExtra("backPager", 0);


        BroadCastReceiveUtils.registerLocalReceiver(this, Constants.Action.ACTION_OPTION_LOGIN_ACTIVITY,mOnLoginOption);
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
    }

    @OnClick({R.id.iv_common_login,R.id.ll_phone_login,R.id.iv_close})
    public void onViewClicked(View view) {
        if (!RxTool.isFastClick(RxTool.MIN_CLICK_DELAY_TIME_500)) {
            return;
        }
        switch (view.getId()) {
            case R.id.iv_common_login:
                if (!cbCheck.isChecked()){
                    ToastUtils.showShort("请勾选用户协议");
                    return;
                }
                wxLogin();
                break;
            case R.id.ll_phone_login:
                LoginActivity.newIntance(LoginWxActivity.this,0);
                break;
            case R.id.iv_close:
                goFinish();
                break;
        }
    }

    //微信登录
    private void wxLogin() {
        UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {}

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                showWaitDialog();
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("unionId",map.get("unionid"));
                hashMap.put("openId",map.get("openid"));
                hashMap.put("nickname",map.get("name"));
                hashMap.put("headImg",map.get("iconurl"));
                HttpRequest.httpPostString(Constants.Api.HOMEPAGE_USER_WX_LOGIN_URL, hashMap, new HttpRequest.HttpCallback() {
                    @Override
                    public void httpError(Call call, Exception e) {
                        hideWaitDialog();
                    }

                    @Override
                    public void httpResponse(String resultData) {
                        hideWaitDialog();
                        LoginModel loginModel = JSONObject.parseObject(resultData, LoginModel.class);
                        if (loginModel.getResult().isSuccess()){
                            ToastUtils.showShort("登录成功");
                            httpUser();
                            BaseSharePerence.getInstance().setLoginJson(resultData);
                            MainActivity.newIntance(LoginWxActivity.this, 0);
                            finish();
                        }
                    }
                });
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                if (!UMShareAPI.get(LoginWxActivity.this).isInstall(LoginWxActivity.this, SHARE_MEDIA.WEIXIN)) {
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

    //关闭页面
    private void onClosePager(boolean isDialogConnect){
        //刷新首页
        Intent intent=new Intent(Constants.Action.ACTION_REFRESH_HOME);
        intent.putExtra("type","0");
        BroadCastReceiveUtils.sendLocalBroadCast(LoginWxActivity.this, intent);



        if (1 == backPager) {
            MainActivity.newIntance(LoginWxActivity.this, 0);
        }
        finish();

//        if (Constants.SHOW_IM_PAGER){
//            BackLoginUtils backLoginUtils=new BackLoginUtils();
//            if (isDialogConnect){//已注册环信
//                backLoginUtils.imLogin(LoginActivity.this);
//            }else {//未注册环信
//                backLoginUtils.imRegister(LoginActivity.this, new BackLoginUtils.IMRegistListener() {
//                    @Override
//                    public void onIMRegistSuccess(String userName) {
//                        backLoginUtils.imLogin(LoginActivity.this);
//                    }
//
//                    @Override
//                    public void onIMRegistError() {
//
//                    }
//                });
//            }
//        }
    }

    @Override
    public void onBackPressed() {
//        if (backPager==1){
//            MainActivity.newIntance(LoginWxActivity.this, 0);
//        }
//        super.onBackPressed();
//        if ("0".equals(loginCloseType)){
//            this.overridePendingTransition(0,0);
//        }else {
//            this.overridePendingTransition(0,R.anim.activity_up_to_down);
//        }
    }

    private void httpUser() {
        HashMap<String, String> hashMap = new HashMap<>();

        HttpRequest.httpGetString(Constants.Api.USER_URL, hashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {
                UserModel userModel = JSONObject.parseObject(resultData, UserModel.class);
                BaseSharePerence.getInstance().setUserJson(JSON.toJSONString(userModel.getData()));
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        UMShareAPI.get(this).release();
        BroadCastReceiveUtils.unregisterLocalReceiver(this,mOnLoginOption);
    }

    //backPager 0:代表返回上一页 1：代表回到首页
    public static void newIntance(Context context, int backPager) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("backPager", backPager);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

        ((BaseActivity)context).overridePendingTransition(R.anim.activity_down_to_up,0);
    }
}
