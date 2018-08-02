package ru.job4j.concurrency

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test


class ConcurrencyListTest {
    private var conrencyList = ConcurrencyList<Int>()
    @BeforeEach
    fun setUp() {
        conrencyList.add(1)
        conrencyList.add(2)
        conrencyList.add(3)
        conrencyList.add(4)
    }

    private inner class StorageGet constructor(private var storage: ConcurrencyList<Int>, private var index : Int) : Thread() {
         override fun run() {
            this.storage.get(index)
        }
    }

    /**
     * Проверяет что размер кооллекци изменяется каждый раз, при удалении элементов и
     * получаемые элементы эквиваленты ожидаемым
     */
    @Test
    fun whenRemoveThatSizeDecreased() {
        assertThat(conrencyList.get(0), `is`(1))
        conrencyList.remove(0)
        assertThat(conrencyList.get(0), `is`(2))
        assertThat(conrencyList.get(1), `is`(3))
        assertThat(conrencyList.get(2), `is`(4))
        conrencyList.remove(0)
        conrencyList.remove(0)
        conrencyList.remove(0)
        assertThat(conrencyList.size(), `is`(0))
    }

    /**
     * Проверяет, при вызове метода conrencyList.get() из потока a, будет ждать пока в коллекцию добавят элемент
     */
    @RepeatedTest(10)
    fun whenCollectionNullItemsThenGetExpectTheAppearanceOfItems() {
        val a = StorageGet(conrencyList, 0)
        conrencyList.remove(0)
        conrencyList.remove(0)
        conrencyList.remove(0)
        conrencyList.remove(0)
        a.start()
        conrencyList.add(1)
        a.join()
        assertThat(conrencyList.get(0), `is`(1))
    }

    @Test()
    fun givenIteratorWhenGoesOverBorderThenThrowException() {
        val it = conrencyList.iterator()
        assertThat(it.hasNext(), CoreMatchers.`is`(true))
        assertThat(it.next(), CoreMatchers.`is`(1))
        assertThat(it.next(), CoreMatchers.`is`(2))
        assertThat(it.next(), CoreMatchers.`is`(3))
        assertThat(it.next(), CoreMatchers.`is`(4))
        assertThat(it.hasNext(), CoreMatchers.`is`(false))
        try {
            it.next()
        } catch (e : NoSuchElementException) {
            println("no such")
        }
    }

}