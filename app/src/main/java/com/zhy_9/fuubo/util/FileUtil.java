package com.zhy_9.fuubo.util;

import android.os.Environment;

import org.apache.http.util.EncodingUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ZHY_9 on 2015/5/14.
 */
public class FileUtil {

    public static void writeFileOnSDCard(String path, String fileContent) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        byte[] bytes = fileContent.getBytes();
        fos.write(bytes);
        fos.close();
    }

    public static String readFileFromSDCard(String path) throws IOException {
        String fileContent = "";
        FileInputStream fis = new FileInputStream(path);
        int length = fis.available();
        byte[] bytes = new byte[length];
        fis.read(bytes);
        fileContent = EncodingUtils.getString(bytes, "UTF-8");
        fis.close();
        return fileContent;
    }

    public static String getSDPath(){
        File dir = null;
        if (hasSDCard()) {
            dir = Environment.getExternalStorageDirectory();

        }
        return dir.toString();
    }

    public static boolean hasSDCard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static void writeDataToSD (String fileName, String fileContent) {
        File file = new File(getSDPath(), fileName);
        if (hasSDCard()) {
            try {
                FileOutputStream fos = new FileOutputStream(file);
                byte[] os = fileContent.getBytes();
                fos.write(os);
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
