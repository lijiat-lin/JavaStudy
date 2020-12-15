package com.example.knowledge.test;


import java.util.HashMap;
import java.util.Map;

public class Test{
    public static void main(String[] args)   {
        Map<Integer,Integer> map = new HashMap<>();
        map.put(1,10);
        map.put(2,20);
        map.put(3,30);

        map.merge(1,100,Integer::sum);


    }

}