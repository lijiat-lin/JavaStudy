package com.example.knowledge.algorithms.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @program: knowledge
 * @description: 归并排序
 * @author: zhangjialin
 * @create: 2020-09-07 11:35
 */
public class MergeSort {

    public static void sort1(int[] arr,int start,int end){
        if(start>=end){
            return;
        }
        int mid = (start+end)/2;

        //分治递归
        sort1(arr,start,mid);
        sort1(arr,mid+1,end);

        //合并数组
        int i = start;
        int j = mid+1;
        int k = 0;
        int[] temp = new int[end-start+1];
        while (i<=mid && j<=end){
            if(arr[i]<arr[j]){
                temp[k++] = arr[i++];
            }else{
                temp[k++] = arr[j++];
            }
        }
        //判断比较后是否还有剩余
        while (i<=mid){
            temp[k++] = arr[i++];
        }
        while(j<=end){
            temp[k++] = arr[j++];
        }
        //将temp的值赋值给数组
        for (int l = 0; l < end-start+1; l++) {
            arr[start+l] = temp[l];
        }

    }
    public static void main(String[] args) {
//        int[] array = new int[]{2,3,9,5,6,7,8,1,4,10,16,12,14,19,11};
        int[] array = new int[10000];
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            array[i] = random.nextInt(100000)+1;
        }
        System.out.println("排序前-----："+Arrays.toString(array));
        long start = System.currentTimeMillis();
        sort1(array,0,array.length-1);
//        InsertSort.sort2(array);
//        BubbleSort.sort5(array);
//        SelectionSort.sort1(array);
        long end = System.currentTimeMillis();
        System.out.println("归并排序时间："+(end-start));
        System.out.println("排序后-----"+Arrays.toString(array));
    }
}
