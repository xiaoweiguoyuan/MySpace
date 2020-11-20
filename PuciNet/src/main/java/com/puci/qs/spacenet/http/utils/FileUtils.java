package com.puci.qs.spacenet.http.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.widget.Toast;

import com.puci.qs.basedependencies.utils.SaveLog;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtils {
    private static final String ROOT_DIR = "bananapark";


    public static File getImageFile(Context context) {
        //图片根目录
        String imagePath = getDir(context, "pic");
        //文件名
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
        //新建文件
        return new File(imagePath + File.separator + "IMG_" + timeStamp + ".jpg");
    }

    public static String getDir(Context context, String string) {
        if (checkSdCard(context)) {
            return getSDDir(string);
        } else {
            return getDataDir(context, string);
        }
    }

    public static boolean checkSdCard(Context context) {
        System.gc();
        if (Environment.getExternalStorageState().equals("removed")) {
            Toast.makeText(context, "SD卡未连接", Toast.LENGTH_LONG).show();
            return false;
        } else if (Environment.getExternalStorageState().equals("shared")) {
            Toast.makeText(context, "没有读取权限", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }


    /**
     * 获取到手机内存的目录
     *
     * @param string
     * @return
     */
    private static String getDataDir(Context context, String string) {
        // data/data/包名/cache
        String path = context.getCacheDir()
                .getAbsolutePath()
                + File.separator + string;
        File file = new File(path);
        if (!file.exists()) {
            if (file.mkdirs()) {
                return file.getAbsolutePath();
            } else {
                return "";
            }
        }
        return file.getAbsolutePath();
    }

    /**
     * 获取到sd卡的目录
     *
     * @param key_dir
     * @return
     */
    private static String getSDDir(String key_dir) {
        StringBuilder sb = new StringBuilder();
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();// /mnt/sdcard
        sb.append(absolutePath)
                .append(File.separator).append("banana")
                .append(File.separator).append(ROOT_DIR)
                .append(File.separator).append(key_dir);

        String filePath = sb.toString();
        File file = new File(filePath);
        if (!file.exists()) {
            if (file.mkdirs()) {
                return file.getAbsolutePath();
            } else {
                return "";
            }
        }

        return file.getAbsolutePath();
    }

    /**
     * 质量压缩到本地
     *
     * @param target_kb 目标大小（单位：kb）
     */
    public static boolean compressImage2File(Bitmap srcBitmap, File outFile, int target_kb) {
        FileOutputStream out;
        try {
            out = new FileOutputStream(outFile.getAbsolutePath());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            srcBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            int options = 100;
            while ((baos.toByteArray().length / 1024) > target_kb) {
                baos.reset();
                options -= 10;
                if (options < 0) {
                    options = 10;
                    srcBitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
                    break;
                }
                srcBitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
            }
            //压缩到本地
            srcBitmap.compress(Bitmap.CompressFormat.JPEG, options, out);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 将图片进行压缩，转化成jpg格式，主要用于图片上传时使用
     * @param srcFilePath
     * @return
     */
    public static String convertToJpg(String srcFilePath) {
        String jpgFilePath = srcFilePath.substring(0, srcFilePath.lastIndexOf(46)) + "_banana_temp." + "jpg";
        File file = new File(jpgFilePath);
        if (file.exists()) {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return compressImgFile(srcFilePath, jpgFilePath);
        } else if (Build.VERSION.SDK_INT >= 27) {
            return compressImgFile(srcFilePath, jpgFilePath);
        } else {
            return null;
        }
    }

    private static String compressImgFile(String srcFilePath, String jpgFilePath) {
        Bitmap bitmap = BitmapFactory.decodeFile(srcFilePath);
        BufferedOutputStream bos = null;

        try {
            bos = new BufferedOutputStream(new FileOutputStream(jpgFilePath));
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos)) {
                bos.flush();
            }
        } catch (IOException var15) {
            SaveLog.d("BImage", "heif图片转换失败");
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException var14) {
                var14.printStackTrace();
            }

            bitmap.recycle();
        }

        return jpgFilePath;
    }
}
