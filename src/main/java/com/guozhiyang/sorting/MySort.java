package com.guozhiyang.sorting;

import java.util.Arrays;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/4/16 9:41
 * @Version 1.0
 */
public class MySort {

    /**
     * 顺序排序的方法
     * @param args
     */
    public static void main1(String[] args) {
        int[] in={5,2,7,9,10,3,4,2};
        for(int i=0;i<in.length;i++){
            for(int j=i+1;j<in.length;j++){
                if(in[i]>in[j]){
                    int k=in[j];
                    in[j]=in[i];
                    in[i]=k;
                }
            }
        }
        Arrays.stream(in).forEach(t->System.out.println(t));
    }

    /**
     * 冒泡排序方法
     * @param args
     */
    public static void main2(String[] args) {
        long start = System.currentTimeMillis();
        int[] in={5,2,7,9,10,3,4,2};
        for(int i=1;i<in.length;i++){
            for(int j=0;j<in.length-i;j++){
                if(in[j]>in[j+1]){
                    int k=in[j];
                    in[j]=in[j+1];
                    in[j+1]=k;
                }
            }
        }
        Arrays.stream(in).forEach(t->System.out.println(t));
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    /**
     * 快速排序
     * @param args
     */
    public static void main3(String[] args) {
        long start = System.currentTimeMillis();
        int[] in={5,2,7,9,10,3,4,2};
        quickSort(in,0,in.length-1);
        Arrays.stream(in).forEach(s->{System.out.println(s);});
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    public static void quickSort(int[] tt,int low,int high){
        int i=low;
        int j=high;
        if(i>j){
            return;
        }
        int tmp=tt[i];
        while(i<j){
            //先看右边一次递减
            while (tmp<=tt[j]&&i<j){
                j--;
            }
            while(tmp>=tt[i]&&i<j){
                i++;
            }
            if(i<j){
                int t=tt[i];
                tt[i]=tt[j];
                tt[j]=t;
            }
        }
        tt[low]=tt[i];
        tt[i]=tmp;
        quickSort(tt,low,j-1);
        quickSort(tt,j+1,high);
    }

    /**
     * 桶装排序
     * @param args
     */
    public static void main(String[] args) {
        int[] in={5,2,7,9,10,3,4,2};
        int[] ints = new int[100];
        for(int i=0;i<in.length;i++){
            ints[in[i]]++;
        }
        for(int j=0;j<ints.length;j++){
            while(ints[j]!=0){
                ints[j]--;
                System.out.println(j);
            }
        }
    }
}
