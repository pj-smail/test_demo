package com.jsmail.com.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 队列 先进先出
 *
 * 阻塞队列
 * 1、阻塞队列有没有好的一面
 * 2、不得不阻塞，你如何管理
 *
 * 为什么用，有什么好处
 * 在多线程领域:所谓阻塞，在某些情况下会挂起线程(即阻塞），一旦条件满足，被挂起的线程又会自动被唤醒
 * 为什么需要BlockingQueue
 * 好处是我们不需要关心什么时候需要阻塞线程，什么时候需要唤醒线程，因为这一切BlockingQueue都给你一手包办了
 * 在concurrent包发布以前，在多线程环境下，我们每个程序员都必须去自己控制这些细节，尤其还要兼顾效率和线程安全，而这会给我们的程序带来不小的复杂度。
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws Exception {
        /**
         * 抛出异常
         * 当阻塞队列满时，再往队列里add插入元素会抛IllegalStateException: Queue full
         * 当阻塞队列空时，再往队列里remove移除元素会抛NoSuchElementException
         * 特殊值
         * 插入方法，成功ture失败false
         * 移除方法，成功返回出队列的元素，队列里面没有就返回null
         * 一直阻塞
         * 当阻塞队列满时，生产都线程继续往队列里put元素，队列会一直阻塞生产线程直到put数据or响应中断退出。
         * 当阻塞队列空时，消费者线程试图从队列里take元素，队列会一直阻塞消费者线程直到队列可用。
         * 超时退出
         * 当阻塞队列满时，队列会阻塞生产者线程一定时间，超过后限时后生产者线程会退出
         */
        //---------------------------------------------------抛异常
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        /*System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
//        队列满抛异常 java.lang.IllegalStateException
//        System.out.println(blockingQueue.add("x"));

        //检查队列空不空  対首元素
        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //队列空再移除抛异常 java.util.NoSuchElementException
        System.out.println(blockingQueue.remove());*/
        //---------------------------------------------------

        //--------------------------------------------------特殊值 返回布尔类型值
        /*System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("x"));

        System.out.println(blockingQueue.peek());

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());*/
        //--------------------------------------------------

        //-------------------------------------------------- put 当队列满时  会一直堵塞
        /*blockingQueue.put("a");
        blockingQueue.put("a");
        blockingQueue.put("a");
        System.out.println("===================================");
//        blockingQueue.put("a");

        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
//        blockingQueue.take();*/

        //--------------------------------------------------
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));



    }

}
