package com.example.knowledge.test;


import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;


/**
 * @program: knowledge
 * @description:
 * @author: zhangjialin
 * @create: 2020-07-31 11:10
 */
public class Test {

    public static void main(String[] args) throws Exception{


        Date date = new Date(System.currentTimeMillis());
        Date date1 = new Date(date.getTime());
        System.out.println(date.getTime());
        System.out.println(date1.getTime());

    }
}
