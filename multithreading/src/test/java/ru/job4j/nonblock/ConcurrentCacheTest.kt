package ru.job4j.nonblock

import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.hamcrest.Matchers.`is`
import org.junit.Test


internal class ConcurrentCacheTest {

    private val storage = ConcurrentCache()

    inner class updateThread constructor(private val stor : ConcurrentCache, private val id: Int, private val value : Int ) : Thread() {
        override fun run() {
            stor.upDateValue(id ,value)
        }
    }

    @Test()
    fun whenUpdateValueThenVersionIsNotUpdate() {

        storage.add(Base(7, 5))
        val threadLocal = updateThread(storage, 7, 10)
        val threadLocal2 = updateThread(storage, 7, 15)
        threadLocal.start()
      // threadLocal2.start()
        threadLocal.join()
        threadLocal2.join()
        assertThat(storage.getBase(7).version, `is`(1))
        assertThat(storage.getBase(7).getValue(), `is`(10))
    }



    @Test()
    fun whenSetUpThenSizeIs3() {
        storage.add(Base(1, 10 ))
        storage.add(Base(2, 20))
        storage.add(Base(3, 30))
        MatcherAssert.assertThat(storage.size(), Matchers.`is`(3))
        MatcherAssert.assertThat(storage.getBase(1), Matchers.`is`(Base(1, 10)))
        MatcherAssert.assertThat(storage.getBase(2), Matchers.`is`(Base(2, 20)))
        MatcherAssert.assertThat(storage.getBase(3), Matchers.`is`(Base(3, 30)))
    }



    @Test
    fun whenDelete() {
        storage.add(Base(1, 1))
        storage.delete(Base(1, 1))
        assertThat(storage.size(), `is`(0))
    }

}