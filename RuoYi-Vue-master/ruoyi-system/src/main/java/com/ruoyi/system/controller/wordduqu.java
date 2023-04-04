package com.ruoyi.system.controller;

import java.io.*;//导入java.io包中的所有类
import java.util.Scanner;//导入java.util包中的Scanner类
public class wordduqu {//类名
    public static int Word(String str){

        String[] lines = str.split("\r\n|\r|\n");

        return lines.length;

    }
}
