package com.example.knowledge.test;

import java.util.concurrent.locks.ReentrantLock;

public class Test {

    public static void main(String[] args) {

        synchronized (new Object()){

        }

        new ReentrantLock().lock();




    }

}