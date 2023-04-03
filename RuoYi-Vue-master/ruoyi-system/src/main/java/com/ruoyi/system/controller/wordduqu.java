package com.ruoyi.system.controller;


import java.util.Scanner;
public class wordduqu {
    public static int Word(String filestring){
        int n=0;
        String a;
        Scanner sc=new Scanner(System.in);
        filestring=sc.next();
        while((a= sc.next())!=null)
        {
            n++;
        }
        return n;
    }

}
