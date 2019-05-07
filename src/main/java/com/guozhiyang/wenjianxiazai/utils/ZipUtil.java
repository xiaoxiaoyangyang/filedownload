package com.guozhiyang.wenjianxiazai.utils;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

import static org.apache.tools.tar.TarOutputStream.LONGFILE_GNU;

public class ZipUtil {
    private static final Logger logger = LoggerFactory.getLogger(ZipUtil.class);

    private static final Project DEFAULT_PROJECT = new Project();

    public static void zip(String zipFileName, File inputFile) {
        Zip zip = new Zip();
        zip.setProject(DEFAULT_PROJECT);
        zip.setDestFile(new File(zipFileName));

        FileSet fs = new FileSet();
        fs.setProject(DEFAULT_PROJECT);
        fs.setDir(inputFile);
        zip.addFileset(fs);
        zip.execute();
    }

    public static void TarFile(String sSrcFile, String sDstFile) {

        File srcFile = new File(sSrcFile);//要归档的文件对象
        File targetTarFile = new File(sDstFile);//归档后的文件名

        TarOutputStream out = null;

        boolean boo = false;//是否压缩成功

        try {
            out = new TarOutputStream(new BufferedOutputStream(new FileOutputStream(targetTarFile)));
            out.setLongFileMode(LONGFILE_GNU);
            tarNotHaveFolder(srcFile, out, "", false);

            boo = true;

            //归档成功

            //return targetTarFile;

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {

            try {
                if (out != null)
                    out.close();
            } catch (IOException ex) {
                throw new RuntimeException("关闭Tar输出流出现异常", ex);
            } finally {
                //清理操作
                if (!boo && targetTarFile.exists())//归档不成功,
                    targetTarFile.delete();

            }

        }

    }


    /**
     * 归档tar文件
     *
     * @param file 归档的文件对象
     * @param out  输出tar流
     * @param dir  相对父目录名称
     * @param boo  是否把空目录归档进去
     */
    public static void tar(File file, TarOutputStream out, String dir, boolean boo) throws IOException {

        if (file.isDirectory()) {//是目录

            File[] listFile = file.listFiles();//得出目录下所有的文件对象

            if (listFile.length == 0 && boo) {//空目录归档

                out.putNextEntry(new TarEntry(dir + file.getName() + "/"));//将实体放入输出Tar流中

                //System.out.println("归档." + dir + file.getName() + "/");

                return;
            } else {

                for (File cfile : listFile) {

                    tar(cfile, out, dir + file.getName() + "/", boo);//递归归档
                }
            }

        } else if (file.isFile()) {//是文件

            //System.out.println("归档." + dir + file.getName() + "/");

            byte[] bt = new byte[2048 * 2];

            TarEntry ze = new TarEntry(dir + file.getName());//构建tar实体
            //设置压缩前的文件大小
            ze.setSize(file.length());

            //ze.setName(file.getName());//设置实体名称.使用默认名称

            out.putNextEntry(ze);////将实体放入输出Tar流中

            FileInputStream fis = null;

            try {

                fis = new FileInputStream(file);

                int i = 0;

                while ((i = fis.read(bt)) != -1) {//循环读出并写入输出Tar流中

                    out.write(bt, 0, i);
                }

            } catch (IOException ex) {
                throw new IOException("写入归档文件出现异常", ex);
            } finally {

                try {
                    if (fis != null)
                        fis.close();//关闭输入流
                    out.closeEntry();
                } catch (IOException ex) {

                    throw new IOException("关闭输入流出现异常");
                }

            }
        }

    }




    /**
     * 归档tar文件
     *
     * @param file 归档的文件对象
     * @param out  输出tar流
     * @param dir  相对父目录名称
     * @param boo  是否把空目录归档进去
     */
    public static void tarNotHaveFolder(File file, TarOutputStream out, String dir, boolean boo) throws IOException {

        if (file.isDirectory()) {//是目录

            File[] listFile = file.listFiles();//得出目录下所有的文件对象

            if (listFile.length == 0 && boo) {//空目录归档

                out.putNextEntry(new TarEntry(dir + file.getName() + "/"));//将实体放入输出Tar流中

                //System.out.println("归档." + dir + file.getName() + "/");

                return;
            } else {

                for (File cfile : listFile) {

                    tar(cfile, out, "", boo);//递归归档
                }
            }

        } else if (file.isFile()) {//是文件

            //System.out.println("归档." + dir + file.getName() + "/");

            byte[] bt = new byte[2048 * 2];

            TarEntry ze = new TarEntry(dir + file.getName());//构建tar实体
            //设置压缩前的文件大小
            ze.setSize(file.length());

            //ze.setName(file.getName());//设置实体名称.使用默认名称

            out.putNextEntry(ze);////将实体放入输出Tar流中

            FileInputStream fis = null;

            try {

                fis = new FileInputStream(file);

                int i = 0;

                while ((i = fis.read(bt)) != -1) {//循环读出并写入输出Tar流中

                    out.write(bt, 0, i);
                }

            } catch (IOException ex) {
                throw new IOException("写入归档文件出现异常", ex);
            } finally {

                try {
                    if (fis != null)
                        fis.close();//关闭输入流
                    out.closeEntry();
                } catch (IOException ex) {

                    throw new IOException("关闭输入流出现异常");
                }

            }
        }
    }
}

