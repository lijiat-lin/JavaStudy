package com.example.knowledge.test;



import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

class ShareData{
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public ShareData(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void prod() throws Exception{
        while (FLAG){

        }

    }

}
public class Test {

    public static void main(String[] args) {
        ShareData shareData = new ShareData(new ArrayBlockingQueue<>(10));



    }

}