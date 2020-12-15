package com.example.knowledge.java8.lambda;

/**
 * @program: knowledge
 * @description:
 * @author: zhangjialin
 * @create: 2020-12-15 11:37
 */
@FunctionalInterface
interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}

public class TriFunctionTest {
    static int f(int i, long l, double d) { return 99; }
    public static void main(String[] args) {
        TriFunction<Integer, Long, Double, Integer> tf =
                TriFunctionTest::f;
        tf = (i, l, d) -> 55;

        //输出结果：55
        System.out.println(tf.apply(10,10L,10.0));
    }
}
