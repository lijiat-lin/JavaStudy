package com.example.knowledge.test;

import net.minidev.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Test {

    public static void main(String[] args)  {

        Map<String,String> map = new HashMap<>();
        map.put("amount","1.23");
        System.out.println(JSONObject.toJSONString(map));
    }
}