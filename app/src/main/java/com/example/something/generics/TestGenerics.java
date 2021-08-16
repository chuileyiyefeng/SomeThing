package com.example.something.generics;

import androidx.annotation.NonNull;

/**
 * create by pan yi on 2020/11/18
 * desc :
 */
public class TestGenerics {
    public static void main(String[] args) {
        noMethod();
        Generic generic = new Generic();
        Object o = generic.genericMethod("test1", "test2", 3, 4);
        System.out.println(o);
        James james=new James();
        james.getSmartPlate(new FruitPlateGen<Fruit>());
        james.getSmartPlate(new FruitPlateGen<Orange>());
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


        static class Person {
            @NonNull
            @Override
            public String toString() {
                return "person";
            }
        }

        // 泛型类使用泛型方法
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
