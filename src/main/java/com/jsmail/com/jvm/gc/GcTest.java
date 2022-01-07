package com.jsmail.com.jvm.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试MinorGC、 MajorGC、 FulLGC
 * -Xms9m -Xmx9m -XX:+PrintGCDetails
 */
public class GcTest {

    public static void main(String[] args) {
        int i = 0;
        try {
            List<String> list = new ArrayList<>();
            String a = "atguigu.com";
            while (true) {
                list.add(a);
                a = a + a;
                i++;
            }
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("遍历次数为" + i);
        }
    }

}
