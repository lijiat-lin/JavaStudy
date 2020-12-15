package com.example.knowledge.java8.lambda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @program: knowledge
 * @description:
 * @author: zhangjialin
 * @create: 2020-12-15 18:04
 */
public class CollectionSortLambda {
    static final String filePath = "D:\\code\\gitee\\JavaStudy\\src\\main\\java\\com\\example\\knowledge\\java8\\lambda\\Cheese.dat";
    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get(filePath));
        List<String> words = new ArrayList<>();
        for(String line : lines.subList(1,lines.size())){
            for (String word : line.split("[ ,.?]+")){
                words.add(word.toLowerCase());
            }
        }
        List<String> words1 = new ArrayList<>(words);
        List<String> words2 = new ArrayList<>(words);
        List<String> words3 = new ArrayList<>(words);
        words.stream().map(v -> v+" ").forEach(System.out::print);

        //传统排序方式
        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(),o2.length());
            }
        });
        System.out.println();
        words.stream().map(v -> v+" ").forEach(System.out::print);

        //使用lambda表达式  (s1,s2) 符合
        Collections.sort(words1,(s1,s2)-> Integer.compare(s1.length(),s2.length()));
        System.out.println();
        words1.stream().map(v -> v+" ").forEach(System.out::print);

        //使用比较器的构造方法
        Collections.sort(words2,Comparator.comparingInt(String::length));
        System.out.println();
        words2.stream().map(v -> v+" ").forEach(System.out::print);

        //利用java8在List接口中添加的sort方法
        words3.sort(Comparator.comparingInt(String::length));
        System.out.println();
        words3.stream().map(v -> v+" ").forEach(System.out::print);

    }
}
