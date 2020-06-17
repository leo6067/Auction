package com.leo.auction.ui.login.utils;


import com.leo.auction.net.CustomerJsonCallBack;
import com.leo.auction.ui.login.model.LoginModel;
import com.leo.auction.ui.login.model.UserInfoModel;
import com.leo.auction.utils.Globals;

import org.litepal.LitePal;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.utils.wxPay
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/12/28 0028
 * 描    述：静默登录
 * ================================================
 */
public class BackLoginUtils {
    public static final String IM_PASSWORD = "123456";
    private onBackLoginListener listener;
    private UserInfoModel userInfoModel;

    public BackLoginUtils() {
    }

    //后台登录
    public void backstageLogin(final onBackLoginListener listener) {
        this.listener = listener;
        userInfoModel = LitePal.findFirst(UserInfoModel.class);
        if (userInfoModel == null) {
            listener.onLogingFail();
            return;
        }

        login();
    }

    //静默登录
    private void login() {
        LoginModel.sendDefaulltLoginRequest("loginUtils", userInfoModel.getToken(),
                new CustomerJsonCallBack<LoginModel>() {
                    @Override
                    public void onRequestError(LoginModel returnData, String msg) {
//                        LitePal.deleteAll(UserInfoModel.class);
                        Globals.log("xxxxxxxxxxxxxxxxxx  静默登录" + msg  + "    ");
                        listener.onError();
//                        ToastUtils.show(msg);
                    }

                    @Override
                    public void onRequestSuccess(LoginModel returnData) {
                        Globals.log("xxxxxxxxxxxxxxxxxx  静默登录成功"  );
                        //1.登录成功之后  先清空数据库 保证在数据库中只会存在一条数据
                        if (returnData.getData() != null) {
                            LoginUtils.getInstance().loginSuccess(returnData.getData(), new LoginUtils.ILoginOperation() {
                                @Override
                                public void onCompleted(boolean isDialogConnect) {
                                    listener.onLoginSuccess();
                                }
                            });
                        }
                    }
                });

    }



    public interface onBackLoginListener {
        void onLoginSuccess();

        void onLogingFail();

        void onError();
    }

    //环信注册或者登陆监听
    public interface IMRegistListener {
        void onIMRegistSuccess(String userName);

        void onIMRegistError();
    }

    //环信注册或者登陆监听
    public interface IMLoginListener {
        void onIMLoginSuccess();

        void onIMLogingFail();
    }
}
