package com.example.something.kotlin_test.learn

/**
 *  create by pan yi on 2021/7/15
 *  desc : 协变 逆变
 */
// out协变
interface Production<out T> {
    fun product(): T
}

// in 逆变
interface Consumer<in T> {
    fun consume(item: T)
}

// 不变
interface ProductionConsumer<T> {
    fun product(): T
    fun consume(item: T)
}

open class Food
open class FastFood : Food()
class Burger : FastFood()

class FoodStore : Production<Food> {
    override fun product(): Food {
        println("return production")
        return Food()
    }
}

class FastFoodStore : Production<FastFood> {
    override fun product(): FastFood {
        println("return production")
        return FastFood()
    }
}

class BurgerStore : Production<Burger> {
    override fun product(): Burger {
        println("return production")
        return Burger()
    }
}

class EveryBody : Consumer<Food> {
    override fun consume(item: Food) {
        println("eat food")
    }
}

class ModernPeople : Consumer<FastFood> {
    override fun consume(item: FastFood) {
        println("eat FastFood")
    }
}

class AmericanPeople : Consumer<Burger> {
    override fun consume(item: Burger) {
        println("eat Burger")
    }
}

fun main() {
    // 子类泛型对象可以赋值给父类泛型对象 ，用out
    val production1: Production<Food> = FoodStore()
    val production2: Production<Food> = FastFoodStore()

    val consumer1: Consumer<Burger> = EveryBody()
    val consumer2: Consumer<Burger> = ModernPeople()
    consumer2.consume(Burger())
    val consumer3: Consumer<Burger> = AmericanPeople()

}