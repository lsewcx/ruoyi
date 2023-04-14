package com.ruoyi.system.controller;

import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@RestController
public class Zip{
    static int num=0;

    public static String getstr(String filepath) throws IOException {
        StringBuilder str1= new StringBuilder();
        //获取文件输入流
        FileInputStream input = new FileInputStream(filepath);

        //获取ZIP输入流(一定要指定字符集Charset.forName("GBK")否则会报java.lang.IllegalArgumentException: MALFORMED)
        ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(input), Charset.forName("GBK"));

        //定义ZipEntry置为null,避免由于重复调用zipInputStream.getNextEntry造成的不必要的问题
        ZipEntry ze = null;

        //循环遍历
        while ((ze = zipInputStream.getNextEntry()) != null) {


            //读取
            BufferedReader br = new BufferedReader(new InputStreamReader(zipInputStream,Charset.forName("UTF-8")));

            String line;

            //内容不为空，输出
            while ((line = br.readLine()) != null) {
                num+=1;
                str1.append(line);
            }
        }

        //一定记得关闭流
        zipInputStream.closeEntry();
        input.close();
        return str1.toString();
    }

    public static String getstr1(String filepath) throws IOException {
        StringBuilder str1= new StringBuilder();
        //获取文件输入流
        FileInputStream input = new FileInputStream(filepath);

        //获取ZIP输入流(一定要指定字符集Charset.forName("GBK")否则会报java.lang.IllegalArgumentException: MALFORMED)
        ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(input), Charset.forName("GBK"));

        //定义ZipEntry置为null,避免由于重复调用zipInputStream.getNextEntry造成的不必要的问题
        ZipEntry ze = null;

        //循环遍历
        while ((ze = zipInputStream.getNextEntry()) != null) {


            //读取
            BufferedReader br = new BufferedReader(new InputStreamReader(zipInputStream,Charset.forName("GBK")));

            String line;

            //内容不为空，输出
            while ((line = br.readLine()) != null) {
                str1.append(line);
            }
        }

        //一定记得关闭流
        zipInputStream.closeEntry();
        input.close();
        return str1.toString();
    }
    public static int getrow() throws IOException {
        return num;
    }
}