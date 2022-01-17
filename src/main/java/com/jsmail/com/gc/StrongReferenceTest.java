package com.jsmail.com.gc;

/**
 * 强引用
 */
public class StrongReferenceTest {

    public static void main(String[] args) {
        StringBuffer str = new StringBuffer("Hello");
        StringBuffer str1 = str;//str和str1 同时指向new StringBuffer("Hello")

        str = null;//不会进行GC 因为str1 还有指向
        System.gc();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(str1);
    }

}
