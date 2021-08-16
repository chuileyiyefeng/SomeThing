package com.example.something.kotlin_test.learn;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/**
 * create by pan yi on 2021/8/6
 * desc :
 */
public class JavaInvokeKt {

    public int x = 12039803;

    private int hintPoint = 123234;

    public int getHintPoint() {
        return hintPoint;
    }

    public void setHintPoint(int hintPoint) {
        this.hintPoint = hintPoint;
    }

    @NotNull
    public String notNullString() {
        return "HELLO";
    }


    public String nullString() {
        return null;
    }

    @Nullable // 注解
    public String nullAbleString() {
        return null;
    }


    public static void main(String[] args) {
        System.out.println(JavaInvokeKT_ChildKt.makeKotlinName());

        JavaInvokeKotlinMethod method = new JavaInvokeKotlinMethod();
        System.out.println(method.spells);
        method.setInfo();

        // 没有注解前伴生对象的用法
//        JavaInvokeKotlinMethod.Companion.getName();
//        JavaInvokeKotlinMethod.Companion.getNameLength();

        // 有注解后伴生对象的用法
        System.out.println(JavaInvokeKotlinMethod.name);
        System.out.println(JavaInvokeKotlinMethod.getNameLength());
        try {
            // 强制在编译期进行处理
            new JavaInvokeKt().extendHandlerException();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            JavaInvokeKT_ChildKt.throwException();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // java用这个调用函数类型
        //Kotlin函数类型使用FunctionN这样的名字的接口来表示的，FunctionN中 的N代表值参数目。这
        //样的Function接口由23个，从Function0到Function22, 每-个FunctionN都包含- -个invoke函
        //数，专用于调用函数类型函数，所以，任何时候需要调- - 个函数类型，都用它调用invoke
        Function1<String, Unit> function1 = JavaInvokeKT_ChildKt.getTransfer();
        function1.invoke("test");
    }


    public void extendHandlerException() throws IOException {
        throw new IOException();
    }
}
