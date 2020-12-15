package com.example.knowledge.java8.stream;

import java.util.Random;

/**
 * @program: knowledge
 * @description: 随机数的流式编程
 * @author: zhangjialin
 * @create: 2020-12-15 14:00
 */
public class Randoms {
    public static void main(String[] args) {
        new Random(47)
                .ints(5,20)
                .distinct()
                .limit(7)
                .sorted()
                .forEach(System.out::println);
    }
}
