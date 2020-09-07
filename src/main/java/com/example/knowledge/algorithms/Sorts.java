package com.example.knowledge.algorithms;

import java.util.Arrays;

/**
 * @program: knowledge
 * @description: 排序
 * @author: zhangjialin
 * @create: 2020-09-07 16:53
 */
public class Sorts {

    /**
     * 冒泡排序
     * 把相邻的元素两两比较，当一个元素大于右侧相邻元素时，交换他们的位置；
     * @param arr
     */
    public static void bubbleSort(int[] arr){
        boolean isChanged = false;
        for (int i = 0; i < arr.length; i++) {
            isChanged = false;
            for (int j = 0; j < arr.length-1-i; j++) {
                if(arr[j]>arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                    isChanged = true;
                }
            }
            if (!isChanged){
                break;
            }
        }
    }

    /**
     * 插入排序
     * 插入的数据从后往前的依次和前面的有序分区比较，如果插入数据小，则将比较的数据后移
     * @param arr
     */
    public static void insertSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int value = arr[i];
            int index = i-1;
            while(index >=0 && arr[index]>value){
                //移动数据
                arr[index+1] = arr[index];
                index--;
            }
            arr[index+1] = value;
        }
    }

    /**
     * 希尔排序（插入排序的优化）
     * 希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；
     * 随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止
     * @param arr
     */
    public static void shellSort(int[] arr){
        for (int gap = arr.length/2;gap>0;gap /=2){
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                while(j-gap>=0 && arr[j-gap]>arr[j]){
                    int tmp = arr[j];
                    arr[j] = arr[j-gap];
                    arr[j-gap] = tmp;
                    j -= gap;
                }
            }
        }
    }

    /**
     * 选择排序
     * 选择排序算法的实现思路有点类似插入排序，也分已排序区间和未排序区间。
     * 但是选择排序每次会从未排序区间中找到最小的元素，将其放到已排序区间的末尾。
     * @param arr
     */
    public static void selectSort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            //找到最小的元素
            int mark = i;
            for (int j = i; j < arr.length; j++) {
                if(arr[j]<arr[mark]){
                    mark = j;
                }
            }
            //交换
            if(mark!=i){
                int tmp = arr[i];
                arr[i] = arr[mark];
                arr[mark] = tmp;
            }
        }
    }

    /**
     * 归并排序
     * @param arr
     * @param left
     * @param right
     */
    public static void mergeSort(int[] arr, int left, int right) {
        if(left>=right){
            return;
        }
        int q = (right+left)/2;

        mergeSort(arr, left, q);
        mergeSort(arr,q+1,right);
        merge(arr,left,q,right);
    }
    public static void merge(int[] arr,int left,int q,int right){
        int[] tmp = new int[right-left+1];
        int i = left;
        int j = q+1;
        int k = 0;

        while(i<=q && j<=right){
            if(arr[i]<arr[j]){
                tmp[k++] = arr[i++];
            }else{
                tmp[k++] = arr[j++];
            }
        }

        int start = i;
        int end = q;
        if(j<=right){
            start = j;
            end = right;
        }

        while (start<=end){
            tmp[k++] = arr[start++];
        }

        for (int l = 0; l <= right - left; l++) {
            arr[l+left] = tmp[l];
        }
    }
    public static void main(String[] args) {
        int[] array = new int[]{2,3,9,5,6,7,8,1,4,10,16,12,14,19,11};
        mergeSort(array,0,array.length);
        System.out.println(Arrays.toString(array));
    }
}
