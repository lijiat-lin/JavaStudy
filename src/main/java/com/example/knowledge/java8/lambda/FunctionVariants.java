package com.example.knowledge.java8.lambda;

import java.util.function.Function;

/**
 * @program: knowledge
 * @description: Function变体
 * @author: zhangjialin
 * @create: 2020-12-15 11:27
 */

class Foo {}

class Bar {
    Foo f;
    Bar(Foo f) { this.f = f; }
}

class IBaz {
    int i;
    IBaz(int i) {
        this.i = i;
    }
}

class LBaz {
    long l;
    LBaz(long l) {
        this.l = l;
    }
}

class DBaz {
    double d;
    DBaz(double d) {
        this.d = d;
    }
}
public class FunctionVariants {
    static Function<Foo,Bar> f1 = f -> new Bar(f);

}
