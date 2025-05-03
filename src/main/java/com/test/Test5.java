package com.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

@Slf4j(topic = "Test5")
public class Test5 {
    static boolean t2Runed = false;
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition1 = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            try {
                lock.lock();
                while (!t2Runed){
                    try {
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("t1");
            }finally {
                lock.unlock();
            }


        },"t1");

        Thread t2 = new Thread(() -> {


            try {
                lock.lock();
                log.debug("t2");
                t2Runed = true;
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                condition1.signalAll();
            }finally {
                lock.unlock();
            }



        }, "t2");

        t1.start();

        t2.start();
    }
}
