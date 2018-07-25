package ru.job4j.concurrency

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.RepeatedTest

internal class ConcurrencyFirstLastListTest {
    private var storage = ConcurrencyFirstLastList<Int>()

    @BeforeEach
    private fun setUp() {
        val a = StorageAdd(storage, 1)
        val b = StorageAdd(storage, 2)
        val c = StorageAdd(storage, 3)
        val d = StorageAdd(storage, 4)
        val e = StorageAdd(storage, 5)
        a.start()
        b.start()
        c.start()
        d.start()
        e.start()
        a.join()
        b.join()
        c.join()
        d.join()
        e.join()
    }

    inner class StorageAdd constructor(private var storage : ConcurrencyFirstLastList<Int>, private val value: Int) : Thread() {
        override fun run() {
            this.storage.add(value)
        }
    }
    inner class StorageInsertFirst constructor(private var storage : ConcurrencyFirstLastList<Int>, private val value: Int) : Thread() {
        override fun run() {
                this.storage.insetFirst(value)

        }
    }
    inner class StorageDeleteFirst constructor(private var storage : ConcurrencyFirstLastList<Int>) : Thread() {
        override fun run() {
            this.storage.deleteFirst()
        }
    }
    inner class StorageDeleteLAst constructor(private var storage : ConcurrencyFirstLastList<Int>) : Thread() {
        override fun run() {
            this.storage.deleteLast()
        }
    }

    /**
     * Все значение гарантированно добавляются в коллекцию.
     * Без гарантии порядка добавления.
     */
    @RepeatedTest(10)
    fun whenAddThenConcurrency() {
        assertThat(storage.size, `is`(5))
        println(storage + " ")
    }

    /**
     * Производит гарантировнную вставку а начало списка
     */
    @RepeatedTest(10)
    fun whenInSertFirstThenConcerrency() {
        val f = StorageInsertFirst(storage, 6)
        val g = StorageInsertFirst(storage, 7)
        val h = StorageInsertFirst(storage, 8)
        val s = StorageInsertFirst(storage, 9)
        val i = StorageInsertFirst(storage, 10)
        f.start()
        g.start()
        h.start()
        s.start()
        i.start()
        f.join()
        g.join()
        h.join()
        s.join()
        i.join()
        assertThat(storage.size, `is`(10))
        println(storage + "")
    }

    @RepeatedTest(10)
    fun whenInDeleteFirstThenConcerrency() {
        val f = StorageDeleteFirst(storage)
        val g = StorageDeleteFirst(storage)
        val h = StorageDeleteFirst(storage)
        val s = StorageDeleteFirst(storage)
        f.start()
        g.start()
        h.start()
        s.start()
        f.join()
        g.join()
        h.join()
        s.join()
        assertThat(storage.size, `is`(1))

        println(storage + " ")
    }

    @RepeatedTest(10)
    fun whenInDeleteLastThenConcerrency() {
        val f = StorageDeleteLAst(storage)
        val g = StorageDeleteLAst(storage)
        val h = StorageDeleteLAst(storage)
        val s = StorageDeleteLAst(storage)
        f.start()
        g.start()
        h.start()
        s.start()
        f.join()
        g.join()
        h.join()
        s.join()
        assertThat(storage.size, `is`(1))
        println(storage + " ")
    }
}