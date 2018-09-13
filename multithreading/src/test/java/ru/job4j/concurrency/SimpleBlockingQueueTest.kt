package ru.job4j.concurrency

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.RepeatedTest

internal class SimpleBlockingQueueTest {
    private val queue = SimpleBlockingQueue<Int>()

    inner class Consumer constructor(private val storage: SimpleBlockingQueue<Int>) : Thread() {
        override fun run() {
            this.storage.poll()
        }
    }

    inner class Producer constructor(private val storage: SimpleBlockingQueue<Int>, private val value: Int) : Thread() {
        override fun run() {
            this.storage.offer(value)
        }
    }

    @RepeatedTest(10)
    fun whenConcurrencyThenProducer() {
        val f = Consumer(queue)
        val g = Consumer(queue)
        val h = Consumer(queue)
        val a = Producer(queue, 1)
        val b = Producer(queue, 2)
        val c = Producer(queue, 3)
        a.start()
        a.join()
        assertThat(queue.queue.size, `is`(1))
        f.start()
        f.join()
        assertThat(queue.queue.size, `is`(0))
        b.start()
        b.join()
        assertThat(queue.queue.size, `is`(1))
        g.start()
        g.join()
        assertThat(queue.queue.size, `is`(0))
        c.start()
        c.join()
        assertThat(queue.queue.size, `is`(1))
        h.start()
        h.join()
        assertThat(queue.queue.size, `is`(0))
    }
}