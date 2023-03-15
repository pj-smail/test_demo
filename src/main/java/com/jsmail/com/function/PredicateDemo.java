package com.jsmail.com.function;

import java.util.function.Predicate;

/**
 * @author: panju
 * @since: 2023/3/15 17:58
 * @description:
 *  1)Predicate是一个布尔值的函数。
 * 	2)Java Predicate是一个功能接口，属于java.util.function包。
 * 	3)Predicate的功能方法是test(T t)。
 * 	4)Predicate的其他方法是test、isEqual、and、or、negate和not。
 *
 */
public class PredicateDemo {

    public static void main(String[] args) {
        // Is username valid
        Predicate<String> isUserNameValid = u -> u != null && u.length() > 5 && u.length() < 10;
        System.out.println(isUserNameValid.test("Mahesh")); //true
    }

}
