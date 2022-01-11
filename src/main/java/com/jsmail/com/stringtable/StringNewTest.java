package com.jsmail.com.stringtable;

public class StringNewTest {

    /**
     * 1、new String("ab")会创建几个对象？
     * 看字节码是两个。一个对象时：new关键字在堆空间创建的。另一个对象是:字符串常量池中的对象"ab"。字节码指令: ldc
     * 2、new String("a") + new String("b")呢？变量拼接操作都会创建一个StringBuilder。
     * 对象1:new StringBuilder()
     * 对象2:new string( "a")
     * 对象3:常量池中的"a"
     * 对象4:new string( "b")
     * 对象5:常量池中的"b"
     * 深入剖析：StringBuilder的toString():
     * 对象6：new String("ab")
     * 强调一下， toString()的调用，在字符串常量池中，没有生成"ab"
     * @param args
     */
    public static void main(String[] args) {
//        String str = new String("ab");

        String str = new String("a") + new String("b");
    }

}
