package com.hutatar.operators

import io.reactivex.Observable

fun main(args: Array<String>) {
    val m = Mapping()
    m.mapping()

    readLine()
}

class Mapping {


    fun mapping() {
        Observable
                .just("raz", "dwa", "trzy")
                .map{ s -> s.length }
                .subscribe{ println(it) }
    }
}

interface A

open class B : A {
    open var a: String = "a"
}

class C : B() {
    override var a: String = "b"
}