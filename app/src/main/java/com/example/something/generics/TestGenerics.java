package com.example.something.generics;

import androidx.annotation.NonNull;

/**
 * create by pan yi on 2020/11/18
 * desc :
 */
public class TestGenerics {
    public static void main(String[] args) {
//        noMethod();
//        hasMethod();
//        ImplMultipleGenerics<Integer, String> impl = new ImplMultipleGenerics<>(100, "money");
//        System.out.println(impl.getKey() + " " + impl.getValue());
//        ImplGeneral general = new ImplGeneral();
//        general.next();
//        Generic generic = new Generic();
//        generic.genericMethod("xixi", "haha", "xixix");
//        System.out.println(generic.genericMethod("xixi", "haha", "hehe"));
//        Apple apple = new Apple();
//        Generic.Person person = new Generic.Person();
//        Generic.ShowClass<Fruit> fruitShowClass = new Generic.ShowClass<>();


//        fruitShowClass.show1(apple);   //可以放入 apple，因为 apple 是 fruit 的子类
//        fruitShowClass.show1(person);  //编译器会报错，因为 ShowClass<Fruit> 已经限定类型

//        fruitShowClass.show2(apple); //可以放入，泛型方法 <T> 和泛型类中的 <T> 不是同一条 T，可以是任何非基本类型
//        fruitShowClass.show2(person); //可以放入，泛型方法 <T> 和泛型类中的 <T> 不是同一条 T，可以是任何非基本类型
//
//        fruitShowClass.show3(apple); //可以放入，泛型方法 <E> 可以是任何非基本类型
//        fruitShowClass.show3(person); //可以放入，泛型方法 <E> 可以是任何非基本类型
//        BoundClass<String> boundClass=new BoundClass<>();
//        boundClass.setT("apple");
//        System.out.println(boundClass.min("orange"));
//        FruitPlate plate=new FruitPlate();
//        plate.set(new Apple());
//        plate.set(new Orange());
//        James james=new James();
//        james.getSmartPlate(new FruitPlateGen<>());
//        james.getSmartPlate(new FruitPlateGen<Orange>());

//        FruitPlateGen<? extends Fruit> fruitPlateGen=new FruitPlateGen<>();
//        Fruit fruit=fruitPlateGen.get();
//        上限通配符无法 set 数据，但是，可以 get 数据且只能 get 到其上限 Fruit，所以，上限通配符可以安全的访问数据。

        FruitPlateGen<? super Apple> fruitPlateGen1 = new FruitPlateGen<>();
        Apple apple = new Apple();
        fruitPlateGen1.set(apple);
        Object o = fruitPlateGen1.get();
//        下限通配符可以且只能 set 其下限 Apple，也可以 get 数据，但只能用 Object 接收(因为Object是所有类型的父类，这是一个特例)，所以，下限通配符可以安全的写入数据。

//        Pair<char,int> pair=new Pair<>("test",2);// 无法实例化具有基本类型的泛型类型
    }

    // 没有使用泛型
    private static void noMethod() {
        James james = new James();
        Lucy lucy = new Lucy();
        FruitPlate plate = james.getPlate();
        james.addFruit(plate, new Orange());
        lucy.eat((Orange) plate.get());
    }

    // 使用泛型类
    private static void hasMethod() {
        James james = new James();
        Lucy lucy = new Lucy();
        SmartFruitPlate<Orange> plate = james.getSmartPlate();
        james.add(plate, new Orange());
        lucy.eat(plate.get());
    }

    // 类里面的泛型方法
    static class Generic {
        public <T> T genericMethod(T... t) {
            return t[t.length / 2];
        }

        static class TestFruit {
            @NonNull
            @Override
            public String toString() {
                return "fruit";
            }
        }

        static class Apple extends TestFruit {
            @NonNull
            @Override
            public String toString() {
                return "Apple";
            }
        }

        static class Person {
            @NonNull
            @Override
            public String toString() {
                return "person";
            }
        }

        static class ShowClass<T> {
            public void show1(T t) {
                System.out.println("show1");
            }

            public <T> void show2(T t) {
                System.out.println("show2");
            }

            public <E> void show3(E e) {
                System.out.println("show3");
            }
        }


    }


    // 泛型接口
    interface MultipleGenerics<K, V> {
        public K getKey();

        public V getValue();
    }

    interface General<T> {
        public T next();
    }

    static class ImplGeneral implements General<String> {

        @Override
        public String next() {
            System.out.println("泛型实现");
            return "next";
        }
    }

    //  实现泛型接口类
    static class ImplMultipleGenerics<K, V> implements MultipleGenerics<K, V> {
        private K key;
        private V value;

        public ImplMultipleGenerics(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }
    }

    public static class BoundClass<T extends Comparable> {
        private T t;

        public void setT(T t) {
            this.t = t;
        }

        public T min(T outter) {
            if (this.t.compareTo(outter) > 0)
                return outter;
            else
                return this.t;
        }
    }

    static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }


    class BaseResponse2<T> {
        private T data;

        public BaseResponse2(T data) {
            this.data = data;
        }

        public T covert() {
            return data;
        }
    }


}
