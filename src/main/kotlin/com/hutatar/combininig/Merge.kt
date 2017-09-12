package com.hutatar.combininig

import io.reactivex.Observable
import java.util.*
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    val a = Combinning()
    a.properMergeSourcesExample()
//    a.mergeFromList()
//    a.mergeArray()
//    a.mergeWith()
//    a.merge()
}

class Combinning {
    fun merge() {
        val source1: Observable<String> = Observable.just("raz","dwa","trzy","cztery")
        val source2: Observable<String> = Observable.just("piec","szesc","siedem","osiem")

        Observable.merge(source1, source2)
                .subscribe{ println(it) }
        Observable.merge(source2, source1)
                .subscribe{ println(it) }
    }

    fun mergeWith() {
        val source1: Observable<String> = Observable.just("raz","dwa","trzy","cztery")
        val source2: Observable<String> = Observable.just("piec","szesc","siedem","osiem")

        source1.mergeWith(source2).subscribe { println(it) }
    }

    fun mergeArray() {
        val s1: Observable<Int> = Observable.just(1, 2)
        val s2: Observable<Int> = Observable.just(3, 4)
        val s3: Observable<Int> = Observable.just(5, 6)
        val s4: Observable<Int> = Observable.just(7, 8)

        Observable
                .mergeArray(s1,s2,s3,s4)
                .subscribe{ println(it) }
    }

    fun mergeFromList() {
        val s1: Observable<Int> = Observable.just(1, 2)
        val s2: Observable<Int> = Observable.just(3, 4)
        val s3: Observable<Int> = Observable.just(5, 6)
        val s4: Observable<Int> = Observable.just(7, 8)

        val list: List<Observable<Int>> = Arrays.asList(s1,s2,s3,s4)

        Observable.merge(list).subscribe { println(it) }
    }

    fun properMergeSourcesExample() {
        val i1 = Observable
                .interval(2, TimeUnit.SECONDS)
                .map { i -> "source1 $i" }
        val i2 = Observable
                .interval(3, TimeUnit.SECONDS)
                .map { i -> "source2 $i" }
        val i3 = Observable
                .interval(5, TimeUnit.SECONDS)
                .map { i -> "source3 $i" }

        Observable.merge(i1, i2, i3).subscribe { println(it) }

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