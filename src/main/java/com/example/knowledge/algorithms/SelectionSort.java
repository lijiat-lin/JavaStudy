package com.example.knowledge.algorithms;

import java.util.Arrays;

/**
 * @program: knowledge
 * @description: 选择排序
 * @author: zhangjialin
 * @create: 2020-09-04 16:43
 */
public class SelectionSort {

    public static void sort1(int[] arr){


        for (int i = 0; i < arr.length; i++) {
            int index = i;
            //找到最小值
            for (int j = i; j < arr.length; j++) {
                if(arr[j]>arr[j+1]){
                    index = j+1;
                }
            }
            //交换数据
            if(index!=i){

            }


        }
    }
    public static void main(String[] args) {
        int[] array = new int[]{2,3,9,5,6,7,8,1,4};
        sort1(array);
        System.out.println(Arrays.toString(array));
    }
}
