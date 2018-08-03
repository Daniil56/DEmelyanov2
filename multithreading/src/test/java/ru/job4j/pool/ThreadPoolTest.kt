package ru.job4j.pool

import org.junit.jupiter.api.RepeatedTest

class ThreadPoolTest {
    @RepeatedTest(10)
    fun whenPoolTest() {
        val threadPool = ThreadPool()
        for (i in 0..9) {
            threadPool.work(object : Thread() {
                override fun run() {
                    println(" job $i")

                }
            })
        }
        threadPool.shutdown()
        threadPool.work(object : Thread() {
            override fun run() {
                println("this message should't be printed")
            }
        })
    }

}