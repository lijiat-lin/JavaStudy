package com.example.knowledge.java8.lambda;

/**
 * @program: knowledge
 * @description: 构造函数引用
 * @author: zhangjialin
 * @create: 2020-12-15 10:22
 */

class Dog{
    String name;
    int age = -1;
    Dog(){name = "stray";}
    Dog(String name){this.name = name;}
    Dog(String name,int age){this.name = name; this.age = age ;}
}
interface MakeNoArgs{
    Dog make();
}

interface MakeOneArgs{
    Dog make(String name);
}

interface MakeTwoArgs{
    Dog make(String name,int age);
}

public class CtorReference {
    public static void main(String[] args) {
        MakeNoArgs noArgs = Dog::new;
        MakeOneArgs oneArgs = Dog::new;
        MakeTwoArgs twoArgs = Dog::new;

        Dog d1 = noArgs.make();
        Dog d2 = oneArgs.make("dog");
        Dog d3 = twoArgs.make("dog",12);
    }
}
