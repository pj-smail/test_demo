package com.jsmail.com.stringtable;

import org.junit.Test;

public class StringAddTest {

    @Test
    public void test1() {
        String s1 = "a" + "b" + "c";//编译期优化 等同于"abc"
        String s2 = "abc";//"abc"一定是放在字符串常量池中，将此地址赋给s2
        /**
         * 最终.java编译成.cLass,再执行.class s
         * String s1 = "abc";
         * String s2 = "abc";
         */
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
    }

    /**
     * 1.常量与常量的拼接结果在常量池，原理是编译期优化
     * 2.常量池中不会存在相同内容的常量。
     * 3.只要其中有一个是变量，结果就在堆中(相当于新new 的对象)。变量拼接的原理是StringBuilder
     * 4.如果拼接的结果调用intern()方法，则主动将常量池中还没有的字符串对象放入池中，并返回此对象地址。
     */
    @Test
    public void test2() {
        String s1 = "javaEE";
        String s2 = "hadoop" ;
        String s3 = "javaEEhadoop";
        String s4 = "javaEE" + "hadoop";//编译期优化 等同于"javaEEhadoop"
        //如果拼接符号的前后出现了变量，则相当于在堆空间中new String();具体的内容为拼接的结果。
        String s5 = s1 + "hadoop" ;
        String s6 = "javaEE" + s2;
        String s7 = s1 + s2;
        System.out.println(s3 == s4);//
        System.out.println(s3 == s5);
        System.out.println(s3 == s6);
        System.out.println(s3 == s7);
        System.out.println(s5 == s6);
        System.out.println(s5 == s7);
        System.out.println(s6 == s7);

        //intern():判断字符串常量池中是否存在javaEEhadoop值，如果存在，则返回常量池中javaEEhadoop的地址;
        //如果字符串常量池中不存在javaEEhadoop,则在常量池中加载一份javaEEhadoop，并返回此对象的地址。
        String s8 = s6.intern();
        System.out.println(s3 == s8);
    }

    @Test
    public void test3() {
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        /**
         * 如下的s1 + s2的执行细节:
         * 1、stringBuilder s = new stringBuilder();
         * 2、s.append("a")
         * 3、s.append("b")
         * 4、s.toString() --> 约等于new String("ab")
         *
         * 补充：在jdk5.0之后使用的是StringBuilder,在jds5.0之前使用的是StringBuffer
         */
        String s4 = s1 + s2;
        System.out.println(s3 == s4);
    }

    /**
     * 1、字符串拼接操作不一定使用的是StringBuilder!
     * 如果拼接符号左右两边都是字符串常量或常量引用，则仍然使用编译期优化，即非StringBuilder的方式。
     * 2、针对于final修饰类、方法、基本数据类型、引用数据类型的量的结构时，能使用上finaL的时候建议使用上。
     */
    @Test
    public void test4() {
        final String s1 = "a";
        final String s2 = "b";
        String s3 = "ab";
        String s4 = s1 + s2;
        System.out.println(s3 = s4);
    }

    /**
     * 体会执行效率: 通过stringBuilder的append()的方式添加字符串的效率要远高于使用string的字符串拼接方式!
     * 详情: 1、StringBuilder的append()的方式:自始至终中只创建过一个StringBuilder的对象
     *          使用string的字符串拼接方式:创建过多个StringBuilder和String的对象
     *      2、使用String的字符串拼接方式:内存中由于创建了较多的StringBuilder和String的对象，内存占用更大;如果进行GC,需要花费更多的时间
     *
     * 改进的空间:   在实际开发中，如果基本确定要前前后后添加的字符串长度不高于某个限定值highLevel的情况下,建议使用如下构造器
     *              StringBuilder s = new StringBuilder(highLevel); // new Char[highLevel]
     */
    @Test
    public void test5() {
        long start = System.currentTimeMillis();

//        method1(100000);
        method2(100000);

        long end = System.currentTimeMillis();

        System.out.println("花费的时间为:" + (end - start));
    }

    public void method1(int highLevel) {
        String src = "";
        for(int i = 0;i < highLevel;i++){
            src = src + "a";//每次循环都会创建一个StringBuilder、String
        }
//        System.out.println(src);
    }

    public void method2(int highLevel) {
        //只需要创建一个StringBuilder
        StringBuilder src = new StringBuilder();
        for(int i = 0; i < highLevel; i++) {
            src.append( "a");
        }
//        System.out.println(src);
    }

}
