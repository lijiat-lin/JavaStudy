package com.example.knowledge.threadpools;

import java.util.concurrent.Callable;

/**
 * @program: knowledge
 * @description:
 * @author: zhangjialin
 * @create: 2020-06-15 14:53
 */
public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {

        Thread.sleep(1000);
        return Thread.currentThread().getName();
    }
}
