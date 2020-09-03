package com.example.knowledge.algorithms;

import java.util.Arrays;

/**
 * 冒泡排序
 * 冒泡排序是一种稳定排序，值相等的元素并不会打乱原有的顺序
 * 由于该排序算法需要遍历所有的元素，总共遍历（元素数量-1）轮，所以平均时间复杂度是O(n²)
 * @author zhangjialin
 */
public class BubbleSort {

    /**
     * 双重循环冒泡排序
     * @param array
     */
    public static void sort1(int[] array){
        for(int i= 0;i<array.length-1;i++){
            for (int j = 0; j < array.length-i-1; j++) {
                int temp = 0;
                if(array[j]>array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }

            }
        }
    }

    /**
     * 如果已经是有序的则不用再进行比较排序了
     * @param array
     */
    public static void sort2(int[] array){
        for(int i= 0;i<array.length-1;i++){
            //有序标记 每一轮的初始值都是true
            boolean isSorted = true;
            for (int j = 0; j < array.length-i-1; j++) {
                int temp = 0;
                if(array[j]>array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    //因为有元素交换了，所以不是有序的，标记变为false
                    isSorted = false;
                }
            }
            if(isSorted){
                break;
            }
        }
    }

    /**
     * 冒泡排序：把相邻的元素两两比较，当一个元素大于右侧相邻元素时，交换他们的位置；
     * 当一个元素小于或者等于右侧相邻元素时，位置不变
     * 冒泡排序是稳定排序（相同的元素位置不变）
     * 后面的元素已经是最大的排序好的，就减少比较的次数
     * @param array
     */
    public static void sort3(int[] array){

        //记录最后一次交换的位置
        int lastExchangeIndex = 0;
        //记录无序的边界 每次只需要比较到这里为止
        int sortBorder = array.length-1;
        for(int i= 0;i<array.length-1;i++){
            //有序标记 每一轮的初始值都是true
            boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++) {
                int temp = 0;
                if(array[j]>array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    //因为有元素交换了，所以不是有序的，标记变为false
                    isSorted = false;

                    //更新为最后一次交换元素的位置
                    lastExchangeIndex = j;
                }
            }
            sortBorder = lastExchangeIndex;
            if(isSorted){
                break;
            }
        }
    }

    /**
     * 鸡尾酒排序 第一轮从左到右  第二轮 从右到左  。。。
     * @param array
     */
    public static void sort4(int[] array){
        int temp = 0;
        for(int i=0;i<array.length/2;i++){
            //有序标记，每一轮的初始值都是true
            boolean isSorted = true;
            //奇数轮  从左到右比较和交换
            for (int j = 0; j <array.length-i-1 ; j++) {
                if(array[j]>array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    //因为有元素交换了，所以不是有序的，标记变为false
                    isSorted = false;
                }
            }
            if(isSorted){
                break;
            }
            //在偶数轮之前，将isSorted重新标记为true
            isSorted = true;
            for (int j = array.length-i-1; j >1 ; j--) {
                if(array[j]<array[j-1]){
                    temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                    //因为有元素交换了，所以不是有序的，标记变为false
                    isSorted = false;
                }
            }
            if(isSorted){
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{2,3,4,5,6,7,8,1};
        sort4(array);
        System.out.println(Arrays.toString(array));
    }
}
