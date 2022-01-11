package com.jsmail.com.stringtable;

import org.junit.Test;

/**
 * String不可变性
 */
public class StringTest {

    @Test
    public void test1() {
        String s1 = "abc";
        String s2 = "abc";//"abc"存储在局部变量表中
        s1 = "hello";

        System.out.println(s1 == s2);
        System.out.println(s1);
        System.out.println(s2);
    }

    @Test
    public void test2() {
        String s1 = "abc";
        String s2 = "abc";
        s2 += "def";

        System.out.println(s1);
        System.out.println(s2);
    }

    @Test
    public void test3() {
        String s1 = "abc";
        String s2 = s1.replace('a', 'm');
        System.out.println(s1);
        System.out.println(s2);
    }

    @Test
    public void test4() {
        System.out.println();
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        System.out.println("4");
        System.out.println("5");
        //----如下的"1"到"5"不会再次加载
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        System.out.println("4");
        System.out.println("5");
    }

}
