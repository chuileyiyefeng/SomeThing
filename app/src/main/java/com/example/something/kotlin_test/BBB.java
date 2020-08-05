package com.example.something.kotlin_test;

public class BBB {
    public static void main(String[] args) {
        PrintKotlin pk = new PrintKotlin();
        TestBean testBean=new TestBean();
        testBean.setName("张三");
        testBean.setTime("");
        pk.validate(testBean);
    }
}
