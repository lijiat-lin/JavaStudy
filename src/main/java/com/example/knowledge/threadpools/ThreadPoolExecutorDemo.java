package com.example.knowledge.threadpools;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: knowledge
 * @description:
 * @author: zhangjialin
 * @create: 2020-06-15 14:20
 *
 * 我们在代码中模拟了 10 个任务，我们配置的核心线程数为 5 、等待队列容量为 100 ，
 * 所以每次只可能存在 5 个任务同时执行，剩下的 5 个任务会被放到等待队列中去。
 * 当前的 5 个任务之行完成后，才会之行剩下的 5 个任务。
 */
public class ThreadPoolExecutorDemo {

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 100;
    private static final Long KEEP_ALICE_TIME = 1L;

    public static void main(String[] args){
        //使用阿里巴巴推荐的创建线程的方式
        //通过ThreadPoolExecutor的构造函数自定义参数创建
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALICE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        for (int i = 0; i < 5; i++) {
            //创建WorkerThread对象（WorkerThread实现了Runnable接口）
            Runnable worker = new MyRunnable(""+i);
            executor.execute(worker);
        }

        executor.shutdown();
        while (!executor.isTerminated()){

        }
        System.out.println("Finished all threads");
    }
}
