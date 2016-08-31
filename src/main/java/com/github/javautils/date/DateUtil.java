package com.github.javautils.date;

import com.github.javautils.Constants;
import com.github.javautils.string.StringUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by tomoya.
 * Copyright (c) 2016, All Rights Reserved.
 * http://tomoya.cn
 */
public class DateUtil {

    /**
     * 格式化成日期时间字符串
     *
     * @param date
     * @return
     */
    public static String formatDateTime(Date date) {
        if (date == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.FORMAT_DATETIME);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(date);
    }

    /**
     * 格式化成日期字符串
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        if (date == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.FORMAT_DATE);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(date);
    }

    /**
     * 格式化成自定义类型的日期字符串
     *
     * @param date
     * @param style 自定义类型
     * @return
     */
    public static String formatDateTime(Date date, String style) {
        if (date == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat(style);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(date);
    }

    /**
     * 按照格式化类型将字符串转时间
     *
     * @param dateString
     * @param style
     * @return
     */
    public static Date string2Date(String dateString, String style) {
        if (StringUtil.isBlank(dateString)) return null;
        Date date = new Date();
        SimpleDateFormat strToDate = new SimpleDateFormat(style);
        try {
            date = strToDate.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 判断传入的时间是否在当前时间之后，返回boolean值
     * true: 过期
     * false: 还没过期
     *
     * @param date
     * @return
     */
    public static boolean isExpire(Date date) {
        if (date.before(new Date())) return true;
        return false;
    }

    /**
     * 获取 传入 date 之后 hour 小时的日期
     *
     * @param date
     * @param hour
     * @return
     */
    public static Date getHourAfter(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, hour + 1);
        return calendar.getTime();
    }

    /**
     * 获取 传入 date 之前 hour 小时的日期
     *
     * @param date
     * @param hour
     * @return
     */
    public static Date getHourBefore(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, -(hour - 1));
        return calendar.getTime();
    }

    /**
     * 获取 传入 date 之前 day 天的日期
     *
     * @param date
     * @param day
     * @return
     */
    public static Date getDateBefore(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -day);
        return calendar.getTime();
    }

    /**
     * 获取 传入 date 之后 day 天的日期
     *
     * @param date
     * @param day
     * @return
     */
    public static Date getDateAfter(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    /**
     * 获取 传入 date 之后 minute 分钟的日期
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date getMinuteAfter(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 获取 传入 date 之前 minute 分钟的日期
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date getMinuteBefore(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, -minute);
        return calendar.getTime();
    }

}