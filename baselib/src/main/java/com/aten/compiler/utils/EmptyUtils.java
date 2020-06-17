package com.aten.compiler.utils;

import android.text.TextUtils;

/**
 * project:yfpw-android
 * package:${PACKACE_NAME}
 * Created by 彭俊鸿 on 2018/7/4.
 * e-mail : 1031028399@qq.com
 */
public class EmptyUtils {

    //判断字符串是否是空字符串 true:空或者null false:不为空或者null
    public static boolean isEmpty(String str){
        if (TextUtils.isEmpty(str)){
            return true;
        }else {
            return false;
        }
    }

    //设置字符串 若输入的字符串为空或者NULL 返回""
    public static String strEmpty(String str){
        if (TextUtils.isEmpty(str)){
            return "";
        }else {
            return str;
        }
    }
}
