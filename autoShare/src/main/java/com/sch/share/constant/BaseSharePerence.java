package com.sch.share.constant;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by admin on 2017/3/8.
 */
public class BaseSharePerence {


    private static SharedPreferences mSharedPreferences;
    private static BaseSharePerence mInstance;
    private static Object mSyncLock = new Object();

    private Context mContext;

    public static BaseSharePerence getInstance(Context context) {
        synchronized (mSyncLock) {
            if (mInstance == null) {
                mInstance = new BaseSharePerence(context);
            }
        }
        return mInstance;
    }

    private BaseSharePerence(Context context) {
        mContext = context;
        mSharedPreferences = mContext.getSharedPreferences("shareService", Context.MODE_PRIVATE);
    }


    public void removeInstance() {
        if (mInstance != null) {
            mInstance.removeInstance();
            mInstance = null;
        }
    }






    /**
     * @param
     * @param key 要取的数据的键
     * @param defValue 缺省值
     * @return
     */
    public boolean getBoolean(String key, boolean defValue) {
        return mSharedPreferences.getBoolean(key, defValue);
    }

    /**
     * 存储一个boolean类型数据
     * @param
     * @param key
     * @param value
     */
    public void putBoolean(String key, boolean value) {
        mSharedPreferences.edit().putBoolean(key, value).commit();
    }

    /**
     * 存储一个String类型的数据
     * @param
     * @param key
     * @param value
     */
    public void putString(String key, String value) {
        mSharedPreferences.edit().putString(key, value).commit();
    }

    /**
     * 获取一个String类型的数据
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
     * @param
     * @param key
     * @param value
     */
    public void putLong( String key, long value) {
        mSharedPreferences.edit().putLong(key, value).commit();
    }


    /**
     * 获取一个long类型的数据
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
     * @param
     * @param key
     * @param value
     */
    public void putInt(String key, int value) {
        mSharedPreferences.edit().putInt(key, value).commit();
    }



    /**
     * 获取一个int类型的数据
     * @param
     * @param key
     * @param defValue
     * @return
     */
    public int getInt( String key, int defValue) {
        return mSharedPreferences.getInt(key, defValue);
    }


}
