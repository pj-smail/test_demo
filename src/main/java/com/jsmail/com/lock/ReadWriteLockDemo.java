package com.jsmail.com.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache { //资源类

    private volatile Map<String, Object> map = new HashMap();

    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        try {
            rwLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + "正在写" + key);
            try {
                TimeUnit.MICROSECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入完成");
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "正在读");
            try {
                TimeUnit.MICROSECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object resutl = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读完成" + resutl);
        } finally {
            rwLock.readLock().unlock();
        }
    }

}

/**
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行
 * 但是
 * 如果有一个线程想去写共享资源类，就不应该在有其他线程可以对资源类进行读或写
 * 小总结：
 *      读-读能共存
 *      读-写不能共存
 *      写-写不能共存
 *
 *      写操作： 原子+独占  整个过程必须是一个完整的统一体，中间不许被分割，被打断
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 0; i < 5; i++) {
            final int finali = i;
            new Thread(() -> {
                myCache.put(finali+"", finali+"");
            }, "线程" + i).start();
        }

        for (int i = 0; i < 5; i++) {
            final int finali = i;
            new Thread(() -> {
                myCache.get(finali+"");
            }, "线程" + i).start();
        }

    }

}
