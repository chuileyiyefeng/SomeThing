package com.example.something.generics;

/**
 * create by pan yi on 2020/11/17
 * desc : 水果盘接口
 */
public interface Plate<T> {
    public void set(T t);

    public T get();
}
