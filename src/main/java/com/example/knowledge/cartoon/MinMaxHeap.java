package com.example.knowledge.cartoon;

import java.util.Arrays;

/**
 * 二叉堆
 * @author zhangjialin
 */
public class MinMaxHeap {
    /**
     * 上浮 调整(最小堆)
     * @param array 待调整的堆
     */
    public static void upMinAdjust(int[] array){
        //最后一个叶子节点的下标
        int childIndex = array.length-1;
        //最后一个叶子节点的父节点的下标
        int parentIndex = (childIndex-1)/2;

        //temp保存插入的叶子节点值，用于最后的复制
        int temp = array[childIndex];
        while(childIndex>0&&temp<array[parentIndex]){
            //无需真正交换，只需要单向复制即可
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex-1)/2;

        }
        array[childIndex] = temp;

    }


    /**
     * 上浮 调整(最大堆)
     * @param array 待调整的堆
     */
    public static void upMaxAdjust(int[] array){
        //最后一个叶子节点的下标
        int childIndex = array.length-1;
        //最后一个叶子节点的父节点的下标
        int parentIndex = (childIndex-1)/2;

        //temp保存插入的叶子节点值，用于最后的复制
        int temp = array[childIndex];
        while(childIndex>0&&temp>array[parentIndex]){
            //无需真正交换，只需要单向复制即可
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = parentIndex/2;

        }
        array[childIndex] = temp;

    }

    /**
     * 下沉调整（最小堆）
     * @param array 待调整的堆
     * @param parentIndex
     * @param length
     */
    public static void downMinAdjust(int[] array,int parentIndex,int length){
        //temp保存父节点的值，用于最后的复制
        int temp = array[parentIndex];
        int childIndex = parentIndex*2 +1;
        while(childIndex<length){
            //如果有右孩子，且右孩子小于左孩子的值，则定位到右孩子
            if(childIndex+1<length && array[childIndex+1]<array[childIndex]){
                childIndex++;
            }
            //如果父节点小于任何一个孩子的值，则直接跳出
            if(temp<=array[childIndex]){
                break;
            }
            //无需真正的交换，单向赋值即可
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = parentIndex*2 +1;
        }
        array[parentIndex] = temp;
    }

    /**
     * 下沉调整（最小堆）
     * @param array 待调整的堆
     * @param parentIndex
     * @param length
     */
    public static void downMaxAdjust(int[] array,int parentIndex,int length){
        //temp保存父节点的值，用于最后的复制
        int temp = array[parentIndex];
        int childIndex = parentIndex*2 +1;
        while(childIndex<length){
            //如果有右孩子，且右孩子小于左孩子的值，则定位到右孩子
            if(childIndex+1<length && array[childIndex+1]>array[childIndex]){
                childIndex++;
            }
            //如果父节点小于任何一个孩子的值，则直接跳出
            if(temp>=array[childIndex]){
                break;
            }
            //无需真正的交换，单向赋值即可
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = parentIndex*2 +1;
        }
        array[parentIndex] = temp;
    }

    /**
     * 构建堆
     * @param array 待调整的堆
     */
    public static void buildMinHeap(int[] array){
        //从最后一个非叶子节点开始  依次做下沉的调整
        for(int i=(array.length-2)/2;i>=0;i--){
            downMinAdjust(array,i,array.length);
        }
    }

    public static void buildMaxHeap(int[] array){
        //从最后一个非叶子节点开始  依次做下沉的调整
        for(int i=(array.length-2)/2;i>=0;i--){
            downMaxAdjust(array,i,array.length);
        }
    }



    public static void main(String[] args) {
        int[] array = new int[]{1,3,2,6,5,7,8,9,10,4,0};
        upMinAdjust(array);
        System.out.println(Arrays.toString(array));

        buildMaxHeap(array);
        System.out.println(Arrays.toString(array));

        array = new int[]{7,1,3,10,5,2,8,9,6};
        buildMinHeap(array);
        System.out.println(Arrays.toString(array));
    }
}
