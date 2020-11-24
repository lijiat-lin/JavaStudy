package com.example.knowledge.interview;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;


class MyThread implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"**************Callable in");
        TimeUnit.SECONDS.sleep(3);
        return 1024;
    }
}

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());

        new Thread(futureTask,"AAA").start();
        new Thread(futureTask,"BBB").start();


        System.out.println(Thread.currentThread().getName()+"***********");
        int result01 = 100;
        //futureTask.get() 建议放在最后
        //futureTask.get() 要求获取callable线程的计算结果，如果没有计算完成就要去强求，会导致阻塞，只得等待计算完成
        int result02 = futureTask.get();
        System.out.println(result01+result02);

    }
}
