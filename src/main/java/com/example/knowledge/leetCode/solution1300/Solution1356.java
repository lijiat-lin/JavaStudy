package com.example.knowledge.leetCode.solution1300;

import java.util.Arrays;

/**
 * @program: knowledge
 * @description:
 * @author: zhangjialin
 * @create: 2020-11-06 11:33
 */
public class Solution1356 {
    /**
     * 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
     *
     * 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
     *
     * 请你返回排序后的数组。
     *
     * 示例 1：
     *
     * 输入：arr = [0,1,2,3,4,5,6,7,8]
     * 输出：[0,1,2,4,8,3,5,6,7]
     * 解释：[0] 是唯一一个有 0 个 1 的数。
     * [1,2,4,8] 都有 1 个 1 。
     * [3,5,6] 有 2 个 1 。
     * [7] 有 3 个 1 。
     * 按照 1 的个数排序得到的结果数组为 [0,1,2,4,8,3,5,6,7]
     *
     * @param arr
     * @return
     */
    public int[] sortByBits(int[] arr) {

        int[] array = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            array[i] = Integer.bitCount(arr[i]) * 10000000 + arr[i];
        }
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] % 10000000;
        }
        return array;
    }

    public static void main(String[] args) {
        //10000
        System.out.println(Integer.bitCount(16));
        //1111
        System.out.println(Integer.bitCount(15));
        //1110
        System.out.println(Integer.bitCount(14));
        //1101
        System.out.println(Integer.bitCount(13));
        //1100
        System.out.println(Integer.bitCount(12));
        //1011
        System.out.println(Integer.bitCount(11));
    }
}
