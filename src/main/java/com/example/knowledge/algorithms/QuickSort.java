package com.example.knowledge.algorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 快速排序
 * 在每一轮挑选一个基准元素，并让其他比它大的元素移动到一边，比它小的元素移动到数列的另一边，从而把数列拆解成两个部分
 * 主要实现思路：分治法
 * @author 大米哥
 */
public class QuickSort {

    /**
     * 非递归实现
     * @param arr
     * @param start
     * @param end
     */
    private static void quickSortNotRecursive(int[] arr,int start,int end) {
        //用一个集合栈来代替递归的函数栈
        Stack<Map<String,Integer>> quickSortStack = new Stack<Map<String,Integer>>();

        //整个队列的起止下标，以哈希的形式入栈
        Map rootParam = new HashMap();
        rootParam.put("start",start);
        rootParam.put("end",end);
        quickSortStack.push(rootParam);

        while(!quickSortStack.isEmpty()){
            //栈顶元素出栈，获取起止下标
            Map<String,Integer> param = quickSortStack.pop();

            //得到基准元素
            int pivot = partition(arr,param.get("start"),param.get("end"));
            //根据基准元素分成两部分，把每一部分的起止下标入栈
            if(param.get("start")<pivot-1){
                Map<String,Integer> leftParam = new HashMap<>(4);
                leftParam.put("start",param.get("start"));
                leftParam.put("end",pivot-1);
                quickSortStack.push(leftParam);
            }

            if(param.get("end")>pivot+1){
                Map<String,Integer> rightParam = new HashMap<>(4);
                rightParam.put("start",pivot+1);
                rightParam.put("end",param.get("end"));
                quickSortStack.push(rightParam);
            }
        }
    }
    /**
     * 递归实现
     * @param arr
     * @param start
     * @param end
     */
    private static void quickSort(int[] arr,int start,int end){
        //递归结束条件 start大于或者等于end的时候
        if(start>=end){
            return;
        }

        //得到基准元素的位置
        int pivot = partition(arr,start,end);
//        int pivot = partition1(arr,start,end);

        //根据基准元素，分成两部分进行递归排序
        quickSort(arr,start,pivot-1);
        quickSort(arr,pivot+1,end);
    }
    /**
     * 分治（双边循环法）
     * @param arr 待交换的数组
     * @param start 起始下标
     * @param end 结束下标
     * @return
     */
    private static int partition(int[] arr ,int start,int end){
        int pivot = arr[start];
        int left = start;
        int right = end;

        while(left<right){

            //控制right指针比较左移
            while (left<right && arr[right]>pivot){
                right--;
            }

            //控制left指针比较右移
            while(left<right && arr[left]<=pivot){
                left++;
            }

            //交换left和right指针所指向的元素
            if(left<right){
                int p = arr[left];
                arr[left] = arr[right];
                arr[right] = p;
            }
        }

        //pivot和指针重合点交换
        arr[start] = arr[left];
        arr[left] = pivot;

        return left;
    }

    /**
     * 分治（单边循环法）
     * @param arr 待交换的数组
     * @param start 起始下标
     * @param end 结束下标
     * @return
     */
    private static int partition1(int[] arr,int start,int end){
        //取第一个位置（也可以随机选择位置）的元素为基准元素
        int pivot = arr[start];
        int mark = start;

        for (int i = start+1; i <= end; i++) {
            if(arr[i]<pivot){
                mark++;
                int p = arr[mark];
                arr[mark] = arr[i];
                arr[i] = p;
            }
        }

        arr[start] = arr[mark];
        arr[mark] = pivot;

        return mark;

    }
    public static void main(String[] args) {
        int[] arr = new int[]{3,5,7,23,8,0,26,89,67,65,34,9,45,16,18,24,23};
//        quickSort(arr,0,arr.length-1);
        quickSortNotRecursive(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
