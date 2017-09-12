package com.hutatar.stuff

fun main(args: Array<String>) {
    val a = Algebraic()
//    a.first()
//    a.second()
    a.test()
}

class Algebraic {

    fun first() {
        for (i in 1..10) {
            val s: String? = if (Math.random() < 0.5) "Less" else null
            println("length is ${s?.length ?: -1}")
        }
    }

    //destructured
    fun second() {
        val a = Maybe.just(1)
        val (i) = a
        println(a)
        println((i))
        println(i)
    }

    fun test() {
        val lookup = fun (username: String): Maybe<User> {
            return if (username == "test")  Maybe.just(User("test")) else  Maybe.none
        }
        val rec1 = lookup("test")
        val rec2 = lookup("test2")

        val printer = fun(usr: Maybe<User>) {
            when(usr) {
                is Maybe.none -> println("none")
                is Maybe.just<User> -> println("found")
            }
        }
        printer(rec1)
        printer(rec2)
    }

}


data class User(val name: String)

sealed class Maybe<out T> {
    object none: Maybe<Nothing>()
    data class just<T>(val t: T): Maybe<T>()
}