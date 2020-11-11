package com.example.knowledge.test;


import java.util.concurrent.TimeUnit;

/**
 * @program: knowledge
 * @description:
 * @author: zhangjialin
 * @create: 2020-07-31 11:10
 */
public class Test {
    public static void main(String[] args) {
        Mydata mydata = new Mydata();
        int b = 0;

        new Thread(() -> {

            mydata.number++;


        },"AAA").start();
        while (mydata.number == 0) {

        }

    }
}

class Mydata{
    int number = 0;
    public void addTo60(){
        this.number = 60;
    }
}
