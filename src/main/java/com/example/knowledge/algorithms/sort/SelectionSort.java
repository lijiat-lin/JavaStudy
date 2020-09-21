package com.example.knowledge.algorithms.sort;

import java.util.Arrays;

/**
 *
 * 选择排序算法的实现思路有点类似插入排序，也分已排序区间和未排序区间。
 * 但是选择排序每次会从未排序区间中找到最小的元素，将其放到已排序区间的末尾。
 * @program: knowledge
 * @description: 选择排序
 * @author: zhangjialin
 * @create: 2020-09-04 16:43
 */
public class SelectionSort {

    public static void sort1(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            int index = i;
            //找到最小值
            for (int j = i+1; j < arr.length; j++) {
                if(arr[index]>arr[j]){
                    index = j;
                }
            }
            //交换数据
            if(index!=i){
                int temp = arr[index];
                arr[index] = arr[i];
                arr[i] = temp;
            }
        }
    }
    public static void main(String[] args) {
        int[] array = new int[]{2,3,9,5,6,7,8,1,4,10,16,12,14,19,11};
        sort1(array);
        System.out.println(Arrays.toString(array));
    }
}
