package com.example.knowledge.java8.lambda;

/**
 * @program: knowledge
 * @description:
 * @author: zhangjialin
 * @create: 2020-12-14 16:24
 */

interface Strategy{
    String approach(String msg);
}

class Soft implements Strategy{
    @Override
    public String approach(String msg) {
        return msg.toLowerCase()+"?";
    }
}

class Unrelated{
    static String twice(String msg){
        return msg+ " " +msg;
    }
}

public class Strategize {
    Strategy strategy;
    String msg;

    Strategize(String msg){
        //在 Strategize 中，你可以看到 Soft 作为默认策略，在构造函数中赋值
        strategy = new Soft();
        this.msg = msg;
    }

    void communicate(){
        System.out.println(strategy.approach(msg));
    }

    void changeStrategy(Strategy strategy){
        this.strategy = strategy;
    }

    public static void main(String[] args) {
        Strategy[] strategies = {
                //种较为简洁且更加自然的方法是创建一个匿名内部类。即便如此，仍有相当数量的冗余代码
                new Strategy() {
                    @Override
                    public String approach(String msg) {
                        return msg.toUpperCase()+"!";
                    }
                },
                // Java 8 的 Lambda 表达式，其参数和函数体被箭头 -> 分隔开。箭头右侧是从 Lambda 返回的表达式。它与单独定义类和采用匿名内部类是等价的，但代码少得多。
                msg -> msg.substring(0,5),
                //Java 8 的方法引用，它以 :: 为特征。 :: 的左边是类或对象的名称， :: 的右边是方法的名称，但是没有参数列表。
                Unrelated :: twice
        };

        Strategize strategize = new Strategize("Hello there");
        strategize.communicate();
        for (Strategy strategy : strategies){
            //在使用默认的 Soft 策略之后，我们逐步遍历数组中的所有 Strategy，并通过调用 changeStrategy() 方法将每个 Strategy 传入变量 s 中。
            strategize.changeStrategy(strategy);

            //现在，每次调用 communicate() 都会产生不同的行为，具体取决于此刻正在使用的策略代码对象。我们传递的是行为，而并不仅仅是数据。[^3]
            strategize.communicate();
        }
    }
}
