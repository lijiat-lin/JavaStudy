package com.example.knowledge.java8.stream;

import static java.util.stream.IntStream.range;

/**
 * @program: knowledge
 * @description:
 * @author: zhangjialin
 * @create: 2020-12-15 16:29
 */
public class Ranges {

    public static void main(String[] args) {
        int result = 0 ;
        for (int i = 10; i < 20; i++) {
            result+=i;
        }
        System.out.println(result);

        result = 0;
        for (int i : range(10,20).toArray()){
            result+=i;
        }
        System.out.println(result);

        System.out.println(range(10,20).sum());
    }
}
