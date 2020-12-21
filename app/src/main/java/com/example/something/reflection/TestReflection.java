package com.example.something.reflection;

import java.lang.reflect.Method;

/**
 * create by pan yi on 2020/11/24
 * desc :
 */
public class TestReflection {
    public static void main(String[] args) {
        try {
            Class<?> testClass=Class.forName("com.example.something.reflection.Reflection");
            Object reflection=testClass.newInstance();
            Method reflect=testClass.getDeclaredMethod("getOutContent");
            reflect.setAccessible(true);
           testClass.getField("");
            String s= (String) reflect.invoke(reflection);
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
