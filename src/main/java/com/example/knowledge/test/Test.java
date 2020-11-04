package com.example.knowledge.test;


import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ThreadFactory;

/**
 * @program: knowledge
 * @description:
 * @author: zhangjialin
 * @create: 2020-07-31 11:10
 */
public class Test {

    public static void main(String[] args) throws Exception{
        Map<String, String> idenMap = new IdentityHashMap<>();
        String parentId = new String("1");

        idenMap.put(parentId, "hahahha");
        idenMap.put(parentId, "heihiehie");
        idenMap.put(parentId, "sdsfsdfs");
        System.out.println(idenMap.get(parentId));


    }
}
