package com.example.knowledge.threadpools;

import org.assertj.core.util.Lists;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @program: knowledge
 * @description:
 * @author: zhangjialin
 * @create: 2020-06-17 11:25
 */
public class ThreadLocalTest {
    private List<String> messages = Lists.newArrayList();

    public static final ThreadLocal<ThreadLocalTest> holder = ThreadLocal.withInitial(ThreadLocalTest::new);

    public static void add(String message){
        holder.get().messages.add(message);
    }

    public static List<String> clear(){
        List<String> messages = holder.get().messages;
        holder.remove();

        System.out.println("size:" +holder.get().messages.size());
        return messages;
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println("thread1");
                }

            }
        });
    }
}
