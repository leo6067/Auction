package com.leo.auction.ui.login;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.permission.PermissionHelper;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.net.CustomerJsonCallBack;
import com.leo.auction.ui.login.model.LoginModel;
import com.leo.auction.ui.login.model.UserInfoModel;
import com.leo.auction.ui.login.utils.BackLoginUtils;
import com.leo.auction.ui.login.utils.LoginUtils;
import com.leo.auction.ui.main.MainActivity;
import com.leo.auction.utils.Globals;
import com.leo.auction.utils.NetworkUtils;


public class StartActivity extends BaseActivity {


    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_start);
    }

    @Override
    public void initData() {
        super.initData();
        Globals.log("xxxxxxxxxxxxxxxxxx  00 静默登录"   );
        ActivityManager.addActivity(this);
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
                Globals.log("xxxxxxxxxxxxxxxxxx  02 静默登录"   );
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
        BackLoginUtils backLoginUtils = new BackLoginUtils();
        backLoginUtils.backstageLogin(new BackLoginUtils.onBackLoginListener() {
            @Override
            public void onLoginSuccess() {
//                if (Constants.Nouns.SHOW_IM_PAGER) {
//                    //im注册登录
//                    BackLoginUtils backLoginUtils = new BackLoginUtils();
//                    if (UserInfoUtils.getInstance().idDialogConnect()) {//已注册环信
//                        //进行登录
//                        backLoginUtils.imLogin(WelcomeActivity.this);
//                    } else {//未注册环信
//                        backLoginUtils.imRegister(WelcomeActivity.this, new BackLoginUtils.IMRegistListener() {
//                            @Override
//                            public void onIMRegistSuccess(String userName) {
//                                backLoginUtils.imLogin(WelcomeActivity.this);
//                            }
//
//                            @Override
//                            public void onIMRegistError() {
//
//                            }
//                        });
//                    }
//                }
                ActivityManager.JumpActivity(StartActivity.this, MainActivity.class, null);
            }

            @Override
            public void onLogingFail() {
                ActivityManager.JumpActivity(StartActivity.this, LoginActivity.class, null);
            }

            @Override
            public void onError() {
                ActivityManager.JumpActivity(StartActivity.this, LoginActivity.class, null);
            }
        });
//        ActivityManager.JumpActivity(StartActivity.this, MainActivity.class, null);
    }

}