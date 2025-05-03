package com.test;


public class Test2 {
    public static void main(String[] args) {
        Runnable runnable = () ->System.out.println("r1");

        Thread thread = new Thread(runnable,"t2");
        thread.start();
        System.out.println("main");
    }
}
