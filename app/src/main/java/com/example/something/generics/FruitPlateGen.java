package com.example.something.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * create by pan yi on 2020/11/20
 * desc :
 */
public class FruitPlateGen<Fruit> implements Plate<Fruit> {
    private List<Fruit> fruits = new ArrayList<>(6);

    @Override
    public void set(Fruit fruit) {
        fruits.add(fruit);
    }

    @Override
    public Fruit get() {
        int index = fruits.size() - 1;
        if(index >= 0) return fruits.get(index);
        return null;
    }
}
