package com.jsmail.com.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{ //资源类

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();
        try{
            //1、判断
            while (number != 0) {
                // 等待，不能生产
                condition.await();
            }
            //2、干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3、唤醒
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception {
        try{
            lock.lock();
            //1、判断
            while (number == 0) {
                // 等待，不能生产
                condition.await();
            }
            //2、干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3、唤醒
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

}

/**
 * 传统版 生产者消费者
 * 题目:一个初始值为零的变量，两个线程对其交替操作，1个加一个减1 ，来5轮
 * 1、线程         操作(方法)          资源类
 * 2、判断         干活          通知
 * 3、防止虚假唤醒机制  多线程环境下必须用while做判断，用if超过两个线程会出错
 *
 */
public class ProdConsumer_TraditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    shareData.increment();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AA").start();

        new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    shareData.decrement();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"BB").start();

        new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    shareData.increment();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"CC").start();

        new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    shareData.decrement();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"DD").start();
    }

}
