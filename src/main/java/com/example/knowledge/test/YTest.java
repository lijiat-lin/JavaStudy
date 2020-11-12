package com.example.knowledge.test;



public class YTest {

    public synchronized void sendSMS() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t invoked sendSMS()");
        sendEmail();
    }

    public void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t #####invoked sendEmail");
    }

    public static void main(String[] args){
        YTest yTest = new YTest();
        new Thread(() -> {
            try {
                yTest.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(() -> {
            try {
                yTest.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2").start();
    }
}
