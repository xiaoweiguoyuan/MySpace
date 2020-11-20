package com.wuba.car.spacenet.http.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static long getDays(String date) {
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
        long today = Calendar.getInstance().getTimeInMillis();
        try {
            long dateMills = yyyyMMdd.parse(date).getTime();

            long result = today - dateMills;
            return result / (1000 * 60 * 60 * 24);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String timestampToString(long time) {
        long tmpTime = time * 1000;
        String tsStr = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = new Date();
            date.setTime(tmpTime);

            tsStr = dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tsStr;
    }

    public static long getYears(String date) {
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
        Calendar today = Calendar.getInstance();
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(yyyyMMdd.parse(date));
            return today.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getPublishTime(long timestamp) {
        long result = System.currentTimeMillis() - timestamp;
        if (result < 0) {
            return "刚刚";
        } else if (result < 1000 * 60) {
            return "刚刚";
        } else if (result < 1000 * 60 * 60) {
            return (result / 1000 / 60 / 60) + "分钟前";
        } else if (result < 1000 * 60 * 60 * 24) {
            return (result / 1000 / 60 / 60 / 24) + "小时前";
        } else {
            return (result / 1000 / 60 / 60 / 24 / 30) + "天前";
        }
    }

}
