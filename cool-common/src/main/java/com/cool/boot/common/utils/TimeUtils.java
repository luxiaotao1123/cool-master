package com.cool.boot.common.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Time Utils
 * @author  Vincent
 */
public class TimeUtils {

    private static final String second = "秒";
    private static final String minute = "分钟";
    private static final String hour = "小时";
    private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * today time
     *
     * @return the string "yyyy-MM-dd"
     */
    public static String today() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }

    /**
     * current time
     *
     * @return the string "yyyy-MM-dd HH:mm:ss"
     */
    public static String currentTime() {
        return new SimpleDateFormat(TIME_FORMAT).format(new Date());
    }


    /**
     * interval by params and current to second
     *
     * @return the seconds
     */
    public static Integer intervalToSeconds(String date) {
        Integer intervalToMills;
        long start = 0L;
        try {
            start = new SimpleDateFormat(TIME_FORMAT).parse(date).getTime();
        } catch (Exception ignore) {
        }
        intervalToMills = (int) ((new Date().getTime() - start) / 1000);
        return Math.abs(intervalToMills);
    }

    public static Integer intervalToSeconds(Date date){
        int intervalToMills = (int)(new Date().getTime() - date.getTime());
        return Math.abs(intervalToMills/1000);
    }

    public static Integer intervalToSeconds(long timestamp){
        int intervalToMills = (int)(new Date().getTime() - timestamp);
        return Math.abs(intervalToMills/1000);
    }


    /**
     * seconds change minutes
     *
     * @return result minutes
     */
    public static StringBuilder secondsToMinute(Double param, StringBuilder stringBuilder) {
        StringBuilder res;
        if (stringBuilder != null) {
            res = stringBuilder;
        } else {
            res = new StringBuilder();
        }
        if (param < 60) {
            res.append(param.intValue());
            res.append(second);
        } else if (param >= 60 && param < 3600) {
            Double minutes = new BigDecimal(param / 60.0).setScale(0, BigDecimal.ROUND_DOWN).doubleValue();
            res.append(minutes.intValue());
            res.append(minute);
            Double seconds = param % 60;
            if (!seconds.equals(0.0)) {
                res.append(seconds.intValue());
                res.append(second);
            }
        } else {
            Double hours = new BigDecimal(param / 3600.0).setScale(0, BigDecimal.ROUND_DOWN).doubleValue();
            res.append(hours.intValue());
            res.append(hour);
            Double seconds = param % 3600;
            if (!seconds.equals(0.0)) {
                return secondsToMinute(seconds, res);
            }
        }
        return res;
    }

    public static StringBuilder secondsToMinute(Double param) {
        StringBuilder res = new StringBuilder();
        if (param < 60) {
            res.append(param.intValue());
            res.append(second);
        } else if (param >= 60 && param < 3600) {
            Double minutes = new BigDecimal(param / 60.0).setScale(0, BigDecimal.ROUND_DOWN).doubleValue();
            res.append(minutes.intValue());
            res.append(minute);
            Double seconds = param % 60;
            if (!seconds.equals(0.0)) {
                res.append(seconds.intValue());
                res.append(second);
            }
        } else {
            Double hours = new BigDecimal(param / 3600.0).setScale(0, BigDecimal.ROUND_DOWN).doubleValue();
            res.append(hours.intValue());
            res.append(hour);
            Double seconds = param % 3600;
            if (!seconds.equals(0.0)) {
                return secondsToMinute(seconds, res);
            }
        }
        return res;
    }


    /**
     * TimeUtils.currentTime()转换
     */
    public static Date changeStringTimeToDate(String time) {
        //日期格式化
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }




    /**
     * 时间计算
     *
     * @param hour   小时
     * @param minute 分钟
     * @return
     */
    public static String afterTime(String date, Integer hour, Integer minute) {
        //日期格式化
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //计算日期
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(simpleDateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.HOUR, hour);
        calendar.add(Calendar.MINUTE, minute);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String beforeTime(String date, Integer hour, Integer minute) {
        //日期格式化
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //计算日期
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(simpleDateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.HOUR, -hour);
        calendar.add(Calendar.MINUTE, -minute);
        return simpleDateFormat.format(calendar.getTime());
    }


    /**
     * get age by Birthday
     * @param birthday the birthday
     * @return  the age
     */
    public static int getAgeByBirth(Date birthday) {
        int age = 0;
        try {
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());// current

            Calendar birth = Calendar.getInstance();
            birth.setTime(birthday);

            if (birth.after(now)) {
                age = 0;
            } else {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
                if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
                    age += 1;
                }
            }
            return age;
        } catch (Exception e) {
            return 0;
        }
    }




}
