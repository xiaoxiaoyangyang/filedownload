package com.guozhiyang.wenjianxiazai.content;


import com.guozhiyang.wenjianxiazai.utils.ZipUtil;

import java.io.*;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class FileTest {
    public static void hh(String[] args) throws Exception{
        File file=new File("D:/faceImage");
        if(!file.exists()){
            file.mkdir();
        }
        byte[] bytes=new byte[1024];
        File[] files={new File("C:/Users/guozhiyang_vendor/Documents/WeChat Files/All Users/1fcc8cef459eb1e75274d50afeb66541.jpg"),new File("C:/Users/guozhiyang_vendor/Documents/WeChat Files/All Users/799e2cdbcbb9afcece0bbc939743a9f1.jpg")};
        for (File fi:files) {
            UUID uuid = UUID.randomUUID();
            OutputStream out=new FileOutputStream(file+"/"+uuid+".jpg");
            InputStream in=new FileInputStream(fi);
            int len;
            while (true){
                int read = in.read(bytes);
                if(read==-1) {
                    break;
                }
                out.write(bytes);
            }
            in.close();
            out.close();
        }
        String path="D:/de.zip";
        ZipUtil.zip(path, file);
    }
    public static void main(String[] args) throws Exception{
        byte[] bytes = new byte[1024];
        //生成Zip文件名为Demo.zip
        String strZipName="D:/Demo.zip";
        FileOutputStream fileOutputStream = new FileOutputStream(strZipName);
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
        //需要同时下载两个文件时
        File[] files={new File("C:/Users/guozhiyang_vendor/Documents/WeChat Files/All Users/gets_imageId=d0ade41dacd445b9951a1e57ac2d4998.jpg"),new File("C:/Users/guozhiyang_vendor/Documents/WeChat Files/All Users/db5ec5c3eefc5bfcc2c059535218a847.jpg")};
        for(int i=0;i<files.length;i++){
            InputStream inputStream = new FileInputStream(files[i]);
            zipOutputStream.putNextEntry(new ZipEntry("D:/faceimage/"+files[i].getName()));
            int len;
            while(true){
                int read = inputStream.read(bytes);
                if(read==-1){
                    break;
                }
                zipOutputStream.write(bytes,0,read);
            }
            zipOutputStream.closeEntry();
            inputStream.close();
        }
        zipOutputStream.close();
        System.out.println("生成Demo.zip成功");
    }
    public static void mm(String[] args){
        String property = System.getProperty("java.io.tmpdir");
        System.out.println(property);
    }
}
