package com.example.knowledge.interview;


class LocalThread implements Runnable{

    private String lockA;
    private String lockB;

    public LocalThread(String lockA,String lockB){
        this.lockA = lockA;
        this.lockB = lockB;
    }
    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 自己持有"+lockA+"\t 尝试获取"+lockB);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t 自己持有"+lockB+"\t 尝试获取"+lockA);
            }
        }
    }
}

/**
 * @program: knowledge
 * @description:
 * @author: zhangjialin
 * @create: 2020-11-24 10:52
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new LocalThread(lockA,lockB),"ThreadAAA").start();
        new Thread(new LocalThread(lockB,lockA),"ThreadBBB").start();



    }
}
