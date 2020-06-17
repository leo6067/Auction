package com.aten.compiler.model;

import java.io.Serializable;

/**
 * ================================================
 * 项目名称：dgonline-android
 * 包    名：com.aten.compiler.model
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2018/11/9
 * 描    述：存储的缓存 带过期时间model
 * ================================================
 */
public class SpSaveModel<T> implements Serializable{
    private int saveTime;
    private T value;
    private long currentTime;

    public SpSaveModel() {
    }

    public SpSaveModel(int saveTime, T value,long currentTime) {
        this.saveTime = saveTime;
        this.value = value;
        this.currentTime=currentTime;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public int getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(int saveTime) {
        this.saveTime = saveTime;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
