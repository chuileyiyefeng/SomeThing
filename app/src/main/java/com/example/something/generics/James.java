package com.example.something.generics;

/**
 * create by pan yi on 2020/11/17
 * desc : 这个人有获取水果盘的能力
 */
public class James extends Person {
    public FruitPlate getPlate() {
        return new FruitPlate();
    }

    public SmartFruitPlate getSmartPlate() {
        return new SmartFruitPlate();
    }
    public SmartFruitPlate getSmartPlate(FruitPlateGen<? extends Fruit> plate) {
        return new SmartFruitPlate();
    }

    public void addFruit(FruitPlate fruitPlate, Fruit fruit) {
        fruitPlate.set(fruit);
    }

    public void add(SmartFruitPlate<Orange> smartFruitPlate, Orange orange) {
        smartFruitPlate.set(orange);
    }
}
