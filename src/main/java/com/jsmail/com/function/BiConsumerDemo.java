package com.jsmail.com.function;

import java.util.function.BiConsumer;

/**
 * @author: panju
 * @since: 2023/3/15 18:01
 * @description: BiConsumer是一个函数式接口，代表一个接受两个输入参数且不返回任何内容的操作符
 */
public class BiConsumerDemo {

    public static void main(String[] args) {
        BiConsumer<String, String> greeter = (firstname, lastname) -> System.out.println("Hello " + firstname + " " + lastname);
        greeter.accept("James", "Smith");
    }

}
