package com.github.javautils.math;

import com.github.javautils.string.StringUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 工具类-BigDecimal
 *
 * @author yangwen
 * @version 1.0
 * @date 2016年4月7日 下午6:21:36
 */
public class BigDecimalUtil {

    /**
     * 默认精确的小数位
     */
    private static int DEF_DIV_POINT = 10;

    /**
     * 提供精确的小数位处理，去掉保留位数后的数字
     *
     * @param num   需要处理的数字
     * @param point 小数点后保留几位
     * @return 去掉保留位数后的结果
     */
    public static double decimal(double num, int point) {
        if (point < 0) {
            throw new IllegalArgumentException("The point must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(num));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, point, BigDecimal.ROUND_DOWN).doubleValue();
    }

    /**
     * 提供精确的加法运算
     *
     * @param params 参数数组
     * @return 和
     */
    public static double add(double... params) {
        BigDecimal b1 = new BigDecimal(0);
        for (double param : params) {
            BigDecimal b2 = new BigDecimal(Double.toString(param));
            b1 = b1.add(b2);
        }
        return b1.doubleValue();
    }

    /**
     * 提供精确的减法运算
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算
     *
     * @param params 参数数组
     * @return 动态参数的积
     */
    public static double mul(double... params) {
        BigDecimal b1 = new BigDecimal(1);
        for (double param : params) {
            BigDecimal b2 = new BigDecimal(Double.toString(param));
            b1 = b1.multiply(b2);
        }
        return b1.doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_POINT);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入
     *
     * @param v1    被除数
     * @param v2    除数
     * @param point 表示需要精确到小数点以后几位
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int point) {
        if (point < 0) {
            throw new IllegalArgumentException("The point must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, point, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理
     *
     * @param v     需要四舍五入的数字
     * @param point 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int point) {
        if (point < 0) {
            throw new IllegalArgumentException("The point must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, point, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位(2位)四舍五入处理
     *
     * @param v 需要四舍五入的数字
     * @return 四舍五入后的结果
     */
    public static double round(double v) {
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理
     *
     * @param v     需要四舍五入的数字字符串
     * @param point 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(String v, int point) {
        if (point < 0) {
            throw new IllegalArgumentException("The point must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(v);
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, point, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位(2位)四舍五入处理
     *
     * @param v 需要四舍五入的数字字符串
     * @return 四舍五入后的结果
     */
    public static double round(String v) {
        if (StringUtil.isBlank(v)) {
            return 0;
        }
        BigDecimal b = new BigDecimal(v);
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 金额格式化
     *
     * @param s   金额
     * @param len 小数位数
     * @return 格式后的金额
     */

    public static String insertComma(String s, int len) {
        if (s == null || s.length() < 1) {
            return "";
        }
        NumberFormat formater = null;
        double num = Double.parseDouble(s);
        if (len == 0) {
            formater = new DecimalFormat("###,###");
        } else {
            StringBuffer buff = new StringBuffer();
            buff.append("###,###.");
            for (int i = 0; i < len; i++) {
                buff.append("#");
            }
            formater = new DecimalFormat(buff.toString());
        }
        return formater.format(num);
    }
}
