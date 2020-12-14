package com.example.knowledge.java8.lambda;

/**
 * @program: knowledge
 * @description: 方法引用
 * @author: zhangjialin
 * @create: 2020-12-14 17:58
 */
interface Callable {
    /**
     * [1] 我们从单一方法接口开始
     * @param s
     */
    void call(String s);
}

class Describe {
    /**
     * [2] show() 的签名（参数类型和返回类型）符合 Callable 的 call() 的签名。
     * @param msg
     */
    void show(String msg) {
        System.out.println(msg);
    }
}
public class MethodReferences {
    /**
     * hello() 也符合 call() 的签名。
     * @param name
     */
    static void hello(String name) {
        System.out.println("Hello, " + name);
    }

    static class Description {
        String about;
        Description(String desc) { about = desc; }

        /**
         * help() 也符合call() 的签名，它是静态内部类中的非静态方法。
         * @param msg
         */
        void help(String msg) {
            System.out.println(about + " " + msg);
        }
    }

    static class Helper {
        /**
         * assist() 是静态内部类中的静态方法,同样符合call()的签名。
         * @param msg
         */
        static void assist(String msg) {
            System.out.println(msg);
        }
    }

    public static void main(String[] args) {
        Describe d = new Describe();
        //我们将 Describe 对象的方法引用赋值给 Callable ，它没有 show() 方法，而是 call() 方法。 但是，Java 似乎接受用这个看似奇怪的赋值，因为方法引用符合 Callable 的 call() 方法的签名。
        Callable c = d :: show;
        //我们现在可以通过调用 call() 来调用 show()，因为 Java 将 call() 映射到 show()
        c.call("call()");

        //这是一个静态方法引用。
        c = MethodReferences::hello;
        c.call("call()");

        //这是 [6] 的另一个版本：对已实例化对象的方法的引用，有时称为绑定方法引用。
        c = new Description("valuable") :: help;
        c.call("information");

        //最后，获取静态内部类中静态方法的引用与 [8] 中通过外部类引用相似。
        c = Helper:: assist;
        c.call("Help!");
    }
}
