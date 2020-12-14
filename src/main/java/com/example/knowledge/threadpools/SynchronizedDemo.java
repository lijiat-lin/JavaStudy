package com.example.knowledge.threadpools;


import java.util.concurrent.ConcurrentHashMap;

public class SynchronizedDemo {
    public synchronized void method() {
        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>();
    }
}
