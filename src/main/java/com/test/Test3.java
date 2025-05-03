package com.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("futureTask");
                Thread.sleep(2000);
                return 404;
            }
        });

        Thread thread = new Thread(futureTask,"t1");
         thread.start();
        System.out.println(futureTask.get());

    }
}
