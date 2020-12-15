package com.example.knowledge.java8.lambda;

/**
 * @program: knowledge
 * @description:
 * @author: zhangjialin
 * @create: 2020-12-15 09:51
 */
class X {
    String f(){ return "X::f"; }
}

interface A{
    String f();
}

interface MakeString{
    String make();
}

interface TransformX{
    String reansformX(X x);
}
public class UnboundMethodReference {
    public static void main(String[] args) {
        //MakeString ms = X::f;

        TransformX tf = X::f;
        X x = new X();

        System.out.println(tf.reansformX(x));
        System.out.println(x.f());
    }

}
