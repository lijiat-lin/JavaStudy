package com.example.knowledge.algorithms;

import java.util.Arrays;

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
     * 插入的数据从后往前的依次和前面的有序比较，如果插入数据小，则将比较的数据后移
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
    public static void main(String[] args) {
        int[] array = new int[]{2,3,9,5,6,7,8,1,4};
        sort1(array);
        System.out.println(Arrays.toString(array));
    }
}
