package com.jsmail.com.jvm.stack;

import java.util.Date;

/**
 * 演示栈中的溢出情况: StackOverflowError
 *
 * 默认count为：11419
 * 设置栈的大小: -Xss256k
 * count为：2453
 *
 */
public class StackErrorTest {

    private static int count = 1;

    public static void main(String[] args) {
        System.out.println(count);
        count++;
        main(args);
    }

    public static void testStatic() {
        int count = 10;
        Date date = new Date();
        System.out.println(count);
    }

}
