package com.example.knowledge.interview;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: knowledge
 * @description:
 * @author: zhangjialin
 * @create: 2020-11-24 10:52
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {

        //固定线程数的的线程池
        ExecutorService executorService= Executors.newFixedThreadPool(5);
        //线程池中只有一个线程
//        Executors.newSingleThreadExecutor();
        //可变线程数的线程池
//        Executors.newCachedThreadPool();

        //模拟10个用户办理业务
        try {
            for (int i = 1; i <= 20 ; i++) {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }



    }
}
