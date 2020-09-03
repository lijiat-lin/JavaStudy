package com.example.knowledge.cartoon;

import java.util.Arrays;

/**
 * 优先队列
 */
public class TrackerPriorityQueue {
    private int[] array;

    private int size;

    public TrackerPriorityQueue(){
        array = new int[32];
    }

    /**
     * 入队
     * @param key
     */
    public void enQuene(int key){
        //队列长度超出范围  扩容
        if(size>=array.length){
            resize();
        }
        array[size++] = key;
        upAdjust();
    }

    /**
     * 出队
     * @throws Exception
     */
    public int deQueue() throws Exception{
        if(size<=0){
            throw new Exception("the queue is empty");
        }

        //获取堆顶元素
        int head = array[0];
        //让最后一个元素移到堆顶
        array[0] = array[--size];
        downAdjust();
        return head;
    }

    /**
     * 上浮 调整
     */
    private void upAdjust(){
        int childIndex = size-1;
        int parentIndex = (childIndex-1)/2;
        //temp保存插入的叶子节点值  用于最后的复制
        int temp = array[childIndex];

        while (childIndex>0 && temp>array[parentIndex]){

            //无须真正的交换  直接赋值
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = parentIndex/2;

        }
        array[childIndex] = temp;
    }

    /**
     * 下沉调整
     */
    private void downAdjust(){
        int parentIndex = 0;
        int temp = array[parentIndex];
        int childIndex = 1;
        while (childIndex<size){
            if(childIndex+1<size && array[childIndex+1] > array[childIndex]){
                childIndex++;
            }
            if(temp >= array[childIndex]){
                break;
            }
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = childIndex*2 +1;
        }
        array[parentIndex] = temp;
    }

    /**
     * 队列扩容
     */
    private void resize(){
        int newSize = this.size*2;

        this.array = Arrays.copyOf(this.array,newSize);
    }

    public static void main(String[] args) throws Exception {
        TrackerPriorityQueue queue = new TrackerPriorityQueue();
        queue.enQuene(3);
        queue.enQuene(5);
        queue.enQuene(10);
        queue.enQuene(34);
        queue.enQuene(18);
        queue.enQuene(6);
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
    }
}
