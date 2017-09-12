package com.hutatar.operators

import io.reactivex.Observable

fun main(args: Array<String>) {
    val e = Errors()
    e.donOns()
}

class Errors {

    fun first() {
        Observable
                .just(1,2,3,0,5)
                .map { i -> 10 / i }
                .subscribe({ println(it) }, { e -> println(e.message) })
    }

    fun onErrorReturn() {
        Observable
                .just(1,2,3,0,5)
                .map { i -> 10 / i }
                .onErrorReturn { -1 }
                .subscribe({ println(it) }, { e -> println(e.message) })
    }

    fun handle() {
        Observable
                .just(1,2,3,0,5)
                .map { i ->
                    try {
                        10 / i
                    } catch (e: Exception) {
                        0
                    }
                }
                .subscribe({ println(it) }, { e -> println(e.message) })
    }

    fun onErrorResumeNext() {
        Observable
                .just(1,2,3,0,5)
                .map { i -> 10 / i }
                .onErrorResumeNext(Observable.just(1,2))
                .subscribe({ println(it) }, { e -> println(e.message) })
    }

    fun onErrorResumeNext2() {
        Observable
                .just(1,2,3,0,5)
                .map { i -> 10 / i }
                .onErrorResumeNext {e: Throwable -> Observable.just(1) }
                .subscribe({ println(it) }, { e -> println(e.message) })
    }

    fun retry() {
        Observable
                .just(1,2,3,0,5)
                .map { i -> 10 / i }
                .retry(2)
                .subscribe({ println(it) }, { e -> println(e.message) })
    }

    fun donOns() {
        Observable
                .just(1,2,3,0,5)
                .doOnNext{println("next " + it)}
                .map { i -> 10 / i }
                .retry(2)
                .doOnError{ e -> println(e) }
                .doOnComplete { println("completed") }
                .subscribe({ println(it) }, { e -> println(e.message) })
    }
}