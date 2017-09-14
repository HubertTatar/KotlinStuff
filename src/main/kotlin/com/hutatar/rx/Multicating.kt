package com.hutatar.rx

import io.reactivex.Observable
import io.reactivex.observables.ConnectableObservable
import java.util.concurrent.ThreadLocalRandom



fun main(args: Array<String>) {
    val a = Multicating()
//    a.cold1()
//    a.hot1()
//    a.cold2()
//    a.multicastAfterRangeBeforeMap()
//    a.mulitcaseOnePath()
    a.multicastExample()
}

class Multicating {
    fun cold1() {
        val ints: Observable<Int> = Observable.range(1,3)
        ints.subscribe { println(it) }
        ints.subscribe { println(it) }
    }

    fun hot1() {
        val ints: ConnectableObservable<Int> = Observable.range(1,3).publish()

        ints.subscribe { println(it) }
        ints.subscribe { println(it) }

        ints.connect()
    }

    //double randoms
    fun cold2() {
        val ints: Observable<Int> = Observable
                .range(1,3)
                .map { i -> randomInt() }
        ints.subscribe { println(it) }
        ints.subscribe { println(it) }
    }

    fun randomInt(): Int {
        return ThreadLocalRandom.current().nextInt(100000)
    }

    fun multicastAfterRangeBeforeMap() {
        val ints: ConnectableObservable<Int> = Observable.range(1,3).publish()
        val mapped: Observable<Int> = ints.map { i -> randomInt() }

        mapped.subscribe { println(it) }
        mapped.subscribe { println(it) }

        ints.connect()
    }

    fun mulitcaseOnePath() {
        val ints: ConnectableObservable<Int> = Observable
                .range(1,3)
                .map { i-> randomInt() }
                .publish()

        ints.subscribe { println(it) }
        ints.subscribe { println(it) }

        ints.connect()
    }

    fun multicastExample() {
        val ints: ConnectableObservable<Int> = Observable
                .range(1,3)
                .map { i-> randomInt() }
                .publish()

        ints.subscribe { println(it) }
        ints
                .reduce { t1: Int, t2: Int ->  t1 + t2 }
                .subscribe { println(it) }

        ints.connect()
    }
}