package com.example.knowledge.java8.stream;

import java.util.stream.Stream;

/**
 * @program: knowledge
 * @description: 斐波那契数列
 * @author: zhangjialin
 * @create: 2020-12-15 17:22
 */
public class Fibonacci {
    int  x = 1;
    Stream<Integer> numbers(){
        return Stream.iterate(0,i->{
           int result = x+i;
           x = i;
           //返回的值赋值给i
           return result;
        });
    }

    public static void main(String[] args) {
        new Fibonacci().numbers()
                //过滤前20个
                .skip(20)
                //只取10ge
                .limit(10)
                .forEach(System.out::println);
    }
}
