package com.example.something.reflection;

/**
 * create by pan yi on 2020/11/24
 * desc :
 */
public class Reflection {
    private String outContent="这是反射本来的内容";
    void testReflect() {
        System.out.println("这是反射类的内部方法");
    }

    private String getOutContent() {
        return outContent;
    }
}
