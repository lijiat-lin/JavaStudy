package com.example.knowledge.java8.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * @program: knowledge
 * @description: 流的建造者模式
 * @author: zhangjialin
 * @create: 2020-12-15 17:39
 */
public class FileToWordsBuilder {
    Stream.Builder<String> builder = Stream.builder();

    FileToWordsBuilder(String filePath) throws IOException{
        Files.lines(Paths.get(filePath))
                //跳过第一行
                .skip(1)
                .forEach(line -> {
                    for (String w : line.split("[ .?,]+")){
                        builder.add(w);
                    }
                });
    }

    Stream<String> stream(){
        return builder.build();
    }

    public static void main(String[] args) throws IOException {
        new FileToWordsBuilder("D:\\code\\gitee\\JavaStudy\\src\\main\\java\\com\\example\\knowledge\\java8\\stream\\Cheese.dat")
                .stream()
                .limit(7)
                .map(w -> w+ "")
                .forEach(System.out::println);

    }
}
