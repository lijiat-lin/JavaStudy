package com.example.knowledge.java8.stream;

import java.util.*;

/**
 * @program: knowledge
 * @description: 集合流
 * @author: zhangjialin
 * @create: 2020-12-15 14:17
 */
public class CollectionToStream {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>(Arrays.asList("It's a wonderful day for pie!".split(" ")));
        set.stream().map(x -> x+" ").forEach(System.out::print);
        System.out.println();
        Map<String, Double> m = new HashMap<>(6);
        m.put("pi", 3.14159);
        m.put("e", 2.718);
        m.put("phi", 1.618);
        m.entrySet().stream().map(e -> e.getKey()+ ":" + e.getValue()).forEach(System.out::println);

    }
}
