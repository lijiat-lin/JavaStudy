package com.example.knowledge.algorithms;

import java.util.Arrays;
import java.util.Random;

/**
 * 插入排序：插入算法的核心思想是取未排序区间中的元素，在已排序区间中找到合适的插入位置将其插入，
 * 并保证已排序区间数据一直有序。重复这个过程，直到未排序区间中元素为空，算法结束。
 * @program: knowledge
 * @description: 插入排序
 * @author: zhangjialin
 * @create: 2020-09-04 16:06
 */
public class InsertSort {


    /**
     * 插入的数据从后往前的依次和前面的有序分区比较，如果插入数据小，则将比较的数据后移
     * @param arr
     */
    public static void sort1(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int value = arr[i];
            int j = i-1;
            for (;j>=0;j--){
                if (value<arr[j]){
                    //数据移动
                    arr[j+1] = arr[j];
                }else{
                    break;
                }
            }
            //插入数据
            arr[j+1] = value;
        }
    }

    /**
     * 希尔排序
     * 希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，每组包含的关键词越来越多，
     * 当增量减至1时，整个文件恰被分成一组，算法便终止
     * @param arr
     */
    public static void sort2(int[] arr){
        for(int gap = arr.length/2;gap>0;gap/=2){
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                while (j-gap>=0 && arr[j]<arr[j-gap]){
                    //插入排序采用交换法
                    swap(arr,j,j-gap);
                    j-=gap;
                }
            }
        }
    }
    private static void swap(int[] arr,int a,int b){
        arr[a] = arr[a]+arr[b];
        arr[b] = arr[a]-arr[b];
        arr[a] = arr[a]-arr[b];
    }
    public static void main(String[] args) {
//        int[] array = new int[]{2,3,9,5,6,7,8,1,4};
        int[] array = new int[1000];
        Random random = new Random(10);
        for (int i = 0; i < 1000; i++) {
            array[i] = random.nextInt(1000);
        }
        sort2(array);
        System.out.println(Arrays.toString(array));
    }
}
