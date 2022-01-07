package com.jsmail.com.jvm.heap;

import java.util.concurrent.TimeUnit;

/**
 * -Xms10m -Xmx10m
 * 1.设置堆空间大小的参数
 * -Xms用来设置堆空间（年轻代+老年代）的初始内存大小
 * -×是jvm的运行参数
 * ms 是memory start
 * -Xmx用来设置堆空间（年轻代+老年代）的最大内存大小
 * <p>
 * 2．默认堆空间的大小
 * 初始内存大小:物理电脑内存大小/ 64
 * 最大内存大小:物理电脑内存大小/ 4
 * <p>
 * 3．手动设置:-Xms600m -xmx600m
 * 开发中建议将初始堆内存和最大的堆内存设置成相同的值。
 * <p>
 * 4.查看设置的参数: 方式一: jps  / jstat -gc 进程id
 *                方式二: -XX:+PrintGCDetails
 *
 * 使用jvisualvm查看堆信息，一个java实例对应一个堆内存
 */
public class HeapDemo {

    public static void main(String[] args) throws InterruptedException {
//        System.out.println("start...");
//        try {
//            Thread.sleep(10000000000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("end...");

        //返回Java虚拟机中的堆内存总量
        long initialMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        //返回Java虚拟机试图使用的最大堆内存量
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        System.out.println("-xms : " + initialMemory + "M");
        System.out.println("-Xmx : " + maxMemory + "M");

//        System.out.println("系统内存大小为: " + initialMemory * 64.0 / 1024 + "G");
//        System.out.println("系统内存大小为: " + maxMemory * 4.0 / 1024 + "G");

        Thread.sleep(1000000000L);
    }

}
