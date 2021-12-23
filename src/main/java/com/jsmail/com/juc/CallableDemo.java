package com.jsmail.com.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 两者区别
 * Runnable：无返回值，不会抛异常，重写run()
 *
 * Callable：有返回值，抛异常，重写call()
 */
class MyThread2 implements Runnable {

    @Override
    public void run() {

    }

}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t ***********************come in callable");
        TimeUnit.SECONDS.sleep(3);
        return 1024;
    }

}

/**
 *
 */
public class CallableDemo {

    public static void main(String[] args) throws Exception {

        //两个线程，一个主线程，一个是AA futureTask

        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        new Thread(futureTask, "AA").start();
        new Thread(futureTask, "BB").start();//两个不同线程，同一个futureTask 只会执行一次
//        int result2 = futureTask.get();

        System.out.println(Thread.currentThread().getName() + "\t 计算完成");

        int result = 100;

//        while (!futureTask.isDone()) {
//
//        }

        //建议放在最后  否则需要等待此线程操作完成后  主线程才能执行
        int result2 = futureTask.get();//要求获得Callable线程的计算结果，如果没有计算完成就要去强求，会导致堵塞，直到计算完成

        System.out.println("result: " + (result + result2));
    }

}
