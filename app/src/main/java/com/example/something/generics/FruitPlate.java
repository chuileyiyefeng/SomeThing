package com.example.something.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * create by pan yi on 2020/11/17
 * desc : 水果盘类，实现水果盘接口
 */
public class FruitPlate implements Plate {

    private ArrayList items = new ArrayList();

    @Override
    public void set(Object o) {
        items.add(o);
    }

    @Override
    public Object get() {
        if (items.size()>0) {
            return  items.get(items.size()-1);
        }
        return null;
    }
}
