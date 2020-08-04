package com.leo.auction.base;

import android.content.Context;
import android.content.SharedPreferences;

import com.alibaba.fastjson.JSONObject;
import com.leo.auction.model.home.VersionJson;
import com.leo.auction.ui.login.model.CommonModel;
import com.leo.auction.ui.login.model.LoginModel;
import com.leo.auction.ui.login.model.LoginVerModel;
import com.leo.auction.ui.main.mine.model.UserModel;


/**
 * Created by admin on 2017/3/8.
 */
public class BaseSharePerence {


    private static SharedPreferences mSharedPreferences;
    private static BaseSharePerence mInstance;
    private static Object mSyncLock = new Object();

    private Context mContext;

    public static BaseSharePerence getInstance() {
        synchronized (mSyncLock) {
            if (mInstance == null) {
                mInstance = new BaseSharePerence(BaseAppContext.getInstance());
            }
        }
        return mInstance;
    }

    private BaseSharePerence(Context context) {
        mContext = context;
        mSharedPreferences = mContext.getSharedPreferences(Constants.Nouns.SHARE_NAME, Context.MODE_PRIVATE);
    }


    public void removeInstance() {
        if (mInstance != null) {
            mInstance.removeInstance();
            mInstance = null;
        }
    }


    /**
     * 公共参数，等级
     */

    public void setCommonJson(String userJson) {
        mSharedPreferences.edit().putString("setCommonJson", userJson).commit();
    }

    public CommonModel.DataBean getCommonJson() {
        CommonModel.DataBean userInfoModel = null;
        String infoStr = mSharedPreferences.getString("setCommonJson", "");
        if (infoStr.length() > 2) {
            CommonModel loginModel = JSONObject.parseObject(infoStr, CommonModel.class);
            userInfoModel = loginModel.getData();

        }
        return userInfoModel;
    }


    /**
     * 保存验证码 返回
     */
    public void setLoginView(String userJson) {
        mSharedPreferences.edit().putString("setLoginView", userJson).commit();
    }

    public LoginVerModel getLoginView() {
        LoginVerModel loginVerModel = null;
        String jsonStr = mSharedPreferences.getString("setLoginView", "");
        loginVerModel = JSONObject.parseObject(jsonStr, LoginVerModel.class);
        return loginVerModel;
    }


    /**
     * 保存用户登录基本信息
     */
    public void setLoginJson(String userJson) {

        mSharedPreferences.edit().putString("setLoginJson", userJson).commit();
    }

    public LoginModel.DataBean getLoginJson() {
        LoginModel.DataBean userInfoModel = null;
        String infoStr = mSharedPreferences.getString("setLoginJson", "");
        if (infoStr.length() > 2) {
            LoginModel loginModel = JSONObject.parseObject(infoStr, LoginModel.class);
            userInfoModel = loginModel.getData();

        }
        return userInfoModel;
    }


    /**
     * 保存用户账号基本信息
     */
    public void setUserJson(String userJson) {
        mSharedPreferences.edit().putString("setUserJson", userJson).commit();
    }

    public UserModel.DataBean getUserJson() {
        String infoStr = mSharedPreferences.getString("setUserJson", "");
        return JSONObject.parseObject(infoStr, UserModel.DataBean.class);
    }




    /**
     * 保存用户是否登录
     */
    public void setLoginStatus(boolean loginStatus) {
        mSharedPreferences.edit().putBoolean("setLoginStatus", loginStatus).commit();
    }

    public boolean getLoginStatus() {

        return  mSharedPreferences.getBoolean("setLoginStatus", false);
    }




    /**
     * 保存版本升级
     */
    public void setVersion(String LoginInfo) {
        mSharedPreferences.edit().putString("setVersion", LoginInfo).commit();
    }

    public VersionJson.DataBean getVersion() {
        VersionJson.DataBean version = null;
        String infoStr = mSharedPreferences.getString("setVersion", "");
        if (infoStr.length() > 2) {
            VersionJson userInfoBean = JSONObject.parseObject(infoStr, VersionJson.class);
            version = userInfoBean.getData();
        }
        return version;
    }


    /**
     * @param
     * @param key      要取的数据的键
     * @param defValue 缺省值
     * @return
     */
    public boolean getBoolean(String key, boolean defValue) {
        return mSharedPreferences.getBoolean(key, defValue);
    }

    /**
     * 存储一个boolean类型数据
     *
     * @param
     * @param key
     * @param value
     */
    public void putBoolean(String key, boolean value) {
        mSharedPreferences.edit().putBoolean(key, value).commit();
    }

    /**
     * 存储一个String类型的数据
     *
     * @param
     * @param key
     * @param value
     */
    public void putString(String key, String value) {
        mSharedPreferences.edit().putString(key, value).commit();
    }

    /**
     * 获取一个String类型的数据
     *
     * @param
     * @param key
     * @param defValue
     * @return
     */
    public String getString(String key, String defValue) {
        return mSharedPreferences.getString(key, defValue);
    }


    /**
     * 存储一个long类型的数据
     *
     * @param
     * @param key
     * @param value
     */
    public void putLong(String key, long value) {
        mSharedPreferences.edit().putLong(key, value).commit();
    }


    /**
     * 获取一个long类型的数据
     *
     * @param
     * @param key
     * @param defValue
     * @return
     */
    public long getLong(String key, long defValue) {
        return mSharedPreferences.getLong(key, defValue);
    }


    /**
     * 存储一个int类型的数据
     *
     * @param
     * @param key
     * @param value
     */
    public void putInt(String key, int value) {
        mSharedPreferences.edit().putInt(key, value).commit();
    }


    /**
     * 获取一个int类型的数据
     *
     * @param
     * @param key
     * @param defValue
     * @return
     */
    public int getInt(String key, int defValue) {
        return mSharedPreferences.getInt(key, defValue);
    }


}
