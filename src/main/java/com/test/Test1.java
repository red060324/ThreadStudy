package com.test;


public class Test1 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println("t1"));
        thread.setName("t1");
        thread.start();
        System.out.println("main");



    }
}
