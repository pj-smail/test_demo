package com.jsmail.com.thread.pool;

import java.util.concurrent.*;

/**
 * 当任务被提交到线程池，会先判断当前线程数量是否小于corePoolSize，如果小于则创建线程来执行提交的任务，
 * 否则将任务放入workQueue队列，如果workQueue满了，则判断当前线程数量是否小于maximumPoolSize,
 * 如果小于则创建线程执行任务，否则就会调用handler，以表示线程池拒绝接收任务。
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());//四大拒绝策略
        try {
            for (int i = 1; i <= 8; i++) {
                //模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程。
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private static void threadPoolInit() {
        //        System.out.println(Runtime.getRuntime().availableProcessors());//获取CPU核数

        //threadPool相当于一个营业厅，有5个营业窗口

        /**
         * 注意：实际开发中不允许使用Executors来创建，需要自定义线程池
         */
        ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5个处理线程
//        threadPool = Executors.newSingleThreadExecutor();//一池一个处理线程
        threadPool = Executors.newCachedThreadPool();//一池N个处理线程, 一个线程处理不过来时调用多个线程处理
        try {
            for (int i = 1; i <= 10; i++) {
                //模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程。
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
                TimeUnit.MICROSECONDS.sleep(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

}
