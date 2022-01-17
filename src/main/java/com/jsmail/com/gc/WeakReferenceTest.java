package com.jsmail.com.gc;

import java.lang.ref.WeakReference;

/**
 * -Xms10M -Xmx10M -XX:+PrintGCDetails
 */
public class WeakReferenceTest {

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
        //创建对象，建立弱引用
//        SoftReference<User> sf = new SoftReference<User>(new User(1, "panju"));
        //上面的一行代码，等价于如下的三行代码
        User u1 = new User(1, "panju");
        WeakReference<User> userSoftRef = new WeakReference<User>(u1);
        u1 = null;//取消强引用

        //从弱引用中重新获得强引用对象
        System.out.println(userSoftRef.get());

        System.gc();
        //不管当前内存空间足够与否，都会回收它的内存
        System.out.println("After GC...");
        //垃圾回收之后获得弱引用中的对象
        System.out.println(userSoftRef.get());

    }

}
