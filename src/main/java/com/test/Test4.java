package com.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "Test4")
public class Test4 {
    //wait notify实现先后打印
    private static final Object object = new Object();
    private static boolean t2Runed = false;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (object){
                //while循环防止虚假唤醒
                while (!t2Runed) {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                log.debug("t1");

            }

        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (object){
                log.debug("t2");
                t2Runed = true;
                object.notifyAll();
            }
        }, "t2");

        t1.start();
        t2.start();

    }


}
