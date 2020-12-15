package com.example.knowledge.java8.stream;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: knowledge
 * @description: 读取file文件
 * @author: zhangjialin
 * @create: 2020-12-15 14:48
 */
public class RandomWords implements Supplier<String> {
    List<String> words = new ArrayList<>();
    Random random = new Random(50);

    RandomWords(String fname) throws IOException{
        List<String> lines = Files.readAllLines(Paths.get(fname));
        //略过第一行
        for (String line : lines.subList(1,lines.size())){
            for (String word : line.split("[ .?,]+")){
                words.add(word.toLowerCase());
            }
        }
        lines.stream().forEach(System.out::println);
        System.out.println();

        words.stream().forEach(System.out::println);
    }
    @Override
    public String get() {
        return words.get(random.nextInt(words.size()));
    }

    @Override
    public String toString() {
        return words.stream().collect(Collectors.joining(" "));
    }

    public static void main(String[] args) throws IOException{
        System.out.println(Stream.generate(new RandomWords("D:\\code\\gitee\\JavaStudy\\src\\main\\java\\com\\example\\knowledge\\java8\\stream\\Cheese.dat")).limit(10).collect(Collectors.joining(" ")));
    }
}
