package ru.job4j.concurrency

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test

internal class ConcurrencyFirstLastListTest {
    private val storage = ConcurrencyFirstLastList<Int>()

    inner class StorageAdd constructor(private var storage: ConcurrencyFirstLastList<Int>, private val value: Int) : Thread() {
        override fun run() {
            this.storage.offer(value)
        }
    }

    inner class StoragePoll constructor(private var storage: ConcurrencyFirstLastList<Int>) : Thread() {
        override fun run() {
            this.storage.poll()
        }
    }

    @Test()
    fun whenAddThenConcurrency() {
        val a = StorageAdd(storage, 1)
        val b = StorageAdd(storage, 2)
        val c = StoragePoll(storage)
        val d = StorageAdd(storage, 3)
        val e = StorageAdd(storage, 4)
        storage.poll()
        a.start()
        b.start()
        c.start()
        println(storage + " ")
        storage.poll()
        d.start()
        e.start()
        a.join()
        b.join()
        c.join()
        d.join()
        e.join()
    }

    @Test
    fun severalThreadsWorkWithCollection() {
        for (i in 0..199) {
            val t = object : Thread() {
                override fun run() {
                    for (j in 0..999) {
                        storage.offer(10)
                    }
                }
            }
            t.start()
            t.join()
        }
        var sum = 0
        for (anInt in storage) {
            sum += anInt
        }
        assertThat(sum, `is`(2000000))
    }

    @Test
    fun givenListCreatedByVarArgsConstructorWhenAddItemsThenItemsAreReachable() {
        val ints = ConcurrencyFirstLastList(5, 10)
        assertThat(ints.get(0), `is`(5))
        assertThat(ints.get(1), `is`(10))
        assertThat(ints.size(), `is`(2))
        ints.offer(15)
        assertThat(ints.get(2), `is`(15))
        assertThat(ints.size(), `is`(3))

    }

    @Test()
    fun givenIteratorWhenGoesOverBorderThenThrowException() {
        val ints = ConcurrencyFirstLastList(5, 6, 3)
        val it = ints.iterator()
        assertThat(it.hasNext(), `is`(true))
        assertThat(it.next(), `is`(5))
        assertThat(it.next(), `is`(6))
        assertThat(it.next(), `is`(3))
        assertThat(it.hasNext(), `is`(false))
        try {
            it.next()
        } catch (e : NoSuchElementException) {
            println("no such")
        }

    }
}

