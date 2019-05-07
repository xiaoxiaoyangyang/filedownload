package com.guozhiyang.file;

import java.io.*;

public class InputOutput {
    public static void main1(String[] args) throws Exception {
        //InputStream InputStreamReader可以指定字符集 BufferedInputStream
        FileInputStream fileInputStream = new FileInputStream("a.txt");
        FileOutputStream outputStream = new FileOutputStream("b.txt");
        byte[] bytes = new byte[1024];
        while(true){
            int read = fileInputStream.read(bytes);
            if(read==-1) {
                break;
            }
            outputStream.write(bytes,0,read);
        }
        fileInputStream.close();
        outputStream.close();
    }

    public static void main2(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("a.txt");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
      //  DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);
        FileOutputStream outputStream = new FileOutputStream("b.txt");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        byte[] bytes = new byte[1024];
        while(true){
            int read = bufferedInputStream.read(bytes);
            if (read==-1) {
                break;
            }
            bufferedOutputStream.write(bytes,0,read);
        }
        bufferedInputStream.close();
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }

    //八种基本类型和字符串
    public static void main3(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("a.txt");
        DataInputStream dataInputStream = new DataInputStream(fileInputStream);
        FileOutputStream outputStream = new FileOutputStream("b.txt");
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        byte[] bytes = new byte[1024];
        int len;
        while((len=dataInputStream.read(bytes))!=-1){
            dataOutputStream.write(bytes,0,len);
        }

        dataInputStream.close();
        dataOutputStream.flush();
        dataOutputStream.close();
    }

    public static void main4(String[] args) throws IOException {
        String a="ABCDEFG";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(a.getBytes());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len;
        while((len=byteArrayInputStream.read(bytes))!=-1){
            outputStream.write(bytes,0,len);
        }
        byteArrayInputStream.close();
        outputStream.flush();
        outputStream.close();
    }

    public static byte[] getBytes(File f) {
        try {
            InputStream in = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
            byte[] b = new byte[1024];
            int n;
            while ((n = in.read(b)) != -1){
                out.write(b, 0, n);
            }
            in.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
            System.out.println("***请设置文件路径***");
        }
        return null;
    }

    /**
     * 对象流
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Student xiaoming = new Student("xiaoming", 20);
        Student xiaohong = new Student("xiaohong", 21);
        FileOutputStream outputStream = new FileOutputStream("studet.dat");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    /**
     * 字符流
     * @param args
     * @throws IOException
     */
    public static void main6(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("a.text");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        FileOutputStream outputStream = new FileOutputStream("b.text");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "utf-8");
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        while (true){
            String s = bufferedReader.readLine();
            if(s==null){
                break;
            }
            bufferedWriter.write(s);
        }

    }

    public static void main8(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("a.text");
        FileOutputStream outputStream = new FileOutputStream("b.text");
    }

}
