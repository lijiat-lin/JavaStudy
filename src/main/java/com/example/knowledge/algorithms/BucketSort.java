package com.example.knowledge.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * 桶排序：根据无序数组划分  若干个桶 把相应的数值放到相应数值范围的桶中
 *
 * 排序算法         平均时间复杂度         最坏时间复杂度         空间复杂度           是否稳定排序
 * 冒泡排序         O(n²)
 * 鸡尾酒排序        O(n²)
 * 快速排序         O(nlogn)
 * 堆排序           O(nlogn)
 * 计数排序         O(n+m)
 * 桶排序           O(n)
 * @author 大米哥
 */
public class BucketSort {

    public static double[] bucketSort(double[] array){
        //1.得到数列的最小值和最大值
        double max = array[0];
        double min = array[0];
        for (int i = 0; i < array.length; i++) {
            if(array[i]>max){
                max = array[i];
            }
            if(array[i]<min){
                min = array[i];
            }
        }

        double d = max-min;

        //2.初始化桶
        int bucketNum = array.length;
        ArrayList<LinkedList<Double>> bucketList = new ArrayList<>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new LinkedList<>());
        }

        //3.遍历原始数组，将每个元素放入桶中
        for (int i = 0; i < array.length; i++) {
            int num = (int)((array[i]-min)*(bucketNum-1)/d);
            bucketList.get(num).add(array[i]);
        }

        //4.对桶内部元素进行排序
        for (int i = 0; i < bucketList.size(); i++) {
            Collections.sort(bucketList.get(i));
        }
        //5.输出所有元素
        double[] sortArray = new double[array.length];
        int index = 0;
        for (LinkedList<Double> list:bucketList) {
            for(double element:list){
                sortArray[index] = element;
                index++;
            }
        }
        return sortArray;
    }


    public static void main(String[] args) {
        double[] array = new double[]{4.12,6.421,0.0023,3.0,2.123,8.122,4.12,10.09};
        double[] sortArray = bucketSort(array);
        System.out.println(Arrays.toString(sortArray));
    }
}
