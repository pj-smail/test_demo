package com.jsmail.com.lock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TestTransferValue {

    public void changeValue1(int age) {
        age = 30;
    }

    public void changeValue2(Person person) {
        person.setName("XXX");
    }

    public void changeValue3(String name) {
        name = "aaa";
    }

    public static void main(String[] args) {
        TestTransferValue testTransferValue = new TestTransferValue();
        int age = 20;
        testTransferValue.changeValue1(age);
        System.out.println("age: " + age);

        Person person = new Person(1, "zs");
        testTransferValue.changeValue2(person);
        System.out.println("person: " + person.getName());

        String name = "bbb";
        testTransferValue.changeValue3(name);
        System.out.println("name: " + name);
    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Person{
    private int id;
    private String name;
}
