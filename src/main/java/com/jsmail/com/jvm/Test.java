package com.jsmail.com.jvm;

public class Test {

    private static int num = 10;

    static {
        number = 10;
        num = 20;
    }

    private static int number = 20;

    public static void main(String[] args) {
        System.out.println(number);
        System.out.println(num);
    }
}
