package com.guozhiyang.sorting;

import java.util.Arrays;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/4/16 11:18
 * @Version 1.0
 */
public class MyTestSort {
    /**
     * 顺序排序
     * @param args
     */
    public static void main1(String[] args) {
        int[] in={5,2,7,9,10,3,4,2};
        for (int i=0;i<in.length;i++){
            for(int j=i+1;j<in.length;j++){
                if(in[i]>in[j]){
                    int t=in[j];
                    in[j]=in[i];
                    in[i]=t;
                }
            }
        }
        Arrays.stream(in).forEach(s->{
            System.out.println(s);
        });
    }

    /**
     * 冒泡
     * @param args
     */
    public static void main2(String[] args) {
        int[] in={5,2,7,9,10,3,4,2};
        for(int i=1;i<in.length;i++){
            for(int j=0;j<in.length-i;j++){
                if(in[j]>in[j+1]){
                    int t=in[j+1];
                    in[j+1]=in[j];
                    in[j]=t;
                }
            }
        }
        Arrays.stream(in).forEach(s->{
            System.out.println(s);
        });
    }

    /**
     * 快速
     * @param args
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int[] in={5,2,7,9,10,3,4,2};
        quickSort(in,0,in.length-1);
        Arrays.stream(in).forEach(s->{System.out.println(s);});
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
    public static void quickSort(int[] yy,int low,int high){
        int i=low;
        int j=high;
        if(i>j){
            return;
        }
        int tmp=yy[low];
        while (i<j){
            while (tmp<=yy[j]&&i<j){
                j--;
            }
            while (tmp>=yy[i]&&i<j){
                i++;
            }
            if(i<j){
                int t=yy[j];
                yy[j]=yy[i];
                yy[i]=j;
            }
        }
        yy[low]=yy[i];
        yy[i]=tmp;
        quickSort(yy,0,i-1);
        quickSort(yy,j+1,high);
    }
}
