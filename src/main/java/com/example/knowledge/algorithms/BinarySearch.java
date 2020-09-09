package com.example.knowledge.algorithms;

/**
 * @program: knowledge
 * @description: 二分查找
 * @author: zhangjialin
 * @create: 2020-09-09 14:09
 */
public class BinarySearch {


    public static int search(int[] array,int value){
        int low = 0;
        int high = array.length-1;

        while (low<=high){
            int mid = low+(high-low)>>1;
            if(array[mid] == value){
                return mid;
            }else if(array[mid]>value){
                high = mid-1;
            }else{
                low = mid+1;
            }
        }
        return -1;
    }

    /**
     * 如何编程实现“求一个数的平方根”？要求精确到小数点后 6 位
     * @param num
     * @param precision
     * @return
     */
    public static double squareRoot(double num,double precision){
        double low,high,mid,tmp;
        if (num>1){
            low = 1;
            high = num;
        }else{
            low = num;
            high = 1;
        }
        while (low<=high){
            mid = low+(high-low)/2.000;
            tmp = mid*mid;
            if(tmp-num<=precision && tmp-num >= precision*-1){
                return mid;
            }else if (tmp >num){
                high = mid;
            }else{
                low = mid;
            }
        }

        return -1.000;
    }

    /**
     * 查找第一个值等于给定值的元素
     * @param array
     * @param value
     * @return
     */
    public static int search1(int[] array,int value){
        int low = 0;
        int high = array.length-1;

        while (low<=high){
            int mid = low+((high-low)>>1);

            if(array[mid]>value){
                high = mid-1;
            }else if(array[mid]<value){
                low = mid+1;
            }else{
                if(mid == 0 || array[mid-1]!=value){
                    return mid;
                }else {
                    high = mid-1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找最后一个值等于给定值的元素
     * @param array
     * @param value
     * @return
     */
    public static int search2(int[] array,int value){
        int low = 0;
        int high = array.length-1;

        while (low<=high){
            int mid = low+((high-low)>>1);

            if(array[mid]>value){
                high = mid-1;
            }else if(array[mid]<value){
                low = mid+1;
            }else{
                if(mid+1<= array.length-1 && array[mid+1]!=value){
                    return mid;
                }else {
                    low = mid+1;
                }
            }
        }
        return -1;
    }
    /**
     * 查找第一个大于等于给定值的元素
     * @param array
     * @param value
     * @return
     */
    public static int search3(int[] array,int value){
        int low = 0;
        int high = array.length-1;

        while (low<=high){
            int mid = low+((high-low)>>1);

            if(array[mid]>=value){
                if(mid == 0 || array[mid-1] < value){
                    return mid;
                }else{
                    high = mid-1;
                }
            }else {
                low = mid+1;
            }
        }
        return -1;
    }
    /**
     * 查找最后一个小于等于给定值的元素
     * @param array
     * @param value
     * @return
     */
    public static int search4(int[] array,int value) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);

            if (array[mid] <= value) {
                if(mid  == array.length-1 || array[mid+1]>value){
                    return mid;
                }else{
                    low = mid + 1;
                }

            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static int search5(int[] nums,int target){
        int low = 0;
        int high = nums.length-1;

        while (low<=high){
            int mid = low+((high-low)>>1);

            if(nums[mid] == target){
                return mid;
            }
            if(nums[low] == target){
                return low;
            }
            if(nums[high] == target){
                return high;
            }
            if(nums[low]<=nums[mid]){
                //左半区有序，右半区循环
                if(nums[mid]>target && target>nums[low]){
                    //查询目标在有序左半区
                    high = mid-1;
                } else{
                    low = mid+1;
                }
            }else{
                //右半区有序，左半区循环
                if(nums[mid]<target && target<nums[high]){
                    //查询目标在有序右半区
                    low = mid+1;
                }else{
                    high = mid-1;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
//        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,11,11,11,11,11, 12, 14, 16, 19};

        int[] array = new int[]{4,5,6,7,0,1,2};
        int index = search5(array,0);
        System.out.println(index);

//        System.out.println(squareRoot(101.98,0.000001));
    }
}
