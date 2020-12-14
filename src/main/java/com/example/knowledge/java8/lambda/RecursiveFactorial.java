package com.example.knowledge.java8.lambda;

/**
 * @program: knowledge
 * @description:
 * @author: zhangjialin
 * @create: 2020-12-14 17:42
 */
interface IntCall{
    int call(int arg);
}
public class RecursiveFactorial {
    IntCall fib;

    RecursiveFactorial(){
        fib = n -> n == 0? 0:
                n == 1? 1: fib.call(n-1)+fib.call(n-2);
    }

    int fibonacci(int n){
        return fib.call(n);
    }

    public static void main(String[] args) {
        RecursiveFactorial rf = new RecursiveFactorial();
        for (int i = 0; i <= 10  ; i++) {
            System.out.println("i="+i+"       fact="+rf.fibonacci(i));
        }
    }
}
