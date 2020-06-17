package com.leo.auction.utils;

import java.math.BigDecimal;


/**
 * Created by Leo on 2017/8/27.
 *
 * 用于单位换算计算
 */

public class UnitConversionUtils {



    //米换算成千米
    public static  String mTkm(double address)
    {

        if (address > 1000) {
            float v = (float) address / 1000;
            BigDecimal bg = new BigDecimal(v);
            double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  //四舍五入保留两位小数
            return f1+"km";
        } else {

            BigDecimal bg = new BigDecimal(address);
            double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            return f1+"m";
        }
    }





}
