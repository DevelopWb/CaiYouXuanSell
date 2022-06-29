package com.juntai.project.sell.mall.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2020/4/3 15:58
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/3 15:58
 */
public class CalendarUtil {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

    /**
     * 时间戳转 yyyy-MM-dd HH:mm:ss
     *
     * @param data
     * @return
     */
    public static String getStringData(long data) {
        return sdf.format(data);
    }

    /**
     * 获取今日得开始时间
     *
     * @return
     */
    public static String getTodayStartTime() {
        return getZeroTime(getCurrentTime());
    }

    /**
     * 获取昨天的开始时间
     *
     * @return
     */
    public static String getYesterdayStartTime() {
        return getZeroTime(getYesterday());
    }

    public static String getYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取当前的时间
     *
     * @return
     */
    public static String getCurrentTime() {
        Calendar ca = Calendar.getInstance();
        return sdf.format(ca.getTime());
    }

    /**
     * 获取当前系统时间
     *
     * @param format "yyyy-MM-dd  HH:mm:ss"
     * @return
     */
    public static String getCurrentTime(String format) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        String currentTime = sdf.format(date);
        return currentTime;
    }
    /**
     * 获取当前系统时间
     *
     * @param format "yyyy-MM-dd  HH:mm:ss"
     * @return
     */
    public static String getCurrentTime(String format,Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        String currentTime = sdf.format(date);
        return currentTime;
    }

    /**
     * 获取0点的时间
     *
     * @return yyyy-MM-dd 00:00:00
     * @time yyyy-MM-dd **:**:**
     */
    public static String getZeroTime(String time) {
        String time_return = "";
        SimpleDateFormat sdf_a = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        try {
            Date date = sdf.parse(time);
            time_return = sdf_a.format(date) + " 00:00:00";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time_return;
    }


    //将时间字符串转为时间戳字符串
    public static Long getStringTimestamp(String time) {
        Long longTime = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            longTime = sdf.parse(time).getTime() / 1000;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return longTime;

    }
}
