package com.jsmail.com.thread;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

//同步队列
public class SynchronousQueueDemo {

    /**
     * SynchronousQueue没有容量。
     * 与其他BlockingQueue不同，SynchronousQueue是一个不存储元素的BlockingQueue
     * 每一个put操作必须要等待一个take操作，否则不能继续添加元素，反之亦然。
     */
    public static void main(String[] args) {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t put 1");
                synchronousQueue.put("1");

                System.out.println(Thread.currentThread().getName() + "\t put 2");
                synchronousQueue.put("2");

                System.out.println(Thread.currentThread().getName() + "\t put 3");
                synchronousQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t" + synchronousQueue.take());

                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t" + synchronousQueue.take());

                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t" + synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BBB").start();
    }

}
