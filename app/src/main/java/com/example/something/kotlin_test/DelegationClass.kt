package com.example.something.kotlin_test

import kotlin.reflect.KProperty

/**
 *  create by pan yi on 2020/10/22
 *  desc : 委托类测试
 */
class DelegationClass {
    companion object {
        @JvmStatic
        fun main(strings: Array<String>) {
//            SaveDB(Sql()).save()
//            SaveDB(GreenDao()).save()
            val list = ArrayList<String>()
            for (i in 1..10) {
                list.add("$i count")
            }
            val test = LogList({
                println("log")
            }, list).get(0)
            println(test)
            StringLazy("lazy")
        }
    }

    interface DB {
        fun save()
    }

    class Sql : DB {
        override fun save() {
            println("sql save")
        }
    }

    class GreenDao : DB {
        override fun save() {
            println("greenDao save")
        }

    }

    class SaveDB(db: DB) : DB by db

    class LogList(val log: () -> Unit, val list: MutableList<String>) :
        MutableList<String> by list {
        fun getAndLog(position: Int): String {
            println(size.toString())
            log()
            return get(position)
        }
    }

    class Owner {
        var text: String = "Hello"
    }

    class StringLazy(private var s: String = "") {
        operator fun getValue(thisRef: Owner, property: KProperty<*>): String {
            return s
        }

        operator fun setValue(thisRef: Owner, property: KProperty<*>, value: String) {
            s = value
        }

    }
}