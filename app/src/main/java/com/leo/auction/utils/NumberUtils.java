package com.leo.auction.utils;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;



/**
 * Created by Leo on 2017/11/23.
 *
 *
 *


 */

public class NumberUtils {

    /** 可重复使用的Long常量0L */
    public static final Long LONG_ZERO = Long.valueOf(0L);
    /** 可重复使用的Long常量1L */
    public static final Long LONG_ONE = Long.valueOf(1L);
    /** 可重复使用的Long常量-1L */
    public static final Long LONG_MINUS_ONE = Long.valueOf(-1L);
    /** 可重复使用的Integer常量0 */
    public static final Integer INTEGER_ZERO = Integer.valueOf(0);
    /** 可重复使用的Integer常量1 */
    public static final Integer INTEGER_ONE = Integer.valueOf(1);
    /** 可重复使用的Integer常量-1 */
    public static final Integer INTEGER_MINUS_ONE = Integer.valueOf(-1);
    /** 可重复使用的Short常量0 */
    public static final Short SHORT_ZERO = Short.valueOf((short) 0);
    /** 可重复使用的Short常量1 */
    public static final Short SHORT_ONE = Short.valueOf((short) 1);
    /** 可重复使用的Short常量-1 */
    public static final Short SHORT_MINUS_ONE = Short.valueOf((short) -1);
    /** 可重复使用的Byte常量0 */
    public static final Byte BYTE_ZERO = Byte.valueOf((byte) 0);
    /** 可重复使用的Byte常量1 */
    public static final Byte BYTE_ONE = Byte.valueOf((byte) 1);
    /** 可重复使用的Byte常量-1 */
    public static final Byte BYTE_MINUS_ONE = Byte.valueOf((byte) -1);
    /** 可重复使用的Double常量0.0d */
    public static final Double DOUBLE_ZERO = Double.valueOf(0.0d);
    /** 可重复使用的Double常量1.0d */
    public static final Double DOUBLE_ONE = Double.valueOf(1.0d);
    /** 可重复使用的Double常量-1.0d */
    public static final Double DOUBLE_MINUS_ONE = Double.valueOf(-1.0d);
    /** 可重复使用的Float常量0.0f */
    public static final Float FLOAT_ZERO = Float.valueOf(0.0f);
    /** 可重复使用的Float常量1.0f */
    public static final Float FLOAT_ONE = Float.valueOf(1.0f);
    /** 可重复使用的Float常量-1.0f */
    public static final Float FLOAT_MINUS_ONE = Float.valueOf(-1.0f);

    /**
     * 我们使用的时候无需创建该工具类，像这样使用 NumberUtils.toInt("6");
     * 该构造方法是为了符合JavaBean的规范
     */
    public NumberUtils() {
        super();
    }

    //-----------------------------------------------------------------------


    //将字符串三位一个逗号显示
    public static String getString(String str){
        DecimalFormat df = new DecimalFormat("###,###");
        return df.format(Double.parseDouble(str));
    }



    //如果字符串是科学：如123213e21，转化为字符串
    public static String getStringOutE(String str){
        BigDecimal bd = new BigDecimal(str);
        return bd.toPlainString();
    }


    //设置保留两位小数，0.00
    public static String formatDouble(String num)
    {
        if (num.length()<1)
        {
            return "";
        }
        double aDouble = Double.parseDouble(num);

        String pattern = "#0.00";//格式代码，".000"代表保留三位小数，是0的输出0
        DecimalFormat formatter = new DecimalFormat();
        formatter.applyPattern(pattern);

//        DecimalFormat df = new DecimalFormat("0.00");

        return formatter.format(aDouble);
    }








    /**
     * 使用java正则表达式去掉多余的.与0
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s){
        if(s != null && s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }




    // Min in array
    //--------------------------------------------------------------------
    /**
     * 返回数组的最小值
     */
    public static long min(final long... array) {
        // Validates input
        validateArray(array);

        // Finds and returns min
        long min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        return min;
    }

    /**
     * 返回数组中最小的
     * 从3.4版本后 修改方法签名  min(int[]) to min(int...)
     */
    public static int min(final int... array) {
        // Validates input
        validateArray(array);

        // Finds and returns min
        int min = array[0];
        for (int j = 1; j < array.length; j++) {
            if (array[j] < min) {
                min = array[j];
            }
        }

        return min;
    }

