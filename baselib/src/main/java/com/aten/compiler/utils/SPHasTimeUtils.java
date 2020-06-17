package com.aten.compiler.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.aten.compiler.model.SpSaveModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * ================================================
 * 项目名称：dgonline-android
 * 包    名：com.aten.compiler.utils
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2018/11/9
 * 描    述：SharedPreferences工具类，可设置缓存时间
 * ================================================
 */
public class SPHasTimeUtils {

    //保存时间单位
    public static final int TIME_SECOND=1;
    public static final int TIME_MINUTES=60*TIME_SECOND;
    public static final int TIME_HOUR = 60 *TIME_MINUTES;
    public static final int TIME_DAY = TIME_HOUR * 24;
    public static final int TIME_MAX = Integer.MAX_VALUE; // 不限制存放数据的数量
    public static final int DURATION_UNIT=1000;

    private static final String fileName = "config";
    private final Gson gson;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private static SPHasTimeUtils INSTANCE = null;

    public static SPHasTimeUtils getInstance(Context context) {
        if (null == INSTANCE) {
            INSTANCE = new SPHasTimeUtils(context);
        }
        return INSTANCE;
    }
    private SPHasTimeUtils(Context context) {
        gson=new Gson();
        sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void setString(String e, String value) {
        SpSaveModel<String> spSaveModel=new SpSaveModel<>(TIME_MAX,value,System.currentTimeMillis());
        String json=gson.toJson(spSaveModel);
        editor.putString(e, json);
        editor.commit();
    }

    /**
     *
     * @param e  存放的key
     * @param value 存放的value
     * @param saveTime 缓存时间
     */
    public void setString(String e, String value,int saveTime) {
        SpSaveModel<String> spSaveModel=new SpSaveModel<>(saveTime,value,System.currentTimeMillis());
        String json=gson.toJson(spSaveModel);
        editor.putString(e, json);
        editor.commit();
    }

    //数据是否已经超过缓存时间了 true：代表未过期 false代表已过期
    public boolean isOverTimeString(String e){
        String json=sp.getString(e,"");
        if(!TextUtils.isEmpty(json)){
            SpSaveModel<String> spSaveModel= gson.fromJson(json,new TypeToken<SpSaveModel<String>>() {}.getType());
            if(isTimeOut(spSaveModel.getCurrentTime(),spSaveModel.getSaveTime())){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param e 存放的key
     * @param defValue 该key不存在或者过期时，返回的默认值
     * @return
     */
    public String getString(String e, String defValue){
        String json=sp.getString(e,"");
        if(!TextUtils.isEmpty(json)){
            SpSaveModel<String> spSaveModel= gson.fromJson(json,new TypeToken<SpSaveModel<String>>() {}.getType());
            if(isTimeOut(spSaveModel.getCurrentTime(),spSaveModel.getSaveTime())){
                return spSaveModel.getValue();
            }
        }
        return defValue;
    }

    public void setInt(String e, int value) {
        SpSaveModel<Integer> spSaveModel=new SpSaveModel<>(TIME_MAX,value,System.currentTimeMillis());
        String json=gson.toJson(spSaveModel);
        editor.putString(e, json);
        editor.commit();
    }

    public void setInt(String e, int value,int saveTime) {
        SpSaveModel<Integer> spSaveModel=new SpSaveModel<>(saveTime,value,System.currentTimeMillis());
        String json=gson.toJson(spSaveModel);
        editor.putString(e, json);
        editor.commit();
    }

    //数据是否已经超过缓存时间了 true：代表未过期 false代表已过期
    public boolean isOverTimeInt(String e){
        String json=sp.getString(e,"");
        if(!TextUtils.isEmpty(json)){
            SpSaveModel<Integer> spSaveModel= gson.fromJson(json,new TypeToken<SpSaveModel<Integer>>() {}.getType());
            if(isTimeOut(spSaveModel.getCurrentTime(),spSaveModel.getSaveTime())){
                return true;
            }
        }
        return false;
    }

    public Integer getInt(String e, int defValue){
        String json=sp.getString(e,"");
        if(!TextUtils.isEmpty(json)){
            SpSaveModel<Integer> spSaveModel=gson.fromJson(json,new TypeToken<SpSaveModel<Integer>>() {}.getType());
            if(isTimeOut(spSaveModel.getCurrentTime(),spSaveModel.getSaveTime())){
                return spSaveModel.getValue();
            }
        }
        return defValue;
    }

    public void setBoolean(String e, boolean value) {
        SpSaveModel<Boolean> spSaveModel=new SpSaveModel<>(TIME_MAX,value,System.currentTimeMillis());
        String json=gson.toJson(spSaveModel);
        editor.putString(e, json);
        editor.commit();
    }

    public void setBoolean(String e, boolean value,int saveTime) {
        SpSaveModel<Boolean> spSaveModel=new SpSaveModel<>(saveTime,value,System.currentTimeMillis());
        String json=gson.toJson(spSaveModel);
        editor.putString(e, json);
        editor.commit();
    }

    public boolean getBoolean(String e, boolean defValue){
        String json=sp.getString(e,"");
        if(!TextUtils.isEmpty(json)){
            SpSaveModel<Boolean> spSaveModel= gson.fromJson(json,new TypeToken<SpSaveModel<Boolean>>() {}.getType());
            if(isTimeOut(spSaveModel.getCurrentTime(),spSaveModel.getSaveTime())){
                return spSaveModel.getValue();
            }
        }
        return defValue;
    }

    public void setLong(String e, long value) {
        SpSaveModel<Long> spSaveModel=new SpSaveModel<>(TIME_MAX,value,System.currentTimeMillis());
        String json=gson.toJson(spSaveModel);
        editor.putString(e, json);
        editor.commit();
    }

    public void setLong(String e, long value,int saveTime) {
        SpSaveModel<Long> spSaveModel=new SpSaveModel<>(saveTime,value,System.currentTimeMillis());
        String json=gson.toJson(spSaveModel);
        editor.putString(e, json);
        editor.commit();
    }

    public long getLong(String e, long defValue){
        String json=sp.getString(e,"");
        if(!TextUtils.isEmpty(json)){
            SpSaveModel<Long> spSaveModel= gson.fromJson(json,new TypeToken<SpSaveModel<Long>>() {}.getType());
            if(isTimeOut(spSaveModel.getCurrentTime(),spSaveModel.getSaveTime())){
                return spSaveModel.getValue();
            }
        }
        return defValue;
    }

    //true：代表未过期 false代表已过期
    public boolean isTimeOut(long saveCurrentTime,int saveTime){
        return  (System.currentTimeMillis()-saveCurrentTime)/DURATION_UNIT<saveTime;
    }

    public void set(String e, Object value,int saveTime) {
        SpSaveModel<Object> spSaveModel=new SpSaveModel<>(saveTime,value,System.currentTimeMillis());
        String json=gson.toJson(spSaveModel);
        editor.putString(e, json);
        editor.commit();
    }
}
