package com.aten.compiler.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.aten.compiler.utils
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/10/25 0025
 * 描    述：
 * ================================================
 */
public class DecimalFormatUtils {

    /**
            * 内部类实现单例模式
     * 延迟加载，减少内存开销
     */
    private static class SingletonHolder {
        private static DecimalFormatUtils instance = new DecimalFormatUtils();
    }

    /**
     * 私有的构造函数
     */
    private DecimalFormatUtils() {}

    public static DecimalFormatUtils getInstance() {
        return DecimalFormatUtils.SingletonHolder.instance;
    }
    //保留小数的格式
    public static String keepPlaces(String pattern,double o){
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(o);
    }

}
