package com.jsmail.com.juc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CasDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        AtomicStampedReference stampedReference = new AtomicStampedReference(100, 1);

        atomicInteger.compareAndSet(5, 2019);//比较并且修改
        atomicInteger.compareAndSet(5, 2021);

        System.out.println(atomicInteger.get());

        User z3 = new User("z3", 20);
        User li4 = new User("li4", 22);
        AtomicReference<User> atomicReference = new AtomicReference<>();//对象引用
        atomicReference.set(z3);
        atomicReference.compareAndSet(z3, li4);
        atomicReference.compareAndSet(z3, li4);

        System.out.println(atomicReference.get().toString());

        //ABA问题解决
        new ArrayList<>().add(1);

    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class User {
    private String name;
    private int age;
}
