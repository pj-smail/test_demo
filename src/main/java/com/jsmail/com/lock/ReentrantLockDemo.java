package com.jsmail.com.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            try {
                phone.sendSms();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                phone.sendSms();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread1 = new Thread(phone, "t3");
        thread1.start();

        Thread thread2 = new Thread(phone, "t4");
        thread2.start();
    }
}

class Phone implements Runnable {
    public synchronized void sendSms() throws Exception {
        System.out.println(Thread.currentThread().getId() + "\t invoked sendSms");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println(Thread.currentThread().getId() + "\t@########## invoked sendEmail");
    }

    @Override
    public void run() {
        get();
    }

    Lock lock = new ReentrantLock();

    public void get() {
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "\t get()");
            set();
        } finally {
            lock.unlock();
        }
    }

    public void set() {
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "\t ##############set()");
        } finally {
            lock.unlock();
        }
    }

}
