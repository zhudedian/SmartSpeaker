package com.lingan.edongspeechlibrary.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by dyx on 2017/12/26.
 * 临时处理类
 */

public class FileUtil {

    // 保存音频文件
    public static File getWakeupFile( ) {
        File file = null;
        String filePath = null;
        boolean hasSDCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);

        if (hasSDCard) { // SD卡根目录
            filePath = Environment.getExternalStorageDirectory().toString() + File.separator + "wakeup.pcm";
        } else {  // 系统下载缓存根目录
            filePath = Environment.getDownloadCacheDirectory().toString() + File.separator + "wakeup.pcm";
        }

        try {
            file = new File(filePath);
            if (!file.exists()) {
                File dir = new File(file.getParent());
                dir.mkdirs();
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }


    /**
     * 保存 json 到文件
     * @param str json 字符串
     */
    public static void saveFile(String str) {
        String filePath = null;
        boolean hasSDCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (hasSDCard) { // SD卡根目录
            filePath = Environment.getExternalStorageDirectory().toString() + File.separator + System.currentTimeMillis() +".txt";
        } else  // 系统下载缓存根目录
            filePath = Environment.getDownloadCacheDirectory().toString() + File.separator + System.currentTimeMillis() +".txt";

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                File dir = new File(file.getParent());
                dir.mkdirs();
                file.createNewFile();
            }
            FileOutputStream outStream = new FileOutputStream(file);
            outStream.write(str.getBytes());
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
