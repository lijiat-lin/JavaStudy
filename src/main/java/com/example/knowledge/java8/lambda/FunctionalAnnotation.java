package com.example.knowledge.java8.lambda;

/**
 * @program: knowledge
 * @description: 函数式接口
 * @author: zhangjialin
 * @create: 2020-12-15 10:49
 */

@FunctionalInterface
interface Functional{
    String goodbye(String arg);
}

interface FunctionalNoAnn{
    String goodbye(String arg);
}

/*
@FunctionalInterface
interface NotFunctional {
  String goodbye(String arg);
  String hello(String arg);
}
产生错误信息:
NotFunctional is not a functional interface
multiple non-overriding abstract methods
found in interface NotFunctional
*/


public class FunctionalAnnotation {
    public String goodbye(String arg){
        return "GoodBye, "+ arg;
    }

    public static void main(String[] args) {
        FunctionalAnnotation fa = new FunctionalAnnotation();
        Functional f = fa::goodbye;
        FunctionalNoAnn fn = fa::goodbye;
        Functional f1 = a -> "Goodbye, "+ a;
        FunctionalNoAnn fna = a -> "Goodbye, "+a;
    }
}
