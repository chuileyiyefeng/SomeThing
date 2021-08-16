package com.example.something.kotlin_test.learn

/**
 *  create by pan yi on 2021/7/14
 *  desc :泛型测试
 */
fun main() {
    val boy = Magic(Boy("张三", 20))
    val dog = Magic(Dog("20斤"))
    boy.valiable = true
    boy.fetch()?.run {
//        println(this.name)
    }
    val man = boy.fetch {
        Man(it.name, it.age.plus(10))
    }
//    println(man?.age)

    val human = Magic2(Man("20", 30))


    val human2 = Magic3(
        Man("20", 30), Man("20", 30), Man("20", 30), Man("20", 30)
    )
    human2.valiable = true
    val manFetch = human2.fetch(1) {
        Human(it.age)
    }
    val man2 = human2[1]
//    println(manFetch?.age)
//    println(man2?.name)

    val humanReified: Magic4<Man> = Magic4()
    val reified = humanReified.randomOrBackup {
        Man("boy reified", 8)
    }
    println(reified)

}

private open class Human(val age: Int) {

}

private class Man(val name: String, age: Int) : Human(age) {
    override fun toString(): String {
        return "Man(name='$name', age=$age)"
    }
}

private class Boy(val name: String, age: Int) : Human(age) {
    override fun toString(): String {
        return "Boy(name='$name', age=$age)"
    }
}

private class Dog(weight: String) {

}

private class Magic<T>(item: T) {
    private var subject: T = item
    var valiable = false
    fun fetch(): T? {
        return subject.takeIf { valiable }
    }

    fun <R> fetch(operator: (T) -> R): R? {
        return operator(subject).takeIf { valiable }
    }
}

private class Magic2<T : Human>(item: T) {
    private var subject: T = item
    var valiable = false
    fun fetch(): T? {
        return subject.takeIf { valiable }
    }

    fun <R> fetch(operator: (T) -> R): R? {
        return operator(subject).takeIf { valiable }
    }
}

private class Magic3<T : Human>(vararg item: T) {
    private var subject: Array<out T> = item
    var valiable = false
    fun fetch(index: Int): T? {
        return subject[index].takeIf { valiable }
    }

    fun <R> fetch(index: Int, operator: (T) -> R): R? {
        return operator(subject[index]).takeIf { valiable }
    }

    operator fun get(index: Int): T? = subject[index].takeIf { valiable }
}


private class Magic4<T : Human> {
    inline fun <reified S> randomOrBackup(backup: () -> S): S {
        val items: List<Human> = listOf(
            Man("man", 20), Boy("boy", 10)
        )
        val item = items.shuffled().first()
        return if (item is S) {
            item
        } else {
            backup()
        }
    }
}