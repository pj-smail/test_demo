package com.jsmail.com.jvm.heap;

import java.util.ArrayList;
import java.util.Random;

/**
 * 使用jvisualvm 查看堆内存及GC情况
 */
public class HeapInstanceTest {

    byte[] buffer = new byte[new Random().nextInt(1024 * 200)];

    public static void main(String[] args) {
        ArrayList<HeapInstanceTest> objects = new ArrayList<>();
        while (true) {
            objects.add(new HeapInstanceTest());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
