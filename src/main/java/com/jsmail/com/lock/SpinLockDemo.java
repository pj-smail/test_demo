package com.jsmail.com.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

//手写自旋锁
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();//默认为null

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t come in......");
        //AA线程第一次进来为true 取反直接退出
        //BB线程进来为false 取反为true 将一直尝试获得锁  直至退出
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(thread.getName() + "\t myUnLock().....");
    }

    public static void main(String[] args) {
        SpinLockDemo lockDemo = new SpinLockDemo();

        new Thread(() -> {
            lockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lockDemo.myUnLock();
        }, "AA").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            lockDemo.myLock();
            lockDemo.myUnLock();
        }, "BB").start();
    }

}
