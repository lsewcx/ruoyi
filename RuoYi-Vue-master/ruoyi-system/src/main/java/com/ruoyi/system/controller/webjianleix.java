package com.ruoyi.system.controller;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class webjianleix {

    //读取zip文件内的文件,返回文件名称列表
    public static List<Map<String, Object>> readZipFileName(String path){
        int js=0;
        int vue=0;
        int java=0;
        int wuxiao=0;
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            // windows环境下，默认字符集为GBK，ZipFile默认使用UTF-8字符集，当文件名存在中文时，处理时就会报错
            ZipFile zipFile = new ZipFile(path, Charset.forName("GBK"));
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                String a=entry.getName();
                String b= StringUtils.substringAfterLast(a, ".");
                if(b.equals("js")) js++;
                else if(b.equals("vue")) vue++;
                else if(b.equals("java")) java++;
                else wuxiao++;
//                System.out.println(entry.getSize());
                Map<String, Object> map = new HashMap<>();
                map.put("name",entry.getName());
                map.put("size",entry.getSize());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Integer> list1=new ArrayList<>();
        list1.add(js);
        list1.add(vue);
        list1.add(java);
        list1.add(wuxiao);
        return list;
    }
    public static List<Integer> readZipFileName1(String path){
        int js=0;
        int vue=0;
        int java=0;
        int wuxiao=0;
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            // windows环境下，默认字符集为GBK，ZipFile默认使用UTF-8字符集，当文件名存在中文时，处理时就会报错
            ZipFile zipFile = new ZipFile(path, Charset.forName("GBK"));
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                String a=entry.getName();
                String b= StringUtils.substringAfterLast(a, ".");
                if(b.equals("js")) js++;
                else if(b.equals("vue")) vue++;
                else if(b.equals("java")) java++;
                else wuxiao++;
//                System.out.println(entry.getSize());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Integer> list1=new ArrayList<>();
        list1.add(js);
        list1.add(vue);
        list1.add(java);
        list1.add(wuxiao);
        return list1;
    }
}
