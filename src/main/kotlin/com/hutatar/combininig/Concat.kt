package com.hutatar.combininig

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    val a = Concat()
    a.waiting()
}

class Concat {

    fun first() {
        val s1: Observable<String> = Observable.just("aaa", "bbb", "ccc")
        val s2: Observable<String> = Observable.just("ddd", "eee", "asd")

        Observable.concat(s1, s2).subscribe { println(it) }
    }

    fun waiting() {
        val s1: Observable<Long> = Observable
                .interval(1, TimeUnit.SECONDS)
                .take(5)
                .map { i -> i + 1 }

        val s2: Observable<Long> = Observable
                .interval(300, TimeUnit.MILLISECONDS)
                .take(5)
                .map { i -> i + 1 }

        Observable
                .concat(s1, s2)
                .subscribe { println(it) }

        sleep(10000)
    }

    fun sleep(millis: Long) {
        try {
            Thread.sleep(millis)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}