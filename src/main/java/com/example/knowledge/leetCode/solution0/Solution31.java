package com.example.knowledge.leetCode.solution0;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @program: knowledge
 * @description: 下一排队列（中等难度）
 * @author: zhangjialin
 * @create: 2020-11-10 14:34
 */
public class Solution31 {
    /**
     * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
     *
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     *
     * 必须原地修改，只允许使用额外常数空间。
     *
     * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     *
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
        boolean flag = false;
        int minIndex = nums.length-1;
        for (int i = nums.length-1; i >0 ; i--) {
            if(nums[i]>nums[i-1]){
                //查询 位置i以后大于i的最小值，并交换后升序排序
                sort(nums,i-1);
                flag = true;
                break;
            }
        }
        if (!flag){
            Arrays.sort(nums);
        }
    }
    private static void sort(int[] nums,int i){
        int min = Integer.MAX_VALUE;
        int minIndex = i;
        for (int j = i+1; j < nums.length; j++) {
            if(nums[j]>nums[i] && nums[j]<min){
                min = nums[j];
                minIndex = j;
            }
        }
        //交换位置
        int temp = nums[i];
        nums[i] = nums[minIndex];
        nums[minIndex] = temp;
        Arrays.sort(nums,i+1,nums.length);

    }

    public static int getIdCount(List<Object> objects)  {
        int total = 0;
        for (Object obj:objects) {
            try{
                if(obj.getClass().getField("holidayName")!=null){
                    total++;
                }
            } catch(NoSuchFieldException e){
                continue;

            }
        }

        return total;
    }
    public static void main(String[] args) {

        int[] array = new int[]{1,3,2};
        nextPermutation(array);
        System.out.println(Arrays.toString(array));
    }
}
