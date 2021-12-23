package com.jsmail.com.lock;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable {

    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @SneakyThrows
    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "自己持有：" + lockA + " 尝试获取：" + lockB);
            TimeUnit.SECONDS.sleep(2);
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "自己持有：" + lockB + " 尝试获取：" + lockA);
            }
        }
    }

}

/**
 * 死锁是指两个或两个以上的进程在执行过程中，
 * 因争夺资源而造成的一种互相等待的现象，
 * 若无外力干涉那它们都将无法推进下去
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA, lockB), "AAA").start();
        new Thread(new HoldLockThread(lockB, lockA), "BBB").start();

        /**
         * linux    ps -ef/grep xXXX    ls -l
         * window下的java运行程序﹑也有类似ps的查看进程的命令，但是目前我们需要查看的只是java
         * jps = java ps                jps -l
         *
         * 查看死锁问题： 使用jps -l查看死锁进程号
         *             使用jstack 进程号 查看死锁
         */
    }

}
