package com.example.knowledge.design_patterns;

/**
 * @program: knowledge
 * @description: 单例模式
 * @author: zhangjialin
 * @create: 2020-11-11 18:09
 */
public class SingletonDemo {
    private static volatile SingletonDemo instance = null;
    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t 我是构造方法SingletonDemo()");
    }
    /**
     * DCL(Double Check Lock 双端检索模式)
     * @return
     */
    public static SingletonDemo getInstance(){
        //在加锁前后都进行一次判断
        if(instance == null){
            synchronized (SingletonDemo.class){
                if (instance == null){
                    instance = new SingletonDemo();
                }
            }

        }
        return instance;
    }
    public static void main(String[] args) {

    }
}
