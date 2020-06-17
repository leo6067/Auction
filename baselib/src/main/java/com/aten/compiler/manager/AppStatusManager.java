package com.aten.compiler.manager;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.aten.compiler.manager
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/12/31 0031
 * 描    述：app 状态管理
 * ================================================
 */
public class AppStatusManager {
    public static final int STATUS_RECYCLE =-1; //被回收
    public static final int STATUS_NORMAL=1;    //正常
    public int appStatus = STATUS_RECYCLE;//APP状态 初始值被系统回收
    /**
     * 内部类实现单例模式
     * 延迟加载，减少内存开销
     */
    private static class SingletonHolder {
        private static AppStatusManager instance = new AppStatusManager();
    }

    /**
     * 私有的构造函数
     */
    private AppStatusManager() {
    }

    public static AppStatusManager getInstance() {
        return AppStatusManager.SingletonHolder.instance;
    }

    //得到状态
    public int getAppStatus() {
        return appStatus;
    }
    //设置状态
    public void setAppStatus(int appStatus) {
        this.appStatus = appStatus;
    }

}
