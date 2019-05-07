package com.guozhiyang.wenjianxiazai.zhengshiwenjian;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {
    public static void zipFile(File inputFile,ZipOutputStream zipOutputStream){
        try {
            if (inputFile.exists()) {//判断文件是否存在
                if (inputFile.isFile()) {
                    //创建输入流读取文件
                    FileInputStream fis = new FileInputStream(inputFile);
                    BufferedInputStream bis = new BufferedInputStream(fis);

                    //将文件写入zip内
                    ZipEntry ze = new ZipEntry(inputFile.getName());
                    zipOutputStream.putNextEntry(ze);
                    //写入文件的方法，同上
                    byte[] b = new byte[1024];
                    int len;
                    while ((len = bis.read(b)) > 0) {
                        zipOutputStream.write(b, 0, len);
                    }
                    bis.close();
                    fis.close();
                }
            } else {
                try {
                    File[] files = inputFile.listFiles();
                    for (File fi : files) {
                        zipFile(fi, zipOutputStream);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
