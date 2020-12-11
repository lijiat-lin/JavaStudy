package com.example.knowledge.test;


import net.minidev.json.JSONObject;
import org.assertj.core.util.Arrays;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test{


    public static void main(String[] args)   {
        Map<String,Object> map = new HashMap<>();

        int[] array = new int[10];
        map.put("data",array);
        map.put("result","success");


        List<Object> list = Arrays.asList(map.get("data"));
        System.out.println(JSONObject.toJSONString(map));
    }

}