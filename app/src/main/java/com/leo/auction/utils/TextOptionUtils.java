package com.leo.auction.utils;

/**
 * ================================================
 * 项目名称：dgonline-android
 * 包    名：com.aten.dgonline_android.utils
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2018/12/5
 * 描    述：
 * ================================================
 */
public class TextOptionUtils {
    /**
     * 内部类实现单例模式
     * 延迟加载，减少内存开销
     */
    private static class SingletonHolder {
        private static TextOptionUtils instance = new TextOptionUtils();
    }

    /**
     * 私有的构造函数
     */
    private TextOptionUtils() {}

    public static TextOptionUtils getInstance() {
        return TextOptionUtils.SingletonHolder.instance;
    }

    //截取超过30个文字
    public String subLength(String text,int length){
        if (text.length() > length){
            text=text.substring(0,length);
        }

        return text;
    }
}
