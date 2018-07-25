package ru.job4j.concurrency

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test


class ConcurrencyListTest {
    private var conrencyList = ConcurrencyList<Int>()

    @BeforeEach
    fun setUp() {
        val first = StorageAdd(conrencyList, 1)
        val s = StorageAdd(conrencyList, 2)
        val t = StorageAdd(conrencyList, 3)
        val f = StorageAdd(conrencyList, 4)
        val v = StorageAdd(conrencyList, 5)
        val e = StorageAdd(conrencyList, 6)
        first.start()
        s.start()
        t.start()
        f.start()
        v.start()
        e.start()
        first.join()
        s.join()
        t.join()
        f.join()
        v.join()
        e.join()
    }

    private inner class StorageAdd  constructor(private var storage: ConcurrencyList<Int>, private  val value: Int) : Thread() {
        override fun run() {
            this.storage.add(value)}
    }

    private inner class StorageGet constructor(private var storage: ConcurrencyList<Int>, private var index : Int) : Thread() {
         override fun run() {
            this.storage.get(index)
        }
    }

    /**
     * Создает 10 тестов в каждом из которых 6 потоков.
     * Порядок добавляения в коллекцию не гарантируется, гарантируется добавления всех элементов.
     * println(conrencyList + " " ) выводит массив интов на печать
     */
     @RepeatedTest(10)
    fun whenAddThenConcurrency() {
        assertThat(conrencyList.size(), `is`(6))
         println(conrencyList + " ")
     }

    /**
     * Метод должен проверять потокобезопасность метода get,
     * но Thread.start - не имеет возвращаемого значения
     */
    @Test
    fun whenGetSynchroThenConcurrency() {
        val a = StorageGet(conrencyList, 0)
        val b = StorageGet(conrencyList, 1)
        val c = StorageGet(conrencyList, 2)
        val d = StorageGet(conrencyList, 3)
        val e = StorageGet(conrencyList, 4)
        val f = StorageGet(conrencyList, 5)
        a.start()
        b.start()
        c.start()
        d.start()
        e.start()
        f.start()
        a.join()
        b.join()
        c.join()
        d.join()
        e.join()
        f.join()
    }
 }