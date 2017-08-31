package com.hutatar.operators

import io.reactivex.Observable
import java.util.concurrent.TimeUnit


fun main(args: Array<String>) {
    val filters = Filters()
    filters.distinctUntilChaned()

    readLine()
}

class Filters {

    fun distinctUntilChaned() {
        Observable
                .just(1,1,1,2,2,3,3,4)
                .distinctUntilChanged()
                .subscribe{print(it)}
    }

    fun distinct() {
        Observable
                .just(1,2,3,4,5,6,1,2,3)
                .distinct()
                .subscribe { println(it) }
    }

    fun takeWhile() {
        Observable
                .interval(100, TimeUnit.MILLISECONDS)
                .skipWhile { i -> i < 5 }
                .takeWhile { i -> i < 10 }
                .subscribe { println(it) }
    }

    fun skipOP() {
        Observable
                .interval(200, TimeUnit.MILLISECONDS)
                .skip(5)
                .subscribe{println(it)}
    }

    fun takeInterval() {
        Observable.interval(200, TimeUnit.MILLISECONDS)
                .take(2, TimeUnit.SECONDS)
                .subscribe { s ->  println("ras" + s) }
    }

    fun take() {
        val obs = Observable.just("Alpha", "Beta", "Delta", "Epsilon")
        obs
                .take(2)
                .subscribe {  println(it) }
    }

    fun filter() {
        val obs = Observable.just("Alpha", "Beta", "Delta", "Epsilon")
        obs
                .filter { s -> s.length > 5 }
                .subscribe { s -> println(s) }
    }

    fun sleep(time: Long) {
        try {
            Thread.sleep(time)
        } catch(e: InterruptedException) {
            println("Fucked up!")
        }
    }
}