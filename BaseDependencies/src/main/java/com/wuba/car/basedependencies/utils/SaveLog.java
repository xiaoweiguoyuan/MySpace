package com.wuba.car.basedependencies.utils;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SaveLog {
    private static final String TAG = "QiShuiEr";
    private static final Boolean LOG_SWITCH = BuildConfig.DEBUG; // 日志文件总开关
    private static String LOG_NAME = ".txt";// 本类输出的日志文件名称
    private static SimpleDateFormat logfile = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);// 日志文件格式
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);// 日志文件格式
    private static Context mContext;

    public static void setContext(Context context) {
        if (context == null) {
            return;
        }
        mContext = context.getApplicationContext();
    }

    public static void w(Object msg) { // 警告信息
        log(TAG, msg.toString(), 'w');
    }

    public static void e(Object msg) { // 错误信息
        log(TAG, msg.toString(), 'e');
    }

    public static void d(Object msg) {// 调试信息
        log(TAG, msg.toString(), 'd');
    }

    public static void i(Object msg) {//
        log(TAG, msg.toString(), 'i');
    }

    public static void v(Object msg) {
        log(TAG, msg.toString(), 'v');
    }

    public static void w(String text) {
        log(TAG, text, 'w');
    }

    public static void e(String text) {
        log(TAG, text, 'e');
    }

    public static void d(String text) {
        log(TAG, text, 'd');
    }

    public static void i(String text) {
        log(TAG, text, 'i');
    }

    public static void v(String text) {
        log(TAG, text, 'v');
    }

    public static void w(String tag, Object msg) { // 警告信息
        log(tag, msg.toString(), 'w');
    }

    public static void e(String tag, Object msg) { // 错误信息
        log(tag, msg.toString(), 'e');
    }

    public static void d(String tag, Object msg) {// 调试信息
        log(tag, msg.toString(), 'd');
    }

    public static void i(String tag, Object msg) {//
        log(tag, msg.toString(), 'i');
    }

    public static void v(String tag, Object msg) {
        log(tag, msg.toString(), 'v');
    }

    public static void w(String tag, String text) {
        log(tag, text, 'w');
    }

    public static void e(String tag, String text) {
        log(tag, text, 'e');
    }

    public static void d(String tag, String text) {
        log(tag, text, 'd');
    }

    public static void i(String tag, String text) {
        log(tag, text, 'i');
    }

    public static void v(String tag, String text) {
        log(tag, text, 'v');
    }

    /**
     * 根据tag, msg和等级，输出日志
     *
     * @param tag
     * @param msg
     * @param level
     */
    private static void log(String tag, String msg, char level) {
        if (LOG_SWITCH) {//日志文件总开关
            if ('e' == level) { // 输出错误信息
                Log.e(tag, msg);
            } else if ('w' == level) {
                Log.w(tag, msg);
            } else if ('d' == level) {
                Log.d(tag, msg);
            } else if ('i' == level) {
                Log.i(tag, msg);
            } else {
                Log.v(tag, msg);
            }

            writeLogtoFile(String.valueOf(level), tag, msg);
        }
    }

    /**
     * 打开日志文件并写入日志
     *
     * @param mylogtype
     * @param tag
     * @param text
     */
    private static void writeLogtoFile(String mylogtype, String tag, String text) {// 新建或打开日志文件
        if (mContext == null) {
            return;
        }
        Date nowtime = new Date();
        String needWriteFiel = logfile.format(nowtime);
        String needWriteMessage = "time:" + timeFormat.format(nowtime) + ",level:" + mylogtype + ",tag:" + tag + ",content:" + text;

        String path = mContext.getExternalCacheDir().getAbsolutePath();
        File dirsFile = new File(path);

        boolean exist = dirsFile.exists();
        if (!exist) {
            dirsFile.mkdirs();
        }
        //Log.i("创建文件","创建文件");
        File file = new File(dirsFile.toString(), needWriteFiel + LOG_NAME);// MYLOG_PATH_SDCARD_DIR
        if (!file.exists()) {
            try {
                deleteAllFiles(dirsFile);
                //在指定的文件夹中创建文件
                file.createNewFile();
                Log.d(SaveLog.class.getSimpleName(), "创建日志了");
                d(SaveLog.class.getSimpleName(), "版本号:V" + BuildConfig.VERSION_NAME + ",手机型号:" + android.os.Build.MODEL + ",系统版本:" + Build.VERSION.SDK_INT + ",品牌:" + android.os.Build.BRAND);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        try {
            FileWriter filerWriter = new FileWriter(file, true);// 后面这个参数代表是不是要接上文件中原来的数据，不进行覆盖
            BufferedWriter bufWriter = new BufferedWriter(filerWriter);
            bufWriter.write(needWriteMessage);
            bufWriter.newLine();
            bufWriter.close();
            filerWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到现在时间前的几天日期，用来得到需要删除的日志文件名
     */
    private static Date getDateBefore() {
        Date nowtime = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(nowtime);
        now.set(Calendar.DATE, now.get(Calendar.DATE));
        return now.getTime();
    }


    public static String getTodayFilePath() {
        String path = mContext.getExternalCacheDir().getAbsolutePath();
        Date nowtime = new Date();
        String needWriteFiel = logfile.format(nowtime);
        File file = new File(path, needWriteFiel + LOG_NAME);

        SaveLog.d("SaveLog", "今天日志路径:" + file.getAbsolutePath());
        if (file.exists() && file.length() > 0) {
            return file.getAbsolutePath();
        } else {
            return null;
        }
    }


    private static void deleteAllFiles(File root) {
        File files[] = root.listFiles();
        if (files != null)
            for (File f : files) {
                if (f.isDirectory()) { // 判断是否为文件夹
                    deleteAllFiles(f);
                    try {
                        f.delete();
                    } catch (Exception e) {
                    }
                } else {
                    if (f.exists()) { // 判断是否存在
                        deleteAllFiles(f);
                        try {
                            f.delete();
                        } catch (Exception e) {
                        }
                    }
                }
            }
    }
}
