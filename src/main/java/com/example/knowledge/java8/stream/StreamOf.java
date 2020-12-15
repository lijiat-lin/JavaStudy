package com.example.knowledge.java8.stream;

import java.util.stream.Stream;

/**
 * @program: knowledge
 * @description: 流的创建
 * @author: zhangjialin
 * @create: 2020-12-15 14:13
 */
public class StreamOf {
    public static void main(String[] args) {
        Stream.of("It's"," a"," wonderful"," day").forEach(System.out::print);
        System.out.println();
        Stream.of(3.14159,2.1231,1231.231).forEach(System.out::print);
    }
}
