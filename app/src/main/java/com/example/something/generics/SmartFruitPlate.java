package com.example.something.generics;

import java.util.ArrayList;

/**
 * create by pan yi on 2020/11/17
 * desc :
 */
public class SmartFruitPlate<T> implements Plate<T> {
    private ArrayList<T> list = new ArrayList<T>();

    private  T t;
    @Override
    public void set(T t) {
        list.add(t);
    }

    @Override
    public T get() {
        if (list.size() > 0) {
            return list.get(list.size() - 1);
        }
        return null;
    }
}
