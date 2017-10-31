package com.hutatar.stuff

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.io.File

fun main(args : Array<String>) {
    val fr = FileRead()
    fr.secondReader()
    fr.sleep(1000)
}

class FileRead {


    fun firstReader() {
        val a: File = File("C:\\Users\\hubert.tatar\\workspace\\server\\apache-tomcat-8.5.9\\logs\\catalina.out")
        a.useLines {
            lines -> lines.forEach { println(it) }
        }
    }

    fun secondReader() {
        Observable.create(ObservableOnSubscribe<String> { emitter ->
                    try {
                        val a: File = File("C:\\Users\\hubert.tatar\\workspace\\server\\apache-tomcat-8.5.9\\logs\\catalina.out")
                        a.useLines {
                            lines -> lines.forEach { emitter.onNext(it) }
                        }
                        emitter.onComplete()
                    } catch (error: Exception) {
                        emitter.onError(error)
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe{ result ->  println(result) }
    }

    fun thirdReader() {
//        ObservableEmitter
    }

    fun sleep(millis: Long) {
        try {
            Thread.sleep(millis)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}