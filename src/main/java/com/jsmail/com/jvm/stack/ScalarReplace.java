package com.jsmail.com.jvm.stack;

/**
 * 标量替换测试
 * -Xmx100m -Xms100m -XX:+PrintGC
 * -XX:+DoEscapeAnalysis    (是否开启逃逸分析)
 * -XX:-EliminateAllocations    (是否开启标量替换)
 */
public class ScalarReplace {

    public static class User {
        public String name;
        public int id;
    }

    public static void alloc() {
        User u = new User();//未发生逃逸
        u.id = 1;
        u.name = "panju";
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为:" +(end - start) + " ms");
    }

}
