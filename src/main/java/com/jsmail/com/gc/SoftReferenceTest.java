package com.jsmail.com.gc;

import java.lang.ref.SoftReference;

/**
 * -Xms10M -Xmx10M -XX:+PrintGCDetails
 */
public class SoftReferenceTest {

    public static class User {
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int id;
        private String name;

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        //创建对象，建立软引用
//        SoftReference<User> sf = new SoftReference<User>(new User(1, "panju"));
        //上面的一行代码，等价于如下的三行代码
        User u1 = new User(1, "panju");
        SoftReference<User> userSoftRef = new SoftReference<User>(u1);
        u1 = null;//取消强引用

        //从软引用中重新获得强引用对象
        System.out.println(userSoftRef.get());

        System.gc();

        System.out.println("After GC...");
        //垃圾回收之后获得软引用中的对象
        System.out.println(userSoftRef.get());

        try {
            //让系统认为内存资源紧张、不够，软引用被回收
            byte[] b = new byte[1024 * 7168 - 1024 * 880];
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            //再次从软引用中获取数据
            System.out.println(userSoftRef.get());
        }

    }

}
