package com.leo.auction.base;

import android.content.Context;
import android.content.SharedPreferences;

import com.alibaba.fastjson.JSONObject;
import com.leo.auction.model.home.VersionJson;
import com.leo.auction.ui.login.model.LoginModel;
import com.leo.auction.ui.login.model.UserInfoModel;


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
     * 保存用户账号基本信息
     */
    public void setUserJson(String userJson) {
        Constants.Var.ISLOGIN = true;
        mSharedPreferences.edit().putString("setUserJson", userJson).commit();
    }

    public LoginModel.DataBean getUserJson() {
        LoginModel.DataBean userInfoModel = null;
        String infoStr = mSharedPreferences.getString("setUserJson", "");
        if (infoStr.length() > 2) {
            LoginModel loginModel = JSONObject.parseObject(infoStr, LoginModel.class);
            userInfoModel = loginModel.getData();

        }
        return userInfoModel;
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
