package com.example.knowledge.algorithms.sort;

import java.util.*;

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

    public static int[] bucketSort(int[] array){
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

        double d = max-min;

        //2.初始化桶
        int bucketNum = array.length;
        ArrayList<LinkedList<Integer>> bucketList = new ArrayList<>(bucketNum);
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
        int[] sortArray = new int[array.length];
        int index = 0;
        for (LinkedList<Integer> list:bucketList) {
            for(int element:list){
                sortArray[index] = element;
                index++;
            }
        }
        return sortArray;
    }

    /**
     * 1.获取数组的最大值和最小值
     * 2.根据最大值和最小值初始化桶二维数组，以及用来记录每个桶内数据数量的数组
     * 3.将数据分值分配到各个桶中
     * 4.对桶中的数据进行快速排序
     * 5.将数据添加到原数组
     * 桶排序
     *
     * @param arr 数组
     * @param bucketSize 桶容量
     */
    public static void bucketSort(int[] arr, int bucketSize) {
        if (arr.length < 2) {
            return;
        }

        // 数组最小值
        int minValue = arr[0];
        // 数组最大值
        int maxValue = arr[1];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < minValue) {
                minValue = arr[i];
            } else if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }

        // 桶数量
        int bucketCount = (maxValue - minValue) / bucketSize + 1;
        int[][] buckets = new int[bucketCount][bucketSize];
        int[] indexArr = new int[bucketCount];

        // 将数组中值分配到各个桶里
        for (int i = 0; i < arr.length; i++) {
            int bucketIndex = (arr[i] - minValue) / bucketSize;
            if (indexArr[bucketIndex] == buckets[bucketIndex].length) {
                ensureCapacity(buckets, bucketIndex);
            }
            buckets[bucketIndex][indexArr[bucketIndex]++] = arr[i];
        }

        // 对每个桶进行排序，这里使用了快速排序
        int k = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (indexArr[i] == 0) {
                continue;
            }
            quickSortC(buckets[i], 0, indexArr[i] - 1);
            for (int j = 0; j < indexArr[i]; j++) {
                arr[k++] = buckets[i][j];
            }
        }
    }

    /**
     * 数组扩容
     *
     * @param buckets
     * @param bucketIndex
     */
    private static void ensureCapacity(int[][] buckets, int bucketIndex) {
        int[] tempArr = buckets[bucketIndex];
        int[] newArr = new int[tempArr.length * 2];
        for (int j = 0; j < tempArr.length; j++) {
            newArr[j] = tempArr[j];
        }
        buckets[bucketIndex] = newArr;
    }

    /**
     * 快速排序递归函数
     *
     * @param arr
     * @param p
     * @param r
     */
    private static void quickSortC(int[] arr, int p, int r) {
        if (p >= r) {
            return;
        }

        int q = partition(arr, p, r);
        quickSortC(arr, p, q - 1);
        quickSortC(arr, q + 1, r);
    }

    /**
     * 分区函数
     *
     * @param arr
     * @param p
     * @param r
     * @return 分区点位置
     */
    private static int partition(int[] arr, int p, int r) {
        int pivot = arr[r];
        int i = p;
        for (int j = p; j < r; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }

        swap(arr, i, r);
        return i;
    }

    /**
     * 交换
     *
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }

        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }



    public static void main(String[] args) {
        int[] array = new int[]{3,5,7,23,8,0,26,89,67,65,34,9,45,16,18,24,23};
        int[] arr = new int[10000];
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            arr[i] = random.nextInt(100000)+1;
        }
        long start = System.currentTimeMillis();
        int[] sortArray = bucketSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("桶排序时间："+(end-start));
        System.out.println(Arrays.toString(sortArray));
    }
}
