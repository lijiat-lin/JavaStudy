package com.example.knowledge.java8.lambda;

/**
 * @program: knowledge
 * @description:
 * @author: zhangjialin
 * @create: 2020-12-15 10:13
 */
class MultiMethod{
    void two(int i,double d){
        System.out.println("two: i="+i+" d="+d);
    }
    void three(int i,double d,String s){
        System.out.println("three: i="+i+" d="+d+" s="+s);
    }
    void four(int i,double d,String s,char c){
        System.out.println("four: i="+i+" d="+d+" s="+s+" c="+c);
    }
}
interface TwoArgs{
    void call2(MultiMethod method,int i,double d);
}
interface ThreeArgs{
    void call3(MultiMethod method,int i,double d,String s);
}

interface FourArgs{
    void call4(MultiMethod method,int i,double d,String s,char c);
}
public class MultiUnbound {
    public static void main(String[] args) {
        TwoArgs twoArgs = MultiMethod::two;
        ThreeArgs threeArgs = MultiMethod::three;
        FourArgs fourArgs = MultiMethod::four;
        MultiMethod multiMethod = new MultiMethod();
        twoArgs.call2(multiMethod,10,10.0);
        threeArgs.call3(multiMethod,10,10.0,"1000");
        fourArgs.call4(multiMethod,10,10.0,"1000",'A');
    }
}
