package com.example.knowledge.test;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: knowledge
 * @description:
 * @author: zhangjialin
 * @create: 2020-07-31 11:10
 */
public class Test {

    public static void main(String[] args) throws Exception{
        LinkedHashMap accessOrderedMap = new LinkedHashMap(16, 0.75F, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                // 实现自定义删除策略，否则行为就和普遍Map没有区别
                 return size() > 3;
                }
        };
        accessOrderedMap.put("Project1", "Valhalla");
        accessOrderedMap.put("Project2", "Panama");
        accessOrderedMap.put("Project3", "Loom");
        accessOrderedMap.forEach( (k,v) -> { System.out.println(k +":" + v); });
        // 模拟访问
        accessOrderedMap.get("Project2");
        accessOrderedMap.get("Project2");
        accessOrderedMap.get("Project3");
        System.out.println("Iterate over should be not affected:");
        accessOrderedMap.forEach( (k,v) -> { System.out.println(k +":" + v); });
        // 触发删除
        accessOrderedMap.put("Project4", "Mission Control");
        System.out.println("Oldest entry should be removed:");
        accessOrderedMap.forEach((k,v)->{
            System.out.println(k +":" + v);
        });

    }
}
