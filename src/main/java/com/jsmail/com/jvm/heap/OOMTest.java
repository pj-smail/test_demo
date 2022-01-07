package com.jsmail.com.jvm.heap;

import java.util.ArrayList;
import java.util.Random;

/**
 * -Xms600M -Xmx600M
 * java.lang.OutOfMemoryError: Java heap space
 */
public class OOMTest {

    public static void main(String[] args) {
        ArrayList<Picture> list = new ArrayList<>();
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new Picture(new Random().nextInt(1024 * 1024)));
        }
    }

}

class Picture {
    private byte[] pixes;

    public Picture(int length) {
        this.pixes = new byte[length];
    }
}
