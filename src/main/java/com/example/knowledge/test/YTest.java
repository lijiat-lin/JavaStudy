package com.example.knowledge.test;


import java.util.concurrent.atomic.AtomicInteger;

public class YTest {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        atomicInteger.getAndIncrement();
        //期望值是5  期望别的线程没人动过
        //更新值是2019
        //这个会输出true  并且 atomicInteger的值被更改为 2019
        System.out.println(atomicInteger.compareAndSet(5, 2019));

        //这个会输出false  ，并且atomicInteger的值依旧是之前更改后的2019
        System.out.println(atomicInteger.compareAndSet(5, 2019));


    }

}
