package com.leo.auction.mvp;

import com.leo.auction.model.ResultJson;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.HashMap;

/**
 * Created by Leo on 2019/4/11.
 */

public class LoginMvpPersenter implements LoginMvpContract.LoginModel {

    LoginMvpContract.LoginView mLoginView;

    public LoginMvpPersenter(LoginMvpContract.LoginView loginView) {
        mLoginView = loginView;
        mLoginView.bindModel(this);
    }

    @Override
    public void login(String name, String password) {

        HashMap<String, Object> dataMap = new HashMap<>();
        HashMap<String, Object> dataMapA = new HashMap<>();
//        dataMap.put("expand", "user_login");
//        dataMapA.put("u_name", mLoginView.getUserName());
//        dataMapA.put("pwd", MD5Utils.stringToMD5(mLoginView.getUserPwd()));
//        dataMap.put("data", dataMapA);
        mLoginView.showLoading();
        mLoginView.refresh();

        String urlT = "http://api.1355.com/api/v1/article/article_cate_list";
        HashMap<String, String> reMap = new HashMap<>();
        reMap.put("pid", "1");


        EasyHttp.post("api/v1/article/article_cate_list")
                .params(reMap)
                .execute(new SimpleCallBack<ResultJson>() {
                    @Override
                    public void onError(ApiException e) {
                        mLoginView.closeLoading();

                    }

                    @Override
                    public void onSuccess(ResultJson resultJson) {
                        mLoginView.closeLoading();

                    }
                });
    }
}
