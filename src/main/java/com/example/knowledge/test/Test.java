package com.example.knowledge.test;



import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
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
        String data = null;
        boolean retValue;
        while (FLAG){
            data = atomicInteger.incrementAndGet()+"";
            retValue = blockingQueue.offer(data,2L, TimeUnit.SECONDS);
            if (retValue){
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"成功");
            }else{
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t 生产者生产结束");

    }

    public void myConsumer() throws Exception{
        String result = null;
        while (FLAG){
            result = blockingQueue.poll(2L,TimeUnit.SECONDS);

            if (result ==null || result.equalsIgnoreCase("")){
                FLAG = false;
                System.out.println(Thread.currentThread().getName()+"\t 超过两秒没有取到蛋糕，消费退出");
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t 消费成功"+result+"成功");

        }
    }

    public void stop(){
        FLAG = false;
    }

}
public class Test {

    public static void main(String[] args) throws InterruptedException {
        ShareData shareData = new ShareData(new ArrayBlockingQueue<>(10));

        new Thread(() ->{
            System.out.println("生产开始");
            try {
                shareData.prod();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AAA").start();

        new Thread(() ->{
            System.out.println("消费开始");
            try {
                shareData.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"BBB").start();


        TimeUnit.SECONDS.sleep(6);
        shareData.stop();
    }

}