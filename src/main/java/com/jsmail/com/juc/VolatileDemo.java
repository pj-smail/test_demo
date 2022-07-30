package com.jsmail.com.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData {

    volatile int number = 0;

    AtomicInteger num = new AtomicInteger();

    public void addTo60() {
        System.out.println();
        this.number = 60;
        this.num.set(50);
    }

    public void addPlusPlus() {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addMyAtomic() {
        atomicInteger.getAndIncrement();
    }

}

public class VolatileDemo {

    public static void main(String[] args){ //这个线程是用来读取flag的值的
//        seeOkByVolatile();
//        test();
        atomicByVolatile();
    }

    //验证可见性
    public static void seeOkByVolatile() {
        MyData myData = new MyData();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "come in.....");
            try {
                TimeUnit.SECONDS.sleep(3);
                myData.addTo60();
                System.out.println("更新number值为" + myData.number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程1").start();

        while (myData.number == 0) {

        }

        System.out.println("主线程number值被修改" + myData.number);
    }

    //验证原子性
    public static void atomicByVolatile() {
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myData.addPlusPlus();
                    myData.addMyAtomic();
                }
            }, String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "int type, final number value: " + myData.number);
        System.out.println(Thread.currentThread().getName() + "AtomicInteger type, final number value: " + myData.atomicInteger);
    }

//    public static void test() {
//        int i = 0;
//        int b = i++;
//        int c = ++i;
//        System.out.println(b);
//        System.out.println(c);
//    }


}

