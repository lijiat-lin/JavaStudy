package com.example.knowledge.java8.stream;

import java.util.Random;
import java.util.stream.Stream;

/**
 * @program: knowledge
 * @description: 随机数流
 * @author: zhangjialin
 * @create: 2020-12-15 14:33
 */
public class RandomGenerators {
    public static <T> void show(Stream<T> stream){
        stream.limit(4).forEach(System.out::println);
        System.out.println("*******************************************");
    }

    public static void main(String[] args) {
        Random random = new Random(47);
        show(random.ints().boxed());
        show(random.longs().boxed());
        show(random.doubles().boxed());
        //控制上下限
        show(random.ints(2,10).boxed());
        show(random.longs(100,10000).boxed());
        show(random.doubles(0.1,0.5).boxed());

        //控制流的大小
        show(random.ints(2).boxed());
        show(random.longs(2).boxed());
        show(random.doubles(2).boxed());

        //控制流的大小和界限
        show(random.ints(3,2,10).boxed());
        show(random.longs(3,100,10000).boxed());
        show(random.doubles(3,0.1,0.5).boxed());

    }
}