    /**
     * 返回数组中最小的
     * 从3.4版本后 修改方法签名  min(short[]) to min(short...)
     */
    public static short min(final short... array) {
        // Validates input
        validateArray(array);

        // Finds and returns min
        short min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        return min;
    }

    /**
     * 返回数组中最小的
     * 从3.4版本后 修改方法签名  min(byte[]) to min(byte...)
     */
    public static byte min(final byte... array) {
        // Validates input
        validateArray(array);

        // Finds and returns min
        byte min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        return min;
    }

    /**
     * 返回数组中最小的
     * 从3.4版本后 修改方法签名  min(double[]) to min(double...)
     */
    public static double min(final double... array) {
        // Validates input
        validateArray(array);

        // Finds and returns min
        double min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (Double.isNaN(array[i])) {
                return Double.NaN;
            }
            if (array[i] < min) {
                min = array[i];
            }
        }

        return min;
    }

    /**
     * 返回数组中最小的
     * 从3.4版本后 修改方法签名  min(float[]) to min(float...)
     */
    public static float min(final float... array) {
        // Validates input
        validateArray(array);

        // Finds and returns min
        float min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (Float.isNaN(array[i])) {
                return Float.NaN;
            }
            if (array[i] < min) {
                min = array[i];
            }
        }

        return min;
    }

    // Max in array
    //--------------------------------------------------------------------
    /**
     * 返回数组中最大的
     * 从3.4版本后 修改方法签名  min(long[]) to min(long...)
     */
    public static long max(final long... array) {
        // Validates input
        validateArray(array);

        // Finds and returns max
        long max = array[0];
        for (int j = 1; j < array.length; j++) {
            if (array[j] > max) {
                max = array[j];
            }
        }

        return max;
    }

    /**
     * 返回数组中最大的
     * 从3.4版本后 修改方法签名  min(int[]) to min(int...)
     */
    public static int max(final int... array) {
        // Validates input
        validateArray(array);

        // Finds and returns max
        int max = array[0];
        for (int j = 1; j < array.length; j++) {
            if (array[j] > max) {
                max = array[j];
            }
        }

        return max;
    }

    /**
     * 返回数组中最大的
     * 从3.4版本后 修改方法签名  min(short[]) to min(short...)
     */
    public static short max(final short... array) {
        // Validates input
        validateArray(array);

        // Finds and returns max
        short max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        return max;
    }

    /**
     * 返回数组中最大的
     * 从3.4版本后 修改方法签名  min(byte[]) to min(byte...)
     */
    public static byte max(final byte... array) {
        // Validates input
        validateArray(array);

        // Finds and returns max
        byte max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        return max;
    }

    /**
     * 返回数组中最大的
     * 从3.4版本后 修改方法签名  min(double[]) to min(double...)
     */
    public static double max(final double... array) {
        // Validates input
        validateArray(array);

        // Finds and returns max
        double max = array[0];
        for (int j = 1; j < array.length; j++) {
            if (Double.isNaN(array[j])) {
                return Double.NaN;
            }
            if (array[j] > max) {
                max = array[j];
            }
        }

        return max;
    }

    /**
     * 返回数组中最大的
     * 从3.4版本后 修改方法签名  min(float[]) to min(float...)
     */
    public static float max(final float... array) {
        // Validates input
        validateArray(array);

        // Finds and returns max
        float max = array[0];
        for (int j = 1; j < array.length; j++) {
            if (Float.isNaN(array[j])) {
                return Float.NaN;
            }
            if (array[j] > max) {
                max = array[j];
            }
        }

        return max;
    }

    /**
     * 校验参数数组是不是nulll或empty
     */
    private static void validateArray(final Object array) {
//        Validate.isTrue(array != null, "The Array must not be null");
//        Validate.isTrue(Array.getLength(array) != 0, "Array cannot be empty.");
    }

    // 3 param min
    //-----------------------------------------------------------------------
    /**
     * 获取三个long类型参数中最小的
     */
    public static long min(long a, final long b, final long c) {
        if (b < a) {
            a = b;
        }
        if (c < a) {
            a = c;
        }
        return a;
    }

    /**
     * 获取三个int类型参数中最小的
     */
    public static int min(int a, final int b, final int c) {
        if (b < a) {
            a = b;
        }
        if (c < a) {
            a = c;
        }
        return a;
    }


    /**
     * 获取三个short类型参数中最小的
     */
    public static short min(short a, final short b, final short c) {
        if (b < a) {
            a = b;
        }
        if (c < a) {
            a = c;
        }
        return a;
    }

    /**
     * 获取三个byte类型参数中最小的
     */
    public static byte min(byte a, final byte b, final byte c) {
        if (b < a) {
            a = b;
        }
        if (c < a) {
            a = c;
        }
        return a;
    }

    /**
     * 获取三个double类型参数中最小的
     */
    public static double min(final double a, final double b, final double c) {
        return Math.min(Math.min(a, b), c);
    }

    /**
     * 获取三个float类型参数中最小的
     * 如果有一个值是NaN，则NaN将返回，当做无尽处理
     */
    public static float min(final float a, final float b, final float c) {
        return Math.min(Math.min(a, b), c);
    }

    // 3 param max
    //-----------------------------------------------------------------------
    /**
     * 获取三个long类型参数中最大的
     */
    public static long max(long a, final long b, final long c) {
        if (b > a) {
            a = b;
        }
        if (c > a) {
            a = c;
        }
        return a;
    }

    /**
     * 获取三个int类型参数中最大的
     */
    public static int max(int a, final int b, final int c) {
        if (b > a) {
            a = b;
        }
        if (c > a) {
            a = c;
        }
        return a;
    }

    /**
     * 获取三个short类型参数中最大的
     */
    public static short max(short a, final short b, final short c) {
        if (b > a) {
            a = b;
        }
        if (c > a) {
            a = c;
        }
        return a;
    }

    /**
     * 获取三个byte类型参数中最大的
     */
    public static byte max(byte a, final byte b, final byte c) {
        if (b > a) {
            a = b;
        }
        if (c > a) {
            a = c;
        }
        return a;
    }

    /**
     * 获取三个double类型参数中最大的
     */
    public static double max(final double a, final double b, final double c) {
        return Math.max(Math.max(a, b), c);
    }

    /**
     * 获取三个float类型参数中最大的
     */
    public static float max(final float a, final float b, final float c) {
        return Math.max(Math.max(a, b), c);
    }






    /**
     *
     *比例两个int的大小，在Java7有提供相同的方法
     * @since 3.4
     */
    public static int compare(final int x, final int y) {
        if (x == y) {
            return 0;
        }
        return x < y ? -1 : 1;
    }

    /**
     * 比例两个long的大小，在Java7有提供相同的方法
     * @since 3.4
     */
    public static int compare(final long x, final long y) {
        if (x == y) {
            return 0;
        }
        return x < y ? -1 : 1;
    }

    /**
     * 比例两个long的大小，在Java7有提供相同的方法
     * @since 3.4
     */
    public static int compare(final short x, final short y) {
        if (x == y) {
            return 0;
        }
        return x < y ? -1 : 1;
    }

    /**
     * 比例两个byte的大小，在Java7有提供相同的方法
     */
    public static int compare(final byte x, final byte y) {
        return x - y;
    }










    /**
     *转换str为int,如果转换失败则返回0
     *
     */
    public static int toInt(final String str) {
        return toInt(str, 0);
    }

    /**
     *转换str为int，如果转换失败则返回defaultValue
     *
     */
    public static int toInt(final String str, final int defaultValue) {
        if(str == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     *转换str为long，如果转换失败则返回0L
     *
     */
    public static long toLong(final String str) {
        return toLong(str, 0L);
    }

    /**
     *转换str为long，如果转换失败则返回defaultValue
     *
     */
    public static long toLong(final String str, final long defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Long.parseLong(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     *转换str为float，如果转换失败则返回0.0f
     *
     */
    public static float toFloat(final String str) {
        return toFloat(str, 0.0f);
    }

    /**
     *转换str为float，如果转换失败则返回defaultValue
     */
    public static float toFloat(final String str, final float defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Float.parseFloat(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     *转换str为double，如果转换失败则返回0.0d
     */
    public static double toDouble(final String str) {
        return toDouble(str, 0.0d);
    }

    /**
     *转换str为double，如果转换失败则返回defaultValue
     */
    public static double toDouble(final String str, final double defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     *转换str为byte，如果转换失败则返回0
     */
    public static byte toByte(final String str) {
        return toByte(str, (byte) 0);
    }

    /**
     *转换str为byte，如果转换失败则返回defaultValue
     */
    public static byte toByte(final String str, final byte defaultValue) {
        if(str == null) {
            return defaultValue;
        }
        try {
            return Byte.parseByte(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     *转换str为short，如果转换失败则返回0
     */
    public static short toShort(final String str) {
        return toShort(str, (short) 0);
    }

    /**
     *转换str为short，如果转换失败则返回defaultValue
     */
    public static short toShort(final String str, final short defaultValue) {
        if(str == null) {
            return defaultValue;
        }
        try {
            return Short.parseShort(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }



    /**
     * createNumber()方法使用
     * 获取尾数的，也就是返回不包括符号位的数字
     */
    private static String getMantissa(final String str) {
        return getMantissa(str, str.length());
    }

    /**
     * createNumber()方法使用
     * 获取尾数的，也就是返回不包括符号位的数字可以指定获取长度
     */
    private static String getMantissa(final String str, final int stopPos) {
        final char firstChar = str.charAt(0);
        final boolean hasSign = firstChar == '-' || firstChar == '+';

        return hasSign ? str.substring(1, stopPos) : str.substring(0, stopPos);
    }

    /**
     * createNumber()方法使用
     * 如果字符串为空返回true，如果字符串中的没有等于0的返回true
     */
    private static boolean isAllZeros(final String str) {
        if (str == null) {
            return true;
        }
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) != '0') {
                return false;
            }
        }
        return str.length() > 0;
    }

    //-----------------------------------------------------------------------
    /**
     * 转换String为Float
     * 如果String为空则返回null
     */
    public static Float createFloat(final String str) {
        if (str == null) {
            return null;
        }
        return Float.valueOf(str);
    }
    /**
     * 转换String为Double
     * 如果String为空则返回null
     */
    public static Double createDouble(final String str) {
        if (str == null) {
            return null;
        }
        return Double.valueOf(str);
    }

    /**
     * 转换String为Integer，处理hex和octal进制。
     * 注意0开头表示octal进制，不会进行trim操作
     */
    public static Integer createInteger(final String str) {
        if (str == null) {
            return null;
        }
        // decode() handles 0xAABD and 0777 (hex and octal) as well.
        return Integer.decode(str);
    }


    /**
     * 转换String为Long，处理hex和octal进制。
     * 注意0开头表示octal进制，不会进行trim操作
     */
    public static Long createLong(final String str) {
        if (str == null) {
            return null;
        }
        return Long.decode(str);
    }

    /**
     * 转换String为BigInteger
     * 从3.2版本后可以处理hex(0x or #开头)和octal (0开头)进制
     * 字符串为null返回null
     */
    public static BigInteger createBigInteger(final String str) {
        if (str == null) {
            return null;
        }
        int pos = 0; // offset within string
        int radix = 10;
        boolean negate = false; // need to negate later?
        if (str.startsWith("-")) {
            negate = true;
            pos = 1;
        }
        if (str.startsWith("0x", pos) || str.startsWith("0X", pos)) { // hex
            radix = 16;
            pos += 2;
        } else if (str.startsWith("#", pos)) { // alternative hex (allowed by Long/Integer)
            radix = 16;
            pos ++;
        } else if (str.startsWith("0", pos) && str.length() > pos + 1) { // octal; so long as there are additional digits
            radix = 8;
            pos ++;
        } // default is to treat as decimal

        final BigInteger value = new BigInteger(str.substring(pos), radix);
        return negate ? value.negate() : value;
    }

    /**
     * 转换String为Double
     * 如果String为空则返回null
     */
    public static BigDecimal createBigDecimal(final String str) {
        if (str == null) {
            return null;
        }
        // handle JDK1.3.1 bug where "" throws IndexOutOfBoundsException
//        if (StringUtils.isBlank(str)) {
            if (TextUtils.isEmpty(str)) {
            throw new NumberFormatException("A blank string is not a valid number");
        }
        if (str.trim().startsWith("--")) {
            // this is protection for poorness in java.lang.BigDecimal.
            // it accepts this as a legal value, but it does not appear
            // to be in specification of class. OS X Java parses it to
            // a wrong value.
            throw new NumberFormatException(str + " is not a valid number.");
        }
        return new BigDecimal(str);
    }



    //-----------------------------------------------------------------------
//    /**
//     * 判断字符串仅仅包含数字字符
//     * null或empty返回false
//     */
//    public static boolean isDigits(final String str) {
//        return StringUtils.isNumericnu(str);
//    }

    /**
     * 过时的方法被isCreatable取代
     */
    @Deprecated
    public static boolean isNumber(final String str) {
        return isCreatable(str);
    }

    /**
     * 校验String是否是一个有效的Java number
     * 校验number包含hexadecimal 的标记 0x or 0X、八进制数字、科学计数法和其他的数字标记类型，比如1234L
     * 以0开头的非十六进制的数统一作为八进制的数处理。所以String为09将返回false，因为9不是一个有效的八进制。但是是以0.开头的数字，都作为十进制处理
     * null或empty或blank字符串返回false
     */
    public static boolean isCreatable(final String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        final char[] chars = str.toCharArray();
        int sz = chars.length;
        boolean hasExp = false;
        boolean hasDecPoint = false;
        boolean allowSigns = false;
        boolean foundDigit = false;
        // deal with any possible sign up front
        final int start = chars[0] == '-' || chars[0] == '+' ? 1 : 0;
        final boolean hasLeadingPlusSign = start == 1 && chars[0] == '+';
        if (sz > start + 1 && chars[start] == '0') { // leading 0
            if (chars[start + 1] == 'x' || chars[start + 1] == 'X') { // leading 0x/0X
                int i = start + 2;
                if (i == sz) {
                    return false; // str == "0x"
                }
                // checking hex (it can't be anything else)
                for (; i < chars.length; i++) {
                    if ((chars[i] < '0' || chars[i] > '9')
                            && (chars[i] < 'a' || chars[i] > 'f')
                            && (chars[i] < 'A' || chars[i] > 'F')) {
                        return false;
                    }
                }
                return true;
            } else if (Character.isDigit(chars[start + 1])) {
                // leading 0, but not hex, must be octal
                int i = start + 1;
                for (; i < chars.length; i++) {
                    if (chars[i] < '0' || chars[i] > '7') {
                        return false;
                    }
                }
                return true;
            }
        }
        sz--; // don't want to loop to the last char, check it afterwords
        // for type qualifiers
        int i = start;
        // loop to the next to last char or to the last char if we need another digit to
        // make a valid number (e.g. chars[0..5] = "1234E")
        while (i < sz || i < sz + 1 && allowSigns && !foundDigit) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                foundDigit = true;
                allowSigns = false;

            } else if (chars[i] == '.') {
                if (hasDecPoint || hasExp) {
                    // two decimal points or dec in exponent
                    return false;
                }
                hasDecPoint = true;
            } else if (chars[i] == 'e' || chars[i] == 'E') {
                // we've already taken care of hex.
                if (hasExp) {
                    // two E's
                    return false;
                }
                if (!foundDigit) {
                    return false;
                }
                hasExp = true;
                allowSigns = true;
            } else if (chars[i] == '+' || chars[i] == '-') {
                if (!allowSigns) {
                    return false;
                }
                allowSigns = false;
                foundDigit = false; // we need a digit after the E
            } else {
                return false;
            }
            i++;
        }
        if (i < chars.length) {
            if (chars[i] >= '0' && chars[i] <= '9') {
//                if (SystemUtils.IS_JAVA_1_6 && hasLeadingPlusSign && !hasDecPoint) {
//                    return false;
//                }
                // no type qualifier, OK
                return true;
            }
            if (chars[i] == 'e' || chars[i] == 'E') {
                // can't have an E at the last byte
                return false;
            }
            if (chars[i] == '.') {
                if (hasDecPoint || hasExp) {
                    // two decimal points or dec in exponent
                    return false;
                }
                // single trailing decimal point after non-exponent is ok
                return foundDigit;
            }
            if (!allowSigns
                    && (chars[i] == 'd'
                    || chars[i] == 'D'
                    || chars[i] == 'f'
                    || chars[i] == 'F')) {
                return foundDigit;
            }
            if (chars[i] == 'l'
                    || chars[i] == 'L') {
                // not allowing L with an exponent or decimal point
                return foundDigit && !hasExp && !hasDecPoint;
            }
            // last character is illegal
            return false;
        }
        // allowSigns is true iff the val ends in 'E'
        // found digit it to make sure weird stuff like '.' and '1E-' doesn't pass
        return !allowSigns && foundDigit;
    }

    /**
     * 校验提供的字符串是否可以解析为number
     * 可解析的number包括下面方法可以执行字符串 Integer.parseInt(String), Long.parseLong(String), Float.parseFloat(String) or Double.parseDouble(String).
     * 这个方法可以替代java.text.ParseException异常
     * 十六进制和科学计数符号认为是不可解析的
     * @since 3.4
     */
    public static boolean isParsable(final String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.charAt(str.length() - 1) == '.') {
            return false;
        }
        if (str.charAt(0) == '-') {
            if (str.length() == 1) {
                return false;
            }
            return withDecimalsParsing(str, 1);
        }
        return withDecimalsParsing(str, 0);
    }

    private static boolean withDecimalsParsing(final String str, final int beginIdx) {
        int decimalPoints = 0;
        for (int i = beginIdx; i < str.length(); i++) {
            final boolean isDecimalPoint = str.charAt(i) == '.';
            if (isDecimalPoint) {
                decimalPoints++;
            }
            if (decimalPoints > 1) {
                return false;
            }
            if (!isDecimalPoint && !Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }









    /**
     * 将一个String的值转换为java.lang.Number
     * 如果这个字符串以0x、0X、-0x、-0X、#、-#开始，这个字符将转换成 hexadecimal Integer类型，如果前缀后面的数字超过8个则转换成Long类型
     * 如果超过16个则转换成BIgInteger。
     * 还会检查这个值是否是以预定义的字符结束，例如'f','F','d','D','l','L'.如果找到对应的，将开始依次创建较大的类型直到找到能匹配这个值的类型。
     * 如果没有找到类型标识符，将作为小数进行验证并且是依次从Intger到BigInteger，Float 到BigDecimal。
     * 0开头的数字将作为八进制转换，返回的数字是相应的Integer，Long 或BigDecimal
     * 如果参数为空则返回null
     * 这个方法不会对参数进行trim操作，如果头部或尾部有空格的话将会生成NumberFormatExceptions
     *
     */
    public static Number createNumber(final String str) throws NumberFormatException {
        if (str == null) {
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            throw new NumberFormatException("A blank string is not a valid number");
        }
        // Need to deal with all possible hex prefixes here
        final String[] hex_prefixes = {"0x", "0X", "-0x", "-0X", "#", "-#"};
        int pfxLen = 0;
        for(final String pfx : hex_prefixes) {
            if (str.startsWith(pfx)) {
                pfxLen += pfx.length();
                break;
            }
        }
        if (pfxLen > 0) { // 是十六进制 we have a hex number
            char firstSigDigit = 0; // 去掉开头的0 strip leading zeroes
            for(int i = pfxLen; i < str.length(); i++) {
                firstSigDigit = str.charAt(i);
                if (firstSigDigit == '0') { // 统计有多少个0开头 count leading zeroes
                    pfxLen++;
                } else {
                    break;
                }
            }
            final int hexDigits = str.length() - pfxLen; //计算不包含0的数字的长度
            if (hexDigits > 16 || hexDigits == 16 && firstSigDigit > '7') { // 使用BigInteger
                return createBigInteger(str);
            }
            if (hexDigits > 8 || hexDigits == 8 && firstSigDigit > '7') { // 使用Long
                return createLong(str);
            }
            return createInteger(str);
        }
        final char lastChar = str.charAt(str.length() - 1); //获取最后一个数字
        String mant;
        String dec;
        String exp;
        final int decPos = str.indexOf('.');
        final int expPos = str.indexOf('e') + str.indexOf('E') + 1; // assumes both not present
        // if both e and E are present, this is caught by the checks on expPos (which prevent IOOBE)
        // and the parsing which will detect if e or E appear in a number due to using the wrong offset

        if (decPos > -1) { // 这个是一个小数数字 there is a decimal point
            if (expPos > -1) { // 这是一个指数 there is an exponent
                if (expPos < decPos || expPos > str.length()) { // 防止双指数导致IOOBE prevents double exponent causing IOOBE
                    throw new NumberFormatException(str + " is not a valid number.");
                }
                dec = str.substring(decPos + 1, expPos);
            } else {
                dec = str.substring(decPos + 1);
            }
            mant = getMantissa(str, decPos);
        } else {
            if (expPos > -1) {
                if (expPos > str.length()) { // 防止双指数导致IOOBE prevents double exponent causing IOOBE
                    throw new NumberFormatException(str + " is not a valid number.");
                }
                mant = getMantissa(str, expPos);
            } else {
                mant = getMantissa(str);
            }
            dec = null;
        }
        if (!Character.isDigit(lastChar) && lastChar != '.') {
            if (expPos > -1 && expPos < str.length() - 1) {
                exp = str.substring(expPos + 1, str.length() - 1);
            } else {
                exp = null;
            }
            //Requesting a specific type.. 是否是特定的类型
            final String numeric = str.substring(0, str.length() - 1);
            final boolean allZeros = isAllZeros(mant) && isAllZeros(exp);
            switch (lastChar) {
                case 'l' :
                case 'L' :
                    if (dec == null
                            && exp == null
                            && (numeric.charAt(0) == '-'  )) {
//                            && isDigits(numeric.substring(1)) || isDigits(numeric))

                        try {
                            return createLong(numeric);
                        } catch (final NumberFormatException nfe) { // NOPMD
                            // Too big for a long
                        }
                        return createBigInteger(numeric);

                    }
                    throw new NumberFormatException(str + " is not a valid number.");
                case 'f' :
                case 'F' :
                    try {
                        final Float f = NumberUtils.createFloat(str);
                        if (!(f.isInfinite() || f.floatValue() == 0.0F && !allZeros)) {
                            //If it's too big for a float or the float value = 0 and the string
                            //has non-zeros in it, then float does not have the precision we want
                            return f;
                        }

                    } catch (final NumberFormatException nfe) { // NOPMD
                        // ignore the bad number
                    }
                    //$FALL-THROUGH$
                case 'd' :
                case 'D' :
                    try {
                        final Double d = NumberUtils.createDouble(str);
                        if (!(d.isInfinite() || d.floatValue() == 0.0D && !allZeros)) {
                            return d;
                        }
                    } catch (final NumberFormatException nfe) { // NOPMD
                        // ignore the bad number
                    }
                    try {
                        return createBigDecimal(numeric);
                    } catch (final NumberFormatException e) { // NOPMD
                        // ignore the bad number
                    }
                    //$FALL-THROUGH$
                default :
                    throw new NumberFormatException(str + " is not a valid number.");

            }
        }
        //User doesn't have a preference on the return type, so let's start
        //small and go from there...
        //不属于任何类型
        if (expPos > -1 && expPos < str.length() - 1) {
            exp = str.substring(expPos + 1, str.length());
        } else {
            exp = null;
        }
        if (dec == null && exp == null) { // no decimal point and no exponent
            //Must be an Integer, Long, Biginteger
            try {
                return createInteger(str);
            } catch (final NumberFormatException nfe) { // NOPMD
                // ignore the bad number
            }
            try {
                return createLong(str);
            } catch (final NumberFormatException nfe) { // NOPMD
                // ignore the bad number
            }
            return createBigInteger(str);
        }

        //Must be a Float, Double, BigDecimal
        //也许是Float，Double，BigDecimal
        final boolean allZeros = isAllZeros(mant) && isAllZeros(exp);
        try {
            final Float f = createFloat(str);
            final Double d = createDouble(str);
            if (!f.isInfinite()
                    && !(f.floatValue() == 0.0F && !allZeros)
                    && f.toString().equals(d.toString())) {
                return f;
            }
            if (!d.isInfinite() && !(d.doubleValue() == 0.0D && !allZeros)) {
                final BigDecimal b = createBigDecimal(str);
                if (b.compareTo(BigDecimal.valueOf(d.doubleValue())) == 0) {
                    return d;
                }
                return b;
            }
        } catch (final NumberFormatException nfe) { // NOPMD
            // ignore the bad number
        }
        return createBigDecimal(str);
    }
}
