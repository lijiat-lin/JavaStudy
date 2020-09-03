package com.example.knowledge.algorithms;

import java.util.Arrays;

/**
 * 二叉堆的特性
 *  最大堆的堆顶是整个堆中的最大元素
 *  最小堆的堆顶是整个堆中的最小元素
 *
 * 堆排序算法的步骤
 *  1.把无序数组构建成二叉堆 时间复杂度是O(n)
 *  2.循环删除堆顶元素，替换到二叉堆的末尾，调整堆产生新的堆项O(nlogn)
 */
public class HeapSort {
    /**
     * “下沉”调整
     * @param arr 带调整的数组
     * @param parentIndex 父节点
     * @param length 堆的有效大小
     */
    public static void downAdjust(int[] arr,int parentIndex,int length){
        //temp保存父节点的值，用于最后的复制
        int temp = arr[parentIndex];
        int childIndex = parentIndex*2+1;
        while(childIndex<length){
            if(childIndex+1<length && arr[childIndex+1]>arr[childIndex]){
                childIndex++;
            }

            //如果父节点大于任何一个子节点的值，则直接跳出
            if(temp>=arr[childIndex]){
                break;
            }
            arr[parentIndex] = arr[childIndex];
            parentIndex = childIndex;
            childIndex = 2*childIndex +1;
        }
        arr[parentIndex] = temp;
    }

    /**
     * 堆排序（升序）
     * @param array 待调整的堆
     */
    public static void heapSort(int[] array){
        //1.把无序数组构建成最大堆,从最后一个非叶子节点开始，依次做下沉的调整
        for(int i = (array.length-2)/2;i>=0;i--){
            downAdjust(array,i,array.length);
        }
        System.out.println(Arrays.toString(array));
        //2.循环删除堆顶元素，移到集合尾部，调整堆产生新的堆顶
        for(int i=array.length-1;i>0;i--){
            //最后一个元素和第一个元素交换
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            //下沉调整最大堆
            downAdjust(array,0,i);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1,12,27,56,54,17,86,93,100,20,465,123,68,49,341};
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }
}
