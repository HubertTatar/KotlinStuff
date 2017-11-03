package com.hutatar.stuff

import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.io.File

fun main(args : Array<String>) {
    val fr = FileRead()
    fr.thirdReader()
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
        val producer: Observable<String> = Observable.create(ObservableOnSubscribe<String> { emitter ->
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

        val observer: Observer<String> = object: Observer<String> {
            override fun onComplete() {
                println("complete")
            }

            override fun onError(e: Throwable) {
                println("error " + e.message)
            }

            override fun onNext(t: String) {
                println(t)
            }

            override fun onSubscribe(d: Disposable) {
                println("subscribed")
            }
        }

        producer.subscribe(observer)
    }

    fun sleep(millis: Long) {
        try {
            Thread.sleep(millis)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}