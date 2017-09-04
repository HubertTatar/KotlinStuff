package com.hutatar.combininig

import io.reactivex.Observable

fun main(args: Array<String>) {
    val f = FlatMap()
      f.second()
//    f.first()
}

class FlatMap {
    fun first() {
        val a1 = Observable.just("raz", "dwa", "trzy")

        Observable.fromArray(a1).subscribe { println(it) }

        a1.flatMap { i -> Observable.fromArray(i.split("")) }
                .subscribe { println(it) }
    }

    fun second() {
        val a: Observable<String> = Observable.just("532342/324234/9792/ASDwsd", "adsasd/123123/asdasd/213")
        a
                .flatMap { i -> Observable.fromArray(i.split("/")) }
                .doOnNext { println("next $it") }
                .map { s -> s.toString() }
                .filter { s -> s.matches(Regex("[0-9]+"))}
                .doOnNext { println("next $it") }
                .map(Integer::valueOf)
                .subscribe { println(it) }
    }

    fun third() {
        val a = Observable.just("alpha", "beta", "gamma")
    }
}