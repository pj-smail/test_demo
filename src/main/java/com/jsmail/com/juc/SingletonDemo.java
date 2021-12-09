package com.jsmail.com.juc;

public class SingletonDemo {

    private static volatile SingletonDemo instance = null;//volatile 保证可见性和禁止指令重排

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "\t 初始化Singleton............");
    }

    public static SingletonDemo getInstance() {
        /**
         * 如上代码段中的注释：假设线程一执行到instance = new Singleton()这句，这里看起来是一句话，
         * 但实际上其被编译后在JVM执行的对应会变代码就发现，这句话被编译成8条汇编指令，大致做了三件事情：
         * 1）给instance实例分配内存；
         * 2）初始化instance的构造器；
         * 3）将instance对象指向分配的内存空间（注意到这步时instance就非null了）
         */
        if(instance == null) {
            synchronized (SingletonDemo.class) {
                if(instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            }).start();
        }
    }

}
