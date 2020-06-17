package com.aten.compiler.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ================================================
 * 项目名称：dgonline-android
 * 包   名：com.aten.compiler.utils
 * 作   者：彭俊鸿
 * 邮   箱：1031028399@qq.com
 * 版   本：1.0
 * 创建日期：2018/12/25
 * 描   述：字符串操作工具类
 * ================================================
 */
public class StringUtils {

    /*
    * 字符串补位操作
    * indexStr 首位显示字符 如果没有 设置""
    * seatStr 占位字符
    * strNum 字符串的字数 =总的字符串长度-indexStr的长度
    * patchStr 原始字符串
    */
    public static String strPatchPos(String indexStr,String seatStr,String strNum,long patchStr){
        return String.format(indexStr+"%"+seatStr+strNum+"d",patchStr);
    }

    //身份证前三后三 中间*
    public static String encryptionIDCard(String IDCard){
        if (IDCard.length()==15){
            return IDCard.substring(0,3)+"*********"+IDCard.substring(12);
        }else if (IDCard.length()==18){
            return IDCard.substring(0,3)+"************"+IDCard.substring(15);
        }else {
            return "身份证号码格式有误！";
        }
    }

    //手机号码前三后三 中间*
    public static String encryptionPhone(String phone){
        if (!EmptyUtils.isEmpty(phone)&&phone.length()>=11){
            return phone.substring(0,3)+"****"+phone.substring(7);
        }else {
            return "手机号码格式有误！";
        }
    }

    //验证密码是否满足正则 true:代表满足数字,大写字母,小写字母,特殊符,至少其中三种组成密码 false反之
    public static boolean pwdFormat(String pwd) {
        Pattern p = Pattern.compile(
                "^(?![A-Za-z]+$)(?![A-Z\\d]+$)(?![A-Z\\W]+$)(?![a-z\\d]+$)(?![a-z\\W]+$)(?![\\d\\W]+$)\\S{8,100}$");
        Matcher m = p.matcher(pwd);
        if (m.find()) {
            return true;
        }
        return false;
    }
}
