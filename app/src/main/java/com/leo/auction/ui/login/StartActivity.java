package com.leo.auction.ui.login;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.permission.PermissionHelper;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.login.model.LoginModel;

import com.leo.auction.ui.main.MainActivity;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.utils.Globals;

import java.util.HashMap;

import okhttp3.Call;


public class StartActivity extends BaseActivity {


    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_start);
    }

    @Override
    public void initData() {
        super.initData();
        ActivityManager.addActivity(this);
        httpCommon();
        rePremissions();
    }

    void rePremissions() {
        ActivityManager.JumpActivity(StartActivity.this, MainActivity.class, null);
        PermissionHelper permissionHelper = new PermissionHelper();
        permissionHelper.requestPermission(StartActivity.this, new PermissionHelper.onPermissionListener() {
            @Override
            public void onSuccess() {
                backLogin();
            }
            @Override
            public void onFail() {
                backLogin();
            }
//        }, Permission.READ_PHONE_STATE, Permission.WRITE_EXTERNAL_STORAGE,Permission.RECORD_AUDIO);
        },  com.yanzhenjie.permission.Permission.WRITE_EXTERNAL_STORAGE );  //注释掉打电话
//        com.yanzhenjie.permission.Permission.CAMERA,
    }


    /**
     * 用户拒绝，并且选择不再提示,可以引导用户进入权限设置界面开启权限
     *
     * @param context
     */
    private static void alertDialog(@Nullable final Activity context, String dialogMessage) {
        if (context != null) {
            new AlertDialog.Builder(context)
                    .setCancelable(false)
                    .setTitle("权限要求")
                    .setMessage(dialogMessage)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                            intent.setData(uri);
                            context.startActivity(intent);
                            context.finish();
                        }
                    })
                    .setNegativeButton("取消", null)
                    .create()
                    .show();
        }
    }



    //登录
    private void backLogin() {
        LoginModel.DataBean userJson = BaseSharePerence.getInstance().getLoginJson();
        if (userJson!=null){
            showWaitDialog();
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("token",userJson.getToken());
            HttpRequest.httpPostString(Constants.Api.HOMEPAGE_USER_DEFAULT_LOGIN_URL, hashMap, new HttpRequest.HttpCallback() {
                @Override
                public void httpError(Call call, Exception e) {
                    hideWaitDialog();
                }

                @Override
                public void httpResponse(String resultData) {
                    hideWaitDialog();
                    LoginModel loginModel = JSONObject.parseObject(resultData, LoginModel.class);
                    if (loginModel.getResult().isSuccess()){
                        BaseSharePerence.getInstance().setLoginJson(resultData);
                        httpUser();
                        MainActivity.newIntance(StartActivity.this, 0);
                        finish();
                    }
                }
            });
        }else {

            MainActivity.newIntance(StartActivity.this, 0);
            finish();
        }
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


    private void httpCommon(){
        HashMap<String, String> hashMap = new HashMap<>();
        HttpRequest.httpGetString(Constants.Api.COMMON_URL, hashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
            }

            @Override
            public void httpResponse(String resultData) {
                BaseSharePerence.getInstance().setCommonJson(resultData);
            }
        });
    }

}