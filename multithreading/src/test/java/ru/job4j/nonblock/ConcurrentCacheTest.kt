package ru.job4j.nonblock

import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Test


internal class ConcurrentCacheTest {

    private val storage = ConcurrentCache()

    @Before
    fun setUp() {
        val a = storageAdd(storage, Base(1, 1, 10))
        val b = storageAdd(storage, Base(1, 2, 20))
        val c = storageAdd(storage, Base(1, 3, 30))
        a.start()
        b.start()
        c.start()
        a.join()
        b.join()
        c.join()
    }

    inner class storageAdd constructor(private val storage: ConcurrentCache, private val base: Base) : Thread() {
        override fun run() {
            this.storage.add(base)
        }
    }

    inner class storageUpdateId constructor(private val storage: ConcurrentCache, private val base: Int, private val value: Int) : Thread() {
        override fun run() {
            this.storage.upDateId(base, value)
        }
    }

@Test
fun whenSetUpThenSizeIs3() {
    MatcherAssert.assertThat(storage.map.size, Matchers.`is`(3))
    MatcherAssert.assertThat(storage.getBase(1), Matchers.`is`(Base(1, 1, 10)))
    MatcherAssert.assertThat(storage.getBase(2), Matchers.`is`(Base(1, 2, 20)))
    MatcherAssert.assertThat(storage.getBase(3), Matchers.`is`(Base(1, 3, 30)))
}
    @Test//(expected = OptimisticException::class)
    fun whenAddThenConcurrUpdate() {
        val d = storageUpdateId(storage, 1, 11)
        val e = storageUpdateId(storage, 2, 22)
        val f = storageUpdateId(storage, 3, 33)

        val g = storageUpdateId(storage, 1, 12)
        val h = storageUpdateId(storage, 2, 23)
        val i = storageUpdateId(storage, 3, 34)
        d.start()
    //    g.start()
        e.start()
      //  h.start()
        f.start()
    //    i.start()
        i.join()
        f.join()
        g.join()
        d.join()
        h.join()
        e.join()
        println(storage.map)
    }

    @Test
fun whenDelete() {
        storage.delete(Base(1, 1, 10))
        assertThat(storage.map.size, `is`(2))

    }
}