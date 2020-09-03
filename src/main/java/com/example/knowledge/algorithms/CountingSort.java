package com.example.knowledge.algorithms;

import java.util.Arrays;

/**
 * 计数排序
 * 适用于一定范围内的整数排序
 * 在取值范围不是很大的情况下，它的性能甚至快过哪些时间复杂度为O(nlogn)的排序
 */
public class CountingSort {

    public static int[] countSort(int[] array){
        //1.得到数列的最大值
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if(array[i]>max){
                max = array[i];
            }
        }
        //2.根据数列的最大值确定统计数组的长度
        int[] countArray = new int[max+1];

        //3.遍历数组，填充统计数组
        for(int i=0;i<array.length;i++){
            countArray[array[i]]++;
        }
        //4.遍历统计数组，输出结果
        int index = 0;
        int[] sortedArray = new int[array.length];
        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                sortedArray[index++] = i;
            }
        }
        return sortedArray;
    }

    public static int[] countSort1(int[] array){
        //1.得到数列的最小值和最大值
        int max = array[0];
        int min = array[0];
        for (int i = 0; i < array.length; i++) {
            if(array[i]>max){
                max = array[i];
            }
            if(array[i]<min){
                min = array[i];
            }
        }

        int d = max-min;
        //2.创建统计数组，并统计对应元素的个数
        int[] countArray = new int[d+1];
        for (int i = 0; i < array.length; i++) {
            countArray[array[i]-min]++;
        }
        System.out.println("2--countArray:"+Arrays.toString(countArray));
        //3.统计数组做变形，后面的元素等于前面的元素之和
        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i-1];
        }
        System.out.println("3--countArray:"+Arrays.toString(countArray));
        //4.倒序遍历原始数列，从统计组中找到正确位置，输出到结果数组
        int[] sortArray = new int[array.length];
        for (int i = array.length-1; i >=0 ; i--) {
            System.out.println("4--countArray:"+Arrays.toString(countArray));
            System.out.println("4--sortArray:"+Arrays.toString(sortArray));
            System.out.println("4--i:"+i);

            sortArray[countArray[array[i]-min]-1] = array[i];
            countArray[array[i]-min]--;

            System.out.println("-------------------------");
        }
        System.out.println("4--countArray:"+Arrays.toString(countArray));
        System.out.println("4--sortArray:"+Arrays.toString(sortArray));
        return sortArray;
    }

    public static void main(String[] args) {
        int[] array = new int[]{4,4,6,5,3,2,8,1,7,5,6,0,10};
        int[] sortArray = countSort1(array);

        System.out.println(Arrays.toString(sortArray));
    }
}
